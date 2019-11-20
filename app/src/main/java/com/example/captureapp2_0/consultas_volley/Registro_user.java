package com.example.captureapp2_0.consultas_volley;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.captureapp2_0.DB_lite.Sqlite_usuario;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_usuario;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro_user {
    private String fecha, hora;
    private RequestQueue request;
    private Context context;
    private Obj_usuario obj_usuario;

    public Registro_user(Obj_usuario obj_usuario) {
        this.context = Obj_Context.getContext();
        this.obj_usuario = obj_usuario;
    }

    public void Registro_usuario(){
        request= Volley.newRequestQueue(context);
        String mesnaje;
        String URL = "http://pruebas-upemor.ddns.net/android/insertar.php";
        StringRequest getRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        request.getCache().clear();
                        response= response.replace("Connected successfully","");
                        Log.e("datos responce","formato: "+response);
                        try {
                            parseData(response);
                            Registro_sql();
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
            protected Map<String, String> getParams() {
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
        request.add(getRequest);

    }

    private void Registro_sql() {
        obj_usuario.sqLite= new Sqlite_usuario(context);
        if (Addcontact()){
            Log.e("ahora si","ya es tuyo papi");
        }
    }

    private boolean Addcontact(){
        String sql="insert into usuario values('"+obj_usuario.getId_usua()+"','"+obj_usuario.getNombre()+"','"+
                        obj_usuario.getApellido_pater()+"','"+obj_usuario.getApellido_mater()+"','"+
                        obj_usuario.getCorreo()+"','"+ obj_usuario.getCalle()+"','"+obj_usuario.getColonia()+
                        "','"+obj_usuario.getContrasena()+"','"+
                        obj_usuario.getCp()+"','"+obj_usuario.getIdEstado()+"','"+obj_usuario.getFecha_nac()+"');";
        Log.e("cadena","contacto"+sql);
        return obj_usuario.sqLite.ejecutaSQL(sql);
    }
    private void parseData(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response); ;
        if (jsonObject.optString("status").equals("true")){
            saveInfo(response);
        }else {
            Log.e("ya teeee chingas",""+jsonObject.getString("message"));
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
                    Log.e("titlo",dataobj.getString("nombre"));
                    Log.e("titlo2",dataobj.getString("correo"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
