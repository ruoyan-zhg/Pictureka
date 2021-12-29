package Controlador;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControladorInformeAdmin {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private JFXTextField mostrarInforme;

    @FXML
    private TableView<?> tablaInformes;

    @FXML
    private JFXTextField tituloInforme;

    @FXML
    private JFXTextField cuerpoInforme;

    @FXML
    private ImageView imgFormulario;

    @FXML
    private ImageView imgEnviarInforme;
    
    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
	
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
    
	 
	 public ControladorInformeAdmin(String usuario) {
		 if (usuario == "vacio") {
			 logged = false;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
		 }
		 
	}

    @FXML
    void accederPerfil(MouseEvent event) {

    }

    @FXML
    void cerrarSesion(MouseEvent event) {

    }

    @FXML
    void enviarInforme(MouseEvent event) {

    }

}
