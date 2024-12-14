/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainclasses;

import java.io.Serializable;


public class Prenda implements Serializable {
    
    private int idPrenda;
    private int idPublicacion; 
    private String nombre;
    private String tipo;
    private byte[] foto;
    
    // CONSTRUCTOR PARA LECTURA 

    public Prenda(int idPrenda, int idPublicacion, String nombre, String tipo, byte[] foto) {
        this.idPrenda = idPrenda;
        this.idPublicacion = idPublicacion;
        this.nombre = nombre;
        this.tipo = tipo;
        this.foto = foto;
    }
    
    //CONSTRUCTOR PARA CREACION

    public Prenda(int idPublicacion, String nombre, String tipo) {
        this.idPublicacion = idPublicacion;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Prenda{" + "idPrenda=" + idPrenda + ", idPublicacion=" + idPublicacion + ", nombre=" + nombre + ", tipo=" + tipo + ", foto=" + foto + '}';
    }
    
    
}
