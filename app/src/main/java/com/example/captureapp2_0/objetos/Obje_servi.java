package com.example.captureapp2_0.objetos;

import android.database.Cursor;

import com.example.captureapp2_0.DB_lite.Sqlite_DB_manejo;

public class Obje_servi {
    private int id_servi;
    String ip_servidor,DNS_ser,Puerto_orion,Puerto_crateDB;
    private int servidor_predeter;
    public Sqlite_DB_manejo sqLite=null;
    public Cursor cursor=null;

    public int getId_servi() {
        return id_servi;
    }

    public void setId_servi(int id_servi) {
        this.id_servi = id_servi;
    }

    public int getServidor_predeter() {
        return servidor_predeter;
    }

    public void setServidor_predeter(int servidor_predeter) {
        this.servidor_predeter = servidor_predeter;
    }

    public String getIp_servidor() {
        return ip_servidor;
    }

    public void setIp_servidor(String ip_servidor) {
        this.ip_servidor = ip_servidor;
    }

    public String getDNS_ser() {
        return DNS_ser;
    }

    public void setDNS_ser(String DNS_ser) {
        this.DNS_ser = DNS_ser;
    }

    public String getPuerto_orion() {
        return Puerto_orion;
    }

    public void setPuerto_orion(String puerto_orion) {
        Puerto_orion = puerto_orion;
    }

    public String getPuerto_crateDB() {
        return Puerto_crateDB;
    }

    public void setPuerto_crateDB(String puerto_crateDB) {
        Puerto_crateDB = puerto_crateDB;
    }
}
