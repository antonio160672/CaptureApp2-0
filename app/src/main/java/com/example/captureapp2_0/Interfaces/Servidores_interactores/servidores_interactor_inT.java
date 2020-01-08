package com.example.captureapp2_0.Interfaces.Servidores_interactores;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

public interface servidores_interactor_inT {
    void registrar_servidor(Obje_servi obje_servi);
    void consultar(Obje_servi obje_servi);
    void eliminar(int dato,Obje_servi obje_servi);
    void Actualizar(int dato,Obje_servi obje_servi);
    void servidor_por_defecto(int dato,Obje_servi obje_servi);
}
