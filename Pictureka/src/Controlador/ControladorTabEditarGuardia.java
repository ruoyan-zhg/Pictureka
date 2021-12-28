package Controlador;

import java.time.LocalDate;
import java.util.Date;

import com.jfoenix.controls.JFXButton;

import Modelo.Guardia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private DatePicker DateFechaGuardia;

    @FXML
    private Label lblContraseniaGuardia;

    @FXML
    private TextField textContraseniaGuardia;

    @FXML
    private JFXButton btnGuardarGuardia;

    
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
    void guardarGuardia(ActionEvent event) {
    	
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
		fechaNuevo = LocalDate.of(DateFechaGuardia.getValue().getYear(), DateFechaGuardia.getValue().getMonthValue(), DateFechaGuardia.getValue().getDayOfMonth());
		contraseniaNuevo = textContraseniaGuardia.getText();
    	
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

		public JFXButton getBtnGuardarGuardia() {
			return btnGuardarGuardia;
		}

		public void setBtnGuardarGuardia(JFXButton btnGuardarGuardia) {
			this.btnGuardarGuardia = btnGuardarGuardia;
		}

}
