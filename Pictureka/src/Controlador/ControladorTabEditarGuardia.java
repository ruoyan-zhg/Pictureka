package Controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;
import com.jfoenix.controls.JFXButton;

import Modelo.Cifrado;
import Modelo.Datos;
import Modelo.Guardia;
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
 * En esta clase se maneja la edición de la información de un guardia en
 * específico, en la vista de <b>TabEditarGuardia</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */
public class ControladorTabEditarGuardia {

	ControladorEditGuardias controlerEditGuardia;
	@FXML
	private AnchorPane AnchorTabEditarGuardia;

	@FXML
	private GridPane gridPaneEditarGuardia;

	@FXML
	private Label lblUsuarioGuardia;

	@FXML
	private TextField textUsuarioGuardia;

	@FXML
	private Label lblNombreGuardia;

	@FXML
	private TextField textNombreGuardia;

	@FXML
	private Label lblApellido1Guardia;

	@FXML
	private Label lblApellido2Guardia;

	@FXML
	private TextField textApellido1Guardia;

	@FXML
	private TextField textApellido2Guardia;

	@FXML
	private Label lblEmailGuardia;

	@FXML
	private TextField textEmailGuardia;

	@FXML
	private Label lblDniGuardia;

	@FXML
	private TextField textDniGuardia;

	@FXML
	private Label lblFechaGuardia;

	@FXML
	private Label lblContraseniaGuardia;

	@FXML
	private TextField textContraseniaGuardia;

	@FXML
	private DatePicker DateFechaGuardia;

	@FXML
	private JFXButton btnGuardar;
	
	static final String USER = "pri_Pictureka";
	static final String PASS = "asas";
	Connection conn = null;
	Statement stmt = null;
	String sql;

	Guardia guardia;

	/**
	 * 
	 * Constructor de la clase <b>ControladorTabEditarGuardia</b> al que se le pasa
	 * el Controlador de edicciones de guardias.
	 * 
	 * @param controlerEdit Controlador de la clase <b>ControladorEditGuardias</b>
	 *                      con sus respecitvos atributos y métodos.
	 */
	public ControladorTabEditarGuardia(ControladorEditGuardias controlerEdit) {

		this.controlerEditGuardia = controlerEdit;
	}

	@FXML
	/**
	 * 
	 * Inicializa el Tab, comrobando si el administrador ha seleccionado un guardia
	 * a editar.
	 * 
	 */
	public void initialize() {

		// Se comprueba que el administrador ha seleccionado a un guardia de la tabla
		if (!controlerEditGuardia.getTableView().getSelectionModel().isEmpty()) {

			// Se recoge la informacion del guardia que el administrador ha seleccionado y
			// se muestra en los diferentes campos
			textUsuarioGuardia
					.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getUsuario());
			textNombreGuardia
					.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getNombre());
			textApellido1Guardia
					.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getApellido1());
			textApellido2Guardia
					.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getApellido2());
			textDniGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getDni());
			textEmailGuardia
					.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getEmail());
			DateFechaGuardia.setValue(
					controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getFechaNacimiento());
			textContraseniaGuardia.setText(
					controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getContrasenia());
		}
	}

	@FXML
	/**
	 * 
	 * Guarda los cambios realizados en uno o más de los campos de información del guardia, cambiando el contenido del Json de Staff.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre el botón "Guardar Cambios".
	 */
	void GuardarGuardiaEdit(ActionEvent event) {

		Alert error = new Alert(Alert.AlertType.ERROR);
		Alert informacion = new Alert(Alert.AlertType.INFORMATION);
		Registro registro = new Registro();
		Cifrado cifrar = new Cifrado();
		
		// Se guarda en un vector la informacion del json del personal de staff
		

		// Obtenemos los datos de los diferentes jtextfield
		String UsuarioNuevo = textUsuarioGuardia.getText();
		String nombreNuevo = textNombreGuardia.getText();
		String apellido1Nuevo = textApellido1Guardia.getText();
		String apellido2Nuevo = textApellido2Guardia.getText();
		String dniNuevo = textDniGuardia.getText();
		String emailNuevo = textEmailGuardia.getText();
		LocalDate fechaNuevo = DateFechaGuardia.getValue();
		String contraseniaNuevo = textContraseniaGuardia.getText();

		// Comprobamos que haya seleccionado un guardia
		if (!controlerEditGuardia.getTableView().getSelectionModel().isEmpty()) {

			// Comprobamos que el contenido no está vacío
			if (!(UsuarioNuevo.isEmpty() | nombreNuevo.isEmpty() | apellido1Nuevo.isEmpty() | apellido2Nuevo.isEmpty()
					| dniNuevo.isEmpty() | emailNuevo.isEmpty() | (fechaNuevo == null) | contraseniaNuevo.isEmpty())) {

				Staff staff = buscarGuardiaSelecc();
				if((staff.getContrasenia().equals(cifrar.hashing(contraseniaNuevo))==false)) {
					contraseniaNuevo = cifrar.hashing(contraseniaNuevo);
					
				}
						LocalDate fecha = LocalDate.now();
						Period periodo = Period.between(fechaNuevo, fecha);

						// Comprobaciones para los distintos casos que se pueden dar

						// Comprobacion del rango de edad
						if(dniNuevo.length()==9) {
							if (periodo.getYears() > 18 && periodo.getYears() < 100) {
								
								// devuelve true si el usuario no esta repetido
								if (registro.staffRepetido(UsuarioNuevo)) {
									
									// devuelve true si el dni no esta repetido
									if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {
			
										// devuelve true si el email no esta repetido
										if (registro.emailRepetido(emailNuevo) && registro.emailRepetidoStaff(emailNuevo)) {
											

											// Valida el email nuevo
											if (registro.validarEmail(emailNuevo)) {
												
												
												GuardarGuardiaBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
												controlerEditGuardia.actualizarTablaGuardias();
												eliminarContenidoTxtfield();
												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Formato de email incorrecto.");
												error.showAndWait();
												
											}

										} else {
											// Si el guardia mantiene su mismo email
											if (staff.getEmail().equals(emailNuevo)) {
												
												GuardarGuardiaBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
												controlerEditGuardia.actualizarTablaGuardias();
												eliminarContenidoTxtfield();

												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Email ya registrado.");
												error.showAndWait();
												
											}

										}
									} else { // EL GUARDIA MANTIENE SU MISMO DNI
										// si el guardia mantiene su mismo dni

										if (staff.getDni().equals(dniNuevo)) {
																			

											if (registro.emailRepetido(emailNuevo)
													&& registro.emailRepetidoStaff(emailNuevo)) {
												

												if (registro.validarEmail(emailNuevo)) {
													staff.setEmail(emailNuevo);
													

													GuardarGuardiaBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
													controlerEditGuardia.actualizarTablaGuardias();
													eliminarContenidoTxtfield();

													informacion.setHeaderText("Cambios realizados con éxito.");
													informacion.showAndWait();

												} else {
													error.setHeaderText("Formato de email incorrecto.");
													error.showAndWait();
													
												}

											} else {
												if (staff.getEmail().equals(emailNuevo)) {
													
													GuardarGuardiaBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
													controlerEditGuardia.actualizarTablaGuardias();
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

								// EL GUARDIA MANTIENE SU MISMO USUARIO
								else {
									// Si el guardia mantiene su mismo usuario
									if (staff.getUsuario().equals(UsuarioNuevo)) {
																	

										// devuelve true si el email del guardia no esta repetido
										if (registro.emailRepetido(emailNuevo) && registro.emailRepetidoStaff(emailNuevo)) {
											

											if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {

												// Se valdida el nuevo email
												if (registro.validarEmail(emailNuevo)) {

													GuardarGuardiaBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
													controlerEditGuardia.actualizarTablaGuardias();
													eliminarContenidoTxtfield();

													informacion.setHeaderText("Cambios realizados con éxito.");
													informacion.showAndWait();

												} else {
													error.setHeaderText("Formato de email incorrecto.");
													error.showAndWait();
													
												}
											} else {
												// Si el dni es el mismo del guardia

												if (staff.getDni().equals(dniNuevo)) {
													
													

													if (registro.validarEmail(emailNuevo)) {
														

														GuardarGuardiaBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
														controlerEditGuardia.actualizarTablaGuardias();
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
											// Si el email es el mismo del guardia
											if (staff.getEmail().equals(emailNuevo)) {
												
												

												if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {
													

													GuardarGuardiaBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
													controlerEditGuardia.actualizarTablaGuardias();
													eliminarContenidoTxtfield();

													informacion.setHeaderText("Cambios realizados con éxito.");
													informacion.showAndWait();

												} else {
													// Mismo dni del guardia
													if (staff.getDni().equals(dniNuevo)) {
														
														GuardarGuardiaBBDD(UsuarioNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, dniNuevo, emailNuevo, fechaNuevo, contraseniaNuevo);
														controlerEditGuardia.actualizarTablaGuardias();
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
						}
						else {
							error.setHeaderText("El DNI debe tener una longitud de 9 digitos.");
							error.showAndWait();
						}

					
			} else {
				error.setHeaderText("Revise que todos los campos están completos.");
				error.showAndWait();
			}
		} else {
			error.setHeaderText("No se ha seleccionado ningún guardia a modificar.");
			error.showAndWait();
		}
	}

	
	void GuardarGuardiaBBDD(String UsuarioNuevo,String nombreNuevo,String apellido1Nuevo,String apellido2Nuevo,String dniNuevo,String emailNuevo,LocalDate fechaNuevo,String contraseniaNuevo) {

		Date date = Date.valueOf(fechaNuevo);
		
		String userSelecc = controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getUsuario();
		
		if (!controlerEditGuardia.getTableView().getSelectionModel().isEmpty()) {

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

			            //STEP 2: Open a connection
			            System.out.println("Connecting to a selected database...");

			            conn = DriverManager.getConnection(
			                    "jdbc:mariadb://195.235.211.197/priPictureka", USER, PASS);
			            System.out.println("Connectado a la Base de Datos...");
			            sql = "UPDATE STAFF SET "
			            		+ "Usuario = '"+UsuarioNuevo+"', "
			            		+ "Nombre = '"+ nombreNuevo +"', "
			            		+ "Apellido1 = '"+apellido1Nuevo +"',"
	            				+ "Apellido2 = '"+apellido2Nuevo+"', "
			            		+ "identificadorUser = 2, "
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
	
	
	
	
	
	public Staff buscarGuardiaSelecc() {
		Registro registro = new Registro();
		Vector<Staff> staff = registro.recuperarStaff();
		registro.recuperarClientes();
		Staff nuevo = null;
		int i=0;
		boolean encontrado = false;
		while(i<staff.size() && encontrado==false) {

			// Comrpobamos que el guardia a modificar que ha seleccionado el administrador
			// se encuentra en el json
			if (staff.get(i).getUsuario().equals(
					controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getUsuario())
					&& staff.get(i).getNombre()
							.equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem()
									.getNombre())
					&& staff.get(i).getApellido1()
							.equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem()
									.getApellido1())
					&& staff.get(i).getApellido2()
							.equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem()
									.getApellido2())
					&& staff.get(i).getEmail()
							.equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem()
									.getEmail())
					&& staff.get(i).getDni().equals(
							controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getDni())
					&& staff.get(i).getFechaNacimiento()
							.equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem()
									.getFechaNacimiento())
					&& staff.get(i).getContrasenia().equals(controlerEditGuardia.getTableView()
							.getSelectionModel().getSelectedItem().getContrasenia())) {

				nuevo = new Staff(2, this.getTextUsuarioGuardia().getText(), this.textNombreGuardia.getText(), this.textApellido1Guardia.getText(),
						this.textApellido2Guardia.getText(), this.textDniGuardia.getText(), this.textEmailGuardia.getText(), this.textContraseniaGuardia.getText(), this.DateFechaGuardia.getValue());
            	encontrado=true;
			
			}
			i++;
		}
		return nuevo;
	}
	
	void eliminarContenidoTxtfield() {
			
			textUsuarioGuardia.clear();
			textNombreGuardia.clear();
			textApellido1Guardia.clear();
			textApellido2Guardia.clear();
			textDniGuardia.clear();
			textEmailGuardia.clear();
			textContraseniaGuardia.clear();
			DateFechaGuardia.getEditor().clear();
		}
		
	
	
	
	
	
	
	
	

	public AnchorPane getAnchorTabEditarGuardia() {
		return AnchorTabEditarGuardia;
	}

	public void setAnchorTabEditarGuardia(AnchorPane anchorTabEditarGuardia) {
		AnchorTabEditarGuardia = anchorTabEditarGuardia;
	}

	public TextField getTextUsuarioGuardia() {
		return textUsuarioGuardia;
	}

	public JFXButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JFXButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public void setTextUsuarioGuardia(TextField textUsuarioGuardia) {
		this.textUsuarioGuardia = textUsuarioGuardia;
	}

	public TextField getTextNombreGuardia() {
		return textNombreGuardia;
	}

	public void setTextNombreGuardia(TextField textNombreGuardia) {
		this.textNombreGuardia = textNombreGuardia;
	}

	public TextField getTextApellido1Guardia() {
		return textApellido1Guardia;
	}

	public void setTextApellido1Guardia(TextField textApellido1Guardia) {
		this.textApellido1Guardia = textApellido1Guardia;
	}

	public DatePicker getDateFechaGuardia() {
		return DateFechaGuardia;
	}

	public void setDateFechaGuardia(DatePicker dateFechaGuardia) {
		DateFechaGuardia = dateFechaGuardia;
	}

	public TextField getTextApellido2Guardia() {
		return textApellido2Guardia;
	}

	public void setTextApellido2Guardia(TextField textApellido2Guardia) {
		this.textApellido2Guardia = textApellido2Guardia;
	}

	public TextField getTextEmailGuardia() {
		return textEmailGuardia;
	}

	public void setTextEmailGuardia(TextField textEmailGuardia) {
		this.textEmailGuardia = textEmailGuardia;
	}

	public TextField getTextDniGuardia() {
		return textDniGuardia;
	}

	public void setTextDniGuardia(TextField textDniGuardia) {
		this.textDniGuardia = textDniGuardia;
	}

	public TextField getTextContraseniaGuardia() {
		return textContraseniaGuardia;
	}

	public void setTextContraseniaGuardia(TextField textContraseniaGuardia) {
		this.textContraseniaGuardia = textContraseniaGuardia;
	}

}
