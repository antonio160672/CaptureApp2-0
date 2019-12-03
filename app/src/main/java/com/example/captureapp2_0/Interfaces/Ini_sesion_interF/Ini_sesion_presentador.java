package com.example.captureapp2_0.Interfaces.Ini_sesion_interF;

public interface Ini_sesion_presentador {
    //el presentador sirve para mandar las
    // instrucciones puede validar
    //pero no es lo más recomendable todas esas
    // funciones las hace el sistema
    void valida_usuario(String correo,String contra);
    void validar_sharepre();
    //métodos del presentador para validar al
    // usuario y varlidar un sharepreference
}
