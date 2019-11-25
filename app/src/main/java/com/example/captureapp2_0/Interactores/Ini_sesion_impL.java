package com.example.captureapp2_0.Interactores;

import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_Interactor;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.onIni_sesion_Finishlicener;
import com.example.captureapp2_0.objetos.Obj_Context;

import android.content.SharedPreferences;
import android.os.Handler;


public class Ini_sesion_impL implements Ini_sesion_Interactor {
    @Override
    public void validarUser(final String correo, final String contra, final onIni_sesion_Finishlicener listener) {
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                if (!(correo.isEmpty()&&contra.isEmpty())){

                    listener.exito_consul();

                }else {
                    if (correo.equals("")) {
                        listener.correo_seterro();
                    }
                    if (contra.equals("")) {
                        listener.contra_seterro();
                    }
                }

            }
        },100);

    }

    @Override
    public void validar_sharepreference(onIni_sesion_Finishlicener listener) {
        SharedPreferences preferences= Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        preferences.getString("Id_User","NULL");
    }
}
