package com.example.captureapp2_0.Modelo.Modelo.objetos;

public class Obj_json_wifi_bluetooth {
    private String Json_wifi="",Json_bluetooth="";
    private Obj_wifi obj_wifi;
    private Obj_bluetooth obj_bluetooth;

    public void primer_registro_json_wifi(){
        if (obj_wifi!=null){
            Json_wifi="{\"id\":\""+obj_wifi.getId_dip()+"\",\"type\":\"historial_wifi\",\"Id_tipoDispositivo\":{\"value\":"+obj_wifi.getId_tip_dispo()+",\"type\":\"Number\"},\"IdDispositivo\":{\"value\":1,\"type\":\"Number\"},\"Idusuario\":{\"value\":\""+obj_wifi.getId_user()+
                    "\",\"type\":\"Text\"},\"Nombre_dispo\":{\"value\":\""+obj_wifi.getNombre_dispos()+"\",\"type\":\"Text\"},\"RSSI\":{\"value\":\""+obj_wifi.getRSSI()+"\",\"type\":\"Number\"},\"fechacaptura\":{\"value\":\""+
                    (obj_wifi.getFecha_cap()+"T"+obj_wifi.getHora())+"\",\"type\":\"DateTime\"},\"macaddres\":{\"value\":\""+obj_wifi.getMacaddres()+"\",\"type\":\"Text\"}}";
        }

    }

    public void actualizacion_entidad_wifi(){
        if (obj_wifi!=null){
            Json_wifi="{\"RSSI\":{\"type\":\"Number\",\"value\":\""+obj_wifi.getRSSI()+"\"}," +
                    "\"fechacaptura\":{\"type\":\"DateTime\",\"value\":\""+(obj_wifi.getFecha_cap()+"T"+obj_wifi.getHora())+"\"}}";
        }
    }

    public void primer_registro_json_bluethoo(){
        if (obj_bluetooth!=null){
            Json_bluetooth="{\"id\":\""+obj_bluetooth.getId_dip()+"\",\"type\":\"historial_bluetooth\",\"fechacaptura\":{\"value\":\""+(obj_bluetooth.getFecha_cap()+"T"+obj_bluetooth.getHora())+"\",\"type\":\"DateTime\"},\"" +
                    "macaddres\":{\"value\":\""+obj_bluetooth.getBluetoothAddress()+"\",\"type\":\"Text\"},\"marca\":{\"value\":\"stimote\",\"type\":\"Text\"},\"RSSI\":{\"value\":"+obj_bluetooth.getRSSI()+",\"type\":\"Number\"}," +
                    "\"TX\":{\"value\":"+obj_bluetooth.getTX()+",\"type\":\"Number\"},\"Major\":{\"value\":\""+obj_bluetooth.getMAJOR()+"\",\"type\":\"Text\"},\"UUID\":{\"value\":\""+obj_bluetooth.getUUID()+"\",\"type\":\"Text\"},\"" +
                    "Id_tipoDispositivo\":{\"value\":1,\"type\":\"Number\"},\"Idusuario\":{\"value\":\""+obj_bluetooth.getId_user()+"\",\"type\":\"Text\"}}";
        }

    }

    public void actualizacion_entidad_bluetooth(){
        if (obj_bluetooth!=null){
            Json_bluetooth="{\"RSSI\":{\"type\":\"Number\",\"value\":"+obj_bluetooth.getRSSI()+"},\"TX\":{\"type\":\"Number\",\"value\":"+obj_bluetooth.getTX()+"}," +
                            "\"Major\":{\"type\":\"Text\",\"value\":\""+obj_bluetooth.getMAJOR()+"\"}," +
                            "\"fechacaptura\":{\"type\":\"DateTime\",\"value\":\""+(obj_bluetooth.getFecha_cap()+"T"+obj_bluetooth.getHora())+"\"}}";
        }
    }

    public String getJson_wifi() {
        return Json_wifi;
    }

    public String getJson_bluetooth() {
        return Json_bluetooth;
    }

    public void setObj_wifi(Obj_wifi obj_wifi) {
        this.obj_wifi = obj_wifi;
    }

    public void setObj_bluetooth(Obj_bluetooth obj_bluetooth) {
        this.obj_bluetooth = obj_bluetooth;
    }
}
