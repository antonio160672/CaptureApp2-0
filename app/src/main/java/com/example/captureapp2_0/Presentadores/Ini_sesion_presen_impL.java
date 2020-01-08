package com.example.captureapp2_0.Presentadores;

import com.example.captureapp2_0.Modelo.Modelo.Interactores.Ini_sesion_impL;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_Interactor;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_presentador;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_vista;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.onIni_sesion_Finishlicener;

public class Ini_sesion_presen_impL implements Ini_sesion_presentador, onIni_sesion_Finishlicener {

    private Ini_sesion_vista vista_iniSesi; //metodos que instancian a las interfaces
    private Ini_sesion_Interactor intera_ini_Sesion;

    //método del constructor
    public Ini_sesion_presen_impL(Ini_sesion_vista vista_iniSesi) {
        this.vista_iniSesi = vista_iniSesi;
        intera_ini_Sesion=new Ini_sesion_impL();//ahora implementa pero al interactor
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
            intera_ini_Sesion.validarUser(correo,contra,this);
        }
    }

    @Override//instancia los métodos de error de la vista
    public void correo_seterro() {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.showerrorcorreo();
        }

    }

    @Override//métodos de error del correo
    public void contra_seterro() {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.showerrorcontra();
        }
    }

    @Override//retorna instrucción a la vista para iniciar un servicio
    public void exito_consul() {
        if (intera_ini_Sesion != null) {
            vista_iniSesi.mover_menu_pri();
        }
    }
}
