package com.example.captureapp2_0.Modelo.Modelo.Interactores;

import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_Interactor;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.onIni_sesion_Finishlicener;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Inicio_sesion_volley;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;


public class Ini_sesion_impL implements Ini_sesion_Interactor {
    String variable;
    @Override//método para validar los campos y datos sobre el
    public void validarUser(final String correo, final String contra, final onIni_sesion_Finishlicener listener) {
        new Handler().postDelayed(new Runnable(){//metodo para que se tenga un tiempo de
            //respuesta más largo
            boolean bandera=true;
            @Override
            public void run() {
                if (correo.isEmpty()&&contra.isEmpty()){
                    listener.contra_seterro("Correo vacío");
                    listener.correo_seterro("Correo vacío");
                    bandera=false;

                }else {
                    if (correo.equals("")) {
                        listener.correo_seterro("Correo vacío");
                        bandera=false;
                    }
                    if (contra.equals("")) {
                        listener.contra_seterro("Correo vacío");
                        bandera=false;
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                        listener.correo_seterro("Correo invalido");
                        bandera=false;
                    }
                }
                if (bandera){
                    Inicio_sesion_volley volley=new Inicio_sesion_volley(listener);
                    volley.validar_contra_correo(correo,contra);
                }
                listener.progressbar_desactiva();

            }
        },1000);

    }

    @Override
    public void validar_volley_preguntas(onIni_sesion_Finishlicener listener, Objeto_preguntas preguntas, String correo) {
        Inicio_sesion_volley volley=new Inicio_sesion_volley(listener);
        volley.validar_pregunta(preguntas,correo);

    }

    @Override
    public void actualizar_contra(onIni_sesion_Finishlicener listener, String correo, String contra) {
        Inicio_sesion_volley volley=new Inicio_sesion_volley(listener);
        volley.actualizar_contra(correo,contra);
    }

    @Override//primer método en ser comprobado para iniciar el menú principal
    public void validar_sharepreference(onIni_sesion_Finishlicener listener) {
        SharedPreferences preferences= Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        //instancia el valor del SharedPreferences para verificar si existe una
        //variable de ese tipo
        /*SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Id_User","74");
        editor.commit();*/
        variable=preferences.getString("Id_User","NULL");
        if (!variable.equals("NULL")){//en caso de que sea diferente de NULL
            if (consulta_registros_sql()){//consulta si se tienen registros en SQLite
                listener.exito_consul();//en caso de ser correcto medinate el lsitener
                                        //invoca el metodo de consulta exitosa
            }
        }

    }

    private boolean consulta_registros_sql() {
        Obj_usuario obj_usuario=new Obj_usuario();//se intancia un tipo objeto
        obj_usuario.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());//con los datos del contexto
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
