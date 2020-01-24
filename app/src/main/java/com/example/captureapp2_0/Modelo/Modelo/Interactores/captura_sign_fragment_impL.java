package com.example.captureapp2_0.Modelo.Modelo.Interactores;

import android.os.Handler;
import android.util.Log;

import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.Interactor_captuSig;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.onlistener_captura_segnas_interf;
import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Registro_bluetooth_volley;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Registro_wifi_volley;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.gestion_datos_sin_conexion_volley_sql;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;

import java.util.ArrayList;

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
    public void verificar_recuperar_datos() {
        Obj_bluetooth obj_bluetooth=new Obj_bluetooth();
        Obj_wifi wifi=new Obj_wifi();
        gestion_datos_sin_conexion_volley_sql gestion=new gestion_datos_sin_conexion_volley_sql(
                                                            obj_bluetooth,wifi,this);
        gestion.recuperar_datos_sin_conexi();
    }

    @Override
    public void envio_datos_sinconex(final ArrayList<Obj_bluetooth> Array_bluet, final ArrayList<Obj_wifi> Array_wifi, final Obje_servi obje_servi) {
        final Obj_bluetooth bluetooth=Array_bluet.get(0);
        final Obj_wifi wifi =Array_wifi.get(0);
        new Handler().postDelayed(new Runnable(){//metodo para que se tenga un tiempo de
            //respuesta más largo
            @Override
            public void run() {
                Registro_bluetooth_volley registro_bluetooth_volley=new Registro_bluetooth_volley();
                Registro_wifi_volley registroWifiVolley=new Registro_wifi_volley();
                for (Obj_bluetooth objeto_blue:Array_bluet) {
                    registro_bluetooth_volley.setObj_bluetooth(objeto_blue);
                    try {
                        if(!(obje_servi.getDNS_ser().equals(""))){
                            registro_bluetooth_volley.SQLite_exitencia_registro(obje_servi.getDNS_ser(),obje_servi.getPuerto_crateDB(),
                                    obje_servi.getPuerto_orion());
                        }else{
                            registro_bluetooth_volley.SQLite_exitencia_registro(obje_servi.getIp_servidor(),obje_servi.getPuerto_crateDB(),
                                    obje_servi.getPuerto_orion());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                for (Obj_wifi objeto_wifi:Array_wifi) {
                    registroWifiVolley.setObj_wifi(objeto_wifi);
                    try {
                        if(!(obje_servi.getDNS_ser().equals(""))){
                            registroWifiVolley.SQLite_exitencia_registro(obje_servi.getDNS_ser(),obje_servi.getPuerto_crateDB(),
                                    obje_servi.getPuerto_orion());
                        }else{
                            registroWifiVolley.SQLite_exitencia_registro(obje_servi.getIp_servidor(),obje_servi.getPuerto_crateDB(),
                                    obje_servi.getPuerto_orion());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listener.dismi_progress("");
            }
        },5000);
        gestion_datos_sin_conexion_volley_sql sql=new gestion_datos_sin_conexion_volley_sql(bluetooth,wifi,this);

        sql.limpieza_tablas();
    }

    @Override
    public void retonar_datos_sin_conex(ArrayList<Obj_bluetooth> Array_bluet, ArrayList<Obj_wifi> Array_wifi){
        listener.retornar_datos_sin_conx(Array_bluet,Array_wifi);
    }


}
