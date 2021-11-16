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
	
	public int registrarCliente(String usuario, String dni, String email, String Contrasenia) {
		/*
		 * VALORES DE ESTADO
		 * 
		 * 0 = completo 
		 * 1 = sin completar las comprobaciones
		 * 2 = email no valido 
		 * 3 = email ya registrado anteriormente
		 */
		
		recuperarUsuarios();
		int estado = 1;
		if (validarEmail(email)) {		//devuelve true si el email es valido
			if (emailRepetido(email)){		//devuelve true si el email no ha sido registrado
				usuarios.addElement(new Cliente(usuario, dni, email, Contrasenia));
				estado = 0;		
			}else {
				estado = 3;
			}
		}
		else{
			estado = 2;
		}
		return estado;	
	}
	public int registrarAdministrador(String usuario, String dni, String email, String contrasenia, String nombre, String apellido1,
			String apellido2) {
		/*
		 * VALORES DE ESTADO
		 * 
		 * 0 = completo 
		 * 1 = sin completar las comprobaciones
		 * 2 = email no valido 
		 * 3 = email ya registrado anteriormente
		 * 
		 * 
		 */
		
		recuperarUsuarios();
		int estado = 1;
		if (validarEmail(email)) {		//devuelve true si el email es valido
			if (emailRepetido(email)){		//devuelve true si el email no ha sido registrado
				usuarios.addElement(new Guardia(usuario, dni, email, contrasenia, nombre, apellido1,
					 apellido2));
				estado = 0;		
			}else {
				estado = 3;
			}
		}
		else{
			estado = 2;
		}
		return estado;	
		
	}
	public int registrarGuardia() {
		/*
		 * VALORES DE ESTADO
		 * 
		 * 0 = completo 
		 * 1 = sin completar las comprobaciones
		 * 2 = email no valido 
		 * 3 = email ya registrado anteriormente
		 */
		
		recuperarUsuarios();
		int estado = 1;
		if (validarEmail(email)) {		//devuelve true si el email es valido
			if (emailRepetido(email)){		//devuelve true si el email no ha sido registrado
				usuarios.addElement(new Cliente(usuario, dni, email, Contrasenia));
				estado = 0;		
			}else {
				estado = 3;
			}
		}
		else{
			estado = 2;
		}
		return estado;	
		
	}
	public boolean validarEmail(String email) {
		// Patrón para validar el email
		boolean comprobar = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            comprobar = true;
        } else {
        }
        return comprobar;
	}
	public boolean emailRepetido(String email) {
		boolean noRepetido = true;
		int contador = 0;
		while (noRepetido != true && contador < usuarios.size()) {
			if (usuarios.elementAt(contador).getEmail().equals(email)) {
				noRepetido = false;	
			}
		}
		return noRepetido;
	}
	public void recuperarUsuarios() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		this.usuarios = datos.desserializarJsonAusuarios();
	}
	public void escribirUsuarios() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		datos.serializarArrayAJson(usuarios);
	}
	

}
