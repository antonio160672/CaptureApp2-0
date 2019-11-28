package com.example.captureapp2_0.Presentadores;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import com.example.captureapp2_0.Interactores.captura_sign_fragment_impL;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.Interactor_captuSig;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.onlistener_captura_segnas_interf;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.presentador_captureFragment_interface;

import com.example.captureapp2_0.Vistas.ui_vistas.cap_segnals.captacion_Fragment_view;
import com.example.captureapp2_0.objetos.Obj_Context;
import java.util.List;

public class capturaSeg_fragment_presenta_impL implements presentador_captureFragment_interface, onlistener_captura_segnas_interf {
    private captacion_Fragment_view fragmentView;
    private Interactor_captuSig interactor_captuSig;
    private BluetoothAdapter bAdapter;
    WifiManager admini_wifi;
    public capturaSeg_fragment_presenta_impL(captacion_Fragment_view fragmentView) {
        this.fragmentView = fragmentView;
        bAdapter = BluetoothAdapter.getDefaultAdapter();
        interactor_captuSig=new captura_sign_fragment_impL(this);
        admini_wifi=(WifiManager)Obj_Context.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void varificar_servicios() {
        if (isOnline(Obj_Context.getContext())){
            bAdapter.disable();
            if (ActivityCompat.checkSelfPermission(Obj_Context.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                fragmentView.activar_servicios();
            }
        }else {
            fragmentView.mensaje_servicios_wifi();
        }

    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    @Override
    public void escaneo_wifi_bluethoo() {
        if (bAdapter.enable()){
            bAdapter.disable();
            Toast.makeText(Obj_Context.getContext(),"Bluetooth activado",Toast.LENGTH_LONG).show();
            fragmentView.agregar_viewpager();
        }
    }
    @Override
    public void destruir(){
            bAdapter.disable();
    }

    @Override
    public void enviarA_viewpager(List<String> wifi, List lista_blue, List lista_completa) {
        fragmentView.agregar_viewpager();
    }
}
