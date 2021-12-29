package Controlador;

import java.io.IOException;

import Modelo.modelo_Museo;
import Modelo.Cliente;
import Modelo.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class ControladorPerfil {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private BorderPane BordPanePrincipal;

    @FXML
    private ImageView imgUsuario1;

    @FXML
    private Label LabelUsuario;

    @FXML
    private Label RFechaDNa;

    @FXML
    private Label LabelFechaDNa;

    @FXML
    private Label REmail;

    @FXML
    private Label RDNI;

    @FXML
    private Label RUsuario;

    @FXML
    private Label LabelEmail;

    @FXML
    private Label LabelDNI;

    @FXML
    private Label LabelNombre;

    @FXML
    private Label LabelReserva;

    @FXML
    private Label LabelApellido2;

    @FXML
    private Label RReserva;

    @FXML
    private Label RApellido2;

    @FXML
    private Label RApellido1;

    @FXML
    private Label LabelApellido1;

    @FXML
    private Label RNombre;

    @FXML
    private ImageView imgRegresar;
    
    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
	
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
    private int identificador;
    
    private Cliente cliente;
    
    private Staff staff;
    
	 public ControladorPerfil(String usuario)  {
		 if (usuario == "vacio") {
			 logged = false;
			 Alert error = new Alert(Alert.AlertType.ERROR);
			 error.setHeaderText("Error: PROGRAMADOR UN USUARIO NO DEBERIA VER SUS CREDENCIALES SIN HABER INICIADO SESION");
			 error.show();
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
			 modelo_Museo museo = new modelo_Museo();
			 //museo obtener identificador de usuario
			 int _identificador = museo.devolverIdentificador(usuario);
			 identificador = _identificador;
			 //guardar el usuario dependiendo del identificador
			 if (identificador == 1) {
				Cliente provisionalCli = museo.devolverCliente(usuario);
			 	this.cliente = provisionalCli;
			 }
			 else if(identificador == -1) {
				 Alert error = new Alert(Alert.AlertType.ERROR);
				 error.setHeaderText("Error: Archivo no encontrado");
				 error.show();
			 }
			 else {
				Staff provisionalStaff = museo.devolverStaff(usuario);
		 		this.staff = provisionalStaff;
			 }
		 }
	}

	 @FXML
	  	public void initialize() {
		 	switch(identificador) {
		 		case 1:
		 			RUsuario.setText(cliente.getUsuario());
		 			RDNI.setText(cliente.getDni());
		 			REmail.setText(cliente.getEmail());
		 			RFechaDNa.setText((cliente.getFechaNacimiento()).toString());
		 			LabelApellido1.setVisible(false);
		 			LabelApellido2.setVisible(false);
		 			LabelNombre.setVisible(false);
		 			RApellido1.setVisible(false);
		 			RApellido2.setVisible(false);
		 			RNombre.setVisible(false);
		 			LabelReserva.setVisible(false);
		 			RReserva.setVisible(false);
		 			
		 			break;
		 		case 2:
		 			staffConfiguracion(); 
		 			break;
		 		case 3:
		 			staffConfiguracion();
		 			break;
		 	}
	  	 	
	  	}
	 
    @FXML
    void volver(MouseEvent event) {

    	modelo_Museo museo = new modelo_Museo();


    	//Comprueba que lo devuelto por el método loginUsuario se corresponde con los diferentes identificadores que tienen cada usuario
    	if (identificador==1) {
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
                controlerPrincipal.getLblBienvenido().setVisible(false);
                controlerPrincipal.getBtnBarArriba().setStyle("-fx-background-color: #00aae4");
    	        controlerPrincipal.getAvatarUsuario().setImage(new Image("/avatarCliente.png"));
                

                //Se añade el contenido de la ventana cargada en el AnchorPane del padre
    	        anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
                
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
  
    
    	}
    	else if (identificador==2) {
    		//Se carga el contenido de la ventana
        	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaGuardia.fxml"));
        	//Se le asigna el controlador de la ventana para editar información de los guardias
            ControladorGuardia controlerPrincipal = new ControladorGuardia(usuario);
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
    	else if (identificador==3) {
    		//Se carga el contenido de la ventana
        	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaAdministrador.fxml"));
        	//Se le asigna el controlador de la ventana para editar información de los guardias
            ControladorAdministrador controlerPrincipal = new ControladorAdministrador(usuario);
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

    }
    private void staffConfiguracion() {
    	
    	RUsuario.setText(staff.getUsuario());
		RDNI.setText(staff.getDni());
		REmail.setText(staff.getEmail());
		RFechaDNa.setText((staff.getFechaNacimiento()).toString());
		RApellido1.setText(staff.getApellido1());
		RApellido2.setText(staff.getApellido2());
		RNombre.setText(staff.getNombre());	
		imgUsuario1.setVisible(false);
		LabelReserva.setVisible(false);
		RReserva.setVisible(false);
    }
}

