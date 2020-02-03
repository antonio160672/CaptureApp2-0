package com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces;

import android.widget.Toast;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public interface onlistener_usuario {
    void ocurtar_dialog_mostrardatos();
    void enviar_error_nueva_contra(String mensaje);
    void enviar_correcta_actuali(String mensaje);
    void enviar_datos_usuario(Obj_usuario usuario);
    void progress_hiden_sin_mensaje(String mensaje);
}
