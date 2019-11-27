package com.example.captureapp2_0.Vistas.ui_vistas.cap_segnals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class captacion_fragmen_Model extends ViewModel {

    private MutableLiveData<String> mText;

    public captacion_fragmen_Model() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}