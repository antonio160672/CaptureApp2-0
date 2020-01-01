package com.example.captureapp2_0.Vistas.Graficas;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Vista_graficas_Fragment extends Fragment implements Graficas_vistas_Inter {
    private Graficas_presentador_inter graficas_presentador;
    private Obj_Context obj_context;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_grafcias, container, false);
        graficas_presentador=new Graficas_presentador_impL(this);
        obj_context=new Obj_Context(getContext());
        validar_fechas();//hace la funcion momentania del boton
        return root;
    }

    @Override
    public void mostrar_grafica(BarData data, ArrayList<LegendEntry> etiquetas) {
        Log.e("valor eti","eti:"+etiquetas.size());
        BarChart chart = root.findViewById(R.id.chart);
        Legend leyenda = chart.getLegend();
        leyenda.setForm(Legend.LegendForm.CIRCLE);
        leyenda.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        leyenda.setCustom(etiquetas);
        //chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(etiquetas));
        chart.setFitBars(true);
        chart.getLegend().setEnabled(true);
        chart.setData(data);
        chart.invalidate();
    }

    @Override
    public void validar_fechas() {
        graficas_presentador.Validar_fechas("31-12-2019","01-01-2020");
    }

    @Override
    public void cambio_ventana() {

    }
}