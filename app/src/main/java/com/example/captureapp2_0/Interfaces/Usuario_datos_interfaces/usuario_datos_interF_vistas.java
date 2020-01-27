package com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public interface usuario_datos_interF_vistas {
    void cargar_dialog();
    void ocultas_LinearLayout();
    void ocurtar_dialog_mostrardatos();
    void error_nueva_contra(String mensaje);
    void progressbar_show();
    void  progressbar_hiden(String mensaje);
    void cargar_usuario(Obj_usuario usuario);
    void dialog_datosEdit_usuario();

}
