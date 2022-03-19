package Modelo;

import java.time.LocalDate;
import java.util.Vector;

/**
 * 
 * Esta clase tiene acceso a todos los datos y funciones del museo
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */

public class modelo_Museo {
	private Registro registro;
	private Museo museo;
	
	
	/**
	 * Crea un Museo. No recibe parametros ya que crea todo desde cero
	 */
	public modelo_Museo() {
		Registro _registro = new Registro();
		registro = _registro;
		museo = new Museo();
	}
	/**
	 * Registra de un Cliente
	 * 
	 * @param usuario Usuario que decide el usuario
	 * @param dni Dni del usuario
	 * @param email Email del usuario
	 * @param contrasenia constrasenia del usuario
	 * @param fechaNacimiento fecha de nacimiento del usuario
	 * @param reservas Reservas realizadas por el usuario
	 * @return Devuelve la informacion o estado sobre el resultado del registro de los clientes
	 */
	public String registrarClientes(String usuario, String dni, String email, String Contrasenia,LocalDate fechaNacimiento) {
		String estado = this.registro.registrarCliente(usuario, dni, email, Contrasenia, fechaNacimiento);
		return estado;
	}
	/**
	 * Registra un administrador
	 * 
	 * @param usuario Usuario asignado al administrador
	 * @param dni DNI asignado al administrador
	 * @param email email asignado al administrador
	 * @param contrasenia contrasenia asignado al administrador
	 * @param fechaNacimiento fechaNacimiento asignado al administrador
	 * @param nombre nombre asignado al administrador
	 * @param apellido1 apellido1 asignado al administrador
	 * @param apellido2 apellido2 asignado al administrador
	 * @return Devuelve la informacion o estado sobre el resultado del registro de los administradores
	 */
	public String registrarAdministradores(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2, LocalDate fechaNacimiento) {
		String estado = this.registro.registrarAdministrador(usuario, dni, email, contrasenia , nombre, apellido1,
				 apellido2, fechaNacimiento);
		return estado;
	}
	/**
	 * Registra un guardias
	 * 
	 * @param usuario Usuario asignado al administrador
	 * @param dni DNI asignado al administrador
	 * @param email email asignado al administrador
	 * @param contrasenia contrasenia asignado al administrador
	 * @param fechaNacimiento fechaNacimiento asignado al administrador
	 * @param nombre nombre asignado al administrador
	 * @param apellido1 apellido1 asignado al administrador
	 * @param apellido2 apellido2 asignado al administrador
	 * @return Devuelve la informacion o estado sobre el resultado del registro de los guardias
	 */
	public String registrarGuardias(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2, LocalDate fechaNacimiento) {
		String estado = this.registro.registrarGuardia(usuario, dni, email, contrasenia , nombre, apellido1,
				 apellido2, fechaNacimiento);
		return estado;
	}
	
	/**
	 * Comprueba que la informacion proporcionada es correcta y permitirle entrar en su cuenta
	 * 
	 * @param emailOUsuario correo electronico o usuario para identificar al usuario
	 * @param contrasenia contrasenia introducida por el usuario
	 * @return la informacion o estado del resultado del login del usuario
	 */
	public Cliente loginUsuario(String emailOUsuario, String contrasenia) {
		return registro.loginDeUsuarios(emailOUsuario, contrasenia);
	}
	public Staff loginStaffs(String emailOUsuario, String contrasenia) {
		return registro.loginStaff(emailOUsuario, contrasenia);
	}
	/**
	 * Funcion para saber que tipo de usuario es
	 * 
	 * @param usuario usuario que se desea saber el tipo de usuario que es
	 * @return Devuelve 1 si es cliente, 2 si es guardia, 3 si es un administrador
	 */
	public int devolverIdentificador(String usuario) {
		return registro.rDevolderIdentificador(usuario);
	}
	/**
	 * Devuelve un cliente 
	 * 
	 * @param usuario usuario del cliente que necesita su informacion
	 * @return Devuelve el cliente
	 */
	public Cliente devolverCliente(String usuario) {
		return registro.rDevolverCliente(usuario);
	}
	/**
	 * Devuelve un cliente 
	 * 
	 * @param usuario usuario del staff que necesita su informacion
	 * @return Devuelve un staff
	 */
	public Staff devolverStaff(String usuario) {
		return registro.rDevolderStaff(usuario);
	}
	/**
	 * Crea una sala apartir del su numero de sala designado
	 * 
	 * @param identificador numero de sala
	 */
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
