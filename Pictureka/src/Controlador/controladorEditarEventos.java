package Controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToolbar;
import Modelo.Datos;
import Modelo.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;

/**
 * 
 * En esta clase se maneja los cambios que se realizan a la hora de editar un evento, en la vista <b>VentanaEditarEventos</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */

public class controladorEditarEventos {

	@FXML
	private AnchorPane anchorPanePrincipal;

	@FXML
	private BorderPane borderPaneEditEventos;

	@FXML
	private JFXToolbar toolBarAdministrador;

	@FXML
	private ImageView btnVolverAtras;

	@FXML
	private ImageView imgAvatarAdmin;

	@FXML
	private ComboBox<String> comboBoxElegirEvento;

	@FXML
    private TextArea txtAreaInfo;


	@FXML
	private Region imgAniadirImagen;

	@FXML
	private Button btnAniadirCambios;

	@FXML
	private TextField txtFieldTitulo;

	private String usuario; // esta el usuario o mail del usuario que tiene la sesion iniciada

	boolean logged; // Este nos dira si la parsona esta logueada o no
	    
	/**
	 * 
	 *  Constructor de la clase <b>controladorEditarEventos</b>, que guarda la información de un adminsitrador.
	 *  
	 * @param usuario	El administrador que se encuentre iniciado sesión.
	 */
	public controladorEditarEventos(String usuario) {
		if (usuario == "vacio") {
			logged = false;
			this.usuario = usuario;
		} else {
			this.usuario = usuario;
			logged = true;
		}

	}
	    
	    
	    
	private Path pathImagen;

	private Path pathSRC; // Creamos un objeto Path para guardar el path del src

	Datos handler = new Datos(); // Instanciamos un objeto para meter el Json de eventos en un vector

	private Vector<Evento> eventos = new Vector<Evento>(); // creamos el vector de eventos
	
	/**
	 * 
	 * Inicializa la ventana de ediatar eventos con su respectiva informaci�n.
	 * 
	 */
	public void initialize() {
		eventos = handler.desserializarJsonAEventos();// Ingresamos los datos del Json al vector de eventos
		String[] nombres = asignarNombreAEventos(); 	//recorre los eveentos y devuelve el nombre de cada posicion de los eventos
		ObservableList<String> list = FXCollections.observableArrayList("1 - " + nombres[0], "2 - " + nombres[1], "3 - " + nombres[2], "4 - " + nombres[3]);
		comboBoxElegirEvento.setItems(list);
		//Image image = new Image("file:" + "/C:/Users/jolie/OneDrive/Documentos/GitHub/PR_INF_21-22-pictureka/Pictureka/Imagenes_Multimedia/anadir-imagen.png");
		//imgAniadirImagen.setBackground(
				//new Background(new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY)));
		

		
	}

	



	String imagen; // Almacena el nombre (ubicacion de la imagen) que se añadir al evento

	@FXML
	/**
	 * 
	 * Dirige al administrador a la ventana perfil para visualizar su informacion.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre la imagen de su avatar.
	 */
	void accederPerfilAdmin(MouseEvent event) {
		if (logged == false) {
			Alert error = new Alert(Alert.AlertType.ERROR);
			error.setHeaderText("Oh no! Para acceder a esta función debes estar iniciado sesión.");
			error.showAndWait();

		} else {
			// Se carga el contenido de la ventana
			FXMLLoader loaderPrincipala = new FXMLLoader(getClass().getResource("/application/VentanaPerfil.fxml"));
			// Se le asigna el controlador de la ventana para editar informacion de los
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

				// Se añade el contenido de la ventana cargada en el AnchorPane del padre
				anchorPanePrincipal.getChildren().setAll(PaneVentanaPrincipal);
				// Cambia el color de la barra de la ventana perfil
				controlerPrincipal.getBarra().setStyle("-fx-background-color:  #FF8000");

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	@FXML
	/**
	 * 
	 * A�ade los cambios realizados por el administrador a la ventana de eventos.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre el botón "Añadir Cambios".
	 */
	void aniadirCambios(MouseEvent event) {
		Alert error = new Alert(Alert.AlertType.ERROR);
		Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
		
		//Se comrpueba que la seleccion del comboBox no sea nula
		if (comboBoxElegirEvento.getValue() != null) {
			if (txtAreaInfo.getLength() >= 10 && txtAreaInfo.getText() != null) {
				if (imagen != null) {
					if (txtFieldTitulo.getLength() >= 10 && txtFieldTitulo.getText() != null) {

						// Si todo sale bien la imagen se guarda en el src
						try {
							Files.copy(pathImagen, pathSRC);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// Se guradan los datos en el vector de eventos 
						guardarDatos();
						// y se actualiza el Json con los datos actualizados del vector
						handler.serializarVectorEventosAJson(eventos);
						aviso.setTitle("Exito.");
						aviso.setHeaderText("Evento cambiado exitosamente.");
						aviso.showAndWait();
						abrirAdmin();
					} else {
						error.setTitle("Datos incompletos.");
						error.setHeaderText("Porfavor ingrese un titulo adecuado para el evento.");
						error.show();
					}

				} else {
					error.setTitle("Datos incompletos.");
					error.setHeaderText("Porfavor ingrese una Imagen para añadir al evento.");
					error.show();
				}

			} else {
				error.setTitle("Datos incompletos.");
				error.setHeaderText("Porfavor ingrese la informacion correspondiente al evento.");
				error.show();
			}

		} else {
			error.setTitle("Datos incompletos.");
			error.setHeaderText("Porfavor ingrese el evento que se reemplazará.");
			error.show();
		}

	}

	@FXML
	/**
	 * 
	 * Abre el explorador de archivos del administrador, para poder elegir la imagen que desea añadir a la ventana de eventos.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre la región.
	 */
	void aniadirImagen(MouseEvent event) {
		
		//Abre el explorador de archivos
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");

		// Agregar filtros para facilitar la busqueda
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		// Obtener la imagen seleccionada
		File imgFile = fileChooser.showOpenDialog(null);

		// Mostar la imagen
		if (imgFile != null) {
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			imgAniadirImagen.setBackground(
					new Background(new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY)));
			// imagen = imgFile.getName();
			// para guardar el path de la imagen seleccionada
			pathImagen = imgFile.toPath();
			// Se almacena el path al que ira dirigido la imagen con el nombre que ya tenia
			// la imagen seleccionada
			String directoryName = System.getProperty("user.dir");
			String pathConcatenar = "\\Imagenes_Multimedia\\";
			pathSRC = Paths.get(directoryName + pathConcatenar + imgFile.getName());
			imagen = imgFile.getName();

		}

	}

	@FXML
	/**
	 * 
	 * Devuelve al administrador a su ventana inciial.
	 * 
	 * @param event		Evento causado cuando el administrador pulsa sobre la imagen de volver atrás.
	 */
	void regresarPrincipalAdmin(MouseEvent event) {
		abrirAdmin();

	}

	/**
	 * 
	 * Muestra la ventana inicial del administrador.
	 * 
	 */
	void abrirAdmin() {
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
	
	private String[] asignarNombreAEventos() {
		String[] nombres = new String[4];
		for (int i = 0; i < eventos.size() ;i++) {
			nombres[eventos.elementAt(i).getIdentificador()-1] = eventos.elementAt(i).getNombre();		//recupera el nombre de cada una de las posiciones de los eventos
		}
		for (int i = 0; i < eventos.size() ;i++) {
			if (nombres[i].equals(null)) {		//en caso de que una no exista, le asigna vacio
				nombres[i] = "Vacio";
			}
		}
		return nombres;
	}

	public void guardarDatos() {// Este metodo guardara los cambios en el Json de eventos con la informacion que
								// haya solicitado
		String[] datosSeleccion = comboBoxElegirEvento.getValue().split(" - ");	//recupera la seleccion
		int seleccion = Integer.parseInt(datosSeleccion[0]);		//asigna la parte de la seleccion que tiene el numero y omite el nombre
		int contador = 0;
		boolean encontrado = false;
		while (contador < eventos.size() && encontrado != true) {	//recorre hasta encontrar el que se quiere reemplazar
			if(eventos.elementAt(contador).getIdentificador() == seleccion){	//si coincide el identificador, se procede a cambiarlo
				encontrado = true;
				eventos.elementAt(contador).setNombre(txtFieldTitulo.getText());
				eventos.elementAt(contador).setImagen(imagen);
				eventos.elementAt(contador).setInformacion(txtAreaInfo.getText());
			}
			contador++;
		}
		if(encontrado = false) {
			eventos.add(new Evento(seleccion, txtFieldTitulo.getText(), imagen, txtAreaInfo.getText()));	//si no habia evento con ese identificador, se procede a crearlo
		}
		/*if (seleccion == 1) {
			eventos.elementAt(0).setNombre(txtFieldTitulo.getText());
			eventos.elementAt(0).setImagen(imagen);
			eventos.elementAt(0).setInformacion(txtAreaInfo.getText());

		} else if (seleccion == 2) {
			eventos.elementAt(1).setNombre(txtFieldTitulo.getText());
			eventos.elementAt(1).setImagen(imagen);
			eventos.elementAt(1).setInformacion(txtAreaInfo.getText());
		} else if (seleccion == 3) {
			eventos.elementAt(2).setNombre(txtFieldTitulo.getText());
			eventos.elementAt(2).setImagen(imagen);
			eventos.elementAt(2).setInformacion(txtAreaInfo.getText());
		} else {
			eventos.elementAt(3).setNombre(txtFieldTitulo.getText());
			eventos.elementAt(3).setImagen(imagen);
			eventos.elementAt(3).setInformacion(txtAreaInfo.getText());
		}*/
		

	}

}
