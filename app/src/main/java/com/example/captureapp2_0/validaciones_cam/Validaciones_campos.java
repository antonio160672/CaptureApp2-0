package com.example.captureapp2_0.validaciones_cam;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones_campos {

    public Validaciones_campos() {

    }
    public String Val_Nombres(String s){//funcion que valida que solo existan
                                        //letras en el campo
        ///   "^[\\\\w+]+(\\\\.[\\\\w-]{1,62}){0,126}@[\\\\w-]{1,63}(\\\\.[\\\\w-]{1,62})+/[\\\\w-]+$"
        Pattern pattern = Pattern.compile("^[^ ]+( [^ ]+)*$");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find() == true) {
            pattern = Pattern.compile("^[a-zA-Z\\u00F1\\u00D1\\s]+$");
            matcher = pattern.matcher(s);
            if (matcher.find() == true) {
                return null;
            } else {
                return "Solo se permiten letras";
            }
        } else {
            return "No se permiten espacial inicio";
        }

    }

    public Boolean Val_Correo(String s, EditText editText){
        if (s.equals("")){
            editText.setError("Campo vacio");
            return false;
        }else {
            Pattern pattern = Pattern.compile("^[^ ]+( [^ ]+)*$");
            Matcher matcher = pattern.matcher(s);
            if (matcher.find() == true) {
                pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
                matcher = pattern.matcher(s);
                if (matcher.find() == true) {
                    return true;
                } else {
                    editText.setError("Ingrese un correo valido");
                    return false;
                }
            } else {
                editText.setError("No se permiten espacios al inicio o al final");
                return false;
            }
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
