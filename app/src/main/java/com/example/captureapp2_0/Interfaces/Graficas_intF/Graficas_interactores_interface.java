package com.example.captureapp2_0.Interfaces.Graficas_intF;

import com.example.captureapp2_0.Presentadores.Graficas_presentador_impL;

public interface Graficas_interactores_interface {
    void validacion_fechas(String fecha_ini,String fecha_fin,String dispositivo, String grafica);
    void Recuperar_datos_bluetooth();
    void Recuperar_datos_wifi();
    void validacion_campos();
}
