package com.example.captureapp2_0.Vistas.cap_segnals.vistas_listas;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class fragmen_listas_bluetooth extends Fragment implements BeaconConsumer {
    ListView listView;
    View view;
    ArrayAdapter<String> adaptador;
    public  List<String> Lista_beacon = new ArrayList<String>();
    private BeaconManager beaconManager=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmen_listas_wifi_blue, container, false);
        listView = view.findViewById(R.id.Lista_señales);
        cargarlista();
        return view;
    }

    void cargarlista(){
        Log.e("si entro beacon","porrrrx1");
        beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());//instancia los servicios de beacon
        //las siguientes dos lineas hacen referencia al modelo de beacon en este caso estimote
        beaconManager.getBeaconParsers().add(new
                BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        //como el anterior instancia el protocolo Eddystine y buscara todos los beacons con este
        //protocolo
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));
        beaconManager.bind(this);
        adaptador = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, Lista_beacon);
        listView.setAdapter(adaptador);
    }

    @Override
    public void onBeaconServiceConnect() {
        Log.e("si entro beacon","ya no aqui");
        beaconManager.removeAllRangeNotifiers();
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Lista_beacon.clear();
                    for (Beacon beacon : beacons) {
                        if (beacons.contains(beacon)){
                            Lista_beacon.add("UUID: " + beacon.getId1()
                                    + "\nMAJOR: " + beacon.getId2()
                                    + "\nRSSI: " + beacon.getRssi()
                                    + "\nTX: " + beacon.getTxPower()
                                    + "\nDISTANCE: " + beacon.getDistance()
                                    +"\nBluetoothAddress:"+beacon.getBluetoothAddress());
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
