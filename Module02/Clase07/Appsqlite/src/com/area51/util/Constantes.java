package com.area51.util;

public class Constantes {
	
	//DATOS DE LA APLICACION
	public static int registro = 0;
	public static String usuario = "";
	
	

	//NOMBRE Y VERSION DE LA BASE DE DATOS
	public static String DB_NAME = "porathena.db";
	public static int DB_VERSION = 1;

	//NOMBRE DE LA TABLA
	public static String DB_TABLE = "usuarios";
	
	//NOMBRE DE COLUMNAS
	public static String C_ID = "id";
	public static String C_USER = "usuario";
	public static String C_PASSWORD = "clave";
	
	//POSICION DE COLUMNAS

	public static int C_ID_INDEX = 0;
	public static int C_USER_INDEX = 1;
	public static int C_PASSWORD_INDEX = 2;
	
	public static String CREATE_TABLE 
		= "CREATE TABLE " + DB_TABLE + 
				"(" + C_ID  + " INTEGER AUTO_INCREMENT," + 
				C_USER + " TEXT," + 
				C_PASSWORD + " TEXT" +
				")";
	
	public static String DROP_TABLE 
		= "DROP TABLE " + DB_TABLE;
	
}






