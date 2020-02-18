package com.example.captureapp2_0.Presentadores;

import android.util.Log;

import com.example.captureapp2_0.Modelo.Modelo.Interactores.Ini_sesion_impL;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_Interactor;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_presentador;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_vista;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.onIni_sesion_Finishlicener;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

public class Ini_sesion_presen_impL implements Ini_sesion_presentador, onIni_sesion_Finishlicener {

    private Ini_sesion_vista vista_iniSesi; //metodos que instancian a las interfaces
    private Ini_sesion_Interactor intera_ini_Sesion;

    //método del constructor
    public Ini_sesion_presen_impL(Ini_sesion_vista vista_iniSesi) {
        this.vista_iniSesi = vista_iniSesi;
        intera_ini_Sesion=new Ini_sesion_impL();//ahora implementa pero al interactor
    }

    @Override
    public void validar_preguntas(Objeto_preguntas preguntas, String correo) {
        if (intera_ini_Sesion != null) {
            intera_ini_Sesion.validar_volley_preguntas(this,preguntas,correo);
        }
    }

    @Override//primer método indispensable envía instrucción al Interactor para verificar
             //existencia de datos
    public void validar_sharepre() {
        if (intera_ini_Sesion != null) {
            intera_ini_Sesion.validar_sharepreference(this);
        }
    }

    @Override
    public void valida_usuario(String correo, String contra) {
        if (intera_ini_Sesion != null) {//se verifica la existencia de una vista
            progressbar_activar();
            intera_ini_Sesion.validarUser(correo,contra,this);
        }
    }

    @Override//instancia los métodos de error de la vista
    public void correo_seterro(String mensaje) {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.showerrorcorreo(mensaje);
        }

    }

    @Override//métodos de error del correo
    public void contra_seterro(String mensaje) {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.showerrorcontra(mensaje);
        }
    }

    @Override//retorna instrucción a la vista para iniciar un servicio
    public void exito_consul() {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.mover_menu_pri();
        }
    }

    @Override
    public void preguntas_correctas(String correo) {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.preguntas_validas(correo);
        }
    }

    @Override
    public void preguntas_invali(String mensaje) {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.error_preguntas_contr(mensaje);
        }

    }

    @Override
    public void enviar_contra(String correo, String contra) {
        if (intera_ini_Sesion != null) {
            intera_ini_Sesion.actualizar_contra(this,correo,contra);
        }
    }

    @Override
    public void progressbar_activar() {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.progressbar_show();
        }
    }

    @Override
    public void progressbar_desactiva() {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.progressbar_hiden();
        }
    }
}
