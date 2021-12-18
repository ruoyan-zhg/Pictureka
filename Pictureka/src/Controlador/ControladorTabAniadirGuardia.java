package Controlador;

import java.time.LocalDate;
import java.util.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Modelo.Guardia;
import Modelo.Registro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ControladorTabAniadirGuardia {

	ControladorEditGuardias controlerEdit = new ControladorEditGuardias();
	
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

    
    public ControladorTabAniadirGuardia(ControladorEditGuardias controladorEdit) {

    	controlerEdit = controladorEdit;
	}
    
    
    @FXML
    void GuardarNuevoGuardia(ActionEvent event) {
    	
    	Registro registro = new Registro();
    	
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
    	
    	//Se escribe en el json de usuarios
    	registro.registrarGuardia(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo, fechaNacimientoNuevo);
    	//Se supone que se tiene que añadir a la tabla pero saca un null pointer
    	this.controlerEdit.getTableView().getItems().add(new Guardia(usuarioNuevo, dniNuevo, emailNuevo, contraseniaNuevo, fechaNacimientoNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo));
    	
    	
    	
    }

	public AnchorPane getAnchorPaneTab1() {
		return anchorPaneTab1;
	}

	public void setAnchorPaneTab1(AnchorPane anchorPaneTab1) {
		this.anchorPaneTab1 = anchorPaneTab1;
	}

}
