package com.example.captureapp2_0.Modelo.Modelo.Interactores.Servidores_interactores.Dialog_servidor;

import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validacampos_individuales {
    public  boolean validar_ip(String s, TextView dia_ip_servidor){
        Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find() == true){
            return true;
        }else
        {
            dia_ip_servidor.setError("Ip invalida");
            return false;
        }

    }

    public boolean validar_dns(String s, TextView dia_dns){
        Pattern pattern = Pattern.compile("^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find() == true){
            return true;
        }else
        {
            dia_dns.setError("DNS invalida");
            return false;
        }
    }

    public boolean validar_puerto(String s,TextView puerto){
        int puerto_num=Integer.parseInt(s);
        if (puerto_num>0&&puerto_num<=65536){
            return true;
        }else{
            puerto.setError("Puerto fuera de rango");
            return false;
        }

    }

    public String Val_Nombres(String s){//funcion que valida que solo existan
        //letras en el campo
        Pattern pattern = Pattern.compile("^[^ ]+( [^ ]+)*$");//expresión regular para espacios en
        Matcher matcher = pattern.matcher(s);//blanco
        if (matcher.find() == true) {//verifica si existen espacios en blanco al inicio o final
            pattern = Pattern.compile("^[a-zA-ZÀ-ÖØ-öø-ÿ\\u00F1\\u00D1\\s]+$");//solo letras
            matcher = pattern.matcher(s);
            if (matcher.find() == true) {//comprueba si hay algún número o carácter extraño
                return null;
            } else {
                return "Solo se permiten letras";
            }
        } else {
            return "No se permiten espacial inicio";
        }
    }

}
