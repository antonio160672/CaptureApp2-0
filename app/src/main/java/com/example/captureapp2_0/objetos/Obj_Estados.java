package com.example.captureapp2_0.objetos;

import java.util.Hashtable;

public class Obj_Estados {
    public Hashtable<String,String> contenedor_stados;

    public Obj_Estados() {
        contenedor_stados=new Hashtable<String,String>();
        cargar();
    }
    public void cargar(){
        contenedor_stados.put("Aguascalientes" ,"1");
        contenedor_stados.put("Baja California","2");
        contenedor_stados.put("3","Baja California Sur");
        contenedor_stados.put("4","Campeche");
        contenedor_stados.put("5","Coahuila de Zaragoza");
        contenedor_stados.put("6","Colima");
        contenedor_stados.put("7","Chiapas");
        contenedor_stados.put("8","Chihuahua");
        contenedor_stados.put("9","Distrito Federal");
        contenedor_stados.put("10","Durango");
        contenedor_stados.put("11","Guanajuato");
        contenedor_stados.put("12","Guerrero");
        contenedor_stados.put("13","Hidalgo");
        contenedor_stados.put("14","Jalisco");
        contenedor_stados.put("15","México");
        contenedor_stados.put("16","Michoacán de Ocampo");
        contenedor_stados.put("17","Morelos ");
        contenedor_stados.put("18","Nayarit");
        contenedor_stados.put("19","Nuevo León");
        contenedor_stados.put("20","Oaxaca");
        contenedor_stados.put("21","Puebla");
        contenedor_stados.put("22","Querétaro");
        contenedor_stados.put("23","Quintana Roo");
        contenedor_stados.put("24","San Luis Potosí");
        contenedor_stados.put("25","Sinaloa");
        contenedor_stados.put("26","Sonora");
        contenedor_stados.put("27","Tabasco");
        contenedor_stados.put("28","Tamaulipas");
        contenedor_stados.put("29","Tlaxcala");
        contenedor_stados.put("30","Veracruz");
        contenedor_stados.put("31","Yucatán");
        contenedor_stados.put("32","Zacatecas");
    }
}
