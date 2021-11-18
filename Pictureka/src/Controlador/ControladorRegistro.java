package Controlador;

import java.io.IOException;
import java.time.LocalDate;

import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControladorRegistro {
	
	@FXML
	private AnchorPane anchorPaneRegistro;

    @FXML
    private VBox vboxRegistro;

    @FXML
    private Pane paneRegistro;

    @FXML
    private ImageView imgPaneRegistro;

    @FXML
    private Label lblUsuarioRegistro;

    @FXML
    private TextField textUsuarioRegistro;

    @FXML
    private Label lblCorreoElectronico;

    @FXML
    private TextField textCorreoElectronico;

    @FXML
    private Label lblDni;

    @FXML
    private TextField textDni;

    @FXML
    private Label lblFechaNacimiento;

    @FXML
    private DatePicker chooserCalendario;

    @FXML
    private Label lblContraseniaRegistro;

    @FXML
    private Label lblRepeatPassword;

    @FXML
    private PasswordField textFieldRepeatPassword;

    @FXML
    private PasswordField txtFieldPassword;

    @FXML
    private Button btnCancelRegistro;

    @FXML
    private Button btnRegistrar;

    @FXML
    void CancelRegistro(ActionEvent event) {
    	
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/InterfazLogin.fxml"));
        ControladorInicioSesion controlerInicioSesion = new ControladorInicioSesion();
        loaderApp.setController(controlerInicioSesion);
        try {
            Pane registerPane = (Pane) loaderApp.load();
            paneRegistro.getChildren().clear();
            paneRegistro.getChildren().add(registerPane);
            
           
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    	
    	
    	
    }

    @FXML
    void RegistrarUsuario(ActionEvent event) {
    	modelo_Museo museo = new modelo_Museo();
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
    	
    	String estado;
    	if(txtFieldPassword.getText().equals(textFieldRepeatPassword.getText())) {
    		estado = museo.registrarClientes(textUsuarioRegistro.getText(), textDni.getText(), textCorreoElectronico.getText(), txtFieldPassword.getText());
    		//LocalDate date = chooserCalendario.getValue();
    		//System.out.println(date);
    		if (estado.equals("Validacion completada con exito")) {
    			confirmacion.setHeaderText(estado);
    			confirmacion.show();
    		}
    		else {
    			
    			error.setHeaderText(estado);
    			error.show();
    		}
    		
    	}
    	else {
    		 error.setHeaderText("Error: las contraseñas no coinciden");
    		 error.show();
    	}
    	
    }

}

