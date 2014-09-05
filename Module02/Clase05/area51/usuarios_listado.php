<?php

//Llamamos a las clases necesarias
require_once 'app/conexionbd.php';
require_once 'models/ModelUsuario.php';


//Variables de respuesta
$response = new stdClass();
$response->respuesta 	= 'Error';
$response->data 		= '';
$response->cantidad 	= '0';

//Listamos los usuarios
$mu = new ModelUsuario();
$registros = $mu->listado( $conexion );

//Obtenemos la cantidad de registros de la consulta
$response->cantidad = $registros->num_rows;

if( $response->cantidad > 0 ){
	//Existen datos, cambiamos la respuesta
	$response->respuesta = 'OK';

	while ( $dato = $registros->fetch_object() ) {

		$aux = new stdClass();
		$aux->nombre 	= $dato->NOMBRE_USUARIO;
		$aux->id 		= $dato->ID_USUARIO;
		$aux->correo 	= $dato->CORREO_USUARIO;
		$aux->registro 	= $dato->REGISTRO_USUARIO;

		$response->data[] = $aux;

	}

}

echo json_encode($response);

?>