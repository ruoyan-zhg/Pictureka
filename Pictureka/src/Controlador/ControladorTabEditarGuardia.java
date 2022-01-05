package Controlador;


import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;
import com.jfoenix.controls.JFXButton;
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

    
    public ControladorTabEditarGuardia(ControladorEditGuardias controlerEdit) {
    	
    	this.controlerEditGuardia = controlerEdit;
    }
    
    
    @FXML
    public void initialize() {
    	
    	//Se comprueba que el administrador ha seleccionado a un guardia de la tabla
        if (!controlerEditGuardia.getTableView().getSelectionModel().isEmpty()) {
        	
        	//Se recoge la informacion del guardia que el administrador ha seleccionado y se muestra en los diferentes campos
        	textUsuarioGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getUsuario());
        	textNombreGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getNombre());
        	textApellido1Guardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getApellido1());
        	textApellido2Guardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getApellido2());
        	textDniGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getDni());
        	textEmailGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getEmail());
        	DateFechaGuardia.setValue(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getFechaNacimiento());
        	textContraseniaGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getContrasenia());
    	
        	
        }

        
    }
    
    
    
    @FXML
    void GuardarGuardiaEdit(ActionEvent event) {
    	
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert informacion = new Alert(Alert.AlertType.INFORMATION);
    	Registro registro = new Registro();
    	Datos datos = new Datos();

    	//Se guarda en un vector la informacion del json del personal de staff
    	Vector <Staff> staff = datos.desserializarJsonStaff();
    	int i = 0;
    	
    	String UsuarioNuevo;
		String nombreNuevo;
		String apellido1Nuevo;
		String apellido2Nuevo;
		String dniNuevo;
		String emailNuevo;
		LocalDate fechaNuevo;
		String contraseniaNuevo;
		
		//Obtenemos los datos de los diferentes jtextfield
		UsuarioNuevo = textUsuarioGuardia.getText();
		nombreNuevo = textNombreGuardia.getText();
		apellido1Nuevo = textApellido1Guardia.getText();
		apellido2Nuevo = textApellido2Guardia.getText();
		dniNuevo = textDniGuardia.getText();
		emailNuevo = textEmailGuardia.getText();
		fechaNuevo = DateFechaGuardia.getValue();
		contraseniaNuevo = textContraseniaGuardia.getText();
		
		// Comprobamos que haya seleccionado un guardia
		if (!controlerEditGuardia.getTableView().getSelectionModel().isEmpty()) {

			// Comprobamos que el contenido no esté vacío
			if (!(UsuarioNuevo.isEmpty() | nombreNuevo.isEmpty() | apellido1Nuevo.isEmpty() | apellido2Nuevo.isEmpty()
					| dniNuevo.isEmpty() | emailNuevo.isEmpty() | (fechaNuevo == null) | contraseniaNuevo.isEmpty())) {

				registro.recuperarStaff();
				registro.recuperarUsuarios();

				// Recorremos el json staff
				for (i = 0; i < staff.size(); i++) {

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

						LocalDate fecha = LocalDate.now();
						Period periodo = Period.between(fechaNuevo, fecha);

						// Comprobaciones para los distintos casos que se pueden dar

						// Comprobacion del rango de edad
						if (periodo.getYears() > 18 && periodo.getYears() < 100) {
							staff.get(i).setFechaNacimiento(fechaNuevo);

							// devuelve true si el usuario no esta repetido
							if (registro.staffRepetido(UsuarioNuevo)) {
								staff.get(i).setUsuario(UsuarioNuevo);
								System.out.println("Usuario no repetido");

								// devuelve true si el dni no esta repetido
								if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {
									staff.get(i).setDni(dniNuevo);
									System.out.println("Dni no repetido");

									// devuelve true si el email no esta repetidp
									if (registro.emailRepetido(emailNuevo) && registro.emailRepetidoStaff(emailNuevo)) {
										System.out.println("Email no repetido");

										// Valida el email nuevo
										if (registro.validarEmail(emailNuevo)) {

											staff.get(i).setEmail(emailNuevo);
											System.out.println("Formato del email nuevo correcto");

											// Modifica los valores restantes del vector
											staff.get(i).setNombre(nombreNuevo);
											staff.get(i).setApellido1(apellido1Nuevo);
											staff.get(i).setApellido2(apellido2Nuevo);
											staff.get(i).setContrasenia(contraseniaNuevo);

											// Sobreescribe el contenido del vector
											registro.escribirStaffNuevo(staff);

											informacion.setHeaderText("Cambios realizados con éxito.");
											informacion.showAndWait();

										} else {
											error.setHeaderText("Formato de email incorrecto.");
											error.showAndWait();
											System.out.println("Formato del email nuevo incorrecto");
										}

									} else {
										// Si el guardia mantiene su mismo email
										if (staff.get(i).getEmail().equals(emailNuevo)) {
											staff.get(i).setEmail(emailNuevo);
											System.out.println("Mismo email del guardia");

											staff.get(i).setNombre(nombreNuevo);
											staff.get(i).setApellido1(apellido1Nuevo);
											staff.get(i).setApellido2(apellido2Nuevo);
											staff.get(i).setDni(dniNuevo);
											staff.get(i).setContrasenia(contraseniaNuevo);

											registro.escribirStaffNuevo(staff);

											informacion.setHeaderText("Cambios realizados con éxito.");
											informacion.showAndWait();

										} else {
											error.setHeaderText("Email ya registrado.");
											error.showAndWait();
											System.out.println("Email de otro guardia");
										}

									}
								} else { //EL GUARDIA MANTIENE SU MISMO DNI
									// si el guardia mantiene su mismo dni

									if (staff.get(i).getDni().equals(dniNuevo)) {
										staff.get(i).setEmail(dniNuevo);
										System.out.println("Mismo dni del guardia");

										if (registro.emailRepetido(emailNuevo)
												&& registro.emailRepetidoStaff(emailNuevo)) {
											System.out.println("Email no repetido");

											if (registro.validarEmail(emailNuevo)) {
												staff.get(i).setEmail(emailNuevo);
												System.out.println("Formato del email nuevo correcto");

												staff.get(i).setNombre(nombreNuevo);
												staff.get(i).setApellido1(apellido1Nuevo);
												staff.get(i).setApellido2(apellido2Nuevo);
												staff.get(i).setContrasenia(contraseniaNuevo);

												registro.escribirStaffNuevo(staff);

												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Formato de email incorrecto.");
												error.showAndWait();
												System.out.println("Formato del email nuevo incorrecto");
											}

										} else {
											if (staff.get(i).getEmail().equals(emailNuevo)) {
												staff.get(i).setEmail(emailNuevo);
												System.out.println("Mismo email del guardia");
												
												staff.get(i).setNombre(nombreNuevo);
												staff.get(i).setApellido1(apellido1Nuevo);
												staff.get(i).setApellido2(apellido2Nuevo);
												staff.get(i).setContrasenia(contraseniaNuevo);

												registro.escribirStaffNuevo(staff);

												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();
												

											} else {
												error.setHeaderText("Email ya registrado.");
												error.showAndWait();
												System.out.println("Email de otro guardia");
											}
										}

									} else {
										error.setHeaderText("Dni ya registrado.");
										error.showAndWait();
										System.out.println("Dni de otro guardia");
									}

								}

							}
							
							//EL GUARDIA MANTIENE SU MISMO USUARIO
							else {
								// Si el guardia mantiene su mismo usuario
								if (staff.get(i).getUsuario().equals(UsuarioNuevo)) {
									staff.get(i).setUsuario(UsuarioNuevo);
									System.out.println("Mismo usuario del guardia");

									// devuelve true si el email del guardia no esta repetido
									if (registro.emailRepetido(emailNuevo) && registro.emailRepetidoStaff(emailNuevo)) {
										System.out.println("Email no repetido");

										if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {
											System.out.println("Dni no repetido");
											staff.get(i).setDni(dniNuevo);

											// Se valdida el nuevo email
											if (registro.validarEmail(emailNuevo)) {

												staff.get(i).setEmail(emailNuevo);
												System.out.println("Formato del email nuevo correcto");

												staff.get(i).setNombre(nombreNuevo);
												staff.get(i).setApellido1(apellido1Nuevo);
												staff.get(i).setApellido2(apellido2Nuevo);
												staff.get(i).setContrasenia(contraseniaNuevo);

												registro.escribirStaffNuevo(staff);

												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Formato de email incorrecto.");
												error.showAndWait();
												System.out.println("Formato del email nuevo incorrecto");
											}
										} else {
											// Si el dni es el mismo del guardia

											if (staff.get(i).getDni().equals(dniNuevo)) {
												staff.get(i).setDni(dniNuevo);
												System.out.println("Mismo dni del guardia");

												if (registro.validarEmail(emailNuevo)) {
													staff.get(i).setEmail(emailNuevo);
													System.out.println("Formato del email nuevo correcto");

													staff.get(i).setNombre(nombreNuevo);
													staff.get(i).setApellido1(apellido1Nuevo);
													staff.get(i).setApellido2(apellido2Nuevo);
													staff.get(i).setContrasenia(contraseniaNuevo);

													registro.escribirStaffNuevo(staff);

													informacion.setHeaderText("Cambios realizados con éxito.");
													informacion.showAndWait();
												} else {
													error.setHeaderText("Formato de email incorrecto.");
													error.showAndWait();
													System.out.println("Formato del email nuevo incorrecto");
												}

											} else {
												error.setHeaderText("Dni ya registrado.");
												error.showAndWait();
												System.out.println("Dni de otro guardia");
											}

										}
										
									} else {
										// Si el email es el mismo del guardia
										if (staff.get(i).getEmail().equals(emailNuevo)) {
											staff.get(i).setEmail(emailNuevo);
											System.out.println("Mismo email del guardia");

											if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {
												System.out.println("Dni no repetido");

												staff.get(i).setNombre(nombreNuevo);
												staff.get(i).setApellido1(apellido1Nuevo);
												staff.get(i).setApellido2(apellido2Nuevo);
												staff.get(i).setDni(dniNuevo);
												staff.get(i).setContrasenia(contraseniaNuevo);

												registro.escribirStaffNuevo(staff);

												informacion.setHeaderText("Cambios realizados con éxito.");
												informacion.showAndWait();

											} else {
												// Mismo dni del guardia
												if (staff.get(i).getEmail().equals(dniNuevo)) {
													staff.get(i).setDni(dniNuevo);

													staff.get(i).setNombre(nombreNuevo);
													staff.get(i).setApellido1(apellido1Nuevo);
													staff.get(i).setApellido2(apellido2Nuevo);
													staff.get(i).setContrasenia(contraseniaNuevo);

													registro.escribirStaffNuevo(staff);

													informacion.setHeaderText("Cambios realizados con éxito.");
													informacion.showAndWait();

												} else {
													error.setHeaderText("Dni ya registrado.");
													error.showAndWait();
													System.out.println("Dni de otro guardia");
												}
											}
										} else {
											error.setHeaderText("Email ya registrado.");
											error.showAndWait();
											System.out.println("Email de otro guardia");
										}
									}

								} else {
									error.setHeaderText("Usuario ya registrado.");
									error.showAndWait();
									System.out.println("Usuario de otro guardia");
								}

							}

						} else {
							error.setHeaderText("Rango de edad no aceptable.");
							error.showAndWait();
						}

					}

				}
			} else {
				error.setHeaderText("Revise que todos losc campos estén completos.");
				error.showAndWait();
			}
		} else {
			error.setHeaderText("No se ha seleccionado ningún guardia a modificar.");
			error.showAndWait();
		}
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
