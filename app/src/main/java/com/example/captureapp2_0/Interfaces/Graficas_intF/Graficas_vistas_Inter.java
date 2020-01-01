package com.example.captureapp2_0.Interfaces.Graficas_intF;

import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;

import java.util.ArrayList;

public interface Graficas_vistas_Inter {
    void mostrar_grafica(BarData data, ArrayList<LegendEntry> etiquetas);
    void validar_fechas();
    void cambio_ventana();
}
