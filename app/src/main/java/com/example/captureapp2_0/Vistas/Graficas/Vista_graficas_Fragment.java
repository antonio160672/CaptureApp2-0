package com.example.captureapp2_0.Vistas.Graficas;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.captureapp2_0.Interfaces.Graficas_intF.Graficas_presentador_inter;
import com.example.captureapp2_0.Interfaces.Graficas_intF.Graficas_vistas_Inter;
import com.example.captureapp2_0.Presentadores.Graficas_presentador_impL;
import com.example.captureapp2_0.R;
import com.example.captureapp2_0.objetos.Obj_Context;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Vista_graficas_Fragment extends Fragment implements Graficas_vistas_Inter ,View.OnClickListener{
    private Graficas_presentador_inter graficas_presentador;
    private Obj_Context obj_context;
    private View root;
    private Button fecha_ini,fecha_fin,cargar_grafi;
    private Spinner dispositivo,grafica;
    private String fecha_ini_string,fecha_fin_string;
    private BarChart chart;
    private LineChart Lchart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        root = inflater.inflate(R.layout.fragment_grafcias, container, false);

        graficas_presentador=new Graficas_presentador_impL(this);
        obj_context=new Obj_Context(getContext());
        cargar_campos();
        chart.setVisibility(View.GONE);
        return root;
    }
    @Override
    public void cargar_campos() {
        Lchart=root.findViewById(R.id.Linerchart);
        chart= root.findViewById(R.id.chart);
        fecha_ini=root.findViewById(R.id.Fecha_inio);
        fecha_fin=root.findViewById(R.id.FEcha_fin);
        cargar_grafi=root.findViewById(R.id.BTN_generar);
        fecha_ini.setOnClickListener(this);
        fecha_fin.setOnClickListener(this);
        cargar_grafi.setOnClickListener(this);
        dispositivo=root.findViewById(R.id.spinner_wifi_bluetooth);
        grafica=root.findViewById(R.id.tipo_grafica);
        fecha_ini_string="";
        fecha_fin_string="";
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Fecha_inio:
                fecha_capt(1);
                Log.e("fecha","fecha de inicio");
                break;
            case R.id.FEcha_fin:
                Log.e("fecha","fecha de fin");
                fecha_capt(0);
                break;
            case R.id.BTN_generar:
                validar_datos();//hace la funcion momentania del boton
                Log.e("fecha","validar y generar");
                break;
            default:
                break;
        }

    }

    @Override
    public void fecha_capt(final int bandera) {
        int[]datos_fecha_hor=new int [5];
        final Calendar calen=Calendar.getInstance();
        datos_fecha_hor[0]=calen.get(Calendar.DAY_OF_MONTH);
        datos_fecha_hor[1]=(calen.get(Calendar.MONTH)+1);
        datos_fecha_hor[2]=calen.get(Calendar.YEAR);
        DatePickerDialog calenda= new DatePickerDialog(Obj_Context.getContext(), AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int mes, int dia) {
                        if (bandera==1){
                            fecha_ini_string=(dia+"-"+(mes+1)+"-"+year);
                            fecha_ini.setText(fecha_ini_string);
                        }
                        else{
                            fecha_fin_string=(dia+"-"+(mes+1)+"-"+year);
                            fecha_fin.setText(fecha_fin_string);
                        }
                    }
                },datos_fecha_hor[2],datos_fecha_hor[1],datos_fecha_hor[0]);
        calenda.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        calenda.show();
    }

    @Override
    public void mostrar_grafica(BarData data, ArrayList<LegendEntry> etiquetas) {
        Log.e("valor eti","eti:"+etiquetas.size());
        Lchart.setVisibility(View.GONE);
        chart.setVisibility(View.VISIBLE);
        Legend leyenda = chart.getLegend();
        leyenda.setForm(Legend.LegendForm.CIRCLE);
        leyenda.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        leyenda.setCustom(etiquetas);
        leyenda.setWordWrapEnabled(true);
        //chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(etiquetas));
        chart.setFitBars(true);
        chart.getLegend().setEnabled(true);
        chart.setData(data);
        chart.invalidate();
    }

    @Override
    public void mostrar_grafica_lineal(LineData data, ArrayList<LegendEntry> etiquetas) {
        chart.setVisibility(View.GONE);
        Lchart.setVisibility(View.VISIBLE);

        Legend leyenda = Lchart.getLegend();
        leyenda.setForm(Legend.LegendForm.CIRCLE);
        leyenda.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        leyenda.setCustom(etiquetas);
        leyenda.setWordWrapEnabled(true);
        //chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(etiquetas));
        Lchart.getLegend().setEnabled(true);
        Lchart.setData(data);
        Lchart.invalidate();
    }

    @Override
    public void validar_datos() {
        /*if (fecha_fin_string){
            Log.e("valor dispo",":"+fecha_fin_string);
        }*/
        graficas_presentador.Validar_fechas(fecha_ini_string,fecha_fin_string,
                dispositivo.getSelectedItem().toString(),grafica.getSelectedItem().toString());
    }

    @Override
    public void show_error_fecha(String mensaje) {
        Toast.makeText(Obj_Context.getContext(),mensaje,Toast.LENGTH_LONG).show();
    }

    @Override
    public void show_error_logico_fechas(String mensaje) {

    }

    @Override
    public void show_error_dispositivo(String mensaje) {
        TextView errorText = (TextView)dispositivo.getSelectedView();
        errorText.setError("");
        errorText.setTextColor(Color.RED);//just to highlight that this is an error
        errorText.setText(mensaje);

    }

    @Override
    public void show_graficas(String mensaje) {
        TextView errorText = (TextView)grafica.getSelectedView();
        errorText.setError("");
        errorText.setTextColor(Color.RED);//just to highlight that this is an error
        errorText.setText(mensaje);
    }
}