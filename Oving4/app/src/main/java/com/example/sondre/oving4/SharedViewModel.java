package com.example.sondre.oving4;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

public class SharedViewModel extends ViewModel {


    private final MutableLiveData<Integer> currentDrawable = new MutableLiveData<Integer>();

    public SharedViewModel() {
        currentDrawable.setValue(0);
    }

    public void setCurrentDrawable(Integer integer) {
        currentDrawable.setValue(integer);
        Log.i("Sondre", integer.toString());
    }

    public LiveData<Integer> getCurrentDrawable() {
        return currentDrawable;
    }

}
