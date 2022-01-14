package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import Modelo.Informe;
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

/**
 * 
 * En esta clase se maneja la lectura y escritura de informes de parte del
 * admiistrador, en la vista <b>VentanaInformeAdmin</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */
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

	private String usuario; // esta el usuario o mail del usuario que tiene la sesion iniciada

	boolean logged; // Este nos dira si la parsona esta logueada o no

	private String informe;

	private Vector<Informe> informes;

	/**
	 * 
	 * Constructor de la clase <b>ControladorInformeAdmin</b> que guarda la
	 * informaci�n del administrador.
	 * 
	 * @param usuario El administrador que est� iniciado sesi�n.
	 */
	public ControladorInformeAdmin(String usuario) {
		if (usuario == "vacio") {
			logged = false;
			this.usuario = usuario;
		} else {
			this.usuario = usuario;
			logged = true;
		}

	}
	
	/**
	 * 
	 * Inicializa la ventana con un m�todo que refresca el contenido de la tabla.
	 * 
	 */
	public void initialize() {
		refrescarTabla();
	}

	@FXML
	/**
	 * 
	 * Obtiene la posici�n del elemento que se ha seleccionado en la tabla de informes.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre la tabla.
	 */
	public void clickItem(MouseEvent event) {
		//Comrpueba que la seleccion del usaurio no sea vacia
		if (!tablaInformes.getSelectionModel().isEmpty()) {
			int posicion = tablaInformes.getSelectionModel().getSelectedIndex();
			mostrarInforme(-posicion + informes.size() - 1);
		}

	}

	@FXML
	/**
	 * 
	 * Muestra la informaci�n del administrador que se encuentre iniciado sesi�n.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre la imagen de su avatar.
	 */
	void accederPerfil(MouseEvent event) {
		if (logged == false) {
			Alert error = new Alert(Alert.AlertType.ERROR);
			error.setHeaderText("Oh no! Para acceder a esta función debes estar iniciado sesión.");
			error.showAndWait();

		} else {
			// Se carga el contenido de la ventana
			FXMLLoader loaderPrincipala = new FXMLLoader(getClass().getResource("/application/VentanaPerfil.fxml"));
			// Se le asigna el controlador de la ventana para editar informaci�n de los
			// guardias
			ControladorPerfil controlerPrincipal = new ControladorPerfil(usuario);
			loaderPrincipala.setController(controlerPrincipal);
			AnchorPane PaneVentanaPrincipal;

			try {
				// Se carga en un AnchorPane la ventana
				PaneVentanaPrincipal = (AnchorPane) loaderPrincipala.load();

				// Se elimina el contenido de la ventana padre
				anchorPanePrincipal.getChildren().clear();

				// Se ajusta el AnchorPane para que sea escalable
				AnchorPane.setTopAnchor(PaneVentanaPrincipal, 0.0);
				AnchorPane.setRightAnchor(PaneVentanaPrincipal, 0.0);
				AnchorPane.setLeftAnchor(PaneVentanaPrincipal, 0.0);
				AnchorPane.setBottomAnchor(PaneVentanaPrincipal, 0.0);

				// Se a�ade el contenido de la ventana cargada en el AnchorPane del padre
				anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
				controlerPrincipal.getBarra().setStyle("-fx-background-color:  #FF8000");

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	@FXML
	/**
	 * 
	 * Dirige al administrador a su ventana inicial.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre la imagen de volver atr�s.
	 */
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
	/**
	 * 
	 * Envia el informe escrito por el administrador, con su respectivo t�tulo y cuerpo.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre la imagen para enviar el informe.
	 */
	void enviarInforme(MouseEvent event) {
		Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
		Alert error = new Alert(Alert.AlertType.ERROR);
		//Se comprueba que ninguno de los dos campos se encuentren vacios
		if (!(tituloInforme.getText().isEmpty() | cuerpoInforme.getText().isEmpty())) {
			//Se comprueba que el cuerpo del email tenga un minimo de 20 caracteres
			if (cuerpoInforme.getText().matches("^.{20,}")) {
				modelo_Museo museo = new modelo_Museo();
				String nombre = museo.getRegistro().rDevolverNombreStaff(usuario);
				try {
					//Se escribe el nuevo informe y se refresca la tabla
					museo.getRegistro().escribirInforme(nombre, tituloInforme.getText(), cuerpoInforme.getText());
					refrescarTabla();
					confirmacion.setHeaderText("Informe guardado con éxito");
					confirmacion.show();
				} catch (FileNotFoundException e) {
					error.setHeaderText("¡Archivo no encontrado!");
					error.show();
				}

			} else {
				error.setHeaderText("Porfavor el cuerpo del informe debe ser mayor a 20 caracteres");
				error.show();
			}
		} else {
			error.setHeaderText("Porfavor rellene los campos del informe!");
			error.show();
		}
	}

	/**
	 * 
	 * Refresca la informaci�n de la tabla, mostrando el contenido del Json.
	 * 
	 */
	private void refrescarTabla() {
		modelo_Museo museo = new modelo_Museo();
		Vector<Informe> _informes = museo.getRegistro().devolverInforme();
		//Elimina el contenido de la tabla
		tablaInformes.getItems().clear();
		if (_informes.size() > 0) {
			this.informes = _informes;
			//Rellena la tabla con la informacion del Json de Informes
			for (int i = (informes.size() - 1); i >= 0; i--) {
				// Se muestran los informes obtenidos en la tabla
				tablaInformes.getItems().add(informes.elementAt(i));
			}
			// Obtenemos el las diferentes columnas de la tabla y asociamos cada columna al
			// tipo de dato que queremos guardar
			Autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
			Titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
			Cuerpo.setCellValueFactory(new PropertyValueFactory<>("cuerpo"));
			Fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			mostrarInforme(0);

		}
	}
	
	/**
	 * 
	 * Muestra la informaci�n del informe seleccionado en la tabla.
	 * 
	 * @param posicion
	 */
	private void mostrarInforme(int posicion) {
		this.informe = "Autor:  " + informes.elementAt(posicion).getAutor() + "\n" + "Fecha:  "
				+ informes.elementAt(posicion).getFecha() + "\n\n" + "Titulo:  "
				+ informes.elementAt(posicion).getTitulo() + "\n\n" + informes.elementAt(posicion).getCuerpo();
		mostrarInforme.setText(informe);
	}

}
