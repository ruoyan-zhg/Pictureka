package Controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import com.jfoenix.controls.JFXButton;

import Modelo.Cifrado;
import Modelo.Cliente;
import Modelo.Datos;
import Modelo.Registro;
import Modelo.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * En esta clase se maneja la edición de información de un administrador, en la vista <b>TabEditarAdministrador</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 * 
 */
public class ControladorTabEditarAdmin {

	ControladorEditarAdministrador controlerEditAdmin;
    @FXML
    private AnchorPane AnchorTabEditarAdmin;

    @FXML
    private GridPane gridPaneEditarAdmin;

    @FXML
    private Label lblUsuarioAdmin;

    @FXML
    private TextField textUsuarioAdmin;

    @FXML
    private Label lblNombreAdmin;

    @FXML
    private TextField textNombreAdmin;

    @FXML
    private Label lblApellido1Admin;

    @FXML
    private Label lblApellido2Admin;

    @FXML
    private TextField textApellido1Admin;

    @FXML
    private TextField textApellido2Admin;

    @FXML
    private Label lblEmailAdmin;

    @FXML
    private TextField textEmailAdmin;

    @FXML
    private Label lblDniAdmin;

    @FXML
    private TextField textDniAdmin;

    @FXML
    private Label lblFechaAdmin;

    @FXML
    private Label lblContraseniaAdmin;

    @FXML
    private TextField textContraseniaAdmin;

    @FXML
    private DatePicker DateFechaAdmin;

    @FXML
    private JFXButton btnGuardar;
    
    static final String USER = "pri_Pictureka";
    static final String PASS = "asas";
    Connection conn = null;
    Statement stmt = null;
    String sql;

    /**
     * 
     * Constructor de la clase <b>ControladorTabEditarAdmin</b> al que se le pasa
	 * el Controlador de edicciones de administradores.
     * 
     * @param controlerEdit		Controlador de la clase <b>ControladorEditarAdministrador</b>
	 *                      	con sus respecitvos atributos y métodos.
     */
    public ControladorTabEditarAdmin(ControladorEditarAdministrador controlerEdit) {
    	
    	this.controlerEditAdmin = controlerEdit;
    
    }
    
    
	@FXML
	/**
	 * 
	 * Inicializa la ventana, comprobando la selección del administrador y mostrando su información en los campos.
	 * 
	 */
	public void initialize() {

		// Se comprueba que el administrador ha seleccionado a un administrador de la tabla
		if (!controlerEditAdmin.getTableViewAdministrador().getSelectionModel().isEmpty()) {

			// Se recoge la informacion del administrador que se ha seleccionado y
			// se muestra en los diferentes campos
			textUsuarioAdmin
					.setText(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getUsuario());
			textNombreAdmin
					.setText(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getNombre());
			textApellido1Admin
					.setText(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getApellido1());
			textApellido2Admin
					.setText(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getApellido2());
			textDniAdmin.setText(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getDni());
			textEmailAdmin
					.setText(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getEmail());
			DateFechaAdmin.setValue(
					controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getFechaNacimiento());
			textContraseniaAdmin.setText(
					controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getContrasenia());

		}

	}
    
    

	@FXML
	/**
	 * 
	 * Guarda los cambios realizados en uno o más de los campos de información del administrador, cambiando el contenido del Json de Staff.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre el botón "Guardar Cambios".
	 */
    void GuardarAdminEdit(ActionEvent event) {

		Alert error = new Alert(Alert.AlertType.ERROR);
		Alert informacion = new Alert(Alert.AlertType.INFORMATION);
		Registro registro = new Registro();
		Cifrado cifrar = new Cifrado();

		// Se guarda en un vector la informacion del json del personal de staff

		// Obtenemos los datos de los diferentes jtextfield
		String UsuarioNuevo = textUsuarioAdmin.getText();
		String nombreNuevo = textNombreAdmin.getText();
		String apellido1Nuevo = textApellido1Admin.getText();
		String apellido2Nuevo = textApellido2Admin.getText();
		String dniNuevo = textDniAdmin.getText();
		String emailNuevo = textEmailAdmin.getText();
		LocalDate fechaNuevo = DateFechaAdmin.getValue();
		String contraseniaNuevo = textContraseniaAdmin.getText();

		// Comprobamos que haya seleccionado un administrador
		if (!controlerEditAdmin.getTableViewAdministrador().getSelectionModel().isEmpty()) {

			// Comprobamos que el contenido no está vacío
			if (!(UsuarioNuevo.isEmpty() | nombreNuevo.isEmpty() | apellido1Nuevo.isEmpty() | apellido2Nuevo.isEmpty()
					| dniNuevo.isEmpty() | emailNuevo.isEmpty() | (fechaNuevo == null) | contraseniaNuevo.isEmpty())) {

				Staff staff = buscarAdminSelecc();
				if ((staff.getContrasenia().equals(cifrar.hashing(contraseniaNuevo)) == false)) {
					contraseniaNuevo = cifrar.hashing(contraseniaNuevo);

				}

				// Recorremos el json staff

				LocalDate fecha = LocalDate.now();
				Period periodo = Period.between(fechaNuevo, fecha);

				// Comprobaciones para los distintos casos que se pueden dar

				// Comprobacion del rango de edad
				if (dniNuevo.length() == 9) {
					if (UsuarioNuevo.length() <= 10) {
						if (periodo.getYears() > 18 && periodo.getYears() < 100) {
							// staff.get(i).setFechaNacimiento(fechaNuevo);

							// devuelve true si el usuario no esta repetido
							if (registro.staffRepetido(UsuarioNuevo) && registro.usuarioRepetido(UsuarioNuevo)) {
								// staff.get(i).setUsuario(UsuarioNuevo);

								// devuelve true si el dni no esta repetido
								if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {
									// staff.get(i).setDni(dniNuevo);

									// devuelve true si el email no esta repetidp
									if (registro.emailRepetido(emailNuevo) && registro.emailRepetidoStaff(emailNuevo)) {

										// Valida el email nuevo
										if (registro.validarEmail(emailNuevo)) {
											/********************************************************************************************************/
											GuardarAdminBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo,
													dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
											controlerEditAdmin.actualizarTablaAdmins();
											eliminarContenidoTxtfield();
											informacion.setHeaderText("Cambios realizados con éxito.");
											informacion.showAndWait();

										} else {
											error.setHeaderText("Formato de email incorrecto.");
											error.showAndWait();

										}

									} else {
										// Si el administrador mantiene su mismo email
										if (staff.getEmail().equals(emailNuevo)) {

											GuardarAdminBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo,
													dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
											controlerEditAdmin.actualizarTablaAdmins();
											eliminarContenidoTxtfield();
											informacion.setHeaderText("Cambios realizados con éxito.");
											informacion.showAndWait();

										} else {
											error.setHeaderText("Email ya registrado.");
											error.showAndWait();

										}

									}
								} else { // EL ADMIN MANTIENE SU MISMO DNI
									// si el administrador mantiene su mismo dni

									if (staff.getDni().equals(dniNuevo)) {
										// staff.get(i).setDni(dniNuevo);

										if (registro.emailRepetido(emailNuevo)
												&& registro.emailRepetidoStaff(emailNuevo)) {

											if (registro.validarEmail(emailNuevo)) {

												GuardarAdminBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo,
														apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo,
														contraseniaNuevo);
												controlerEditAdmin.actualizarTablaAdmins();
												eliminarContenidoTxtfield();
												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Formato de email incorrecto.");
												error.showAndWait();

											}

										} else {
											if (staff.getEmail().equals(emailNuevo)) {

												GuardarAdminBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo,
														apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo,
														contraseniaNuevo);
												controlerEditAdmin.actualizarTablaAdmins();
												eliminarContenidoTxtfield();
												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Email ya registrado.");
												error.showAndWait();

											}
										}

									} else {
										error.setHeaderText("Dni ya registrado.");
										error.showAndWait();

									}

								}

							}

							// EL ADMIN MANTIENE SU MISMO USUARIO
							else {
								// Si el guardia mantiene su mismo usuario
								if (staff.getUsuario().equals(UsuarioNuevo)) {
									staff.setUsuario(UsuarioNuevo);

									// devuelve true si el email del admin no esta repetido
									if (registro.emailRepetido(emailNuevo) && registro.emailRepetidoStaff(emailNuevo)) {

										if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {

											// staff.get(i).setDni(dniNuevo);

											// Se valdida el nuevo email
											if (registro.validarEmail(emailNuevo)) {

												GuardarAdminBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo,
														apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo,
														contraseniaNuevo);
												controlerEditAdmin.actualizarTablaAdmins();
												eliminarContenidoTxtfield();

												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Formato de email incorrecto.");
												error.showAndWait();

											}
										} else {
											// Si el dni es el mismo del admin

											if (staff.getDni().equals(dniNuevo)) {
												// staff.get(i).setDni(dniNuevo);

												if (registro.validarEmail(emailNuevo)) {

													GuardarAdminBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo,
															apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo,
															contraseniaNuevo);
													controlerEditAdmin.actualizarTablaAdmins();
													eliminarContenidoTxtfield();
													informacion.setHeaderText("Cambios realizados con éxito.");
													informacion.showAndWait();
												} else {
													error.setHeaderText("Formato de email incorrecto.");
													error.showAndWait();

												}

											} else {
												error.setHeaderText("Dni ya registrado.");
												error.showAndWait();

											}

										}

									} else {
										// Si el email es el mismo del admin
										if (staff.getEmail().equals(emailNuevo)) {
											// staff.get(i).setEmail(emailNuevo);

											if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {

												GuardarAdminBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo,
														apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo,
														contraseniaNuevo);
												controlerEditAdmin.actualizarTablaAdmins();
												eliminarContenidoTxtfield();
												// registro.escribirStaffNuevo(staff);

												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												// Mismo dni del admin
												if (staff.getDni().equals(dniNuevo)) {

													GuardarAdminBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo,
															apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo,
															contraseniaNuevo);
													controlerEditAdmin.actualizarTablaAdmins();
													eliminarContenidoTxtfield();

													informacion.setHeaderText("Cambios realizados con éxito.");
													informacion.showAndWait();

												} else {
													error.setHeaderText("Dni ya registrado.");
													error.showAndWait();

												}
											}
										} else {
											error.setHeaderText("Email ya registrado.");
											error.showAndWait();

										}
									}

								} else {
									error.setHeaderText("Usuario ya registrado.");
									error.showAndWait();

								}

							}

						} else {
							error.setHeaderText("Rango de edad no aceptable.");
							error.showAndWait();
						}
					} else {
						error.setHeaderText("El DNI debe tener una longutid de 9 digitos.");
						error.showAndWait();
					}
				} else {
					error.setHeaderText("La longitud del Usuario no es válida.");
					error.showAndWait();
				}

			} else {
				error.setHeaderText("Revise que todos losc campos están completos.");
				error.showAndWait();
			}
		} else {
			error.setHeaderText("No se ha seleccionado ningún administrador a modificar.");
			error.showAndWait();
		}

	}
	

	void GuardarAdminBBDD(String UsuarioNuevo,String nombreNuevo,String apellido1Nuevo,String apellido2Nuevo,String dniNuevo,String emailNuevo,LocalDate fechaNuevo,String contraseniaNuevo) {
		

		Date date = Date.valueOf(fechaNuevo);
		
		String userSelecc = controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getUsuario();
		
		if (!controlerEditAdmin.getTableViewAdministrador().getSelectionModel().isEmpty()) {

			// Comprobamos que el contenido no está vacío
			if (!(UsuarioNuevo.isEmpty() | nombreNuevo.isEmpty() | apellido1Nuevo.isEmpty() | apellido2Nuevo.isEmpty()
					| dniNuevo.isEmpty() | emailNuevo.isEmpty() | (fechaNuevo == null) | contraseniaNuevo.isEmpty())) {
				LocalDate fecha = LocalDate.now();
				Period periodo = Period.between(fechaNuevo, fecha);

				// Comprobaciones para los distintos casos que se pueden dar

				// Comprobacion del rango de edad
				if (periodo.getYears() > 18 && periodo.getYears() < 100) {
					
					try {
						Class.forName("org.mariadb.jdbc.Driver");

			            conn = DriverManager.getConnection(
			                    "jdbc:mariadb://195.235.211.197/priPictureka", USER, PASS);
			            sql = "UPDATE STAFF SET "
			            		+ "Usuario = '"+UsuarioNuevo+"', "
			            		+ "Nombre = '"+ nombreNuevo +"', "
			            		+ "Apellido1 = '"+apellido1Nuevo +"',"
	            				+ "Apellido2 = '"+apellido2Nuevo+"', "
			            		+ "identificadorUser = 3, "
			            		+ "Dni = '"+dniNuevo +"', "
	            				+ "FechaNacimiento = '"+date+"', "
			            		+ "Email= '"+ emailNuevo +"', "
			            		+ "Contraseña = '"+contraseniaNuevo +"' "
			            				+ "WHERE "
			            				+ "STAFF.Usuario = '"+userSelecc+"';";

			           
			            stmt = conn.createStatement();
			   			ResultSet rs = stmt.executeQuery( sql );
			   			
			   			stmt.close();
			   			rs.close();
			   		}
					catch(SQLException | ClassNotFoundException e){
						
					}
				}
				
			}
		
			}
	}
	//busca el admin que se haya seleccionado en la tabla
	public Staff buscarAdminSelecc(){
		Registro registro = new Registro();
		Vector<Staff> staff = registro.recuperarStaff();
		registro.recuperarClientes();
		Staff nuevo = null;
		int i=0;
		boolean encontrado = false;
		
		// Recorremos el json staff
        while(i<staff.size() && encontrado==false) {

            // Comrpobamos que el administrador a modificar que se ha seleccionado
            if (staff.get(i).getUsuario().equals(
                    controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getUsuario())
                    && staff.get(i).getNombre()
                            .equals(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem()
                                    .getNombre())
                    && staff.get(i).getApellido1()
                            .equals(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem()
                                    .getApellido1())
                    && staff.get(i).getApellido2()
                            .equals(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem()
                                    .getApellido2())
                    && staff.get(i).getEmail()
                            .equals(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem()
                                    .getEmail())
                    && staff.get(i).getDni().equals(
                            controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem().getDni())
                    && staff.get(i).getFechaNacimiento()
                            .equals(controlerEditAdmin.getTableViewAdministrador().getSelectionModel().getSelectedItem()
                                    .getFechaNacimiento())
                    && staff.get(i).getContrasenia().equals(controlerEditAdmin.getTableViewAdministrador()
                            .getSelectionModel().getSelectedItem().getContrasenia())) {
            	
            	nuevo = new Staff(3, this.getTextUsuarioAdmin().getText(), this.textNombreAdmin.getText(), this.textApellido1Admin.getText(),
						this.textApellido2Admin.getText(), this.textDniAdmin.getText(), this.textEmailAdmin.getText(), this.textContraseniaAdmin.getText(), this.DateFechaAdmin.getValue());
            	encontrado=true;
            
            }
            i++;
        }
		return nuevo;
	}
	
	
	void eliminarContenidoTxtfield() {
		
		textUsuarioAdmin.clear();
		textNombreAdmin.clear();
		textApellido1Admin.clear();
		textApellido2Admin.clear();
		textDniAdmin.clear();
		textEmailAdmin.clear();
		textContraseniaAdmin.clear();
		DateFechaAdmin.getEditor().clear();
	}
	

	public ControladorEditarAdministrador getControlerEditAdmin() {
		return controlerEditAdmin;
	}

	public void setControlerEditAdmin(ControladorEditarAdministrador controlerEditAdmin) {
		this.controlerEditAdmin = controlerEditAdmin;
	}

	public AnchorPane getAnchorTabEditarAdmin() {
		return AnchorTabEditarAdmin;
	}

	public void setAnchorTabEditarAdmin(AnchorPane anchorTabEditarAdmin) {
		AnchorTabEditarAdmin = anchorTabEditarAdmin;
	}

	public GridPane getGridPaneEditarAdmin() {
		return gridPaneEditarAdmin;
	}

	public void setGridPaneEditarAdmin(GridPane gridPaneEditarAdmin) {
		this.gridPaneEditarAdmin = gridPaneEditarAdmin;
	}

	public TextField getTextUsuarioAdmin() {
		return textUsuarioAdmin;
	}

	public void setTextUsuarioAdmin(TextField textUsuarioAdmin) {
		this.textUsuarioAdmin = textUsuarioAdmin;
	}

	public TextField getTextNombreAdmin() {
		return textNombreAdmin;
	}

	public void setTextNombreAdmin(TextField textNombreAdmin) {
		this.textNombreAdmin = textNombreAdmin;
	}

	public TextField getTextApellido1Admin() {
		return textApellido1Admin;
	}

	public void setTextApellido1Admin(TextField textApellido1Admin) {
		this.textApellido1Admin = textApellido1Admin;
	}

	public TextField getTextApellido2Admin() {
		return textApellido2Admin;
	}

	public void setTextApellido2Admin(TextField textApellido2Admin) {
		this.textApellido2Admin = textApellido2Admin;
	}

	public TextField getTextEmailAdmin() {
		return textEmailAdmin;
	}

	public void setTextEmailAdmin(TextField textEmailAdmin) {
		this.textEmailAdmin = textEmailAdmin;
	}

	public TextField getTextDniAdmin() {
		return textDniAdmin;
	}

	public void setTextDniAdmin(TextField textDniAdmin) {
		this.textDniAdmin = textDniAdmin;
	}

	public TextField getTextContraseniaAdmin() {
		return textContraseniaAdmin;
	}

	public void setTextContraseniaAdmin(TextField textContraseniaAdmin) {
		this.textContraseniaAdmin = textContraseniaAdmin;
	}

	public DatePicker getDateFechaAdmin() {
		return DateFechaAdmin;
	}

	public void setDateFechaAdmin(DatePicker dateFechaAdmin) {
		DateFechaAdmin = dateFechaAdmin;
	}

	public JFXButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JFXButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

}

