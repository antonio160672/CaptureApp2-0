package com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;

public interface Interactor_captuSig {
    Obje_servi verificar_servidor(Obje_servi servi);
    Boolean Salida_internet(String DNSS,String IP);
    void verificar_recuperar_datos();
    void retonar_datos_sin_conex(ArrayList<Obj_bluetooth> Array_bluet,ArrayList<Obj_wifi> Array_wifi);
    void envio_datos_sinconex(ArrayList<Obj_bluetooth> Array_bluet, ArrayList<Obj_wifi> Array_wifi, Obje_servi obje_servi);
}
