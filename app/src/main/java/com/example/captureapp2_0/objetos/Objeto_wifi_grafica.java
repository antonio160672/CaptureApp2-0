package com.example.captureapp2_0.objetos;

import java.util.ArrayList;

public class Objeto_wifi_grafica {
    private String Id_db,Id,Macaddres;
    public ArrayList<Integer> lista_Rssi;

    public Objeto_wifi_grafica() {
        this.lista_Rssi=new ArrayList<>();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMacaddres() {
        return Macaddres;
    }

    public void setMacaddres(String macaddres) {
        Macaddres = macaddres;
    }

    public String getId_db() {
        return Id_db;
    }

    public void setId_db(String id_db) {
        Id_db = id_db;
    }
}
