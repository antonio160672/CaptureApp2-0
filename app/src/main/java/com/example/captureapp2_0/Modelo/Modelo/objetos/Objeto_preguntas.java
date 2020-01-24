package com.example.captureapp2_0.Modelo.Modelo.objetos;

import android.database.Cursor;

import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;

import java.util.Hashtable;

public class Objeto_preguntas {
    int Pregunta1,pregunta2;
    String Srespuesta1,Srespuesta2;
    public Hashtable<String,String> preguntas_recuperacion;
    public Sqlite_DB_manejo sqLite=null;
    public Cursor cursor=null;
    public Objeto_preguntas() {
        preguntas_recuperacion=new Hashtable<String,String>();
        cargar();
    }

    public void cargar(){
        preguntas_recuperacion.put("¿Cuál es el nombre de tu mascota?" ,"1");
        preguntas_recuperacion.put("¿Cuál fue tú primer empleo?","2");
        preguntas_recuperacion.put("¿Cuál es tú comida favorita?","3");
        preguntas_recuperacion.put("¿Cuál es el nombre de tú primer profesor?","4");

    }

    public int getPregunta1() {
        return Pregunta1;
    }

    public void setPregunta1(int pregunta1) {
        Pregunta1 = pregunta1;
    }

    public int getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(int pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getSrespuesta1() {
        return Srespuesta1;
    }

    public void setSrespuesta1(String srespuesta1) {
        Srespuesta1 = srespuesta1;
    }

    public String getSrespuesta2() {
        return Srespuesta2;
    }

    public void setSrespuesta2(String srespuesta2) {
        Srespuesta2 = srespuesta2;
    }
}
