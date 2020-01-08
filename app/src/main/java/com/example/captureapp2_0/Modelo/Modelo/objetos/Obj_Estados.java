package com.example.captureapp2_0.Modelo.Modelo.objetos;

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
        contenedor_stados.put("Baja California Sur","3");
        contenedor_stados.put("Campeche","4");
        contenedor_stados.put("Coahuila de Zaragoza","5");
        contenedor_stados.put("Colima","6");
        contenedor_stados.put("Chiapas","7");
        contenedor_stados.put("Chihuahua","8");
        contenedor_stados.put("Distrito Federal","9");
        contenedor_stados.put("Durango","10");
        contenedor_stados.put("Guanajuato","11");
        contenedor_stados.put("Guerrero","12");
        contenedor_stados.put("Hidalgo","13");
        contenedor_stados.put("Jalisco","14");
        contenedor_stados.put("México","15");
        contenedor_stados.put("Michoacán de Ocampo","16");
        contenedor_stados.put("Morelos","17");
        contenedor_stados.put("Nayarit","18");
        contenedor_stados.put("Nuevo León","19");
        contenedor_stados.put("Oaxaca","20");
        contenedor_stados.put("Puebla","21");
        contenedor_stados.put("Querétaro","22");
        contenedor_stados.put("Quintana Roo","23");
        contenedor_stados.put("San Luis Potosí","24");
        contenedor_stados.put("Sinaloa","25");
        contenedor_stados.put("Sonora","26");
        contenedor_stados.put("Tabasco","27");
        contenedor_stados.put("Tamaulipas","28");
        contenedor_stados.put("Tlaxcala","29");
        contenedor_stados.put("Veracruz","30");
        contenedor_stados.put("Yucatán","31");
        contenedor_stados.put("Zacatecas","32");
    }

    public void Remplazar(){
        contenedor_stados.clear();
        contenedor_stados.put("1" ,"Aguascalientes");
        contenedor_stados.put("2","Baja California");
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
        contenedor_stados.put("17","Morelos");
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
