package com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces;


import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;
import java.util.List;

public interface view_captacion_fragmen_intfa {
    void agregar_viewpager_conexion_internet(Obje_servi obje_servi);
    void viewpager_sin_conexion_internet();
    void capturar_sin_internet();
    void verificar_datos_sin_conex();
    void error_en_servidors(String mensaje);
    void activar_servicios();
    void cargar_datos_servi(Obje_servi obje_servi);
    void Mensaje_datos_capt_sin_servidor(ArrayList<Obj_bluetooth> Array_bluet, ArrayList<Obj_wifi> Array_wifi);
    void progressbar_show();
    void progressbar_hiden(String Error);


}
