<?php

    require_once "DBManager.php";

    $db = new DBManager();

    header('Content-Type: application/json');

    try {
        switch ($_SERVER['REQUEST_METHOD']) {
            case 'GET':
                if (isset($_GET['idPublicacion'])) {
                    $reacciones = $db->getReaccionesByPublicacion($_GET['idPublicacion']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Reacciones encontradas',
                        'data' => $reacciones
                    ]);
                } else if (isset($_GET['idUsuario'])) {
                    $reacciones = $db->getReaccionesByUsuario($_GET['idUsuario']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Reacciones encontradas',
                        'data' => $reacciones
                    ]);
                } else {
                    $reacciones = $db->getReacciones();
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Reacciones encontradas',
                        'data' => $reacciones
                    ]);
                }
                break;
            case 'POST':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['tipo']) || !isset($data['idUsuario']) || !isset($data['idPublicacion'])) {
                    throw new Exception("Faltan datos");
                }

                $tipo = $data['tipo'];
                $idUsuario = $data['idUsuario'];
                $idPublicacion = $data['idPublicacion'];

                $reaccion = $db->createReaccion($tipo, $idUsuario, $idPublicacion);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Reaccion creada',
                    'data' => $reaccion
                ]);

                break;
            case 'PUT':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['idReaccion']) || !isset($data['tipo'])) {
                    throw new Exception("Faltan datos");
                }

                $idReaccion = $data['idReaccion'];
                $tipo = $data['tipo'];

                $reaccion = $db->updateReaccion($idReaccion, $tipo);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Reaccion actualizada',
                    'data' => $reaccion
                ]);

                break;
            case 'DELETE':
                $data = json_decode(file_get_contents('php://input'), true);
                
                // Si no esta seteado el id en el url lanzamos error
                if (!isset($_GET['idReaccion'])) {
                    throw new Exception("Faltan datos");
                }

                $idReaccion = $_GET['idReaccion'];

                $db->deleteReaccion($idReaccion);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Reaccion eliminada'
                ]);

                break;
        }
    } catch (Exception $e) {
        echo json_encode([
            'OK' => false,
            'message' => $e->getMessage()
        ]);
    }


?>