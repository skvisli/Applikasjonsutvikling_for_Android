package com.example.sondre.hangman;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.HashMap;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<HashMap> alphabetList = new MutableLiveData<>();
    private HashMap map = new HashMap();

    public SharedViewModel() {
        char ch;
        for (ch = 'a'; ch <= 'z'; ch++) {
            map.put(ch, false);
        }
        alphabetList.setValue(map);
    }

    public MutableLiveData<HashMap> getAlphabetList() {
        return alphabetList;
    }

    public void setKeyPressed(char key) {
        map.put(key, true);
        alphabetList.postValue(map);
    }

}
