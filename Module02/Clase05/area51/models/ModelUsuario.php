<?php

class ModelUsuario{


	public function listado( $conexion ){

		$sql = "SELECT ID_USUARIO, REGISTRO_USUARIO, CORREO_USUARIO,
				NOMBRE_USUARIO
				FROM usuario
				ORDER BY NOMBRE_USUARIO ASC ";

		return $conexion->query($sql);

	}

}
?>