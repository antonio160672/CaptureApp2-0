package com.example.captureapp2_0.Interfaces.Ini_sesion_interF;

public interface Ini_sesion_vista {
    //interfaz de la vista, contiene los métodos que realiza la vista
    void showerrorcorreo(String mensaje);//mostrar errores
    void showerrorcontra(String mensaje);//error en contraseña
    void mover_menu_pri();//navegar al menu principal
    void generar_dialog_preguntas();
    void error_preguntas_contr(String mensaje);
    void preguntas_validas(String correo);
}


