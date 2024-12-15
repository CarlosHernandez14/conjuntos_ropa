/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conjuntos_ropa;

import com.mycompany.dao.WebServiceManager;
import com.mycompany.domainclasses.Comentario;
import com.mycompany.domainclasses.Publicacion;
import com.mycompany.domainclasses.Reaccion;
import com.mycompany.domainclasses.Usuario;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class Pruebas {

    public static void main(String[] args) {

//        ArrayList<Publicacion> pubs = (ArrayList<Publicacion>) WebServiceManager.obtenerPublicacionesPorUsuario(11);
//        System.out.println("PUBLICACIONES");
//        pubs.forEach(System.out::println);
        //ArrayList<Reaccion> reacciones = (ArrayList<Reaccion>) WebServiceManager.obtenerReaccionesPorPublicacion(9);
//
//        System.out.println("REACCIONES");
//        reacciones.forEach(System.out::println);
//        ArrayList<Comentario> comentarios = (ArrayList<Comentario>) WebServiceManager.obtenerComentariosPorPublicacion(9);
//        System.out.println("COMENTARIOS DE 9 PUB");
//        comentarios.forEach(System.out::println);

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) WebServiceManager.obtenerUsuarios();
        System.out.println("USURAIOS");
        usuarios.forEach(System.out::println);
    }

}
