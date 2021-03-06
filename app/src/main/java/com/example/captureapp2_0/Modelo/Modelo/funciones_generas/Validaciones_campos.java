package com.example.captureapp2_0.Modelo.Modelo.funciones_generas;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones_campos {

    public Validaciones_campos() {

    }

    public String Val_Nombres(String s){//función que valida letras
        Pattern pattern = Pattern.compile("\\p{Punct}");//expresión regular para espacios en
        Matcher matcher = pattern.matcher(s);//blanco
        if (matcher.find() == false) {//verifica si existen espacios en blanco al inicio o final
            pattern = Pattern.compile("^[a-zA-ZÀ-ÖØ-öø-ÿ\\u00F1\\u00D1\\s]+$");//solo letras
            matcher = pattern.matcher(s);
            if (matcher.find() == true) {//comprueba si hay algún número o carácter extraño
                return null;
            } else {
                return "Solo se permiten letras";
            }
        } else {
            return "No se permiten caracteres especiales";
        }
    }
    public String Direccion(String s){//función que valida letras
        Pattern pattern = Pattern.compile("^[\\wÀ-ÖØ-öø-ÿ#\\u00F1\\u00D1\\s]*$");//solo letras
        Matcher matcher =pattern.matcher(s);
            if (matcher.find() == true) {//comprueba si hay algún número o carácter extraño
                return null;
            } else {
                return "Solo se permiten letras";
            }
    }


    public Boolean Val_num(String s, EditText editText){
        ///   "^[\\\\w+]+(\\\\.[\\\\w-]{1,62}){0,126}@[\\\\w-]{1,63}(\\\\.[\\\\w-]{1,62})+/[\\\\w-]+$"
        if (s.equals("")){
            editText.setError("Campo vacio");
            return false;
        }else {
            Pattern pattern = Pattern.compile("^[^ ]+( [^ ]+)*$");
            Matcher matcher = pattern.matcher(s);
            if (matcher.find() == true) {
                pattern = Pattern.compile("^[0-9]+$");
                matcher = pattern.matcher(s);
                if (matcher.find() == true) {
                    return true;
                } else {
                    editText.setError("Solo se permiten numero");
                    return false;
                }
            } else {
                editText.setError("No se permiten espacios al inicio o al final");
                return false;
            }
        }
    }
}
