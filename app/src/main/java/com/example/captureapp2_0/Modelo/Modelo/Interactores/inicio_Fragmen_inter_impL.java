package com.example.captureapp2_0.Modelo.Modelo.Interactores;


import android.content.SharedPreferences;
import android.util.Log;

import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.inter_inicioFragmen_interactor;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.oninter_inicioFragment_Finishlicener;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Gestion_preguntas_SqliteVolley;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Estados;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

import java.util.Calendar;

public class inicio_Fragmen_inter_impL implements inter_inicioFragmen_interactor {

    private int correc_actua;
    private oninter_inicioFragment_Finishlicener listener;
    @Override
    public void cargar_datos(Obj_usuario obj_usuario, Obj_Estados obj_estados, oninter_inicioFragment_Finishlicener listener) {
        this.listener=listener;
        ejecutar_consulta(obj_usuario,obj_estados,listener);
    }

    @Override
    public void ejecutar_consulta(Obj_usuario obj_usuario, Obj_Estados obj_estados, oninter_inicioFragment_Finishlicener listener) {
        obj_usuario.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
        SharedPreferences preferences=Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        String ID=preferences.getString("Id_User","nulo");
        String sql="SELECT * FROM usuario where id_user="+ID;
        obj_usuario=obj_usuario.sqLite.Recuerar_datos_user(sql,obj_usuario);
        retornardatos(obj_usuario,obj_estados,listener);
    }

    @Override//retorna a la vista toda la informacion del usuario
    public void retornardatos(Obj_usuario obj_usuario, Obj_Estados obj_estados, oninter_inicioFragment_Finishlicener listener) {

        listener.cargar_Nombre_show(obj_usuario.getNombre()+" "+obj_usuario.getApellido_pater()
                                    +" "+obj_usuario.getApellido_mater());
        listener.cargar_correo_show(obj_usuario.getCorreo());
        obj_estados.Remplazar();
        listener.cargar_estado_show(obj_estados.contenedor_stados.get(obj_usuario.getIdEstado()));
        listener.cargar_Municipio_show(obj_usuario.getMunicipio());
        listener.cargar_colonia_show(obj_usuario.getColonia());
        listener.cargar_calle_show(obj_usuario.getCalle());
        listener.cargar_CP_show(obj_usuario.getCp());
        String[] arrayfecha = obj_usuario.getFecha_nac().split(" ");
        String[] naci=arrayfecha[0].split("-");
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int edad_real=(year-(Integer.parseInt(naci[2])));
        listener.cargar_edad_show(Integer.toString(edad_real));
    }

    @Override
    public void consultar_preguntas_recu() {
        Obj_usuario obj_usuario=new Obj_usuario();
        obj_usuario.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
        SharedPreferences preferences=Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        String ID=preferences.getString("Id_User","nulo");
        String sql="SELECT * FROM preguntas_recu where id_user="+ID;
        obj_usuario.cursor=obj_usuario.sqLite.consultaSQL(sql);
        if(obj_usuario.cursor.getCount()==0){
            listener.mostrar_preguntas();
        }

    }

    @Override
    public void registrar_actualizar_pregun(Objeto_preguntas preguntas) {
        preguntas.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
        SharedPreferences preferences=Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        String ID=preferences.getString("Id_User","nulo");
        Gestion_preguntas_SqliteVolley gestion_preguntas_volley=new
                Gestion_preguntas_SqliteVolley(preguntas,listener,ID);
        gestion_preguntas_volley.actualizar_preguntas();

        Log.e("pregunta","pregunta1:"+preguntas.getPregunta1()+" pregunta2:"+preguntas.getPregunta2()+
                " respuesta 1:"+preguntas.getSrespuesta1()+" respuesta2:"+preguntas.getSrespuesta2());
    }
}