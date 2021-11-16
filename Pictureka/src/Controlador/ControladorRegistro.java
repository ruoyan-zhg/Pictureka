package Controlador;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    	
    	
    }

}

