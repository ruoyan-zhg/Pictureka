package Controlador;

import java.util.ArrayList;
import java.util.Vector;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
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
    private ButtonBar btnBarArriba;

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
    private Region region1;

    @FXML
    private Region region2;

    @FXML
    private Region region3;

    @FXML
    private GridPane GridPaneObras2;

    @FXML
    private JFXTextArea TextAreaQuitasol;

    @FXML
    private JFXTextArea TextAreaMeninas;


    @FXML
    private Region regionEventoDerecha;

    @FXML
    private Region regionEventoIzq;
    
    //ArrayList que guardara las imagenes que se mostraran en la ventana principal
  	ArrayList<Image> imagenes = new ArrayList<Image>();
  	Datos handler = new Datos(); //Instanciamos un objeto para meter el Json de eventos en un vector
  	Vector<Evento> eventos = new Vector<Evento>(); //creamos el vector de eventos
  	
  	//Contadores que indicaran como un puntero las imagenes que se muestran en los imgviews del image slider
   	int count = 0;
   	int countDos = 1;
   	int countTres = 2;
   	boolean logged = false; //Este nos dira si la parsona esta logueada o no
   	
  	@FXML
  	public void initialize() {
  		//Se añaden al ArrayList las imagenes que queremos que se muestren
  		imagenes.add(new Image("/MonaLisa.jpg"));
  	 	imagenes.add(new Image("/Dali.jpg"));
  	 	imagenes.add(new Image("/Sixtina.jpg"));
  	 	imagenes.add(new Image("/scream.jpg"));
  	 	imagenes.add(new Image("/VanGogh.jpg"));
  	 	imagenes.add(new Image("/people.jpg"));
  	 	
  	 	eventos = handler.desserializarJsonAEventos();//Ingresamos los datos del Json al vector de eventos
  	 	
  	 	region1.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(0)), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	region2.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(1)), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	region3.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenes.get(2)), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	
  	 	
  	 	Image horario = new Image(eventos.elementAt(0).getImagen()); //A las imagenes correspondientes le asignamos la direccion en la que se encuentran
  	 	Image museo = new Image(eventos.elementAt(1).getImagen());
  	 	//Luego se le asigna la descripcion (A futuro).
  	 	
  	 	regionEventoIzq.setBackground(new Background(new BackgroundFill(new ImagePattern(museo), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	regionEventoDerecha.setBackground(new Background(new BackgroundFill(new ImagePattern(horario), CornerRadii.EMPTY, Insets.EMPTY)));
  	 	
  	 	
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

    }

    @FXML
    void tocarLupa(MouseEvent event) {

    }

    @FXML
    void verEventos(MouseEvent event) {

    }

}
