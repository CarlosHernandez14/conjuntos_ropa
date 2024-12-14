/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mycompany.domainclasses.Comentario;
import com.mycompany.domainclasses.Prenda;
import com.mycompany.domainclasses.Publicacion;
import com.mycompany.domainclasses.Reaccion;
import com.mycompany.domainclasses.RolUsuario;
import com.mycompany.domainclasses.TipoReaccion;
import com.mycompany.domainclasses.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WebServiceManager {

    public static final String URL = "http://localhost/WS_ConjuntosRopa/";

    // Funcion para iniciar sesion
    public static Usuario iniciarSesion(String correo, String contrasena) {
        try {
            String url = URL + "usuarios.php?" + "correo=" + correo + "&contrasena=" + contrasena;

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONObject usuarioJson = (JSONObject) resultJson.get("data");

            int idUsuario = Integer.parseInt(usuarioJson.get("idUsuario").toString());
            String nombre = usuarioJson.get("nombre").toString();
            String correoUsuario = usuarioJson.get("correo").toString();
            String contrasenaUsuario = usuarioJson.get("contrasena").toString();
            boolean bloqueado = usuarioJson.get("bloqueado").toString().equals("1");

            String rolString = usuarioJson.get("rol").toString();
            RolUsuario rol;

            try {
                rol = RolUsuario.valueOf(rolString);
            } catch (IllegalArgumentException ex) {
                throw new Exception("Error al obtener el rol del usuario");
            }

            Usuario usuario = new Usuario(idUsuario, nombre, correoUsuario, contrasenaUsuario, rol, bloqueado);

            return usuario;

        } catch (IOException ex) {
            System.out.println("Error al iniciar sesion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al iniciar sesion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al iniciar sesion: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para obtener todos los usuarios
    public static List<Usuario> obtenerUsuarios() {
        try {
            String url = URL + "usuarios.php";

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray usuariosJson = (JSONArray) resultJson.get("data");

            List<Usuario> usuarios = new ArrayList<>();

            for (Object usuarioObject : usuariosJson) {
                JSONObject usuarioJson = (JSONObject) usuarioObject;

                int idUsuario = Integer.parseInt(usuarioJson.get("idUsuario").toString());
                String nombre = usuarioJson.get("nombre").toString();
                String correo = usuarioJson.get("correo").toString();
                String contrasena = usuarioJson.get("contrasena").toString();
                boolean bloqueado = usuarioJson.get("bloqueado").toString().equals("1");

                String rolString = usuarioJson.get("rol").toString();
                RolUsuario rol;

                try {
                    rol = RolUsuario.valueOf(rolString);
                } catch (IllegalArgumentException ex) {
                    throw new Exception("Error al obtener el rol del usuario");
                }

                Usuario usuario = new Usuario(idUsuario, nombre, correo, contrasena, rol, bloqueado);
                usuarios.add(usuario);
            }

            return usuarios;

        } catch (IOException ex) {
            System.out.println("Error al obtener usuarios: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener usuarios: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener usuarios: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para guardar un usuario
    public static int guardarUsuario(Usuario usuario) {
        String url = URL + "usuarios.php";
        try {

            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put("nombre", usuario.getNombre());
            usuarioJson.put("correo", usuario.getCorreo());
            usuarioJson.put("contrasena", usuario.getContrasena());
            usuarioJson.put("rol", usuario.getRol().toString());
            usuarioJson.put("bloqueado", usuario.isBloqueado());

            String response = Request.Post(url)
                    .bodyString(usuarioJson.toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idUsuario = Integer.parseInt(resultJson.get("data").toString());

            return idUsuario;

        } catch (IOException ex) {
            System.out.println("Error al guardar usuario: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al guardar usuario: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al guardar usuario: " + ex.getMessage());
        }
        return -1;
    }

    // Funcion para actualizar un usuario
    public static int actualizarUsuario(Usuario usuario) {
        String url = URL + "usuarios.php";
        try {

            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put("idUsuario", usuario.getIdUsuario());
            if (usuario.getNombre() != null) {
                usuarioJson.put("nombre", usuario.getNombre());
            }
            if (usuario.getCorreo() != null) {
                usuarioJson.put("correo", usuario.getCorreo());
            }
            if (usuario.getContrasena() != null) {
                usuarioJson.put("contrasena", usuario.getContrasena());
            }
            if (usuario.getRol() != null) {
                usuarioJson.put("rol", usuario.getRol().toString());
            }
            usuarioJson.put("bloqueado", usuario.isBloqueado());

            String response = Request.Put(url)
                    .bodyString(usuarioJson.toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idUsuario = Integer.parseInt(resultJson.get("data").toString());

            return idUsuario;

        } catch (IOException ex) {
            System.out.println("Error al actualizar usuario: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al actualizar usuario: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al actualizar usuario: " + ex.getMessage());
        }
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Funciones para obtener los comentarios

    // Funcion para obtener todos los comentarios
    public static List<Comentario> obtenerComentarios() {
        try {
            String url = URL + "comentarios.php";

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray comentariosJson = (JSONArray) resultJson.get("data");

            List<Comentario> comentarios = new ArrayList<>();

            for (Object comentarioObject : comentariosJson) {
                JSONObject comentarioJson = (JSONObject) comentarioObject;

                int idComentario = Integer.parseInt(comentarioJson.get("idComentario").toString());
                String contenido = comentarioJson.get("contenido").toString();
                int idUsuario = Integer.parseInt(comentarioJson.get("idUsuario").toString());
                int idPublicacion = Integer.parseInt(comentarioJson.get("idPublicacion").toString());
                Date fecha = Date.valueOf(comentarioJson.get("fecha").toString());

                Comentario comentario = new Comentario(idComentario, idPublicacion, idUsuario, contenido, fecha);

                comentarios.add(comentario);
            }

            return comentarios;

        } catch (IOException ex) {
            System.out.println("Error al obtener comentarios: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener comentarios: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener comentarios: " + ex.getMessage());
        }
        return null;
    }

    // Obtener comentarios por id de publicacion
    public static List<Comentario> obtenerComentariosPorPublicacion(int idPublicacion) {
        try {
            String url = URL + "comentarios.php?" + "idPublicacion=" + idPublicacion;

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray comentariosJson = (JSONArray) resultJson.get("data");

            List<Comentario> comentarios = new ArrayList<>();

            for (Object comentarioObject : comentariosJson) {
                JSONObject comentarioJson = (JSONObject) comentarioObject;

                int idComentario = Integer.parseInt(comentarioJson.get("idComentario").toString());
                String contenido = comentarioJson.get("contenido").toString();
                int idUsuario = Integer.parseInt(comentarioJson.get("idUsuario").toString());
                Date fecha = Date.valueOf(comentarioJson.get("fecha").toString());

                Comentario comentario = new Comentario(idComentario, idPublicacion, idUsuario, contenido, fecha);

                comentarios.add(comentario);
            }

            return comentarios;

        } catch (IOException ex) {
            System.out.println("Error al obtener comentarios por publicacion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener comentarios por publicacion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener comentarios por publicacion: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para obtener los comentarios de un usuario
    public static List<Comentario> obtenerComentariosPorUsuario(int idUsuario) {
        try {
            String url = URL + "comentarios.php?" + "idUsuario=" + idUsuario;

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray comentariosJson = (JSONArray) resultJson.get("data");

            List<Comentario> comentarios = new ArrayList<>();

            for (Object comentarioObject : comentariosJson) {
                JSONObject comentarioJson = (JSONObject) comentarioObject;

                int idComentario = Integer.parseInt(comentarioJson.get("idComentario").toString());
                String contenido = comentarioJson.get("contenido").toString();
                int idPublicacion = Integer.parseInt(comentarioJson.get("idPublicacion").toString());
                Date fecha = Date.valueOf(comentarioJson.get("fecha").toString());

                Comentario comentario = new Comentario(idComentario, idPublicacion, idUsuario, contenido, fecha);

                comentarios.add(comentario);
            }

            return comentarios;

        } catch (IOException ex) {
            System.out.println("Error al obtener comentarios por usuario: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener comentarios por usuario: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener comentarios por usuario: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para guardar un comentario
    public static int guardarComentario(Comentario comentario) {
        String url = URL + "comentarios.php";
        try {

            JSONObject comentarioJson = new JSONObject();
            comentarioJson.put("idPublicacion", comentario.getIdPublicacion());
            comentarioJson.put("idUsuario", comentario.getIdUsuario());
            comentarioJson.put("contenido", comentario.getContenido());

            String response = Request.Post(url)
                    .bodyString(comentarioJson.toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idComentario = Integer.parseInt(resultJson.get("data").toString());

            return idComentario;

        } catch (IOException ex) {
            System.out.println("Error al guardar comentario: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al guardar comentario: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al guardar comentario: " + ex.getMessage());
        }
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // Funciones para manejar las reacciones de las publicaciones

    // Funcion para obtener las reacciones de una publicacion
    public static List<Reaccion> obtenerReaccionesPorPublicacion(int idPublicacion) {
        try {
            String url = URL + "reacciones.php?" + "idPublicacion=" + idPublicacion;

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray reaccionesJson = (JSONArray) resultJson.get("data");

            List<Reaccion> reacciones = new ArrayList<>();

            for (Object reaccionObject : reaccionesJson) {
                JSONObject reaccionJson = (JSONObject) reaccionObject;

                int idReaccion = Integer.parseInt(reaccionJson.get("idReaccion").toString());
                int idUsuario = Integer.parseInt(reaccionJson.get("idUsuario").toString());

                String tipoReaccionString = reaccionJson.get("tipo").toString();
                TipoReaccion tipo;
                try {
                    tipo = TipoReaccion.valueOf(tipoReaccionString);
                } catch (IllegalArgumentException ex) {
                    throw new Exception("Error al obtener el tipo de reaccion");
                }

                Date fecha = Date.valueOf(reaccionJson.get("fecha").toString());

                Reaccion reaccion = new Reaccion(idReaccion, idPublicacion, idUsuario, tipo, fecha);

                reacciones.add(reaccion);
            }

            return reacciones;

        } catch (IOException ex) {
            System.out.println("Error al obtener reacciones por publicacion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener reacciones por publicacion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener reacciones por publicacion: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para obtener las reacciones de un usuario
    public static List<Reaccion> obtenerReaccionesPorUsuario(int idUsuario) {
        try {
            String url = URL + "reacciones.php?" + "idUsuario=" + idUsuario;

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray reaccionesJson = (JSONArray) resultJson.get("data");

            List<Reaccion> reacciones = new ArrayList<>();

            for (Object reaccionObject : reaccionesJson) {
                JSONObject reaccionJson = (JSONObject) reaccionObject;

                int idReaccion = Integer.parseInt(reaccionJson.get("idReaccion").toString());
                int idPublicacion = Integer.parseInt(reaccionJson.get("idPublicacion").toString());

                String tipoReaccionString = reaccionJson.get("tipo").toString();
                TipoReaccion tipo;
                try {
                    tipo = TipoReaccion.valueOf(tipoReaccionString);
                } catch (IllegalArgumentException ex) {
                    throw new Exception("Error al obtener el tipo de reaccion");
                }

                Date fecha = Date.valueOf(reaccionJson.get("fecha").toString());

                Reaccion reaccion = new Reaccion(idReaccion, idPublicacion, idUsuario, tipo, fecha);

                reacciones.add(reaccion);
            }

            return reacciones;

        } catch (IOException ex) {
            System.out.println("Error al obtener reacciones por usuario: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener reacciones por usuario: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener reacciones por usuario: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para guardar una reaccion
    public static int guardarReaccion(Reaccion reaccion) {
        String url = URL + "reacciones.php";
        try {

            JSONObject reaccionJson = new JSONObject();
            reaccionJson.put("idPublicacion", reaccion.getIdPublicacion());
            reaccionJson.put("idUsuario", reaccion.getIdUsuario());
            reaccionJson.put("tipo", reaccion.getTipo().toString());

            String response = Request.Post(url)
                    .bodyString(reaccionJson.toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idReaccion = Integer.parseInt(resultJson.get("data").toString());

            return idReaccion;

        } catch (IOException ex) {
            System.out.println("Error al guardar reaccion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al guardar reaccion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al guardar reaccion: " + ex.getMessage());
        }
        return -1;
    }

    // Funcion para eliminar una reaccion
    public static int eliminarReaccion(int idReaccion) {
        String url = URL + "reacciones.php?idReaccion=" + idReaccion;
        try {

            String response = Request.Delete(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idReaccionEliminada = Integer.parseInt(resultJson.get("data").toString());

            return idReaccionEliminada;

        } catch (IOException ex) {
            System.out.println("Error al eliminar reaccion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al eliminar reaccion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al eliminar reaccion: " + ex.getMessage());
        }
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // Funciones para manejar las prendas

    // Funcion para obtener todas las prendas de una publicacion
    public static List<Prenda> obtenerPrendasPorPublicacion(int idPublicacion) {
        try {
            String url = URL + "prendas.php?" + "idPublicacion=" + idPublicacion;

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray prendasJson = (JSONArray) resultJson.get("data");

            List<Prenda> prendas = new ArrayList<>();

            for (Object prendaObject : prendasJson) {
                JSONObject prendaJson = (JSONObject) prendaObject;

                int idPrenda = Integer.parseInt(prendaJson.get("idPrenda").toString());
                String nombre = prendaJson.get("nombre").toString();
                String tipo = prendaJson.get("tipo").toString();

                byte[] fotoBytes = Base64.getDecoder().decode(prendaJson.get("foto").toString());

                Prenda prenda = new Prenda(idPrenda, idPublicacion, nombre, tipo, fotoBytes);

                prendas.add(prenda);
            }

            return prendas;

        } catch (IOException ex) {
            System.out.println("Error al obtener prendas por publicacion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener prendas por publicacion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener prendas por publicacion: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para guardar una prenda
    public static int guardarPrenda(Prenda prenda) {
        String url = URL + "prendas.php";
        try {

            JSONObject prendaJson = new JSONObject();
            prendaJson.put("idPublicacion", prenda.getIdPublicacion());
            prendaJson.put("nombre", prenda.getNombre());
            prendaJson.put("tipo", prenda.getTipo());

            if (prenda.getFoto() != null) {
                prendaJson.put("foto", Base64.getEncoder().encodeToString(prenda.getFoto()));
            }

            String response = Request.Post(url)
                    .bodyString(prendaJson.toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idPrenda = Integer.parseInt(resultJson.get("data").toString());

            return idPrenda;

        } catch (IOException ex) {
            System.out.println("Error al guardar prenda: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al guardar prenda: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al guardar prenda: " + ex.getMessage());
        }
        return -1;
    }

    // Funcion para eliminar una prenda
    public static int eliminarPrenda(int idPrenda) {
        String url = URL + "prendas.php?idPrenda=" + idPrenda;
        try {

            String response = Request.Delete(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idPrendaEliminada = Integer.parseInt(resultJson.get("data").toString());

            return idPrendaEliminada;

        } catch (IOException ex) {
            System.out.println("Error al eliminar prenda: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al eliminar prenda: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al eliminar prenda: " + ex.getMessage());
        }
        return -1;
    }

    // Funcion para actualizar una prenda
    public static int actualizarPrenda(Prenda prenda) {
        String url = URL + "prendas.php";
        try {

            JSONObject prendaJson = new JSONObject();
            prendaJson.put("idPrenda", prenda.getIdPrenda());
            if (prenda.getNombre() != null) {
                prendaJson.put("nombre", prenda.getNombre());
            }
            if (prenda.getTipo() != null) {
                prendaJson.put("tipo", prenda.getTipo());
            }
            if (prenda.getFoto() != null) {
                prendaJson.put("foto", Base64.getEncoder().encodeToString(prenda.getFoto()));
            }

            String response = Request.Put(url)
                    .bodyString(prendaJson.toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idPrenda = Integer.parseInt(resultJson.get("data").toString());

            return idPrenda;

        } catch (IOException ex) {
            System.out.println("Error al actualizar prenda: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al actualizar prenda: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al actualizar prenda: " + ex.getMessage());
        }
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // Funciones para manejar las publicaciones

    // Funcion para obtener todas las publicaciones
    public static List<Publicacion> obtenerPublicaciones() {
        try {
            String url = URL + "publicaciones.php";

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray publicacionesJson = (JSONArray) resultJson.get("data");

            List<Publicacion> publicaciones = new ArrayList<>();

            for (Object publicacionObject : publicacionesJson) {
                JSONObject publicacionJson = (JSONObject) publicacionObject;

                int idPublicacion = Integer.parseInt(publicacionJson.get("idPublicacion").toString());
                String titulo = publicacionJson.get("titulo").toString();
                String descripcion = publicacionJson.get("descripcion").toString();
                Date fecha = Date.valueOf(publicacionJson.get("fecha_creacion").toString());
                int idUsuario = Integer.parseInt(publicacionJson.get("idUsuario").toString());

                byte[] fotoBytes = Base64.getDecoder().decode(publicacionJson.get("foto").toString());

                int idReferencia = Integer.parseInt(publicacionJson.get("idReferencia").toString());

                // Consultamos las prendas de la publicacion
                ArrayList<Prenda> prendas = (ArrayList<Prenda>) obtenerPrendasPorPublicacion(idPublicacion);

                // Consultamos los comentarios de la publicacion
                ArrayList<Comentario> comentarios = (ArrayList<Comentario>) obtenerComentariosPorPublicacion(
                        idPublicacion);

                // Consultamos las reacciones de la publicacion
                ArrayList<Reaccion> reacciones = (ArrayList<Reaccion>) obtenerReaccionesPorPublicacion(idPublicacion);

                Publicacion publicacion = new Publicacion(idPublicacion, idUsuario, titulo, descripcion, fotoBytes,
                        idReferencia, fecha, prendas, comentarios, reacciones);

                publicaciones.add(publicacion);
            }

            return publicaciones;

        } catch (IOException ex) {
            System.out.println("Error al obtener publicaciones: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener publicaciones: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener publicaciones: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para obtener publicaciones por id de usuario
    public static List<Publicacion> obtenerPublicacionesPorUsuario(int idUsuario) {
        try {
            String url = URL + "publicaciones.php?" + "idUsuario=" + idUsuario;

            String response = Request.Get(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray publicacionesJson = (JSONArray) resultJson.get("data");

            List<Publicacion> publicaciones = new ArrayList<>();

            for (Object publicacionObject : publicacionesJson) {
                JSONObject publicacionJson = (JSONObject) publicacionObject;

                int idPublicacion = Integer.parseInt(publicacionJson.get("idPublicacion").toString());
                String titulo = publicacionJson.get("titulo").toString();
                String descripcion = publicacionJson.get("descripcion").toString();
                Date fecha = Date.valueOf(publicacionJson.get("fecha_creacion").toString());

                byte[] fotoBytes = Base64.getDecoder().decode(publicacionJson.get("foto").toString());

                int idReferencia = Integer.parseInt(publicacionJson.get("idReferencia").toString());

                // Consultamos las prendas de la publicacion
                ArrayList<Prenda> prendas = (ArrayList<Prenda>) obtenerPrendasPorPublicacion(idPublicacion);

                // Consultamos los comentarios de la publicacion
                ArrayList<Comentario> comentarios = (ArrayList<Comentario>) obtenerComentariosPorPublicacion(
                        idPublicacion);

                // Consultamos las reacciones de la publicacion
                ArrayList<Reaccion> reacciones = (ArrayList<Reaccion>) obtenerReaccionesPorPublicacion(idPublicacion);

                Publicacion publicacion = new Publicacion(idPublicacion, idUsuario, titulo, descripcion, fotoBytes,
                        idReferencia, fecha, prendas, comentarios, reacciones);

                publicaciones.add(publicacion);
            }

            return publicaciones;

        } catch (IOException ex) {
            System.out.println("Error al obtener publicaciones por usuario: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener publicaciones por usuario: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener publicaciones por usuario: " + ex.getMessage());
        }
        return null;
    }

    // Funcion para guardar una publicacion
    public static int guardarPublicacion(Publicacion publicacion) {
        String url = URL + "publicaciones.php";
        try {

            JSONObject publicacionJson = new JSONObject();
            publicacionJson.put("idUsuario", publicacion.getIdUsuario());
            publicacionJson.put("titulo", publicacion.getTitulo());
            publicacionJson.put("descripcion", publicacion.getDescripcion());
            publicacionJson.put("idReferencia", publicacion.getIdReferencia());

            if (publicacion.getFoto() != null) {
                publicacionJson.put("foto", Base64.getEncoder().encodeToString(publicacion.getFoto()));
            }

            String response = Request.Post(url)
                    .bodyString(publicacionJson.toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idPublicacion = Integer.parseInt(resultJson.get("data").toString());

            return idPublicacion;

        } catch (IOException ex) {
            System.out.println("Error al guardar publicacion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al guardar publicacion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al guardar publicacion: " + ex.getMessage());
        }
        return -1;
    }

    // Funcion para actualizar una publicacion
    public static int actualizarPublicacion(Publicacion publicacion) {
        String url = URL + "publicaciones.php";
        try {

            JSONObject publicacionJson = new JSONObject();
            publicacionJson.put("idPublicacion", publicacion.getIdPublicacion());
            if (publicacion.getTitulo() != null) {
                publicacionJson.put("titulo", publicacion.getTitulo());
            }
            if (publicacion.getDescripcion() != null) {
                publicacionJson.put("descripcion", publicacion.getDescripcion());
            }
            if (publicacion.getFoto() != null) {
                publicacionJson.put("foto", Base64.getEncoder().encodeToString(publicacion.getFoto()));
            }

            String response = Request.Put(url)
                    .bodyString(publicacionJson.toJSONString(), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idPublicacion = Integer.parseInt(resultJson.get("data").toString());

            return idPublicacion;

        } catch (IOException ex) {
            System.out.println("Error al actualizar publicacion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al actualizar publicacion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al actualizar publicacion: " + ex.getMessage());
        }
        return -1;
    }

    // Funcion para eliminar una publicacion
    public static int eliminarPublicacion(int idPublicacion) {
        String url = URL + "publicaciones.php?idPublicacion=" + idPublicacion;
        try {

            String response = Request.Delete(url).execute().returnContent().asString();

            JSONParser parser = new JSONParser();

            JSONObject resultJson = (JSONObject) parser.parse(response);

            if (!Boolean.parseBoolean(resultJson.get("OK").toString())) {
                String errorMessage = (String) resultJson.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            int idPublicacionEliminada = Integer.parseInt(resultJson.get("data").toString());

            return idPublicacionEliminada;

        } catch (IOException ex) {
            System.out.println("Error al eliminar publicacion: " + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al eliminar publicacion: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al eliminar publicacion: " + ex.getMessage());
        }
        return -1;
    }

}
