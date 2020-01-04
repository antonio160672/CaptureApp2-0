package com.example.captureapp2_0.Interfaces.Graficas_intF;

import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

public interface onlistener_graficas {
    void retornar_grafica(BarData data, ArrayList<LegendEntry> etiquetas);
    void retornarr_grafica_lineal(LineData data, ArrayList<LegendEntry> etiquetas);
    void cambiar_ventana();
    void enviar_error_fecha(String mensaje);
    void enviar_error_logico_fechas(String mensaje);
    void enviar_error_dispositivo(String mensaje);
    void enviar_error_graficas(String mensaje);
}
