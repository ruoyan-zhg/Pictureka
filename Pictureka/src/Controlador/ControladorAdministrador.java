package Controlador;

import java.io.IOException;
import com.jfoenix.controls.JFXToolbar;
import Modelo.Sala;
import Modelo.modelo_Museo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * En esta clase se manejan las funcionalidades del administrador, en la vista <b>VentanaAdministrador</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */

public class ControladorAdministrador {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private JFXToolbar toolBarAdministrador;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private ImageView imgAvatarAdmin;

    @FXML
    private ImageView imgInformes;

    @FXML
    private LineChart<?, ?> tablaEntradas;

    @FXML
    private ImageView imgEditarGuardias;

    @FXML
    private ImageView imgEditarEventos;
    
    @FXML
    private ImageView imgEditarAdministrador;

    @FXML
    private JFXToolbar ToolBarSensores;

    @FXML
    private Text textSensores;

    @FXML
    private ImageView imgSala1;

    @FXML
    private ImageView imgSala2;

    @FXML
    private ImageView imgSala4;

    @FXML
    private ImageView imgSala3;
    
    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
	
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
	 /**
	  * 
	  * Constructor de la clase <b>ControladorAdministrador</b> que guarda la información del administrador.
	  * 
	  * @param usuario		El administrador que se encuentre iniciado sesión.
	  */
	 public ControladorAdministrador(String usuario) {
		 if (usuario == "vacio") {
			 logged = false;
			 this.usuario = usuario;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
		 }
		 
	}

    @FXML
    /**
     * 
     * Muestra todos los informes enviados, y ofrece la posibilidad de crear nuevos informes.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen de los informes.
     */
    void accederInformes(MouseEvent event) {
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaInformeAdmin.fxml"));
    	//Se le asigna el controlador de la ventana para editar informacion de los guardias
    	
        ControladorInformeAdmin controlerSala1 = new ControladorInformeAdmin(usuario);
        loaderSala1.setController(controlerSala1);
        AnchorPane PaneSala1;

		try {
			//Se carga en un AnchorPane la ventana
			PaneSala1 = (AnchorPane) loaderSala1.load();
			
			//Se elimina el contenido de la ventana padre
			anchorPanePrincipal.getChildren().clear();
        	
        	//Se ajusta el AnchorPane para que sea escalable
            AnchorPane.setTopAnchor(PaneSala1, 0.0);
            AnchorPane.setRightAnchor(PaneSala1, 0.0);
            AnchorPane.setLeftAnchor(PaneSala1, 0.0);
            AnchorPane.setBottomAnchor(PaneSala1, 0.0);
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneSala1);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}

    }

    @FXML
    /**
     * 
     * Muestra la información del administrador que se encuentre iniciado sesión.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen de su avatar.
     */
    void accederPerfil(MouseEvent event) {
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipala = new FXMLLoader(getClass().getResource("/application/VentanaPerfil.fxml"));
    	//Se le asigna el controlador de la ventana para editar informacion de los guardias
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
            
            //Cambia el color de la barra de la ventana perfil
            controlerPrincipal.getBarra().setStyle("-fx-background-color:  #FF8000");
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}

    }

    @FXML
    /**
     * 
     * Devuelve al administrador a la ventana principal habiendo cerrado su sesión.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen de cerrar sesión.
     */
    void cerrarSesion(MouseEvent event) {
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    	this.usuario = "vacio";
    	this.logged = false;
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal(usuario);
        loaderPrincipal.setController(controlerPrincipal);
        
        try {
        	AnchorPane registerPane = (AnchorPane) loaderPrincipal.load();
        	anchorPanePrincipal.getChildren().clear();
            AnchorPane.setTopAnchor(registerPane, 0.0);
            AnchorPane.setRightAnchor(registerPane, 0.0);
            AnchorPane.setLeftAnchor(registerPane, 0.0);
            AnchorPane.setBottomAnchor(registerPane, 0.0);
            anchorPanePrincipal.getChildren().setAll(registerPane);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    /**
     * 
     * Muestra la ventana para editar eventos, como su t�tulo, contenido e imagen.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen para editar eventos.
     */
    void editarEventos(MouseEvent event) {
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderEdit = new FXMLLoader(getClass().getResource("/application/VentanaEditarEventos.fxml"));
        controladorEditarEventos controlerEditEvents = new controladorEditarEventos(usuario);
        loaderEdit.setController(controlerEditEvents);
        
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
    /**
     * 
     * Muestra todos los guardias y ofrece la posibilidad de editar su información o registrar uno nuevo.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen para editar guardias.
     */
    void editarGuardias(MouseEvent event) {
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderEdit = new FXMLLoader(getClass().getResource("/application/VentanaEditGuardias.fxml"));
        ControladorEditGuardias controlerEdit = new ControladorEditGuardias(usuario);
        loaderEdit.setController(controlerEdit);
        
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
    void mostrarInfoSensores(MouseEvent event) {

    }

    @FXML
    
    /**
     * 
     * Muestra la ventana de la Sala 1, mostrando la información de los diferentes sensores.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen de la primera sala.
     */
    void sala1(MouseEvent event) {
    	modelo_Museo museo = new modelo_Museo();
    	Sala temporal = museo.getMuseo().recuperar1Salas(1);
    	if (temporal.getIdentificador() != -1){			//debido a que si tiene un identificador -1 significa que no exite
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
        	//Se le asigna el controlador de la ventana para editar informacion de los guardias
        	
            ControladorSalas controlerSala1 = new ControladorSalas(usuario, temporal, "Administrador");
            loaderSala1.setController(controlerSala1);
            AnchorPane PaneSala1;

    		try {
    			//Se carga en un AnchorPane la ventana
    			PaneSala1 = (AnchorPane) loaderSala1.load();
    			
    			//Se elimina el contenido de la ventana padre
    			anchorPanePrincipal.getChildren().clear();
            	
            	//Se ajusta el AnchorPane para que sea escalable
                AnchorPane.setTopAnchor(PaneSala1, 0.0);
                AnchorPane.setRightAnchor(PaneSala1, 0.0);
                AnchorPane.setLeftAnchor(PaneSala1, 0.0);
                AnchorPane.setBottomAnchor(PaneSala1, 0.0);
                

                //Se añade el contenido de la ventana cargada en el AnchorPane del padre
                anchorPanePrincipal.getChildren().setAll(PaneSala1);
                
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        	
    	}
    	else {
    		mostrarErrorSala();
    	}

    }

    @FXML
    /**
     * 
     * Muestra la ventana de la Sala 2, mostrando la información de los diferentes sensores.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen de la segunda sala.
     */
    void sala2(MouseEvent event) {

    	modelo_Museo museo = new modelo_Museo();
    	Sala temporal = museo.getMuseo().recuperar1Salas(2);
    	if (temporal.getIdentificador() != -1){		//debido a que si tiene un identificador -1 significa que no exite
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
        	//Se le asigna el controlador de la ventana para editar informacion de los guardias
        	
            ControladorSalas controlerSala1 = new ControladorSalas(usuario, temporal, "Administrador");
            loaderSala1.setController(controlerSala1);
            AnchorPane PaneSala1;

    		try {
    			//Se carga en un AnchorPane la ventana
    			PaneSala1 = (AnchorPane) loaderSala1.load();
    			
    			//Se elimina el contenido de la ventana padre
    			anchorPanePrincipal.getChildren().clear();
            	
            	//Se ajusta el AnchorPane para que sea escalable
                AnchorPane.setTopAnchor(PaneSala1, 0.0);
                AnchorPane.setRightAnchor(PaneSala1, 0.0);
                AnchorPane.setLeftAnchor(PaneSala1, 0.0);
                AnchorPane.setBottomAnchor(PaneSala1, 0.0);
                

                //Se añade el contenido de la ventana cargada en el AnchorPane del padre
                anchorPanePrincipal.getChildren().setAll(PaneSala1);
                
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        	
    	}
    	else {
    		mostrarErrorSala();
    	}
    }

    @FXML
    /**
     * 
     * Muestra la ventana de la Sala 3, mostrando la información de los diferentes sensores.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen de la tercera sala.
     */
    void sala3(MouseEvent event) {

    	modelo_Museo museo = new modelo_Museo();
    	Sala temporal = museo.getMuseo().recuperar1Salas(3);
    	if (temporal.getIdentificador() != -1){		//debido a que si tiene un identificador -1 significa que no exite
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
        	
            ControladorSalas controlerSala1 = new ControladorSalas(usuario, temporal, "Administrador" );
            loaderSala1.setController(controlerSala1);
            AnchorPane PaneSala1;

    		try {
    			//Se carga en un AnchorPane la ventana
    			PaneSala1 = (AnchorPane) loaderSala1.load();
    			
    			//Se elimina el contenido de la ventana padre
    			anchorPanePrincipal.getChildren().clear();
            	
            	//Se ajusta el AnchorPane para que sea escalable
                AnchorPane.setTopAnchor(PaneSala1, 0.0);
                AnchorPane.setRightAnchor(PaneSala1, 0.0);
                AnchorPane.setLeftAnchor(PaneSala1, 0.0);
                AnchorPane.setBottomAnchor(PaneSala1, 0.0);
                

                //Se añade el contenido de la ventana cargada en el AnchorPane del padre
                anchorPanePrincipal.getChildren().setAll(PaneSala1);
                
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        	
    	}
    	else {
    		mostrarErrorSala();
    	}
    }

    @FXML
    /**
     * 
     * Muestra la ventana de la Sala 4, mostrando la información de los diferentes sensores.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen de la cuarta sala.
     */
    void sala4(MouseEvent event) {

    	modelo_Museo museo = new modelo_Museo();
    	Sala temporal = museo.getMuseo().recuperar1Salas(4);
    	if (temporal.getIdentificador() != -1){		//debido a que si tiene un identificador -1 significa que no exite
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
        	//Se le asigna el controlador de la ventana para editar informacion de los guardias
        	
            ControladorSalas controlerSala1 = new ControladorSalas(usuario, temporal, "Administrador");
            loaderSala1.setController(controlerSala1);
            AnchorPane PaneSala1;

    		try {
    			//Se carga en un AnchorPane la ventana
    			PaneSala1 = (AnchorPane) loaderSala1.load();
    			
    			//Se elimina el contenido de la ventana padre
    			anchorPanePrincipal.getChildren().clear();
            	
            	//Se ajusta el AnchorPane para que sea escalable
                AnchorPane.setTopAnchor(PaneSala1, 0.0);
                AnchorPane.setRightAnchor(PaneSala1, 0.0);
                AnchorPane.setLeftAnchor(PaneSala1, 0.0);
                AnchorPane.setBottomAnchor(PaneSala1, 0.0);
                

                //Se añade el contenido de la ventana cargada en el AnchorPane del padre
                anchorPanePrincipal.getChildren().setAll(PaneSala1);
                
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        	
    	}
    	else {
    		mostrarErrorSala();
    	}
    }
    
    @FXML
    /**
     * 
     * Muestra una lista de los administradores, ofreciendo la posibilidad de editar su información o de registrar uno nuevo.
     * 
     * @param event		Evento causado cuando el administrador pulsa sobre la imagen de edicción de un administrador.
     */
    void editarInfoAdministrador(MouseEvent event) {

    	//Se carga el contenido de la ventana
    	FXMLLoader loaderEdit = new FXMLLoader(getClass().getResource("/application/VentanaEditarAdministrador.fxml"));
        ControladorEditarAdministrador controlerEdit = new ControladorEditarAdministrador(usuario);
        loaderEdit.setController(controlerEdit);
        
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
    
    /**
     * 
     * M�todo que muestra un error al administrador sobre la sala.
     * 
     */
    private void mostrarErrorSala() {
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	error.setHeaderText("La sala no está disponible");
		error.show();
		
	}
    

    
    
	public JFXToolbar getToolBarAdministrador() {
		return toolBarAdministrador;
	}

	public void setToolBarAdministrador(JFXToolbar toolBarAdministrador) {
		this.toolBarAdministrador = toolBarAdministrador;
	}
	

}
