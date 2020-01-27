package com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces;

import android.widget.EditText;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public interface usuario_datos_int_interF {
    void validar_contraseña(EditText dialog, String contrase);
    void verficar_contra_volley(String contra,String conf_contra);
    void recuperar_datos_usuario();
    void editar_datos_usuario(Obj_usuario usuario);
}
