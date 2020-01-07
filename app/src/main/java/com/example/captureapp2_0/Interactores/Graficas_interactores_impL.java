package com.example.captureapp2_0.Interactores;

import android.util.Log;

import com.example.captureapp2_0.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Interfaces.Graficas_intF.Graficas_interactores_interface;
import com.example.captureapp2_0.Interfaces.Graficas_intF.onlistener_graficas;
import com.example.captureapp2_0.Presentadores.Graficas_presentador_impL;
import com.example.captureapp2_0.funciones_generas.fechas_trasnformacion;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_bluetooth;
import com.example.captureapp2_0.objetos.Obj_wifi;
import com.example.captureapp2_0.objetos.Objeto_bluetoo_grafica;
import com.example.captureapp2_0.objetos.Objeto_wifi_grafica;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.util.ArrayList;

public class Graficas_interactores_impL implements Graficas_interactores_interface {
    private onlistener_graficas onlistenerGraficas;
    private long fecha_ini,fecha_fin;
    private String dispositivo,grafica,fechaini_strin,fechafin_string;
    private Obj_bluetooth bluetooth;
    private Obj_wifi wifi;
    private fechas_trasnformacion trasnformacion;
    private ArrayList<Objeto_bluetoo_grafica> List_blue_grafi;
    private ArrayList<Objeto_wifi_grafica> List_Wifi_grafi;

    public Graficas_interactores_impL(onlistener_graficas onlistenerGraficas) {
        this.onlistenerGraficas = onlistenerGraficas;
        bluetooth = new Obj_bluetooth();
        wifi=new Obj_wifi();
        trasnformacion=new fechas_trasnformacion();
    }

    @Override
    public void validacion_fechas(String fecha_ini,String fecha_fin,String dispositivo, String grafica) {
            this.fechaini_strin=fecha_ini;
            this.fechafin_string=fecha_fin;
            this.dispositivo=dispositivo;
            this.grafica=grafica;
            validacion_campos();
        ///////////////////transfromar las fechas a enteros

    }

    @Override
    public void validacion_campos() {
        if (fechafin_string.isEmpty()&&fechaini_strin.isEmpty()&&
                dispositivo.equals("Dispositivo")&&grafica.equals("Grafica")){
            onlistenerGraficas.enviar_error_fecha("Las fechas no fueron ingresadas");
            onlistenerGraficas.enviar_error_graficas("No elegido");
            onlistenerGraficas.enviar_error_dispositivo("No elegido");
        }else {
            if (fechaini_strin.isEmpty()){
                onlistenerGraficas.enviar_error_fecha("Fecha de inicio no seleccionada");
            }
            if (fechafin_string.isEmpty()){
                onlistenerGraficas.enviar_error_fecha("Fecha de fin no seleccionada");
            }
            if(dispositivo.equals("Dispositivo")){
                onlistenerGraficas.enviar_error_dispositivo("No elegido");
            }
            if(grafica.equals("Grafica")){
                onlistenerGraficas.enviar_error_graficas("No elegido");
            }else{
                try {
                    this.fecha_ini=trasnformacion.fecha_milisegundos(fechaini_strin+" 00:00:00","dd-MM-yyyy hh:mm:ss");
                    this.fecha_fin=trasnformacion.fecha_milisegundos(fechafin_string+" 00:00:00","dd-MM-yyyy hh:mm:ss");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.e("fechas","fecha ini:"+fecha_ini);
                Log.e("fechas","fecha fin:"+fecha_fin);

                if (!(fecha_fin>=fecha_ini)){
                    onlistenerGraficas.enviar_error_fecha("Rango de fechas no permitido");
                }else{
                    if (dispositivo.equals("Wifi")&&grafica.equals("Barras")){
                        Recuperar_datos_wifi();
                        generar_grafica_wifi_barras(List_Wifi_grafi);
                    }
                    if (dispositivo.equals("Wifi")&&grafica.equals("Lineas")){
                        Recuperar_datos_wifi();
                        generar_grafica_wifi_Lineas(List_Wifi_grafi);
                    }
                    if (dispositivo.equals("Bluetooth")&&grafica.equals("Barras")){
                       Recuperar_datos_bluetooth();
                       generar_grafica_bluetooth_barras(List_blue_grafi);
                    }
                    if (dispositivo.equals("Bluetooth")&&grafica.equals("Lineas")){
                        Recuperar_datos_bluetooth();
                        generar_grafica_bluetooth_Lineas(List_blue_grafi);
                    }
                }
            }
        }
    }

    private void generar_grafica_wifi_Lineas(ArrayList<Objeto_wifi_grafica> list_wifi_grafi) {
        ArrayList<LegendEntry> etiquetas = new ArrayList<LegendEntry>();
        ArrayList<ILineDataSet>dataSets=new ArrayList<>();
        int o=0;
        for (Objeto_wifi_grafica datos_gra:list_wifi_grafi) {
            Log.e("veces que","veces que entro"+o);
            ArrayList<Entry> Lineas=new ArrayList<>();
            int f=1;
            for (int x=0;x<datos_gra.lista_Rssi.size();x++){
                Lineas.add(new Entry(f,datos_gra.lista_Rssi.get(x)));
                f++;
            }
            LegendEntry entry=new LegendEntry();
            entry.formColor=ColorTemplate.COLORFUL_COLORS[o];
            entry.label=(datos_gra.getMacaddres());
            etiquetas.add(entry);
            LineDataSet set=new LineDataSet(Lineas,datos_gra.getMacaddres());
            set.setColor(ColorTemplate.COLORFUL_COLORS[o]);
            set.setFillAlpha(110);
            dataSets.add(set);
            o++;
        }
        LineData data=new LineData(dataSets);
        Log.e("tamaño del set",":"+dataSets.size());
        onlistenerGraficas.retornarr_grafica_lineal(data,etiquetas);
    }

    private void generar_grafica_bluetooth_Lineas(ArrayList<Objeto_bluetoo_grafica> list_blue_grafi) {
        ArrayList<LegendEntry> etiquetas = new ArrayList<LegendEntry>();
        ArrayList<ILineDataSet>dataSets=new ArrayList<>();
        int o=0;
        for (Objeto_bluetoo_grafica datos_gra:list_blue_grafi) {
            ArrayList<Entry> Lineas=new ArrayList<>();
            int f=1;
            for (int x=0;x<datos_gra.lista_Rssi.size();x++){
                Lineas.add(new Entry(f,Float.parseFloat(datos_gra.lista_Rssi.get(x))));
                f++;
            }
            LegendEntry entry=new LegendEntry();
            entry.formColor=ColorTemplate.COLORFUL_COLORS[o];
            entry.label=(datos_gra.getBluetoothAddress());
            etiquetas.add(entry);
            LineDataSet set=new LineDataSet(Lineas,datos_gra.getBluetoothAddress());
            set.setColor(ColorTemplate.COLORFUL_COLORS[o]);
            set.setFillAlpha(110);
            dataSets.add(set);
            o++;
        }
        LineData data=new LineData(dataSets);
        Log.e("tamaño del set",":"+dataSets.size());
        onlistenerGraficas.retornarr_grafica_lineal(data,etiquetas);
    }

    @Override
    public void Recuperar_datos_bluetooth() {
        boolean bandera=false;
        ArrayList<Obj_bluetooth> lista_blueList=new ArrayList();
        bluetooth.sqLite=new Sqlite_DB_manejo(Obj_Context.getContext());
        String sql="SELECT * FROM Entidad_Bluetooth where Fecha>="+fecha_ini+" and Fecha<="+fecha_fin;
        lista_blueList=bluetooth.sqLite.Recuperar_lista_bluetooth(sql);
        List_blue_grafi=new ArrayList();
        if (lista_blueList!=null){
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
        }else
            onlistenerGraficas.enviar_error_logico_fechas("Sin datos en rangos de fechas");
    }

    @Override
    public void Recuperar_datos_wifi() {
        boolean bandera=false;
        ArrayList<Obj_wifi> lista_wiif=new ArrayList();//se crea una lista de objetos wifi
        wifi.sqLite=new Sqlite_DB_manejo(Obj_Context.getContext());//se genera consulta
        String sql="SELECT * FROM Entidad_wifi where Fecha>="+fecha_ini+" and Fecha<="+fecha_fin;
        Log.e("tirulo",sql);
        lista_wiif=wifi.sqLite.Recuperar_lista_wifi(sql);//recupera datos
        List_Wifi_grafi=new ArrayList();//se inicia la lista de wifi pero para graficos
         if (lista_wiif!=null){
             for (Obj_wifi objetos:lista_wiif) {
                 bandera=false;
                 for (int x=0;x<List_Wifi_grafi.size();x++){//recorre toda la lista pero de la
                     //de objetos de la lista de graficos
                     if (List_Wifi_grafi.get(x).getMacaddres().equals(objetos.getMacaddres())){
                         //en caso de que tenga el mismo identificador solo agrega el RSSi
                         List_Wifi_grafi.get(x).lista_Rssi.add(Integer.parseInt(objetos.getRSSI()));
                         bandera=true;
                     }
                 }
                 if(bandera==false){
                     Objeto_wifi_grafica nueva_grafi=new Objeto_wifi_grafica();//en caso de que ya
                     //existan algun dato en la lista de graficos, pero no exista el dato recopilado
                     //se crea un objeto de grafico wifi y se carga de informacion y se agrega
                     nueva_grafi.setId_db(objetos.getId_dip());
                     nueva_grafi.setMacaddres(objetos.getMacaddres());
                     nueva_grafi.lista_Rssi.add(Integer.parseInt(objetos.getRSSI()));
                     List_Wifi_grafi.add(nueva_grafi);//se agrega el nuevo objeto
                 }
             }
         }else
             onlistenerGraficas.enviar_error_logico_fechas("Sin datos en rangos");

    }

    private void generar_grafica_wifi_barras(ArrayList<Objeto_wifi_grafica> list_blue_grafi) {
        ArrayList<BarEntry> barras=new ArrayList<>();
        ArrayList<LegendEntry> etiquetas = new ArrayList<LegendEntry>();
        float i= (float) 0.1;
        int o=0;
        for (Objeto_wifi_grafica datos_gra:list_blue_grafi) {
            barras.add(new BarEntry(i,datos_gra.lista_Rssi.size()));
            LegendEntry entry=new LegendEntry();
            entry.formColor=ColorTemplate.COLORFUL_COLORS[o];
            entry.label=(datos_gra.getMacaddres());
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

    private void generar_grafica_bluetooth_barras(ArrayList<Objeto_bluetoo_grafica> list_blue_grafi) {
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
