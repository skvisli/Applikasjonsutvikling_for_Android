package com.example.sondre.oving6client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ClientActivity extends AppCompatActivity {
    EditText mNum1;
    EditText mNum2;
    TextView answer;
    String num1;
    String num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNum1 = findViewById(R.id.editTextNum1);
        mNum2 = findViewById(R.id.editTextNum2);
        answer = findViewById(R.id.textView);
    }

    public void calculate(View view) {
        num1 = mNum1.getText().toString();
        num2 = mNum2.getText().toString();
        Runnable r = new Client(this, num1, num2);
        new Thread(r).start();
    }

    public void setText(final String text) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                answer.setText(text);

            }
        });
    }
}
