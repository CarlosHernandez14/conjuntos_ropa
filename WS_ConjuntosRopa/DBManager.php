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
            }

            $query = "UPDATE usuario SET nombre = '$nombre', correo = '$correo', contrasena = SHA1('$contrasena'), rol = '$rol' WHERE idUsuario = $id";
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
            $query = "INSERT INTO publicacion (titulo, descripcion, foto, idUsuario) VALUES ('$titulo', '$descripcion', '$foto', $idUsuario)";

            if (is_null($foto)) {
                $query = "INSERT INTO publicacion (titulo, descripcion, idUsuario) VALUES ('$titulo', '$descripcion', $idUsuario)";
            }
            
            $result = mysqli_query($link, $query);
            
            if ($result) {
                // Si hay un resultado consultamos el id del publicacion creado
                $query = "SELECT * FROM publicacion WHERE titulo = '$titulo' AND descripcion = '$descripcion' AND idUsuario = $idUsuario";
                $result = mysqli_query($link, $query);

                if (!$result) {
                    $this->close($link);
                    throw new Exception("Error al obtener el publicacion creado");
                }

                $publicacion = mysqli_fetch_assoc($result);
                $this->close($link);

                // Retornamos el id del publicacion creado
                return $publicacion['iPublicacion'];
            } else {
                $this->close($link);
                throw new Exception("Error al crear la publicacion");
            }
        }

        // Funcion para actualizar un conjunto
        public function updatePublicacion($id, $titulo = null, $descripcion = null, $foto = null) {
            $link = $this->open();

            // Obtenemos el conjunto actual
            $publicacion = $this->getPublicacionById($id);

            if (!$publicacion) {
                $this->close($link);
                throw new Exception("La publicacion no existe");
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

            $query = "UPDATE publicacion SET titulo = '$titulo', descripcion = '$descripcion', foto = '$foto' WHERE idPublicacion = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                // Retornamos el id del publicacion actualizado
                return $id;
            } else {
                $this->close($link);
                throw new Exception("Error al actualizar la publicacion");
            }
        }

        // Funcion para eliminar un conjunto
        public function deletePublicacion($id) {
            $link = $this->open();
            $query = "DELETE FROM publicacion WHERE idPublicacion = $id";
            $result = mysqli_query($link, $query);
            
            if ($result) {
                $this->close($link);
                return true;
            } else {
                $this->close($link);
                throw new Exception("Error al eliminar la publicacion");
            }
        }

    }

?>