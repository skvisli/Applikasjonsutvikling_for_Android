package com.example.sondre.hangman;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements KeyboardFragment.OnFragmentKeyPressListener, QuitDialogFragment.OnDialogChoiceListener {
    //private SharedViewModel sharedViewModel;
    private LinearLayout linearLayoutWord;
    private Button buttonNewWord;
    private Button buttonStartOver;
    private TextView textViewNumOfWins;
    private TextView textViewNumOfLosses;
    private TextView textViewOutput;
    private TextView textViewNumOfWords;
    private ImageView imageViewHelpButton;
    private ImageView imageViewPowerButton;
    private String TAG = "Sondre";

    private TextView[] textViewArray;
    private ImageView[] hangman = new ImageView[10];

    public KeyboardFragment keyboardFragment;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumOfLosses = findViewById(R.id.textView_numOfLosses);
        textViewNumOfWins = findViewById(R.id.textView_numOfWins);
        textViewNumOfWords = findViewById(R.id.textView_numOfWords);
        imageViewHelpButton = findViewById(R.id.imageView_help);
        imageViewPowerButton = findViewById(R.id.imageView_power);
        imageViewHelpButton.setOnClickListener((v) -> openHelp());
        imageViewPowerButton.setOnClickListener((v) -> powerOff());
        buttonNewWord = findViewById(R.id.button_newWord);
        buttonStartOver = findViewById(R.id.button_start_over);
        textViewOutput = findViewById(R.id.textView_output);
        linearLayoutWord = findViewById(R.id.linearLayout_word);

        hangman[0] = findViewById(R.id.imageView_pole);
        hangman[1] = findViewById(R.id.imageView_roof);
        hangman[2] = findViewById(R.id.imageView_rope);
        hangman[3] = findViewById(R.id.imageView_head);
        hangman[4] = findViewById(R.id.imageView_torso);
        hangman[5] = findViewById(R.id.imageView_lArm);
        hangman[6] = findViewById(R.id.imageView_rArm);
        hangman[7] = findViewById(R.id.imageView_lLeg);
        hangman[8] = findViewById(R.id.imageView_rLeg);
        hangman[9] = findViewById(R.id.imageView_face);

        // TODO: Implement ViewModel
        //sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        keyboardFragment = (KeyboardFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentKeyboard);
        gameLogic = new GameLogic(this);
    }

/*    // TODO: Implement Save Instance State
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "INSTANCE SAVED");
        savedInstanceState.putString("SAVED_STRING", "exampleString");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String savedString = savedInstanceState.getString("SAVED_STRING");
        Log.i(TAG, "INSTANCE RESTORED");
        Log.i(TAG, savedString);
    }*/

    public void setTextViewOutput(String text) {
        textViewOutput.setText(text);
    }

    public void setTextViewNumOfWins(int numOfWins) {
        textViewNumOfWins.setText(String.valueOf(numOfWins));
    }

    public void setTextViewNumOfLosses(int numOfLosses) {
        textViewNumOfLosses.setText(String.valueOf(numOfLosses));
    }

    public void setTextViewNumOfWords(int numOfWordsPlayed, int numOfWords) {
        textViewNumOfWords.setText(getResources().getString(R.string.wordCounter, numOfWordsPlayed, numOfWords));
    }

    public void buildWordOnScreen(String word) {
        // Clears the hangman-progress from previous sessions
        for (ImageView imageView: hangman) {
            imageView.setVisibility(View.INVISIBLE);
        }
        // Clears old word from screen
        linearLayoutWord.removeAllViews();
        keyboardFragment.clearKeysPressed();

        textViewArray = new TextView[word.length()];
        for (int i = 0; i < word.length(); i++) {
            TextView textView = new TextView(this);
            textView.setWidth(100);
            textView.setTextSize(32);
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            textView.setText("  ");
            textViewArray[i] = textView;
            Log.i(TAG, String.valueOf(textView));
            linearLayoutWord.addView(textViewArray[i]);
        }
    }

    public void showNewWordButton() {
        buttonNewWord.setVisibility(View.VISIBLE);
    }

    public void hideNewWordButton() {
        buttonNewWord.setVisibility(View.INVISIBLE);
    }

    public void showStartOverButton() {
        buttonStartOver.setVisibility(View.VISIBLE);
    }

    public void hideStartOverButton() {
        buttonStartOver.setVisibility(View.INVISIBLE);
    }

    public void newWord(View view) {
        gameLogic.createNewWord();
    }

    public void startOver(View view) {
        gameLogic = new GameLogic(this);
    }

    public void addCorrectChar(char ch, int i) {
        textViewArray[i].setText(String.valueOf(ch));
    }

    public void addHangmanPart(int numOfWrongAnswers) {
        hangman[numOfWrongAnswers].setVisibility(View.VISIBLE);
    }

    public void showSolution(String currentWord) {
        for (int i = 0; i < currentWord.length(); i++) {
            addCorrectChar(currentWord.charAt(i), i);
        }
    }

    private void openHelp() {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivityForResult(intent, 1);
    }

    private void powerOff() {
        DialogFragment dialogFragment = new QuitDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "QuitDialogFragment");
    }

    @Override
    public void onKeyPress(char ch) {
        Log.i(TAG, String.valueOf(ch));
        gameLogic.isPartOfWord(ch);
    }

    @Override
    public void onQuit() {
        // User touched the dialog's positive button
        finishAndRemoveTask();

    }

    @Override
    public void onCancel() {
        // User touched the dialog's negative button
    }

}
