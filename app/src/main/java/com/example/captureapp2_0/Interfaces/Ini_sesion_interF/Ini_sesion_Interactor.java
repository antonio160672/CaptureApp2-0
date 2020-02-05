package com.example.captureapp2_0.Interfaces.Ini_sesion_interF;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

public interface Ini_sesion_Interactor {
    //se realiza toda la lógica
    void validarUser(String correo, String contra,
                     onIni_sesion_Finishlicener listener);
    void validar_sharepreference(onIni_sesion_Finishlicener listener);
    void validar_volley_preguntas(onIni_sesion_Finishlicener listener,
                                  Objeto_preguntas preguntas, String correo);
    void actualizar_contra(onIni_sesion_Finishlicener listener,String correo,String contra);
    //se realizan métodos muy específicos
}


