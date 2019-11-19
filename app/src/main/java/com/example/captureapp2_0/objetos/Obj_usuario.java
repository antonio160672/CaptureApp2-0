package com.example.captureapp2_0.objetos;

import java.io.Serializable;

public class Obj_usuario implements Serializable {
    /*String nombre,apellido_pater,apellido_mater,correo,
            contrasena,calle,colonia,cp,idTipoUsuario,idCompania,idEstatus,idEstado;*/
    String nombre,apellido_pater,apellido_mater,correo,
            contrasena,calle,colonia,cp,idEstado;

    public Obj_usuario() {
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
