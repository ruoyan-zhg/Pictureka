package Controlador;

import java.io.IOException;
import java.util.Vector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToolbar;
import Modelo.Datos;
import Modelo.Guardia;
import Modelo.Staff;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private JFXButton btnGuardarCambios;
       
    
    @FXML
    public void initialize() {
    	
    	Datos datos = new Datos();
    	Vector <Staff> staff = datos.desserializarJsonStaff();
    	
    	//Se leen los datos del Json del staff
    	for (int i=0; i<staff.size(); i++) {
    		//Obtiene solo el personal con numero de identificacion 2
    		if (staff.get(i).getIdentificadorUser()==2) {
    			
    			//Se muestran los guardias obtenidos en la tabla
    			tableView.getItems().add(new Guardia(staff.get(i).getUsuario(), staff.get(i).getDni(), staff.get(i).getEmail(),
    			staff.get(i).getContrasenia(), staff.get(i).getFechaNacimiento(), staff.get(i).getNombre(), 
    			staff.get(i).getApellido1(), staff.get(i).getApellido2()));
    		}
    	}
    		
		//Obtenemos el las diferentes columnas de la tabla y asociamos cada columna al tipo de dato que queremos guardar
    	Usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
    	Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	PrimerApellido.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
    	SegundoApellido.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
    	Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    	DNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	FechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
    	Contrasenia.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));
		
    }
    
    
    @FXML
    void cerrarSesion(MouseEvent event) {
    	
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal("vacio");
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
        ControladorTabAniadirGuardia controlerTabAniadir = new ControladorTabAniadirGuardia(this);
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
        ControladorTabEditarGuardia controlerPrincipal = new ControladorTabEditarGuardia(this);
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
    
    @FXML
    void GuardarTodosCambios(ActionEvent event) {
    	
    	Datos datos = new Datos();
    	Vector <Staff> staff = datos.desserializarJsonStaff();
    	tableView.getItems().clear();
    	//Se leen los datos del Json del staff
    	for (int i=0; i<staff.size(); i++) {
    		//Obtiene solo el personal con numero de identificacion 2
    		if (staff.get(i).getIdentificadorUser()==2) {
    			
    			//Se muestran los guardias obtenidos en la tabla
    			tableView.getItems().add(new Guardia(staff.get(i).getUsuario(), staff.get(i).getDni(), staff.get(i).getEmail(),
    			staff.get(i).getContrasenia(), staff.get(i).getFechaNacimiento(), staff.get(i).getNombre(), 
    			staff.get(i).getApellido1(), staff.get(i).getApellido2()));
    		}
    	}
    		
		//Obtenemos el las diferentes columnas de la tabla y asociamos cada columna al tipo de dato que queremos guardar
    	Usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
    	Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	PrimerApellido.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
    	SegundoApellido.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
    	Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    	DNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	FechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
    	Contrasenia.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));
    	
    	
    }
    
    @FXML
    void CancelarEdiccion(ActionEvent event) {
    	
    	FXMLLoader loaderAdmin = new FXMLLoader(getClass().getResource("/application/VentanaAdministrador.fxml"));
        ControladorAdministrador controlerAdmin = new ControladorAdministrador();
        loaderAdmin.setController(controlerAdmin);
        
        try {
        	AnchorPane PaneAdmin = (AnchorPane) loaderAdmin.load();
        	anchorPaneEditGuardia.getChildren().clear();
            AnchorPane.setTopAnchor(PaneAdmin, 0.0);
            AnchorPane.setRightAnchor(PaneAdmin, 0.0);
            AnchorPane.setLeftAnchor(PaneAdmin, 0.0);
            AnchorPane.setBottomAnchor(PaneAdmin, 0.0);
            anchorPaneEditGuardia.getChildren().setAll(PaneAdmin);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    
    public AnchorPane getAnchorPaneEditGuardia() {
		return anchorPaneEditGuardia;
	}


	public void setAnchorPaneEditGuardia(AnchorPane anchorPaneEditGuardia) {
		this.anchorPaneEditGuardia = anchorPaneEditGuardia;
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
