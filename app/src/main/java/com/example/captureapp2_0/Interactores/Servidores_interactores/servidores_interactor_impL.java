package com.example.captureapp2_0.Interactores.Servidores_interactores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.captureapp2_0.Interfaces.Servidores_interactores.onlistener_Registro_servidores_frag_interac;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_interactor_inT;
import com.example.captureapp2_0.consultas_volley_sqlite.Registro_sqlite_Servidores;
import com.example.captureapp2_0.objetos.Obje_servi;

public class servidores_interactor_impL implements servidores_interactor_inT {
    private onlistener_Registro_servidores_frag_interac onlistenerRegistro;

    @Override
    public void registrar_servidor(Obje_servi obje_servi) {
        if (obje_servi!=null){
            Registro_sqlite_Servidores servidores=new Registro_sqlite_Servidores();
            if (servidores.Sql_servidores_registrar(obje_servi)){
                onlistenerRegistro.retornar_mensaje();
            }
        }
    }
}