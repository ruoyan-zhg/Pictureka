package Controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;

import com.jfoenix.controls.JFXTextField;

import Modelo.Guardia;
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
        
        //Se crea una tabla que almacenara informacion de los guardias
        TableView<Guardia> tableView = new TableView<Guardia>();

        //Se crean las diferentes columnas de informacion
	    TableColumn<Guardia, String> column1 = new TableColumn<>("Usuario");
	    column1.setCellValueFactory(new PropertyValueFactory<>("usuario"));


	    TableColumn<Guardia, String> column2 = new TableColumn<>("Nombre");
	    column2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	    
	    TableColumn<Guardia, String> column3 = new TableColumn<>("Primer apellido");
	    column3.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
	    
	    TableColumn<Guardia, String> column4 = new TableColumn<>("Segundo apellido");
	    column4.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
	    
	    TableColumn<Guardia, String> column5 = new TableColumn<>("DNI");
	    column5.setCellValueFactory(new PropertyValueFactory<>("dni"));
	    
	    TableColumn<Guardia, Date> column6 = new TableColumn<>("Fecha de nacimiento");
	    column6.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

	    TableColumn<Guardia, String> column7 = new TableColumn<>("Email");
	    column7.setCellValueFactory(new PropertyValueFactory<>("email"));
	    
	    TableColumn<Guardia, String> column8 = new TableColumn<>("Contraseña");
	    column8.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));

	    //Se añaden las columnas a la tabla
	    tableView.getColumns().add(column1);
	    tableView.getColumns().add(column2);
	    tableView.getColumns().add(column3);
	    tableView.getColumns().add(column4);
	    tableView.getColumns().add(column5);
	    tableView.getColumns().add(column6);
	    tableView.getColumns().add(column7);
	    tableView.getColumns().add(column8);
	    
	    //Se añade la informacion de los guardias a la tabla
	    tableView.getItems().add(new Guardia("2308", "534859348K", "jolie@gmail.com", "123456", LocalDate.of(2001, 9, 27), "Jolie", "Alain", "Vasquez"));
	    tableView.getItems().add(new Guardia("1001", "546785023F", "oscar@gmail.com", "657j862", LocalDate.of(2001, 11, 23), "Oscar", "Guerra", "Gonzalez"));
	    
	    //Se establece como MasterPane la tabla que se ha creado anteriormente
	    controlerAdmin.getMasterPaneGuardias().setMasterNode(tableView);
	    //El DetailPane será una nueva hoja de propiedades
	    controlerAdmin.getMasterPaneGuardias().setDetailNode(new PropertySheet());
	    controlerAdmin.getMasterPaneGuardias().setShowDetailNode(true);
	    
	    
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
