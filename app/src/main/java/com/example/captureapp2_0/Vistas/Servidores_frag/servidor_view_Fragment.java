package com.example.captureapp2_0.Vistas.Servidores_frag;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_presentador_frag_inteR;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_view_fragmen_inteR;
import com.example.captureapp2_0.Presentadores.sevidores_presen_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.Servidores_frag.Dialog_servidor.validar_Servi_datos;
import com.example.captureapp2_0.Vistas.cap_segnals.Viewpager_adaptador_view;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.example.captureapp2_0.objetos.Obje_servi;

public class servidor_view_Fragment extends Fragment implements servidores_view_fragmen_inteR,View.OnClickListener {

    private View root;
    private Button BTN_Registro_ser,Dia_btnRegistar;
    private TextView Titulo_list_view;
    private ListView Lista_servido;
    private Dialog dialog;
    private servidores_presentador_frag_inteR presentador_frag;
   // private TextView Dia_ip_servidor,Dia_DNS_ser,Dia_Puerto_orion,Dia_Puerto_crateDB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        root = inflater.inflate(R.layout.fragment_conexion_servidores, container, false);
        cargar_datosVista();
        activar_fuente();
        presentador_frag=new sevidores_presen_impL(this);
        return root;
    }
    private void activar_fuente() {                                                                                                    //medio de un ID
        Typeface face=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Mohave-Bold.otf");
        Titulo_list_view.setTypeface(face);
        BTN_Registro_ser.setTypeface(face);
    }

    @SuppressLint("RestrictedApi")
    private void cargar_datosVista() {
        BTN_Registro_ser=root.findViewById(R.id.BTN_Registro_ser);
        Titulo_list_view=root.findViewById(R.id.Titulo_list_view);
        Lista_servido=root.findViewById(R.id.Lista_servido);
        BTN_Registro_ser.setOnClickListener(this);
        dialog=new Dialog(Obj_Context.getContext());
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BTN_Registro_ser:
                 agregar_servi();
                break;
            default:
                break;
        }
    }

    @Override
    public void cargarLista_vista() {

    }

    @Override
    public void mensaje_sin_servido() {

    }

    @Override
    public void agregar_servi() {
        dialog.setContentView(R.layout.mini);
        Dia_btnRegistar=dialog.findViewById(R.id.Dia_btnRegistar);
        Dia_btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar_Servi_datos validar_servi_datos=new validar_Servi_datos(dialog);
                validar_servi_datos.iniciar_datos();
                if (validar_servi_datos.validarcamposblanco()){
                    Obje_servi obje_servi=new Obje_servi();
                    obje_servi=validar_servi_datos.getObjeServi();
                    presentador_frag.registrar_servidor(obje_servi);

                }else{
                    Log.e("datos22222","ni madress vato");
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}