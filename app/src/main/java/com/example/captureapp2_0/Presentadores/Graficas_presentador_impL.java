package com.example.captureapp2_0.Presentadores;

import com.example.captureapp2_0.Interactores.Graficas_interactores_impL;
import com.example.captureapp2_0.Interfaces.Graficas_intF.Graficas_interactores_interface;
import com.example.captureapp2_0.Interfaces.Graficas_intF.Graficas_presentador_inter;
import com.example.captureapp2_0.Interfaces.Graficas_intF.onlistener_graficas;
import com.example.captureapp2_0.Vistas.Graficas.Vista_graficas_Fragment;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

public class Graficas_presentador_impL implements Graficas_presentador_inter, onlistener_graficas {
    private Vista_graficas_Fragment vista_grafi;
    private Graficas_interactores_interface graficasInteractores;

    public Graficas_presentador_impL(Vista_graficas_Fragment vista_grafi) {
        this.vista_grafi = vista_grafi;
        graficasInteractores=new Graficas_interactores_impL(this);

    }

    @Override
    public void Validar_fechas(String fecha_ini, String fecha_fin, String dispositivo, String grafica) {
        if (vista_grafi!=null){
            graficasInteractores.validacion_fechas(fecha_ini,fecha_fin,dispositivo,grafica);
        }
    }

    @Override
    public void retornar_grafica(BarData data, ArrayList<LegendEntry> etiquetas) {
        if (vista_grafi!=null)
            vista_grafi.mostrar_grafica(data,etiquetas);
    }

    @Override
    public void retornarr_grafica_lineal(LineData data, ArrayList<LegendEntry> etiquetas) {
        if (vista_grafi!=null)
            vista_grafi.mostrar_grafica_lineal(data,etiquetas);
    }

    @Override
    public void enviar_error_fecha(String mensaje) {
        if (vista_grafi!=null){
            vista_grafi.show_error_fecha(mensaje);
        }
    }

    @Override
    public void enviar_error_logico_fechas(String mensaje) {
        if (vista_grafi!=null){
            vista_grafi.show_error_logico_fechas(mensaje);
        }
    }

    @Override
    public void enviar_error_dispositivo(String mensaje) {
        if (vista_grafi!=null){
            vista_grafi.show_error_dispositivo(mensaje);
        }
    }

    @Override
    public void enviar_error_graficas(String mensaje) {
        if (vista_grafi!=null){
            vista_grafi.show_graficas(mensaje);
        }
    }

    @Override
    public void cambiar_ventana() {

    }
}
