package com.example.captureapp2_0.Vistas.ui_vistas.cap_segnals;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.captureapp2_0.R;
import com.example.captureapp2_0.objetos.Obj_Context;

import java.util.ArrayList;
import java.util.List;

public class fragment_lista_wifi extends Fragment {
    ListView listView;
    View view;
    ArrayAdapter<String> adaptador;
    public List<String> Lista_wifi = new ArrayList<String>();
    WifiManager wifiManager;//esta variable saca la informacion del wifi
    WifiInfo wifiInfo;//este extrae informacion del wifimanger
    WifiScanReceiver wifiReciever;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_wifi, container, false);
        listView = view.findViewById(R.id.Lista_señales);
        cargarlista();
        return view;
    }

    private void cargarlista() {
        listView= view.findViewById(R.id.Lista_señales_wifi);//instancia a la vista y la manda a la lista
        wifiManager = (WifiManager) Obj_Context.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //saca los datos de los servicios del telefono por eso el service y manda el wifi service
        wifiInfo = wifiManager.getConnectionInfo();//este saca la informacion de los servivios y los manda
        //a una variable para informar
        Log.e("mensaje info","men:"+wifiInfo.getRssi()+" "+wifiInfo.getSSID());
        wifiReciever = new WifiScanReceiver();//esto es la clase que se tiene abajo esto
        //inicia la captura
        wifiManager.startScan();
        adaptador = new ArrayAdapter<String>(Obj_Context.getContext(),
                android.R.layout.simple_list_item_1, Lista_wifi);
        listView.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(wifiReciever);
        super.onDestroyView();
    }
    //en los dos instancia a la clase del WifiScanReceiver de abajo
    public void onResume() {//
        getActivity().registerReceiver(wifiReciever,new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    class WifiScanReceiver extends BroadcastReceiver {//BroadcastReceiver no lo investigue
        @SuppressLint("UseValueOf")//como es un metodo sobre escrito recibe el contexto
        /*Context representa el estado actual de la aplicación y permite obtener información
        acerca de su entorno de ejecución. Es una clase abstracta
        y su implementación depende del sistema Android.*/
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = wifiManager.getScanResults();
            //inicia la lista del manger por eso el . get resultados saca los
            //resultados de la busqueda del momento
            Log.e("mensaje","si entro por 2 "+wifiScanList.size());
            Lista_wifi.clear();
            for (int i = 0; i < wifiScanList.size(); i++) {//recorre la lista y muestra los datos
                String level= Integer.toString(wifiScanList.get(i).level);
                String level2=Integer.toString(WifiManager.calculateSignalLevel(wifiScanList.get(i).level,10));
                String ssid = wifiScanList.get(i).SSID; //Get the SSID
                String bssid =  wifiScanList.get(i).BSSID; //Get the BSSID
                String algo=wifiScanList.get(i).capabilities;
                Log.e("dato wifi:","Nombre:"+ssid+" macaddres: "+bssid+
                        " level sin cal:"+level+" capabilitires:"+algo+"\n");
                Lista_wifi.add("Nombre: " + ssid
                        + "\nmacaddres: " + bssid
                        + "\nRSSI: " + level
                        + "\ncapabilitires: " + algo);
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