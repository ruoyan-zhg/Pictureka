package Controlador;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorRegistro {

    @FXML
    private Pane PaneRegistro;

    @FXML
    private Button btnCrearUser;

    @FXML
    private Button btnCancelRegistro;

    @FXML
    private Label lblUser;

    @FXML
    private TextField txtfieldUser;

    @FXML
    private Label lblEmail;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private Label lbl_dni;

    @FXML
    private TextField txtFieldDNI;

    @FXML
    private Label lbl_calendario;

    @FXML
    private DatePicker chooserCalendario;

    @FXML
    private Label lbl_password;

    @FXML
    private PasswordField txtFieldPassword;

    @FXML
    private Label lbl_repeatPassword;

    @FXML
    private PasswordField txtFieldRepeatPassword;

    @FXML
    private ImageView imgPaneLogo;

}
