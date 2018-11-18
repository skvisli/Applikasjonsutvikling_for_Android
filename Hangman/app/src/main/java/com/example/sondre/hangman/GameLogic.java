package com.example.sondre.hangman;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameLogic {

    private ArrayList<String> wordsList = new ArrayList<>();
    private ArrayList<String> unusedWordsList;
    private String currentWord;
    private String[] localWord;

    private int numOfWrongAnswers;
    private int numOfWordsPlayed = 0;
    private int numOfWins = 0;
    private int numOfLosses = 0;
    int unicode = 0x1F389;
    private String TAG = "Sondre";
    private MainActivity mainActivity;

    public GameLogic(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        Log.i(TAG, Locale.getDefault().getLanguage());
        if (Locale.getDefault().getLanguage().equals("nb") || Locale.getDefault().getLanguage().equals("nn")){
            getWordsFromFile("words_no");
        } else {
            getWordsFromFile("words");
        }
        unusedWordsList = new ArrayList<>(wordsList);
        mainActivity.setTextViewNumOfWins(numOfWins);
        mainActivity.setTextViewNumOfLosses(numOfLosses);
        mainActivity.setTextViewOutput("");
        createNewWord();
    }

    private void getWordsFromFile(String filepath) {
        int resource =  mainActivity.getResources().getIdentifier(filepath, "raw", mainActivity.getPackageName());
        InputStream in = mainActivity.getResources().openRawResource(resource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                wordsList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewWord() {
        initialiseNewWord(pickRandomWord());
        mainActivity.hideNewWordButton();
        mainActivity.hideStartOverButton();
        mainActivity.setTextViewOutput("");
    }

    private String pickRandomWord() {
        Random rand = new Random();
        String randomWord = unusedWordsList.get(rand.nextInt(unusedWordsList.size()));
        unusedWordsList.remove(randomWord);
        currentWord = randomWord;
        Log.i(TAG, randomWord);
        return randomWord;
    }

    private void initialiseNewWord(String word) {
        numOfWordsPlayed += 1;
        numOfWrongAnswers = 0;
        mainActivity.setTextViewNumOfWords(numOfWordsPlayed, wordsList.size());
        mainActivity.keyboardFragment.clearKeysPressed();
        mainActivity.buildWordOnScreen(word);

        //Creates a local copy of the word to avoid getting the chars from the TextViews in MainActivity for each comparison
        localWord = new String[word.length()];
        Arrays.fill(localWord, "");
    }

    // Calls isWordSolved if true, calls isOutOfTries if false.
    public void isPartOfWord(char ch) {
        boolean partOfWord = false;
        for (int i = 0; i < currentWord.length(); i++) {
            char c = currentWord.charAt(i);
            if (ch == c) {
                mainActivity.addCorrectChar(ch, i);
                localWord[i] = String.valueOf(ch);
                partOfWord = true;

            }
        }
        if (partOfWord) {
            isWordSolved();
        } else if (!partOfWord) {
            mainActivity.addHangmanPart(numOfWrongAnswers);
            numOfWrongAnswers += 1;
            isOutOfTries();
        }
    }

    public boolean isWordSolved() {
        for (int i = 0; i < localWord.length; i++) {
            if (localWord[i].equals("")) {
                return false;
            }
        }
        numOfWins += 1;
        mainActivity.setTextViewNumOfWins(numOfWins);
        mainActivity.setTextViewOutput(getEmojiByUnicode(unicode) + mainActivity.getResources().getString(R.string.win_text) + getEmojiByUnicode(unicode));
        mainActivity.keyboardFragment.disableAllKeys();
        if (unusedWordsList.isEmpty()) {
            mainActivity.showStartOverButton();
        } else {
            mainActivity.showNewWordButton();
        }
        return true;
    }

    private void isOutOfTries() {
        if (numOfWrongAnswers >= 10) {
            numOfLosses += 1;
            mainActivity.setTextViewNumOfLosses(numOfLosses);
            mainActivity.setTextViewOutput(mainActivity.getResources().getString(R.string.loose_text));
            mainActivity.keyboardFragment.disableAllKeys();
            mainActivity.showSolution(currentWord);
            if (!unusedWordsList.isEmpty()) {
                mainActivity.showNewWordButton();
            } else {
                mainActivity.showStartOverButton();
            }
        }
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
