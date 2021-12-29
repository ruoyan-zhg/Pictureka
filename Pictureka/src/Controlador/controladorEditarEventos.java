package Controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.control.TextField;
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
	    
	    @FXML
	    private TextField txtFieldTitulo;
	    
	    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
		
	    boolean logged; //Este nos dira si la parsona esta logueada o no
	    
	    
		 
		 public controladorEditarEventos(String usuario) {
			 if (usuario == "vacio") {
				 logged = false;
			 }
			 else {
				 this.usuario = usuario;
				 logged = true;
			 }
			 
		}
	    
	    
	    
	    
	    private Path pathImagen;
	    
	    private Path pathSRC; //Creamos un objeto Path para guardar el path del src
	    
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
	    				if(txtFieldTitulo.getLength()>=10 && txtFieldTitulo.getText() != null) {
		    				
		    				//Si todo sale bien la imagen se guarda en el src
		    				try {
								Files.copy(pathImagen, pathSRC);
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		    				
	    					//Se guradan los datos en el vector de eventos
	    					guardarDatos();
	    					//y se actualiza el Json con los datos actualizados del vector
		    				handler.serializarVectorEventosAJson(eventos);
		    				aviso.setTitle("Exito.");
		    	    		aviso.setHeaderText("Evento cambiado exitosamente.");
		    	    		aviso.showAndWait();
		    	    		abrirAdmin();
	    				}
	    				else {
	    					error.setTitle("Datos incompletos.");
				    		error.setHeaderText("Porfavor ingrese un titulo adecuado para el evento.");
				    		error.show();
	    				}

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
	    	            imagen = imgFile.getName();
	    	            //para guardar el path de la imagen seleccionada
	    	            pathImagen = imgFile.toPath();
	    	            //Se almacena el path al que ira dirigido la imagen con el nombre que ya tenia la imagen seleccionada
	    	            pathSRC = Paths.get("C:\\Users\\jolie\\OneDrive\\Documentos\\GitHub\\PR_INF_21-22-pictureka\\Pictureka\\src\\" + imgFile.getName());
	    	            
	    	            
	    	        }
	    	        
	    	    

	    }

	    @FXML
	    void regresarPrincipalAdmin(MouseEvent event) {
	    	abrirAdmin();
	    	

	    }
	    
	    void abrirAdmin() {
	    	FXMLLoader loaderEdit = new FXMLLoader(getClass().getResource("/application/VentanaAdministrador.fxml"));
	        ControladorAdministrador controlerAdmin = new ControladorAdministrador(usuario);
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
	    
	    public void guardarDatos() {//Este metodo guardara los cambios en el Json de eventos con la informacion que haya solicitado
	    	int seleccion = Integer.parseInt(comboBoxElegirEvento.getValue().toString());
	    	if(seleccion == 1) {
	    		eventos.elementAt(0).setNombre(txtFieldTitulo.getText());
	    		eventos.elementAt(0).setImagen("/"+imagen);
	    		eventos.elementAt(0).setInformacion(txtAreaInfo.getText());
	    		
	    	}
	    	else if(seleccion == 2) {
	    		eventos.elementAt(1).setNombre(txtFieldTitulo.getText());
	    		eventos.elementAt(1).setImagen("/"+imagen);
	    		eventos.elementAt(1).setInformacion(txtAreaInfo.getText());
	    	}
	    	else if(seleccion == 3) {
	    		eventos.elementAt(2).setNombre(txtFieldTitulo.getText());
	    		eventos.elementAt(2).setImagen("/"+imagen);
	    		eventos.elementAt(2).setInformacion(txtAreaInfo.getText());
	    	}
	    	else{
	    		eventos.elementAt(3).setNombre(txtFieldTitulo.getText());
	    		eventos.elementAt(3).setImagen("/"+imagen);
	    		eventos.elementAt(3).setInformacion(txtAreaInfo.getText());
	    	}
	    	
	    }

	   
	    
	    

	


}
