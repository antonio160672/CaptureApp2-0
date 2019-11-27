package com.example.captureapp2_0.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_presentador;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_vista;
import com.example.captureapp2_0.Presentadores.Ini_sesion_presen_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.menu_principal;
import com.example.captureapp2_0.objetos.Obj_Context;

public class Ini_sesion extends AppCompatActivity implements Ini_sesion_vista {

    private EditText usuario,contra;
    private TextView titulo;
    public Obj_Context obj_context;
    private Ini_sesion_presentador presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ini_sesion);

        usuario= findViewById(R.id.NombreUsu);//extrae datos de los campos
        contra= findViewById(R.id.Contraseña);//extrae datos de la contraseña

        getSupportActionBar().hide();//oculta la barra

        presentador =new Ini_sesion_presen_impL(this);
        obj_context=new Obj_Context(this);
        presentador.validar_sharepre();
        activar_fuente();//llama al metodo que activa la fuente


    }

    private void activar_fuente() {
        titulo = findViewById(R.id.Titulo);   //definimos en ONCREATE donde esta por                                                                                                     //medio de un ID
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Mohave-Bold.otf");
        titulo.setTypeface(face);

    }

    @Override
    public void showerrorcorreo() {
        usuario.setError("correo invalido");
    }

    @Override
    public void showerrorcontra() {
        contra.setError("Contraseña invalida");
    }

    @Override
    public void mover_menu_pri() {
        //este hace el cambio de vistas
        Intent r = new Intent(this, menu_principal.class);
        startActivity(r);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }

    public void Registro_usu(View view) {
        //Intent r = new Intent(this, Registro_par1Vista.class);
        Intent r = new Intent(this, Registro_par1Vista.class);
        startActivity(r);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }

    public void Ini_sesion(View view) {
        presentador.valida_usuario(usuario.getText().toString(),contra.getText().toString());
    }
}
