package com.example.captureapp2_0.consultas_volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obj_json_wifi_bluetooth;
import com.example.captureapp2_0.objetos.Obj_wifi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Registro_wifi_volley {
    public Obj_wifi obj_wifi;
    private RequestQueue request;
    private Context context;
    private JSONObject jsonBody;

    public Registro_wifi_volley() {
        this.context = Obj_Context.getContext();
        if (context!=null)
            request= Volley.newRequestQueue(context);
    }

    public void envio_wifi() throws JSONException {
        final boolean[] bandera = new boolean[1];
        transfor_json();
        String URL = "http://pruebas-upemor.ddns.net:1026/v2/entities";/*
        Log.e("valor del json", String.valueOf(jsonBody));*/
        JsonObjectRequest jobReq = new JsonObjectRequest(Request.Method.POST, URL, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.e("el valor","el valor del jason");
                        Log.e("json",":"+jsonObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }

                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        request.add(jobReq);
    }

    private void transfor_json() throws JSONException {
        Obj_json_wifi_bluetooth jsonWifiBluetooth=new Obj_json_wifi_bluetooth();
        Log.e("id",""+obj_wifi.getNombre_dispos());
        obj_wifi.setId_user("68");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        String hora = hourFormat.format(date);
        obj_wifi.setFecha_cap(fecha);
        obj_wifi.setHora(hora);
        obj_wifi.setId_tip_dispo("1");
        jsonWifiBluetooth.setObj_wifi(obj_wifi);
        jsonWifiBluetooth.cargar_json();

        jsonBody = new JSONObject(jsonWifiBluetooth.getJson());
    }


    public Obj_wifi getObj_wifi() {
        return obj_wifi;
    }

    public void setObj_wifi(Obj_wifi obj_wifi) {
        this.obj_wifi = obj_wifi;
    }

}
