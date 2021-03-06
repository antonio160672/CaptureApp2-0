package com.example.captureapp2_0.Vistas.Registro_vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.captureapp2_0.Interfaces.Registro_par2_interF.Interf_Registro_par2_vista;
import com.example.captureapp2_0.Interfaces.Registro_par2_interF.inter_Registro_par2_presentador;
import com.example.captureapp2_0.Presentadores.Registro_par2_presen_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.menu_principal;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import java.util.Calendar;

public class Registro_par2Vista extends AppCompatActivity implements Interf_Registro_par2_vista{
    private TextView text_fech;
    private TextView titulo;
    private Spinner Estado_spi;
    private EditText municipi,calle,colonia,CP;
    private Obj_usuario obj_usuario;
    private inter_Registro_par2_presentador presentador;
    public Obj_Context obj_context;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_par2);

        getSupportActionBar().hide();//oculta la barra

        obj_usuario = (Obj_usuario) getIntent().getSerializableExtra("usuario");
        text_fech=findViewById(R.id.text_fech);
        Estado_spi=findViewById(R.id.spinner_estados);
        municipi=findViewById(R.id.Munici);
        calle=findViewById(R.id.calle);
        colonia=findViewById(R.id.colonia);
        CP=findViewById(R.id.CP);
        activar_fuente();
        presentador=new Registro_par2_presen_impL(this);

    }

    private void activar_fuente() {
        titulo = findViewById(R.id.Titulo_part2);   //definimos en ONCREATE donde esta por                                                                                                     //medio de un ID
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Mohave-Bold.otf");
        titulo.setTypeface(face);
        text_fech.setTypeface(face);
        municipi.setTypeface(face);
        calle.setTypeface(face);
        colonia.setTypeface(face);
        CP.setTypeface(face);
    }

    public void fecha_nacimiento(View view) {
        int[]datos_fecha_hor=new int [5];
        text_fech.setError(null);
        final Calendar calen=Calendar.getInstance();
        datos_fecha_hor[0]=calen.get(Calendar.DAY_OF_MONTH);
        datos_fecha_hor[1]=(calen.get(Calendar.MONTH)+1);
        datos_fecha_hor[2]=calen.get(Calendar.YEAR);
        DatePickerDialog calenda= new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int mes, int dia) {
                text_fech.setText(dia+"-"+(mes+1)+"-"+year);
            }
        },datos_fecha_hor[2],datos_fecha_hor[1],datos_fecha_hor[0]);
        calenda.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        calenda.show();
        /*Obj_Estados obj_estados=new Obj_Estados();
        Spinner spAnimals = (Spinner)findViewById(R.id.spinner_estados);
        Log.e("valoreeees",spAnimals.getSelectedItem().toString());
        Toast.makeText(this, obj_estados.contenedor_stados.get(spAnimals.getSelectedItem().toString()),
                Toast.LENGTH_LONG).show();*/
       /* Intent r = new Intent(this, Registro_par2Vista.class);
        startActivity(r);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);*/
    }

    public void Registrar(View view) {
        Log.e("fecha actiual","mesaje:"+text_fech.getText().toString());
        presentador.validar_Registro_interacto(obj_usuario,text_fech.getText().toString(),Estado_spi.getSelectedItem().toString(),
                municipi.getText().toString(),calle.getText().toString(),colonia.getText().toString(),
                CP.getText().toString());
        obj_context=new Obj_Context(this);
    }

    @Override
    public void progressbar_show() {
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Enviando datos al servidor");
        //muestras el ProgressDialog
        progressDialog.show();
    }

    @Override
    public void progressbar_hiden(String mensaje) //en caso de existir error lo mostrara
    {
        Log.e("esconder","mensajeee");
        progressDialog.dismiss();
        if (!mensaje.isEmpty()){
            Toast.makeText(Obj_Context.getContext(),mensaje,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showerrorfecha(String error) {
        text_fech.setError(error);
    }

    @Override
    public void showerrorspiner(String error) {
        TextView errorText = (TextView)Estado_spi.getSelectedView();
        errorText.setError("");
        errorText.setTextColor(Color.RED);//just to highlight that this is an error
        errorText.setText(error);
    }

    @Override
    public void showerrormunicipio(String error) {
        municipi.setError(error);
    }

    @Override
    public void showerrorcalle(String error) {
        calle.setError(error);
    }

    @Override
    public void showerrorcolonia(String error) {
        colonia.setError(error);
    }

    @Override
    public void showerrorCP(String error) {
        CP.setError(error);
    }

    @Override
    public void navegador() {
        Intent r = new Intent(this, menu_principal.class);
        startActivity(r);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }
}
