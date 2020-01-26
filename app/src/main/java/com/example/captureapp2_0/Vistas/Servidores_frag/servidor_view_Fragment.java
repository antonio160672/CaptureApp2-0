package com.example.captureapp2_0.Vistas.Servidores_frag;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_presentador_frag_inteR;
import com.example.captureapp2_0.Interfaces.Servidores_interactores.servidores_view_fragmen_inteR;
import com.example.captureapp2_0.Presentadores.sevidores_presen_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Modelo.Modelo.Interactores.Servidores_interactores.Dialog_servidor.validar_Servi_datos;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;

import java.util.ArrayList;

public class servidor_view_Fragment extends Fragment implements servidores_view_fragmen_inteR,View.OnClickListener {
    private Obje_servi obje_servi;
    private View root;

    public ArrayList<Obje_servi> servis_l=new ArrayList<Obje_servi>();
    private Adaptador_recycle_Servido servidores_adap;
    //este es el adaptador de los servidores
    Obj_Context obj_context;

    private Button BTN_Registro_ser,Dia_btnRegistar;
    private TextView Titulo_list_view;

    public RecyclerView RecycleServi;

    private Dialog dialog;
    private servidores_presentador_frag_inteR presentador_frag;
   // private TextView Dia_ip_servidor,Dia_DNS_ser,Dia_Puerto_orion,Dia_Puerto_crateDB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        root = inflater.inflate(R.layout.fragment_conexion_servidores, container, false);
        obj_context=new Obj_Context(getContext());

        presentador_frag=new sevidores_presen_impL(this);
        obje_servi=new Obje_servi();
        cargar_datosVista();

        servidores_adap=new Adaptador_recycle_Servido(servis_l,this);
        RecycleServi.setAdapter(servidores_adap);

        presentador_frag.consultar_servi(obje_servi);

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
        RecycleServi=root.findViewById(R.id.RecycleServi);
        RecycleServi.setLayoutManager(new LinearLayoutManager(Obj_Context.getContext(), LinearLayoutManager.VERTICAL,false));

        BTN_Registro_ser.setOnClickListener(this);
        dialog=new Dialog(Obj_Context.getContext());
        activar_fuente();

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
    public void cargarLista_vista(ArrayList<Obje_servi> servis) {
        if (servis!=null){
            servis_l.clear();
            servis_l.addAll(servis);
        }else {
            servis_l.clear();
        }
        servidores_adap.notifyDataSetChanged();
        //updateList();
        /*for (int i=0;i<servis_l.size();i++){
            Log.e("se entro en vista","DNNS:"+servis_l.get(i).getDNS_ser()+" IP:"+servis_l.get(i).getIp_servidor()+
                    " ID:"+servis_l.get(i).getId_servi()+" Serpre:"+servis_l.get(i).getServidor_predeter()+"\n");
        }*/
    }

    @Override
    public void mensaje_sin_servido(String mensaje) {
        Toast.makeText(Obj_Context.getContext(),mensaje,Toast.LENGTH_LONG).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void eliminar_servi(final int dato, final Obje_servi obje_servi) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Eliminar Servidor");
        builder.setMessage("¿Desea eliminar este servidor?");
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                presentador_frag.eliminar_dispo(dato,obje_servi);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        builder.show();

    }

    @Override
    public void agregar_servi() {
        dialog=new Dialog(Obj_Context.getContext());
        dialog.setContentView(R.layout.mini_servidores);
        Dia_btnRegistar=dialog.findViewById(R.id.Dia_btnRegistar);
        Dia_btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar_Servi_datos validar_servi_datos=new validar_Servi_datos(dialog);
                validar_servi_datos.iniciar_datos();
                if (validar_servi_datos.validarcamposblanco()){
                    obje_servi=validar_servi_datos.getObjeServi();
                    presentador_frag.registrar_servidor(obje_servi);
                    obje_servi=null;
                    dialog.dismiss();
                }else{
                    Log.e("datos22222","ni madress vato");
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void Editar_servi(final int dato,Obje_servi obje_servi) {
        final Obje_servi[] nuevoobje_servi = {obje_servi};
        TextView Dia_ip_servidor,Dia_DNS_ser,Dia_Puerto_orion,Dia_Puerto_crateDB,titulo_servi;
        dialog.setContentView(R.layout.mini_servidores);
        titulo_servi=dialog.findViewById(R.id.titulo_servi);
        Dia_btnRegistar=dialog.findViewById(R.id.Dia_btnRegistar);
        titulo_servi.setText("Editar servidor");
        Dia_btnRegistar.setText("Editar");
        Dia_ip_servidor=dialog.findViewById(R.id.ip_servidor);
        Dia_DNS_ser=dialog.findViewById(R.id.DNS_ser);
        Dia_Puerto_orion=dialog.findViewById(R.id.Puerto_orion);
        Dia_Puerto_crateDB=dialog.findViewById(R.id.Puerto_crateDB);
        Dia_ip_servidor.setText(nuevoobje_servi[0].getIp_servidor());
        Dia_DNS_ser.setText(nuevoobje_servi[0].getDNS_ser());
        Dia_Puerto_orion.setText(nuevoobje_servi[0].getPuerto_orion());
        Dia_Puerto_crateDB.setText(nuevoobje_servi[0].getPuerto_crateDB());

        Dia_btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar_Servi_datos validar_servi_datos=new validar_Servi_datos(dialog);
                validar_servi_datos.iniciar_datos();
                validar_servi_datos.servi_pr=nuevoobje_servi[0].getServidor_predeter();
                if (validar_servi_datos.validarcamposblanco()){
                    nuevoobje_servi[0]=validar_servi_datos.getObjeServi();
                    presentador_frag.actualizar_dispo(dato,nuevoobje_servi[0]);
                    dialog.dismiss();
                }else{
                    Toast.makeText(Obj_Context.getContext(),"No se logro editar el servidor",Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }

    @Override
    public void Servidor_por_defecto(final int dato, final Obje_servi obje_servi) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cambiar servidor por defecto");
        builder.setMessage("¿Desea cambiar el servidor por defecto?");
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                presentador_frag.cambiar_servidor(dato,obje_servi);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //   cancelar();
            }
        });
        builder.show();
    }
}