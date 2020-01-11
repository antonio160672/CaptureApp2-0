package com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

public interface presentador_captureFragment_interface {
    void verificar_servicios();
    void escaneo_wifi_bluethoo_con_conexion(Obje_servi obje_servi);
    void escaneo_wifi_bluethoo_sin_conexion();
    void destruir();
    boolean verificar_conexion_servi(String DDNS,String IP);
}
