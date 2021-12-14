package Controlador;

import java.awt.event.MouseEvent;
import java.io.IOException;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ControladorEventos {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private BorderPane BordPanePrincipal;

    @FXML
    private ToolBar ToolBar;

    @FXML
    private ImageView btnContacto;

    @FXML
    private ImageView btnCorreo;

    @FXML
    private ImageView btnMensaje;

    @FXML
    private ButtonBar ButtonBarPrincipal;

    @FXML
    private ImageView imgViewLupa;

    @FXML
    private JFXTextField txtField_busqueda;

    @FXML
    private ImageView imgCalendar;

    @FXML
    private ImageView imgTickets;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private GridPane GridPaneCuadros;

    @FXML
    private GridPane GridPaneObras;

    @FXML
    private ImageView imgViewSlider;

    @FXML
    private ImageView imgViewSlider3;

    @FXML
    private ImageView imgViewSlider2;

    @FXML
    private ImageView imgView_BtnFlecha1;

    @FXML
    private ImageView imgView_BtnFlecha;

    @FXML
    private GridPane GridPaneObras2;

    @FXML
    private ImageView imgViewQuitasol;

    @FXML
    private JFXTextArea TextAreaQuitasol;

    @FXML
    private ImageView imgViewMeninas;

    @FXML
    private JFXTextArea TextAreaMeninas;

    @FXML
    void cambioImg(MouseEvent event) {

    }

    @FXML
    void cambioImgAtras(MouseEvent event) {

    }

    @FXML
    void mandarCorreo(MouseEvent event) {

    }

    @FXML
    void mandarMensaje(MouseEvent event) {

    }

    @FXML
    void mostrarContacto(MouseEvent event) {

    }

    @FXML
    void reservarTicket(MouseEvent event) {

    }

    @FXML
    void tocarLupa(MouseEvent event) {

    }

    @FXML
    void verEventos(ActionEvent event) {
    	
    	//Llamamos al codigo hecho en fxml
		FXMLLoader loaderCalendar = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
		ControladorVPrincipal controlVPrincipal = new ControladorVPrincipal();
		//Asociamos la vista con el controlador
		loaderCalendar.setController(controlVPrincipal);
		
		
    	
    
    }
}



