<?php

$dbhost = "localhost";
$dbuser = "area51";
$dbpass = "area51";
$dbname = "apiarea51";

$dbtime = " SET time_zone = 'America/Lima' ";
$dbcharset = " SET NAMES 'utf8' ";

	$conexion = new mysqli( $dbhost, $dbuser, $dbpass, $dbname );        
	$conexion->query( $dbtime );
    $conexion->query( $dbcharset );

    if (mysqli_connect_error()) {
       die('Error de conexión (' . mysqli_connect_errno() . ') ' 
       	. mysqli_connect_error());
    }

?>