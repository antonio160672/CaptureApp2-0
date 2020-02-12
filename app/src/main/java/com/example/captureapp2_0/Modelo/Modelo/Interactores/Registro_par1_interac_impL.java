package com.example.captureapp2_0.Modelo.Modelo.Interactores;

import android.os.Handler;
import android.util.Log;
import android.util.Patterns;

import com.android.volley.Response;
import com.example.captureapp2_0.Interfaces.Registro_par1_interf.interRegistro_par1_Interactor;
import com.example.captureapp2_0.Interfaces.Registro_par1_interf.oninter_Registro_par1_Finishlicener;
import com.example.captureapp2_0.Interfaces.VolleyListener;
import com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite.Registro_user;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.funciones_generas.Validaciones_campos;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro_par1_interac_impL implements interRegistro_par1_Interactor, VolleyListener {
    private int bandera,Exist_cor;
    private Obj_usuario obj_usuario;
    private oninter_Registro_par1_Finishlicener listener;
    boolean valor;
    @Override//este metodo valida que tengan datos los campos
    public void validarRegistropar1(final String Nombre, final String Apellido_parte, final String Apellido_mater,
                                    final String Correo, final String Contra, final String Conf_contra,
                                    final oninter_Registro_par1_Finishlicener listener) {
        this.listener=listener;
        bandera=1;
        volley(Correo);
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
                        validar_cadenas(Nombre.trim(),Apellido_parte.trim()
                                ,Apellido_mater.trim(),Correo.trim(),Contra.trim(),Conf_contra.trim());

                }
                listener.dismi_progress("");

            }
        },1500);
    }

    @Override
    public void requestFinished(boolean exsitance) {

        Log.e("valo","formato: "+exsitance);
        if (exsitance)
            Exist_cor=2;
        else
            Exist_cor=1;
    }

    private void volley(final String Correo) {
        Exist_cor=2;
        final VolleyListener volleyListener = (VolleyListener) this;
        Registro_user registro_user=new Registro_user(null,null);
        valor=registro_user.validar_correo(Correo,
                new Response.Listener<String>() {
                    @Override//aqui se recibe una la respuesta
                    public void onResponse(String response) {
                        response= response.replace("Connected successfully","");
                        Log.e("datos responce","formato: "+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optString("status").equals("true")){
                                volleyListener.requestFinished(true);
                            }else {
                                volleyListener.requestFinished(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    //aqui se validan que se cuente con los datos correctos
    //que no haya numeros en campos que solo reciben string
    private void validar_cadenas(final String Nombre, final String Apellido_parte, final String Apellido_mater,
                         final String Correo, final String Contra, final String Conf_contra){
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
        Log.e("tamaño",":"+Contra.length());
        if (!((Contra.length()>=8&&Contra.length()<=40)&&(Conf_contra.length()>=8&&Conf_contra.length()<=40))){
            listener.Contra_seterro("Tamaño minimo 8 caracteres");
            bandera=0;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Correo).matches()){
            listener.Correo_seterro("Correo invalido");
            bandera=0;
        }
        if (Exist_cor==2){
            listener.Correo_seterro("El correo ya esta registrado");
            bandera=0;
        }
        listener.dismi_progress("");
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
