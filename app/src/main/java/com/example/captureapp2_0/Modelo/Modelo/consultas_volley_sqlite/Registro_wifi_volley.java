package com.example.captureapp2_0.Modelo.Modelo.consultas_volley_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import com.example.captureapp2_0.Modelo.Modelo.funciones_generas.fechas_trasnformacion;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_json_wifi_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Registro_wifi_volley {
    public Obj_wifi obj_wifi;
    private RequestQueue request;
    private Context context;
    private JSONObject jsonBody;
    private String URL, sql;
    private int Request_Method,tipo_json;
    private fechas_trasnformacion trasnformacion;

    public Registro_wifi_volley() {
        this.context = Obj_Context.getContext();
        if (context!=null)
            request= Volley.newRequestQueue(context);
    }

    public void SQLite_exitencia() throws JSONException {
        if (obj_wifi!=null){
            obj_wifi.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
            obj_wifi.cursor=consulta_entidad();
            if(obj_wifi.cursor.getCount()>0){
                Log.e("si","existe el dato");
                URL= "http://pruebas-upemor.ddns.net:1026/v2/entities/"+obj_wifi.getId_dip()+"/attrs";
                Log.e("URL de actualizacion",":"+URL);
                Request_Method=1;//metodo pach para actualizar
                tipo_json=1;
            }else
            {
                Log.e("no","existe el dato\n");
                Log.e("pero","ahora si  va a existir\n");
                URL= "http://pruebas-upemor.ddns.net:1026/v2/entities";
                Request_Method=1;//metodo post para registrar
                tipo_json=0;
            }
            if(insertar_entidad_wifi())
                envio_wifi();
        }
    }

    private Cursor consulta_entidad()//en este método se consulta si existe el dato con el id Id_dipositivo
    {
        sql="select * from Entidad_wifi where Id_dip='"+obj_wifi.getId_dip()+"'";
        Log.e("aqui",""+sql);
        return obj_wifi.sqLite.consultaSQL(sql);
    }

    private boolean insertar_entidad_wifi()// insertara los datos en la tabla
    {
        trasnformacion=new fechas_trasnformacion();
        try {
            Long fecha=trasnformacion.fecha_milisegundos((obj_wifi.getFecha_cap()+" "+obj_wifi.getHora()),"yyyy-MM-dd");
            Log.e("fecha wifi",":  "+fecha);
            obj_wifi.setFecha_mili(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sql="insert into Entidad_wifi values(NULL,'"+obj_wifi.getId_dip()+"','"+obj_wifi.getNombre_dispos()+"','"+
                obj_wifi.getMacaddres()+"','"+obj_wifi.getRSSI()+"',"+
                obj_wifi.getFecha_mili()+",'"+ obj_wifi.getHora()+"','"+obj_wifi.getId_user()
                +"','"+obj_wifi.getId_tip_dispo()+"');";
        Log.e("cadena","contacto"+sql);
        return obj_wifi.sqLite.ejecutaSQL(sql);
    }

    public void envio_wifi() throws JSONException{
        transfor_json(tipo_json);//json para
        Log.e("valor del json", String.valueOf(jsonBody));
        JsonObjectRequest jobReq = new JsonObjectRequest(Request_Method, URL, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        request.getCache().clear();
                        Log.e("el valor","el valor del jason");
                        Log.e("json",":"+jsonObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("json",":"+volleyError);
                    }

                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                if (tipo_json==1){
                    headers.put("X-HTTP-Method-Override", "PATCH");
                }
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        request.add(jobReq);
    }

    private void transfor_json(int i) throws JSONException {
        Obj_json_wifi_bluetooth jsonWifiBluetooth=new Obj_json_wifi_bluetooth();
        jsonWifiBluetooth.setObj_wifi(obj_wifi);
        if (i==0){
            jsonWifiBluetooth.primer_registro_json_wifi();
        }else{
            jsonWifiBluetooth.actualizacion_entidad_wifi();
        }
        jsonBody = new JSONObject(jsonWifiBluetooth.getJson_wifi());
    }


    public void setObj_wifi(Obj_wifi obj_wifi) {
        this.obj_wifi = obj_wifi;
    }

}
