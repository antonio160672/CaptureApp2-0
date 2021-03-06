package com.example.captureapp2_0.Vistas.cap_segnals.vistas_listas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.cap_segnals.Adaptador_lista_diseño.Adaptador;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Registro_wifi_volley;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class fragment_lista_wifi extends Fragment {
    ListView listView;//list para la interfas grafica
    View view;
    Adaptador adaptador1;//este se ocupa para el diseño de la lista
    public ArrayList<Obj_wifi> Lista_wifi = new ArrayList<Obj_wifi>();
    WifiManager wifiManager;//esta variable saca la informacion del wifi
    WifiInfo wifiInfo;//este extrae informacion del wifimanger
    WifiScanReceiver wifiReciever;
    Obj_wifi obj_wifi;//clase que almacena los datos de la clase wifi aqui se guarda la informacion
    //linea 105
    Registro_wifi_volley registro_wifi_volley;
    String ID;
    private Obje_servi obje_servi;

    public fragment_lista_wifi(Obje_servi obje_servi) {
        this.obje_servi = obje_servi;
    }
    public fragment_lista_wifi() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_wifi, container, false);
        listView = view.findViewById(R.id.Lista_señales);
        SharedPreferences preferences=Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        ID=preferences.getString("Id_User","nulo");
        ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
        try {
            cargarlista();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //instancia a la vista y la manda a la lista
        return view;
    }


    private void cargarlista() throws JSONException {
        listView= view.findViewById(R.id.Lista_señales_wifi);
        wifiManager = (WifiManager)(WifiManager)getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //saca los datos de los servicios del telefono por eso el service y manda el wifi service
        wifiInfo = wifiManager.getConnectionInfo();//este saca la informacion de los servivios y los manda
        //a una variable para informar
        wifiReciever = new WifiScanReceiver();//esto es la clase que se tiene abajo esto
        //inicia la captura
        wifiManager.startScan();/*
        adaptador = new ArrayAdapter<String>(Obj_Context.getContext(),
                android.R.layout.simple_list_item_1, Lista_wifi);*/
        registro_wifi_volley=new Registro_wifi_volley();

        adaptador1=new Adaptador(Lista_wifi,Obj_Context.getContext(),null);
        listView.setAdapter(adaptador1);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Obj_Context.getContext().unregisterReceiver(wifiReciever);
    }
    //en los dos instancia a la clase del WifiScanReceiver de abajo
    public void onResume() {//
        super.onResume();
        getActivity().registerReceiver(wifiReciever,new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    class WifiScanReceiver extends BroadcastReceiver {//BroadcastReceiver no lo investigue
        @RequiresApi(api = Build.VERSION_CODES.M)
        @SuppressLint("UseValueOf")//como es un metodo sobre escrito recibe el contexto
        /*Context representa el estado actual de la aplicación y permite obtener información
        acerca de su entorno de ejecución. Es una clase abstracta
        y su implementación depende del sistema Android.*/
        public void onReceive(Context c, Intent intent){
            List<ScanResult> wifiScanList = wifiManager.getScanResults();
            //inicia la lista del manger por eso el . get resultados saca los
            //resultados de la busqueda del momento
            Log.e("entra en wifi","tamañooooooooooo "+wifiScanList.size());
            if (wifiScanList.size()>0){
                Lista_wifi.clear();
                //va sacando cada entidad de los datos
                for (int i = 0; i < wifiScanList.size(); i++) {//recorre la lista y muestra los datos
                    obj_wifi=new Obj_wifi();
                    String level= Integer.toString(wifiScanList.get(i).level);
                    String ssid = wifiScanList.get(i).SSID; //Get the SSID
                    String bssid =  wifiScanList.get(i).BSSID; //Get the BSSID

                    SimpleDateFormat dateFormat = new //crea la estructura de la fecha
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    DateFormat hourFormat = new //crea estrucutra de la hora
                            SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    Date date = new Date();//se instancia la hora y fecha
                    String fecha = dateFormat.format(date);//se les da formato
                    String hora = hourFormat.format(date);//se les da formato
                    obj_wifi.setId_dip((bssid+":"+ID));//id Dispositivo
                    obj_wifi.setNombre_dispos(ssid);//nombre dispositico
                    obj_wifi.setMacaddres(bssid);//macaddres
                    obj_wifi.setRSSI(level);//nivel de señal
                    obj_wifi.setFecha_cap(fecha);
                    obj_wifi.setHora(hora);
                    obj_wifi.setId_user(ID);//iD Usuario
                    obj_wifi.setId_tip_dispo("1");
                    Lista_wifi.add(obj_wifi);
                    registro_wifi_volley.setObj_wifi(obj_wifi);
                    if (obje_servi!=null&&(!obje_servi.getDNS_ser().equals("")||!obje_servi.getIp_servidor().equals(""))){
                        try {
                            if((obje_servi.getDNS_ser().equals(""))||(obje_servi.getDNS_ser().equals("Sin DNNS"))){
                                registro_wifi_volley.SQLite_exitencia_registro(obje_servi.getIp_servidor(),obje_servi.getPuerto_crateDB(),
                                        obje_servi.getPuerto_orion());
                            }else{
                                registro_wifi_volley.SQLite_exitencia_registro(obje_servi.getDNS_ser(),obje_servi.getPuerto_crateDB(),
                                        obje_servi.getPuerto_orion());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        registro_wifi_volley.insertar_sin_conexion_entidad_wifi();
                        Log.e("sin wifi","tamaño lista"+Lista_wifi.size());
                    }
                }
            }
            updateList();
            wifiManager.startScan(); //se manda a llamar a si misma
        }
    }
    public void updateList() {
        view.post(new Runnable() {
            @Override
            public void run() {
                //this should wipe out all existing adapter data
                adaptador1.notifyDataSetChanged();
            }
        });
    }


}