package com.example.captureapp2_0.Vistas.cap_segnals.vistas_listas;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.cap_segnals.Adaptador_lista_diseño.Adaptador;
import com.example.captureapp2_0.consultas_volley.Registro_bluetooth_volley;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_bluetooth;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class fragmen_listas_bluetooth extends Fragment implements BeaconConsumer {
    ListView listView;
    View view;
    public  ArrayList<Obj_bluetooth> Lista_beacon = new ArrayList<Obj_bluetooth>();
    private BeaconManager beaconManager=null;
    private Obj_bluetooth obj_bluetooth;
    private String ID;
    private Registro_bluetooth_volley bluetooth_volley;

    Adaptador adaptador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmen_listas_wifi_blue, container, false);
        listView = view.findViewById(R.id.Lista_señales);
        SharedPreferences preferences= Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        ID=preferences.getString("Id_User","nulo");
        cargarlista();
        return view;
    }

    void cargarlista(){
        beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());//instancia los servicios de beacon
        //las siguientes dos lineas hacen referencia al modelo de beacon en este caso estimote
        beaconManager.getBeaconParsers().add(new
                BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        //como el anterior instancia el protocolo Eddystine y buscara todos los beacons con este
        //protocolo
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));
        bluetooth_volley =new Registro_bluetooth_volley();

        beaconManager.bind(this);
        adaptador=new Adaptador(null,Obj_Context.getContext(),  Lista_beacon);
        listView.setAdapter(adaptador);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.removeAllRangeNotifiers();
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Lista_beacon.clear();
                    for (Beacon beacon : beacons) {
                        if (beacons.contains(beacon)){
                            obj_bluetooth=new Obj_bluetooth();
                            SimpleDateFormat dateFormat = new //crea la estructura de la fecha
                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            DateFormat hourFormat = new //crea estrucutra de la hora
                                    SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                            Date date = new Date();//se instancia la hora y fecha
                            String fecha = dateFormat.format(date);//se les da formato
                            String hora = hourFormat.format(date);//se les da formato
                            obj_bluetooth.setUUID(beacon.getId1().toString());
                            obj_bluetooth.setBluetoothAddress(beacon.getBluetoothAddress());
                            obj_bluetooth.setId_dip(beacon.getBluetoothAddress()+":"+ID);
                            obj_bluetooth.setMAJOR(beacon.getId2().toString());
                            obj_bluetooth.setRSSI(Integer.toString(beacon.getRssi()));
                            obj_bluetooth.setTX(Integer.toString(beacon.getTxPower()));
                            obj_bluetooth.setId_tip_dispo("2");
                            obj_bluetooth.setId_user(ID);
                            obj_bluetooth.setFecha_cap(fecha);
                            obj_bluetooth.setHora(hora);
                            Lista_beacon.add(obj_bluetooth);
                            bluetooth_volley.setObj_bluetooth(obj_bluetooth);
                            try {
                                bluetooth_volley.SQLite_exitencia();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    updateList();
                }
            }
        });
        try {

            beaconManager.setForegroundScanPeriod(1500);
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }
        // Especifica una clase que debería ser llamada cada vez que BeaconsService obtiene datos, una vez por segundo por defecto

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

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.setAndroidLScanningDisabled(true);
        beaconManager.unbind(this);
    }

    @Override
    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        getActivity().unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return getActivity().bindService(intent, serviceConnection, i);

    }
}
