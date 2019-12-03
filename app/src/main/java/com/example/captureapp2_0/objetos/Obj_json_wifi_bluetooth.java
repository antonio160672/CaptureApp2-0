package com.example.captureapp2_0.objetos;

public class Obj_json_wifi_bluetooth {
    String Json="";
    Obj_wifi obj_wifi;

    public Obj_json_wifi_bluetooth() {
    }
   public void primer_registro_json(){
        if (obj_wifi!=null){
            Json="{\"id\":\""+obj_wifi.getId_dip()+"\",\"type\":\"historial_wifi\",\"Id_tipoDispositivo\":{\"value\":"+obj_wifi.getId_tip_dispo()+",\"type\":\"Number\"},\"IdDispositivo\":{\"value\":1,\"type\":\"Number\"},\"Idusuario\":{\"value\":"+obj_wifi.getId_user()+
                    ",\"type\":\"Number\"},\"Nombre_dispo\":{\"value\":\""+obj_wifi.getNombre_dispos()+"\",\"type\":\"Text\"},\"RSSI\":{\"value\":\""+obj_wifi.getRSSI()+"\",\"type\":\"Number\"},\"fechacaptura\":{\"value\":\""+
                    (obj_wifi.getFecha_cap()+"T"+obj_wifi.getHora())+"\",\"type\":\"DateTime\"},\"macaddres\":{\"value\":\""+obj_wifi.getMacaddres()+"\",\"type\":\"Text\"}}";
        }

    }
    public void actualizacion_entidad(){
        if (obj_wifi!=null){
            Json="{\"RSSI\":{\"type\":\"Number\",\"value\":\""+obj_wifi.getRSSI()+"\"}," +
                    "\"fechacaptura\":{\"type\":\"DateTime\",\"value\":\""+(obj_wifi.getFecha_cap()+"T"+obj_wifi.getHora())+"\"}}";
        }
    }

    public String getJson() {
        return Json;
    }

    public Obj_wifi getObj_wifi() {
        return obj_wifi;
    }

    public void setObj_wifi(Obj_wifi obj_wifi) {
        this.obj_wifi = obj_wifi;
    }
}
