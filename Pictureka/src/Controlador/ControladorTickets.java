package Controlador;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ControladorTickets {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private ImageView imgCliente;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private BorderPane BorderPaneTickets;

    @FXML
    private GridPane gridPaneTickets;

    @FXML
    private Label lblTickets;

    @FXML
    private JFXTextField textTickets;

    @FXML
    private Label lblHorario;

    @FXML
    private JFXTimePicker hourTickets;

    @FXML
    private JFXDatePicker dateTickets;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnContinuar;

    @FXML
    private ImageView imgTickets;
    
    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
	
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
	 public ControladorTickets(String usuario) {
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
    void CancelarReserva(ActionEvent event) {

    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    	//Se le asigna el controlador de la ventana para editar informaci�n de los guardias
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
            controlerPrincipal.setLogged(true);
            controlerPrincipal.getLblBienvenido().setVisible(false);
            controlerPrincipal.getGridPaneButton().setStyle("-fx-background-color: #00aae4");
	        controlerPrincipal.getAvatarUsuario().setImage(new Image("/avatarCliente.png"));
            

            //Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void ReservarTickets(ActionEvent event) {
    	
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	
    	String validarNumero = textTickets.getText();
    	
    	//LocalDate fechaTickets = dateTickets.getValue();
    	
    	int validar = 0;
    	
    	int ticketAleatorio = 0;
    	 
    	
    	try {
    		
    		validar = Integer.parseInt(validarNumero);
    		ticketAleatorio = (int) (Math.random()* 1000000); // ticket aleatorio
    		int value = Math.abs(validar);
    		if(value > 0 && value <=12);
    		// comprobamos fechas
    		LocalDate fechaTickets = dateTickets.getValue();
    		LocalDate fecha_Actual = LocalDate.now();
    		
    		// comprobamos hora
    		LocalTime horaTickets = hourTickets.getValue();
    		LocalTime hora_Actual = LocalTime.now();
    		int hora = hora_Actual.getHour();
    		int minutos = hora_Actual.getMinute();
    		
    		LocalTime horaMinutoTickets = LocalTime.of(hora, minutos);
    		
			if (fechaTickets.isAfter(fecha_Actual)) {
				if (fechaTickets.getDayOfWeek().toString().equals("MONDAY")
						| fechaTickets.getDayOfWeek().toString().equals("TUESDAY")
						| fechaTickets.getDayOfWeek().toString().equals("WEDNESDAY")
						| fechaTickets.getDayOfWeek().toString().equals("THURSDAY")) {
					
					if (horaTickets.isBefore(LocalTime.of(10, 00)) & horaTickets.isAfter(LocalTime.of(20, 00))) {
					
						
					}
					else {
						error.setHeaderText("La hora introducida no es válida.");
						error.setContentText("Revise los horarios.");
						error.showAndWait();
					}
					
				}

				else if (fechaTickets.getDayOfWeek().toString().equals("FRIDAY")
						| fechaTickets.getDayOfWeek().toString().equals("SATURDAY")) {

					if (horaTickets.isBefore(LocalTime.of(10, 00)) & horaTickets.isAfter(LocalTime.of(21, 00))) {

					}
					else {
						error.setHeaderText("La hora introducida no es válida.");
						error.setContentText("Revise los horarios.");
						error.showAndWait();
					}
				}
				else { 
					if (horaTickets.isBefore(LocalTime.of(11, 00)) & horaTickets.isAfter(LocalTime.of(19, 00))) {
				}
					else {
						error.setHeaderText("La hora introducida no es válida.");
						error.setContentText("Revise los horarios.");
						error.showAndWait();
					}

			}
			}
			

			else if (fechaTickets.isEqual(fecha_Actual)) {
				if (fechaTickets.isAfter(fecha_Actual)) {
					if (fechaTickets.getDayOfWeek().toString().equals("MONDAY")
							| fechaTickets.getDayOfWeek().toString().equals("TUESDAY")
							| fechaTickets.getDayOfWeek().toString().equals("WEDNESDAY")
							| fechaTickets.getDayOfWeek().toString().equals("THURSDAY")) {
						
						if (horaTickets.isBefore(LocalTime.of(10, 00)) & horaTickets.isAfter(LocalTime.of(20, 00))) {
						
							
						}
						else {
							error.setHeaderText("La hora introducida no es válida.");
							error.setContentText("Revise los horarios.");
							error.showAndWait();
						}
						
					}

					else if (fechaTickets.getDayOfWeek().toString().equals("FRIDAY")
							| fechaTickets.getDayOfWeek().toString().equals("SATURDAY")) {

						if (horaTickets.isBefore(LocalTime.of(10, 00)) & horaTickets.isAfter(LocalTime.of(21, 00))) {
						}
						
						else {
							error.setHeaderText("La hora introducida no es válida.");
							error.setContentText("Revise los horarios.");
							error.showAndWait();						
					}
					}
					else { 
						if (horaTickets.isBefore(LocalTime.of(11, 00)) & horaTickets.isAfter(LocalTime.of(19, 00))) {
					}
						else {
							error.setHeaderText("La hora introducida no es válida.");
							error.setContentText("Revise los horarios.");
							error.showAndWait();
						}

				}
				}
				

			} else {
				error.setHeaderText("La hora introducida no es válida.");
				error.setContentText("Revise los horarios.");
				error.showAndWait();
			}	
    		
    	}
        	
   
    	catch(NumberFormatException ex) {
    		error.setHeaderText("El numero de tickets introducido es invalido");
    		error.showAndWait();
    		
    	}
    }
    	
    

    @FXML
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

}
