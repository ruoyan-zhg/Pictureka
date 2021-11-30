package Controlador;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControladorVPrincipal {

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
	 private ImageView imgViewSlider;

	 @FXML
	 private ImageView imgViewSlider3;

	 @FXML
	 private ImageView imgViewSlider2;

	 @FXML
	 private ImageView imgView_BtnFlecha1;

	 @FXML
	 private ImageView imgView_BtnFlecha;

	ArrayList<Image> imagenes = new ArrayList<Image>();
 	
	@FXML
	public void initialize() {
		imagenes.add(new Image("/MonaLisa.jpg"));
	 	imagenes.add(new Image("/Dali.jpg"));
	 	imagenes.add(new Image("/Sixtina.jpg"));
	 	imagenes.add(new Image("/scream.jpg"));
	 	imagenes.add(new Image("/VanGogh.jpg"));
	 	imagenes.add(new Image("/people.jpg"));
	}
	
	int count = 0;
	
	
    
    
	

    @FXML
    void cambioImg(MouseEvent event) {
    	
    	//Array al que se le pasan las imagenes a presentar en el image slider
    	
    	
    	imgViewSlider.setImage(imgViewSlider2.getImage());
    	imgViewSlider2.setImage(imgViewSlider3.getImage());
    	imgViewSlider3.setImage(imagenes.get(count));
    	
    	count++;
    	if(count>=imagenes.size()) {
    		count=0;
    	}
    	
    	//System.out.println(count+"\n");
    
    	
    }
    
    
    
    @FXML
    void cambioImgAtras(MouseEvent event) {
    	
    	count--;
    	if(count<0) {
    		count=(imagenes.size())-1;
    	}
    	imgViewSlider3.setImage(imgViewSlider2.getImage());
    	imgViewSlider2.setImage(imgViewSlider.getImage());
    	imgViewSlider.setImage(imagenes.get(count));
    	
    }


    @FXML
    void accederPerfil(MouseEvent event) {
    	
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/InterfazLogin.fxml"));
        ControladorInicioSesion controlerInicio = new ControladorInicioSesion();
        loaderApp.setController(controlerInicio);
    }

    @FXML
    void reservarTicket(MouseEvent event) {
    	
    }

    @FXML
    void tocarLupa(MouseEvent event) {

    }

    @FXML
    void verEventos(MouseEvent event) {

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
    void tocarCalendario(MouseEvent event) {

    }

}
