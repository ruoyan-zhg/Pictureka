package Controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ControladorTickets {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private ImageView imgCliente;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private BorderPane BorderPaneTickets;

    @FXML
    private GridPane gridPaneTickets;

    @FXML
    private Label lblTickets;

    @FXML
    private JFXTextField textTickets;

    @FXML
    private Label lblHorario;

    @FXML
    private JFXTimePicker hourTickets;

    @FXML
    private JFXDatePicker dateTickets;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnContinuar;

    @FXML
    private ImageView imgTickets;
    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
	
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
    
	 
	 public ControladorTickets(String usuario) {
		 if (usuario == "vacio") {
			 logged = false;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
		 }
		 
	}
    @FXML
    void CancelarReserva(ActionEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal(usuario);
        loaderPrincipal.setController(controlerPrincipal);
        AnchorPane PaneVentanaPrincipal;

		try {
			//Se carga en un AnchorPane la ventana
			PaneVentanaPrincipal = (AnchorPane) loaderPrincipal.load();
			
			//Se elimina el contenido de la ventana padre
        	anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneVentanaPrincipal, 0.0);
            AnchorPane.setRightAnchor(PaneVentanaPrincipal, 0.0);
            AnchorPane.setLeftAnchor(PaneVentanaPrincipal, 0.0);
            AnchorPane.setBottomAnchor(PaneVentanaPrincipal, 0.0);
            controlerPrincipal.setLogged(true);
            controlerPrincipal.getLblBienvenido().setVisible(false);
            controlerPrincipal.getBtnBarArriba().setStyle("-fx-background-color: #00aae4");
	        controlerPrincipal.getAvatarUsuario().setImage(new Image("/avatarCliente.png"));
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void ReservarTickets(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(MouseEvent event) {

    }

}

