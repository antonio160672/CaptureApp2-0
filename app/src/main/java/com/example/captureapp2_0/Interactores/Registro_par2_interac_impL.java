package com.example.captureapp2_0.Interactores;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.captureapp2_0.Interfaces.Registro_par2_interF.interRegistro_par2_Interactor;
import com.example.captureapp2_0.Interfaces.Registro_par2_interF.oninter_Registro_par2_Finishlicener;
import com.example.captureapp2_0.consultas_volley.Registro_user;
import com.example.captureapp2_0.objetos.Obj_Estados;
import com.example.captureapp2_0.objetos.Obj_usuario;

public class Registro_par2_interac_impL implements interRegistro_par2_Interactor {
    private int bandera;
    private Obj_usuario obj_usuario;
    private oninter_Registro_par2_Finishlicener listener;

    @Override
    public void validar_registrop2_intera(Obj_usuario obj_usuario, final String fecha, final String estado,
                                          final String municipio, final String calle, final String colonia, final String CP,
                                          final oninter_Registro_par2_Finishlicener listener) {
        this.listener=listener;
        this.obj_usuario=obj_usuario;
        bandera=1;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                prueba(fecha,estado,municipio,calle,colonia,CP);
                if ((fecha.equals("Fecha")||fecha.equals("campo vacio")) && estado.equals("Estado")
                        && municipio.isEmpty() && calle.isEmpty() && colonia.isEmpty() && CP.isEmpty()) {
                    listener.fecha_seterror("campo vacio");
                    listener.spiner_seterror("campo vacio");
                    listener.municipio_seterror("campo vacio");
                    listener.calle_seterror("campo vacio");
                    listener.colonia_setrror("campo vacio");
                    listener.CP_seterror("campo vacio");
                }
                else{
                    if (fecha.equals("Fecha")||fecha.equals("campo vacio") ){
                        listener.fecha_seterror("campo vacio");
                        bandera=0;
                    }
                    if ( estado.equals("Estado")) {
                        listener.spiner_seterror("campo vacio");
                        bandera=0;
                    }
                    if (municipio.equals("")) {
                        listener.municipio_seterror("campo vacio");
                        bandera=0;
                    }
                    if (calle.equals("")) {
                        listener.calle_seterror("campo vacio");
                        bandera=0;
                    }
                    if (colonia.equals("")) {
                        listener.colonia_setrror("campo vacio");
                        bandera=0;
                    }
                    if (CP.equals("")) {
                        listener.CP_seterror("campo vacio");
                        bandera=0;
                    }
                    if (bandera!=0){
                        validar_cadenas(fecha,estado,municipio, calle,colonia,CP);
                    }
                }
            }
        },100);
    }

    private void validar_cadenas(String fecha, String estado, String municipio, String calle, String colonia, String cp) {
        bandera=1;
        if (!(cp.length()>=5&&cp.length()<=8)){
            listener.CP_seterror("El CP no es valido");
            bandera=0;
        }
        if (bandera==1){
            Obj_Estados obj_estados=new Obj_Estados();
            obj_usuario.setCalle(calle);
            obj_usuario.setColonia(colonia);
            obj_usuario.setCp(cp);
            obj_usuario.setFecha_nac(fecha);
            obj_usuario.setIdEstado(obj_estados.contenedor_stados.get(estado));
            prueba(fecha, estado, municipio, calle, colonia, cp);
            resgistro_volley_sqlite();
        }
    }

    private void resgistro_volley_sqlite() {
        Registro_user registro_user=new Registro_user(obj_usuario);
        if (registro_user.Registro_usuario()){

        }

    }

    private void prueba(String fecha, String estado, String municipio, String calle, String colonia, String cp) {
        Log.e("usuario","contra::"+obj_usuario.getContrasena()+" correo:"+obj_usuario.getCorreo()+"\n");
        Log.e("datos","fecha:"+fecha+" estado:"+estado);
    }
}
