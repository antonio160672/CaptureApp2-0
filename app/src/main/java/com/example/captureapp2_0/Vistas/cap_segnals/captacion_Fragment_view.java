package com.example.captureapp2_0.Vistas.cap_segnals;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.presentador_captureFragment_interface;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.view_captacion_fragmen_intfa;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_bluetooth;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_wifi;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obje_servi;
import com.example.captureapp2_0.Presentadores.capturaSeg_fragment_presenta_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.cap_segnals.vistas_listas.fragmen_listas_bluetooth;
import com.example.captureapp2_0.Vistas.cap_segnals.vistas_listas.fragment_lista_wifi;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.util.ArrayList;

public class captacion_Fragment_view extends Fragment  implements View.OnClickListener, view_captacion_fragmen_intfa {
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
    ViewPager viewPager;//permite generar las vistas abase de fragments
    TabLayout tabLayout;//permite moverse entre diferentes fragments en una misma vista
    FloatingActionButton actionButton,desactib;//boton para activar servicios
    View root;//la vista
    Obj_Context obj_context;
    Viewpager_adaptador_view adaptador_view;//aqui se agregan los fragments es una clse que esta
    //en cao_segnals
    private Obje_servi obje_servi;
    private presentador_captureFragment_interface presetador;//presentador esta en las interfaces

    private ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //linea 49 extrae la vista para trabajar con ella
        root = inflater.inflate(R.layout.fragment_captacion_segnl, container, false);
        obj_context=new Obj_Context(getContext());//carga el objeto context con este contexto

        cargar_datos();//esto inicia todas las variables carga las referencias
        presetador =new capturaSeg_fragment_presenta_impL(this);//instancia al presentador
        presetador.verificar_servicios();//verifica que esten activo los servicios esto se hace
                                        //desde el presentador

        adaptador_view=new Viewpager_adaptador_view(getFragmentManager());//agrega los fragmentos aun
        //adaptador de vistas es el que permite moverse entre las dos imagenes

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                /* Este codigo es para identificar tu request */ 1);

        verificar_datos_sin_conex();
        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1 /* El codigo que puse a mi request */: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // aqui ya tengo permisos
                } else {
                    // aqui no tengo permisos
                }
                return;
            }
        }
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ActionButton:
                if (presetador.verificar_conexion_servi(obje_servi.getDNS_ser(),obje_servi.getIp_servidor())){
                    actionButton.setVisibility(view.GONE);
                    desactib.setVisibility(view.VISIBLE);
                    presetador.escaneo_wifi_bluethoo_con_conexion(obje_servi);
                }else{
                    error_en_servidors("imposible conectarse con el servidor");
                    actionButton.setVisibility(root.GONE);
                    desactib.setVisibility(root.VISIBLE);
                    capturar_sin_internet();
                }
                break;
            case R.id.Desactivar_button:
                presetador.destruir();
                Log.e("mensajje","");
                actionButton.setVisibility(view.VISIBLE);
                desactib.setVisibility(view.GONE);
                adaptador_view.clearFragemen();
                viewPager.setAdapter(adaptador_view);
                break;
            default:
                break;
        }
    }

    @Override
    public void progressbar_show() {
        progressDialog= new ProgressDialog(Obj_Context.getContext());
        progressDialog.setMessage("Enviando datos al servidor");
        //muestras el ProgressDialog
        progressDialog.show();
        Log.e("startAsyncTask", "start");
    }

    @Override
    public void progressbar_hiden(String mensaje) //en caso de existir error lo mostrara
    {
        progressDialog.dismiss();
        if (!mensaje.isEmpty()){
            Toast.makeText(Obj_Context.getContext(),mensaje,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void verificar_datos_sin_conex() {
        presetador.verificar_datos_sin_conex();
    }

    @Override
    public void Mensaje_datos_capt_sin_servidor(final ArrayList<Obj_bluetooth> Array_bluet, final ArrayList<Obj_wifi> Array_wifi) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Datos captados sin conexión");
        builder.setMessage("¿Desea enviar los datos captados sin conexión?");
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                if (presetador.verificar_conexion_servi(obje_servi.getDNS_ser(),obje_servi.getIp_servidor())){
                    dialogo1.dismiss();
                    presetador.enviar_datos_sinconex(Array_bluet,Array_wifi,obje_servi);
                }else{
                    error_en_servidors("No es posible conectar con el servidor de Fiware");
                    error_en_servidors("Favor de cambiar servidor predeterminado");
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        builder.show();
    }

    @Override
    public void capturar_sin_internet() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Sin conexión al servidor");
        builder.setMessage("¿Desea capturar datos y luego enviar al servidor?");
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                presetador.escaneo_wifi_bluethoo_sin_conexion();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        builder.show(); 
    }

    @Override
    public void cargar_datos_servi(Obje_servi obje_servi) {
        this.obje_servi=obje_servi;
    }

    @SuppressLint("RestrictedApi")
    private void cargar_datos() {
        actionButton=root.findViewById(R.id.ActionButton);
        adaptador_view=new Viewpager_adaptador_view(getFragmentManager());
        tabLayout=root.findViewById(R.id.TAP);
        actionButton.setOnClickListener(this);
        desactib=root.findViewById(R.id.Desactivar_button);
        desactib.setVisibility(root.GONE);
        desactib.setOnClickListener(this);
        viewPager=root.findViewById(R.id.vista_pager);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void mensaje_servicios_wifi() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Permisos de WI-Fi");
        builder.setMessage("Por favor active los servicios WIFi");
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void activar_servicios() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Permisos de ubicación");
        builder.setMessage("Por favor active los permisos");
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ActivityCompat.requestPermissions(
                        getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSIONS);
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", Obj_Context.getContext().getPackageName(), null);
                intent.setData(uri);
                Obj_Context.getContext().startActivity(intent);
            }
        });
        builder.show();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void error_en_servidors(String mensaje) {
        Toast.makeText(Obj_Context.getContext(),mensaje,Toast.LENGTH_LONG).show();
        actionButton.setVisibility(root.GONE);
    }

    @Override
    public void viewpager_sin_conexion_internet() {
        adaptador_view=new Viewpager_adaptador_view(getFragmentManager());
        adaptador_view.addFRagmen(newinstance_wifi());
        adaptador_view.addFRagmen(newinstance_bluetooth());
        viewPager.setAdapter(adaptador_view);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void agregar_viewpager_conexion_internet(Obje_servi obje_servi) {
        adaptador_view=new Viewpager_adaptador_view(getFragmentManager());
        adaptador_view.addFRagmen(newinstance_wifi(obje_servi));
        adaptador_view.addFRagmen(newinstance_bluetooth(obje_servi));
        viewPager.setAdapter(adaptador_view);
        tabLayout.setupWithViewPager(viewPager);
    }

    private fragmen_listas_bluetooth newinstance_bluetooth(Obje_servi obje_servi){
        Log.e("con internet","esta dentro de blue con internet");
        fragmen_listas_bluetooth fragmenwifi_blue=new fragmen_listas_bluetooth(obje_servi);
        return fragmenwifi_blue;

    }

    private fragment_lista_wifi newinstance_wifi(Obje_servi obje_servi){
        Log.e("con internet","esta dentro de wifi con internet");
        fragment_lista_wifi fragment_lista_wifi=new fragment_lista_wifi(obje_servi);
        return fragment_lista_wifi;
    }

    private fragmen_listas_bluetooth newinstance_bluetooth(){
        Log.e("sin internet","sin  internetttttt");
        fragmen_listas_bluetooth fragmenwifi_blue=new fragmen_listas_bluetooth();
        fragmenwifi_blue.cargarlista();
        return fragmenwifi_blue;
    }

    private fragment_lista_wifi newinstance_wifi(){
        fragment_lista_wifi fragment_lista_wifi=new fragment_lista_wifi();
        return fragment_lista_wifi;
    }



}
