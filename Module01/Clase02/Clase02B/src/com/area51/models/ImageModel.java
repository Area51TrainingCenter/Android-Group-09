package com.area51.models;

public class ImageModel {

	// Atributos
	protected int idImagen;
	protected String nombreImagen;
	protected int rutaInternaImagen;
	protected String rutaExternaImagen;

	// Getters and Setters

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public int getRutaInternaImagen() {
		return rutaInternaImagen;
	}

	public void setRutaInternaImagen(int rutaInternaImagen) {
		this.rutaInternaImagen = rutaInternaImagen;
	}

	public String getRutaExternaImagen() {
		return rutaExternaImagen;
	}

	public void setRutaExternaImagen(String rutaExternaImagen) {
		this.rutaExternaImagen = rutaExternaImagen;
	}

}
