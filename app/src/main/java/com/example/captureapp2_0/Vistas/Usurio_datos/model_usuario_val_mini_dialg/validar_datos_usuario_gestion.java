package com.example.captureapp2_0.Vistas.Usurio_datos.model_usuario_val_mini_dialg;

import android.app.Dialog;
import android.widget.EditText;
import android.widget.TextView;

import com.example.captureapp2_0.Modelo.Modelo.Interactores.Servidores_interactores.Dialog_servidor.validacampos_individuales;
import com.example.captureapp2_0.Modelo.Modelo.objetos.Obj_usuario;

public class validar_datos_usuario_gestion {
    EditText NombreUsu_regi,Apellido_pa,Apellido_ma;
    TextView text_fech;
    Dialog dialog;
    String SNombreUsu_regi,SApellido_pa,SApellido_ma,Stext_fech;
    Obj_usuario objUsuario;

    public validar_datos_usuario_gestion(Dialog dialog) {
        this.dialog = dialog;
    }

    public void cargar_usuario( EditText NombreUsu_regi,EditText Apellido_pa,EditText Apellido_ma,
                                TextView text_fech){
        this.NombreUsu_regi=NombreUsu_regi;
        this.Apellido_pa=Apellido_pa;
        this.Apellido_ma=Apellido_ma;
        this.text_fech=text_fech;
        SNombreUsu_regi=NombreUsu_regi.getText().toString();
        SApellido_pa=Apellido_pa.getText().toString();
        SApellido_ma=Apellido_ma.getText().toString();
        Stext_fech=text_fech.getText().toString();
    }

    public boolean validar_usuario() {
        boolean bandera=true;
        if(SNombreUsu_regi.isEmpty()&&SApellido_pa.isEmpty()&&SApellido_ma.isEmpty()){
            NombreUsu_regi.setError("Campo vacío");
            Apellido_pa.setError("Campo vacío");
            Apellido_ma.setError("Campo vacío");
            bandera=false;
        }else{
            if (SNombreUsu_regi.equals("")){
                NombreUsu_regi.setError("Campo vacío");
                bandera=false;
            }
            if (SApellido_pa.equals("")){
                Apellido_pa.setError("Campo vacío");
                bandera=false;
            }
            if (SApellido_ma.equals("")){
                Apellido_ma.setError("Campo vacío");
                bandera=false;
            }
            if (bandera){
                SNombreUsu_regi=SNombreUsu_regi.trim();
                SApellido_pa=SApellido_pa.trim();
                SApellido_ma=SApellido_ma.trim();
                if (!validar_individual_usuario()){
                    bandera=false;
                }else{
                    objUsuario=new Obj_usuario();
                    objUsuario.setFecha_nac(Stext_fech);
                    objUsuario.setNombre(SNombreUsu_regi);
                    objUsuario.setApellido_pater(SApellido_pa);
                    objUsuario.setApellido_mater(SApellido_ma);
                    setObjUsuario(objUsuario);
                }
            }
        }
        return bandera;
    }

    private boolean validar_individual_usuario() {
        validacampos_individuales individuales=new validacampos_individuales();
        boolean bandera=true;
        if (!individuales.Val_Nombres_bolean(SNombreUsu_regi)){
            NombreUsu_regi.setError("Valores no permitidos");
            bandera=false;
        }
        if (!individuales.Val_Nombres_bolean(SApellido_pa)){
            Apellido_pa.setError("Valores no permitidos");
            bandera=false;
        }
        if (!individuales.Val_Nombres_bolean(SApellido_ma)){
            Apellido_ma.setError("Valores no permitidos");
            bandera=false;
        }
        return bandera;
    }

    public Obj_usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Obj_usuario objUsuario) {
        this.objUsuario = objUsuario;
    }
}
