package com.example.captureapp2_0.Vistas.Usurio_datos;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.usuario_datos_interF_vistas;
import com.example.captureapp2_0.Interfaces.Usuario_datos_interfaces.usuario_datos_presnT_interF;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Presentadores.usuario_datos_preseN_impL;
import com.example.captureapp2_0.R;

public class usuario_datos_vistas extends Fragment implements usuario_datos_interF_vistas,View.OnClickListener {
    View root;
    private Dialog dialog;
    private LinearLayout layout;
    private usuario_datos_presnT_interF presnTInterF;
    private Button Cambiar_usuario,Cambiar_direccion,btneditar;
    private EditText Contra_Nue,Contra_conf_Nuev;
    private ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
        Log.e("entroo","mensajeee");
    }

    @Override
    public void progressbar_hiden(String mensaje) {
        Contra_Nue.setText("");
        Contra_conf_Nuev.setText("");
        progressDialog.dismiss();
        if (!mensaje.isEmpty()){
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
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void ocurtar_dialog_mostrardatos() {
        dialog.dismiss();
        layout.setVisibility(View.VISIBLE);
        Cambiar_usuario=root.findViewById(R.id.Cambiar_usuario);
        Cambiar_direccion=root.findViewById(R.id.Cambiar_direccion);
        btneditar=root.findViewById(R.id.btneditar);
        Cambiar_usuario.setOnClickListener(this);
        Cambiar_direccion.setOnClickListener(this);
        btneditar.setOnClickListener(this);
        Contra_Nue=root.findViewById(R.id.Contra_Nue);
        Contra_conf_Nuev=root.findViewById(R.id.Contra_conf_Nuev);

    }
}