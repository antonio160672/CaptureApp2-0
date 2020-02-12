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
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.cap_segnals.Adaptador_lista_diseño.Adaptador;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Registro_bluetooth_volley;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;

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
import java.util.Locale;

public class fragmen_listas_bluetooth extends Fragment implements BeaconConsumer {
    ListView listView;
    View view;
    public  ArrayList<Obj_bluetooth> Lista_beacon = new ArrayList<Obj_bluetooth>();
    private BeaconManager beaconManager=null;
    private Obj_bluetooth obj_bluetooth;
    private String ID;
    private Registro_bluetooth_volley bluetooth_volley;
    private Obje_servi obje_servi;
    Adaptador adaptador;


    public fragmen_listas_bluetooth(Obje_servi obje_servi) {
        this.obje_servi = obje_servi;
    }
    public fragmen_listas_bluetooth() {
    }

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

    public void cargarlista(){
        beaconManager = BeaconManager.
                getInstanceForApplication(getApplicationContext());//instancia los servicios de beacon
        //las siguientes dos líneas hacen referencia al modelo de beacon en este caso estimote
        beaconManager.getBeaconParsers().add(new
                BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        //Eddystine y buscara todos los beacons con este protocolo
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));
        bluetooth_volley =new Registro_bluetooth_volley();

        beaconManager.bind(this);//se llama al método onBeaconServiceConnect
        adaptador=new Adaptador(null,
                Obj_Context.getContext().getApplicationContext(),  Lista_beacon);
        //se instancia un adaptador para mostrar la información de la lista
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.removeAllRangeNotifiers();
        beaconManager.addRangeNotifier(new RangeNotifier() {//sobre escribe métodos
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                //recolecta colección de beacons
                if (beacons.size() > 0) {//mientras sea mayor a cero
                    Lista_beacon.clear();//limpia la lista que almacena datos
                    for (Beacon beacon : beacons) {//se recorre con forech para cada objeto
                        if (beacons.contains(beacon)){
                            obj_bluetooth=new Obj_bluetooth();//objeto que almacena datos
                            SimpleDateFormat dateFormat = new //crea la estructura de la fecha
                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            DateFormat hourFormat = new //crea estrucutra de la hora
                                    SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                            Date date = new Date();//se instancia la hora y fecha
                            String fecha = dateFormat.format(date);//se les da formato fecha
                            String hora = hourFormat.format(date);//se les da formato
                            obj_bluetooth.setUUID(beacon.getId1().toString());//UUID
                            obj_bluetooth.setBluetoothAddress(beacon.getBluetoothAddress());//MAC
                            obj_bluetooth.setId_dip(beacon.getBluetoothAddress()+":"+ID);//ID+MAC
                            obj_bluetooth.setMAJOR(beacon.getId2().toString());
                            obj_bluetooth.setRSSI(Integer.toString(beacon.getRssi()));//señal
                            obj_bluetooth.setTX(Integer.toString(beacon.getTxPower()));//bateria
                            obj_bluetooth.setId_tip_dispo("2");
                            obj_bluetooth.setId_user(ID);
                            obj_bluetooth.setFecha_cap(fecha);
                            obj_bluetooth.setHora(hora);
                            bluetooth_volley.setObj_bluetooth(obj_bluetooth);//Volley
                            if ((obje_servi!=null)&&(!obje_servi.getDNS_ser().equals("")||!obje_servi.getIp_servidor().equals(""))){
                                Log.e("Nueva log","valor del servidor"+obje_servi.getDNS_ser());
                                Log.e("Nueva log","valor del servidor"+obje_servi.getIp_servidor());
                                try {
                                    if(!(obje_servi.getDNS_ser().equals(""))){
                                        bluetooth_volley.SQLite_exitencia_registro(obje_servi.getDNS_ser(),obje_servi.getPuerto_crateDB(),
                                        obje_servi.getPuerto_orion());
                                    }else{
                                        bluetooth_volley.SQLite_exitencia_registro(obje_servi.getIp_servidor(),obje_servi.getPuerto_crateDB(),
                                                obje_servi.getPuerto_orion());
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                bluetooth_volley.Insertar_sin_servidor_Sin_conexion_entidad_Bluetooth();

                                Log.e("sin datos","tamaño lista"+Lista_beacon.size());
                            }
                            Lista_beacon.add(obj_bluetooth);
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
                listView.setAdapter(adaptador);
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
        return Obj_Context.getContext().getApplicationContext();
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        getApplicationContext().unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return getApplicationContext().bindService(intent, serviceConnection, i);

    }
}
