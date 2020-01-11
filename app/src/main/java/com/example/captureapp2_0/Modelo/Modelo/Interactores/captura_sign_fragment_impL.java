package com.example.captureapp2_0.Modelo.Modelo.Interactores;

import android.util.Log;

import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.Interactor_captuSig;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.onlistener_captura_segnas_interf;
import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

public class captura_sign_fragment_impL implements Interactor_captuSig{
    onlistener_captura_segnas_interf listener;

    public captura_sign_fragment_impL(onlistener_captura_segnas_interf listener) {
        this.listener = listener;
    }

    @Override
    public Boolean Salida_internet(String DNSS,String IP) {
        try {
            Log.e("se esta"," dentro de la salida a internet");
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 "+DNSS);
            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Obje_servi verificar_servidor(Obje_servi servi) {
        if(servi!=null){
            servi.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
        }
        String sql="select * from Servidor where servidor_prede=1";
        return servi.sqLite.Recuperar_servidor_objeto(sql,servi);
    }

    @Override
    public void captura_wifi() {
    }

    @Override
    public void captura_blue() {
    }



}
