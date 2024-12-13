/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainclasses;

import java.io.Serializable;
import java.sql.Date;



public class Comentario implements Serializable {
    
    private int idComentario;
    private int idPublicacion; 
    private int idUsuario;
    private String contenido;
    private Date fecha_comentario;
    
    // CONSTRUCTOR PARA LECTURA

    public Comentario(int idComentario, int idPublicacion, int idUsuario, String contenido, Date fecha_comentario) {
        this.idComentario = idComentario;
        this.idPublicacion = idPublicacion;
        this.idUsuario = idUsuario;
        this.contenido = contenido;
        this.fecha_comentario = fecha_comentario;
    }
    
    //CONSTRUCTOR PARA INSERCION DE DATOS

    public Comentario(int idPublicacion, int idUsuario, String contenido) {
        this.idPublicacion = idPublicacion;
        this.idUsuario = idUsuario;
        this.contenido = contenido;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }

    @Override
    public String toString() {
        return "Comentario{" + "idComentario=" + idComentario + ", idPublicacion=" + idPublicacion + ", idUsuario=" + idUsuario + ", contenido=" + contenido + ", fecha_comentario=" + fecha_comentario + '}';
    }
    
    
}
