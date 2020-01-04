package com.example.captureapp2_0.Interfaces.Graficas_intF;

import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

public interface Graficas_vistas_Inter {
    void mostrar_grafica(BarData data, ArrayList<LegendEntry> etiquetas);
    void mostrar_grafica_lineal( LineData data,ArrayList<LegendEntry> etiquetas);
    void validar_datos();
    void cargar_campos();
    void fecha_capt(int bandera);
    void show_error_fecha(String mensaje);
    void show_error_logico_fechas(String mensaje);
    void show_error_dispositivo(String mensaje);
    void show_graficas(String mensaje);



}
