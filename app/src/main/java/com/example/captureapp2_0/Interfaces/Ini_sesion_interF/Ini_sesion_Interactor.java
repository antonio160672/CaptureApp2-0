package com.example.captureapp2_0.Interfaces.Ini_sesion_interF;

public interface Ini_sesion_Interactor {
    //se realiza toda la lógica
    void validarUser(String correo, String contra,
                     onIni_sesion_Finishlicener listener);
    void validar_sharepreference(onIni_sesion_Finishlicener listener);
    //se realizan métodos muy específicos
}


