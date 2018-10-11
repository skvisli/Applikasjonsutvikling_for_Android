package com.example.oving2tester;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_GET_RANDOM = "com.example.oving2.ACTION_GET_RANDOM";
    static final int GET_RANDOM_INT_REQUEST = 1;  // The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goTo(View view) {
        // Create the text message with a string
        Intent intent = new Intent(ACTION_GET_RANDOM);
        intent.putExtra(Intent.EXTRA_TEXT, 100);
        intent.setType("text/plain");

        // Verify that the intent will resolve to an activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == GET_RANDOM_INT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                CharSequence text = String.valueOf(data.getIntExtra(Intent.EXTRA_TEXT, 0));
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}
