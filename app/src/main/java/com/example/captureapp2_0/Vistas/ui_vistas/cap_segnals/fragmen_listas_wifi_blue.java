package com.example.captureapp2_0.Vistas.ui_vistas.cap_segnals;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.captureapp2_0.R;
import com.example.captureapp2_0.objetos.Obj_Context;

import java.util.ArrayList;
import java.util.List;

public class fragmen_listas_wifi_blue extends Fragment {
    ListView listView;
    View view;
    ArrayAdapter<String> adaptador;
    public  List<String> ejemploLista2 = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragmen_listas_wifi_blue,container,false);
        listView=view.findViewById(R.id.Lista_señales);
        cargarlista();
        return view;
    }
    void cargarlista(){
        if(listView!=null){
            Log.e("estatus",ejemploLista2.get(0));
            adaptador = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_expandable_list_item_1, ejemploLista2);
            listView.setAdapter(adaptador);
        }

    }
}
