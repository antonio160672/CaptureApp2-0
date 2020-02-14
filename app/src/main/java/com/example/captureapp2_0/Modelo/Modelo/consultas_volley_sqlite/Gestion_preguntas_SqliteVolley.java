package com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.captureapp2_0.Interfaces.Menu_princi_inter.oninter_inicioFragment_Finishlicener;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Gestion_preguntas_SqliteVolley {
    private RequestQueue request;
    private Context context;
    private Objeto_preguntas preguntas;
    private String id;
    private oninter_inicioFragment_Finishlicener listener;

    public Gestion_preguntas_SqliteVolley(Objeto_preguntas preguntas, oninter_inicioFragment_Finishlicener listener, String ID) {
        this.preguntas = preguntas;
        this.context = Obj_Context.getContext();
        this.listener=listener;
        this.id=ID;
    }

    public void actualizar_preguntas(){
        request= Volley.newRequestQueue(context);//se instancia un nuevo reques con el contexto de la aplicación
        String URL ="http://207.249.127.94/android/Actualizar_preguntas.php";
        // String URL = "http://pruebas-upemor.ddns.net/android/Actualizar_preguntas.php";//se crea una url
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
                                if(agregar_preguntas()){//manda a llmar la funcion para el registro sqlite
                                    listener.mensaje("Se registraron las preguntas");
                                }
                            }else {
                                Log.e("error","en el servidor");
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
                params.put("pregunta1",Integer.toString(preguntas.getPregunta1()));
                params.put("pregunta2",Integer.toString(preguntas.getPregunta2()));
                params.put("respuesta1",preguntas.getSrespuesta1());
                params.put("respuesta2",preguntas.getSrespuesta2());
                params.put("ID",id);
                return params;
            }

        };
        request.add(getRequest);//agrega a una pila para que sea enviada
    }

    private boolean agregar_preguntas(){
        String sql="insert into preguntas_recu values(null,'"+id+"','"+
                preguntas.getPregunta1()+"','"+preguntas.getSrespuesta1()+"','"+
                preguntas.getPregunta2()+"','"+ preguntas.getSrespuesta2()+"');";
        Log.e("cadena","contacto"+sql);
        return preguntas.sqLite.ejecutaSQL(sql);
    }

}
