package Controlador;

import java.time.LocalDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import Modelo.Administrador;
import Modelo.Cifrado;
import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * En esta clase se maneja el registro de un administrador, en la vista <b>TabAniadirAdministrador</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */
public class ControladorTabAniadirAdmin {

	ControladorEditarAdministrador controlerEdit;
    @FXML
    private AnchorPane anchorPaneTab1;

    @FXML
    private GridPane gridTab1;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lbl1Apellido;

    @FXML
    private Label lbl2Apellido;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblDni;

    @FXML
    private Label lblFechaNacimiento;

    @FXML
    private Label lblContrasenia;

    @FXML
    private JFXButton btnGuardarAdmin;

    @FXML
    private JFXTextField textUsuario;

    @FXML
    private JFXTextField textNombre;

    @FXML
    private JFXTextField text1Apellido;

    @FXML
    private JFXTextField text2Apellido;

    @FXML
    private JFXTextField textEmail;

    @FXML
    private JFXTextField textDNI;

    @FXML
    private JFXTextField textContrasenia;

    @FXML
    private JFXDatePicker DateAdminNacimiento;
     
    /**
     * 
     * Constructor de la clase <b>ControladorTabAniadirAdmin</b> al que se le pasa el Controlador de ediccion de administradores.
     * 
     * @param controladorEdit	Controlador de la clase <b>ControladorEditAdministrador</b> con su respectivos atributos y m�todos.
     */
    public ControladorTabAniadirAdmin(ControladorEditarAdministrador controladorEdit) {
    	controlerEdit = controladorEdit;
    	
	}
    
    @FXML
    /**
     * 
     * Registra un nuevo administrador con la informaci�n introducida por el administrador. Se escribe en el Json de Staff y se muestra
     * en la tabla de administradores.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre el bot�n "A�adir".
     */
    void GuardarNuevoAdmin(ActionEvent event) {
    	
    	modelo_Museo modelo = new modelo_Museo();
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
    	Cifrado cifrar = new Cifrado();
    	
    	String usuarioNuevo;
    	String nombreNuevo;
    	String apellido1Nuevo;
    	String apellido2Nuevo;
    	String emailNuevo;
    	String dniNuevo;
    	String contraseniaNuevo;
    	LocalDate fechaNacimientoNuevo;
    	
    	//Recoge lo introducido por el usuario
    	usuarioNuevo = textUsuario.getText();
    	nombreNuevo = textNombre.getText();
    	apellido1Nuevo = text1Apellido.getText();
    	apellido2Nuevo = text2Apellido.getText();
    	emailNuevo = textEmail.getText();
    	dniNuevo = textDNI.getText();
    	contraseniaNuevo = textContrasenia.getText();
    	fechaNacimientoNuevo = DateAdminNacimiento.getValue();
    	
    	//Comprueba que los diferentes campos no esten vacios
    	if (usuarioNuevo.isEmpty() || nombreNuevo.isEmpty() || apellido1Nuevo.isEmpty() || apellido2Nuevo.isEmpty() || emailNuevo.isEmpty() ||
    			dniNuevo.isEmpty() || contraseniaNuevo.isEmpty() || fechaNacimientoNuevo==null) {
    			
    		error.setHeaderText("Error");
    		error.setContentText("Compruebe los campos a rellenar para añadir un guardia");
    		error.showAndWait();
    	}
    	else {
			// Se escribe en el json de usuarios

			String emailRepetido = "El email introducido ya ha sido registrado";
			String usuarioRepetido = "Usuario ya registrado";
			String validacion = "Validacion completada con exito";
			String emailIncorrecto = "El email introducido no es valido";
			String edadAceptable = "Rango de edad no aceptable";
			String dniRepetido = "El dni introducido ya ha sido registrado";
			
			// Dependiendo del estado que devuelva el metodo registrarGuardias, se realizara
			// una accion u otra
			if (modelo.registrarAdministradores(usuarioNuevo, dniNuevo, emailNuevo, cifrar.hashing(contraseniaNuevo), nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(validacion)) {
				// se muestra en la tabla al nuevo guardia
				this.controlerEdit.getTableViewAdministrador().getItems().add(new Administrador(usuarioNuevo, dniNuevo, emailNuevo,
						contraseniaNuevo, fechaNacimientoNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo));
				confirmacion.setHeaderText(validacion);
				confirmacion.showAndWait();
			}

			else if (modelo.registrarAdministradores(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(emailRepetido)) {

				error.setHeaderText(emailRepetido);
				error.showAndWait();
			} else if (modelo.registrarAdministradores(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(emailIncorrecto)) {

				error.setHeaderText(emailIncorrecto);
				error.showAndWait();
			} else if (modelo.registrarAdministradores(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(usuarioRepetido)) {

				error.setHeaderText(usuarioRepetido);
				error.showAndWait();
			} else if (modelo.registrarAdministradores(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(edadAceptable)) {

				error.setHeaderText(edadAceptable);
				error.showAndWait();

			} else if (modelo.registrarAdministradores(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(dniRepetido)) {
				error.setHeaderText(dniRepetido);
				error.showAndWait();

			}
		}
    	
    }

    
    
    
	public ControladorEditarAdministrador getControlerEdit() {
		return controlerEdit;
	}

	public void setControlerEdit(ControladorEditarAdministrador controlerEdit) {
		this.controlerEdit = controlerEdit;
	}

	public AnchorPane getAnchorPaneTab1() {
		return anchorPaneTab1;
	}

	public void setAnchorPaneTab1(AnchorPane anchorPaneTab1) {
		this.anchorPaneTab1 = anchorPaneTab1;
	}

	public GridPane getGridTab1() {
		return gridTab1;
	}

	public void setGridTab1(GridPane gridTab1) {
		this.gridTab1 = gridTab1;
	}

	public JFXButton getBtnGuardarAdmin() {
		return btnGuardarAdmin;
	}

	public void setBtnGuardarAdmin(JFXButton btnGuardarAdmin) {
		this.btnGuardarAdmin = btnGuardarAdmin;
	}

	public JFXTextField getTextUsuario() {
		return textUsuario;
	}

	public void setTextUsuario(JFXTextField textUsuario) {
		this.textUsuario = textUsuario;
	}

	public JFXTextField getTextNombre() {
		return textNombre;
	}

	public void setTextNombre(JFXTextField textNombre) {
		this.textNombre = textNombre;
	}

	public JFXTextField getText1Apellido() {
		return text1Apellido;
	}

	public void setText1Apellido(JFXTextField text1Apellido) {
		this.text1Apellido = text1Apellido;
	}

	public JFXTextField getText2Apellido() {
		return text2Apellido;
	}

	public void setText2Apellido(JFXTextField text2Apellido) {
		this.text2Apellido = text2Apellido;
	}

	public JFXTextField getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(JFXTextField textEmail) {
		this.textEmail = textEmail;
	}

	public JFXTextField getTextDNI() {
		return textDNI;
	}

	public void setTextDNI(JFXTextField textDNI) {
		this.textDNI = textDNI;
	}

	public JFXTextField getTextContrasenia() {
		return textContrasenia;
	}

	public void setTextContrasenia(JFXTextField textContrasenia) {
		this.textContrasenia = textContrasenia;
	}

	public JFXDatePicker getDateAdminNacimiento() {
		return DateAdminNacimiento;
	}

	public void setDateAdminNacimiento(JFXDatePicker dateAdminNacimiento) {
		DateAdminNacimiento = dateAdminNacimiento;
	}
    
    
    

}
