package com.example.captureapp2_0.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_presentador;
import com.example.captureapp2_0.Interfaces.Ini_sesion_interF.Ini_sesion_vista;
import com.example.captureapp2_0.Modelo.Modelo.funciones_generas.validar_preguntas;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;
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
    private Dialog dialog;
    private Objeto_preguntas preguntas;

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

    @Override
    public void generar_dialog_preguntas() {
        Button BTN_preguntas;
        final EditText correo;
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.mini_preguntas);
        BTN_preguntas=dialog.findViewById(R.id.dialog_but_pregun);
        correo=dialog.findViewById(R.id.Correo_pregunta);
        correo.setVisibility(View.VISIBLE);
        BTN_preguntas.setText("Validar");
        BTN_preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar_preguntas validar_preguntas=new validar_preguntas(dialog);
                validar_preguntas.iniciar_datos();
                if (validar_preguntas.validarcamposblanco()){
                    preguntas=validar_preguntas.getPreguntas();
                    if (validar_preguntas.validar_correo()){
                        if (preguntas!=null){
                            presentador.validar_preguntas(preguntas,correo.getText().toString());
                            dialog.dismiss();
                        }
                    }
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void error_preguntas_contr(String mensaje) {
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    @Override
    public void preguntas_validas(final String correo) {
        TextView cambi_contra,titulo;
        Button Cancelar,Ingresar;
        final EditText[] contra = new EditText[1];
        dialog=new Dialog(Obj_Context.getContext());
        dialog.setContentView(R.layout.mini_con_trase);
        titulo=dialog.findViewById(R.id.textView2);
        titulo.setText("Cambio de contraseña");
        cambi_contra=dialog.findViewById(R.id.cambiarContra);
        cambi_contra.setVisibility(View.GONE);
        Cancelar=dialog.findViewById(R.id.Cancelar_dialog);
        Ingresar=dialog.findViewById(R.id.ingresar_dialog);
        Ingresar.setText("Actualizar");
        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contra[0] =dialog.findViewById(R.id.Contrauser);
                if (contra[0].getText().toString().isEmpty()){
                    contra[0].setError("Contraseña vacía");
                }else {
                    if (contra[0].getText().toString().length()<8){
                        contra[0].setError("Minimo 8 caracteres");
                    }else{
                        presentador.enviar_contra(correo,contra[0].getText().toString());
                        dialog.dismiss();
                    }
                }
            }
        });
        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                getFragmentManager().popBackStack();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    @Override//método sobre escrita del implements de Ini_sesion_vista
    public void showerrorcorreo(String mensaje) {
        usuario.setError(mensaje);
    }

    @Override//método sobre escrito de imple de Ini_sesion_vista
    public void showerrorcontra(String mensaje) {
        contra.setError(mensaje);
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
        usuario= findViewById(R.id.NombreUsu);//extrae datos de los campos
        contra= findViewById(R.id.Contraseña);//extrae datos de la contraseña

        presentador.valida_usuario(usuario.getText().toString(),contra.getText().toString());
    }

    public void Preguntas_recu(View view) {
        generar_dialog_preguntas();
    }
}
