package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.jfoenix.controls.JFXButton;

import Modelo.Datos;
import Modelo.Guardia;
import Modelo.Registro;
import Modelo.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ControladorTabEditarGuardia {

	ControladorEditGuardias controlerEditGuardia = new ControladorEditGuardias();
	@FXML
    private AnchorPane AnchorTabEditarGuardia;

    @FXML
    private GridPane gridPaneEditarGuardia;

    @FXML
    private Label lblUsuarioGuardia;

    @FXML
    private TextField textUsuarioGuardia;

    @FXML
    private Label lblNombreGuardia;

    @FXML
    private TextField textNombreGuardia;

    @FXML
    private Label lblApellido1Guardia;

    @FXML
    private Label lblApellido2Guardia;

    @FXML
    private TextField textApellido1Guardia;

    @FXML
    private TextField textApellido2Guardia;

    @FXML
    private Label lblEmailGuardia;

    @FXML
    private TextField textEmailGuardia;

    @FXML
    private Label lblDniGuardia;

    @FXML
    private TextField textDniGuardia;

    @FXML
    private Label lblFechaGuardia;

    @FXML
    private Label lblContraseniaGuardia;

    @FXML
    private TextField textContraseniaGuardia;

    @FXML
    private DatePicker DateFechaGuardia;

    @FXML
    private JFXButton btnGuardar;

    
    public ControladorTabEditarGuardia(ControladorEditGuardias controlerEdit) {
    	
    	this.controlerEditGuardia = controlerEdit;
    }
    
    
    @FXML
    public void initialize() {

    	//Se comprueba que el administrador ha seleccionado a un guardia de la tabla
        if (!controlerEditGuardia.getTableView().getSelectionModel().isEmpty()) {
        	
        	//Se recoge la informacion del guardia que el administrador ha seleccionado y se muestra en los diferentes campos
        	textUsuarioGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getUsuario());
        	textNombreGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getNombre());
        	textApellido1Guardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getApellido1());
        	textApellido2Guardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getApellido2());
        	textDniGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getDni());
        	textEmailGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getEmail());
        	DateFechaGuardia.setValue(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getFechaNacimiento());
        	textContraseniaGuardia.setText(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getContrasenia());
    	
        	
        }
        
    }
    
    @FXML
    void GuardarGuardiaEdit(ActionEvent event) {
    	
    	Alert error = new Alert(Alert.AlertType.ERROR);
    	Alert informacion = new Alert(Alert.AlertType.INFORMATION);
    	Registro registro = new Registro();
    	Datos datos = new Datos();
    	Gson gson = new Gson();


    	Vector <Staff> staff = datos.desserializarJsonStaff();
    	int i = 0;
    	
    	String UsuarioNuevo;
		String nombreNuevo;
		String apellido1Nuevo;
		String apellido2Nuevo;
		String dniNuevo;
		String emailNuevo;
		LocalDate fechaNuevo;
		String contraseniaNuevo;
		
		UsuarioNuevo = textUsuarioGuardia.getText();
		nombreNuevo = textNombreGuardia.getText();
		apellido1Nuevo = textApellido1Guardia.getText();
		apellido2Nuevo = textApellido2Guardia.getText();
		dniNuevo = textDniGuardia.getText();
		emailNuevo = textEmailGuardia.getText();
		fechaNuevo = DateFechaGuardia.getValue();
		contraseniaNuevo = textContraseniaGuardia.getText();
		

		if (!UsuarioNuevo.isEmpty() || !nombreNuevo.isEmpty() || !apellido1Nuevo.isEmpty() || !apellido2Nuevo.isEmpty() || !dniNuevo.isEmpty()
				|| !emailNuevo.isEmpty() || !(fechaNuevo==null) || !contraseniaNuevo.isEmpty()) {
			
			registro.recuperarStaff();
			registro.recuperarUsuarios();
			if (registro.validarEmail(emailNuevo) && registro.emailRepetido(emailNuevo) && registro.emailRepetidoStaff(emailNuevo) && registro.staffRepetido(UsuarioNuevo)) {
		    	LocalDate fecha = LocalDate.now();
		    	Period periodo = Period.between(fechaNuevo, fecha);
		    	
		    	if (periodo.getYears() > 18 && periodo.getYears() < 100) {
		    		
					
					
					for (i=0; i<staff.size(); i++) {
						
						
						if (staff.get(i).getUsuario().equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getUsuario())
								&& staff.get(i).getNombre().equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getNombre())
								&& staff.get(i).getApellido1().equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getApellido1())
								&& staff.get(i).getApellido2().equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getApellido2())
								&& staff.get(i).getEmail().equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getEmail())
								&& staff.get(i).getDni().equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getDni())
								&& staff.get(i).getFechaNacimiento().equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getFechaNacimiento())
								&& staff.get(i).getContrasenia().equals(controlerEditGuardia.getTableView().getSelectionModel().getSelectedItem().getContrasenia())) {
	
							staff.get(i).setUsuario(UsuarioNuevo);
							staff.get(i).setNombre(nombreNuevo);
							staff.get(i).setApellido1(apellido1Nuevo);
							staff.get(i).setApellido2(apellido2Nuevo);
							staff.get(i).setEmail(emailNuevo);
							staff.get(i).setDni(dniNuevo);
							staff.get(i).setFechaNacimiento(fechaNuevo);
							staff.get(i).setContrasenia(contraseniaNuevo);
							
							registro.escribirStaffNuevo(staff);
							
							//TODO Actualizar los datos de inmediato
							informacion.setHeaderText("Los datos se actualizarán para la próxima entrada");
							informacion.showAndWait();
							
							/*
							FXMLLoader loaderAdmin = new FXMLLoader(getClass().getResource("/application/VentanaAdministrador.fxml"));
					        ControladorEditGuardias controlerAdmin = new ControladorEditGuardias();
					        loaderAdmin.setController(controlerAdmin);
					        
					        try {
					        	AnchorPane registerPane = (AnchorPane) loaderAdmin.load();
					        	controlerAdmin.getAnchorEditGuardia().getChildren().clear();
					            AnchorPane.setTopAnchor(registerPane, 0.0);
					            AnchorPane.setRightAnchor(registerPane, 0.0);
					            AnchorPane.setLeftAnchor(registerPane, 0.0);
					            AnchorPane.setBottomAnchor(registerPane, 0.0);
					            controlerAdmin.getAnchorEditGuardia().getChildren().setAll(registerPane);
					            
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							*/
							
							
							
							/*
							controlerEditGuardia.getTableView().getItems().clear();
							
							for (int j=0; j<staff.size();j++) {
								
								if (staff.get(j).getIdentificadorUser()==2) {
									// Se muestran los guardias obtenidos en la tabla
									controlerEditGuardia.getTableView().getItems().add(new Guardia(staff.get(j).getUsuario(), staff.get(j).getDni(),
									staff.get(j).getEmail(), staff.get(j).getContrasenia(),
									staff.get(j).getFechaNacimiento(), staff.get(j).getNombre(),
									staff.get(j).getApellido1(), staff.get(j).getApellido2()));
									
								}
							}
							*/
							
					    
							
						}
						
					}

		    		
		    		
		    		
		    	}

			}
			else {
				error.setHeaderText("Revise la información introducida en los campos.");
				error.showAndWait();
			}
			
		}
		else {
			error.setHeaderText("No se ha seleccionado ningún guardia a modificar.");
			error.showAndWait();
		}
    	
    }


	
	
	 public AnchorPane getAnchorTabEditarGuardia() {
			return AnchorTabEditarGuardia;
		}

		public void setAnchorTabEditarGuardia(AnchorPane anchorTabEditarGuardia) {
			AnchorTabEditarGuardia = anchorTabEditarGuardia;
		}

		public TextField getTextUsuarioGuardia() {
			return textUsuarioGuardia;
		}

		public JFXButton getBtnGuardar() {
			return btnGuardar;
		}

		public void setBtnGuardar(JFXButton btnGuardar) {
			this.btnGuardar = btnGuardar;
		}

		public void setTextUsuarioGuardia(TextField textUsuarioGuardia) {
			this.textUsuarioGuardia = textUsuarioGuardia;
		}

		public TextField getTextNombreGuardia() {
			return textNombreGuardia;
		}

		public void setTextNombreGuardia(TextField textNombreGuardia) {
			this.textNombreGuardia = textNombreGuardia;
		}

		public TextField getTextApellido1Guardia() {
			return textApellido1Guardia;
		}

		public void setTextApellido1Guardia(TextField textApellido1Guardia) {
			this.textApellido1Guardia = textApellido1Guardia;
		}

	    public DatePicker getDateFechaGuardia() {
			return DateFechaGuardia;
		}

		public void setDateFechaGuardia(DatePicker dateFechaGuardia) {
			DateFechaGuardia = dateFechaGuardia;
		}
		
		public TextField getTextApellido2Guardia() {
			return textApellido2Guardia;
		}

		public void setTextApellido2Guardia(TextField textApellido2Guardia) {
			this.textApellido2Guardia = textApellido2Guardia;
		}

		public TextField getTextEmailGuardia() {
			return textEmailGuardia;
		}

		public void setTextEmailGuardia(TextField textEmailGuardia) {
			this.textEmailGuardia = textEmailGuardia;
		}

		public TextField getTextDniGuardia() {
			return textDniGuardia;
		}

		public void setTextDniGuardia(TextField textDniGuardia) {
			this.textDniGuardia = textDniGuardia;
		}


		public TextField getTextContraseniaGuardia() {
			return textContraseniaGuardia;
		}

		public void setTextContraseniaGuardia(TextField textContraseniaGuardia) {
			this.textContraseniaGuardia = textContraseniaGuardia;
		}

}
