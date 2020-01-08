package com.example.captureapp2_0.Modelo.Modelo.funciones_generas;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fechas_trasnformacion {
    public fechas_trasnformacion() {
    }

    public String milisegundos_fecha(String dateInMilliseconds, String dateFormat) {
        return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
    }
    public long fecha_milisegundos(String myDate,String formato) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        Date date = sdf.parse(myDate);
        long millis = date.getTime();
        return millis;
    }
}
