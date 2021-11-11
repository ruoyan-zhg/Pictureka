package Controlador;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControladorInicioSesion {

    @FXML
    private AnchorPane anchorPaneLogin;

    @FXML
    private Label LabelUsuario;

    @FXML
    private Label LabelContrasenia;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Label LabelPictureka;

    @FXML
    private TextField miTextoUsuario;

    @FXML
    private TextField miTextoContrasenia;

    @FXML
    void IniciarSesion(ActionEvent event) {

    }

    @FXML
    void Registrarse(ActionEvent event) {
    	
    	FXMLLoader loaderRegistro = new FXMLLoader(getClass().getResource("/application/VentanaRegistro.fxml"));
        ControladorInicioSesion controlerInicio = new ControladorInicioSesion();
        loaderRegistro.setController(controlerInicio);
        Parent root;
        
        try {
            Pane registerPane = (Pane) loaderRegistro.load();
            anchorPaneLogin.getChildren().clear();
            anchorPaneLogin.getChildren().add(registerPane);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

}

