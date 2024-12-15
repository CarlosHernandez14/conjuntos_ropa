<?php

    require_once 'DBManager.php';

    $db = new DBManager();

    header('Content-Type: application/json');

    try {
        switch ($_SERVER['REQUEST_METHOD']) {
            case 'GET':
                if (isset($_GET['idUsuario'])) {
                    $usuario = $db->getUsuarioById($_GET['idUsuario']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Usuario encontrado',
                        'data' => $usuario
                    ]);
                } else if (isset($_GET['correo']) && isset($_GET['contrasena'])) {
                    $usuario = $db->login($_GET['correo'], $_GET['contrasena']);
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Usuario encontrado',
                        'data' => $usuario
                    ]);
                } else {
                    $usuarios = $db->getUsuarios();
                    echo json_encode([
                        'OK' => true,
                        'message' => 'Usuarios encontrados',
                        'data' => $usuarios
                    ]);
                }
                break;
            case 'POST':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['nombre']) || !isset($data['correo']) || !isset($data['contrasena'])) {
                    throw new Exception("Faltan datos");
                }

                $nombre = $data['nombre'] ?? null;
                $correo = $data['correo'] ?? null;
                $contrasena = $data['contrasena'] ?? null;
                $rol = $data['rol'] ?? null;

                $usuario = $db->createUser($nombre, $correo, $contrasena, $rol);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Usuario creado',
                    'data' => $usuario
                ]);

                break;
            case 'PUT':
                $data = json_decode(file_get_contents('php://input'), true);
                
                if (!isset($data['idUsuario'])) {
                    throw new Exception("Faltan datos");
                }

                $idUsuario = $data['idUsuario'];
                $nombre = $data['nombre'] ?? null;
                $correo = $data['correo'] ?? null;
                $contrasena = $data['contrasena'] ?? null;
                $rol = $data['rol'] ?? null;
                // Validacion de boolean bloqueado
                $bloqueado = isset($data['bloqueado']) ? filter_var($data['bloqueado'], FILTER_VALIDATE_BOOLEAN, FILTER_NULL_ON_FAILURE) : null;

                $usuario = $db->updateUser($idUsuario, $nombre, $correo, $contrasena, $rol, $bloqueado);

                echo json_encode([
                    'OK' => true,
                    'message' => 'Usuario actualizado',
                    'data' => $usuario
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