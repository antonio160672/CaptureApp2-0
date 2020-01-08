package com.example.captureapp2_0.Presentadores;

import com.example.captureapp2_0.Modelo.Modelo.Interactores.inicio_Fragmen_inter_impL;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.inter_inicioFragmen_interactor;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.inter_inicioFragment_presentador;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.oninter_inicioFragment_Finishlicener;
import com.example.captureapp2_0.Vistas.Menu_princi.inicioFragment_view;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Estados;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public class inicioFragmen_presen_impL implements inter_inicioFragment_presentador, oninter_inicioFragment_Finishlicener {
    private inicioFragment_view inicioFragment_view;
    private inter_inicioFragmen_interactor interactor;

    public inicioFragmen_presen_impL(inicioFragment_view inicioFragment_view) {
        this.inicioFragment_view = inicioFragment_view;
        interactor=new inicio_Fragmen_inter_impL();
        Llamar_consultaSQLite();
    }

    @Override
    public void Llamar_consultaSQLite() {
        if (inicioFragment_view!=null){
            Obj_usuario obj_usuario=new Obj_usuario();
            Obj_Estados obj_estados=new Obj_Estados();
            interactor.cargar_datos(obj_usuario,obj_estados,this);

        }

    }

    @Override
    public void cargar_Nombre_show(String dato) {
        inicioFragment_view.cargar_Nombre_sql(dato);
    }

    @Override
    public void cargar_correo_show(String dato) {
        inicioFragment_view.cargar_correo_sql(dato);
    }

    @Override
    public void cargar_estado_show(String dato) {
        inicioFragment_view.cargar_estado_sql(dato);
    }

    @Override
    public void cargar_calle_show(String dato) {
        inicioFragment_view.cargar_calle_sql(dato);
    }

    @Override
    public void cargar_colonia_show(String dato) {
        inicioFragment_view.cargar_colonia_sql(dato);
    }

    @Override
    public void cargar_edad_show(String dato) {
        inicioFragment_view.cargar_edad_sql(dato);
    }

    @Override
    public void cargar_CP_show(String dato) {
        inicioFragment_view.cargar_CP_sql(dato);
    }

    @Override
    public void cargar_Municipio_show(String dato) {
        inicioFragment_view.cargar_Municipio_sql(dato);
    }
}
