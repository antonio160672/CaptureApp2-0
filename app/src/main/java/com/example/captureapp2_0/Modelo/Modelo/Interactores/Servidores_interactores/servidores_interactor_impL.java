package com.example.captureapp2_0.Modelo.Modelo.Interactores.Servidores_interactores;

import android.util.Log;

import com.example.captureapp2_0.Interfaces.Servidores_interactores.onlistener_Registro_servidores_frag_interac;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_interactor_inT;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.gestion_sqlite_Servidores;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;

public class servidores_interactor_impL implements servidores_interactor_inT {
    private onlistener_Registro_servidores_frag_interac onlistenerRegistro;
    ArrayList<Obje_servi> servis;

    public servidores_interactor_impL(onlistener_Registro_servidores_frag_interac onlistenerRegistro) {
        this.onlistenerRegistro = onlistenerRegistro;
    }

    @Override
    public void registrar_servidor(Obje_servi obje_servi) {
        if (obje_servi!=null){
            gestion_sqlite_Servidores servidores=new gestion_sqlite_Servidores();
            if (servidores.Sql_servidores_registrar(obje_servi)){
                servis=servidores.recuperardatos_servi(obje_servi);
                if(servis!=null){
                    Log.e("valores servidor","DNNS:"+servis.get(0).getDNS_ser()+" IP:"+servis.get(0).getIp_servidor()+
                            " ID:"+servis.get(0).getId_servi()+" Serpre:"+servis.get(0).getServidor_predeter());
                    onlistenerRegistro.retornar_lista(servis);
                    onlistenerRegistro.retornar_mensaje();
                }
            }
        }
    }

    @Override
    public void consultar(Obje_servi obje_servi) {
        gestion_sqlite_Servidores servidores=new gestion_sqlite_Servidores();
        servis=servidores.recuperardatos_servi(obje_servi);
        if(servis!=null){
            Log.e("valores servidor","DNNS:"+servis.get(0).getDNS_ser()+" IP:"+servis.get(0).getIp_servidor()+
                    " ID:"+servis.get(0).getId_servi()+" Serpre:"+servis.get(0).getServidor_predeter());
            onlistenerRegistro.retornar_lista(servis);
            onlistenerRegistro.retornar_mensaje();
        }
    }

    @Override
    public void eliminar(int dato, Obje_servi obje_servi) {
        gestion_sqlite_Servidores servidores=new gestion_sqlite_Servidores();
        if(servidores.Eliminar_dato(dato,obje_servi)){
            servis=servidores.recuperardatos_servi(obje_servi);
            onlistenerRegistro.retornar_lista(servis);
            onlistenerRegistro.retornar_mensaje();
            servidores=null;
        }
    }

    @Override
    public void Actualizar(int dato, Obje_servi obje_servi) {
        gestion_sqlite_Servidores servidores=new gestion_sqlite_Servidores();
        if(servidores.Actualizar(dato,obje_servi)){
            servis=servidores.recuperardatos_servi(obje_servi);
            onlistenerRegistro.retornar_lista(servis);
            onlistenerRegistro.retornar_mensaje();
            servidores=null;
        }
    }

    @Override
    public void servidor_por_defecto(int dato, Obje_servi obje_servi) {
        gestion_sqlite_Servidores servidores=new gestion_sqlite_Servidores();
        if(servidores.cambiar_servidor_sql(dato,obje_servi)){
            servis=servidores.recuperardatos_servi(obje_servi);
            onlistenerRegistro.retornar_lista(servis);
            onlistenerRegistro.retornar_mensaje();
            servidores=null;
        }

    }
}