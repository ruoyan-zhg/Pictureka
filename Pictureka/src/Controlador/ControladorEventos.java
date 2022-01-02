package Controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
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
import Modelo.*;

public class ControladorEventos {

	@FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private BorderPane BordPanePrincipal;

    @FXML
    private ImageView btnContacto;

    @FXML
    private ImageView btnCorreo;

    @FXML
    private ImageView btnMensaje;

    @FXML
    private ImageView imgView_BtnFlecha1;

    @FXML
    private ImageView imgView_BtnFlecha;

    @FXML
    private Region region1;

    @FXML
    private Region region2;

    @FXML
    private Region region3;

    @FXML
    private GridPane GridPaneObras2;

    @FXML
    private Region regionEventoDerecha;

    @FXML
    private Region regionEventoIzq;

    @FXML
    private JFXTextArea txtAreaUno;

    @FXML
    private Label lblEventoUno;

    @FXML
    private JFXTextArea txtAreaDos;

    @FXML
    private Label lblEventoDos;

    @FXML
    private Region regionEventAbajoIzq;

    @FXML
    private Region regionEventoAbajoDer;

    @FXML
    private Label lblEventoTres;

    @FXML
    private JFXTextArea txtAreaTres;

    @FXML
    private Label lblEventoCuatro;

    @FXML
    private JFXTextArea txtAreaCuatro;

    @FXML
    private GridPane gridPaneEventos;

    @FXML
    private ButtonBar btnBarArriba;

    @FXML
    private ImageView imgCalendar;

    @FXML
    private ImageView imgTickets;

    @FXML
    private ImageView imgUsuario;
    
    @FXML
    private ImageView imgVolverAtrasEventos;
    
    
    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
		
   boolean logged; //Este nos dira si la parsona esta logueada o no
   
   
	 
	 public ControladorEventos(String usuario) {
		 if (usuario == "vacio") {
			 logged = false;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
		 }
		 
	}


    
    //ArrayList que guardara las imagenes que se mostraran en la ventana principal
  	ArrayList<Image> imagenes = new ArrayList<Image>();
  	Datos handler = new Datos(); //Instanciamos un objeto para meter el Json de eventos en un vector
  	Vector<Evento> eventos = new Vector<Evento>(); //creamos el vector de eventos
  	
  	//Contadores que indicaran como un puntero las imagenes que se muestran en los imgviews del image slider
   	int count = 0;
   	int countDos = 1;
   	int countTres = 2;
   	
  	@FXML
  	public void initialize() {
  		//Se añaden al ArrayList las imagenes que queremos que se muestren
  		imagenes.add(new Image("/MonaLisa.jpg"));
  	 	imagenes.add(new Image("/Dali.jpg"));
  	 	imagenes.add(new Image("/Sixtina.jpg"));
  	 	imagenes.add(new Image("/scream.jpg"));
  	 	imagenes.add(new Image("/VanGogh.jpg"));
  	 	imagenes.add(new Image("/people.jpg"));
  	 	
  	 	eventos = handler.desserializarJsonAEventos();
  	 	
  	 	
  	 	region1.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(0)), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	region2.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(1)), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	region3.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(2)), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	
  	 	
  	 	//Cosas de la Informacion
  	 	Image arribaIzq = new Image(eventos.elementAt(0).getImagen()); //A las imagenes correspondientes le asignamos la direccion en la que se encuentran
  	 	Image arribaDer = new Image(eventos.elementAt(1).getImagen());
  	 	Image abajoIzq = new Image(eventos.elementAt(2).getImagen()); //A las imagenes correspondientes le asignamos la direccion en la que se encuentran
  	 	Image abajoDer = new Image(eventos.elementAt(3).getImagen());
  	 	
  	 	
  	 	//asignamos imagenes a la region
  	 	
  	 	regionEventoIzq.setBackground(new Background(new BackgroundFill(new ImagePattern(arribaIzq), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	regionEventoDerecha.setBackground(new Background(new BackgroundFill(new ImagePattern(arribaDer), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	regionEventAbajoIzq.setBackground(new Background(new BackgroundFill(new ImagePattern(abajoIzq), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	regionEventoAbajoDer.setBackground(new Background(new BackgroundFill(new ImagePattern(abajoDer), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	
  	 	//asignamos los titulos
  	 	
  	 	lblEventoUno.setText(eventos.elementAt(0).getNombre());
  	 	lblEventoDos.setText(eventos.elementAt(1).getNombre());
  	 	lblEventoTres.setText(eventos.elementAt(2).getNombre());
  	 	lblEventoCuatro.setText(eventos.elementAt(3).getNombre());
  	 	
  	 	//asignamos las descripciones
  	 	
  	 	txtAreaUno.setText(eventos.elementAt(0).getInformacion());
  	 	txtAreaDos.setText(eventos.elementAt(1).getInformacion());
  	 	txtAreaTres.setText(eventos.elementAt(2).getInformacion());
  	 	txtAreaCuatro.setText(eventos.elementAt(3).getInformacion());
  	 	
  	 	
  	}
  	
  	
  	
  	public GridPane getGridPaneEventos() {
		return gridPaneEventos;
	}
	
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
    	
    	region1.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(count)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	region2.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(countDos)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	region3.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(countTres)), CornerRadii.EMPTY, Insets.EMPTY)));
    	
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
    	
    	region1.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(count)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	region2.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(countDos)), CornerRadii.EMPTY, Insets.EMPTY)));
	 	region3.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(countTres)), CornerRadii.EMPTY, Insets.EMPTY)));
    	
    }

    @FXML
    void mandarCorreo1(MouseEvent event) {

    }

    @FXML
    void mandarMensaje1(MouseEvent event) {

    }

    @FXML
    void mostrarContacto1(MouseEvent event) {

    }

    @FXML
    void reservarTickets(MouseEvent event) {
    	
    	if(logged == false) {
        	Alert error = new Alert(Alert.AlertType.ERROR);
			error.setHeaderText("Oh no! Para acceder a esta función debes estar iniciado sesión.");
    		error.showAndWait();
        	
        }
        else {
        	
        }
    	
    }

    @FXML
    void verEventos(MouseEvent event) {

    }
    
    @FXML
    void volverAtrasEventos(MouseEvent event) {
    	
    	if (logged==false) {
    		String usuario = "vacio";
    		this.logged = false;
    		
    		// Se carga el contenido de la ventana
    		FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    		// Se le asigna el controlador de la ventana para editar información de los
    		// guardias

    		ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal(usuario);
    		loaderPrincipal.setController(controlerPrincipal);
    		AnchorPane PaneVentanaPrincipal;

    		try {
    			// Se carga en un AnchorPane la ventana
    			PaneVentanaPrincipal = (AnchorPane) loaderPrincipal.load();

    			// Se elimina el contenido de la ventana padre
    			anchorPanePrincipal.getChildren().clear();

    			// Se ajusta el AnchorPane para que sea escalable
    			AnchorPane.setTopAnchor(PaneVentanaPrincipal, 0.0);
    			AnchorPane.setRightAnchor(PaneVentanaPrincipal, 0.0);
    			AnchorPane.setLeftAnchor(PaneVentanaPrincipal, 0.0);
    			AnchorPane.setBottomAnchor(PaneVentanaPrincipal, 0.0);

    			// Se añade el contenido de la ventana cargada en el AnchorPane del padre
    			anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);

    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    		
    		
    		
    	}
    	else {
    		
    		// Se carga el contenido de la ventana
    		FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    		// Se le asigna el controlador de la ventana para editar información de los
    		// guardias

    		ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal(usuario);
    		loaderPrincipal.setController(controlerPrincipal);
    		AnchorPane PaneVentanaPrincipal;

    		try {
    			// Se carga en un AnchorPane la ventana
    			PaneVentanaPrincipal = (AnchorPane) loaderPrincipal.load();

    			// Se elimina el contenido de la ventana padre
    			anchorPanePrincipal.getChildren().clear();

    			// Se ajusta el AnchorPane para que sea escalable
    			AnchorPane.setTopAnchor(PaneVentanaPrincipal, 0.0);
    			AnchorPane.setRightAnchor(PaneVentanaPrincipal, 0.0);
    			AnchorPane.setLeftAnchor(PaneVentanaPrincipal, 0.0);
    			AnchorPane.setBottomAnchor(PaneVentanaPrincipal, 0.0);

    			// Se añade el contenido de la ventana cargada en el AnchorPane del padre
    			anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
    			
    			controlerPrincipal.getAvatarUsuario().setImage(new Image("/avatarCliente.png"));
    			controlerPrincipal.getGridPaneButton().setStyle("-fx-background-color: #00aae4");

    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    		
    	}
    	
		
    		
    	
    }
    

}
