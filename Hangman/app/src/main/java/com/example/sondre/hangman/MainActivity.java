package com.example.sondre.hangman;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Sondre";
    private SharedViewModel sharedViewModel;
    TextView textViewOutput;
    private LinearLayout linearLayoutWord;
    private String word1 = "abc";
    private String word2 = "bca";
    private String word3 = "cba";
    private String word4 = "bac";
    private String word5 = "cab";
    private String word6 = "abcabc";
    private TextView[] textViewArray;
    private Context context;

    private ArrayList<String> wordList = new ArrayList<>();
    private String currentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        wordList.add(word1);
        wordList.add(word2);
        wordList.add(word3);
        wordList.add(word4);
        wordList.add(word5);
        //wordList.add(word6);

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        textViewOutput = findViewById(R.id.textView_output);
        linearLayoutWord = findViewById(R.id.linearLayout_word);
        sharedViewModel.getAlphabetList().observe(this, new Observer<HashMap>() {
            @Override
            public void onChanged(@Nullable HashMap hashMap) {
                //Log.i("Sondre", hashMap.get('a').toString());
                //isPartOfWord('a');
            }
        });
        startGame();
    }

    private void startGame() {
        buildWordOnScreen(pickNewWord());
    }

    private String pickNewWord() {
        Random rand = new Random();
        String randomWord = wordList.get(rand.nextInt(wordList.size()));
        currentWord = randomWord;
        Log.i(TAG, randomWord);
        return randomWord;
    }

    private void buildWordOnScreen(String word) {
        linearLayoutWord.removeAllViews();
        textViewArray = new TextView[word.length()];
        for(int i = 0; i < word.length(); i++) {
            TextView textView= new TextView(this);
            textView.setWidth(100);
            textView.setText("  ");
            textView.setTextSize(32);
            textView.setPaintFlags(textView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            textViewArray[i] = textView;
            linearLayoutWord.addView(textViewArray[i]);
        }
    }

    public void isPartOfWord(char ch) {
        for (int i = 0; i < currentWord.length(); i++){
            char c = currentWord.charAt(i);
            if (ch == c) {
                //Log.i("Sondre", "isPartof");
                addCorrectChar(ch, i);
            }
        }
    }

    private void addCorrectChar(char ch, int i) {
        textViewArray[i].setText(String.valueOf(ch));
        isWordSolved();
        //Log.i("Sondre", "addCorrectChar");
    }

    public boolean isWordSolved() {
        for (int i = 0; i < linearLayoutWord.getChildCount(); i++) {
            if (linearLayoutWord.getChildAt(i) instanceof TextView) {
                if (((TextView) linearLayoutWord.getChildAt(i)).getText().equals("  ")) {
                    textViewOutput.setText("Ikke løst");
                    return false;
                }
            }
        }
        textViewOutput.setText("FERDIG");
        buildWordOnScreen(pickNewWord());
        return true;
    }
}
