package com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;

public class gestion_sqlite_Servidores {
    private Context obj_context=Obj_Context.getContext();
    private  int servidor_predeter=0;
    public boolean Sql_servidores_registrar(Obje_servi obje_servi){
        obje_servi.sqLite= new Sqlite_DB_manejo(obj_context);
        obje_servi.cursor=verficar_evistencia_datos(obje_servi);
        if (obje_servi.cursor.getCount()==0){
            servidor_predeter=1;
        }
        if (agregar_servidor(obje_servi)){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Obje_servi> recuperardatos_servi(Obje_servi obje_servi){
        if(obje_servi.sqLite==null){
            obje_servi.sqLite= new Sqlite_DB_manejo(obj_context);
        }
        String sql="select * from Servidor";
        return  obje_servi.sqLite.Recuperar_servido(sql);
    }

    private boolean agregar_servidor(Obje_servi obje_servi){
        if (obje_servi.getIp_servidor().equals("")){
            obje_servi.setIp_servidor("Sin IP");
        }else{
            obje_servi.setDNS_ser("Sin DNNS");
        }
        String sql="insert into Servidor values(NULL,'"+obje_servi.getIp_servidor()+"','"+
                obje_servi.getDNS_ser()+"','"+obje_servi.getPuerto_orion()+"','"+
                obje_servi.getPuerto_crateDB()+"',"+servidor_predeter+");";
        Log.e("cadena","contacto"+sql);
        return obje_servi.sqLite.ejecutaSQL(sql);
    }

    private Cursor verficar_evistencia_datos(Obje_servi obje_servi) {
        String sql="";
        sql="select * from Servidor";
        return obje_servi.sqLite.consultaSQL(sql);
    }

    public boolean Eliminar_dato(int Id,Obje_servi obje_servi){
        obje_servi.sqLite= new Sqlite_DB_manejo(obj_context);
        String sql="DELETE FROM Servidor WHERE id ="+Id;
        if (obje_servi.sqLite.ejecutaSQL(sql)){
            return true;
        }else {
            return false;
        }
    }

    public boolean Actualizar(int Id,Obje_servi obje_servi){
        obje_servi.sqLite= new Sqlite_DB_manejo(obj_context);
        String sql="UPDATE Servidor set ip='"+obje_servi.getIp_servidor()+"',dnns='"+obje_servi.getDNS_ser()+"',"+
                "perto_orion='"+obje_servi.getPuerto_orion()+"',puerto_crate='"+obje_servi.getPuerto_crateDB()+"', servidor_prede='"+
                 obje_servi.getServidor_predeter()+"' WHERE id ="+Id;
        Log.e("cadena","cadena mas importante del dia:\n"+sql);
        if (obje_servi.sqLite.ejecutaSQL(sql)){
            return true;
        }else {
            return false;
        }
    }

    public boolean cambiar_servidor_sql(int Id,Obje_servi obje_servi){
        obje_servi.sqLite= new Sqlite_DB_manejo(obj_context);
        String sql="UPDATE Servidor set servidor_prede=0 where servidor_prede=1";
        if (obje_servi.sqLite.ejecutaSQL(sql)){
            sql="UPDATE Servidor set servidor_prede='1' WHERE id ="+Id;
            if (obje_servi.sqLite.ejecutaSQL(sql)){
                Log.e("cadena","segunda validacion:\n"+sql);
                return true;
            }else
                return false;
        }else {
            return false;
        }
    }
}
