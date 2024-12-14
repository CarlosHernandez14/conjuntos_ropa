<?php

    require_once "DBManager.php";

    $db = new DBManager();

    header('Content-Type: application/json');

    try {
        switch ($_SERVER['REQUEST_METHOD']) {
            case 'GET':
                if (isset($_GET['idPublicacion'])) {
                    $publicacion = $db->getPublicacionById($_GET['idPublicacion']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Publicacion encontrada',
                        'data' => $publicacion
                    ]);
                } else if (isset($_GET['idUsuario'])) {
                    $publicaciones = $db->getPublicacionesByUsuario($_GET['idUsuario']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Publicaciones encontradas',
                        'data' => $publicaciones
                    ]);
                } 
                else {
                    $publicaciones = $db->getPublicaciones();
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Publicaciones encontradas',
                        'data' => $publicaciones
                    ]);
                }
                break;
            case 'POST':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['titulo']) || !isset($data['contenido']) || !isset($data['idUsuario'])) {
                    throw new Exception("Faltan datos");
                }

                $titulo = $data['titulo'] ?? null;
                $contenido = $data['contenido'] ?? null;
                $idUsuario = $data['idUsuario'] ?? null;
                $foto = $data['foto'] ?? null;

                if ($foto) {
                    $foto = base64_decode($foto);
                }

                $publicacion = $db->createPublicacion($titulo, $contenido, $foto, $idUsuario);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Publicacion creada',
                    'data' => $publicacion
                ]);

                break;
            case 'PUT':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['idPublicacion'])) {
                    throw new Exception("Faltan datos");
                }

                $idPublicacion = $data['idPublicacion'] ?? null;
                $titulo = $data['titulo'] ?? null;
                $descripcion = $data['descripcion'] ?? null;
                $foto = $data['foto'] ?? null;
                
                if ($foto) {
                    $foto = base64_decode($foto);
                }

                $publicacion = $db->updatePublicacion($idPublicacion, $titulo, $descripcion, $foto);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Publicacion actualizada',
                    'data' => $publicacion
                ]);

                break;
            case 'DELETE':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($_GET['idPublicacion'])) {
                    throw new Exception("Faltan datos");
                }

                $idPublicacion = $_GET['idPublicacion'];

                $publicacion = $db->deletePublicacion($idPublicacion);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Publicacion eliminada',
                    'data' => $publicacion
                ]);

                break;

            default:
                echo json_encode([
                    'OK' => false,
                    'error' => 'Método no soportado'
                ]);
                break;
        }
    } catch (Exception $e) {
        echo json_encode([
            'OK' => false,
            'error' => $e->getMessage()
        ]);
    }


?>