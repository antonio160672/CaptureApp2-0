package com.example.captureapp2_0.Interfaces.Menu_princi_inter;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Estados;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

public interface inter_inicioFragmen_interactor {
    void cargar_datos(Obj_usuario obj_usuario, Obj_Estados obj_estados,
                         oninter_inicioFragment_Finishlicener listener);
    void ejecutar_consulta(Obj_usuario obj_usuario, Obj_Estados obj_estados,
                           oninter_inicioFragment_Finishlicener listener);
    void retornardatos(Obj_usuario obj_usuario, Obj_Estados obj_estados,
                       oninter_inicioFragment_Finishlicener listener);
    void consultar_preguntas_recu();
    void registrar_actualizar_pregun(Objeto_preguntas preguntas);

}
