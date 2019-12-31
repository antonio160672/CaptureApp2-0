package com.example.captureapp2_0.Interactores;

import android.os.Handler;
import android.util.Patterns;

import com.example.captureapp2_0.Interfaces.Registro_par1_interf.interRegistro_par1_Interactor;
import com.example.captureapp2_0.Interfaces.Registro_par1_interf.oninter_Registro_par1_Finishlicener;
import com.example.captureapp2_0.objetos.Obj_usuario;
import com.example.captureapp2_0.funciones_generas.Validaciones_campos;

public class Registro_par1_interac_impL implements interRegistro_par1_Interactor {
    private int bandera;
    private Obj_usuario obj_usuario;
    private oninter_Registro_par1_Finishlicener listener;
    @Override//este metodo valida que tengan datos los campos
    public void validarRegistropar1(final String Nombre, final String Apellido_parte, final String Apellido_mater,
                                    final String Correo, final String Contra, final String Conf_contra,
                                    final oninter_Registro_par1_Finishlicener listener) {
        this.listener=listener;
        bandera=1;
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                if (Nombre.isEmpty()&&Apellido_parte.isEmpty()&&Apellido_mater.isEmpty()&&
                        Correo.isEmpty()&&Contra.isEmpty()&&Conf_contra.isEmpty()){
                        listener.Nombr_seterro("campo vacio");
                        listener.Apellido_pa_seterro("campo vacio");
                        listener.Apellido_mate_seterro("campo vacio");
                        listener.Correo_seterro("campo vacio");
                        listener.Contra_seterro("campo vacio");
                }else {
                    if (Nombre.equals("")) {
                        listener.Nombr_seterro("campo vacio");
                        bandera=0;
                    }
                    if (Apellido_parte.equals("")) {
                        listener.Apellido_pa_seterro("campo vacio");
                        bandera=0;
                    }
                    if (Apellido_mater.equals("")) {
                        listener.Apellido_mate_seterro("campo vacio");
                        bandera=0;
                    }
                    if (Correo.equals("")) {
                        listener.Correo_seterro("campo vacio");
                        bandera=0;
                    }
                    if (Contra.equals("")) {
                        listener.Correo_seterro("campo vacio");
                        bandera=0;
                    }
                    if (Conf_contra.equals("")) {
                        listener.Correo_seterro("campo vacio");
                        bandera=0;
                    }
                    if (bandera!=0){
                        validar_cadenas(Nombre,Apellido_parte,Apellido_mater,Correo,Contra,Conf_contra);
                    }

                }

            }
        },100);
    }

    //aqui se validan que se cuente con los datos correctos
    //que no haya numeros en campos que solo reciben string
    private void validar_cadenas(final String Nombre, final String Apellido_parte, final String Apellido_mater,
                         final String Correo, final String Contra, final String Conf_contra){
        bandera=1;
        Validaciones_campos validaciones_campos=new Validaciones_campos();
        String error;
        error=validaciones_campos.Val_Nombres(Nombre);
        if (error!=null){
            listener.Nombr_seterro(error);
            bandera=0;
        }
        error=validaciones_campos.Val_Nombres(Apellido_parte);
        if (error!=null){
            listener.Apellido_pa_seterro(error);
            bandera=0;
        }
        error=validaciones_campos.Val_Nombres(Apellido_mater);
        if (error!=null){
            listener.Apellido_mate_seterro(error);
            bandera=0;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Correo).matches()){
            listener.Correo_seterro("Correo invalido");
            bandera=0;
        }
        if (!Contra.equals(Conf_contra)){
            listener.Contra_seterro("contraseñas diferentes");
            bandera=0;
        }
        if (bandera==1){
            //se debe mandar a llamar a volley para corroborar la inexistencia del correo
            //pero de momento se llamara al objeto de clase usuario.
            cargar_obejto_usuario(Nombre,Apellido_parte,Apellido_mater,Correo,Contra,Conf_contra,listener);

        }
    }
    //aqui se carga un objeto de tipo usuario con todos los datos del usurio
    //que se va a registrar
    private void cargar_obejto_usuario(final String Nombre, final String Apellido_parte, final String Apellido_mater,
                                       final String Correo, final String Contra, final String Conf_contra,
                                       final oninter_Registro_par1_Finishlicener listener){
        obj_usuario=new Obj_usuario();
        obj_usuario.setNombre(Nombre);
        obj_usuario.setApellido_pater(Apellido_parte);
        obj_usuario.setApellido_mater(Apellido_mater);
        obj_usuario.setCorreo(Correo);
        obj_usuario.setContrasena(Contra);
        listener.exito_valida(obj_usuario);

    }
}
