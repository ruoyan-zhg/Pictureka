package Modelo;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private Vector<Informe> informes;
	
	
	 static final String USER = "db_lsalmeron";
	 static final String PASS = "Le963963";
	
	public Registro () {
		Vector <Cliente> _usuarios = new Vector<Cliente>();
		Vector <Staff> _staff = new Vector<Staff>();
		Vector<Informe> _informes = new Vector<Informe>();
		this.usuarios = _usuarios;
		this.staff = _staff;
		this.informes = _informes;
	}
	
	/**
	 * aqui se maneja el registro del cliente, mediante sus datos 
	 * @param usuario
	 * @param dni
	 * @param email
	 * @param Contrasenia
	 * @param fechaNacimiento
	 * @return String
	 */
	
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
		
		if (validarEmail(email)) { // devuelve true si el email es valido
			
			// Comprobamos si el email esta registrado en clientes y el staff
			if (emailRepetido(email) && emailRepetidoStaff(email)) { // devuelve true si el email no ha sido registrado
				
				if (dniRepetido(dni) && dniStaffRepetido(dni)) {
					
					if (usuarioRepetido(usuario)) {
						
						usuarios.addElement(new Cliente(usuario, dni, email, Contrasenia, fechaNacimiento));
						escribirUsuarios();
						
						estado = "Validacion completada con exito";
					} else {
						estado = "Usuario ya registrado";
					}
				} else {
					estado = "El dni introducido ya ha sido registrado";
				}

			} else {
				estado = "El email introducido ya ha sido registrado";
			}
		} else {
			estado = "El email introducido no es valido";
		}
		return estado;
	
	}
	
	/**
	 * aqui se maneja el registro de los administradores, mediante las siguientes comprobaciones
	 * @param usuario
	 * @param dni
	 * @param email
	 * @param contrasenia
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param fechaNacimiento
	 * @return
	 */
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
		recuperarStaff();

		String estado = "Validacion incompleta";
		LocalDate fecha = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, fecha);

		if (periodo.getYears() > 18 && periodo.getYears() < 100) {

			if (validarEmail(email)) { // devuelve true si el email es valido
				if (emailRepetido(email) && emailRepetidoStaff(email)) { // devuelve true si el email no ha sido registrado
																			
					if (dniRepetido(dni) && dniStaffRepetido(dni)) {
						if (staffRepetido(usuario)) {
							staff.addElement(new Administrador(usuario, dni, email, contrasenia, fechaNacimiento,
									nombre, apellido1, apellido2));
							escribirStaff();
							estado = "Validacion completada con exito";
						} else {
							estado = "Usuario ya registrado";
						}
					} else {
						estado = "El dni introducido ya ha sido registrado";
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
	
	/**
	 * aqui se maneja el registro de los guardias, mediante comprobaciones
	 * @param usuario
	 * @param dni
	 * @param email
	 * @param contrasenia
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param fechaNacimiento
	 * @return
	 */
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
				if (emailRepetido(email) && emailRepetidoStaff(email)) { // devuelve true si el email no ha sido registrado
					if (dniRepetido(dni) && dniStaffRepetido(dni)) {

						if (staffRepetido(usuario)) {
							staff.addElement(new Guardia(usuario, dni, email, contrasenia, fechaNacimiento, nombre,
									apellido1, apellido2));
							escribirStaff();
							estado = "Validacion completada con exito";
						} else {
							estado = "Usuario ya registrado";
						}
					} else {
						estado = "El dni introducido ya ha sido registrado";
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

    
	/**
	 * aqui se maneja el login del usuario 
	 * @param emailOUsuario
	 * @param contrasenia
	 * @return
	 */
	public int loginDeUsuarios(String emailOUsuario, String contrasenia) {
		
		
		Connection conn = null;
        Statement stmt = null;
        String sql;
        String sql2;
        int tipoUsuario = 0;
        
        try {
            //STEP 1: Register JDBC driver
        	Class.forName("org.mariadb.jdbc.Driver");

            //STEP 2: Open a connection

            conn = DriverManager.getConnection(
                    "jdbc:mariadb://195.235.211.197/priPictureka", USER, PASS);
            
            //Se realiza la consulta en la tabla de CLIENTE
            sql = "SELECT * FROM CLIENTE "
            		+ " WHERE (CLIENTE.Usuario = '"+emailOUsuario+"'OR CLIENTE.Email = '"+emailOUsuario+"') AND CLIENTE.Contraseña = '"+contrasenia+"'";
            stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( sql );
			while ( rs.next() ) {
				String Usuario = rs.getString("Usuario");
				String Contrasenia = rs.getString("Contraseña");
				System.out.println( "Usuario = " + Usuario );
				System.out.println( "Contraseña = " + Contrasenia );
				tipoUsuario = 1;

			}
			
			//Si no se ha encontrado en la de CLIENTE se busca en la de STAFF
			sql2 = "SELECT * FROM STAFF "
            		+ " WHERE (STAFF.Usuario = '"+emailOUsuario+"' OR STAFF.Email = '"+emailOUsuario+"') AND STAFF.Contraseña = '"+contrasenia+"'";
			rs = stmt.executeQuery(sql2);
			while ( rs.next() ) {
				String Usuario = rs.getString("Usuario");
				String Contrasenia = rs.getString("Contraseña");
				int identificadorUser = rs.getInt("identificadorUser");
				System.out.println( "Usuario = " + Usuario );
				System.out.println( "Contraseña = " + Contrasenia );
				System.out.println( "Identificador = " + identificadorUser );
				
				//Dependiendo del Staff que sea sera un identificador u otro
				if (identificadorUser==2) {
					
					tipoUsuario = 2;
				}
				else if (identificadorUser==3) {
					tipoUsuario = 3;
				}

			}

			rs.close();
			stmt.close();
			
			//STEP 6: Cerrando conexion.
			conn.close();     
        }
        
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
		
		
		
		return tipoUsuario;
		
	}
	
  /**
   * compruebamos el email
   * @param email
   * @return boolean
   */
	public boolean validarEmail(String email) {
		// Patr�n para validar el email
		boolean comprobar = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            comprobar = true;
        }
        return comprobar;
	}
	/**
	 * en el caso que se repita el email
	 * @param email
	 * @return boolean
	 */
	public boolean emailRepetido(String email) { 
		boolean noRepetido = true;
		int contador = 0;
		while (noRepetido != false && contador < usuarios.size()) {
			if (usuarios.elementAt(contador).getEmail().equals(email)) {
				noRepetido = false;	
			}
			contador++;
		}
		return noRepetido;
	}
	/**
	 * cuando se repite el email del staff
	 * @param email
	 * @return boolean
	 */
	public boolean emailRepetidoStaff(String email) {
		boolean noRepetido = true;
		int contador = 0;
		while (noRepetido != false && contador < staff.size()) {
			if (staff.elementAt(contador).getEmail().equals(email)) {
				noRepetido = false;	
			}
			contador++;
		}
		return noRepetido;
	}
	
	/**
	 * cuando se introduce un usuario repetido
	 * @param usuario
	 * @return boolean
	 */
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
	/**
	 * Funcion para cuando se introduzca un staff repetido
	 * @param staffs
	 * @return
	 */
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
	
	/**
	 * Funcion para evitar que se introduzca un email repetido
	 * @param dni
	 * @return
	 */
	public boolean dniRepetido(String dni) {
		
		boolean noRepetido = true;
		int contador = 0;
		while (noRepetido != false && contador < usuarios.size()) {
			if (usuarios.elementAt(contador).getDni().equals(dni)) {
				noRepetido = false;	
			}
			contador++;
		}

		return noRepetido;
	}
	
	public boolean dniStaffRepetido(String dni) {
		
		boolean noRepetido = true;
		int contador = 0;
		while (noRepetido != false && contador < staff.size()) {
			if (staff.elementAt(contador).getDni().equals(dni)) {
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
	
	public String rDevolverNombreStaff(String usuario) {
		recuperarStaff();
		Staff sta = recuperar1Staff(usuario);
		return sta.getNombre();
	}
	
	
	

	
	
	/**
	 * ESCRITURA Y LECTURA DE LOS CLIENTES
	 */
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
			
		}
		datos.serializarArrayAJson(usuarios);
	}
	
	
	
	/**
	 * ESCRITURA Y LECTURA DE JSON DEL STAFF
	 */
	public void escribirStaff() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		for (int i = 0; i< staff.size(); i++) {
			
		}
		datos.serializarStaffAJson(staff);
	}
		
	
	public void escribirStaffNuevo(Vector<Staff> staffNuevo) {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		for (int i = 0; i< staffNuevo.size(); i++) {
			
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

	public Vector<Informe> devolverInforme() {
		recuperarInformes();
		return this.informes;
	}
	
	public void escribirInforme(int id, String autor, String titulo, String destino, String cuerpo) throws FileNotFoundException {
		recuperarInformes();
		Informe informe = new Informe (id, autor, titulo, destino, cuerpo);
		informes.addElement(informe);
		escribirInformes();
	}
	

	private void recuperarInformes() {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		Vector<Informe> _informes = datos.desserializarJsonInforme();
		if(_informes != null){ 
			this.informes = _informes;
		}
	}
	public void escribirInformes() throws FileNotFoundException {
		Datos datos = new Datos();
		//Try catch quizas el archivo no abre
		datos.serializarVectorInformesAJson(informes);
	}

	public Vector<Staff> getStaff() {
		recuperarStaff();
		return staff;
	}

	
	

	
	

}
