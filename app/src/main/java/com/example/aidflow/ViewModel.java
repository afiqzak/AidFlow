package com.example.aidflow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private final MutableLiveData<Boolean> isHistoryFiltered = new MutableLiveData<>(false);
    private final MutableLiveData<Integer> selectedHistFilter = new MutableLiveData<>(0);

    public MutableLiveData<Boolean> getIsHistoryFiltered() {
        return isHistoryFiltered;
    }

    public MutableLiveData<Integer> getSelectedHistFilter() {
        return selectedHistFilter;
    }
}
