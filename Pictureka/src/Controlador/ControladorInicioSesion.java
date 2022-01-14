package Controlador;

import java.io.IOException;
import java.util.Vector;
import com.jfoenix.controls.JFXButton;
import Modelo.Cliente;
import Modelo.Datos;
import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 * En esta clase se maneja el inicio de sesión de cada usuario en la vista <b>InterfazLogin</b>, mostrando la respectiva ventana a cada
 * usuarip.
 * 
 * @author Jolie Alain V�squez
 * @author Oscar Gonz�lez Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmer�n L�pez
 *
 */

public class ControladorInicioSesion {

    @FXML
    private AnchorPane MyAnchorPane;

    @FXML
    private GridPane gridPaneLogin;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblUsuario;

    @FXML
    private TextField textUsuario;

    @FXML
    private Label lblContrasenia;

    @FXML
    private PasswordField textContrasenia;

    @FXML
    private JFXButton btnRegistrarse;

    @FXML
    private JFXButton btnInicioSesion;

    @FXML
    private ImageView btnVolver;
    
    @FXML
    /**
     * 
     * Vuelve a la ventana principal, cancelando el inicio de sesi�n del usuario.
     * 
     * @param event		Evento causado cuando el usaurio pulsa sobre la imagen de vovler atr�s.
     */
    void volverAtras(MouseEvent event) {
    	
    	//Cargamos la ventana principal 
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal("vacio");
        loaderPrincipal.setController(controlerPrincipal);
        
        try {
        	AnchorPane principal = (AnchorPane) loaderPrincipal.load();
        	MyAnchorPane.getChildren().clear();
            AnchorPane.setTopAnchor(principal, 0.0);
            AnchorPane.setRightAnchor(principal, 0.0);
            AnchorPane.setLeftAnchor(principal, 0.0);
            AnchorPane.setBottomAnchor(principal, 0.0);
            MyAnchorPane.getChildren().setAll(principal);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    /**
     * 
     * Recoge el nombre de usuario o email y la contrase�a para comprobar que tal usuario existe, y de esa manera mostrar la vista correspondiente
     * 
     * @param event   Evento causado cuando el usuario pulsa sobre el bot�n de <b>Inicar sesi�n</b> para poder acceder a la aplicaci�n
     */
    void InicarSesion(ActionEvent event) {
    	
    	modelo_Museo museo = new modelo_Museo();
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
    	
    	//Comprueba que lo devuelto por el m�todo loginUsuario se corresponde con los diferentes identificadores que tienen cada usuario
    	if (museo.loginUsuario(textUsuario.getText(), textContrasenia.getText())==1) {
    		confirmacion.setHeaderText("Login correcto");
    		//Espera a que el usuario interactue con el mensaje para abrir la ventana Principal
    		confirmacion.showAndWait();
    			
    			
    			//Llamamos al codigo hecho en fxml
    			FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    			ControladorVPrincipal controlVPrincipal = new ControladorVPrincipal(textUsuario.getText());
    			//Asociamos la vista con el controlador
    			loaderApp.setController(controlVPrincipal);
    			//Llamar a la funcion load de loader
    			Parent root;
    			try {
    				root = loaderApp.load();
    		        Stage stage = new Stage();
    		        stage.setScene(new Scene(root));
    		        stage.setMaximized(true);
    		        stage.setMinHeight(900);
    		        stage.setMinWidth(950);
    		        stage.show();
    		        stage.getIcons().add(new Image("Pictureka2.png"));
    		        
    		        //Obtenemos la ventanaLogo
    		        Stage primaryStage = (Stage)btnInicioSesion.getScene().getWindow();
    		        //Escondemos la ventana
    		        primaryStage.hide();
    		        //Cargamos los datos en un vector de clientes
    		        Datos datos = new Datos();
    		        String nombreUsuario = "";
    		        Vector<Cliente> clientes = datos.desserializarJsonAusuarios();
    		        //Recorremos el vector para encontrar al usuario que esta iniciando sesion
    		        for (int i=0; i<clientes.size(); i++) {
    		        	//Si el usuario introducido por el cliente es igual a un email o un nombre de usuario, se ha encontrado al cliente
    		        	if (clientes.get(i).getEmail().equals(textUsuario.getText()) | clientes.get(i).getUsuario().equals(textUsuario.getText())) {
    		        		//Se guarda su nombre para mostrarlo en el Label de bienvenida
    		        		nombreUsuario = clientes.get(i).getUsuario();
    		        	}
    		        }
    		        
    		        controlVPrincipal.setLogged(true);
    		        controlVPrincipal.getGridPaneButton().setStyle("-fx-background-color: #00aae4");
    		        controlVPrincipal.getAvatarUsuario().setImage(new Image("/avatarCliente.png"));
    		        controlVPrincipal.getLblBienvenido().setText("¡Bienvenid@ "+(nombreUsuario)+"!");
    		        controlVPrincipal.getLblBienvenido().setStyle("-fx-background-color: #00aae4");
    		        
    		        
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
  
    
    	}
    	else if (museo.loginUsuario(textUsuario.getText(), textContrasenia.getText())==2) {
    		
    		confirmacion.setHeaderText("Login correcto");
    		//Espera a que el usuario interactue con el mensaje para abrir la ventana Principal
    		confirmacion.showAndWait();
    		//Se carga el contenido de la ventana
        	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaGuardia.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
            ControladorGuardia controlerGuardia= new ControladorGuardia(textUsuario.getText());
            loaderApp.setController(controlerGuardia);
            Parent root;
			try {
				root = loaderApp.load();
		        Stage stage = new Stage();
		        stage.setScene(new Scene(root));
		        stage.setMaximized(true);
		        stage.setMinHeight(900);
		        stage.setMinWidth(950);
		        stage.show();
		        stage.getIcons().add(new Image("Pictureka2.png"));
		        
		        //Obtenemos la ventanaLogo
		        Stage primaryStage = (Stage)btnInicioSesion.getScene().getWindow();
		        //Escondemos la ventana
		        primaryStage.hide();
		        
		        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    	else if (museo.loginUsuario(textUsuario.getText(), textContrasenia.getText())==3) {
    		
    		confirmacion.setHeaderText("Login correcto");
    		//Espera a que el usuario interactue con el mensaje para abrir la ventana Principal
    		confirmacion.showAndWait();
    		
    		
    		
    		//Se carga el contenido de la ventana
        	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaAdministrador.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
            ControladorAdministrador controlerAdmin = new ControladorAdministrador(textUsuario.getText());
            loaderApp.setController(controlerAdmin);
            Parent root;
			try {
				root = loaderApp.load();
		        Stage stage = new Stage();
		        stage.setScene(new Scene(root));
		        stage.setMaximized(true);
		        stage.setMinHeight(900);
		        stage.setMinWidth(950);
		        stage.show();
		        stage.getIcons().add(new Image("Pictureka2.png"));
		        
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
	/**
	 * 
	 * Muestra la ventana de Registro donde el usuario debe rellenar los campos necesarios para crearse un usuario en la aplicaci�n.
	 * 
	 * @param event		Evento causado cuando el usuario pulsa sobre el bot�n de Registrarse.
	 */
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
    	
    }

}







