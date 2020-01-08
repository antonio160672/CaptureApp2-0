package com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Interfaces.Registro_par2_interF.oninter_Registro_par2_Finishlicener;
import com.example.captureapp2_0.Modelo.Modelo.funciones_generas.fechas_trasnformacion;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Registro_user {
    private RequestQueue request;
    private Context context;
    private Obj_usuario obj_usuario;
    private oninter_Registro_par2_Finishlicener listener;
    private long fecha;
    private fechas_trasnformacion trasnformacion;

    public Registro_user(Obj_usuario obj_usuario, oninter_Registro_par2_Finishlicener listener) {
        this.context = Obj_Context.getContext();
        this.obj_usuario = obj_usuario;
        this.listener=listener;
        trasnformacion=new fechas_trasnformacion();
        try {
            fecha=trasnformacion.fecha_milisegundos(obj_usuario.getFecha_nac()+" 00:00:00","dd-MM-yyyy hh:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void Registro_usuario(){
        request= Volley.newRequestQueue(context);//se instancia un nuevo reques con el contexto de la aplicación
        final boolean[] bandera = new boolean[1];
        //String URL = "http://192.168.1.75/android/insert.php";
        String URL = "http://pruebas-upemor.ddns.net/android/insert.php";//se crea una url
        StringRequest getRequest = new StringRequest(Request.Method.POST, URL,//se indica el metodo
                //de comunicación tipo post, la url y la información
                new Response.Listener<String>() {
                    @Override//aqui se recibe una la respuesta
                    public void onResponse(String response) {
                        request.getCache().clear();//se limpia la respuesta
                        response= response.replace("Connected successfully","");
                        Log.e("datos responce","formato: "+response);
                        try {
                            parseData(response);//transforma un json para ser analizado
                            if(Registro_sql()){//manda a llmar la funcion para el registro sqlite
                                listener.exito_valida();//manda a llamar el metodo del presentador
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
                            bandera[0]=false;
                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() {//metodo para envío de datos
                //mapea las instrucciones
                Map<String, String> params = new HashMap<String, String>();
                params.put("Nombre",obj_usuario.getNombre());
                params.put("Apell_pater",obj_usuario.getApellido_pater());
                params.put("Apell_mater",obj_usuario.getApellido_mater());
                params.put("Correo",obj_usuario.getCorreo());
                params.put("Contra",obj_usuario.getContrasena());
                params.put("calle",obj_usuario.getCalle());
                params.put("colonia",obj_usuario.getColonia());
                params.put("cp",obj_usuario.getCp());
                params.put("idEstado",obj_usuario.getIdEstado());
                return params;
            }

        };
        request.add(getRequest);//agrega a una pila para que sea enviada
    }

    private void parseData(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response); ;
        if (jsonObject.optString("status").equals("true")){
            saveInfo(response);
        }else {
            Log.e("contenido erro",""+jsonObject.getString("message"));
        }
    }

    private void saveInfo(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    obj_usuario.setId_usua(dataobj.getString("idUsuario"));
                    salvar_preferencias();
                    Log.e("titlo",dataobj.getString("nombre"));
                    Log.e("titlo2",dataobj.getString("correo"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void salvar_preferencias() {
        SharedPreferences preferences=context.getSharedPreferences
                ("userid",context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Id_User",obj_usuario.getId_usua());
        editor.commit();
    }

    private boolean Registro_sql() {
        obj_usuario.sqLite= new Sqlite_DB_manejo(context);
        if (Addcontact()){
            return true;
        }else {
            return false;
        }
    }

    private boolean Addcontact(){
        String sql="insert into usuario values('"+obj_usuario.getId_usua()+"','"+obj_usuario.getNombre()+"','"+
                obj_usuario.getApellido_pater()+"','"+obj_usuario.getApellido_mater()+"','"+
                obj_usuario.getCorreo()+"','"+ obj_usuario.getContrasena()+"','"+obj_usuario.getMunicipio()
                +"','"+obj_usuario.getCalle()+"','"+obj_usuario.getColonia()+"','"+
                fecha+"','"+obj_usuario.getCp()+"','"+obj_usuario.getIdEstado()+"');";
        Log.e("cadena","contacto"+sql);
        return obj_usuario.sqLite.ejecutaSQL(sql);
    }
}
