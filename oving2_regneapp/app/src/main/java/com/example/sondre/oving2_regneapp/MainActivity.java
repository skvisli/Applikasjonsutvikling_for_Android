package com.example.sondre.oving2_regneapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView_num1;
    TextView textView_num2;
    EditText editText_answ;
    EditText editText_upper_limit;
    String toast_text;
    boolean nextNum;

    public static final String ACTION_GET_RANDOM = "com.example.oving2.ACTION_GET_RANDOM";
    static final int GET_RANDOM_INT_REQUEST = 1;  // The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_num1 = findViewById(R.id.textView_num1);
        textView_num2 = findViewById(R.id.textView_num2);
        editText_answ = findViewById(R.id.editText_answ);
        editText_upper_limit = findViewById(R.id.editText_upper_limit);

        // Input is set to numberPassword for best keyboard experience
        // setTransformationMethod(null) prevents hiding of characters
        editText_answ.setTransformationMethod(null);
        editText_upper_limit.setTransformationMethod(null);

        setEnterListener();

    }

    public void add(View view) {
        try {
            int num1 = Integer.parseInt(textView_num1.getText().toString());
            int num2 = Integer.parseInt(textView_num2.getText().toString());
            int answ = Integer.parseInt(editText_answ.getText().toString());
            if (num1 + num2 == answ) {
                toast_text = getString(R.string.correct);
            } else {
                toast_text = getString(R.string.wrong) + " " + (num1 + num2);
            }
            setNewNumbers(view);
        } catch (Exception e) {
            toast_text = getString(R.string.unval);
        }
        editText_answ.setText("");
        Toast.makeText(getApplicationContext(), toast_text, Toast.LENGTH_SHORT).show();
    }

    public void multiply(View view) {
        try {
            int num1 = Integer.parseInt(textView_num1.getText().toString());
            int num2 = Integer.parseInt(textView_num2.getText().toString());
            int answ = Integer.parseInt(editText_answ.getText().toString());

            if (num1 * num2 == answ) {
                toast_text = getString(R.string.correct);
            } else {
                toast_text = getString(R.string.wrong) + " " + (num1 * num2);
            }
            setNewNumbers(view);
        } catch (Exception e) {
            toast_text = getString(R.string.unval);
        }
        editText_answ.setText("");
        Toast.makeText(getApplicationContext(), toast_text, Toast.LENGTH_SHORT).show();
    }

    public void setNewNumbers(View view) {
        getRandomNumber(view);
        getRandomNumber(view);
    }

    public void getRandomNumber(View view) {
        try {
            // Create the text message with a string
            Intent intent = new Intent(ACTION_GET_RANDOM);
            intent.putExtra(Intent.EXTRA_TEXT, Integer.parseInt(editText_upper_limit.getText().toString()));
            intent.setType("text/plain");

            // Verify that the intent will resolve to an activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 1);
            }
        } catch (Exception e) {
            toast_text = getString(R.string.unval_limit);
            Toast.makeText(getApplicationContext(), toast_text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == GET_RANDOM_INT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                if (nextNum) {
                    textView_num1.setText(String.valueOf(data.getIntExtra(Intent.EXTRA_TEXT, 0)));
                } else {
                    textView_num2.setText(String.valueOf(data.getIntExtra(Intent.EXTRA_TEXT, 0)));
                }
                nextNum = !nextNum;
            }
        }
    }

    // Adds enter button to keyboard when editing editText_upper_limit
    // Also refreshes the math problem on enter click
    private void setEnterListener() {
        editText_upper_limit.setImeOptions(6);
        editText_upper_limit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    setNewNumbers(v);
                    return true;
                }
                return false;
            }
        });
    }
}