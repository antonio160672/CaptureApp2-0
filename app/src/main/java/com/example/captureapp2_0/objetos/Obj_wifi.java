package com.example.captureapp2_0.objetos;

import android.database.Cursor;

import com.example.captureapp2_0.DB_lite.Sqlite_DB_manejo;

public class Obj_wifi {
    String Nombre_dispos,id_dip,macaddres,RSSI,id_tip_dispo,id_user,fecha_cap,hora;

    public static Sqlite_DB_manejo sqLite=null;
    public static Cursor cursor=null;

    public String getId_dip() {
        return id_dip;
    }

    public void setId_dip(String id_dip) {
        this.id_dip = id_dip;
    }

    public String getNombre_dispos() {
        return Nombre_dispos;
    }

    public void setNombre_dispos(String nombre_dispos) {
        Nombre_dispos = nombre_dispos;
    }

    public String getMacaddres() {
        return macaddres;
    }

    public void setMacaddres(String macaddres) {
        this.macaddres = macaddres;
    }

    public String getRSSI() {
        return RSSI;
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }

    public String getId_tip_dispo() {
        return id_tip_dispo;
    }

    public void setId_tip_dispo(String id_tip_dispo) {
        this.id_tip_dispo = id_tip_dispo;
    }

    public String getFecha_cap() {
        return fecha_cap;
    }

    public void setFecha_cap(String fecha_cap) {
        this.fecha_cap = fecha_cap;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
