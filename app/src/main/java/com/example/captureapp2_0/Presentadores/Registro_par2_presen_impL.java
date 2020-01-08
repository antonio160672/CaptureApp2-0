package com.example.captureapp2_0.Presentadores;

import com.example.captureapp2_0.Modelo.Modelo.Interactores.Registro_par2_interac_impL;
import com.example.captureapp2_0.Interfaces.Registro_par2_interF.interRegistro_par2_Interactor;
import com.example.captureapp2_0.Interfaces.Registro_par2_interF.inter_Registro_par2_presentador;
import com.example.captureapp2_0.Interfaces.Registro_par2_interF.oninter_Registro_par2_Finishlicener;
import com.example.captureapp2_0.Vistas.Registro_par2Vista;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public class Registro_par2_presen_impL implements inter_Registro_par2_presentador, oninter_Registro_par2_Finishlicener{
    private Registro_par2Vista registro_par2Vista;
    private interRegistro_par2_Interactor interRegistro_par2_interactor;

    public Registro_par2_presen_impL(Registro_par2Vista registro_par2Vista) {
        this.registro_par2Vista = registro_par2Vista;
        interRegistro_par2_interactor=new Registro_par2_interac_impL();
    }

    @Override
    public void validar_Registro_interacto(Obj_usuario obj_usuario, String fecha, String estado,
                                           String municipio, String calle, String colonia, String CP) {
        interRegistro_par2_interactor.validar_registrop2_intera(obj_usuario,fecha,estado,municipio,
                                        calle,colonia,CP,this);
    }

    @Override
    public void fecha_seterror(String error) {
        registro_par2Vista.showerrorfecha(error);
    }

    @Override
    public void spiner_seterror(String errot) {
        registro_par2Vista.showerrorspiner(errot);
    }

    @Override
    public void municipio_seterror(String error) {
        registro_par2Vista.showerrormunicipio(error);
    }

    @Override
    public void calle_seterror(String error) {
        registro_par2Vista.showerrorcalle(error);
    }

    @Override
    public void colonia_setrror(String error) {
        registro_par2Vista.showerrorcolonia(error);
    }

    @Override
    public void CP_seterror(String error) {
        registro_par2Vista.showerrorCP(error);
    }

    @Override
    public void exito_valida() {
        registro_par2Vista.navegador();
    }
}
