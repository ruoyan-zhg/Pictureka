package Controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXToolbar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControladorAdministrador {

	 @FXML
	 private AnchorPane anchorPanePrincipal;

	 @FXML
	 private VBox VBoxPrincipal;

	 @FXML
	private JFXToolbar toolBarAdministrador;

	 @FXML
	 private ImageView imgCerrarSesion;

	 @FXML
	 private ImageView imgAvatarAdmin;

	 @FXML
	 private ImageView imgInformes;

	 @FXML
	 private LineChart<?, ?> tablaEntradas;

	 @FXML
	 private ImageView imgEditarGuardias;

	 @FXML
	 private ImageView imgEditarEventos;

	 @FXML
	 private ImageView imgTemperatura;

	 @FXML
	 private ImageView imgDistancia;

	 @FXML
	 private ImageView imgLuz;

	 @FXML
	 private Text textSensores;
	 
	 private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
		
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
    
	 
	 public ControladorAdministrador(String usuario) {
		 if (usuario == "vacio") {
			 logged = false;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
		 }
		 
	}

    @FXML
    void SensorDistancia(MouseEvent event) {

    }

    @FXML
    void SensorLuz(MouseEvent event) {

    }

    @FXML
    void SensorTemperatura(MouseEvent event) {

    }

    @FXML
    void accederInformes(MouseEvent event) {

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
    void cerrarSesion(MouseEvent event) {
    	
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal(usuario);
        loaderPrincipal.setController(controlerPrincipal);
        
        try {
        	AnchorPane registerPane = (AnchorPane) loaderPrincipal.load();
        	anchorPanePrincipal.getChildren().clear();
            AnchorPane.setTopAnchor(registerPane, 0.0);
            AnchorPane.setRightAnchor(registerPane, 0.0);
            AnchorPane.setLeftAnchor(registerPane, 0.0);
            AnchorPane.setBottomAnchor(registerPane, 0.0);
            anchorPanePrincipal.getChildren().setAll(registerPane);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @FXML
    void editarEventos(MouseEvent event) {
    	FXMLLoader loaderEdit = new FXMLLoader(getClass().getResource("/application/VentanaEditarEventos.fxml"));
        controladorEditarEventos controlerEditEvents = new controladorEditarEventos(usuario);
        loaderEdit.setController(controlerEditEvents);
        
        try {
        	AnchorPane PaneEdit = (AnchorPane) loaderEdit.load();
        	anchorPanePrincipal.getChildren().clear();
            AnchorPane.setTopAnchor(PaneEdit, 0.0);
            AnchorPane.setRightAnchor(PaneEdit, 0.0);
            AnchorPane.setLeftAnchor(PaneEdit, 0.0);
            AnchorPane.setBottomAnchor(PaneEdit, 0.0);
            anchorPanePrincipal.getChildren().setAll(PaneEdit);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }

    @FXML
    void editarGuardias(MouseEvent event) {
    	
    	FXMLLoader loaderEdit = new FXMLLoader(getClass().getResource("/application/VentanaEditGuardias.fxml"));
        ControladorEditGuardias controlerEdit = new ControladorEditGuardias();
        loaderEdit.setController(controlerEdit);
        
        try {
        	AnchorPane PaneEdit = (AnchorPane) loaderEdit.load();
        	anchorPanePrincipal.getChildren().clear();
            AnchorPane.setTopAnchor(PaneEdit, 0.0);
            AnchorPane.setRightAnchor(PaneEdit, 0.0);
            AnchorPane.setLeftAnchor(PaneEdit, 0.0);
            AnchorPane.setBottomAnchor(PaneEdit, 0.0);
            anchorPanePrincipal.getChildren().setAll(PaneEdit);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

	public JFXToolbar getToolBarAdministrador() {
		return toolBarAdministrador;
	}

	public void setToolBarAdministrador(JFXToolbar toolBarAdministrador) {
		this.toolBarAdministrador = toolBarAdministrador;
	}

}

