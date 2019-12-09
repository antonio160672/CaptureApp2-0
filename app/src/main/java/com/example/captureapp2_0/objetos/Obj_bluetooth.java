package com.example.captureapp2_0.objetos;
import android.database.Cursor;
import com.example.captureapp2_0.DB_lite.Sqlite_DB_manejo;

public class Obj_bluetooth {
    public static Sqlite_DB_manejo sqLite=null;
    public static Cursor cursor=null;
    String UUID,BluetoothAddress,id_dip,MAJOR,RSSI,TX,id_tip_dispo,id_user,fecha_cap,hora;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getBluetoothAddress() {
        return BluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        BluetoothAddress = bluetoothAddress;
    }

    public String getId_dip() {
        return id_dip;
    }

    public void setId_dip(String id_dip) {
        this.id_dip = id_dip;
    }

    public String getMAJOR() {
        return MAJOR;
    }

    public void setMAJOR(String MAJOR) {
        this.MAJOR = MAJOR;
    }

    public String getRSSI() {
        return RSSI;
    }

    public void setRSSI(String RSSI) {
        this.RSSI = RSSI;
    }

    public String getTX() {
        return TX;
    }

    public void setTX(String TX) {
        this.TX = TX;
    }

    public String getId_tip_dispo() {
        return id_tip_dispo;
    }

    public void setId_tip_dispo(String id_tip_dispo) {
        this.id_tip_dispo = id_tip_dispo;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getFecha_cap() {
        return fecha_cap;
    }

    public void setFecha_cap(String fecha_cap) {
        this.fecha_cap = fecha_cap;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
