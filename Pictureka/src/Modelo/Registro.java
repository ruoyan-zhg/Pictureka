package Modelo;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 
 * Esta clase maneja el inicio de sesión o registro de usuarios, edicción de datos
 * 
 */

public class Registro {
	private Vector <Usuario> usuarios;
	
	public Registro () {
		Vector <Usuario> _usuarios = new Vector<Usuario>();
		this.usuarios = _usuarios;
	}
	
	public String registrarCliente(String usuario, String dni, String email, String Contrasenia) {
		/*
		 * VALORES DE ESTADO
		 * 
		 * 0 = completo 
		 * 1 = sin completar las comprobaciones
		 * 2 = email no valido 
		 * 3 = email ya registrado anteriormente
		 * 4
		 */
		
		recuperarUsuarios();
		String estado = "Validacion incompleta";
		if (validarEmail(email)) {		//devuelve true si el email es valido
			if (emailRepetido(email)){		//devuelve true si el email no ha sido registrado
				if (usuarioRepetido(usuario)) {
					usuarios.addElement(new Cliente(usuario, dni, email, Contrasenia));
					escribirUsuarios();
					System.out.println(usuarios.size());
					estado = "Validacion completada con exito";
				}
				else {
					estado = "Usuario ya registrado";
				}
					
			}else {
				estado = "El email introducido ya ha sido registrado";
			}
		}
		else{
			estado = "El email introducido no es valido";
		}
		return estado;	
	}
	public String registrarAdministrador(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2) {
		/*
		 * VALORES DE ESTADO
		 * 
		 * 0 = completo 
		 * 1 = sin completar las comprobaciones
		 * 2 = email no valido 
		 * 3 = email ya registrado anteriormente
		 * 4 = usuario repetido
		 * 
		 */
		
		recuperarUsuarios();
		String estado = "Validacion incompleta";
		if (validarEmail(email)) {		//devuelve true si el email es valido
			if (emailRepetido(email)){		//devuelve true si el email no ha sido registrado
				if (usuarioRepetido(usuario)) {
					usuarios.addElement(new Guardia(usuario, dni, email, contrasenia, nombre, apellido1,
							 apellido2));
					escribirUsuarios();
					estado = "Validacion completada con exito";
				}
				else {
					estado = "Usuario ya registrado";
				}	
			}else {
				estado = "El email introducido ya ha sido registrado";
			}
		}
		else{
			estado = "El email introducido no es valido";
		}
		return estado;	
		
	}
	public String registrarGuardia(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2) {
		/*
		 * VALORES DE ESTADO
		 * 
		 * 0 = completo 
		 * 1 = sin completar las comprobaciones
		 * 2 = email no valido 
		 * 3 = email ya registrado anteriormente
		 * 4 = usuario repetido
		 */
		
		recuperarUsuarios();
		String estado = "Validacion incompleta";
		if (validarEmail(email)) {		//devuelve true si el email es valido
			if (emailRepetido(email)){		//devuelve true si el email no ha sido registrado
				if (usuarioRepetido(usuario)) {
					usuarios.addElement(new Administrador(usuario, dni, email, contrasenia, nombre, apellido1,
							 apellido2));
					escribirUsuarios();
					estado = "Validacion completada con exito";
				}
				else {
					estado = "Usuario ya registrado";
				}	
			}else {
				estado = "El email introducido ya ha sido registrado";
			}
		}
		else{
			estado = "El email introducido no es valido";
		}
		return estado;	
		
	}


	public boolean loginDeUsuarios(String emailOUsuario, String contrasenia) {
		boolean login = false;
		int contador = 0;
		recuperarUsuarios();
		System.out.println(usuarios.size());
		while (login != true && contador < usuarios.size()) {
			if(usuarios.elementAt(contador).getEmail().equals(emailOUsuario) && usuarios.elementAt(contador).getContrasenia().equals(contrasenia)||
					usuarios.elementAt(contador).getUsuario().equals(emailOUsuario) && usuarios.elementAt(contador).getContrasenia().equals(contrasenia)) {
				login = true;
			}
			contador++;
		}
		return login;
	}

	public boolean validarEmail(String email) {
		// Patrón para validar el email
		boolean comprobar = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            comprobar = true;
        }
        return comprobar;
	}
	public boolean emailRepetido(String email) {
		boolean noRepetido = true;
		int contador = 0;
		System.out.println(usuarios.size());
		while (noRepetido != false && contador < usuarios.size()) {
			if (usuarios.elementAt(contador).getEmail().equals(email)) {
				noRepetido = false;	
			}
		}
		return noRepetido;
	}
	public boolean usuarioRepetido(String usuario) {
		boolean noRepetido = true;
		int contador = 0;
		while (noRepetido != false && contador < usuarios.size()) {
			if (usuarios.elementAt(contador).getEmail().equals(usuario)) {
				noRepetido = false;	
			}
		}
		return noRepetido;
	}
	
	public void recuperarUsuarios() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		Vector<Usuario> _usuarios = datos.desserializarJsonAusuarios();
		if(_usuarios != null){ 
			this.usuarios = _usuarios ;
		}
	}
	public void escribirUsuarios() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		for (int i = 0; i< usuarios.size(); i++) {
			System.out.println(usuarios.elementAt(i).getUsuario());
		}
		datos.serializarArrayAJson(usuarios);
	}
	

}
