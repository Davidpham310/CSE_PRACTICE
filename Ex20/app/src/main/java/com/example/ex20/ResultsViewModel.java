package com.example.ex20;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ResultsViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<String>> results = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<String>> getResults() {
        return results;
    }

    public void addResult(String result) {
        ArrayList<String> currentResults = results.getValue();
        if (currentResults != null) {
            currentResults.add(result);
            results.setValue(currentResults);
        }
    }
}
