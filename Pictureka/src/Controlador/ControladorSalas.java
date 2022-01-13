package Controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;

import Modelo.Museo;
import Modelo.Sala;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
    private ImageView imgAvatar;

    @FXML
    private ImageView imgVolverGuardia;

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
    
    private Sala sala;
    
    private String tipoStaff;
	 
	 public ControladorSalas(String usuario, Sala _sala, String _tipoStaff) {
		 if (usuario == "vacio") {
			 logged = false;
			 this.usuario = usuario;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
			 this.sala = _sala;
			 tipoStaff =_tipoStaff;
		 }
		 
	}
	@FXML
	public void initialize() {
		Museo museo = new Museo();
		textLuz.setText("Actualmente esta cargada la sala "+sala.getIdentificador());
		if (tipoStaff.equals("Guardia")) {
			imgAvatar.setImage(new Image("/guardiaAvatar.png"));
		}
		else {
			imgAvatar.setImage(new Image("/administradorAvatar.png"));
		}
	}

	 	
	@FXML
	void volverAtrasSalas(MouseEvent event) {
		if(tipoStaff.equals("Guardia")) {
			//Se carga el contenido de la ventana
	    	FXMLLoader loaderGuardia = new FXMLLoader(getClass().getResource("/application/VentanaGuardia.fxml"));
	    	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
	        ControladorGuardia controlerGuardia = new ControladorGuardia(usuario);
	        loaderGuardia.setController(controlerGuardia);
	        AnchorPane PaneGuardia;
			try {
				//Se carga en un AnchorPane la ventana
				PaneGuardia = (AnchorPane) loaderGuardia.load();
				
				//Se elimina el contenido de la ventana padre
				anchorPaneSala.getChildren().clear();
	        	
	        	//Se ajusta el AnchorPane para que sea escalable
	            AnchorPane.setTopAnchor(PaneGuardia, 0.0);
	            AnchorPane.setRightAnchor(PaneGuardia, 0.0);
	            AnchorPane.setLeftAnchor(PaneGuardia, 0.0);
	            AnchorPane.setBottomAnchor(PaneGuardia, 0.0);
	            

	            //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
	            anchorPaneSala.getChildren().setAll(PaneGuardia);
	            
	           
	            
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		else {
			//Se carga el contenido de la ventana
	    	FXMLLoader loaderAdministrador = new FXMLLoader(getClass().getResource("/application/VentanaAdministrador.fxml"));
	    	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
	        ControladorAdministrador controlerAdministrador = new ControladorAdministrador(usuario);
	        loaderAdministrador.setController(controlerAdministrador);
	        AnchorPane PaneAdministrador;

			try {
				//Se carga en un AnchorPane la ventana
				PaneAdministrador = (AnchorPane) loaderAdministrador.load();
				
				//Se elimina el contenido de la ventana padre
				anchorPaneSala.getChildren().clear();
	        	
	        	//Se ajusta el AnchorPane para que sea escalable
	            AnchorPane.setTopAnchor(PaneAdministrador, 0.0);
	            AnchorPane.setRightAnchor(PaneAdministrador, 0.0);
	            AnchorPane.setLeftAnchor(PaneAdministrador, 0.0);
	            AnchorPane.setBottomAnchor(PaneAdministrador, 0.0);
	            

	            //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
	            anchorPaneSala.getChildren().setAll(PaneAdministrador);
	            
	           
	            
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}

    @FXML
    void verPerfil(MouseEvent event) {
    	if(logged == false) {
        	Alert error = new Alert(Alert.AlertType.ERROR);
			error.setHeaderText("Oh no! Para acceder a esta funci�n debes estar iniciado sesi�n.");
    		error.showAndWait();
        	
        }
        else {
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderPrincipala = new FXMLLoader(getClass().getResource("/application/VentanaPerfil.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
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
                

                //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
                anchorPaneSala.getChildren().setAll(PaneVentanaPrincipal);
                controlerPrincipal.getBarra().setStyle("-fx-background-color:  #FF8000");
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }

    }
    
}
