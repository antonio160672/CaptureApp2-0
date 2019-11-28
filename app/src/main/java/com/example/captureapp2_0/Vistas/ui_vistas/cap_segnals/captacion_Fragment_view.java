package com.example.captureapp2_0.Vistas.ui_vistas.cap_segnals;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.presentador_captureFragment_interface;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.view_captacion_fragmen_intfa;
import com.example.captureapp2_0.Presentadores.capturaSeg_fragment_presenta_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class captacion_Fragment_view extends Fragment  implements View.OnClickListener, view_captacion_fragmen_intfa {

    private captacion_fragmen_Model galleryViewModel;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionButton actionButton;
    View root;
    Viewpager_adaptador_view adaptador_view;
    private presentador_captureFragment_interface presetador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(captacion_fragmen_Model.class);

        root = inflater.inflate(R.layout.fragment_captacion_segnl, container, false);
        Obj_Context obj_context=new Obj_Context(getContext());
        presetador =new capturaSeg_fragment_presenta_impL(this);
        presetador.varificar_servicios();
        actionButton=root.findViewById(R.id.ActionButton);

        adaptador_view=new Viewpager_adaptador_view(getFragmentManager());
        tabLayout=root.findViewById(R.id.TAP);
        actionButton.setOnClickListener(this);
        return root;
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ActionButton:
                cargar_datos();//se va a mover llegado el momento
                iniciar_captura();
                break;
            default:
                break;
        }
    }
    @Override
    public void iniciar_captura() {
        presetador.escaneo_wifi_bluethoo();
    }
    private void cargar_datos() {
        viewPager=root.findViewById(R.id.vista_pager);
    }
    @Override
    public void agregar_viewpager(List<String> ejemploLista, List lista_wifi_blue) {
        Viewpager_adaptador_view adaptador_view=new Viewpager_adaptador_view(getFragmentManager());
        adaptador_view.addFRagmen(newinstance(ejemploLista));
        adaptador_view.addFRagmen(newinstance(lista_wifi_blue));
        viewPager.setAdapter(adaptador_view);
        Toast.makeText(getContext(),"ya estas en la carga",Toast.LENGTH_LONG).show();
        tabLayout.setupWithViewPager(viewPager);
    }
    private fragmen_listas_wifi_blue newinstance(List<String> ejemploLista){
        fragmen_listas_wifi_blue fragmenwifi_blue=new fragmen_listas_wifi_blue();
        fragmenwifi_blue.ejemploLista2=ejemploLista;
        return fragmenwifi_blue;
    }



}