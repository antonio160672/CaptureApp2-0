package com.example.captureapp2_0.Vistas.cap_segnals;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.presentador_captureFragment_interface;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.view_captacion_fragmen_intfa;
import com.example.captureapp2_0.Presentadores.capturaSeg_fragment_presenta_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.Vistas.cap_segnals.vistas_listas.fragmen_listas_bluetooth;
import com.example.captureapp2_0.Vistas.cap_segnals.vistas_listas.fragment_lista_wifi;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class captacion_Fragment_view extends Fragment  implements View.OnClickListener, view_captacion_fragmen_intfa {
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
    ViewPager viewPager;//permite generar las vistas abase de fragments
    TabLayout tabLayout;//permite moverse entre diferentes fragments en una misma vista
    FloatingActionButton actionButton,desactib;//boton para activar servicios
    View root;//la vista
    Viewpager_adaptador_view adaptador_view;//aqui se agregan los fragments es una clse que esta
    //en cao_segnals
    private presentador_captureFragment_interface presetador;//presentador esta en las interfaces

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //linea 49 extrae la vista para trabajar con ella
        root = inflater.inflate(R.layout.fragment_captacion_segnl, container, false);

        Obj_Context obj_context=new Obj_Context(getContext());//carga el objeto context con este contexto
        cargar_datos();//esto inicia todas las variables carga las referencias
        presetador =new capturaSeg_fragment_presenta_impL(this);//instancia al presentador
        presetador.varificar_servicios();//verifica que esten activo los servicios esto se hace
                                        //desde el presentador

        adaptador_view=new Viewpager_adaptador_view(getFragmentManager());//agrega los fragmentos aun
        //adaptador de vistas es el que permite moverse entre las dos imagenes

        return root;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ActionButton:
                actionButton.setVisibility(view.GONE);
                desactib.setVisibility(view.VISIBLE);
                presetador.escaneo_wifi_bluethoo();
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

    @Override
    public void iniciar_captura() {
        presetador.escaneo_wifi_bluethoo();
    }



    @Override
    public void agregar_viewpager() {
        adaptador_view=new Viewpager_adaptador_view(getFragmentManager());
        adaptador_view.addFRagmen(newinstance_wifi());
        adaptador_view.addFRagmen(newinstance_bluetooth());
        viewPager.setAdapter(adaptador_view);
        tabLayout.setupWithViewPager(viewPager);
    }

    private fragmen_listas_bluetooth newinstance_bluetooth(){
        fragmen_listas_bluetooth fragmenwifi_blue=new fragmen_listas_bluetooth();
        return fragmenwifi_blue;
    }
    private fragment_lista_wifi newinstance_wifi(){
        fragment_lista_wifi fragment_lista_wifi=new fragment_lista_wifi();
        return fragment_lista_wifi;
    }



}