package Controlador;
import java.io.IOException;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControladorGuardia {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private JFXTextField numTicket;

    @FXML
    private JFXTextField tituloInforme;

    @FXML
    private JFXTextField cuerpoInforme;

    @FXML
    private Text SalaText;

    @FXML
    private ImageView imgTicket;

    @FXML
    private ImageView imgCerrarSesion1111;

    @FXML
    private ButtonBar imgValidar;

    @FXML
    private ImageView imgEnviarInforme;

    @FXML
    private ImageView imgFormulario;

    @FXML
    private ImageView imgSala1;

    @FXML
    private ImageView imgSala2;

    @FXML
    private ImageView imgSala4;

    @FXML
    private ImageView imgSala3;
    
    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
	
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
    
	 
	 public ControladorGuardia(String usuario) {
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
    			anchorPanePrincipal.getChildren().clear();
            	
            	//Se ajusta el AnchorPane para que sea escalable
                AnchorPane.setTopAnchor(PaneVentanaPrincipal, 0.0);
                AnchorPane.setRightAnchor(PaneVentanaPrincipal, 0.0);
                AnchorPane.setLeftAnchor(PaneVentanaPrincipal, 0.0);
                AnchorPane.setBottomAnchor(PaneVentanaPrincipal, 0.0);
                

                //Se añade el contenido de la ventana cargada en el AnchorPane del padre
                anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
                
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}

        }
    	
    }
    
    @FXML
    void enviar(MouseEvent event) {

    }

    @FXML
    void cerrarSesion(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
    	this.usuario = "vacio";
    	this.logged = false;
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
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	
    }

    @FXML
    void sala1(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorSalas controlerSala1 = new ControladorSalas(usuario);
        loaderSala1.setController(controlerSala1);
        AnchorPane PaneSala1;

		try {
			//Se carga en un AnchorPane la ventana
			PaneSala1 = (AnchorPane) loaderSala1.load();
			
			//Se elimina el contenido de la ventana padre
			anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneSala1, 0.0);
            AnchorPane.setRightAnchor(PaneSala1, 0.0);
            AnchorPane.setLeftAnchor(PaneSala1, 0.0);
            AnchorPane.setBottomAnchor(PaneSala1, 0.0);
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneSala1);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	
    }

    @FXML
    void sala2(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderSala2 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorSalas controlerSala2 = new ControladorSalas(usuario);
        loaderSala2.setController(controlerSala2);
        AnchorPane PaneSala2;

		try {
			//Se carga en un AnchorPane la ventana
			PaneSala2 = (AnchorPane) loaderSala2.load();
			
			//Se elimina el contenido de la ventana padre
			anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneSala2, 0.0);
            AnchorPane.setRightAnchor(PaneSala2, 0.0);
            AnchorPane.setLeftAnchor(PaneSala2, 0.0);
            AnchorPane.setBottomAnchor(PaneSala2, 0.0);
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneSala2);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	
    }

    @FXML
    void sala3(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderSala3 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorSalas controlerSala3 = new ControladorSalas(usuario);
        loaderSala3.setController(controlerSala3);
        AnchorPane PaneSala3;

		try {
			//Se carga en un AnchorPane la ventana
			PaneSala3 = (AnchorPane) loaderSala3.load();
			
			//Se elimina el contenido de la ventana padre
			anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneSala3, 0.0);
            AnchorPane.setRightAnchor(PaneSala3, 0.0);
            AnchorPane.setLeftAnchor(PaneSala3, 0.0);
            AnchorPane.setBottomAnchor(PaneSala3, 0.0);
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneSala3);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    }

    @FXML
    void sala4(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderSala4 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorSalas controlerSala4 = new ControladorSalas(usuario);
        loaderSala4.setController(controlerSala4);
        AnchorPane PaneSala4;

		try {
			//Se carga en un AnchorPane la ventana
			PaneSala4 = (AnchorPane) loaderSala4.load();
			
			//Se elimina el contenido de la ventana padre
			anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneSala4, 0.0);
            AnchorPane.setRightAnchor(PaneSala4, 0.0);
            AnchorPane.setLeftAnchor(PaneSala4, 0.0);
            AnchorPane.setBottomAnchor(PaneSala4, 0.0);
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneSala4);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    }

    @FXML
    void validarTicket(MouseEvent event) {

    }

}
