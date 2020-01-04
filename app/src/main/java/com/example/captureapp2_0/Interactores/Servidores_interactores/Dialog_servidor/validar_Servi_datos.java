package com.example.captureapp2_0.Interactores.Servidores_interactores.Dialog_servidor;

import android.app.Dialog;
import android.widget.TextView;

import com.example.captureapp2_0.R;
import com.example.captureapp2_0.objetos.Obje_servi;

public class validar_Servi_datos {
    private TextView Dia_ip_servidor,Dia_DNS_ser,Dia_Puerto_orion,Dia_Puerto_crateDB;
    Dialog dialog;
    String ip_servidor,DNS_ser,Puerto_orion,Puerto_crateDB;
    private Obje_servi objeServi;

    public validar_Servi_datos(Dialog dialog) {
        this.dialog = dialog;
    }

    public void iniciar_datos(){
        if (dialog!=null){
            Dia_ip_servidor=dialog.findViewById(R.id.ip_servidor);
            Dia_DNS_ser=dialog.findViewById(R.id.DNS_ser);
            Dia_Puerto_orion=dialog.findViewById(R.id.Puerto_orion);
            Dia_Puerto_crateDB=dialog.findViewById(R.id.Puerto_crateDB);

            ip_servidor=Dia_ip_servidor.getText().toString();
            DNS_ser=Dia_DNS_ser.getText().toString();
            Puerto_orion=Dia_Puerto_orion.getText().toString();
            Puerto_crateDB=Dia_Puerto_crateDB.getText().toString();
        }
    }

    public boolean validarcamposblanco(){
        if((ip_servidor.isEmpty()||DNS_ser.isEmpty())&&Puerto_orion.isEmpty()&&Puerto_crateDB.isEmpty()){
            Dia_ip_servidor.setError("Campo vacío");
            Dia_DNS_ser.setError("Campo vacío");
            Dia_Puerto_orion.setError("Campo vacío");
            Dia_Puerto_crateDB.setError("Campo vacío");
            return false;
        }else{
            if(ip_servidor.isEmpty()){
                if (DNS_ser.isEmpty()){
                    Dia_DNS_ser.setError("Campo vacío");
                    return false;
                }
            }
            if (DNS_ser.isEmpty()){
                if(ip_servidor.isEmpty()){
                    Dia_ip_servidor.setError("Campo vacío");
                    return false;
                }
            }
            if (Puerto_orion.isEmpty()){
                Dia_Puerto_orion.setError("Campo vacío");
                return false;
            }
            if (Puerto_crateDB.isEmpty()){
                Dia_Puerto_crateDB.setError("Campo vacío");
                return false;
            }
            else {
                if(validacampos_indiv()){
                    return true;
                }else
                {
                    return false;
                }
            }
        }
    }

    private boolean validacampos_indiv() {
        validacampos_individuales  individuales=new validacampos_individuales();
        if (!ip_servidor.isEmpty()){
            if (individuales.validar_ip(ip_servidor,Dia_ip_servidor)){
                if (individuales.validar_puerto(Puerto_orion,Dia_Puerto_orion)){
                    if (individuales.validar_puerto(Puerto_crateDB,Dia_Puerto_crateDB)){
                        if(!DNS_ser.equals("")){
                            individuales.validar_dns(DNS_ser,Dia_DNS_ser);
                        }
                        objeServi=new Obje_servi();
                        cargar_obj_servi();
                        return true;
                    }else
                    {
                        return false;
                    }
                }else {
                    return false;
                }
            }else{
                return false;
            }
        }else{
            if (individuales.validar_dns(DNS_ser,Dia_DNS_ser)){
                if (individuales.validar_puerto(Puerto_orion,Dia_Puerto_orion)){
                    if (individuales.validar_puerto(Puerto_crateDB,Dia_Puerto_crateDB)){
                        objeServi=new Obje_servi();
                        cargar_obj_servi();
                        return true;
                    }else
                    {
                        return false;
                    }
                }else {
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    private void cargar_obj_servi() {
        objeServi.setIp_servidor(ip_servidor);
        objeServi.setDNS_ser(DNS_ser);
        objeServi.setPuerto_orion(Puerto_orion);
        objeServi.setPuerto_crateDB(Puerto_crateDB);
        setObjeServi(objeServi);
    }

    public Obje_servi getObjeServi() {
        return objeServi;
    }

    public void setObjeServi(Obje_servi objeServi) {
        this.objeServi = objeServi;
    }
}
