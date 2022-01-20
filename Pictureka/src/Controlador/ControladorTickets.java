package Controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import Modelo.Cliente;
import Modelo.Datos;
import Modelo.Reserva;
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

/**
 * 
 * En esta clase se maneja la funcionalidad para reservar tickets de la vista <b>VentanaTickets</b>
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */

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
    
    /**
     * 
     * Constructor de la clase <b>ControladorTickets</b> que guarda información del usuario.
     * 
     * @param usuario		El usuario que está inciado sesión en ese momento.
     */
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
    /**
     * 
     * Devuelve al usuario a la ventana principal, cancelando la reserva que se estuviera realizando.
     * 
     * @param event		Evento causado cuando el usuario pulsa sobre el botón "Cancelar".
     */
    void CancelarReserva(ActionEvent event) {

    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
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
            

            //Se añade el contenido de la ventana cargada en el AnchorPane del padre
            anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
            
           
            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    /**
     * 
     * Método que realiza la reserva de tickets de un cliente. Creando un identificador para cada una de las reservas, junto con
     * la información introducida: número de tickets, fecha y hora de reserva.
     * 
     * @param event		Evento causado cuando el usuario confirma su reserva cuando pulsa sobre el botón de "Continuar".
     */
    void ReservarTickets(ActionEvent event) {
    	
    	//Creacion de las diferentes alertas
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    	
		int numTickets = 0;
		//Se cargan los datos de los Json en los vectores
		Datos datos = new Datos();
    	Vector<Cliente> clientes = datos.desserializarJsonAusuarios();
    	Vector<Reserva> reservas = datos.desserializarJsonAReservas();
    	
    	int idReserva = 0;
    	//Se genera un numero de 6 digitos entre (100000-999999) como identificador
    	idReserva = (int) ((Math.random()*(999999-100000) + 100000));
		
    	//Se comprueba que ninguno de los campos se encuentre vacio
		if (!(textTickets.getText().isEmpty() | hourTickets.getValue() == null | dateTickets.getValue() == null)) {

			try {
				// Se parsea lo introducido por el usuario a un int
				numTickets = Integer.parseInt(textTickets.getText());
				int tickets = 0;
				// Se realiza el valor absoluto del numero de tickets
				tickets = Math.abs(numTickets);
				// Se comprueba que el usuario pueda reservar entre el minimo y maximo de
				// tickets
				if (tickets >= 1 && tickets <= 12) {

					// Se obtienen las fechas y horas actuales para compararla con la hora y fecha
					// seleccionada
					LocalDate dateSeleccionada = dateTickets.getValue();
					LocalTime horaSeleccionada = hourTickets.getValue();

					// Se obtienen las fechas actuales
					LocalDate fechaHoy = LocalDate.now();
					LocalTime horaHoy = LocalTime.now();

					int hour = horaHoy.getHour();
					int min = horaHoy.getMinute();
					// Obtenemos solo la hora y los min de la hora actual
					LocalTime horaHoy2 = LocalTime.of(hour, min);

					// la fecha seleccionada es menor que la actual
					if (dateSeleccionada.isBefore(fechaHoy)) {
						error.setHeaderText("La fecha seleccionada es inválida.");
						error.showAndWait();

					}
					// la fecha seleccionada es mayor que la fecha actual
					else if (dateSeleccionada.isAfter(fechaHoy)) {

						// comprobar que la hora seleccionada, se encuentra dentro del rango de apertura
						// del museo
 
						if (dateSeleccionada.getDayOfWeek().toString().equals("MONDAY")
								| dateSeleccionada.getDayOfWeek().toString().equals("TUESDAY")
								| dateSeleccionada.getDayOfWeek().toString().equals("WEDNESDAY")
								| dateSeleccionada.getDayOfWeek().toString().equals("THURSDAY")) {

							// Comrpobar con el horario de lunes a jueves
							
							if (horaSeleccionada.isAfter(LocalTime.of(10, 00)) && horaSeleccionada.isBefore(LocalTime.of(20, 00))) {
								
								//Se recorre el vector de clientes
								for (int i=0; i<clientes.size(); i++) {
						    		if (clientes.get(i).getUsuario().equals(usuario)) {
						    			//Se introduce el identificador en el vector de reservas
						    			clientes.get(i).getReservas().add(idReserva);
						    		}
						    	}
								//En el json de reservas se aniade la reserva con su respectiva informacion
						    	reservas.addElement(new Reserva(idReserva,tickets,dateTickets.getValue(), hourTickets.getValue()));
						    	datos.serializarVectorReservasAJson(reservas);
						    	datos.serializarArrayAJson(clientes);
						    	
						    	confirmation.setHeaderText("Reserva realizada con éxito.");
						    	confirmation.showAndWait();
								
								 

								
							}
							else {
								error.setHeaderText("La hora seleccionada es inválida.");
								error.setContentText("Revise nuestros horarios de apertura.");
								error.showAndWait();
							}
							
							
						}
						// Si el dia que ha seleccionado es un viernes o sabado
						else if (dateSeleccionada.getDayOfWeek().toString().equals("FRIDAY")
								| dateSeleccionada.getDayOfWeek().toString().equals("SATURDAY")) {

							// Comprobar con el horario de viernes a sabado
							if (horaSeleccionada.isAfter(LocalTime.of(10, 00)) && horaSeleccionada.isBefore(LocalTime.of(21, 00))) {
								
								for (int i=0; i<clientes.size(); i++) {
						    		if (clientes.get(i).getUsuario().equals(usuario)) {
						    			clientes.get(i).getReservas().add(idReserva);
						    		}
						    	}
								//En el json de reservas se aniade la reserva con su respectiva informacion
						    	reservas.addElement(new Reserva(idReserva,tickets,dateTickets.getValue(), hourTickets.getValue()));
						    	datos.serializarVectorReservasAJson(reservas);
						    	datos.serializarArrayAJson(clientes);
						    	
						    	confirmation.setHeaderText("Reserva realizada con éxito.");
						    	confirmation.showAndWait();
								
							}
							else {
								error.setHeaderText("La hora seleccionada es inválida.");
								error.setContentText("Revise nuestros horarios de apertura.");
								error.showAndWait();
							}
							
							
						}
						// Si el dia que ha seleccionado es un domingo
						else {
							// Comprobar con el horario del domingo
							if (horaSeleccionada.isAfter(LocalTime.of(11, 00)) && horaSeleccionada.isBefore(LocalTime.of(19, 00))) {
								
								for (int i=0; i<clientes.size(); i++) {
						    		if (clientes.get(i).getUsuario().equals(usuario)) {
						    			clientes.get(i).getReservas().add(idReserva);
						    		}
						    	}
								//En el json de reservas se aniade la reserva con su respectiva información
						    	reservas.addElement(new Reserva(idReserva,tickets,dateTickets.getValue(), hourTickets.getValue()));
						    	datos.serializarVectorReservasAJson(reservas);
						    	datos.serializarArrayAJson(clientes);
						    	
						    	confirmation.setHeaderText("Reserva realizada con éxito.");
						    	confirmation.showAndWait();
								
							}
							else {
								error.setHeaderText("La hora seleccionada es inválida.");
								error.setContentText("Revise nuestros horarios de apertura.");
								error.showAndWait();
							}

						}

					}
					// la fecha seleccionada es igual a la fecha actual
					else {
						// comprueba que la hora que se selecciona no es atrasada de la actual

						if (horaSeleccionada.isAfter(horaHoy2)) {
							// comprobar si la hora adelantada a la hora actual, se encuentra dentro del
							// rango de apertura del museo

							if (dateSeleccionada.getDayOfWeek().toString().equals("MONDAY")
									| dateSeleccionada.getDayOfWeek().toString().equals("TUESDAY")
									| dateSeleccionada.getDayOfWeek().toString().equals("WEDNESDAY")
									| dateSeleccionada.getDayOfWeek().toString().equals("THURSDAY")) {

								// Comrpobar con el horario de lunes a jueves
								if (horaSeleccionada.isAfter(LocalTime.of(10, 00)) && horaSeleccionada.isBefore(LocalTime.of(20, 00))) {
									
									for (int i=0; i<clientes.size(); i++) {
							    		if (clientes.get(i).getUsuario().equals(usuario)) {
							    			clientes.get(i).getReservas().add(idReserva);
							    		}
							    	}
									//En el json de reservas se aniade la reserva con su respectiva informacion
							    	reservas.addElement(new Reserva(idReserva,tickets,dateTickets.getValue(), hourTickets.getValue()));
							    	datos.serializarVectorReservasAJson(reservas);
							    	datos.serializarArrayAJson(clientes);
							    	
							    	confirmation.setHeaderText("Reserva realizada con éxito.");
							    	confirmation.showAndWait();
									
								}
								else {
									error.setHeaderText("La hora seleccionada es inválida.");
									error.setContentText("Revise nuestros horarios de apertura.");
									error.showAndWait();
								}
							}
							// Si el dia que ha seleccionado es un viernes o sabado
							else if (dateSeleccionada.getDayOfWeek().toString().equals("FRIDAY")
									| dateSeleccionada.getDayOfWeek().toString().equals("SATURDAY")) {

								// Comprobar con el horario de viernes a sabado
								if (horaSeleccionada.isAfter(LocalTime.of(10, 00)) && horaSeleccionada.isBefore(LocalTime.of(21, 00))) {
									
									for (int i=0; i<clientes.size(); i++) {
							    		if (clientes.get(i).getUsuario().equals(usuario)) {
							    			clientes.get(i).getReservas().add(idReserva);
							    		}
							    	}
									//En el json de reservas se aniade la reserva con su respectiva informacion
							    	reservas.addElement(new Reserva(idReserva,tickets,dateTickets.getValue(), hourTickets.getValue()));
							    	datos.serializarVectorReservasAJson(reservas);
							    	datos.serializarArrayAJson(clientes);
							    	
							    	confirmation.setHeaderText("Reserva realizada con éxito.");
							    	confirmation.showAndWait();
									
								}
								else {
									error.setHeaderText("La hora seleccionada es inválida.");
									error.setContentText("Revise nuestros horarios de apertura.");
									error.showAndWait();
								}

							}
							// Si el dia que ha seleccionado es un domingo
							else {
								// Comprobar con el horario de domingo
								if (horaSeleccionada.isAfter(LocalTime.of(11, 00)) && horaSeleccionada.isBefore(LocalTime.of(19, 00))) {
									
									for (int i=0; i<clientes.size(); i++) {
							    		if (clientes.get(i).getUsuario().equals(usuario)) {
							    			clientes.get(i).getReservas().add(idReserva);
							    		}
							    	}
									//En el json de reservas se aniade la reserva con su respectiva informacion
							    	reservas.addElement(new Reserva(idReserva,tickets,dateTickets.getValue(), hourTickets.getValue()));
							    	datos.serializarVectorReservasAJson(reservas);
							    	datos.serializarArrayAJson(clientes);
							    	
							    	confirmation.setHeaderText("Reserva realizada con éxito.");
							    	confirmation.showAndWait();
									
								}
								else {
									error.setHeaderText("La hora seleccionada es inválida.");
									error.setContentText("Revise nuestros horarios de apertura.");
									error.showAndWait();
								}

							}

						} else {
							error.setHeaderText("La hora seleccionada para reservar es inválida.");
							error.showAndWait();
						}

					}

				} else {
					error.setHeaderText("Número de tickets inválido.");
					error.showAndWait();
				}

			} catch (NumberFormatException ex) {
				error.setHeaderText("Formato del número de tickets incorrecto.");
				error.showAndWait();
			}
		} else {
			error.setHeaderText("Por favor rellene todos los campos.");
			error.showAndWait();
		}
		

	}
    	
    

    @FXML
    /**
     * 
     * Dirige al usuario a la ventana para visualizar su información.
     * 
     * @param event		Evento causado cuando el usuario pulsa sobre la imagen de su avatar.
     */
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
    /**
     * 
     *  Devuelve al usuario a la ventana principal habiendo cerrado su sesión.
     * 
     * @param event		Evento causado cuando el usuario pulsa sobre la imagen para cerrar sesión.
     */
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

}
