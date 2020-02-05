package com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite;

import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.onIni_sesion_Finishlicener;
import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inicio_sesion_volley {
    onIni_sesion_Finishlicener listener;
    private RequestQueue request;
    private Obj_usuario objUsuario;
    private Objeto_preguntas preguntas;

    public Inicio_sesion_volley(onIni_sesion_Finishlicener listener) {
        this.listener = listener;
    }

    public void validar_pregunta(final Objeto_preguntas preguntas, final String correo){
        request= Volley.newRequestQueue(Obj_Context.getContext());//se instancia un nuevo reques con el contexto de la aplicación
        //String URL = "http://192.168.1.75/android/insert.php";
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
                                    listener.preguntas_correctas(correo);
                            }else {
                                listener.preguntas_invali("Correo o preguntas incorrectas");
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
                params.put("Correo",correo);
                params.put("opcion","5");
                return params;
            }

        };
        request.add(getRequest);//agrega a una pila para que sea enviada
    }

    public void actualizar_contra(final String correo,final String contra){
        request= Volley.newRequestQueue(Obj_Context.getContext());//se instancia un nuevo reques con el contexto de la aplicación
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
                                listener.preguntas_invali("Contraseña actualizada");
                            }else {
                                listener.preguntas_invali("Error en el servidor");
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
                params.put("Correo",correo);
                params.put("opcion","6");
                return params;
            }

        };
        request.add(getRequest);//agrega a una pila para que sea enviada
    }

    public void validar_contra_correo(final String correo,final String contra){
        request= Volley.newRequestQueue(Obj_Context.getContext());//se instancia un nuevo reques con el contexto de la aplicación
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
                            if (jsonObject.optString("status").equals("correo")){
                                listener.correo_seterro("No existe el correo");
                            }else if(jsonObject.optString("status").equals("contra")){
                                listener.correo_seterro("Correo o contraseña invalida");
                                listener.contra_seterro("Correo o contraseña invalida");
                            }else if(jsonObject.optString("status").equals("true")){
                                try {
                                    parseData(response);//transforma un json para ser analizado
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
                params.put("Correo",correo);
                params.put("opcion","7");
                return params;
            }

        };
        request.add(getRequest);//agrega a una pila para que sea enviada
    }

    private void parseData(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        objUsuario=new Obj_usuario();
        preguntas=new Objeto_preguntas();
        try {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject dataobj = dataArray.getJSONObject(i);
                objUsuario.setId_usua(dataobj.getString("idUsuario"));
                objUsuario.setNombre(dataobj.getString("nombre"));
                objUsuario.setApellido_pater(dataobj.getString("apelldioP"));
                objUsuario.setApellido_mater(dataobj.getString("apellidoM"));
                objUsuario.setCorreo(dataobj.getString("correo"));
                objUsuario.setContrasena(dataobj.getString("contrasena"));
                objUsuario.setCalle(dataobj.getString("calle"));
                objUsuario.setColonia(dataobj.getString("colonia"));
                objUsuario.setCp(dataobj.getString("cp"));
                objUsuario.setCalle(dataobj.getString("calle"));
                objUsuario.setCalle(dataobj.getString("calle"));
                objUsuario.setIdEstado(dataobj.getString("idEstado"));
                preguntas.setPregunta1(Integer.parseInt(dataobj.getString("pregunta1")));
                preguntas.setPregunta2(Integer.parseInt(dataobj.getString("pregunta2")));
                preguntas.setSrespuesta1(dataobj.getString("respuesta1"));
                preguntas.setSrespuesta2(dataobj.getString("respuesta2"));
            }
            if (user_gesti()){
                salvar_preferencias();
                listener.exito_consul();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private boolean user_gesti(){
        String fecha="634089600";
        objUsuario.setMunicipio("Cuernavaca");
        Obj_usuario miniusu = new Obj_usuario();
        objUsuario.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
        String sql="DELETE FROM usuario";
        objUsuario.sqLite.ejecutaSQL(sql);
        sql="DELETE FROM preguntas_recu";
        objUsuario.sqLite.ejecutaSQL(sql);
        sql="insert into usuario values('"+objUsuario.getId_usua()+"','"+objUsuario.getNombre()+"','"+
                objUsuario.getApellido_pater()+"','"+objUsuario.getApellido_mater()+"','"+
                objUsuario.getCorreo()+"','"+ objUsuario.getContrasena()+"','"+objUsuario.getMunicipio()
                +"','"+objUsuario.getCalle()+"','"+objUsuario.getColonia()+"','"+
                fecha+"','"+objUsuario.getCp()+"','"+objUsuario.getIdEstado()+"');";
        Log.e("cadena","contacto"+sql);
        if (objUsuario.sqLite.ejecutaSQL(sql)){
            sql="insert into preguntas_recu values(null,'"+objUsuario.getId_usua()+"','"+
                    preguntas.getPregunta1()+"','"+preguntas.getSrespuesta1()+"','"+
                    preguntas.getPregunta2()+"','"+ preguntas.getSrespuesta2()+"');";
            if (objUsuario.sqLite.ejecutaSQL(sql)){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }


    private void salvar_preferencias() {
        SharedPreferences preferences=Obj_Context.getContext().getSharedPreferences
                ("userid",Obj_Context.getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Id_User",objUsuario.getId_usua());
        editor.commit();
    }
}
