package com.example.captureapp2_0.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_presentador;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_vista;
import com.example.captureapp2_0.Presentadores.Ini_sesion_presen_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.Registro_vistas.Registro_par1Vista;
import com.example.captureapp2_0.menu_principal;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;

public class Ini_sesion extends AppCompatActivity implements Ini_sesion_vista {

    private EditText usuario,contra;//Variables de interfaz grafica, Campos editables
    private TextView titulo;//titulos de la aplicación
    public Obj_Context obj_context;//contexto sobre la aplicación
    private Ini_sesion_presentador presentador;//interfaz del presentador para comunicación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ini_sesion);

        usuario= findViewById(R.id.NombreUsu);//extrae datos de los campos
        contra= findViewById(R.id.Contraseña);//extrae datos de la contraseña

        getSupportActionBar().hide();//oculta la barra

        presentador =new Ini_sesion_presen_impL(this);//se instancia con la clas
                                                                 //en este caso el presentadorimpl
        obj_context=new Obj_Context(this);//se carga el contexto
        presentador.validar_sharepre();//se valida la existencia de un usuario mediante preferencs
        activar_fuente();//llama al metodo que activa la fuente


    }

    private void activar_fuente()//función para carga de títulos
    {
        titulo = findViewById(R.id.Titulo);   //definimos en ONCREATE donde esta por                                                                                                     //medio de un ID
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Mohave-Bold.otf");
        //línea 47 se busca dentro de archivos fonts para buscar la fuente para el titulo
        titulo.setTypeface(face);
    }

    @Override//método sobre escrita del implements de Ini_sesion_vista
    public void showerrorcorreo() {
        usuario.setError("correo invalido");
    }

    @Override//método sobre escrito de imple de Ini_sesion_vista
    public void showerrorcontra() {
        contra.setError("Contraseña invalida");
    }

    @Override //método sobre escrito para navegación entre menús
    public void mover_menu_pri() {
        //este hace el cambio de vistas
        Intent r = new Intent(this, menu_principal.class);
        startActivity(r);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }

    public void Registro_usu(View view) //metodo onclick para ir a registro de usuario
    {
        //Intent r = new Intent(this, Registro_par1Vista.class);
        Intent r = new Intent(this, Registro_par1Vista.class);
        startActivity(r);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }

    public void Ini_sesion(View view) //onclick para validar el inicio de sesión
    {
        presentador.valida_usuario(usuario.getText().toString(),contra.getText().toString());
    }
}
