package Modelo;

import java.time.LocalDate;

/**
 * 
 * En esta clase se almacena la informacion de los informes
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */
public class Informe {
	private String autor;
	private String titulo;
	private String cuerpo;
	
	private String fecha;
	
	/**
	 * Contructor de un informe
	 * 
	 * @param autor Autor del informe 
	 * @param titulo Titulo del informe
	 * @param cuerpo Cuerpo del informe
	 */
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
