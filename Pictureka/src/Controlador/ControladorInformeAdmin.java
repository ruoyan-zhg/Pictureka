package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Modelo.Datos;
import Modelo.Guardia;
import Modelo.Informe;
import Modelo.Staff;
import Modelo.modelo_Museo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControladorInformeAdmin {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private JFXTextArea mostrarInforme;

    @FXML
    private TableView<Informe> tablaInformes;
    
    @FXML
    private TableColumn<Informe, String> Autor;
    
    @FXML
    private TableColumn<Informe, String> Titulo;
    
    @FXML
    private TableColumn<Informe, String> Cuerpo;
    
    @FXML
    private TableColumn<Informe, String> Fecha;
    

    @FXML
    private JFXTextField tituloInforme;

    @FXML
    private JFXTextField cuerpoInforme;

    @FXML
    private ImageView imgFormulario;

    @FXML
    private ImageView imgEnviarInforme;
    
    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
	
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
    private String informe;

	private Vector<Informe> informes;
    
    
	 
	 public ControladorInformeAdmin(String usuario) {
		 if (usuario == "vacio") {
			 logged = false;
			 this.usuario = usuario;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
		 }
		 
	}
	 
	public void initialize() {
		refrescarTabla();
	}
	
	@FXML
	public void clickItem(MouseEvent event){
		if (!tablaInformes.getSelectionModel().isEmpty()) {
			int posicion = tablaInformes.getSelectionModel().getSelectedIndex();
			mostrarInforme(-posicion+informes.size()-1);
		}
		
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
    void cerrarSesion(MouseEvent event) {

    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
    	this.usuario = "vacio";
    	this.logged = false;
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
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

    }
    
    @FXML
    void volverAtras(MouseEvent event) {
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
    

    @FXML
    void enviarInforme(MouseEvent event) {
    	Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
		Alert error = new Alert(Alert.AlertType.ERROR);
    	if(!(tituloInforme.getText().isEmpty() | cuerpoInforme.getText().isEmpty() )) {
    		if(cuerpoInforme.getText().matches("^.{20,}")) {
    			modelo_Museo museo = new modelo_Museo();
    			String nombre = museo.getRegistro().rDevolverNombreStaff(usuario);
    			try {
    				museo.getRegistro().escribirInforme(nombre, tituloInforme.getText(), cuerpoInforme.getText());
    				refrescarTabla();
    				confirmacion.setHeaderText("Informe guardado con exito");
        			confirmacion.show();
    			}
    			catch(FileNotFoundException e){
    				error.setHeaderText("Archivo no encontrado!");
            		error.show();
    			}
    			
    		}
    		else {
        		error.setHeaderText("Porfavor el cuerpo del informe debe ser mayor a 20 caracteres");
        		error.show();
    		}
    	}
    	else {
    		error.setHeaderText("Porfavor rellene los campos del informe!");
    		error.show();
    	}
    }
    
    private void refrescarTabla() {
    	modelo_Museo museo = new modelo_Museo();
		Vector <Informe> _informes = museo.getRegistro().devolverInforme();
		tablaInformes.getItems().clear();
		if(_informes.size() > 0) {
			this.informes = _informes;
			for (int i=(informes.size()-1); i >= 0; i--) {
				//Se muestran los informes obtenidos en la tabla
				tablaInformes.getItems().add(informes.elementAt(i));
			}
			//Obtenemos el las diferentes columnas de la tabla y asociamos cada columna al tipo de dato que queremos guardar
			Autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
			Titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
			Cuerpo.setCellValueFactory(new PropertyValueFactory<>("cuerpo"));
			Fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			mostrarInforme(0);
			
		}
	}

	private void mostrarInforme(int posicion) {
    	this.informe = "Autor:  "+informes.elementAt(posicion).getAutor()
    			+ "\n" + "Fecha:  "+ informes.elementAt(posicion).getFecha()
    			+ "\n\n" + "Titulo:  "+ informes.elementAt(posicion).getTitulo()
    			+ "\n\n" +informes.elementAt(posicion).getCuerpo();
    			mostrarInforme.setText(informe);
	}

}
