package com.example.sondre.oving6client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Client().start();
    }
}
