/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainclasses;

import java.io.Serializable;
import java.sql.Date;


public class Reaccion implements Serializable {
    private int idReaccion;
    private int idPublicacion;
    private int idUsuario;
    private TipoReaccion tipo;
    private Date fecha_creacion;
    
    // CONSTRUCTOR PARA LECTURA DE DATOS

    public Reaccion(int idReaccion, int idPublicacion, int idUsuario, TipoReaccion tipo, Date fecha_creacion) {
        this.idReaccion = idReaccion;
        this.idPublicacion = idPublicacion;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.fecha_creacion = fecha_creacion;
    }
    
    // CONSTRUCTOR PARA CREACION DE DATOS

    public Reaccion(int idPublicacion, int idUsuario, TipoReaccion tipo) {
        this.idPublicacion = idPublicacion;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
    }

    public int getIdReaccion() {
        return idReaccion;
    }

    public void setIdReaccion(int idReaccion) {
        this.idReaccion = idReaccion;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoReaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoReaccion tipo) {
        this.tipo = tipo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        return "Reaccion{" + "idReaccion=" + idReaccion + ", idPublicacion=" + idPublicacion + ", idUsuario=" + idUsuario + ", tipo=" + tipo + ", fecha_creacion=" + fecha_creacion + '}';
    }
    
    
}
