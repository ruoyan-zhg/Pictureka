package Modelo;

import java.time.LocalDate;

public class Administrador extends Usuario{

	//Atributos
	//Numero que obtiene el admin para poder acceder a la app
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	
	
	//Constructores

	public Administrador(String usuario, String dni, String email, String contrasenia, LocalDate fechaNacimiento , String nombre, String apellido1,
			String apellido2) {
		super(3,usuario, dni, email, contrasenia,fechaNacimiento);
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}



	//Métodos
	
	
	
	
	
	
	
	
	
	
	
	
	//Getters y Setters
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
}
