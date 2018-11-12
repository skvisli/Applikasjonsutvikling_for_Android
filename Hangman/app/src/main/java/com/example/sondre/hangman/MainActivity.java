package com.example.sondre.hangman;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Paint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private SharedViewModel sharedViewModel;
    TextView textView;
    private LinearLayout linearLayoutWord;
    private String word1 = "hangman";
    private TextView[] textViewArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        textView = findViewById(R.id.textView_output);
        linearLayoutWord = findViewById(R.id.linearLayout_word);
        sharedViewModel.getAlphabetList().observe(this, new Observer<HashMap>() {
            @Override
            public void onChanged(@Nullable HashMap hashMap) {
                //Log.i("Sondre", hashMap.get('a').toString());
                isPartOfWord('a', word1);
            }
        });
        textViewArray = new TextView[word1.length()];
        for(int i = 0; i < word1.length(); i++) {
            TextView textView= new TextView(this);
            textView.setWidth(100);
            textView.setText("  ");
            textView.setTextSize(32);
            textView.setPaintFlags(textView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            textViewArray[i] = textView;
            linearLayoutWord.addView(textViewArray[i]);
        }
        /*buildWord(word1);*/
    }

    /*private void buildWord(String word) {
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            //textViewArray[i].setText(c);

        }
    }*/

    private void isPartOfWord(char ch, String word) {
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (ch == c) {
                Log.i("Sondre", String.valueOf(ch));
                addCorrectChar(ch, i);
            }
        }
    }

    private void addCorrectChar(char ch, int i) {
        textViewArray[i].setText(String.valueOf(ch));
        Log.i("Sondre", (String.valueOf(textViewArray.length)));
    }
}
