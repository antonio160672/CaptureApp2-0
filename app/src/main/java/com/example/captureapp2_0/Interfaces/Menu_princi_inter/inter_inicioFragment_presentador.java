package com.example.captureapp2_0.Interfaces.Menu_princi_inter;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

public interface inter_inicioFragment_presentador {
    void Llamar_consultaSQLite();
    void validar_preguntas();
    void registrar_actualizar_pregun(Objeto_preguntas preguntas);
}
