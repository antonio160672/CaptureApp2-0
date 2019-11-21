package com.example.captureapp2_0.ui.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.captureapp2_0.R;

public class HomeFragment extends Fragment {
    private TextView titulo,text_Nombre,usuario_titu;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        activar_fuente(root);
        final TextView textView = null;//root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
    private void activar_fuente(View root) {
        titulo = root.findViewById(R.id.Titulo);   //definimos en ONCREATE donde esta por
        text_Nombre=root.findViewById(R.id.text_Nombre);// medio de un ID
        usuario_titu=root.findViewById(R.id.titulo_user);
        Typeface face=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Mohave-Bold.otf");
        titulo.setTypeface(face);
        text_Nombre.setTypeface(face);
        usuario_titu.setTypeface(face);

    }
}