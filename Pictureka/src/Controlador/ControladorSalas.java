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
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * 
 * En estaa clase, se maneja la información recogida de todos los sensores y se muestran en la vista <b>VentanaSala</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 */

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
	 
    
    /**
     * 
     * Constructor de la clase <b>ControladorSalas</b> que guarda la información del usuario.
     * 
     * @param usuario		El usuario que se encuentre iniciado sesión. 
     * @param _sala			La sala en la que se encuentre el usuario.
     * @param _tipoStaff	Tipo de Staff que se encuentra iniciado sesión.
     */
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
	/**
	 * 
	 * Inicializa la ventana sala, con su respectiva número de sala y con la información del usuario que se encuentra iniciado sesión.
	 * 
	 */
	public void initialize() {
		Museo museo = new Museo();
		
		if (sala.getIdentificador() == 1) {
			
			textLuz.setText("LUZ BRILLANTE 75%:  Analog reading = 651\n"
					       +"LUZ BRILLANTE 70%:  Analog reading = 625\n"
					       +"LUZ BRILLANTE 66%:  Analog reading = 600\n"
					       +"LUZ BRILLANTE 75%:  Analog reading = 651");
			
			
			textTemperatura.setText("TEMPERATURA ESTABLE: 24 °C\n"
								  +"TEMPERATURA ESTABLE: 24.2 °C\n"
								  +"TEMPERATURA ESTABLE: 24.4 °C\n"
								  +"TEMPERATURA ESTABLE: 24.6 °C");
			
		}
		else if (sala.getIdentificador()==2) {
			
		textLuz.setText("LUZ TENUE 48%:  Analog reading = 397\n"
				       +"LUZ TENUE 50%:  Analog reading = 400\n"
				       +"LUZ TENUE 44%:  Analog reading = 390\n"
				       +"LUZ TENUE 45%:  Analog reading = 393");
		
		
		textTemperatura.setText("TEMPERATURA ESTABLE: 26 °C\n"
							  +"TEMPERATURA ESTABLE: 26.9 °C\n"
							  +"TEMPERATURA ESTABLE: 26.8 °C\n"
							  +"TEMPERATURA ESTABLE: 26.6 °C");
		}
		else if (sala.getIdentificador()==3) {
			
		textLuz.setText("LUZ BRILLANTE 80%:  Analog reading = 670\n"
				       +"LUZ BRILLANTE 84%:  Analog reading = 674\n"
				       +"LUZ BRILLANTE 86%:  Analog reading = 676\n"
				       +"LUZ BRILLANTE 85%:  Analog reading = 675");
		
		
		textTemperatura.setText("TEMPERATURA ESTABLE: 29.9 °C\n"
							  +"¡¡TEMPERATURA ALTA!!: 30.1 °C\n"
							  +"¡¡TEMPERATURA ALTA!!: 30.5 °C\n"
							  +"¡¡TEMPERATURA ALTA!!: 31.3 °C");
			
		}
		else if (sala.getIdentificador()==4) {
	
		textLuz.setText("LUZ BRILLANTE 91%:  Analog reading = 690\n"
				       +"LUZ BRILLANTE 94%:  Analog reading = 694\n"
				       +"LUZ BRILLANTE 93%:  Analog reading = 693\n"
				       +"LUZ BRILLANTE 92%:  Analog reading = 692");
		
		
		textTemperatura.setText("TEMPERATURA ESTABLE: 25.9 °C\n"
							  +"TEMPERATURA ESTABLE: 26.1 °C\n"
							  +"TEMPERATURA ESTABLE: 26.3 °C\n"
							  +"TEMPERATURA ESTABLE: 26.2 °C");
			
			
		}
		
		
		//textLuz.setText("Actualmente esta cargada la sala "+sala.getIdentificador());
		//Dependiendo del usuario que se encuentre iniciado sesion se muestra una u otro avatar
		if (tipoStaff.equals("Guardia")) {
			imgAvatar.setImage(new Image("/guardiaAvatar.png"));
		}
		else {
			imgAvatar.setImage(new Image("/administradorAvatar.png"));
		}
	}

	 	
	@FXML
	/**
	 * 
	 * Devuelve al usuario a su ventana inicial, dependiendo del usuario que está iniciado sesión.
	 * 
	 * @param event		Evento causado cuando el guardia pulsa sobre la imagen de vuelta atrás.
	 */
	void volverAtrasSalas(MouseEvent event) {
		if(tipoStaff.equals("Guardia")) {
			//Se carga el contenido de la ventana
	    	FXMLLoader loaderGuardia = new FXMLLoader(getClass().getResource("/application/VentanaGuardia.fxml"));
	    	//Se le asigna el controlador de la ventana para editar información de los guardias
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
	            

	            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
	            anchorPaneSala.getChildren().setAll(PaneGuardia);
	            
	           
	            
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		else {
			//Se carga el contenido de la ventana
	    	FXMLLoader loaderAdministrador = new FXMLLoader(getClass().getResource("/application/VentanaAdministrador.fxml"));
	    	//Se le asigna el controlador de la ventana para editar información de los guardias
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
	            

	            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
	            anchorPaneSala.getChildren().setAll(PaneAdministrador);
	            
	           
	            
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}

    @FXML
    /**
     * 
     * Muestra la información del usuario que se encuentra iniciado sesión.
     * 
     * @param event		Evento causado cuando el usuario pulsa sobre la imagen de su avatar.
     */
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
                controlerPrincipal.getBarra().setStyle("-fx-background-color:  #FF8000");
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }

    }
    
}
