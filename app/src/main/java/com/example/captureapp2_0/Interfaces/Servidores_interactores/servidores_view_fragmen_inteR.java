package com.example.captureapp2_0.Interfaces.Servidores_interactores;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;

public interface servidores_view_fragmen_inteR {
    void cargarLista_vista(ArrayList<Obje_servi> servis);
    void mensaje_sin_servido(String mensaje);
    void agregar_servi();
    void eliminar_servi(int dato,Obje_servi obje_servi);
    void Editar_servi(int dato,Obje_servi obje_servi);
    void Servidor_por_defecto(int dato,Obje_servi obje_servi);

}
