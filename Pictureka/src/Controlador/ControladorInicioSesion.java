package Controlador;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorInicioSesion {

    @FXML
    private Label LabelUsuario;

    @FXML
    private Label LabelContraseña;

    @FXML
    private Button Registrarse;

    @FXML
    private Button IniciarSesion;

    @FXML
    private Label LabelPictureka;

    @FXML
    private TextField miTextoUsuario;

    @FXML
    private TextField miTextoContraseña;

    @FXML
    void IniciarSesion(ActionEvent event) {
    	String texto = miTextoUsuario.getText();

    }

    @FXML
    void Registrarse(ActionEvent event) {
    	String texto = miTextoContraseña.getText();

    }

}
