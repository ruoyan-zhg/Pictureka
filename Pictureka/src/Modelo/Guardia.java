package Modelo;

public class Guardia extends Usuario{
	
	//Atributos
	//Numero que obtiene el guardia para poder acceder a la app proporcionado por el admin
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	//private int salaActual;
	//Atributo del horario de trabajo
	
	//Constructores

	public Guardia(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2) {
		super(2,usuario, dni, email, contrasenia);
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	


	//Métodos

	
	
	
	//Getters y Setters
	public String getApellido2() {
		return apellido2;
	}
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
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
}
