package Controlador;

import java.time.LocalDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Modelo.Cifrado;
import Modelo.Guardia;
import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * En esta clase se maneja el registro de un guardia, en la vista <b>TabAniadirGuardia</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */

public class ControladorTabAniadirGuardia {

	ControladorEditGuardias controlerEdit;
	
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
    private JFXButton btnGuardar;

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
    private JFXDatePicker DateGuardiaNacimiento;
     
	
    /**
     * 
     * Constructor de la clase <b>ControladorTabAdniadirGuardia</b> al que se le pasa el Controlador de ediccion de guardias.
     * 
     * @param controladorEdit	Controlador de la clase <b>ControladorEditGuardias</b> con su respectivos atributos y m�todos. 
     */
    public ControladorTabAniadirGuardia(ControladorEditGuardias controladorEdit) {
    	controlerEdit = controladorEdit;
    	
	}
    
    
    @FXML
    /**
     * 
     * Registra un nuevo guardia con la informaci�n introducida por el administrador. Se escribe en el Json de Staff y se muestra
     * en la tabla de guardias.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre el bot�n "A�adir".
     */
    void GuardarNuevoGuardia(ActionEvent event) {
    	
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
    	fechaNacimientoNuevo = DateGuardiaNacimiento.getValue();
    	
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
			if (modelo.registrarGuardias(usuarioNuevo, dniNuevo, emailNuevo, cifrar.hashing(contraseniaNuevo), nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(validacion)) {
				// se muestra en la tabla al nuevo guardia
				this.controlerEdit.getTableView().getItems().add(new Guardia(usuarioNuevo, dniNuevo, emailNuevo,
						contraseniaNuevo, fechaNacimientoNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo));
				confirmacion.setHeaderText(validacion);
				confirmacion.showAndWait();
			}

			else if (modelo.registrarGuardias(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(emailRepetido)) {

				error.setHeaderText(emailRepetido);
				error.showAndWait();
			} else if (modelo.registrarGuardias(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(emailIncorrecto)) {

				error.setHeaderText(emailIncorrecto);
				error.showAndWait();
			} else if (modelo.registrarGuardias(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(usuarioRepetido)) {

				error.setHeaderText(usuarioRepetido);
				error.showAndWait();
			} else if (modelo.registrarGuardias(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(edadAceptable)) {

				error.setHeaderText(edadAceptable);
				error.showAndWait();

			} else if (modelo.registrarGuardias(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo,
					apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo).equals(dniRepetido)) {
				error.setHeaderText(dniRepetido);
				error.showAndWait();

			}
		}
	}

	public AnchorPane getAnchorPaneTab1() {
		return anchorPaneTab1;
	}

	public void setAnchorPaneTab1(AnchorPane anchorPaneTab1) {
		this.anchorPaneTab1 = anchorPaneTab1;
	}

}
