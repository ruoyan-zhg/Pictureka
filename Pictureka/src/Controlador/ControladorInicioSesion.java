package Controlador;


import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControladorInicioSesion {
	@FXML
	private AnchorPane MyAnchorPane;

    @FXML
    private Pane paneLogin;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblContrasenia;

    @FXML
    private TextField textUsuario;

    @FXML
    private TextField textConstrasenia;

    @FXML
    private JFXButton btnInicioSesion;

    @FXML
    private JFXButton btnRegistrarse;

    @FXML
    void InicarSesion(ActionEvent event) {
    	modelo_Museo museo = new modelo_Museo();
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
    	if (museo.loginUsuario(textUsuario.getText(), textConstrasenia.getText())) {
    		confirmacion.setHeaderText("Login correcto");
    		confirmacion.show();
    	}
    	else {
    		error.setHeaderText("Error: El usuario/email o contraseña introducida son incorrectas");
    		error.show();
    	}

    }

    @FXML
    void Registrarse(ActionEvent event) {
    	
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaRegistro.fxml"));
        ControladorRegistro controlerRegistro = new ControladorRegistro();
        loaderApp.setController(controlerRegistro);
        try {
            Pane registerPane = (Pane) loaderApp.load();
            paneLogin.getChildren().clear();
            paneLogin.getChildren().add(registerPane);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    	

    }

}







