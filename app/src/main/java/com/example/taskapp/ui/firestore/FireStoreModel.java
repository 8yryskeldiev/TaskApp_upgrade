package com.example.taskapp.ui.firestore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FireStoreModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FireStoreModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is firestore fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}