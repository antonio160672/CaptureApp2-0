package com.example.captureapp2_0.Modelo.Modelo.objetos;

import java.util.ArrayList;

public class Objeto_bluetoo_grafica {
    private String Id_db,Id,BluetoothAddress;
    public ArrayList<String>lista_Rssi;

    public Objeto_bluetoo_grafica() {
        this.lista_Rssi=new ArrayList<>();
    }

    public String getId_db() {
        return Id_db;
    }

    public void setId_db(String id_db) {
        Id_db = id_db;
    }

    public String getBluetoothAddress() {
        return BluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        BluetoothAddress = bluetoothAddress;
    }
}
