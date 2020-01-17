package com.example.captureapp2_0.Modelo.Modelo.DB_lite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.captureapp2_0.Modelo.Modelo.funciones_generas.fechas_trasnformacion;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;

public class Sqlite_DB_manejo {

    Context context;
    private Helper helper;
    private SQLiteDatabase bd;
    String nombre_base = "bd";// nombre de la base de datos
    int version = 3;
    String[] tablas = {"CREATE TABLE IF NOT EXISTS usuario (id_user INTEGER PRIMARY KEY, nombre text, " +
            "apelldioP text, apellidoM text, correo text,contrasena text,municipio text, calle text, colonia text,fecha INTEGER," +
            "cp text,idEstado INTEGER)",
            "CREATE TABLE IF NOT EXISTS Entidad_wifi (id INTEGER PRIMARY KEY AUTOINCREMENT, Id_dip text, Nombre_dispositivo text, Macaddres text, " +
                    "RSSI text, Fecha INTEGER,Hora text, Id_user INTEGER,Id_tipo_disposi INTEGER)",
            "CREATE TABLE IF NOT EXISTS Entidad_Bluetooth (id INTEGER PRIMARY KEY AUTOINCREMENT,Id_dip text,UUID text,Macaddres text,RSSI text,"+
                    "TX text,MAJOR text,Fecha INTEGER,Hora text,Id_user INTEGER,Id_tipo_disposi INTEGER)",
            "CREATE TABLE IF NOT EXISTS Servidor(id INTEGER PRIMARY KEY AUTOINCREMENT,ip text, dnns text,perto_orion text,puerto_crate text,servidor_prede INTEGER)",
            "CREATE TABLE IF NOT EXISTS Sin_conexion_entidad_wifi (id INTEGER PRIMARY KEY AUTOINCREMENT, Id_dip text, Nombre_dispositivo text, Macaddres text, " +
                    "RSSI text, Fecha text,Hora text, Id_user INTEGER,Id_tipo_disposi INTEGER)",
            "CREATE TABLE IF NOT EXISTS Sin_conexion_entidad_Bluetooth (id INTEGER PRIMARY KEY AUTOINCREMENT,Id_dip text,UUID text,Macaddres text,RSSI text,"+
                    "TX text,MAJOR text,Fecha text,Hora text,Id_user INTEGER,Id_tipo_disposi INTEGER)"};///Aqui van todas las tablas a crear,
    // para hacer modificaciones a la base de datos borrar la aplicacion e instalar nuevamente
    public Sqlite_DB_manejo(Context context)
    {
        this.context = context;
    }

    private void Abrir() throws SQLiteException //Abrir la base de datos
    {
        helper = new Helper(this.context);
        bd = helper.getWritableDatabase();
    }

    private void Cerrar() //Cerrar la base de datos
    {
        helper.close();
    }

    public boolean ejecutaSQL(String sql) // ejecutar insert, update, delete
    {
        try {
            this.Abrir();
            bd.execSQL(sql);
            this.Cerrar();
            return true;
        }catch (SQLiteException s)
        {
            return false;
        }
    }

    public Cursor consultaSQL(String sql)// consultas select * from .....
    {
        try {
            this.Abrir();
            Cursor c = bd.rawQuery(sql,null);
            return c;
        }catch (SQLiteException s)
        {
            this.Cerrar();
            return null;
        }
    }

    public Obj_usuario Recuerar_datos_user(String sql,Obj_usuario obj_usuario)// consultas select * from para
    // llenar un objeto de tipo usuasrio
    {
        fechas_trasnformacion trasnformacion=new fechas_trasnformacion();
        try {
            this.Abrir();
            Cursor c = bd.rawQuery(sql,null);//se inicia un cursor el cual se mueve en
                                                         //la consulta
            if(c.moveToFirst()){
                Log.e("datos",""+c.getString(0));
                obj_usuario.setId_usua(c.getString(0));
                obj_usuario.setNombre(c.getString(1));
                obj_usuario.setApellido_pater(c.getString(2));
                obj_usuario.setApellido_mater(c.getString(3));
                obj_usuario.setCorreo(c.getString(4));
                obj_usuario.setMunicipio(c.getString(6));
                obj_usuario.setCalle(c.getString(7));
                obj_usuario.setColonia(c.getString(8));
                obj_usuario.setFecha_nac(trasnformacion.milisegundos_fecha(c.getString(9),"dd-MM-yyyy hh:mm:ss"));
                obj_usuario.setCp(c.getString(10));
                obj_usuario.setIdEstado(c.getString(11));
            }
            this.Cerrar();
            return obj_usuario;
        }catch (SQLiteException s)
        {
            this.Cerrar();
            return null;
        }

    }

    public Obje_servi Recuperar_servidor_objeto(String sql,Obje_servi obje_servi){
        try {
            this.Abrir();
            Cursor c = bd.rawQuery(sql,null);//se inicia un cursor el cual se mueve en
            //la consulta
            if(c.moveToFirst()){
                Log.e("datos",""+c.getString(0));
                obje_servi.setId_servi(c.getInt(0));
                obje_servi.setIp_servidor(c.getString(1));
                obje_servi.setDNS_ser(c.getString(2));
                obje_servi.setPuerto_orion(c.getString(3));
                obje_servi.setPuerto_crateDB(c.getString(4));
                obje_servi.setServidor_predeter(c.getInt(5));
                this.Cerrar();
                return obje_servi;
            }else{
                return null;
            }
        }catch (SQLiteException s)
        {
            this.Cerrar();
            return null;
        }

    }

    public ArrayList<Obj_bluetooth> Recuperar_lista_bluetooth(String sql)// consultas select * from para llenar las listas de las diferentes clases
    {
        ArrayList<Obj_bluetooth> lista_blueList=new ArrayList();
        try {
            this.Abrir();
            Cursor c = bd.rawQuery(sql,null);
            if(c.moveToFirst()){
                do {
                    Obj_bluetooth bluetooth=new Obj_bluetooth();
                    bluetooth.setId_dip(c.getString(1));//id numero primera fila
                    bluetooth.setUUID(c.getString(2));
                    bluetooth.setBluetoothAddress(c.getString(3));
                    bluetooth.setRSSI(c.getString(4));
                    bluetooth.setTX(c.getString(5));
                    bluetooth.setMAJOR(c.getString(6));
                    bluetooth.setFecha_cap(c.getString(7));
                    bluetooth.setHora(c.getString(8));
                    bluetooth.setId_user(c.getString(9));
                    bluetooth.setId_tip_dispo((c.getString(10)));
                    lista_blueList.add(bluetooth);
                }while (c.moveToNext());
                this.Cerrar();
                return lista_blueList;
            }else{
                return null;
            }
        }catch (SQLiteException s)
        {
            this.Cerrar();
            return null;
        }

    }

    public ArrayList<Obj_wifi> Recuperar_lista_wifi(String sql)// consultas select * from para llenar las listas de las diferentes clases
    {
        ArrayList<Obj_wifi> lista_wifi=new ArrayList();
        try {
            this.Abrir();
            Cursor c = bd.rawQuery(sql,null);
            if(c.moveToFirst()){
                do {
                    String Nombre_dispos,id_dip,macaddres,RSSI,id_tip_dispo,id_user,fecha_cap,hora;
                    Obj_wifi wifi=new Obj_wifi();
                    wifi.setId_dip(c.getString(1));//id numero primera fila
                    wifi.setNombre_dispos(c.getString(2));
                    wifi.setMacaddres(c.getString(3));
                    wifi.setRSSI(c.getString(4));
                    wifi.setFecha_cap(c.getString(5));
                    wifi.setHora(c.getString(6));
                    wifi.setId_user(c.getString(7));
                    wifi.setId_tip_dispo(c.getString(8));
                    lista_wifi.add(wifi);
                }while (c.moveToNext());
                this.Cerrar();
                return lista_wifi;
            }else{
                return null;
            }
        }catch (SQLiteException s)
        {
            this.Cerrar();
            return null;
        }

    }

    public ArrayList<Obje_servi> Recuperar_servido(String sql)// consultas select * from para llenar las listas de las diferentes clases
    {
        ArrayList<Obje_servi> Lista_sevidores=new ArrayList();
        try {
            this.Abrir();
            Cursor c = bd.rawQuery(sql,null);
            if(c.moveToFirst()){
                do {
                    Obje_servi servi=new Obje_servi();
                    servi.setId_servi(c.getInt(0));//id numero primera fila
                    servi.setIp_servidor(c.getString(1));
                    servi.setDNS_ser(c.getString(2));
                    servi.setPuerto_orion(c.getString(3));
                    servi.setPuerto_crateDB(c.getString(4));
                    servi.setServidor_predeter(c.getInt(5));
                    Lista_sevidores.add(servi);
                }while (c.moveToNext());
                this.Cerrar();
                return Lista_sevidores;
            }else{
                return null;
            }
        }catch (SQLiteException s)
        {
            this.Cerrar();
            return null;
        }

    }


    public ArrayList llenar_repo(String sql, int bandera)// consultas select * from para llenar las listas de las diferentes clases
    {
        ArrayList lista=new ArrayList();
        try {
            this.Abrir();
            Cursor c = bd.rawQuery(sql,null);
            if(c.moveToFirst()){
                String datos="";
                if (bandera==1){
                    do {
                        lista.add(c.getString(1));
                        lista.add(c.getString(2));
                        lista.add(c.getString(3));
                        lista.add(c.getString(4));
                        lista.add(c.getString(5));
                    }while (c.moveToNext());
                }else if (bandera==2){
                    do {
                        lista.add(c.getString(0));
                        lista.add(c.getString(1));
                        lista.add(c.getString(2));
                        lista.add(c.getString(3));
                        lista.add(c.getString(4));
                        lista.add(c.getString(5));
                    }while (c.moveToNext());
                }else if (bandera==3){
                    do {
                        datos=c.getString(0)+" ,"+c.getString(1)+", "+c.getString(2)+", "+c.getString(3)+", "+
                                c.getString(4)+", "+c.getString(5);
                        Log.e("cadena",""+datos);
                        lista.add(datos);
                    }while (c.moveToNext());
                }else if (bandera==4){
                    do {
                        lista.add(c.getString(1));
                        lista.add(c.getString(2));
                        lista.add(c.getString(3));
                        lista.add(c.getString(4));
                        lista.add(c.getString(5));
                        lista.add(c.getString(6));
                        lista.add(c.getString(7));
                    }while (c.moveToNext());
                }
            }else{
                lista.add("0");
                Log.e("cadena","no hay datos");
            }
            return lista;
        }catch (SQLiteException s)
        {
            this.Cerrar();
            return null;
        }

    }


    private class Helper extends SQLiteOpenHelper {
        public Helper(Context context) {
            super(context, nombre_base, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase bd) {
            for(int i = 0; i < tablas.length; i++)
                bd.execSQL(tablas[i]); //Ejecuta las tablas
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}

