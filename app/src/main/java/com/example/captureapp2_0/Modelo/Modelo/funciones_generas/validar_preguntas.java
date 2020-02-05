package com.example.captureapp2_0.Modelo.Modelo.funciones_generas;

import android.app.Dialog;
import android.graphics.Color;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.captureapp2_0.Modelo.Modelo.Interactores.Servidores_interactores.Dialog_servidor.validacampos_individuales;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;
import com.example.captureapp2_0.R;

public class validar_preguntas {
    private Spinner Pregunta1,Pregunta2;
    private TextView respuesta1,respuesta2;
    private String Srespuesta1,Srespuesta2,Spregunta1,Spregunta2,Scorreo;
    private Dialog dialog;
    private Objeto_preguntas preguntas;
    private EditText correo;

    public validar_preguntas(Dialog dialog) {
        this.dialog = dialog;
    }


    public void iniciar_datos(){
        if (dialog!=null){
            Pregunta1=dialog.findViewById(R.id.Preguntas_segur1);
            Pregunta2=dialog.findViewById(R.id.Preguntas_segur2);
            respuesta1=dialog.findViewById(R.id.respu_1);
            respuesta2=dialog.findViewById(R.id.respu_2);

            correo=dialog.findViewById(R.id.Correo_pregunta);
            Scorreo=correo.getText().toString();

            Spregunta1=Pregunta1.getSelectedItem().toString();
            Spregunta2=Pregunta2.getSelectedItem().toString();
            Srespuesta1=respuesta1.getText().toString();
            Srespuesta2=respuesta2.getText().toString();
            Srespuesta1=Srespuesta1.trim();
            Srespuesta2=Srespuesta2.trim();
        }
    }
    public boolean validar_correo(){
        boolean bandera=true;
        if((Spregunta1.isEmpty())){
            correo.setError("Campo vacío");
            bandera= false;
        }else {
            if (!Patterns.EMAIL_ADDRESS.matcher(Scorreo).matches()){
                correo.setError("Correo invalido");
                bandera= false;
            }
        }
        return bandera;
    }

    public boolean validarcamposblanco(){
        if((Spregunta1.equals("Preguntas de seguridad")&&Spregunta2.equals("Preguntas de seguridad"))
                &&Srespuesta1.isEmpty()&&Srespuesta1.isEmpty()){
            TextView errorText = (TextView)Pregunta1.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("No seleccionado");
            TextView errorText2 = (TextView)Pregunta2.getSelectedView();
            errorText2.setError("");
            errorText2.setTextColor(Color.RED);//just to highlight that this is an error
            errorText2.setText("No seleccionado");
            respuesta1.setError("Campo vacío");
            respuesta2.setError("Campo vacío");

            return false;
        }else{
            if(Spregunta1.equals("Preguntas de seguridad")){
                TextView errorText = (TextView)Pregunta1.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("No seleccionado");
            }
            if(Spregunta2.equals("Preguntas de seguridad")){
                TextView errorText = (TextView)Pregunta2.getSelectedView();
                errorText.setError("");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("No seleccionado");
            }
            if (Srespuesta1.isEmpty()){
                respuesta1.setError("Campo vacío");
                return false;
            }
            if (Srespuesta2.isEmpty()){
                respuesta1.setError("Campo vacío");
                return false;
            }
            else {
                Log.e("cadena",":"+Srespuesta1);
                if(validacampos_indiv()){
                    return true;
                }else
                {
                    return false;
                }
            }
        }
    }

    private boolean validacampos_indiv() {
        validacampos_individuales individuales=new validacampos_individuales();
        String respuesta="";
        boolean bandera=true;
        respuesta=individuales.Val_Nombres(Srespuesta1);
        if (!(respuesta==null)){
            bandera=false;
            respuesta1.setError(respuesta);
        }
        respuesta=individuales.Val_Nombres(Srespuesta2);
        if (!(respuesta==null)){
            bandera=false;
            respuesta2.setError(respuesta);
        }
        if (bandera){
            iniciar_objeto_preguntas();
        }
        return bandera;
    }

    private void iniciar_objeto_preguntas() {
        preguntas=new Objeto_preguntas();
        preguntas.setPregunta1(Integer.parseInt(preguntas.preguntas_recuperacion.get(Spregunta1)));
        preguntas.setPregunta2(Integer.parseInt(preguntas.preguntas_recuperacion.get(Spregunta2)));
        preguntas.setSrespuesta1(Srespuesta1);
        preguntas.setSrespuesta2(Srespuesta2);
        setPreguntas(preguntas);
    }

    public Objeto_preguntas getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Objeto_preguntas preguntas) {
        this.preguntas = preguntas;
    }
}
