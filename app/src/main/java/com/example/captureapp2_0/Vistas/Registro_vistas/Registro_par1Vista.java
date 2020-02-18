package com.example.captureapp2_0.Vistas.Registro_vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.captureapp2_0.Interfaces.Registro_par1_interf.inter_Registro_par1_presentador;
import com.example.captureapp2_0.Interfaces.Registro_par1_interf.inter_Registro_par1_vista;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Presentadores.Registro_par1_presen_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public class Registro_par1Vista extends AppCompatActivity implements inter_Registro_par1_vista{

    private EditText Nombre,Apellido_parte,Apellido_mater,Correo,Contra,Conf_contra;
    private TextView titulo;
    private inter_Registro_par1_presentador presentador;
    private ProgressDialog progressDialog;
    Obj_Context obj_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_par1);
        getSupportActionBar().hide();//oculta la barra
        obj_context=new Obj_Context(getBaseContext());

        Nombre= findViewById(R.id.NombreUsu_regi);//extrae el nombre del usuario
        Apellido_parte= findViewById(R.id.Apellido_pa);//extrae dato del apellido paterno
        Apellido_mater= findViewById(R.id.Apellido_ma);//extrae dato del apellido materno
        Correo= findViewById(R.id.Correo_regis);//extrae datos del correo
        Contra= findViewById(R.id.Contra_regis);//extrae datos de la contraseña
        Conf_contra=findViewById(R.id.Contra_conf);//extrae datos para confirmar contraseña

        activar_fuente();
        presentador=new Registro_par1_presen_impL(this);
    }


    private void activar_fuente() {
        titulo = findViewById(R.id.Titulo);   //definimos en ONCREATE donde esta por                                                                                                     //medio de un ID
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Mohave-Bold.otf");
        titulo.setTypeface(face);
        Nombre.setTypeface(face);
        Apellido_parte.setTypeface(face);
        Apellido_mater.setTypeface(face);
        Correo.setTypeface(face);
        Contra.setTypeface(face);
        Conf_contra.setTypeface(face);
    }

    public void Validar_Reg(View view) {
        presentador.validar_Registro_interacto(Nombre.getText().toString(),Apellido_parte.getText().toString(),
                Apellido_mater.getText().toString(),Correo.getText().toString(),Contra.getText().toString(),
                Conf_contra.getText().toString());
    }

    @Override
    public void progressbar_show() {
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Validando datos");
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
    public void showerrorNombr(String error) {
        Nombre.setError(error);
    }

    @Override
    public void showerrorApellido_pa(String error) {
        Apellido_parte.setError(error);
    }

    @Override
    public void showerrorApellido_mate(String error) {
        Apellido_mater.setError(error);
    }

    @Override
    public void showerrorCorreo(String error) {
        Correo.setError(error);
    }

    @Override
    public void showerrorContra(String error) {
        Contra.setText("");
        Conf_contra.setText("");
        Contra.setError(error);
        Conf_contra.setError(error);
    }

    @Override
    public void Siguiente_venta(Obj_usuario obj_usuario) {
        Intent r = new Intent(this, Registro_par2Vista.class);
        r.putExtra("usuario",obj_usuario);
        startActivity(r);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        finish();

    }
}
