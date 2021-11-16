package Modelo;

public class Usuario {

	//Atributos
	//Identificador para diferenciar el tipo de usuario al leer el Json
	private int identificadorUser;
	private String usuario;
	private String dni;
	private String email;
	private String contrasenia;
	
	//Contructores

	public Usuario(int identificadorUser, String usuario, String dni, String email, String contrasenia) {
		super();
		this.identificadorUser = identificadorUser;
		this.usuario = usuario;
		this.dni = dni;
		this.email = email;
		this.contrasenia = contrasenia;
	}
	
	
	
	
	
	//Metódos
	
	
	
	
	
	
	
	
	
	
	
	//Getters y Setters
	
	public int getIdentificadorUser() {
		return identificadorUser;
	}
	public void setIdentificadorUser(int identificadorUser) {
		this.identificadorUser = identificadorUser;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}








