<?php

    require_once "DBManager.php";

    $db = new DBManager();

    header('Content-Type: application/json');

    try {
        switch ($_SERVER['REQUEST_METHOD']) {
            case 'GET':
                if (isset($_GET['idPrenda'])) {
                    $prenda = $db->getPrendaById($_GET['idPrenda']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Prenda encontrada',
                        'data' => $prenda
                    ]);
                } else if (isset($_GET['idPublicacion'])) {
                    $prendas = $db->getPrendasByPublicacion($_GET['idPublicacion']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Prendas encontradas',
                        'data' => $prendas
                    ]);
                } else {
                    $prendas = $db->getPrendas();
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Prendas encontradas',
                        'data' => $prendas
                    ]);
                }
                break;
            case 'POST':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['nombre']) || !isset($data['tipo']) || !isset($data['idPublicacion'])) {
                    throw new Exception("Faltan datos");
                }

                $nombre = $data['nombre'];
                $tipo = $data['tipo'];
                $idPublicacion = $data['idPublicacion'];
                $foto = $data['foto'] ?? null;

                if ($foto) {
                    $foto = base64_decode($foto);
                }

                $prenda = $db->createPrenda($nombre, $tipo, $foto, $idPublicacion);


                echo json_encode([
                    'OK' => true,
                    'message' => 'Prenda creada',
                    'data' => $prenda
                ]);

                break;
            case 'PUT':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['idPrenda'])) {
                    throw new Exception("Faltan datos");
                }

                $idPrenda = $data['idPrenda'];
                $nombre = $data['nombre'] ?? null;
                $tipo = $data['tipo'] ?? null;
                $foto = $data['foto'] ?? null;

                if ($foto) {
                    $foto = base64_decode($foto);
                }


                $prenda = $db->updatePrenda($idPrenda, $nombre, $tipo, $foto);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Prenda actualizada',
                    'data' => $prenda
                ]);

                break;

            case 'DELETE':

                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($_GET['idPrenda'])) {
                    throw new Exception("Faltan datos");
                }

                $idPrenda = $_GET['idPrenda'];

                $prenda = $db->deletePrenda($idPrenda);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Prenda eliminada',
                    'data' => $prenda
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