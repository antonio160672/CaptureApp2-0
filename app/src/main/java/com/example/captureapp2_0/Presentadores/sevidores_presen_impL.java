package com.example.captureapp2_0.Presentadores;

import android.util.Log;

import com.example.captureapp2_0.Modelo.Modelo.Interactores.Servidores_interactores.servidores_interactor_impL;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.onlistener_Registro_servidores_frag_interac;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_interactor_inT;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_presentador_frag_inteR;
import com.example.captureapp2_0.Vistas.Servidores_frag.servidor_view_Fragment;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;

public class sevidores_presen_impL implements onlistener_Registro_servidores_frag_interac, servidores_presentador_frag_inteR {
    private servidor_view_Fragment servidor_view_fragment;
    private servidores_interactor_inT servidores_interactor_impL;

    public sevidores_presen_impL(servidor_view_Fragment servidor_view_fragment) {
        this.servidor_view_fragment = servidor_view_fragment;
        servidores_interactor_impL=new servidores_interactor_impL(this);
    }

    @Override
    public void consultar_servi(Obje_servi obje_servi) {
        if(obje_servi!=null){
            servidores_interactor_impL.consultar(obje_servi);
        }

    }

    @Override
    public void eliminar_dispo(int Id, Obje_servi obje_servi) {
        if(obje_servi!=null){
            servidores_interactor_impL.eliminar(Id,obje_servi);
        }
    }

    @Override
    public void actualizar_dispo(int dato, Obje_servi obje_servi) {
        if(obje_servi!=null){
            Log.e("servidor","valor del dnss:"+obje_servi.getDNS_ser());
            servidores_interactor_impL.Actualizar(dato,obje_servi);
        }
    }

    @Override
    public void registrar_servidor(Obje_servi obje_servi) {
        if(servidor_view_fragment!=null){
            servidores_interactor_impL.registrar_servidor(obje_servi);
            Log.e("servidor","valor del dnss:"+obje_servi.getDNS_ser());
        }
    }

    @Override
    public void cambiar_servidor(int dato, Obje_servi obje_servi) {
        if(servidor_view_fragment!=null){
            servidores_interactor_impL.servidor_por_defecto(dato,obje_servi);
            Log.e("servidor","valor del dnss:"+obje_servi.getDNS_ser());
        }
    }

    @Override
    public void retornar_lista( ArrayList<Obje_servi> servis) {
        if(servidor_view_fragment!=null){
            Log.e("vamos bien","creo");
            servidor_view_fragment.cargarLista_vista(servis);
        }
    }

    @Override
    public void retornar_mensaje() {

    }
}

