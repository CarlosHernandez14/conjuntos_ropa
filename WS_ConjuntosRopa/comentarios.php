<?php

    require_once "DBManager.php";

    $db = new DBManager();

    header('Content-Type: application/json');


    try {
        switch ($_SERVER['REQUEST_METHOD']) {
            case 'GET':
                if (isset($_GET['idPublicacion'])) {
                    $comentarios = $db->getComentariosByPublicacion($_GET['idPublicacion']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Comentarios encontrados',
                        'data' => $comentarios
                    ]);
                } else if (isset($_GET['idUsuario'])) {
                    $comentarios = $db->getComentariosByUsuario($_GET['idUsuario']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Comentarios encontrados',
                        'data' => $comentarios
                    ]);
                } else {
                    $comentarios = $db->getComentarios();
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Comentarios encontrados',
                        'data' => $comentarios
                    ]);
                }
                break;
            case 'POST':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['contenido']) || !isset($data['idUsuario']) || !isset($data['idPublicacion'])) {
                    throw new Exception("Faltan datos");
                }

                $contenido = $data['contenido'];
                $idUsuario = $data['idUsuario'];
                $idPublicacion = $data['idPublicacion'];

                $comentario = $db->createComentario($contenido, $idUsuario, $idPublicacion);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Comentario creado',
                    'data' => $comentario
                ]);

                break;
            case 'PUT':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['idComentario']) || !isset($data['contenido'])) {
                    throw new Exception("Faltan datos");
                }

                $idComentario = $data['idComentario'];
                $contenido = $data['contenido'];

                $comentario = $db->updateComentario($idComentario, $contenido);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Comentario actualizado',
                    'data' => $comentario
                ]);

                break;
            case 'DELETE':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($_GET['idComentario'])) {
                    throw new Exception("Faltan datos");
                }

                $idComentario = $_GET['idComentario'];

                $comentario = $db->deleteComentario($idComentario);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Comentario eliminado',
                    'data' => $comentario
                ]);

                break;

            default:
                throw new Exception("Metodo no soportado");
                break;
        }
    } catch (Exception $e) {
        echo json_encode([
            'OK' => false,
            'error' => $e->getMessage()
        ]);
    }


?>