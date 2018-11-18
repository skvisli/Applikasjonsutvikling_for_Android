package com.example.sondre.hangman;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.HashMap;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<HashMap> alphabetList = new MutableLiveData<>();
    private HashMap map = new HashMap();
    //TODO: Implement ViewModel

}
