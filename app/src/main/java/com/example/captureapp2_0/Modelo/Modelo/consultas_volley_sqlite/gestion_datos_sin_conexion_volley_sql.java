package com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite;

import android.util.Log;

import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Modelo.Modelo.Interactores.captura_sign_fragment_impL;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;

import java.util.ArrayList;

public class gestion_datos_sin_conexion_volley_sql {
    private Obj_bluetooth bluetooth;
    private Obj_wifi wifi;
    private captura_sign_fragment_impL impL;

    public gestion_datos_sin_conexion_volley_sql(Obj_bluetooth bluetooth, Obj_wifi wifi,captura_sign_fragment_impL impL) {
        this.bluetooth = bluetooth;
        this.wifi = wifi;
        this.impL= impL;

        this.bluetooth.sqLite=new Sqlite_DB_manejo(Obj_Context.getContext());
        this.wifi.sqLite=new Sqlite_DB_manejo(Obj_Context.getContext());
    }


    public void recuperar_datos_sin_conexi(){
        String blue_sql="select * from Sin_conexion_entidad_Bluetooth";
        String wifi_sql="select * from Sin_conexion_entidad_wifi";
        ArrayList<Obj_bluetooth> Array_bluet=bluetooth.sqLite.Recuperar_lista_bluetooth(blue_sql);
        ArrayList<Obj_wifi> Array_wifi=wifi.sqLite.Recuperar_lista_wifi(wifi_sql);
        if(Array_bluet!=null&&Array_wifi!=null){
            impL.retonar_datos_sin_conex(Array_bluet,Array_wifi);
        }
    }

    public void limpieza_tablas(){
        String blue_sql="DELETE FROM Sin_conexion_entidad_Bluetooth";
        String wifi_sql="DELETE FROM Sin_conexion_entidad_wifi";
        bluetooth.sqLite.ejecutaSQL(blue_sql);
        bluetooth.sqLite.ejecutaSQL(wifi_sql);


    }
}
