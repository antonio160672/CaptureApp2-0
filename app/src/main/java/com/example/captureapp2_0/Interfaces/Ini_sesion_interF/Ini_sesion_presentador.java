package com.example.captureapp2_0.Interfaces.Ini_sesion_interF;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

public interface Ini_sesion_presentador {
    //el presentador controla que
    // función debe realizar el sistema
    void valida_usuario(String correo,String contra);
    void validar_sharepre();//sí existen datos de
    void validar_preguntas(Objeto_preguntas preguntas, String correo);
    void enviar_contra(String correo,String contra);
    //inicio de sesión
}
