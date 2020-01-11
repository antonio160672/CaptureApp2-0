package com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

public interface Interactor_captuSig {
    Obje_servi verificar_servidor(Obje_servi servi);
    Boolean Salida_internet(String DNSS,String IP);
    void captura_wifi();
    void captura_blue();
}
