package com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces;

import android.widget.EditText;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

public interface usuario_datos_presnT_interF {
    void validar_contra(EditText dialog, String contrase);
    void verficar_contras(String nueva_contra,String conf_contra);
    void recuperar_datos_usuario();
    void editar_datos_personales(Obj_usuario usuario);
    void validar_preguntas(Objeto_preguntas validar_preguntas);
}
