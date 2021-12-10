package Controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToolbar;

import Modelo.Guardia;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ControladorEditGuardias {

    @FXML
    private AnchorPane anchorPaneEditGuardia;

    @FXML
    private VBox VBoxEditGuardia;

	@FXML
    private JFXToolbar ToolBarAdmin;

    @FXML
    private ImageView imgAdministrador;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private GridPane gridPaneMaster;

    @FXML
    private TableView<Guardia> tableView;

    @FXML
    private TableColumn<Guardia, String> Usuario;

    @FXML
    private TableColumn<Guardia, String> Nombre;

    @FXML
    private TableColumn<Guardia, String> PrimerApellido;

    @FXML
    private TableColumn<Guardia, String> SegundoApellido;

    @FXML
    private TableColumn<Guardia, String> Email;

    @FXML
    private TableColumn<Guardia, String> DNI;

    @FXML
    private TableColumn<Guardia, String> FechaNacimiento;

    @FXML
    private TableColumn<Guardia, String> Contrasenia;

    @FXML
    private JFXTabPane TabPaneGuardia;

    @FXML
    private Tab TabAniadirGuardia;

    @FXML
    private AnchorPane AnchorTabAniadir;

    @FXML
    private Tab TabEditGuardia;

    @FXML
    private AnchorPane AnchorEditGuardia;

    @FXML
    private GridPane gridPaneEdit;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    void cerrarSesion(MouseEvent event) {
    	
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal();
        loaderApp.setController(controlerPrincipal);
        
        try {
        	AnchorPane registerPane = (AnchorPane) loaderApp.load();
        	anchorPaneEditGuardia.getChildren().clear();
            AnchorPane.setTopAnchor(registerPane, 0.0);
            AnchorPane.setRightAnchor(registerPane, 0.0);
            AnchorPane.setLeftAnchor(registerPane, 0.0);
            AnchorPane.setBottomAnchor(registerPane, 0.0);
            anchorPaneEditGuardia.getChildren().setAll(registerPane);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void AniadirGuardia(Event event) {
    	
		//Se carga la segunda ventana del TabPane
        FXMLLoader loaderTabAniadir = new FXMLLoader(getClass().getResource("/application/TabAniadirGuardia.fxml"));
    	//Se le asigna el controlador de la ventana para editar información de los guardias
        //TODO Pasarle a este  controlador el controlador de edit guardias para que no saque un null pointer al cargar el nuevo guardia en la tabla 
        ControladorTabAniadirGuardia controlerTabAniadir = new ControladorTabAniadirGuardia();
        loaderTabAniadir.setController(controlerTabAniadir);
        AnchorPane anchorTabAniadir;
        
        try {
			anchorTabAniadir = (AnchorPane) loaderTabAniadir.load();
			AnchorTabAniadir.getChildren().clear();
            AnchorPane.setBottomAnchor(anchorTabAniadir, 0.0);
            AnchorPane.setRightAnchor(anchorTabAniadir, 0.0);
            AnchorPane.setLeftAnchor(anchorTabAniadir, 0.0);
            AnchorPane.setBottomAnchor(anchorTabAniadir, 0.0);
            AnchorTabAniadir.getChildren().setAll(anchorTabAniadir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	@FXML
    void EditarGuardia(Event event) {
		
		FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/TabEditarGuardia.fxml"));
        ControladorTabEditarGuardia controlerPrincipal = new ControladorTabEditarGuardia();
        loaderApp.setController(controlerPrincipal);
        
        try {
        	AnchorPane registerPane = (AnchorPane) loaderApp.load();
        	AnchorEditGuardia.getChildren().clear();
            AnchorPane.setTopAnchor(registerPane, 0.0);
            AnchorPane.setRightAnchor(registerPane, 0.0);
            AnchorPane.setLeftAnchor(registerPane, 0.0);
            AnchorPane.setBottomAnchor(registerPane, 0.0);
            AnchorEditGuardia.getChildren().setAll(registerPane);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }


    @FXML
    void verPerfil(MouseEvent event) {

    }

    
    
    
	public TableView<Guardia> getTableView() {
		return tableView;
	}

	public void setTableView(TableView<Guardia> tableView) {
		this.tableView = tableView;
	}

	public TableColumn<Guardia, String> getUsuario() {
		return Usuario;
	}

	public void setUsuario(TableColumn<Guardia, String> usuario) {
		Usuario = usuario;
	}

	public TableColumn<Guardia, String> getNombre() {
		return Nombre;
	}

	public void setNombre(TableColumn<Guardia, String> nombre) {
		Nombre = nombre;
	}

	public TableColumn<Guardia, String> getPrimerApellido() {
		return PrimerApellido;
	}

	public void setPrimerApellido(TableColumn<Guardia, String> primerApellido) {
		PrimerApellido = primerApellido;
	}

	public TableColumn<Guardia, String> getSegundoApellido() {
		return SegundoApellido;
	}

	public void setSegundoApellido(TableColumn<Guardia, String> segundoApellido) {
		SegundoApellido = segundoApellido;
	}

	public TableColumn<Guardia, String> getEmail() {
		return Email;
	}

	public void setEmail(TableColumn<Guardia, String> email) {
		Email = email;
	}

	public TableColumn<Guardia, String> getDNI() {
		return DNI;
	}

	public void setDNI(TableColumn<Guardia, String> dNI) {
		DNI = dNI;
	}

	public TableColumn<Guardia, String> getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(TableColumn<Guardia, String> fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public TableColumn<Guardia, String> getContrasenia() {
		return Contrasenia;
	}

	public void setContrasenia(TableColumn<Guardia, String> contrasenia) {
		Contrasenia = contrasenia;
	}

	public Tab getTabAniadirGuardia() {
		return TabAniadirGuardia;
	}

	public void setTabAniadirGuardia(Tab tabAniadirGuardia) {
		TabAniadirGuardia = tabAniadirGuardia;
	}

	public Tab getTabEditGuardia() {
		return TabEditGuardia;
	}

	public void setTabEditGuardia(Tab tabEditGuardia) {
		TabEditGuardia = tabEditGuardia;
	}
	

    public AnchorPane getAnchorTabAniadir() {
		return AnchorTabAniadir;
	}

	public void setAnchorTabAniadir(AnchorPane anchorTabAniadir) {
		AnchorTabAniadir = anchorTabAniadir;
	}

	public AnchorPane getAnchorEditGuardia() {
		return AnchorEditGuardia;
	}

	public void setAnchorEditGuardia(AnchorPane anchorEditGuardia) {
		AnchorEditGuardia = anchorEditGuardia;
	}
    
    
    


}
