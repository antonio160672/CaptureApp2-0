package com.example.captureapp2_0.Interfaces.Servidores_interactores;

import com.example.captureapp2_0.objetos.Obje_servi;

public interface servidores_presentador_frag_inteR {
    void registrar_servidor(Obje_servi obje_servi);
    void consultar_servi(Obje_servi obje_servi);
    void eliminar_dispo(int Id,Obje_servi obje_servi);
    void actualizar_dispo(int dato,Obje_servi obje_servi);
}