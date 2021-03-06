package com.example.captureapp2_0.Modelo.Modelo.Interactores;

import android.os.Handler;

import com.example.captureapp2_0.Interfaces.Registro_par2_interF.interRegistro_par2_Interactor;
import com.example.captureapp2_0.Interfaces.Registro_par2_interF.oninter_Registro_par2_Finishlicener;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Registro_user;
import com.example.captureapp2_0.Modelo.Modelo.funciones_generas.Validaciones_campos;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Estados;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public class Registro_par2_interac_impL implements interRegistro_par2_Interactor {
    private int bandera;
    private Obj_usuario obj_usuario;
    private oninter_Registro_par2_Finishlicener listener;

    @Override//valida cadenas para ver que no esten vacías
    public void validar_registrop2_intera(Obj_usuario obj_usuario, final String fecha, final String estado,
                                          final String municipio, final String calle, final String colonia, final String CP,
                                          final oninter_Registro_par2_Finishlicener listener) {
        this.listener=listener;
        this.obj_usuario=obj_usuario;
        bandera=1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //en esta seccion se verifica la existencia de campos vacío
                if ((fecha.equals("Fecha")||fecha.equals("Campo vacío")) && estado.equals("Estado")
                        && municipio.isEmpty() && calle.isEmpty() && colonia.isEmpty() && CP.isEmpty()) {
                    listener.fecha_seterror("Campo vacío");
                    listener.spiner_seterror("Campo vacío");
                    listener.municipio_seterror("Campo vacío");
                    listener.calle_seterror("Campo vacío");
                    listener.colonia_setrror("Campo vacío");
                    listener.CP_seterror("Campo vacío");
                }
                else{
                    if (fecha.equals("Fecha")||fecha.equals("Campo vacío") ){
                        listener.fecha_seterror("Campo vacío");
                        bandera=0;
                    }
                    if ( estado.equals("Estado")) {
                        listener.spiner_seterror("Campo vacío");
                        bandera=0;
                    }
                    if (municipio.equals("")) {
                        listener.municipio_seterror("Campo vacío");
                        bandera=0;
                    }
                    if (calle.equals("")) {
                        listener.calle_seterror("Campo vacío");
                        bandera=0;
                    }
                    if (colonia.equals("")) {
                        listener.colonia_setrror("Campo vacío");
                        bandera=0;
                    }
                    if (CP.equals("")) {
                        listener.CP_seterror("Campo vacío");
                        bandera=0;
                    }
                    validar_cadenas(fecha,estado.trim(),municipio.trim(), calle.trim(),colonia.trim(),CP);

                }
                listener.dismi_progress("");
            }
        },1500);
    }

    //aqui se validan las cadenas para no tener datos no esperados
    private void validar_cadenas(String fecha, String estado, String municipio, String calle, String colonia, String cp) {
        Validaciones_campos validaciones_campos=new Validaciones_campos();
        String error;
        if (!(cp.length()>=5&&cp.length()<=8)){
            listener.CP_seterror("El CP no es valido");
            bandera=0;
        }
        error=validaciones_campos.Val_Nombres(municipio);
        if (error!=null){
            listener.municipio_seterror(error);
            bandera=0;
        }
        error=validaciones_campos.Direccion(calle);
        if (error!=null){
            listener.calle_seterror(error);
            bandera=0;
        }
        error=validaciones_campos.Val_Nombres(colonia);
        if (error!=null){
            listener.colonia_setrror(error);
            bandera=0;
        }
        if (bandera==1){
            Obj_Estados obj_estados=new Obj_Estados();
            obj_usuario.setCalle(calle);
            obj_usuario.setColonia(colonia);
            obj_usuario.setCp(cp);
            obj_usuario.setFecha_nac(fecha);
            obj_usuario.setMunicipio(municipio);
            obj_usuario.setIdEstado(obj_estados.contenedor_stados.get(estado));
            resgistro_volley_sqlite();
        }
    }

    private void resgistro_volley_sqlite() {
        Registro_user registro_user=new Registro_user(obj_usuario,listener);
        registro_user.Registro_usuario();
    }

}
