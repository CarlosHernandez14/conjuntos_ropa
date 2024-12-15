<?php

    class DBManager {
        private $db;
        private $host;
        private $usuario;
        private $contrasena;
        private $puerto;

        public function __construct() {
            $this->db = "conjuntos_ropa";
            $this->host = "localhost";
            $this->usuario = "root";
            $this->contrasena = null;
            $this->puerto = 3306;
        }

        public function open(){
            $link = mysqli_connect(
                $this->host, $this->usuario, $this->contrasena, $this->db, $this->puerto
            ) or die("No se pudo abrir la conexion a la base de datos");
            return $link;
        }

        public function close($link){
            mysqli_close($link);
        }

        /////////////////////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE USUARIOS

        // Funcion para iniciar sesion
        public function login($correo, $contrasena) {
            $link = $this->open();
            $query = "SELECT * FROM usuario WHERE correo = '$correo' AND contrasena = SHA1('$contrasena')";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $usuario = mysqli_fetch_assoc($result);
                $this->close($link);

                if (!$usuario) {
                    throw new Exception("Usuario no encontrado");
                }

                return $usuario;
            } else {
                $this->close($link);
                throw new Exception("Error al iniciar sesion");
            }
        }

        // Funcion para obtener todos los usuarios
        public function getUsuarios(){
            $link = $this->open();
            $query = "SELECT * FROM usuario";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $usuarios = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $usuarios[] = $row;
                }
                
                $this->close($link);
                
                return $usuarios;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener los usuarios");
            }
            
            
            $this->close($link);
            return $usuarios;
        }

        // Funcion para obtener un usuario por su id
        public function getUsuarioById($id){
            $link = $this->open();
            $query = "SELECT * FROM usuario WHERE idUsuario = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $usuario = mysqli_fetch_assoc($result);
                $this->close($link);
                return $usuario;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener el usuario");
            }
        }

        // Funcion para guardar un usuario
        public function createUser($nombre, $correo, $contrasena, $rol = null) {
            $link = $this->open();
            $query = "INSERT INTO usuario (nombre, correo, contrasena, rol) VALUES ('$nombre', '$correo', SHA1('$contrasena'), '$rol')";

            if (is_null($rol)) {
                $query = "INSERT INTO usuario (nombre, correo, contrasena) VALUES ('$nombre', '$correo', SHA1('$contrasena'))";
            }

            $result = mysqli_query($link, $query);
            
            if ($result) {
                // Si hay un resultado consultamos el id del usuario creado
                $query = "SELECT * FROM usuario WHERE correo = '$correo' AND contrasena = SHA1('$contrasena')";
                $result = mysqli_query($link, $query);

                if (!$result) {
                    $this->close($link);
                    throw new Exception("Error al obtener al usuario creado");
                }

                $usuario = mysqli_fetch_assoc($result);
                $this->close($link);

                // Retornamos el id del usuario creado
                return $usuario['idUsuario'];
            } else {
                $this->close($link);
                throw new Exception("Error al crear el usuario");
            }
        }

        // Funcion para actualizar un usuario
        public function updateUser($id, $nombre = null, $correo = null, $contrasena = null, $rol = null, $bloqueado = null) {
            $link = $this->open();

            // Obtenemos el usuario actual
            $usuario = $this->getUsuarioById($id);

            if (!$usuario) {
                $this->close($link);
                throw new Exception("El usuario no existe");
            }

            // Si no se envia un valor para un campo, se mantiene el valor actual
            if (is_null($nombre)) {
                $nombre = $usuario['nombre'];
            }

            if (is_null($correo)) {
                $correo = $usuario['correo'];
            }

            if (is_null($contrasena)) {
                $contrasena = $usuario['contrasena'];
            }

            if (is_null($rol)) {
                $rol = $usuario['rol'];
            }

            if (is_null($bloqueado)) {
                $bloqueado = $usuario['bloqueado'];
            } else {
                $bloqueado = $bloqueado ? 1 : 0;
            }

            $query = "UPDATE usuario SET nombre = '$nombre', correo = '$correo', rol = '$rol', bloqueado = $bloqueado WHERE idUsuario = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                // Retornamos el id del usuario actualizado
                return $id;
            } else {
                $this->close($link);
                throw new Exception("Error al actualizar el usuario");
            }
        }

        /////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE CONJUNTOS (Publicacion)

        // Funcion para obtener todos los conjuntos
        public function getPublicaciones(){
            $link = $this->open();
            $query = "SELECT * FROM publicacion";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $conjuntos = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    // Convertimos la imagen blob a base64
                    $imageBlob = $row['foto'];
                    $imageBase64 = base64_encode($imageBlob);
                    $row['foto'] = $imageBase64;
                    $conjuntos[] = $row;
                }
                
                $this->close($link);
                
                return $conjuntos;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener las publicaciones");
            }
        }

        // Funcion para obtener un conjunto por su id
        public function getPublicacionById($id){
            $link = $this->open();
            $query = "SELECT * FROM publicacion WHERE idPublicacion = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $conjunto = mysqli_fetch_assoc($result);
                // Convertimos la imagen blob a base64
                $imageBlob = $conjunto['foto'];
                $imageBase64 = base64_encode($imageBlob);
                $conjunto['foto'] = $imageBase64;
                $this->close($link);
                return $conjunto;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener la publicacion");
            }
        }

        // Funcion para obtener los conjuntos de un usuario
        public function getPublicacionesByUsuario($idUsuario){
            $link = $this->open();
            $query = "SELECT * FROM publicacion WHERE idUsuario = $idUsuario";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $conjuntos = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    // Convertimos la imagen blob a base64
                    $imageBlob = $row['foto'];
                    $imageBase64 = base64_encode($imageBlob);
                    $row['foto'] = $imageBase64;
                    $conjuntos[] = $row;
                }
                
                $this->close($link);
                
                return $conjuntos;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener las publicaciones");
            }
        }

        // Funcion para guardar un conjunto
        public function createPublicacion($titulo, $descripcion, $foto = null, $idUsuario) {
            $link = $this->open();
            
            if (is_null($foto)) {
                $query = "INSERT INTO publicacion (titulo, descripcion, idUsuario) VALUES (?, ?, ?)";
                $stmt = $link->prepare($query);
                $stmt->bind_param("ssi", $titulo, $descripcion, $idUsuario);
            } else {
                $query = "INSERT INTO publicacion (titulo, descripcion, foto, idUsuario) VALUES (?, ?, ?, ?)";
                $stmt = $link->prepare($query);
                $stmt->bind_param("sssi", $titulo, $descripcion, $foto, $idUsuario);
            }
        
            if ($stmt->execute()) {
                // Obtener el ID de la publicación creada
                $idPublicacion = $stmt->insert_id;
                
                $stmt->close();

                $this->close($link);
        
                // Consultar y retornar la publicación creada
                return $idPublicacion;
            } else {
                $stmt->close();
                $this->close($link);
                throw new Exception("Error al crear la publicacion: " . $link->error);
            }
        }
        

        // Funcion para actualizar un conjunto (usando prepared statements)
        public function updatePublicacion($id, $titulo = null, $descripcion = null, $foto = null) {
            $link = $this->open();

            // Obtenemos el conjunto actual
            $publicacion = $this->getPublicacionById($id);

            if (!$publicacion) {
                $this->close($link);
                throw new Exception("El conjunto no existe");
            }

            // Si no se envia un valor para un campo, se mantiene el valor actual
            if (is_null($titulo)) {
                $titulo = $publicacion['titulo'];
            }

            if (is_null($descripcion)) {
                $descripcion = $publicacion['descripcion'];
            }

            if (is_null($foto)) {
                $foto = $publicacion['foto'];
            }

            $query = "UPDATE publicacion SET titulo = ?, descripcion = ?, foto = ? WHERE idPublicacion = ?";
            $stmt = $link->prepare($query);
            $stmt->bind_param("sssi", $titulo, $descripcion, $foto, $id);
            $result = $stmt->execute();
            
            if ($result) {
                $stmt->close();
                $this->close($link);
                // Retornamos el id del conjunto actualizado
                return $id;
            } else {
                $stmt->close();
                $this->close($link);
                throw new Exception("Error al actualizar el conjunto: " . $link->error);
            }
        }

        // Funcion para eliminar un conjunto
        public function deletePublicacion($id) {
            $link = $this->open();
            $query = "DELETE FROM publicacion WHERE idPublicacion = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                return $id;
            } else {
                $this->close($link);
                throw new Exception("Error al eliminar la publicacion");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE COMENTARIOS

        // Funcion para obtener todos los comentarios
        public function getComentarios(){
            $link = $this->open();
            $query = "SELECT * FROM comentario";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $comentarios = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $comentarios[] = $row;
                }
                
                $this->close($link);
                
                return $comentarios;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener los comentarios");
            }
        }

        // Funcion para obtener un comentario por su id
        public function getComentarioById($id){
            $link = $this->open();
            $query = "SELECT * FROM comentario WHERE idComentario = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $comentario = mysqli_fetch_assoc($result);
                $this->close($link);
                return $comentario;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener el comentario");
            }
        }

        // Funcion para obtener los comentarios de un usuario
        public function getComentariosByUsuario($idUsuario){
            $link = $this->open();
            $query = "SELECT * FROM comentario WHERE idUsuario = $idUsuario";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $comentarios = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $comentarios[] = $row;
                }
                
                $this->close($link);
                
                return $comentarios;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener los comentarios");
            }
        }

        // Funcion para obtener los comentarios de un conjunto
        public function getComentariosByPublicacion($idPublicacion){
            $link = $this->open();
            $query = "SELECT * FROM comentario WHERE idPublicacion = $idPublicacion";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $comentarios = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $comentarios[] = $row;
                }
                
                $this->close($link);
                
                return $comentarios;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener los comentarios");
            }
        }

        // Funcion para guardar un comentario
        public function createComentario($contenido, $idUsuario, $idPublicacion) {
            $link = $this->open();
            $query = "INSERT INTO comentario (contenido, idUsuario, idPublicacion) VALUES ('$contenido', $idUsuario, $idPublicacion)";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                // Si hay un resultado consultamos el id del comentario creado
                $query = "SELECT * FROM comentario WHERE contenido = '$contenido' AND idUsuario = $idUsuario AND idPublicacion = $idPublicacion";
                $result = mysqli_query($link, $query);

                if (!$result) {
                    $this->close($link);
                    throw new Exception("Error al obtener el comentario creado");
                }

                $comentario = mysqli_fetch_assoc($result);
                $this->close($link);

                // Retornamos el id del comentario creado
                return $comentario['idComentario'];
            } else {
                $this->close($link);
                throw new Exception("Error al crear el comentario");
            }
        }

        // Funcion para actualizar un comentario
        public function updateComentario($id, $contenido = null) {
            $link = $this->open();

            // Obtenemos el comentario actual
            $comentario = $this->getComentarioById($id);

            if (!$comentario) {
                $this->close($link);
                throw new Exception("El comentario no existe");
            }

            // Si no se envia un valor para un campo, se mantiene el valor actual
            if (is_null($contenido)) {
                $contenido = $comentario['contenido'];
            }

            $query = "UPDATE comentario SET contenido = '$contenido' WHERE idComentario = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                // Retornamos el id del comentario actualizado
                return $id;
            } else {
                $this->close($link);
                throw new Exception("Error al actualizar el comentario");
            }
        }

        // Funcion para eliminar un comentario
        public function deleteComentario($id) {
            $link = $this->open();
            $query = "DELETE FROM comentario WHERE idComentario = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                return $id;
            } else {
                $this->close($link);
                throw new Exception("Error al eliminar el comentario");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE reacciones
        
        // Funcion para obtener todas las reacciones
        public function getReacciones(){
            $link = $this->open();
            $query = "SELECT * FROM reaccion";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $reacciones = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $reacciones[] = $row;
                }
                
                $this->close($link);
                
                return $reacciones;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener las reacciones");
            }
        }

        // Funcion para obtener una reaccion por su id
        public function getReaccionById($id){
            $link = $this->open();
            $query = "SELECT * FROM reaccion WHERE idReaccion = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $reaccion = mysqli_fetch_assoc($result);
                $this->close($link);
                return $reaccion;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener la reaccion");
            }
        }
        
        // Funcion para obtener las reacciones de un usuario
        public function getReaccionesByUsuario($idUsuario){
            $link = $this->open();
            $query = "SELECT * FROM reaccion WHERE idUsuario = $idUsuario";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $reacciones = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $reacciones[] = $row;
                }
                
                $this->close($link);
                
                return $reacciones;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener las reacciones");
            }
        }

        // Funcion para obtener las reacciones de un conjunto
        public function getReaccionesByPublicacion($idPublicacion){
            $link = $this->open();
            $query = "SELECT * FROM reaccion WHERE idPublicacion = $idPublicacion";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $reacciones = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    $reacciones[] = $row;
                }
                
                $this->close($link);
                
                return $reacciones;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener las reacciones");
            }
        }

        // Funcion para guardar una reaccion
        public function createReaccion($tipo, $idUsuario, $idPublicacion) {
            $link = $this->open();
            $query = "INSERT INTO reaccion (tipo, idUsuario, idPublicacion) VALUES ('$tipo', $idUsuario, $idPublicacion)";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                // Si hay un resultado consultamos el id de la reaccion creada
                $query = "SELECT * FROM reaccion WHERE tipo = '$tipo' AND idUsuario = $idUsuario AND idPublicacion = $idPublicacion";
                $result = mysqli_query($link, $query);

                if (!$result) {
                    $this->close($link);
                    throw new Exception("Error al obtener la reaccion creada");
                }

                $reaccion = mysqli_fetch_assoc($result);
                $this->close($link);

                // Retornamos el id de la reaccion creada
                return $reaccion['idReaccion'];
            } else {
                $this->close($link);
                throw new Exception("Error al crear la reaccion");
            }
        }

        // Funcion para actualizar una reaccion
        public function updateReaccion($id, $tipo = null) {
            $link = $this->open();

            // Obtenemos la reaccion actual
            $reaccion = $this->getReaccionById($id);

            if (!$reaccion) {
                $this->close($link);
                throw new Exception("La reaccion no existe");
            }

            // Si no se envia un valor para un campo, se mantiene el valor actual
            if (is_null($tipo)) {
                $tipo = $reaccion['tipo'];
            }

            $query = "UPDATE reaccion SET tipo = '$tipo' WHERE idReaccion = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                // Retornamos el id de la reaccion actualizada
                return $id;
            } else {
                $this->close($link);
                throw new Exception("Error al actualizar la reaccion");
            }
        }

        // Funcion para eliminar una reaccion
        public function deleteReaccion($id) {
            $link = $this->open();
            $query = "DELETE FROM reaccion WHERE idReaccion = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                return $id;
            } else {
                $this->close($link);
                throw new Exception("Error al eliminar la reaccion");
            }
        }

        /////////////////////////////////////////////////////////////////////////////////////////
        // FUNCIONES PARA MANEJO DE PRENDAS

        // Funcion para obtener todas las prendas
        public function getPrendas(){
            $link = $this->open();
            $query = "SELECT * FROM prenda";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $prendas = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    // Convertimos la imagen blob a base64
                    $imageBlob = $row['foto'];
                    $imageBase64 = base64_encode($imageBlob);
                    $row['foto'] = $imageBase64;
                    $prendas[] = $row;
                }
                
                $this->close($link);
                
                return $prendas;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener las prendas");
            }
        }

        // Funcion para obtener una prenda por su id
        public function getPrendaById($id){
            $link = $this->open();
            $query = "SELECT * FROM prenda WHERE idPrenda = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $prenda = mysqli_fetch_assoc($result);
                // Convertimos la imagen blob a base64
                $imageBlob = $prenda['foto'];
                $imageBase64 = base64_encode($imageBlob);
                $prenda['foto'] = $imageBase64;
                $this->close($link);
                return $prenda;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener la prenda");
            }
        }

        // Funcion para obtener las prendas de un conjunto
        public function getPrendasByPublicacion($idPublicacion){
            $link = $this->open();
            $query = "SELECT * FROM prenda WHERE idPublicacion = $idPublicacion";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $prendas = [];
                while ($row = mysqli_fetch_assoc($result)) {
                    // Convertimos la imagen blob a base64
                    $imageBlob = $row['foto'];
                    $imageBase64 = base64_encode($imageBlob);
                    $row['foto'] = $imageBase64;
                    $prendas[] = $row;
                }
                
                $this->close($link);
                
                return $prendas;
            } else {
                $this->close($link);
                throw new Exception("Error al obtener las prendas");
            }
        }

        // Funcion para guardar una prenda (usando prepared statements)
        public function createPrenda($nombre, $tipo, $foto, $idPublicacion) {
            $link = $this->open();

            if (is_null($foto)) {
                $query = "INSERT INTO prenda (nombre, tipo, idPublicacion) VALUES (?, ?, ?)";
                $stmt = $link->prepare($query);
                $stmt->bind_param("ssi", $nombre, $tipo, $idPublicacion);
            } else {
                $query = "INSERT INTO prenda (nombre, tipo, foto, idPublicacion) VALUES (?, ?, ?, ?)";
                $stmt = $link->prepare($query);
                $stmt->bind_param("sssi", $nombre, $tipo, $foto, $idPublicacion);
            }
        
            if ($stmt->execute()) {
                // Obtener el ID de la prenda creada
                $idPrenda = $stmt->insert_id;
                
                $stmt->close();

                $this->close($link);
        
                // Consultar y retornar la prenda creada
                return $idPrenda;
            } else {
                $stmt->close();
                $this->close($link);
                throw new Exception("Error al crear la prenda: " . $link->error);
            }
        }

        // Funcion para actualizar una prenda (usando prepared statements)
        public function updatePrenda($id, $nombre = null, $tipo = null, $foto = null) {
            $link = $this->open();

            // Obtenemos la prenda actual
            $prenda = $this->getPrendaById($id);

            if (!$prenda) {
                $this->close($link);
                throw new Exception("La prenda no existe");
            }

            // Si no se envia un valor para un campo, se mantiene el valor actual
            if (is_null($nombre)) {
                $nombre = $prenda['nombre'];
            }

            if (is_null($tipo)) {
                $tipo = $prenda['tipo'];
            }

            if (is_null($foto)) {
                $foto = $prenda['foto'];
            }

            $query = "UPDATE prenda SET nombre = ?, tipo = ?, foto = ? WHERE idPrenda = ?";
            $stmt = $link->prepare($query);
            $stmt->bind_param("sssi", $nombre, $tipo, $foto, $id);
            $result = $stmt->execute();
            
            if ($result) {
                $stmt->close();
                $this->close($link);
                // Retornamos el id de la prenda actualizada
                return $id;
            } else {
                $stmt->close();
                $this->close($link);
                throw new Exception("Error al actualizar la prenda: " . $link->error);
            }
        }

        // Funcion para eliminar una prenda
        public function deletePrenda($id) {
            $link = $this->open();
            $query = "DELETE FROM prenda WHERE idPrenda = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                return $id;
            } else {
                $this->close($link);
                throw new Exception("Error al eliminar la prenda");
            }
        }


    }

?>