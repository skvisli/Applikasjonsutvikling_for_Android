package com.example.sondre.hangman;


import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameLogic {

    private ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(
            "hestehode",
            "hårstrikk"
            /*"hårstrikk",
            "datamaskin",
            "android",
            "java",
            "python"*/)
    );
    private List<String> unusedWordsList = new ArrayList<>(wordsList);

    private String[] localWord;
    private int numOfWrongAnswers;
    private int numOfWordsPlayed = 0;
    private int numOfWins = 0;
    private int numOfLosses = 0;

    private String currentWord;

    private String TAG = "Sondre";

    MainActivity mainActivity;

    public GameLogic(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mainActivity.setTextViewNumOfWins(numOfWins);
        mainActivity.setTextViewNumOfLosses(numOfLosses);
        mainActivity.setTextViewOutput("NYTT SPILL");
        createNewWord();
    }

    public void createNewWord() {
        initialiseNewWord(pickRandomWord());
        mainActivity.hideNewWordButton();
        mainActivity.hideStartOverButton();
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
        mainActivity.keyboardFragmen.clearKeysPressed();

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
                //Log.i("Sondre", "isPartof");
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

    private void isOutOfTries() {
        if (numOfWrongAnswers >= 10) {
            numOfLosses += 1;
            mainActivity.setTextViewNumOfLosses(numOfLosses);
            mainActivity.setTextViewOutput("DU TAPTE");
            mainActivity.keyboardFragmen.disableAllKeys();
            mainActivity.showSolution(currentWord);
            if (!unusedWordsList.isEmpty()) {
                mainActivity.showNewWordButton();
            } else {
                mainActivity.showStartOverButton();
            }
        }
    }

    public boolean isWordSolved() {
        for (int i = 0; i < localWord.length; i++) {
            if (localWord[i].equals("")) {
                mainActivity.setTextViewOutput("ikke ferdig");
                return false;
            }
        }
        numOfWins += 1;
        mainActivity.setTextViewNumOfWins(numOfWins);
        mainActivity.setTextViewOutput("FERDIG");
        mainActivity.keyboardFragmen.disableAllKeys();
        if (unusedWordsList.isEmpty()) {
            mainActivity.showStartOverButton();
        } else {
            mainActivity.showNewWordButton();
        }
        return true;
    }
}
