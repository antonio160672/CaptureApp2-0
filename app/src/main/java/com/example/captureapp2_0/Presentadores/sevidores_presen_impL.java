package com.example.captureapp2_0.Presentadores;

import com.example.captureapp2_0.Interactores.servidores_interactor_impL;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.onlistener_Registro_servidores_frag_interac;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_presentador_frag_inteR;
import com.example.captureapp2_0.Vistas.Servidores_frag.servidor_view_Fragment;
import com.example.captureapp2_0.objetos.Obje_servi;

public class sevidores_presen_impL implements onlistener_Registro_servidores_frag_interac, servidores_presentador_frag_inteR {
    private servidor_view_Fragment servidor_view_fragment;
    private servidores_interactor_impL servidores_interactor_impL;

    public sevidores_presen_impL(servidor_view_Fragment servidor_view_fragment) {
        this.servidor_view_fragment = servidor_view_fragment;
        servidores_interactor_impL=new servidores_interactor_impL();
    }

    @Override
    public void registrar_servidor(Obje_servi obje_servi) {
        if(servidor_view_fragment!=null){
            servidores_interactor_impL.registrar_servidor(obje_servi);
        }
    }

    @Override
    public void retornar_lista() {

    }

    @Override
    public void retornar_mensaje() {

    }
}
