/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainclasses;

import java.io.Serializable;

 

public class Usuario implements Serializable {
    
    private int idUsuario;
    private String nombre;
    private String correo; 
    private String contrasena;
    private RolUsuario rol;
    private boolean bloqueado;

    // CONSTRUCTOR DE LECTURA
    public Usuario(int idUsuario, String nombre, String correo, String contrasena, RolUsuario rol, boolean bloqueado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.bloqueado = bloqueado;
    }
    
    // CONSTRUCTOR PARA CREACION
    public Usuario(String nombre, String correo, String contrasena, RolUsuario rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", correo=" + correo + ", contrasena=" + contrasena + ", rol=" + rol + ", bloqueado=" + bloqueado + '}';
    }
    
    
}
