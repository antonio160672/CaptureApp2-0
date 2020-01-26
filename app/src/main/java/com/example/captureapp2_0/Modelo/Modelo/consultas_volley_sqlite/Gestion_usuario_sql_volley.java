package com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.onlistener_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Gestion_usuario_sql_volley {
    private onlistener_usuario onlistener_usuario;
    private RequestQueue request;
    private Context context;


    public Gestion_usuario_sql_volley(onlistener_usuario onlistener_usuario) {
        this.onlistener_usuario = onlistener_usuario;
        this.context = Obj_Context.getContext();
        request= Volley.newRequestQueue(context);//se instancia un nuevo reques con el contexto de la aplicación
    }
    public void consutar_contra_voller(final EditText contra, final String contrase, final String ID){
        String URL = "http://pruebas-upemor.ddns.net/android/Gestion_datos_usu.php";//se crea una url
        StringRequest getRequest = new StringRequest(Request.Method.POST, URL,//se indica el metodo
                //de comunicación tipo post, la url y la información
                new Response.Listener<String>() {
                    @Override//aqui se recibe una la respuesta
                    public void onResponse(String response) {
                        request.getCache().clear();//se limpia la respuesta
                        response= response.replace("Connected successfully","");
                        Log.e("datos responce","formato: "+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optString("status").equals("true")){
                                onlistener_usuario.ocurtar_dialog_mostrardatos();
                            }else {
                                contra.setError("Contraseña invalida");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (null != error.networkResponse) {
                            Log.e("error" + ": ", "Error Response code: " + error.networkResponse.statusCode);
                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() {//metodo para envío de datos
                //mapea las instrucciones
                Map<String, String> params = new HashMap<String, String>();
                params.put("Contra_validar",contrase);
                params.put("Id",ID);
                params.put("opcion","1");

                return params;
            }

        };
        request.add(getRequest);//agrega a una pila para que sea enviada
    }

    public void actualizar_contra(final String contra, final String ID){
        String URL = "http://pruebas-upemor.ddns.net/android/Gestion_datos_usu.php";//se crea una url
        StringRequest getRequest = new StringRequest(Request.Method.POST, URL,//se indica el metodo
                //de comunicación tipo post, la url y la información
                new Response.Listener<String>() {
                    @Override//aqui se recibe una la respuesta
                    public void onResponse(String response) {
                        request.getCache().clear();//se limpia la respuesta
                        response= response.replace("Connected successfully","");
                        Log.e("datos responce","formato: "+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optString("status").equals("true")){
                                onlistener_usuario.enviar_correcta_actuali("Correcta actualización");
                            }else {
                                onlistener_usuario.enviar_correcta_actuali("error al actualizar");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (null != error.networkResponse) {
                            Log.e("error" + ": ", "Error Response code: " + error.networkResponse.statusCode);
                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() {//metodo para envío de datos
                //mapea las instrucciones
                Map<String, String> params = new HashMap<String, String>();
                params.put("Contra_validar",contra);
                params.put("Id",ID);
                params.put("opcion","2");
                return params;
            }

        };
        request.add(getRequest);//agrega a una pila para que sea enviada

    }
}