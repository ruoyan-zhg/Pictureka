package Controlador;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

import com.jfoenix.controls.JFXTextArea;

import Modelo.Cliente;
import Modelo.Datos;
import Modelo.Informe;
import Modelo.Reserva;
import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControladorEditarReserva {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private ImageView imgVolverAtras;
    
    @FXML
    private TableView<Reserva> tablaReservas;

    @FXML
    private TableColumn<Reserva, Integer> Ident;

    @FXML
    private TableColumn<Reserva, Integer> cantidad;

    @FXML
    private TableColumn<Reserva, LocalDate> fecha;

    @FXML
    private TableColumn<Reserva, LocalTime> hora;

    @FXML
    private JFXTextArea mostrarReservas;

    @FXML
    private Button btnEliminar;
    
    boolean logged;
    
    String usuario;
    
    private String infoReserva;
    
    private Datos datos = new Datos();
    
    private Vector<Reserva> reservas = datos.desserializarJsonAReservas();
    private Vector<Cliente> usuarios = datos.desserializarJsonAusuarios();
    
    private Vector<Integer> identificadores = new Vector<Integer>();
    private Vector<Reserva> reservasEspecificas = new Vector<Reserva>();
    
    public void initialize() {
    	getReserUser();
		refrescarTabla();
	}
    
    public ControladorEditarReserva(String usuario) {
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
    void clickItem(MouseEvent event) {
    	if (!tablaReservas.getSelectionModel().isEmpty()) {
			int posicion = tablaReservas.getSelectionModel().getSelectedIndex();
			mostrarReservas(-posicion+reservasEspecificas.size()-1);
		}
    }

    @FXML
    void eliminarReserva(ActionEvent event) {
    	
    	Alert alerta = new Alert(AlertType.INFORMATION);
    	if(tablaReservas.getSelectionModel().getSelectedItem()!=null) {
    		int idEliminar = tablaReservas.getSelectionModel().getSelectedItem().getIdentificador();
    		for(int i = 0; i<reservasEspecificas.size(); i++) {
        		if(idEliminar==reservasEspecificas.elementAt(i).getIdentificador()) {
        			reservasEspecificas.removeElement(reservasEspecificas.elementAt(i));
        			
        		}
        	}
        	
        	for(int i = 0; i<usuarios.size(); i++) {
        		if(usuarios.elementAt(i).getUsuario().equals(usuario)) {
        			for(int j = 0; j<usuarios.elementAt(i).getReservas().size(); j++) {
        				if(usuarios.elementAt(i).getReservas().elementAt(j)==idEliminar) {
        					usuarios.elementAt(i).getReservas().removeElementAt(j);
        				}
        				
        	    	}
        		}
        	}
        	for(int i = 0; i<identificadores.size(); i++) {
        		if(identificadores.elementAt(i)==idEliminar) {
        			identificadores.remove(identificadores.elementAt(i));
        		}
        	}
        	for(int i = 0; i<reservas.size(); i++) {
        		if(reservas.elementAt(i).getIdentificador()==idEliminar) {
        			reservas.remove(reservas.elementAt(i));
        		}
        	}
        	
        	refrescarTabla();
        	datos.serializarVectorReservasAJson(reservas);
        	datos.serializarArrayAJson(usuarios);
        	alerta.setHeaderText("La reserva se ha eliminado con exito!");
    		alerta.showAndWait();
    	}
    	else {
    		alerta.setHeaderText("Porfavor seleccione alguna reserva para eliminar!");
    		alerta.showAndWait();
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
                controlerPrincipal.getBarra().setStyle("-fx-background-color:  #FF8000");
               
                
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }
    }

    @FXML
    void volverAtras(MouseEvent event) {
    	
    	//Se carga el contenido de la ventana
    	FXMLLoader loaderPrincipal = new FXMLLoader(getClass().getResource("/application/VentanaPerfil.fxml"));
    	//Se le asigna el controlador de la ventana para editar informaciï¿½n de los guardias
        ControladorPerfil controlerPerfil = new ControladorPerfil(usuario);
        loaderPrincipal.setController(controlerPerfil);
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
           

            //Se aï¿½ade el contenido de la ventana cargada en el AnchorPane del padre
	        anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);



		} catch (IOException e1) {
			e1.printStackTrace();
		}

    }
    
    private void mostrarReservas(int posicion) {
    	
    	this.infoReserva = "\t\t\t\tReserva Seleccionada:"
    					+"\nIdentificador:  "+reservasEspecificas.elementAt(posicion).getIdentificador()
		    			+ "\nNumero de tickets:  "+ reservasEspecificas.elementAt(posicion).getNumTickets()
		    			+ "\nFecha:  "+ reservasEspecificas.elementAt(posicion).getFecha()
		    			+ "\nHora: "+reservasEspecificas.elementAt(posicion).getHora();
    			mostrarReservas.setText(infoReserva);
	}
    
    private void getReserUser() {
    	//Almacena los identificadores de las reservas del usuario 
    	for(int i=0; i<usuarios.size();i++) {
    		if(usuarios.elementAt(i).getUsuario().equals(usuario)) {
    			identificadores=usuarios.elementAt(i).getReservas();
    		}
    	}
    	//Cuando las haya almacenado busca en el vector de reservas generales 
    	//las reservas del usuario y las almacena en un vector se reservas especificas
    	for(int i=0; i<reservas.size();i++) {
    		for(int j=0; j<identificadores.size(); j++) {
				if(reservas.elementAt(i).getIdentificador()==identificadores.elementAt(j)) {
					reservasEspecificas.add(reservas.elementAt(i));
				}
			}
    	}
    	
    }

    
    private void refrescarTabla() {
    	
		tablaReservas.getItems().clear();
		//Si la persona tiene reservas, entonces las muestra en la tabla
		if(reservasEspecificas.size() > 0) {
			for (int i=(reservasEspecificas.size()-1); i >= 0; i--) {
				tablaReservas.getItems().add(reservasEspecificas.elementAt(i)); //Y se aniaden a la tabla
			}
			//Obtenemos el las diferentes columnas de la tabla y asociamos cada columna al tipo de dato que queremos guardar
			Ident.setCellValueFactory(new PropertyValueFactory<>("identificador"));
			cantidad.setCellValueFactory(new PropertyValueFactory<>("numTickets"));
			fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
			mostrarReservas(reservasEspecificas.size()-1);
			
		}
	}
    
    
}
