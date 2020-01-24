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
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_json_wifi_bluetooth;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Registro_bluetooth_volley {
    Obj_bluetooth obj_bluetooth;
    private RequestQueue request;
    private Context context;
    private JSONObject jsonBody;
    private String URL, sql;
    private int Request_Method,tipo_json;//tipo de json para actualizar o registrar
    private fechas_trasnformacion trasnformacion;

    public Registro_bluetooth_volley() {
        this.context = Obj_Context.getContext();
        if (context!=null)
            request= Volley.newRequestQueue(context);
    }
    public void SQLite_exitencia_registro(String direccion,String puer_crate,String puerto_Orion) throws JSONException {
        if (obj_bluetooth!=null){
            obj_bluetooth.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
            obj_bluetooth.cursor=consulta_entidad();
            if(obj_bluetooth.cursor.getCount()>0){
                URL= "http://"+direccion+":"+puerto_Orion+"/v2/entities/"+obj_bluetooth.getId_dip()+"/attrs";
                Log.e("URL de actualizacion",":"+URL);
                Request_Method=1;//metodo pach para actualizar
                tipo_json=1;
            }else
            {
                URL= "http://"+direccion+":"+puerto_Orion+"/v2/entities/";
                Request_Method=1;//metodo post para registrar
                tipo_json=0;
            }
            if(insertar_entidad_Bluetooth())
                envio_bluetooth();
        }
    }

    private Cursor consulta_entidad()//en este método se consulta si existe el dato con el id Id_dipositivo
    {
        sql="select * from Entidad_Bluetooth where Id_dip='"+obj_bluetooth.getId_dip()+"'";
        Log.e("aqui",""+sql);
        return obj_bluetooth.sqLite.consultaSQL(sql);
    }

    private boolean insertar_entidad_Bluetooth()// insertara los datos en la tabla
    {
        trasnformacion=new fechas_trasnformacion();
        try {
            obj_bluetooth.setFecha_mili(trasnformacion.fecha_milisegundos((obj_bluetooth.getFecha_cap()+" "+obj_bluetooth.getHora()),"yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sql="insert into Entidad_Bluetooth values(NULL,'"+obj_bluetooth.getId_dip()+"','"+obj_bluetooth.getUUID()+"','"+
                obj_bluetooth.getBluetoothAddress()+"','"+obj_bluetooth.getRSSI()+"','"+obj_bluetooth.getTX()+"','"+
                obj_bluetooth.getMAJOR()+"','"+ obj_bluetooth.getFecha_mili()+"','"+obj_bluetooth.getHora()+
                "','"+obj_bluetooth.getId_user()+"','"+obj_bluetooth.getId_tip_dispo()+"');";
        Log.e("cadena","contacto"+sql);
        return obj_bluetooth.sqLite.ejecutaSQL(sql);
    }

    public boolean Insertar_sin_servidor_Sin_conexion_entidad_Bluetooth()// insertara los datos en la tabla
            //de señales bluetooth pero sin conexion a sservidor
    {
        obj_bluetooth.sqLite= new Sqlite_DB_manejo(Obj_Context.getContext());
        String sql="insert into Sin_conexion_entidad_Bluetooth values(NULL,'"+obj_bluetooth.getId_dip()+
                "','"+obj_bluetooth.getUUID()+"','"+
                obj_bluetooth.getBluetoothAddress()+"','"+obj_bluetooth.getRSSI()+"','"+obj_bluetooth.getTX()+"','"+
                obj_bluetooth.getMAJOR()+"','"+ obj_bluetooth.getFecha_cap()+"','"+obj_bluetooth.getHora()+
                "','"+obj_bluetooth.getId_user()+"','"+obj_bluetooth.getId_tip_dispo()+"');";
        Log.e("cadena","contacto"+sql);
        return obj_bluetooth.sqLite.ejecutaSQL(sql);
    }

    public void envio_bluetooth() throws JSONException{

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
        jsonWifiBluetooth.setObj_bluetooth(obj_bluetooth);
        if (i==0){
            jsonWifiBluetooth.primer_registro_json_bluethoo();
        }else{
            jsonWifiBluetooth.actualizacion_entidad_bluetooth();
        }
        jsonBody = new JSONObject(jsonWifiBluetooth.getJson_bluetooth());
    }

    public void setObj_bluetooth(Obj_bluetooth obj_bluetooth) {
        this.obj_bluetooth = obj_bluetooth;
    }
}
