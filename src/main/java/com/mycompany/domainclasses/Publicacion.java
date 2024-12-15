/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainclasses;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class Publicacion {

    private int idPublicacion;
    private int idUsuario;
    private String titulo;
    private String descripcion;
    private byte[] foto;
    private int idReferencia;
    private Date fecha_creacion;
    private ArrayList<Prenda> prendas;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Reaccion> reacciones;

    // CONSTRUCTOR PARA LECTURA DE DATOS;
    public Publicacion(int idPublicacion, int idUsuario, String titulo, String descripcion, byte[] foto, 
            int idReferencia, Date fecha_creacion, ArrayList<Prenda> prendas, ArrayList<Comentario> comentarios, ArrayList<Reaccion> reacciones) {
        this.idPublicacion = idPublicacion;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.idReferencia = idReferencia;
        this.fecha_creacion = fecha_creacion;
        this.prendas = prendas;
        this.comentarios = comentarios;
        this.reacciones = reacciones;
    }

    // CONSTRUCTOR PARA CREACION
    public Publicacion(int idUsuario, String titulo, String descripcion) {
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public ArrayList<Prenda> getPrendas() {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas) {
        this.prendas = prendas;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(ArrayList<Reaccion> reacciones) {
        this.reacciones = reacciones;
    }
    
    @Override
    public String toString() {
        return "Publicacion{" + "idPublicacion=" + idPublicacion + ", idUsuario=" + idUsuario + ", titulo=" + titulo + ", descripcion=" + descripcion + ", foto=" + foto + ", idReferencia=" + idReferencia + ", fecha_creacion=" + fecha_creacion + '}';
    }

}
