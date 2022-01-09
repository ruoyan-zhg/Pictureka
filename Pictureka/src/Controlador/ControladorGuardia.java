package Controlador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import com.jfoenix.controls.JFXTextField;

import Modelo.Cliente;
import Modelo.Datos;
import Modelo.Informe;
import Modelo.Sala;
import Modelo.Tickets;
import Modelo.modelo_Museo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControladorGuardia {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private JFXTextField numTicket;

    @FXML
    private JFXTextField tituloInforme;

    @FXML
    private JFXTextField cuerpoInforme;

    @FXML
    private Text SalaText;

    @FXML
    private ImageView imgTicket;

    @FXML
    private ImageView imgCerrarSesion1111;

    @FXML
    private ButtonBar imgValidar;

    @FXML
    private ImageView imgEnviarInforme;

    @FXML
    private ImageView imgFormulario;

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
	  * Constructor de la calse <b>ControladorGuardia</b> que guarda la informaci�n del usuario.
	  * 
	  * @param usuario		Informaci�n del usuario que se encuentra iniciado sesi�n.
	  */
	 public ControladorGuardia(String usuario) {
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
     * Muestra la ventana Perfil, donde el guarda puede visualizar su informaci�n personal.
     * 
     *@param event		Evento causado cuando el guardia pulsa sobre su avatar.
     */
    void accederPerfil(MouseEvent event) {
    	if(logged == false) {
        	Alert error = new Alert(Alert.AlertType.ERROR);
			error.setHeaderText("Oh no! Para acceder a esta funci�n debes estar iniciado sesi�n.");
    		error.showAndWait();
        	
        }
        else {
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderPrincipala = new FXMLLoader(getClass().getResource("/application/VentanaPerfil.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
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
                

                //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
                anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
                
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}

        }
    	
    }
    
    @FXML
    void enviar(MouseEvent event) {
    	Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
		Alert error = new Alert(Alert.AlertType.ERROR);
    	if(!(tituloInforme.getText().isEmpty() | cuerpoInforme.getText().isEmpty() )) {
    		if(cuerpoInforme.getText().matches("^.{20,}")) {
    			modelo_Museo museo = new modelo_Museo();
    			String nombre = museo.getRegistro().rDevolverNombreStaff(usuario);
    			try {
    				museo.getRegistro().escribirInforme(nombre, tituloInforme.getText(), cuerpoInforme.getText());
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

    @FXML
    /**
     * 
     * Devuelve al guardia a la ventana principal, cerrando su sesi�n actual.
     * 
     * @param event		Evento causado cuando el guardia pulsa sobre la imagen de cerrar sesi�n.
     */
    void cerrarSesion(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
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
            

            //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	
    }

    @FXML
    /**
     * 
     * Muestra la ventana de la Sala 1, mostrando la informaci�n de los diferentes sensores.
     * 
     * @param event		Evento causado cuando el guardia pulsa sobre la imagen de la primera sala.
     */
    void sala1(MouseEvent event) {
    	modelo_Museo museo = new modelo_Museo();
    	Sala temporal = museo.getMuseo().recuperar1Salas(1);
    	if (temporal.getIdentificador() != -1){			//debido a que si tiene un identificador -1 significa que no exite
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
        	
            ControladorSalas controlerSala1 = new ControladorSalas(usuario, temporal, "Guardia");
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
                

                //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
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
	 * Muestra la ventana de la Sala 2, mostrando la informaci�n de los diferentes sensores.
	 * 
	 * @param event		Evento causado cuando el guardia pulsa sobre la imagen de la segunda sala.
	 */
    void sala2(MouseEvent event) {
		modelo_Museo museo = new modelo_Museo();
    	Sala temporal = museo.getMuseo().recuperar1Salas(2);
    	if (temporal.getIdentificador() != -1){		//debido a que si tiene un identificador -1 significa que no exite
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
        	
            ControladorSalas controlerSala1 = new ControladorSalas(usuario, temporal, "Guardia");
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
                

                //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
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
     * Muestra la ventana de la Sala 3, mostrando la informaci�n de los diferentes sensores.
     * 
     *@param event		Evento causado cuando el guardia pulsa sobre la imagen de la tercera sala.
     */
    void sala3(MouseEvent event) {
    	
    	modelo_Museo museo = new modelo_Museo();
    	Sala temporal = museo.getMuseo().recuperar1Salas(3);
    	if (temporal.getIdentificador() != -1){		//debido a que si tiene un identificador -1 significa que no exite
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
        	
            ControladorSalas controlerSala1 = new ControladorSalas(usuario, temporal, "Guardia");
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
                

                //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
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
     * Muestra la ventana de la Sala 4, mostrando la informaci�n de los diferentes sensores.
     * 
     * @param event		Evento causado cuando el guardia pulsa sobre la imagen de la cuarta sala.
     */
    void sala4(MouseEvent event) {
    	
    	modelo_Museo museo = new modelo_Museo();
    	Sala temporal = museo.getMuseo().recuperar1Salas(4);
    	if (temporal.getIdentificador() != -1){		//debido a que si tiene un identificador -1 significa que no exite
        	//Se carga el contenido de la ventana
        	FXMLLoader loaderSala1 = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
        	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
        	
            ControladorSalas controlerSala1 = new ControladorSalas(usuario, temporal, "Guardia");
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
                

                //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
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
     * M�todo que valida un ticket de un cliente. Se introduce el identificador del ticket y se comprueba que sea v�lido.
     * 
     * @param event		Evento causado cuando el guardia pulsa sobre la imagen para validar el ticket.
     */
    void validarTicket(MouseEvent event) {
    	
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert informative = new Alert(Alert.AlertType.CONFIRMATION);
    	String ticketAcomprobar = numTicket.getText();
    	int identificadorTicket = 0;
    	try {
    		identificadorTicket = Integer.parseInt(ticketAcomprobar);
    		if (identificadorTicket >= 100000 && identificadorTicket <= 999999) {
    			System.out.println("Ticket dentro del rango");
    			
    	    	Datos datos = new Datos();
    	    	Vector<Cliente> clientes = datos.desserializarJsonAusuarios();
    	    	Vector<Tickets> tickets;
    	    	
    	    	for(int i=0; i<clientes.size(); i++) {
    	    		tickets = clientes.get(i).getTickets();
    	    		for (int j=0; j<tickets.size(); j++) {
    	    			if (tickets.get(j).getIdentificador()==identificadorTicket) {
    	    				
    	    			}
    	    		}
    	    		
    	    	}
    			
    		}
    		else {
    			error.setHeaderText("Rango del identificador del ticket no aceptable.");
    			error.showAndWait();
    		}
    		
    		
    	}
    	catch(NumberFormatException ex) {
    		error.setHeaderText("Formato del identificador no v�lido.");
    		error.showAndWait();
    	}
    	
    	

    }
    
    /**
     * 
     * M�todo que muestra un error al guardia sobre la sala.
     * 
     */
    private void mostrarErrorSala() {
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	error.setHeaderText("La sala no esta disponible");
		error.show();
		
	}
    
  
	
}
