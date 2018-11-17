package com.example.sondre.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class HelpActivity extends AppCompatActivity {
    private ImageView imageViewBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        imageViewBackButton = findViewById(R.id.imageView_back);
        imageViewBackButton.setOnClickListener((v) -> {
            finish();
        });
    }
}
