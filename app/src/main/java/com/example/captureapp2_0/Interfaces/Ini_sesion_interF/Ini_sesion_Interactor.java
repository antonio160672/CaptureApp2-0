package com.example.captureapp2_0.Interfaces.Ini_sesion_interF;

public interface Ini_sesion_Interactor {

    void validarUser(String correo, String contra, onIni_sesion_Finishlicener listener);//esto manda la informacion
    //para que el interactor valide la información del usuario
    void validar_sharepreference(onIni_sesion_Finishlicener listener);
}
