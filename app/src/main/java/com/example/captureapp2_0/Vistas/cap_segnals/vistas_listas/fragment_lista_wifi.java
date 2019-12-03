package com.example.captureapp2_0.Vistas.cap_segnals.vistas_listas;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.captureapp2_0.R;
import com.example.captureapp2_0.consultas_volley.Registro_wifi_volley;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_wifi;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class fragment_lista_wifi extends Fragment {
    ListView listView;
    View view;
    ArrayAdapter<String> adaptador;
    public List<String> Lista_wifi = new ArrayList<String>();
    WifiManager wifiManager;//esta variable saca la informacion del wifi
    WifiInfo wifiInfo;//este extrae informacion del wifimanger
    WifiScanReceiver wifiReciever;
    Obj_wifi obj_wifi;//clase que almacena los datos de la clase wifi aqui se guarda la informacion
    //linea 105
    Registro_wifi_volley registro_wifi_volley;
    String ID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_wifi, container, false);
        listView = view.findViewById(R.id.Lista_señales);
        SharedPreferences preferences=Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        ID=preferences.getString("Id_User","nulo");
        try {
            cargarlista();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void cargarlista() throws JSONException {
        listView= view.findViewById(R.id.Lista_señales_wifi);//instancia a la vista y la manda a la lista
        wifiManager = (WifiManager) Obj_Context.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //saca los datos de los servicios del telefono por eso el service y manda el wifi service
        wifiInfo = wifiManager.getConnectionInfo();//este saca la informacion de los servivios y los manda
        //a una variable para informar
        wifiReciever = new WifiScanReceiver();//esto es la clase que se tiene abajo esto
        //inicia la captura
        wifiManager.startScan();
        adaptador = new ArrayAdapter<String>(Obj_Context.getContext(),
                android.R.layout.simple_list_item_1, Lista_wifi);
        listView.setAdapter(adaptador);

        obj_wifi=new Obj_wifi();
        registro_wifi_volley=new Registro_wifi_volley();

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

            Lista_wifi.clear();
            if (wifiScanList.size()>0){
                //va sacando cada entidad de los datos
                for (int i = 0; i < wifiScanList.size(); i++) {//recorre la lista y muestra los datos
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
                    Lista_wifi.add("Nombre: " + ssid
                            + "\nMacaddres: " + bssid
                            + "\nRSSI: " + level);

                    obj_wifi.setId_dip((bssid+":"+ID));//id Dispositivo
                    obj_wifi.setNombre_dispos(ssid);//nombre dispositico
                    obj_wifi.setMacaddres(bssid);//macaddres
                    obj_wifi.setRSSI(level);//nivel de señal
                    obj_wifi.setFecha_cap(fecha);
                    obj_wifi.setHora(hora);
                    obj_wifi.setId_user(ID);//iD Usuario
                    obj_wifi.setId_tip_dispo("1");

                    registro_wifi_volley.setObj_wifi(obj_wifi);
                    try {
                        registro_wifi_volley.SQLite_exitencia();
                    } catch (JSONException e) {
                        e.printStackTrace();
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
                adaptador.notifyDataSetChanged();
            }
        });
    }


}