<?php

    class DBManager {
        private $db;
        private $host;
        private $usuario;
        private $contrasena;
        private $puerto;

        public function __construct() {
            $this->db = "repartidora_agua";
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
    }

?>