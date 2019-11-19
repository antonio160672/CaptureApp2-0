package com.example.captureapp2_0.Interactores;

import android.os.Handler;
import android.util.Log;

import com.example.captureapp2_0.Interfaces.Registro_par2_interF.interRegistro_par2_Interactor;
import com.example.captureapp2_0.Interfaces.Registro_par2_interF.oninter_Registro_par2_Finishlicener;
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
                }else{
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
    }

    private void prueba(String fecha, String estado, String municipio, String calle, String colonia, String cp) {
        Log.e("datos","fecha:"+fecha+" estado:"+estado);
    }
}
