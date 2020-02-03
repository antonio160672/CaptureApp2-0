package com.example.captureapp2_0.Modelo.Modelo.Interactores;

import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;

import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.onlistener_usuario;
import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.usuario_datos_int_interF;
import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Gestion_usuario_sql_volley;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

public class usuario_datos_interactor_impL implements usuario_datos_int_interF {
    onlistener_usuario usuario;
    String ID;
    Gestion_usuario_sql_volley volley;
    public usuario_datos_interactor_impL(onlistener_usuario usuario) {
        this.usuario = usuario;
        SharedPreferences preferences= Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
         ID=preferences.getString("Id_User","nulo");
    }

    @Override
    public void recuperar_datos_usuario() {
        Obj_usuario obj_usuario=new Obj_usuario();
        obj_usuario.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
        String sql="SELECT * FROM usuario where id_user="+ID;
        obj_usuario=obj_usuario.sqLite.Recuerar_datos_user(sql,obj_usuario);
        String[]fecha=obj_usuario.getFecha_nac().split(" ");
        obj_usuario.setFecha_nac(fecha[0]);
        usuario.enviar_datos_usuario(obj_usuario);
    }

    @Override
    public void validar_contraseña(EditText contra, String contrase) {
        volley =new Gestion_usuario_sql_volley(usuario);
        volley.consutar_contra_voller(contra,contrase,ID);

    }

    @Override
    public void validar_preguntas_volley(final Objeto_preguntas objetoPreguntas) {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                volley =new Gestion_usuario_sql_volley(usuario);
                volley.verificar_preguntas(objetoPreguntas,ID);
            }},1500);
    }

    @Override
    public void verficar_contra_volley(final String contra, final String conf_contra) {
        final boolean[] bandera = {true};
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
        if (contra.isEmpty()&&conf_contra.isEmpty()){
            usuario.enviar_error_nueva_contra("Contraseñas vacías");
        }else{
            if (contra.isEmpty()){
                usuario.enviar_error_nueva_contra("Contraseñas vacías");
                bandera[0] =false;
            }
            if (conf_contra.isEmpty()){
                usuario.enviar_error_nueva_contra("Contraseñas vacías");
                bandera[0] =false;
            }
            if (!((contra.length()>=8&&contra.length()<=40)&&(conf_contra.length()>=8&&conf_contra.length()<=40))){
                usuario.enviar_error_nueva_contra("Tamaño minimo 8 caracteres");
                bandera[0] =false;
            }
            if (!contra.equals(conf_contra)){
                usuario.enviar_error_nueva_contra("Las contraseñas no son iguales");
                bandera[0] =false;
            }
            if (bandera[0]){
                if (!(volley!=null)){
                    Gestion_usuario_sql_volley volley =new Gestion_usuario_sql_volley(usuario);
                }
                volley.actualizar_contra(contra,ID);
            }
        }
        }},1500);
    }

    @Override
    public void editar_datos_usuario(final Obj_usuario usuario_objeto) {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (!(volley!=null)){
                    Gestion_usuario_sql_volley volley =new Gestion_usuario_sql_volley(usuario);
                }
                usuario_objeto.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
                volley.actualizar_datos_usuario(usuario_objeto,ID);
                usuario.enviar_correcta_actuali("Correcta actualización");

            }},1500);
    }
}
