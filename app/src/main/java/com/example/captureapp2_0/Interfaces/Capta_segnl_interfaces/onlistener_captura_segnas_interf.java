package com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;

import java.util.ArrayList;
import java.util.List;

public interface onlistener_captura_segnas_interf {
    //void enviarA_viewpager(List<String> wifi, List lista_blue,List lista_completa);
    void enviar_error_enservidores(String mensaje);
    void retornar_datos_sin_conx(ArrayList<Obj_bluetooth> Array_bluet, ArrayList<Obj_wifi> Array_wifi);
    void dismi_progress(String mensajen);

}
