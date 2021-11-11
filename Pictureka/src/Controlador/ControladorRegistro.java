package Controlador;

	import javafx.event.ActionEvent;
import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.ColorPicker;
	import javafx.scene.control.DatePicker;
	import javafx.scene.control.Label;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
	import javafx.scene.image.ImageView;
	import javafx.scene.layout.Pane;

	public class ControladorRegistro {

	    @FXML
	    private Pane PaneRegistro;

	    @FXML
	    private TextField txtfieldUser;

	    @FXML
	    private Label lblUser;

	    @FXML
	    private Label lbl_repeatPassword;

	    @FXML
	    private Label lbl_password;

	    @FXML
	    private PasswordField txtFieldPassword;

	    @FXML
	    private PasswordField txtFieldRepeatPassword;

	    @FXML
	    private DatePicker chooserCalendario;

	    @FXML
	    private Label lbl_calendario;

	    @FXML
	    private Button BtnCrearUser;

	    @FXML
	    private ColorPicker comboBxColorAvatar;

	    @FXML
	    private Label lbl_colorAvatar;
	    
	   
	    @FXML
	    void cancelRegistro(ActionEvent event) {

	    }

	    @FXML
	    void crearUsuario(ActionEvent event) {

	    }

	}
	    
