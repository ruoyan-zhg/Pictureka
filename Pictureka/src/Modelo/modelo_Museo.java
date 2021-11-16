package Modelo;

public class modelo_Museo {
	private Registro registro;
	
	
	public modelo_Museo() {
		Registro _registro = new Registro();
		registro = _registro;
	}
	public int registrarClientes(String usuario, String dni, String email, String Contrasenia) {
		int estado = this.registro.registrarCliente(usuario, dni, email, Contrasenia);
		return estado;
		
	}
	public int registrarAdministradores(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2) {
		int estado = this.registro.registrarAdministrador(usuario, dni, email, contrasenia, nombre, apellido1,
				 apellido2);
		return estado;
	}
	public int registrarGuardias(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2) {
		int estado = this.registro.registrarGuardia(usuario, dni, email, contrasenia, nombre, apellido1,
				 apellido2);
		return estado;
	}
	public boolean loginUsuario(String emailOUsuario, String contrasenia) {
		return registro.loginDeUsuarios(emailOUsuario, contrasenia);
	}
}
