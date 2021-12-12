package Controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;

import Modelo.Guardia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;

/**
 * 
 * En la clase ControladorVPrincipal, manejamos todos los eventos que ocurren en la vista <b>VentanaPrincipal</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */


public class ControladorVPrincipal {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private BorderPane BordPanePrincipal;
    
    @FXML
    private ButtonBar btnBarArriba;

    @FXML
    private ImageView btnContacto;

    @FXML
    private ImageView btnCorreo;

    @FXML
    private ImageView btnMensaje;

    @FXML
    private ImageView imgViewLupa;

    @FXML
    private JFXTextField txtField_busqueda;

    @FXML
    private ImageView imgCalendar;

    @FXML
    private ImageView imgTickets;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private ImageView imgView_BtnFlecha1;

    @FXML
    private ImageView imgView_BtnFlecha;

    @FXML
    private Region regionUno;

    @FXML
    private Region regionDos;

    @FXML
    private Region regionTres;

    @FXML
    private GridPane gridPaneInfo;

    @FXML
    private Region regionImg;

    @FXML
    private Region regionMuseo;
    
    
	 
	 //ArrayList que guardara las imagenes que se mostraran en la ventana principal
	 ArrayList<Image> imagenes = new ArrayList<Image>();
 	
	@FXML
	public void initialize() {
		//Se añaden al ArrayList las imagenes que queremos que se muestren
		imagenes.add(new Image("/MonaLisa.jpg"));
	 	imagenes.add(new Image("/Dali.jpg"));
	 	imagenes.add(new Image("/Sixtina.jpg"));
	 	imagenes.add(new Image("/scream.jpg"));
	 	imagenes.add(new Image("/VanGogh.jpg"));
	 	imagenes.add(new Image("/people.jpg"));
	 	
	 	regionUno.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(0)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	regionDos.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(1)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	regionTres.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(2)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	
	 	
	 	Image horario = new Image("/Horarios.jpg");
	 	Image museo = new Image("/museoLouvre.jpg");
	 	
	 	regionImg.setBackground(new Background(new BackgroundFill(new ImagePattern(horario), CornerRadii.EMPTY, Insets.EMPTY)));
	 	regionMuseo.setBackground(new Background(new BackgroundFill(new ImagePattern(museo), CornerRadii.EMPTY, Insets.EMPTY)));
	 	
	 	
	}
	
	//Contadores que indicaran como un puntero las imagenes que se muestran en los imgviews del image slider
	int count = 0;
	int countDos = 1;
	int countTres = 2;
	boolean logged = false; //Este nos dira si la parsona esta logueada o no
	
	public void setLogged(boolean log) {
		
		logged = log;
	}
	public boolean getLogged() {
		
		return logged;
	}
	public ButtonBar getBtnBarArriba() {
		return btnBarArriba;
	}
	
	public ImageView getAvatarUsuario() {
		return imgUsuario;
	}
	
	
	
    
    
	

    @FXML
    /**
     * 
     * Mueve las imágenes a la derecha, tantas veces como el usuario pulse el botón.
     * 
     * @param event   Evento causado por el usuario al pulsar el botón de slide derecho.
     */
    void cambioImg(MouseEvent event) {
    	
    	
    	count++;
    	countDos++;
    	countTres++;
    	if(count>=imagenes.size()) {
    		count=0;
    	}
    	if(countDos>=imagenes.size()) {
    		countDos=0;
    	}
    	if(countTres>=imagenes.size()) {
    		countTres=0;
    	}
    	
    	
    	
    	regionUno.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(count)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	regionDos.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(countDos)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	regionTres.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(countTres)), CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	
    	
    }
    
    
    
    @FXML
    
    /**
     * 
     * Mueve las imágenes a la izquierda, tantas veces como el usuario pulse el botón.
     * 
     * @param event   Evento causado por el usuario al pulsar el botón de slide izquierdo.
     */
    void cambioImgAtras(MouseEvent event) {
    	
    	
    	
    	count--;
    	countDos--;
    	countTres--;
    	if(count<0) {
    		count=(imagenes.size())-1;
    	}
    	if(countDos<0) {
    		countDos=(imagenes.size())-1;
    	}
    	if(countTres<0) {
    		countTres=(imagenes.size())-1;
    	}
    	
    	
    	regionUno.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(count)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	regionDos.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(countDos)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	regionTres.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(countTres)), CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	
    }

    @FXML
    /**
     * 
     * Dirige al usuario a la ventana para inicar sesión, ya que se puede acceder al programa sin la necesidad de estar registrado.
     * 
     * @param event   Evento causado cuando el usuario pulsa sobre la imagen del avatar.
     */
    void accederPerfil(MouseEvent event) {

    	//Se carga el contenido de la ventana
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaEditGuardias.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorEditGuardias controlerAdmin = new ControladorEditGuardias();
        loaderApp.setController(controlerAdmin);
        AnchorPane PaneEditGuardias;
        
		if(logged == false) {
			Alert error = new Alert(Alert.AlertType.ERROR);
			error.setHeaderText("Oh no! Para acceder a esta función debes estar iniciado sesión.");
			error.showAndWait();
			abrirLogin();
		        	
		}
		else {
		try {
			//Se carga en un AnchorPane la ventana
			PaneEditGuardias = (AnchorPane) loaderApp.load();
			
			//Se elimina el contenido de la ventana padre
        	anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneEditGuardias, 0.0);
            AnchorPane.setRightAnchor(PaneEditGuardias, 0.0);
            AnchorPane.setLeftAnchor(PaneEditGuardias, 0.0);
            AnchorPane.setBottomAnchor(PaneEditGuardias, 0.0);
            

            
            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneEditGuardias);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
		
		//Obtenemos el las diferentes columnas de la tabla y asociamos cada columna al tipo de dato que queremos guardar
		controlerAdmin.getUsuario().setCellValueFactory(new PropertyValueFactory<>("usuario"));
		controlerAdmin.getNombre().setCellValueFactory(new PropertyValueFactory<>("nombre"));
		controlerAdmin.getPrimerApellido().setCellValueFactory(new PropertyValueFactory<>("apellido1"));
		controlerAdmin.getSegundoApellido().setCellValueFactory(new PropertyValueFactory<>("apellido2"));
		controlerAdmin.getEmail().setCellValueFactory(new PropertyValueFactory<>("email"));
		controlerAdmin.getDNI().setCellValueFactory(new PropertyValueFactory<>("dni"));
		controlerAdmin.getFechaNacimiento().setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
		controlerAdmin.getContrasenia().setCellValueFactory(new PropertyValueFactory<>("contrasenia"));
		
		
		//Se crea un guardia con cierta informacion y se añade a la tabla
		controlerAdmin.getTableView().getItems().add(new Guardia("2308", "534859348K", "jolie@gmail.com", "123456", LocalDate.of(2001, 9, 27), "Jolie", "Alain", "Vasquez"));
		}
    }
		 

    @FXML
    /**
     * 
     * Muestra la ventana de reserva de tickets.
     * 
     * @param event   Evento causado cuando el usuario pulsa la imagen de los tickets.
     */
    void reservarTickets(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderTickets = new FXMLLoader(getClass().getResource("/application/VentanaTickets.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorTickets controlerTickets = new ControladorTickets();
        loaderTickets.setController(controlerTickets);
        AnchorPane PaneTickets;
        System.out.println(logged);
        
        if(logged == false) {
        	Alert error = new Alert(Alert.AlertType.ERROR);
			error.setHeaderText("Oh no! Para acceder a esta función debes estar iniciado sesión.");
    		error.showAndWait();
        	abrirLogin();
        	
        }
        else {
        	 try {
     			//Se carga en un AnchorPane la ventana
     			PaneTickets = (AnchorPane) loaderTickets.load();
     			
     			//Se elimina el contenido de la ventana padre
             	anchorPanePrincipal.getChildren().clear();
             	
             	//Se ajusta el AnchorPane para que sea escalable
                 AnchorPane.setTopAnchor(PaneTickets, 0.0);
                 AnchorPane.setRightAnchor(PaneTickets, 0.0);
                 AnchorPane.setLeftAnchor(PaneTickets, 0.0);
                 AnchorPane.setBottomAnchor(PaneTickets, 0.0);
            
                 //Se añade el contenido de la ventana cargada en el AnchorPane del padre
                 anchorPanePrincipal.getChildren().setAll(PaneTickets);
                 
                
                 
     		} catch (IOException e) {
     			e.printStackTrace();
     		}
        }
       

    }

    @FXML
    void mandarCorreo(MouseEvent event) {

    }

    @FXML
    void mandarMensaje(MouseEvent event) {

    }

    @FXML
    void mostrarContacto(MouseEvent event) {

    }

    @FXML
    void tocarLupa(MouseEvent event) {

    }

    @FXML
    void verEventos(MouseEvent event) {

    }
    
    void abrirLogin() {
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/InterfazLogin.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorInicioSesion controlerInicio = new ControladorInicioSesion();
        loaderApp.setController(controlerInicio);
        AnchorPane PaneInicioSesion;

		try {
			//Se carga en un AnchorPane la ventana
			PaneInicioSesion = (AnchorPane) loaderApp.load();
			
			//Se elimina el contenido de la ventana padre
        	anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneInicioSesion, 0.0);
            AnchorPane.setRightAnchor(PaneInicioSesion, 0.0);
            AnchorPane.setLeftAnchor(PaneInicioSesion, 0.0);
            AnchorPane.setBottomAnchor(PaneInicioSesion, 0.0);
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneInicioSesion);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	
    }

}
