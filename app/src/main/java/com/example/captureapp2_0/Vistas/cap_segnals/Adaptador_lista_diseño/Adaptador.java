package com.example.captureapp2_0.Vistas.cap_segnals.Adaptador_lista_diseño;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater;
    private ArrayList<Obj_wifi> Lista_wifi;
    private ArrayList<Obj_bluetooth> obj_bluetooths;
    private Context context;

    public Adaptador(ArrayList<Obj_wifi> lista_wifi, Context context,ArrayList<Obj_bluetooth> Lista_bluetooth) {
        Lista_wifi = lista_wifi;
        this.context = context;
        obj_bluetooths=Lista_bluetooth;
        inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View view1=inflater.inflate(R.layout.elemento_adaptador_wifi,null);
        TextView Tipo_dispo=view1.findViewById(R.id.Tipo_dispo);
        TextView UUID_Nombre=view1.findViewById(R.id.UUID_Nombre);
        TextView Address=view1.findViewById(R.id.Address);
        TextView RSSI=view1.findViewById(R.id.RSSI);
        TextView MAJOR=view1.findViewById(R.id.MAJOR);
        TextView TX=view1.findViewById(R.id.TX);
        if (obj_bluetooths==null){
            Tipo_dispo.setText("Disp.: Wifi");
            UUID_Nombre.setText("Nombre:"+Lista_wifi.get(i).getNombre_dispos());
            Address.setText("Mac:"+Lista_wifi.get(i).getMacaddres());
            RSSI.setText("RSSI:"+Lista_wifi.get(i).getRSSI());
            MAJOR.setVisibility(view1.INVISIBLE);
            TX.setVisibility(view1.INVISIBLE);
        }else{
            ImageView imageView= view1.findViewById(R.id.imageView2);
            imageView.setImageResource(R.drawable.bluetooth);
            Tipo_dispo.setText("Disp.: Bluetooth");
            UUID_Nombre.setText("UUID: "+obj_bluetooths.get(i).getUUID());
            Address.setText("MAC: "+obj_bluetooths.get(i).getBluetoothAddress());
            RSSI.setText("RSSI: "+obj_bluetooths.get(i).getRSSI());
            MAJOR.setText("Major: "+obj_bluetooths.get(i).getMAJOR());
            TX.setText("TX:"+obj_bluetooths.get(i).getTX());
        }

        return view1;
    }

    @Override
    public int getCount() {
        if (obj_bluetooths==null){
            return Lista_wifi.size();
        }else {
            return obj_bluetooths.size();
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
