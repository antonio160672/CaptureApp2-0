package com.example.captureapp2_0.Vistas.Usurio_datos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.usuario_datos_interF_vistas;
import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.usuario_datos_presnT_interF;
import com.example.captureapp2_0.Modelo.Modelo.Interactores.Servidores_interactores.Dialog_servidor.validar_Servi_datos;
import com.example.captureapp2_0.Modelo.Modelo.funciones_generas.validar_preguntas;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Objeto_preguntas;
import com.example.captureapp2_0.Presentadores.usuario_datos_preseN_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.Usurio_datos.model_usuario_val_mini_dialg.validar_datos_usuario_gestion;

import java.util.Calendar;

public class usuario_datos_vistas extends Fragment implements usuario_datos_interF_vistas,View.OnClickListener {
    View root;
    private Dialog dialog;
    private LinearLayout layout;
    private usuario_datos_presnT_interF presnTInterF;
    private Button Cambiar_usuario,Cambiar_direccion,btneditar;
    private EditText Contra_Nue,Contra_conf_Nuev;
    private ProgressDialog progressDialog;
    private Obj_usuario objUsuario;
    private Objeto_preguntas preguntas;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        root = inflater.inflate(R.layout.fragment_edicion_usuario, container, false);
        Obj_Context obj_context=new Obj_Context(getContext());
        presnTInterF=new usuario_datos_preseN_impL(this);
        ocultas_LinearLayout();
        cargar_dialog();
        return root;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Cambiar_usuario:
                if (objUsuario!=null){
                    dialog_datosEdit_usuario();
                }

                break;
            case R.id.Cambiar_direccion:



                break;
            case R.id.btneditar:
                presnTInterF.verficar_contras(Contra_Nue.getText().toString(),
                        Contra_conf_Nuev.getText().toString());
                break;
            default:
                break;

        }
    }

    @Override
    public void dialog_datosEdit_usuario() {

        final EditText NombreUsu_regi,Apellido_pa,Apellido_ma;
        final TextView text_fech;
        Button BTN_fecha,btnEDitar_usu;
        dialog.setContentView(R.layout.mini_datos_usuario);
        BTN_fecha=dialog.findViewById(R.id.BTN_fecha);
        btnEDitar_usu=dialog.findViewById(R.id.btnEDitar_usu);

        NombreUsu_regi=dialog.findViewById(R.id.NombreUsu_regi);
        Apellido_pa=dialog.findViewById(R.id.Apellido_pa);
        Apellido_ma=dialog.findViewById(R.id.Apellido_ma);
        text_fech=dialog.findViewById(R.id.text_fech_usuari);

        NombreUsu_regi.setText(objUsuario.getNombre());
        Apellido_pa.setText(objUsuario.getApellido_pater());
        Apellido_ma.setText(objUsuario.getApellido_mater());
        text_fech.setText(objUsuario.getFecha_nac());

        BTN_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[]datos_fecha_hor=new int [5];
                text_fech.setError(null);
                final Calendar calen=Calendar.getInstance();
                datos_fecha_hor[0]=calen.get(Calendar.DAY_OF_MONTH);
                datos_fecha_hor[1]=(calen.get(Calendar.MONTH)+1);
                datos_fecha_hor[2]=calen.get(Calendar.YEAR);
                DatePickerDialog calenda= new DatePickerDialog(Obj_Context.getContext(), AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int mes, int dia) {
                                text_fech.setText(dia+"-"+(mes+1)+"-"+year);
                            }
                        },datos_fecha_hor[2],datos_fecha_hor[1],datos_fecha_hor[0]);
                calenda.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                calenda.show();

            }
        });
        btnEDitar_usu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar_datos_usuario_gestion gestion=new validar_datos_usuario_gestion(dialog);
                gestion.cargar_usuario(NombreUsu_regi,Apellido_pa,Apellido_ma,text_fech);
                if (gestion.validar_usuario()){
                    Obj_usuario obj_usuario=gestion.getObjUsuario();
                    presnTInterF.editar_datos_personales(obj_usuario);
                    dialog.dismiss();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void cargar_usuario(Obj_usuario usuario) {
        objUsuario=usuario;
    }

    @Override
    public void error_nueva_contra(String mensaje) {
        Contra_Nue.setText("");
        Contra_conf_Nuev.setText("");
        Contra_Nue.setError(mensaje);
        Contra_conf_Nuev.setError(mensaje);
    }

    @Override
    public void progressbar_show() {
        progressDialog= new ProgressDialog(Obj_Context.getContext());
        progressDialog.setMessage("Validando datos");
        //muestras el ProgressDialog
        progressDialog.show();

    }

    @Override
    public void progressbar_hiden(String mensaje) {
        progressDialog.dismiss();
        if (!mensaje.isEmpty()){
            Contra_Nue.setText("");
            Contra_conf_Nuev.setText("");
            Toast.makeText(Obj_Context.getContext(),mensaje,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void ocultas_LinearLayout() {
        layout=root.findViewById(R.id.Contenedor_principal);
        layout.setVisibility(View.GONE);
    }

    @Override
    public void cargar_dialog() {
        TextView cambi_contra;
        Button Cancelar,Ingresar;
        final EditText[] contra = new EditText[1];
        dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.mini_con_trase);
        cambi_contra=dialog.findViewById(R.id.cambiarContra);
        Cancelar=dialog.findViewById(R.id.Cancelar_dialog);
        Ingresar=dialog.findViewById(R.id.ingresar_dialog);

        cambi_contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generar_dialog_preguntas();
            }
        });
        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contra[0] =dialog.findViewById(R.id.Contrauser);
                if (contra[0].getText().toString().isEmpty()){
                    contra[0].setError("Contraseña vacía");
                }else {
                    presnTInterF.validar_contra(contra[0],contra[0].getText().toString());
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

    @Override
    public void generar_dialog_preguntas() {
        dialog.dismiss();
        Button BTN_preguntas;
        dialog=new Dialog(Obj_Context.getContext());
        dialog.setContentView(R.layout.mini_preguntas);
        BTN_preguntas=dialog.findViewById(R.id.dialog_but_pregun);
        BTN_preguntas.setText("Validar");
        BTN_preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar_preguntas validar_preguntas=new validar_preguntas(dialog);
                validar_preguntas.iniciar_datos();
                if (validar_preguntas.validarcamposblanco()){
                    preguntas=validar_preguntas.getPreguntas();
                    if (preguntas!=null){
                        presnTInterF.validar_preguntas(preguntas);
                    }
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void ocurtar_dialog_mostrardatos() {

        if (dialog!=null){
            dialog.dismiss();
        }
        presnTInterF.recuperar_datos_usuario();
        dialog.dismiss();
        Typeface face=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Mohave-Bold.otf");
        layout.setVisibility(View.VISIBLE);
        Cambiar_usuario=root.findViewById(R.id.Cambiar_usuario);
        Cambiar_direccion=root.findViewById(R.id.Cambiar_direccion);
        btneditar=root.findViewById(R.id.btneditar);
        Cambiar_usuario.setOnClickListener(this);
        Cambiar_direccion.setOnClickListener(this);
        btneditar.setOnClickListener(this);
        Contra_Nue=root.findViewById(R.id.Contra_Nue);
        Contra_conf_Nuev=root.findViewById(R.id.Contra_conf_Nuev);
        Cambiar_usuario.setTypeface(face);
        Cambiar_direccion.setTypeface(face);
        btneditar.setTypeface(face);
        Contra_Nue.setTypeface(face);
        Contra_conf_Nuev.setTypeface(face);
        Cambiar_direccion.setTypeface(face);
    }

    @Override
    public void progressbar_hiden_sin_mensa(String mensaje) {
        progressDialog.hide();
        if(!mensaje.equals("")){
            Toast.makeText(Obj_Context.getContext(),mensaje,Toast.LENGTH_LONG).show();
        }
    }
}