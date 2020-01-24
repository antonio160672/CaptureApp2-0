package com.example.captureapp2_0.Modelo.Modelo.objetos;

import android.database.Cursor;
import com.example.captureapp2_0.Modelo.Modelo.DB_lite.Sqlite_DB_manejo;
import java.io.Serializable;

public class Obj_usuario implements Serializable {
    /*String nombre,apellido_pater,apellido_mater,correo,
            contrasena,calle,colonia,cp,idTipoUsuario,idCompania,idEstatus,idEstado;*/
    public Sqlite_DB_manejo sqLite=null;
    public Cursor cursor=null;

    String id_usua,nombre,apellido_pater,apellido_mater,correo,
            contrasena,calle,colonia,cp,fecha_nac,idEstado,municipio,respuesta1,respuesta2;
    int pregunta1, pregunta2;

    public Obj_usuario() {
    }


    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public int getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(int pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public int getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(int pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getId_usua() {
        return id_usua;
    }

    public void setId_usua(String id_usua) {
        this.id_usua = id_usua;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_pater() {
        return apellido_pater;
    }

    public void setApellido_pater(String apellido_pater) {
        this.apellido_pater = apellido_pater;
    }

    public String getApellido_mater() {
        return apellido_mater;
    }

    public void setApellido_mater(String apellido_mater) {
        this.apellido_mater = apellido_mater;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }
}
