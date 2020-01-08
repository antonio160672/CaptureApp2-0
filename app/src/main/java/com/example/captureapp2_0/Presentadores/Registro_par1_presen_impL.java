package com.example.captureapp2_0.Presentadores;

import com.example.captureapp2_0.Modelo.Modelo.Interactores.Registro_par1_interac_impL;
import com.example.captureapp2_0.Interfaces.Registro_par1_interf.interRegistro_par1_Interactor;
import com.example.captureapp2_0.Interfaces.Registro_par1_interf.inter_Registro_par1_presentador;
import com.example.captureapp2_0.Interfaces.Registro_par1_interf.oninter_Registro_par1_Finishlicener;
import com.example.captureapp2_0.Vistas.Registro_par1Vista;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public class Registro_par1_presen_impL implements inter_Registro_par1_presentador, oninter_Registro_par1_Finishlicener {

    private Registro_par1Vista registro_par1Vista;
    private interRegistro_par1_Interactor interRegistro_par1_interactor;

    public Registro_par1_presen_impL(Registro_par1Vista registro_par1Vista) {
        this.registro_par1Vista = registro_par1Vista;
        interRegistro_par1_interactor=new Registro_par1_interac_impL();
    }

    @Override
    public void validar_Registro_interacto(String Nombre, String Apellido_parte, String Apellido_mater,
                                 String Correo, String Contra, String Conf_contra) {
        if (registro_par1Vista != null) {
            interRegistro_par1_interactor.validarRegistropar1(Nombre,Apellido_parte,Apellido_mater,
                    Correo,Contra,Conf_contra,this);
        }

    }

    @Override
    public void Nombr_seterro(String error) {
        registro_par1Vista.showerrorNombr(error);
    }

    @Override
    public void Apellido_pa_seterro(String error) {
        registro_par1Vista.showerrorApellido_pa(error);
    }

    @Override
    public void Apellido_mate_seterro(String error) {
        registro_par1Vista.showerrorApellido_mate(error);
    }

    @Override
    public void Correo_seterro(String error) {
        registro_par1Vista.showerrorCorreo(error);

    }

    @Override
    public void Contra_seterro(String error) {
        registro_par1Vista.showerrorContra(error);
    }

    @Override
    public void exito_valida(Obj_usuario obj_usuario) {
        registro_par1Vista.Siguiente_venta(obj_usuario);
    }
}
