package Controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import com.jfoenix.controls.JFXButton;

import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class ControladorRegistro {

    @FXML
    private AnchorPane anchorPaneRegistro;

    @FXML
    private GridPane gridRegistro;

    @FXML
    private Region regionRegistro;

    @FXML
    private ImageView imgPaneRegistro;

    @FXML
    private JFXButton btnCancelRegistro;

    @FXML
    private JFXButton btnRegistrar;

    @FXML
    private GridPane gridInfoRegistro;

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
    private PasswordField txtFieldPassword;

    @FXML
    private Label lblRepeatPassword;

    @FXML
    private PasswordField textFieldRepeatPassword;

    @FXML
    void CancelRegistro(ActionEvent event) {

    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/InterfazLogin.fxml"));
        ControladorInicioSesion controlerInicioSesion = new ControladorInicioSesion();
        loaderApp.setController(controlerInicioSesion);
        
        AnchorPane registerPane;
		try {
			registerPane = (AnchorPane) loaderApp.load();
	        anchorPaneRegistro.getChildren().clear();
	        AnchorPane.setTopAnchor(registerPane, 0.0);
	        AnchorPane.setRightAnchor(registerPane, 0.0);
	        AnchorPane.setLeftAnchor(registerPane, 0.0);
	        AnchorPane.setBottomAnchor(registerPane, 0.0);
	        anchorPaneRegistro.getChildren().setAll(registerPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        /*
        Parent root;
		try {
			root = loaderApp.load();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root));
	        stage.show();
	        
	        //Obtenemos la ventanaLogo
	        Stage primaryStage = (Stage)btnCancelRegistro.getScene().getWindow();
	        //Escondemos la ventana
	        primaryStage.hide();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }

    @FXML
    void RegistrarUsuario(ActionEvent event) {

    	modelo_Museo museo = new modelo_Museo();
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
    	String estado;
    	String contrasenia = txtFieldPassword.getText();
    	String repetirContrasenia = textFieldRepeatPassword.getText();
    	LocalDate fechaNuevo = chooserCalendario.getValue(); 
    	if(!(textUsuarioRegistro.getText().isEmpty() | textDni.getText().isEmpty() | textCorreoElectronico.getText().isEmpty() | contrasenia.isEmpty()
				| repetirContrasenia.isEmpty() |(fechaNuevo == null) )) {
    		
        		LocalDate fecha = LocalDate.now();
            	Period periodo = Period.between(chooserCalendario.getValue(), fecha);
        		if (periodo.getYears() > 18 && periodo.getYears() < 100) {
            		if(contrasenia.equals(repetirContrasenia)&&!(contrasenia.equals(""))&&!(contrasenia.equals(" "))) {
                		estado = museo.registrarClientes(textUsuarioRegistro.getText(), textDni.getText(), textCorreoElectronico.getText(), txtFieldPassword.getText(), chooserCalendario.getValue());
                		
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
        		else {
        			error.setHeaderText("Error: Rango de edad no aceptable");
           		 	error.show();
        		}
        	
    		
    	}
    	else {
    		error.setHeaderText("Porfavor rellene todos los campos!");
    		error.show();
    		
    	}
    	
    }

}
