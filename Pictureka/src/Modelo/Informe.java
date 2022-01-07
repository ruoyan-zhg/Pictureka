package Modelo;

import java.time.LocalDate;

public class Informe {
	private String autor;
	private String titulo;
	private String cuerpo;
	
	private String fecha;
	
	public Informe(String autor, String titulo, String cuerpo) {
		this.autor = autor;
		this.titulo = titulo;
		this.cuerpo = cuerpo;
		this.fecha = (LocalDate.now()).toString();
	}


	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getCuerpo() {
		return cuerpo;
	}


	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	
	
	

}
