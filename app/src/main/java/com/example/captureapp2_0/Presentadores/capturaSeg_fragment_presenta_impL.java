package com.example.captureapp2_0.Presentadores;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import com.example.captureapp2_0.Modelo.Modelo.Interactores.captura_sign_fragment_impL;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.Interactor_captuSig;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.onlistener_captura_segnas_interf;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.presentador_captureFragment_interface;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;
import com.example.captureapp2_0.Vistas.cap_segnals.captacion_Fragment_view;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;

import java.util.ArrayList;

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
    public void verificar_servicios() {
        if (isOnline(Obj_Context.getContext())){
            bAdapter.disable();
            if (ActivityCompat.checkSelfPermission(Obj_Context.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                fragmentView.activar_servicios();
            }
        }else {
            fragmentView.mensaje_servicios_wifi();
        }
        Obje_servi servi=new Obje_servi();
        servi=interactor_captuSig.verificar_servidor(servi);
        if(servi!=null){
            fragmentView.cargar_datos_servi(servi);
        }else{
            enviar_error_enservidores("No se tiene ningun servidor registrado\n" +
                    "Favor de registrar primero un servidor");
        }

    }

    @Override
    public void verificar_datos_sin_conex() {
        interactor_captuSig.verificar_recuperar_datos();
    }

    @Override
    public void retornar_datos_sin_conx(ArrayList<Obj_bluetooth> Array_bluet, ArrayList<Obj_wifi> Array_wifi) {
        fragmentView.Mensaje_datos_capt_sin_servidor(Array_bluet,Array_wifi);
    }

    @Override
    public void enviar_datos_sinconex(ArrayList<Obj_bluetooth> Array_bluet, ArrayList<Obj_wifi> Array_wifi, Obje_servi obje_servi) {
        if (fragmentView!=null){
            fragmentView.progressbar_show();
            interactor_captuSig.envio_datos_sinconex(Array_bluet,Array_wifi,obje_servi);
        }

    }

    @Override
    public void dismi_progress(String mensajen) {
        if (fragmentView!=null){
            fragmentView.progressbar_hiden(mensajen);
        }
    }

    @Override
    public boolean verificar_conexion_servi(String DDNS,String IP) {
        if (interactor_captuSig.Salida_internet(DDNS,IP))
            return true;
        else
            return false;
    }

    @Override
    public void enviar_error_enservidores(String mensaje) {
        if (fragmentView!=null){
            fragmentView.error_en_servidors(mensaje);
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    @Override
    public void escaneo_wifi_bluethoo_con_conexion(Obje_servi obje_servi) {
        if (bAdapter.enable()){
            bAdapter.disable();
            Toast.makeText(Obj_Context.getContext(),"Bluetooth activado",Toast.LENGTH_LONG).show();
            fragmentView.agregar_viewpager_conexion_internet(obje_servi);
        }
    }

    @Override
    public void escaneo_wifi_bluethoo_sin_conexion() {
        if (bAdapter.enable()){
            bAdapter.disable();
            Toast.makeText(Obj_Context.getContext(),"Bluetooth activado",Toast.LENGTH_LONG).show();
            fragmentView.viewpager_sin_conexion_internet();
        }
    }

    @Override
    public void destruir(){
            bAdapter.disable();
    }

    /*@Override
    public void enviarA_viewpager(List<String> wifi, List lista_blue, List lista_completa) {
        fragmentView.agregar_viewpager_conexion_internet();
    }*/
}
