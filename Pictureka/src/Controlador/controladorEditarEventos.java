package Controlador;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToolbar;

import Modelo.Datos;
import Modelo.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
	public class controladorEditarEventos {

		@FXML
	    private AnchorPane anchorPanePrincipal;

	    @FXML
	    private BorderPane borderPaneEditEventos;

	    @FXML
	    private JFXToolbar toolBarAdministrador;

	    @FXML
	    private ImageView btnVolverAtras;

	    @FXML
	    private ImageView imgAvatarAdmin;

	    @FXML
	    private ComboBox comboBoxElegirEvento;

	    @FXML
	    private JFXTextArea txtAreaInfo;

	    @FXML
	    private Region imgAniadirImagen;

	    @FXML
	    private Button btnAniadirCambios;
	    
	    Datos handler = new Datos(); //Instanciamos un objeto para meter el Json de eventos en un vector
	  	Vector<Evento> eventos = new Vector<Evento>(); //creamos el vector de eventos
	    
	    public void initialize() {
	    	ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4");
	    	comboBoxElegirEvento.setItems(list);
	    	
	    	eventos = handler.desserializarJsonAEventos();//Ingresamos los datos del Json al vector de eventos
	    }
	    
	    String imagen; //Almacena el nombre (ubicacion de la imagen) que se añadirá al evento

	    @FXML
	    void accederPerfilAdmin(MouseEvent event) {

	    }

	    @FXML
	    void aniadirCambios(MouseEvent event) {
	    	Alert error = new Alert(Alert.AlertType.ERROR);
	    	Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
	    	
	    	if(comboBoxElegirEvento.getValue() != null) {
	    		if(txtAreaInfo.getLength()>=10 && txtAreaInfo.getText() != null) {
	    			if(imagen!= null) {
	    				
	    				
	    				aviso.setTitle("Exito.");
	    	    		aviso.setHeaderText("Evento cambiado exitosamente.");
	    	    		aviso.showAndWait();
	    	    		abrirAdmin();
	    			}
	    			else {
	    				error.setTitle("Datos incompletos.");
			    		error.setHeaderText("Porfavor ingrese una Imagen para añadir al evento.");
			    		error.show();
	    			}
	    			
	    			
	    			
	    		}
	    		else {
	    			error.setTitle("Datos incompletos.");
		    		error.setHeaderText("Porfavor ingrese la informacion correspondiente al evento.");
		    		error.show();
	    		}
	    		
	    		
	    	}
	    	else {
	    		error.setTitle("Datos incompletos.");
	    		error.setHeaderText("Porfavor ingrese el evento que se reemplazará.");
	    		error.show();
	    	}

	    }

	    @FXML
	    void aniadirImagen(MouseEvent event) {
	    	
	    	 
	    	        FileChooser fileChooser = new FileChooser();
	    	        fileChooser.setTitle("Buscar Imagen");

	    	        // Agregar filtros para facilitar la busqueda
	    	        fileChooser.getExtensionFilters().addAll(
	    	                new FileChooser.ExtensionFilter("All Images", "*.*"),
	    	                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
	    	                new FileChooser.ExtensionFilter("PNG", "*.png")
	    	        );

	    	        // Obtener la imagen seleccionada
	    	        File imgFile = fileChooser.showOpenDialog(null);

	    	        // Mostar la imagen
	    	        if (imgFile != null) {
	    	            Image image = new Image("file:" + imgFile.getAbsolutePath());
	    	            imgAniadirImagen.setBackground(new Background(new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY)));
	    	            imagen = "no null";
	    	        }
	    	        
	    	    

	    }

	    @FXML
	    void regresarPrincipalAdmin(MouseEvent event) {
	    	abrirAdmin();
	    	

	    }
	    
	    void abrirAdmin() {
	    	FXMLLoader loaderEdit = new FXMLLoader(getClass().getResource("/application/VentanaAdministrador.fxml"));
	        ControladorAdministrador controlerAdmin = new ControladorAdministrador();
	        loaderEdit.setController(controlerAdmin);
	        
	        try {
	        	AnchorPane PaneEdit = (AnchorPane) loaderEdit.load();
	        	anchorPanePrincipal.getChildren().clear();
	            AnchorPane.setTopAnchor(PaneEdit, 0.0);
	            AnchorPane.setRightAnchor(PaneEdit, 0.0);
	            AnchorPane.setLeftAnchor(PaneEdit, 0.0);
	            AnchorPane.setBottomAnchor(PaneEdit, 0.0);
	            anchorPanePrincipal.getChildren().setAll(PaneEdit);
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    

	


}
