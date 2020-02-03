package com.example.captureapp2_0.Vistas.ui_vistas.informacion;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.captureapp2_0.R;

public class Informacion_app extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_informacion, container, false);
        Log.e("se entro","mas informacion");
        return root;
    }
}