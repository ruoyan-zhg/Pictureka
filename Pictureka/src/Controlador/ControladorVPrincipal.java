package Controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;

import com.jfoenix.controls.JFXTextField;

import Modelo.Datos;
import Modelo.Guardia;
import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	int countDos = 1;
	int countTres = 2;
	
	
	
    
    
	

    @FXML
    void cambioImg(MouseEvent event) {
    	
    	//Array al que se le pasan las imagenes a presentar en el image slider
    	
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
    	
    	imgViewSlider.setImage(imagenes.get(count));
    	imgViewSlider2.setImage(imagenes.get(countDos));
    	imgViewSlider3.setImage(imagenes.get(countTres));
    	
    	
    	
    	System.out.println("Uno"+count+"\n");
    	System.out.println("Dos"+countDos+"\n");
    	System.out.println("Tres"+countTres+"\n");
    
    	
    }
    
    
    
    @FXML
    void cambioImgAtras(MouseEvent event) {
    	
    	
    	
    	
    	System.out.println("Uno "+count+"\n");
    	System.out.println("Dos "+countDos+"\n");
    	System.out.println("Tres "+countTres+"\n");
    	
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
    	imgViewSlider.setImage(imagenes.get(count));
    	imgViewSlider2.setImage(imagenes.get(countDos));
    	imgViewSlider3.setImage(imagenes.get(countTres));
    	
    	
    }


    @FXML
    void accederPerfil(MouseEvent event) {
    	
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
    	
    	
    	
    	/*
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaEditGuardias.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorEditGuardias controlerAdmin = new ControladorEditGuardias();
        loaderApp.setController(controlerAdmin);
        AnchorPane PaneEditGuardias;

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
	    
		
		//Se carga la segunda ventana del TabPane
        FXMLLoader loaderTabAniadir = new FXMLLoader(getClass().getResource("/application/TabAniadirGuardia.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorTabAniadirGuardia controlerTabAniadir = new ControladorTabAniadirGuardia();
        loaderTabAniadir.setController(controlerTabAniadir);
        AnchorPane anchorTabAniadir;
        
        try {
			anchorTabAniadir = (AnchorPane) loaderTabAniadir.load();
			controlerAdmin.getAnchorTabAniadir().getChildren().clear();
            AnchorPane.setBottomAnchor(anchorTabAniadir, 0.0);
            AnchorPane.setRightAnchor(anchorTabAniadir, 0.0);
            AnchorPane.setLeftAnchor(anchorTabAniadir, 0.0);
            AnchorPane.setBottomAnchor(anchorTabAniadir, 0.0);
            controlerAdmin.getAnchorTabAniadir().getChildren().setAll(anchorTabAniadir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
    }
    

    @FXML
    void reservarTicket(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaTickets.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        ControladorTickets controlerTickets = new ControladorTickets();
        loaderApp.setController(controlerTickets);
        AnchorPane PaneTickets;

		try {
			//Se carga en un AnchorPane la ventana
			PaneTickets = (AnchorPane) loaderApp.load();
			
			//Se elimina el contenido de la ventana padre
        	anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneTickets, 0.0);
            AnchorPane.setRightAnchor(PaneTickets, 0.0);
            AnchorPane.setLeftAnchor(PaneTickets, 0.0);
            AnchorPane.setBottomAnchor(PaneTickets, 0.0);
            

            
            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneTickets);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
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
