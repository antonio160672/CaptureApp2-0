package com.example.captureapp2_0.Vistas.Salir_aplicacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_Context;
import com.example.captureapp2_0.R;

public class Salir_vistas extends Fragment {
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_salir, container, false);
        Obj_Context obj_context=new Obj_Context(getActivity());
        cerrar_sesion();

        return root;
    }

    private void cerrar_sesion() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cerrar sesión");
        builder.setMessage("¿Está seguro de que desea cerrar la sesión?");
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                getFragmentManager().popBackStack();
            }
        });
        builder.show();
    }
}
