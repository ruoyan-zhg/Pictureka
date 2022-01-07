package Modelo;

import java.time.LocalDate;
import java.util.Vector;

public class modelo_Museo {
	private Registro registro;
	private Museo museo;
	
	
	public modelo_Museo() {
		Registro _registro = new Registro();
		registro = _registro;
		museo = new Museo();
	}
	public String registrarClientes(String usuario, String dni, String email, String Contrasenia,LocalDate fechaNacimiento) {
		String estado = this.registro.registrarCliente(usuario, dni, email, Contrasenia, fechaNacimiento);
		return estado;
	}
	public String registrarAdministradores(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2, LocalDate fechaNacimiento) {
		String estado = this.registro.registrarAdministrador(usuario, dni, email, contrasenia , nombre, apellido1,
				 apellido2, fechaNacimiento);
		return estado;
	}
	public String registrarGuardias(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2, LocalDate fechaNacimiento) {
		String estado = this.registro.registrarGuardia(usuario, dni, email, contrasenia , nombre, apellido1,
				 apellido2, fechaNacimiento);
		return estado;
	}
	public int loginUsuario(String emailOUsuario, String contrasenia) {
		return registro.loginDeUsuarios(emailOUsuario, contrasenia);
	}
	public int devolverIdentificador(String usuario) {
		return registro.rDevolderIdentificador(usuario);
	}
	public Cliente devolverCliente(String usuario) {
		return registro.rDevolverCliente(usuario);
	}
	public Staff devolverStaff(String usuario) {
		
		return registro.rDevolderStaff(usuario);
	}
	public void nuevaSala(int identificador) {
		museo.MNuevaSala(identificador);
	}
	
	public Registro getRegistro() {
		return registro;
	}
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	public Museo getMuseo() {
		return museo;
	}
	public void setMuseo(Museo museo) {
		this.museo = museo;
	}
	
	
	
	
	
	
	
	
}
