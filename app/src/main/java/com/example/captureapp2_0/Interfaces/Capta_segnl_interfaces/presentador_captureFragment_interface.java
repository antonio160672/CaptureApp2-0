package com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;

public interface presentador_captureFragment_interface {
    void verificar_servicios();
    void escaneo_wifi_bluethoo_con_conexion(Obje_servi obje_servi);
    void escaneo_wifi_bluethoo_sin_conexion();
    void destruir();
    boolean verificar_conexion_servi(String DDNS,String IP);
    void verificar_datos_sin_conex();
    void enviar_datos_sinconex(ArrayList<Obj_bluetooth> Array_bluet, ArrayList<Obj_wifi> Array_wifi, Obje_servi obje_servi);

}
