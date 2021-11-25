package Controlador;


import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControladorInicioSesion {
	@FXML
	private AnchorPane MyAnchorPane;
	
    @FXML
    private GridPane gridPaneLogin;

    @FXML
    private Pane paneLogin;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblContrasenia;

    @FXML
    private TextField textUsuario;

    @FXML
    private TextField textConstrasenia;

    @FXML
    private JFXButton btnInicioSesion;

    @FXML
    private JFXButton btnRegistrarse;

    @FXML
    void InicarSesion(ActionEvent event) {
    	modelo_Museo museo = new modelo_Museo();
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
    	if (museo.loginUsuario(textUsuario.getText(), textConstrasenia.getText())) {
    		confirmacion.setHeaderText("Login correcto");
    		//Espera a que el usuario interactue con el mensaje para abrir la ventana Principal
    		confirmacion.showAndWait();

    			//Llamamos al codigo hecho en fxml
    			FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    			ControladorVPrincipal controlVPrincipal = new ControladorVPrincipal();
    			//Asociamos la vista con el controlador
    			loaderApp.setController(controlVPrincipal);
    			//Llamar a la funcion load de loadere
    			Parent root;
    			try {
    				root = loaderApp.load();
    		        Stage stage = new Stage();
    		        stage.setScene(new Scene(root));
    		        stage.setMinHeight(500);
    		        stage.setMinWidth(700);
    		        stage.show();
    		        
    		        //Obtenemos la ventanaLogo
    		        Stage primaryStage = (Stage)btnInicioSesion.getScene().getWindow();
    		        //Escondemos la ventana
    		        primaryStage.hide();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
  
    
    	}
    	else {
    		error.setHeaderText("Error: El usuario/email o contraseña introducida son incorrectas");
    		error.show();
    	}

    }



	@FXML
    void Registrarse(ActionEvent event) {
    	
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaRegistro.fxml"));
        ControladorRegistro controlerRegistro = new ControladorRegistro();
        loaderApp.setController(controlerRegistro);
        
        try {
        	AnchorPane registerPane = (AnchorPane) loaderApp.load();
        	MyAnchorPane.getChildren().clear();
            AnchorPane.setTopAnchor(registerPane, 0.0);
            AnchorPane.setRightAnchor(registerPane, 0.0);
            AnchorPane.setLeftAnchor(registerPane, 0.0);
            AnchorPane.setBottomAnchor(registerPane, 0.0);
            MyAnchorPane.getChildren().setAll(registerPane);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /*
        Parent root;
		try {
			root = loaderApp.load();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root));
	        stage.show();
	        
	        //Obtenemos la ventanaLogo
	        Stage primaryStage = (Stage)btnRegistrarse.getScene().getWindow();
	        //Escondemos la ventana
	        primaryStage.hide();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    	
    }

}







