package com.example.captureapp2_0.Presentadores;

import android.os.Handler;
import android.widget.EditText;

import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.onlistener_usuario;
import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.usuario_datos_int_interF;
import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.usuario_datos_presnT_interF;
import com.example.captureapp2_0.Modelo.Modelo.Interactores.usuario_datos_interactor_impL;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;
import com.example.captureapp2_0.Vistas.Usurio_datos.usuario_datos_vistas;

public class usuario_datos_preseN_impL implements usuario_datos_presnT_interF, onlistener_usuario {
    private  usuario_datos_vistas usuario_datos_vistas;
    private usuario_datos_int_interF intInterF;

    public usuario_datos_preseN_impL(usuario_datos_vistas usuario_datos_vistas) {
        this.usuario_datos_vistas = usuario_datos_vistas;
        intInterF=new usuario_datos_interactor_impL(this);
    }

    @Override
    public void recuperar_datos_usuario() {
        intInterF.recuperar_datos_usuario();
    }

    @Override
    public void enviar_datos_usuario(Obj_usuario usuario) {
        usuario_datos_vistas.cargar_usuario(usuario);
    }

    @Override
    public void validar_contra(EditText contra, String contrase) {
        if (usuario_datos_vistas!=null){
            intInterF.validar_contraseña(contra, contrase);
        }
    }

    @Override
    public void ocurtar_dialog_mostrardatos() {
        usuario_datos_vistas.ocurtar_dialog_mostrardatos();
    }

    @Override
    public void verficar_contras(String nueva_contra, String conf_contra) {
        usuario_datos_vistas.progressbar_show();
        intInterF.verficar_contra_volley(nueva_contra,conf_contra);

    }

    @Override
    public void enviar_correcta_actuali(String mensaje) {
        usuario_datos_vistas.progressbar_hiden(mensaje);
    }

    @Override
    public void enviar_error_nueva_contra(String mensaje) {
        usuario_datos_vistas.progressbar_hiden("");
        usuario_datos_vistas.error_nueva_contra(mensaje);
    }

    @Override
    public void editar_datos_personales(Obj_usuario usuario) {
        usuario_datos_vistas.progressbar_show();
        intInterF.editar_datos_usuario(usuario);
    }

    @Override
    public void validar_preguntas(Objeto_preguntas validar_preguntas) {
        usuario_datos_vistas.progressbar_show();
        intInterF.validar_preguntas_volley(validar_preguntas);
    }

    @Override
    public void progress_hiden_sin_mensaje(String mensaje) {
        usuario_datos_vistas.progressbar_hiden_sin_mensa(mensaje);
    }
}
