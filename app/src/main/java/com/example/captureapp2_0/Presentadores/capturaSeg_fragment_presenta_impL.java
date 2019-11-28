package com.example.captureapp2_0.Presentadores;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.presentador_captureFragment_interface;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.ui_vistas.cap_segnals.captacion_Fragment_view;
import com.example.captureapp2_0.objetos.Obj_Context;

import java.util.ArrayList;
import java.util.List;

public class capturaSeg_fragment_presenta_impL implements presentador_captureFragment_interface {
    private captacion_Fragment_view fragmentView;

    public capturaSeg_fragment_presenta_impL(captacion_Fragment_view fragmentView) {
        this.fragmentView = fragmentView;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void varificar_servicios() {
        if (ActivityCompat.checkSelfPermission(Obj_Context.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
                fragmentView.activar_servicios();
            }

    }

    @Override
    public void escaneo_wifi_bluethoo() {
        BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bAdapter.enable()){
            bAdapter.disable();
            //fragmentView.agregar_viewpager(ejemploLista,ejemploLista2);
        }else{
            bAdapter.enable();
        }


    }
}
