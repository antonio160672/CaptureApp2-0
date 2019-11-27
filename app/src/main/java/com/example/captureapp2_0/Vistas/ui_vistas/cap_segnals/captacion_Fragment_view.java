package com.example.captureapp2_0.Vistas.ui_vistas.cap_segnals;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.captureapp2_0.R;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class captacion_Fragment_view extends Fragment  {

    private captacion_fragmen_Model galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(captacion_fragmen_Model.class);

        View root = inflater.inflate(R.layout.fragment_captacion_segnl, container, false);


        ViewPager viewPager=root.findViewById(R.id.vista_pager);


        agregar_viewpager(viewPager);


        TabLayout tabLayout=root.findViewById(R.id.TAP);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }

    private void agregar_viewpager(ViewPager viewPager){
        List<String> ejemploLista = new ArrayList<String>();
        ejemploLista.add("Juan");
        ejemploLista.add("Pedro");
        ejemploLista.add("José");
        ejemploLista.add("María");
        ejemploLista.add("Sofía");
        List<String> ejemploLista2 = new ArrayList<String>();
        ejemploLista2.add("josefina");
        ejemploLista2.add("Paaa");
        ejemploLista2.add("Jeeee");
        ejemploLista2.add("Mrtyyy");
        ejemploLista2.add("Srtrty");
        List<String> ejemploLista3 = new ArrayList<String>();
        ejemploLista3.add("jjjj");
        ejemploLista3.add("Paaa");
        ejemploLista3.add("Jeeee");
        ejemploLista3.add("Mrtyyy");
        ejemploLista3.add("Srtrty");
        Viewpager_adaptador_view adaptador_view=new Viewpager_adaptador_view(getFragmentManager());
        adaptador_view.clearFragemen();
        adaptador_view.addFRagmen(newinstance(ejemploLista));
        adaptador_view.addFRagmen(newinstance(ejemploLista2));
        adaptador_view.addFRagmen(newinstance(ejemploLista3));
        viewPager.setAdapter(adaptador_view);
    }

    private fragmen_listas_wifi_blue newinstance(List<String> ejemploLista){
        fragmen_listas_wifi_blue fragmenwifi_blue=new fragmen_listas_wifi_blue();
        fragmenwifi_blue.ejemploLista2=ejemploLista;
        return fragmenwifi_blue;
    }
}