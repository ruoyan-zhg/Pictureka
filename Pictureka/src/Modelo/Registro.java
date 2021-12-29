package Modelo;
import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 
 * Esta clase maneja el inicio de sesión o registro de usuarios, edicción de datos
 * 
 */

public class Registro {
	private Vector <Cliente> usuarios;
	private Vector<Staff> staff;
	
	public Registro () {
		Vector <Cliente> _usuarios = new Vector<Cliente>();
		Vector <Staff> _staff = new Vector<Staff>();
		this.usuarios = _usuarios;
		this.staff = _staff;
	}
	
	public String registrarCliente(String usuario, String dni, String email, String Contrasenia, LocalDate fechaNacimiento) {
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
		recuperarStaff();
		String estado = "Validacion incompleta";
		System.out.println("antes");
		if (validarEmail(email)) {		//devuelve true si el email es valido
			System.out.println("email");
			//Comprobamos si el email esta registrado en clientes y el staff
			if (emailRepetido(email) && emailRepetidoStaff(email)){		//devuelve true si el email no ha sido registrado
				System.out.println("repemail");
				if (usuarioRepetido(usuario)) {
					System.out.println("add");
					usuarios.addElement(new Cliente(usuario, dni, email, Contrasenia, fechaNacimiento));
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
			String apellido2, LocalDate fechaNacimiento) {
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
		recuperarStaff();;
		String estado = "Validacion incompleta";
		if (validarEmail(email)) {		//devuelve true si el email es valido
			if (emailRepetido(email) && emailRepetidoStaff(email)){		//devuelve true si el email no ha sido registrado
				if (staffRepetido(usuario)) {
					staff.addElement(new Administrador(usuario, dni, email, contrasenia, fechaNacimiento, nombre, apellido1,
							 apellido2));
					escribirStaff();
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
			String apellido2, LocalDate fechaNacimiento) {
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
		recuperarStaff();
		String estado = "Validacion incompleta";
		LocalDate fecha = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, fecha);

		if (periodo.getYears() > 18 && periodo.getYears() < 100) {

			if (validarEmail(email)) { // devuelve true si el email es valido
				if (emailRepetido(email) && emailRepetidoStaff(email)) { // devuelve true si el email no ha sido
																			// registrado
					if (staffRepetido(usuario)) {
						staff.addElement(new Guardia(usuario, dni, email, contrasenia, fechaNacimiento, nombre,
								apellido1, apellido2));
						escribirStaff();
						estado = "Validacion completada con exito";
					} else {
						estado = "Usuario ya registrado";
					}
				} else {
					estado = "El email introducido ya ha sido registrado";
				}
			} else {
				estado = "El email introducido no es valido";
			}
		} else {
			estado = "Rango de edad no aceptable";
		}
		return estado;

	}


	public int loginDeUsuarios(String emailOUsuario, String contrasenia) {
		
		int tipoUsuario = 0;
		boolean login = false;
		boolean esCliente = false;
		int contador = 0;
		int contadorStaff = 0
				;
		recuperarUsuarios();
		recuperarStaff();
		
		while (login != true && contador < usuarios.size()) {
			if(usuarios.elementAt(contador).getEmail().equals(emailOUsuario) && usuarios.elementAt(contador).getContrasenia().equals(contrasenia)||
					usuarios.elementAt(contador).getUsuario().equals(emailOUsuario) && usuarios.elementAt(contador).getContrasenia().equals(contrasenia)) {
					login = true;
					esCliente = true;
					tipoUsuario = 1;
				
			}
			contador++;
		}
		

		if (esCliente==false) {
			while (login !=true && contadorStaff < staff.size()) {
				if(staff.elementAt(contadorStaff).getEmail().equals(emailOUsuario) && staff.elementAt(contadorStaff).getContrasenia().equals(contrasenia)||
						staff.elementAt(contadorStaff).getUsuario().equals(emailOUsuario) && staff.elementAt(contadorStaff).getContrasenia().equals(contrasenia)) {
						login = true;
						esCliente = false;
							
						if (staff.elementAt(contadorStaff).getIdentificadorUser()==2) {
							tipoUsuario = 2;
						}
						else if (staff.elementAt(contadorStaff).getIdentificadorUser()==3) {
							tipoUsuario = 3;
						}
					}
					contadorStaff++;
				}
		}
		
		
		return tipoUsuario;
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
			contador++;
		}
		return noRepetido;
	}
	
	public boolean emailRepetidoStaff(String email) {
		boolean noRepetido = true;
		int contador = 0;
		System.out.println(staff.size());
		while (noRepetido != false && contador < staff.size()) {
			if (staff.elementAt(contador).getEmail().equals(email)) {
				noRepetido = false;	
			}
			contador++;
		}
		return noRepetido;
	}
	
	
	public boolean usuarioRepetido(String usuario) {
		boolean noRepetido = true;
		int contador = 0;
		while (noRepetido != false && contador < usuarios.size()) {
			if (usuarios.elementAt(contador).getUsuario().equals(usuario)) {
				noRepetido = false;	
			}
			contador++;
		}
		return noRepetido;
	}
	
	public boolean staffRepetido(String staffs) {
		boolean noRepetido = true;
		int contador = 0;
		while (noRepetido != false && contador < staff.size()) {
			if (staff.elementAt(contador).getUsuario().equals(staffs)) {
				noRepetido = false;	
			}
			contador++;
		}
		return noRepetido;
	}
	
	public int rDevolderIdentificador(String usuario) {
		recuperarUsuarios();
		recuperarStaff();
		int identificador = 0;
		if (usuarios!=null) {
			identificador = identificadorCLiente(usuario);
			if (identificador == -1 && staff != null) {
				identificador = identificadorStaff(usuario);
			}
		}
		else {
			if (staff != null) {
				identificador = identificadorStaff(usuario);
			}
		}
		return identificador;
	}
	
	public Cliente rDevolverCliente(String usuario) {
		recuperarUsuarios();
		Cliente cli = recuperar1Cliente(usuario);
		return cli;
	}

	public Staff rDevolderStaff(String usuario) {
		recuperarStaff();
		Staff sta = recuperar1Staff(usuario);
		return sta;
	}
	
	
	
	
	//ESCRITURA Y LECTURA DE LOS CLIENTES
	public void recuperarUsuarios() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		Vector<Cliente> _usuarios = datos.desserializarJsonAusuarios();
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
	
	
	
	//ESCRITURA Y LECTURA DE JSON DEL STAFF
	public void escribirStaff() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		for (int i = 0; i< staff.size(); i++) {
			System.out.println(staff.elementAt(i).getUsuario());
		}
		datos.serializarStaffAJson(staff);
	}
	
	public void escribirStaffNuevo(Vector<Staff> staffNuevo) {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		for (int i = 0; i< staffNuevo.size(); i++) {
			System.out.println(staffNuevo.elementAt(i).getUsuario());
		}
		datos.serializarStaffAJson(staffNuevo);
	}
	
	
	public void recuperarStaff() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		Vector<Staff> _staff = datos.desserializarJsonStaff();
		if(_staff != null){ 
			this.staff = _staff;
		}
	}
	
	public int identificadorCLiente(String usuario) {
		boolean encontrado = false;
		int identificador = -1;
		int contador = 0;
		while (encontrado != true && contador < usuarios.size()) {
			if (usuarios.elementAt(contador).getEmail().equals(usuario)||usuarios.elementAt(contador).getUsuario().equals(usuario)) {
				encontrado = true;	
				identificador = usuarios.elementAt(contador).getIdentificadorCliente();
			}
			contador++;
		}
		return identificador;
	}
	
	public int identificadorStaff(String usuario) {
		boolean encontrado = false;
		int identificador = -1;
		int contador = 0;
		while (encontrado != true && contador < staff.size()) {
			if (staff.elementAt(contador).getEmail().equals(usuario)||staff.elementAt(contador).getUsuario().equals(usuario)) {
				encontrado = true;	
				identificador = staff.elementAt(contador).getIdentificadorUser();
			}
			contador++;
		}
		return identificador;
	}
	
	public Cliente recuperar1Cliente(String usuario) {
		boolean encontrado = false;
		Cliente cli = new Cliente(usuario, usuario, usuario, usuario, null);
		int contador = 0;
		while (encontrado != true && contador < usuarios.size()) {
			if (usuarios.elementAt(contador).getEmail().equals(usuario)||usuarios.elementAt(contador).getUsuario().equals(usuario)) {
				encontrado = true;	
				cli = usuarios.elementAt(contador);
			}
			contador++;
		}
		return cli;
	}
	
	public Staff recuperar1Staff(String usuario) {
		boolean encontrado = false;
		Staff sta = new Staff(0, usuario, usuario, usuario, usuario, usuario, usuario, usuario, null);
		int contador = 0;
		while (encontrado != true && contador < this.staff.size()) {
			if (staff.elementAt(contador).getEmail().equals(usuario) ||staff.elementAt(contador).getUsuario().equals(usuario)) {
				encontrado = true;	
				sta = staff.elementAt(contador);
			}
			contador++;
		}
		return sta;
	}
	

	
	

}
