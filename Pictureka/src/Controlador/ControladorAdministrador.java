package Controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXToolbar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
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

    }

    @FXML
    void cerrarSesion(MouseEvent event) {
    	
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal();
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
        controladorEditarEventos controlerEditEvents = new controladorEditarEventos();
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

