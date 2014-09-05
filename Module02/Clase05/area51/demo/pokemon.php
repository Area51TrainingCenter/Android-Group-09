<?php

class Pokemon{

	var $nombre;
	var $tipo;

	public function creaPokemon($nombre, $tipo){

		//$this->nombre;

		echo 'Creando un pokemon<br>';
		echo 'de nombre: '.$nombre.'<br>';
		echo 'y de tipo: '.$tipo.'<br>';

	}

/*
	public function creaPokemon(){

		echo 'Soy un pokemon';

	}
*/
	static function eliminaPokemon(){
		echo 'Pokemon eliminado';
	}


}


?>