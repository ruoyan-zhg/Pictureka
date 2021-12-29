package Controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ControladorSalas {

    @FXML
    private AnchorPane anchorPaneSala;

    @FXML
    private VBox VBoxSala;

    @FXML
    private JFXToolbar ToolBarSala;

    @FXML
    private ImageView imgGuardia;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private GridPane GridPaneSala;

    @FXML
    private ImageView imgTemperatura;

    @FXML
    private ImageView imgLuz;

    @FXML
    private TextArea textTemperatura;

    @FXML
    private TextArea textLuz;

    @FXML
    private GridPane GridSensorDistancia;

    @FXML
    private ImageView imgDistancia;

    @FXML
    private JFXButton btnA;

    @FXML
    private JFXButton btnB;

    @FXML
    private JFXButton btnC;

    @FXML
    private JFXButton btnD;
    
    private String usuario;
    
    boolean logged; //Este nos dira si la parsona esta logueada o no
	 
	 public ControladorSalas(String usuario) {
		 if (usuario == "vacio") {
			 logged = false;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
		 }
		 
	}

    @FXML
    void cerrarSesion(MouseEvent event) {

    }

    @FXML
    void verPerfil(MouseEvent event) {
    	if(logged == false) {
        	Alert error = new Alert(Alert.AlertType.ERROR);
			error.setHeaderText("Oh no! Para acceder a esta función debes estar iniciado sesión.");
    		error.showAndWait();
        	
        }
        else {
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderPrincipala = new FXMLLoader(getClass().getResource("/application/VentanaPerfil.fxml"));
        	//Se le asigna el controlador de la ventana para editar información de los guardias
            ControladorPerfil controlerPrincipal = new ControladorPerfil(usuario);
            loaderPrincipala.setController(controlerPrincipal);
            AnchorPane PaneVentanaPrincipal;

    		try {
    			//Se carga en un AnchorPane la ventana
    			PaneVentanaPrincipal = (AnchorPane) loaderPrincipala.load();
    			
    			//Se elimina el contenido de la ventana padre
    			anchorPaneSala.getChildren().clear();
            	
            	//Se ajusta el AnchorPane para que sea escalable
                AnchorPane.setTopAnchor(PaneVentanaPrincipal, 0.0);
                AnchorPane.setRightAnchor(PaneVentanaPrincipal, 0.0);
                AnchorPane.setLeftAnchor(PaneVentanaPrincipal, 0.0);
                AnchorPane.setBottomAnchor(PaneVentanaPrincipal, 0.0);
                

                //Se añade el contenido de la ventana cargada en el AnchorPane del padre
                anchorPaneSala.getChildren().setAll(PaneVentanaPrincipal);
                
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }

    }
}
