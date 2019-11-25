package com.example.captureapp2_0.Vistas.ui_vistas.Menu_princi;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.captureapp2_0.Interfaces.Menu_princi_inter.inter_inicioFragment_presentador;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.inter_inicioFragment_view;
import com.example.captureapp2_0.Presentadores.inicioFragmen_presen_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.objetos.Obj_Context;

public class inicioFragment_view extends Fragment implements inter_inicioFragment_view {
    private TextView titulo,usuario_titu,text_Nombre,Nombre_sql,text_Correo,Correo_sql,text_Estado;
    private TextView estado_sql,text_Calle,Calle_sql,text_Colonia,Colonia_sql;
    private TextView text_Municipio,text_edad,text_CP;
    private TextView CP_sql,Edad_sql,Municipio_sql;
    private View root;
    private inter_inicioFragment_presentador presentador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_inicio_menu, container, false);
        activar_fuente();
        Obj_Context obj_context=new Obj_Context(getContext());
        presentador=new inicioFragmen_presen_impL(this);
        return root;
    }

    private void activar_fuente() {
        titulo = root.findViewById(R.id.Titulo);   //definimos en ONCREATE donde esta por
        usuario_titu=root.findViewById(R.id.titulo_user);
        Nombre_sql=root.findViewById(R.id.Nombre_sql);
        text_Nombre=root.findViewById(R.id.text_Nombre);// medio de un ID
        text_Correo=root.findViewById(R.id.text_Correo);
        Correo_sql=root.findViewById(R.id.Correo_sql);
        text_Estado=root.findViewById(R.id.text_Estado);
        estado_sql=root.findViewById(R.id.estado_sql);
        text_Calle=root.findViewById(R.id.text_Calle);
        Calle_sql=root.findViewById(R.id.Calle_sql);
        text_Colonia=root.findViewById(R.id.text_Colonia);
        Colonia_sql=root.findViewById(R.id.Colonia_sql);
        text_Municipio=root.findViewById(R.id.text_Municipio);
        text_edad=root.findViewById(R.id.text_edad);
        text_CP=root.findViewById(R.id.text_CP);
        CP_sql=root.findViewById(R.id.CP_sql);
        Edad_sql=root.findViewById(R.id.Edad_sql);
        Municipio_sql=root.findViewById(R.id.Municipio_sql);
        Typeface face=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Mohave-Bold.otf");
        titulo.setTypeface(face);
        usuario_titu.setTypeface(face);
        Nombre_sql.setTypeface(face);
        text_Nombre.setTypeface(face);
        text_Correo.setTypeface(face);
        Correo_sql.setTypeface(face);
        text_Estado.setTypeface(face);
        estado_sql.setTypeface(face);
        text_Calle.setTypeface(face);
        Calle_sql.setTypeface(face);
        text_Colonia.setTypeface(face);
        Colonia_sql.setTypeface(face);
    }//se cargan en los diferentes campos la fuente que usara

    @Override
    public void cargar_Nombre_sql(String dato) {
        Nombre_sql.setText(dato);
    }

    @Override
    public void cargar_correo_sql(String dato) {
        Correo_sql.setText(dato);
    }

    @Override
    public void cargar_estado_sql(String dato) {
        estado_sql.setText(dato);
    }

    @Override
    public void cargar_calle_sql(String dato) {
        Calle_sql.setText(dato);
    }

    @Override
    public void cargar_colonia_sql(String dato) {
        Colonia_sql.setText(dato);
    }

    @Override
    public void cargar_CP_sql(String dato) {
        CP_sql.setText(dato);
    }

    @Override
    public void cargar_Municipio_sql(String dato) {
        Municipio_sql.setText(dato);
    }

    @Override
    public void cargar_edad_sql(String dato) {
        Edad_sql.setText(dato);
    }
}