package Modelo;

public class modelo_Museo {
	private Registro registro;
	
	
	public modelo_Museo() {
		Registro _registro = new Registro();
		registro = _registro;
	}
	public String registrarClientes(String usuario, String dni, String email, String Contrasenia) {
		String estado = this.registro.registrarCliente(usuario, dni, email, Contrasenia);
		return estado;
	}
	public String registrarAdministradores(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2) {
		String estado = this.registro.registrarAdministrador(usuario, dni, email, contrasenia, nombre, apellido1,
				 apellido2);
		return estado;
	}
	public String registrarGuardias(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2) {
		String estado = this.registro.registrarGuardia(usuario, dni, email, contrasenia, nombre, apellido1,
				 apellido2);
		return estado;
	}
	public boolean loginUsuario(String emailOUsuario, String contrasenia) {
		return registro.loginDeUsuarios(emailOUsuario, contrasenia);
	}
}
