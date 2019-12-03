package com.example.captureapp2_0.Interactores;


import android.content.SharedPreferences;

import com.example.captureapp2_0.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.inter_inicioFragmen_interactor;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.oninter_inicioFragment_Finishlicener;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_Estados;
import com.example.captureapp2_0.objetos.Obj_usuario;

public class inicio_Fragmen_inter_impL implements inter_inicioFragmen_interactor {


    @Override
    public void cargar_datos(Obj_usuario obj_usuario, Obj_Estados obj_estados, oninter_inicioFragment_Finishlicener listener) {
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
        listener.cargar_edad_show(obj_usuario.getFecha_nac());
    }
}