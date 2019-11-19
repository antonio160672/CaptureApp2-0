package com.example.captureapp2_0.Interfaces.Registro_par1_interf;

import com.example.captureapp2_0.objetos.Obj_usuario;

public interface oninter_Registro_par1_Finishlicener {
    void Nombr_seterro(String error);
    void Apellido_pa_seterro(String error);
    void Apellido_mate_seterro(String error);
    void Correo_seterro(String error);
    void Contra_seterro(String error);

    void exito_valida(Obj_usuario obj_usuario);
}
