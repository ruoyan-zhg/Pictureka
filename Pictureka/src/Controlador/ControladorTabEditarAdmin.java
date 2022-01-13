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

    public ControladorTabEditarAdmin(ControladorEditarAdministrador controlerEdit) {
    	
    	this.controlerEditAdmin = controlerEdit;
    }
    
    
	@FXML
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
    void GuardarAdminEdit(ActionEvent event) {

		Alert error = new Alert(Alert.AlertType.ERROR);
		Alert informacion = new Alert(Alert.AlertType.INFORMATION);
		Registro registro = new Registro();
		Datos datos = new Datos();

		// Se guarda en un vector la informacion del json del personal de staff
		Vector<Staff> staff = datos.desserializarJsonStaff();
		int i = 0;

		String UsuarioNuevo;
		String nombreNuevo;
		String apellido1Nuevo;
		String apellido2Nuevo;
		String dniNuevo;
		String emailNuevo;
		LocalDate fechaNuevo;
		String contraseniaNuevo;

		// Obtenemos los datos de los diferentes jtextfield
		UsuarioNuevo = textUsuarioAdmin.getText();
		nombreNuevo = textNombreAdmin.getText();
		apellido1Nuevo = textApellido1Admin.getText();
		apellido2Nuevo = textApellido2Admin.getText();
		dniNuevo = textDniAdmin.getText();
		emailNuevo = textEmailAdmin.getText();
		fechaNuevo = DateFechaAdmin.getValue();
		contraseniaNuevo = textContraseniaAdmin.getText();

		// Comprobamos que haya seleccionado un administrador
		if (!controlerEditAdmin.getTableViewAdministrador().getSelectionModel().isEmpty()) {

			// Comprobamos que el contenido no est� vac�o
			if (!(UsuarioNuevo.isEmpty() | nombreNuevo.isEmpty() | apellido1Nuevo.isEmpty() | apellido2Nuevo.isEmpty()
					| dniNuevo.isEmpty() | emailNuevo.isEmpty() | (fechaNuevo == null) | contraseniaNuevo.isEmpty())) {

				registro.recuperarStaff();
				registro.recuperarUsuarios();

				// Recorremos el json staff
				for (i = 0; i < staff.size(); i++) {

					// Comrpobamos que el administrador a modificar que se ha seleccionado
					// se encuentra en el json
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

											informacion.setHeaderText("Cambios realizados con �xito.");
											informacion.showAndWait();

										} else {
											error.setHeaderText("Formato de email incorrecto.");
											error.showAndWait();
											System.out.println("Formato del email nuevo incorrecto");
										}

									} else {
										// Si el administrador mantiene su mismo email
										if (staff.get(i).getEmail().equals(emailNuevo)) {
											staff.get(i).setEmail(emailNuevo);
											System.out.println("Mismo email del administrador");

											staff.get(i).setNombre(nombreNuevo);
											staff.get(i).setApellido1(apellido1Nuevo);
											staff.get(i).setApellido2(apellido2Nuevo);
											staff.get(i).setDni(dniNuevo);
											staff.get(i).setContrasenia(contraseniaNuevo);

											registro.escribirStaffNuevo(staff);

											informacion.setHeaderText("Cambios realizados con �xito.");
											informacion.showAndWait();

										} else {
											error.setHeaderText("Email ya registrado.");
											error.showAndWait();
											System.out.println("Email de otro administrador");
										}

									}
								} else { // EL ADMIN MANTIENE SU MISMO DNI
									// si el administrador mantiene su mismo dni

									if (staff.get(i).getDni().equals(dniNuevo)) {
										staff.get(i).setDni(dniNuevo);
										System.out.println("Mismo dni del administrador");

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

												informacion.setHeaderText("Cambios realizados con �xito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Formato de email incorrecto.");
												error.showAndWait();
												System.out.println("Formato del email nuevo incorrecto");
											}

										} else {
											if (staff.get(i).getEmail().equals(emailNuevo)) {
												staff.get(i).setEmail(emailNuevo);
												System.out.println("Mismo email del administrador");

												staff.get(i).setNombre(nombreNuevo);
												staff.get(i).setApellido1(apellido1Nuevo);
												staff.get(i).setApellido2(apellido2Nuevo);
												staff.get(i).setContrasenia(contraseniaNuevo);

												registro.escribirStaffNuevo(staff);

												informacion.setHeaderText("Cambios realizados con �xito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Email ya registrado.");
												error.showAndWait();
												System.out.println("Email de otro administrador");
											}
										}

									} else {
										error.setHeaderText("Dni ya registrado.");
										error.showAndWait();
										System.out.println("Dni de otro administrador");
									}

								}

							}

							// EL ADMIN MANTIENE SU MISMO USUARIO
							else {
								// Si el guardia mantiene su mismo usuario
								if (staff.get(i).getUsuario().equals(UsuarioNuevo)) {
									staff.get(i).setUsuario(UsuarioNuevo);
									System.out.println("Mismo usuario del administrador");

									// devuelve true si el email del admin no esta repetido
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

												informacion.setHeaderText("Cambios realizados con �xito.");
												informacion.showAndWait();

											} else {
												error.setHeaderText("Formato de email incorrecto.");
												error.showAndWait();
												System.out.println("Formato del email nuevo incorrecto");
											}
										} else {
											// Si el dni es el mismo del admin

											if (staff.get(i).getDni().equals(dniNuevo)) {
												staff.get(i).setDni(dniNuevo);
												System.out.println("Mismo dni del administrador");

												if (registro.validarEmail(emailNuevo)) {
													staff.get(i).setEmail(emailNuevo);
													System.out.println("Formato del email nuevo correcto");

													staff.get(i).setNombre(nombreNuevo);
													staff.get(i).setApellido1(apellido1Nuevo);
													staff.get(i).setApellido2(apellido2Nuevo);
													staff.get(i).setContrasenia(contraseniaNuevo);

													registro.escribirStaffNuevo(staff);

													informacion.setHeaderText("Cambios realizados con �xito.");
													informacion.showAndWait();
												} else {
													error.setHeaderText("Formato de email incorrecto.");
													error.showAndWait();
													System.out.println("Formato del email nuevo incorrecto");
												}

											} else {
												error.setHeaderText("Dni ya registrado.");
												error.showAndWait();
												System.out.println("Dni de otro administrador");
											}

										}

									} else {
										// Si el email es el mismo del admin
										if (staff.get(i).getEmail().equals(emailNuevo)) {
											staff.get(i).setEmail(emailNuevo);
											System.out.println("Mismo email del administrador");

											if (registro.dniRepetido(dniNuevo) && registro.dniStaffRepetido(dniNuevo)) {
												System.out.println("Dni no repetido");

												staff.get(i).setNombre(nombreNuevo);
												staff.get(i).setApellido1(apellido1Nuevo);
												staff.get(i).setApellido2(apellido2Nuevo);
												staff.get(i).setDni(dniNuevo);
												staff.get(i).setContrasenia(contraseniaNuevo);

												registro.escribirStaffNuevo(staff);

												informacion.setHeaderText("Cambios realizados con �xito.");
												informacion.showAndWait();

											} else {
												// Mismo dni del admin
												if (staff.get(i).getDni().equals(dniNuevo)) {
													staff.get(i).setDni(dniNuevo);

													staff.get(i).setNombre(nombreNuevo);
													staff.get(i).setApellido1(apellido1Nuevo);
													staff.get(i).setApellido2(apellido2Nuevo);
													staff.get(i).setContrasenia(contraseniaNuevo);

													registro.escribirStaffNuevo(staff);

													informacion.setHeaderText("Cambios realizados con �xito.");
													informacion.showAndWait();

												} else {
													error.setHeaderText("Dni ya registrado.");
													error.showAndWait();
													System.out.println("Dni de otro administrador");
												}
											}
										} else {
											error.setHeaderText("Email ya registrado.");
											error.showAndWait();
											System.out.println("Email de otro administrador");
										}
									}

								} else {
									error.setHeaderText("Usuario ya registrado.");
									error.showAndWait();
									System.out.println("Usuario de otro administrador");
								}

							}

						} else {
							error.setHeaderText("Rango de edad no aceptable.");
							error.showAndWait();
						}

					}

				}
			} else {
				error.setHeaderText("Revise que todos losc campos est�n completos.");
				error.showAndWait();
			}
		} else {
			error.setHeaderText("No se ha seleccionado ning�n administrador a modificar.");
			error.showAndWait();
		}

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
