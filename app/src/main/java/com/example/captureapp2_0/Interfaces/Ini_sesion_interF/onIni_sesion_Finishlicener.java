package com.example.captureapp2_0.Interfaces.Ini_sesion_interF;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

public interface onIni_sesion_Finishlicener {
    //esta interfaz está a la escucha
    // para ordenar a la vista
    void correo_seterro(String mensaje);
    void contra_seterro(String mensaje);
    void exito_consul();
    void preguntas_correctas(String correo);
    void preguntas_invali(String mensaje);
    void progressbar_activar();
    void progressbar_desactiva();
    //esto permite al interactor indicar que hacer
}


