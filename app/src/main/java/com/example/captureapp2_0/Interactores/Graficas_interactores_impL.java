package com.example.captureapp2_0.Interactores;

import android.util.Log;

import com.example.captureapp2_0.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Interfaces.Graficas_intF.Graficas_interactores_interface;
import com.example.captureapp2_0.Interfaces.Graficas_intF.onlistener_graficas;
import com.example.captureapp2_0.Presentadores.Graficas_presentador_impL;
import com.example.captureapp2_0.funciones_generas.fechas_trasnformacion;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_bluetooth;
import com.example.captureapp2_0.objetos.Objeto_bluetoo_grafica;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.util.ArrayList;

public class Graficas_interactores_impL implements Graficas_interactores_interface {
    private onlistener_graficas onlistenerGraficas;
    private long fecha_ini,fecha_fin;
    private Obj_bluetooth bluetooth;
    private fechas_trasnformacion trasnformacion;

    public Graficas_interactores_impL(onlistener_graficas onlistenerGraficas) {
        this.onlistenerGraficas = onlistenerGraficas;
        bluetooth = new Obj_bluetooth();
        trasnformacion=new fechas_trasnformacion();
    }

    @Override
    public void validacion_fechas(String fecha_ini,String fecha_fin) {
        try {
            this.fecha_ini=trasnformacion.fecha_milisegundos(fecha_ini+" 00:00:00","dd-MM-yyyy hh:mm:ss");
            this.fecha_fin=trasnformacion.fecha_milisegundos(fecha_fin+" 00:00:00","dd-MM-yyyy hh:mm:ss");
            Recuperar_datos();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ///////////////////transfromar las fechas a enteros

    }

    @Override
    public void Recuperar_datos() {
        boolean bandera=false;
        ArrayList<Obj_bluetooth> lista_blueList=new ArrayList();
        lista_blueList=null;
        bluetooth.sqLite=new Sqlite_DB_manejo(Obj_Context.getContext());
        String sql="SELECT * FROM Entidad_Bluetooth where Fecha>="+fecha_ini+" and Fecha<="+fecha_fin;
        lista_blueList=bluetooth.sqLite.Recuperar_lista_bluetooth(sql);
        ArrayList<Objeto_bluetoo_grafica> List_blue_grafi=new ArrayList();
        Objeto_bluetoo_grafica grafica=new Objeto_bluetoo_grafica();
        if (lista_blueList!=null)
        for (Obj_bluetooth objetos:lista_blueList) {
            bandera=false;
            for (int x=0;x<List_blue_grafi.size();x++){//recorre toda la lista pero de la
                //de objetos de la lista de graficos
                if (List_blue_grafi.get(x).getBluetoothAddress().equals(objetos.getBluetoothAddress())){
                    //en caso de que tenga el mismo identificador solo agrega el RSSi
                    List_blue_grafi.get(x).lista_Rssi.add(objetos.getRSSI());
                    bandera=true;
                }
            }
            if(bandera==false){
                Objeto_bluetoo_grafica nueva_grafi=new Objeto_bluetoo_grafica();
                nueva_grafi.setId_db(objetos.getId_dip());
                nueva_grafi.setBluetoothAddress(objetos.getBluetoothAddress());
                nueva_grafi.lista_Rssi.add(objetos.getRSSI());
                List_blue_grafi.add(nueva_grafi);
            }
        }
        generar_grafica(List_blue_grafi);
    }

    private void generar_grafica(ArrayList<Objeto_bluetoo_grafica> list_blue_grafi) {
        ArrayList<BarEntry> barras=new ArrayList<>();
        ArrayList<LegendEntry> etiquetas = new ArrayList<LegendEntry>();
        float i= (float) 0.1;
        int o=0;
        for (Objeto_bluetoo_grafica datos_gra:list_blue_grafi) {
            barras.add(new BarEntry(i,datos_gra.lista_Rssi.size()));
            LegendEntry entry=new LegendEntry();
            entry.formColor=ColorTemplate.COLORFUL_COLORS[o];
            entry.label=(datos_gra.getBluetoothAddress());
            etiquetas.add(entry);
            i++;
            o++;
        }
        Log.e("tamaño lista","lista"+etiquetas.size());
        BarDataSet datos=new BarDataSet(barras,"Graficas de Bluetooth");
        datos.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData(datos);
        data.setBarWidth(0.45f);
        onlistenerGraficas.retornar_grafica(data,etiquetas);
    }

}
