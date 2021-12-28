package Modelo;

import java.time.LocalDate;

public class Administrador extends Staff {

	
	
	
	//Constructores

	public Administrador(String usuario, String dni, String email, String contrasenia, LocalDate fechaNacimiento , String nombre, String apellido1,
			String apellido2) {
		super(3,usuario, nombre, apellido1, apellido2, dni, email, contrasenia,fechaNacimiento);
	}



	//Métodos
	

}
