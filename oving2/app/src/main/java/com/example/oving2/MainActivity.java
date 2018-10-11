package com.example.oving2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the UPPER_LIMIT from the intent that started the activity
        Intent intent = getIntent();
        int UPPER_LIMIT = intent.getIntExtra(Intent.EXTRA_TEXT, 0);
        Log.i("limit", String.valueOf(UPPER_LIMIT));
        int random = new Random().nextInt((UPPER_LIMIT - 0) + 1) + 0;

        // Send random int in return
        Intent result = new Intent();
        result.putExtra(Intent.EXTRA_TEXT , random);
        setResult(Activity.RESULT_OK, result);
        finish();
    }




}
