package com.example.sondre.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ((TextView)findViewById(R.id.textView_help_text)).setText(R.string.help_text);
        findViewById(R.id.imageView_back).setOnClickListener((v) -> finish());
    }
}
