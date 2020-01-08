package com.example.captureapp2_0.Interfaces.Registro_par2_interF;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public interface interRegistro_par2_Interactor {
    void validar_registrop2_intera(Obj_usuario obj_usuario, String fecha, String estado,
                                   String municipio, String calle, String colonia, String CP,
                                   oninter_Registro_par2_Finishlicener listener);
}
