package com.example.captureapp2_0.Interactores;

import com.example.captureapp2_0.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Interfaces.Graficas_intF.Graficas_interactores_interface;
import com.example.captureapp2_0.Interfaces.Graficas_intF.onlistener_graficas;
import com.example.captureapp2_0.Presentadores.Graficas_presentador_impL;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_bluetooth;

public class Graficas_interactores_impL implements Graficas_interactores_interface {
    private onlistener_graficas onlistenerGraficas;
    private  String fecha_ini,fecha_fin;
    private Obj_bluetooth bluetooth;

    public Graficas_interactores_impL(onlistener_graficas onlistenerGraficas) {
        this.onlistenerGraficas = onlistenerGraficas;
        bluetooth = new Obj_bluetooth();
    }

    @Override
    public void validacion_fechas(String fecha_ini,String fecha_fin) {
        this.fecha_ini=fecha_ini;
        this.fecha_fin=fecha_fin;
        ///////////////////transfromar las fechas a enteros

    }

    @Override
    public void Recuperar_datos() {
        bluetooth.sqLite=new Sqlite_DB_manejo(Obj_Context.getContext());
        String sql="SELECT * FROM Entidad_Bluetooth where Fecha>="+fecha_ini+"and Fecha<="+fecha_fin;
    }
}
