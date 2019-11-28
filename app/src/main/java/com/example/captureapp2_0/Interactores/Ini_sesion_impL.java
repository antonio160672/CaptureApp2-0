package com.example.captureapp2_0.Interactores;

import com.example.captureapp2_0.DB_lite.Sqlite_usuario;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_Interactor;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.onIni_sesion_Finishlicener;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_usuario;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;


public class Ini_sesion_impL implements Ini_sesion_Interactor {
    String variable;
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
        variable=preferences.getString("Id_User","NULL");
        if (!variable.equals("NULL")){
            if (Registro_sql()){
                listener.exito_consul();
            }
        }

    }

    private boolean Registro_sql() {
        Obj_usuario obj_usuario=new Obj_usuario();
        obj_usuario.sqLite= new Sqlite_usuario(Obj_Context.getContext());
        obj_usuario.cursor=Consulta_existe(obj_usuario);
        if (obj_usuario.cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    private Cursor Consulta_existe(Obj_usuario obj_usuario) {
        String sql="";
        sql="select * from usuario where id_user='"+variable+"' limit 1";
        return obj_usuario.sqLite.consultaSQL(sql);
    }
}
