package no.hist.itfag.books;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private DatabaseManager db;
    ArrayList<Book> Books = new ArrayList<>();
    SharedPreferences sharedPref;
    String backgroundColorPref;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        backgroundColorPref = sharedPref.getString(SettingsActivity.KEY_PREF_BACKGROUND_COLOR, "");
        view = this.getWindow().getDecorView();
        setBackgroundColor(backgroundColorPref);

        // Read books from file
        InputStream in = this.getResources().openRawResource(R.raw.books);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                Books.add(new Book(parts[0], parts[1]));
                //Log.i("sondre", parts[0] + " " + parts[1]);
            }
                reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add books to SQLite
        try {
            db = new DatabaseManager(this);
             db.clean();
            for (Book book: Books) {
                long id = db.insert(book.getBookAuthor(), book.getBookTitle());
                //Log.i("sondre", book.getBookTitle() + " " + book.getBookAuthor());
            }
           //   ArrayList<String> res = db.getAllAuthors();
            ArrayList<String> res = db.getAllBooks();
         //   ArrayList<String> res = db.getBooksByAuthor("Mildrid Ljosland");
         //   ArrayList<String> res = db.getAuthorsByBook("Programmering i C++");
         //   ArrayList<String> res = db.getAllBooksAndAuthors();
            showResults(res);
        }
        catch (Exception e) {
            String tekst = e.getMessage();
            TextView t = (TextView)findViewById(R.id.tw1);
            t.setText(tekst);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundColorPref = sharedPref.getString(SettingsActivity.KEY_PREF_BACKGROUND_COLOR, "");
        setBackgroundColor(backgroundColorPref);
    }

    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list)  {
            res.append(s+"\n");
        }
        TextView t = (TextView)findViewById(R.id.tw1);
        t.setText(res);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setBackgroundColor(String color) {
        if (color.equals("Blue")){
            view.setBackgroundColor(Color.parseColor("#2df9e9"));
        }
        if (color.equals("Green")) {
            view.setBackgroundColor(Color.parseColor("#6ff979"));
        }
        if (color.equals("Red")) {
            view.setBackgroundColor(Color.parseColor("#fb5d5d"));
        }
    }

    public void showTitle(View view) {
        showResults(db.getAllBooks());
    }

    public void showAutor(View view) {
        showResults(db.getAllAuthors());
    }
}
