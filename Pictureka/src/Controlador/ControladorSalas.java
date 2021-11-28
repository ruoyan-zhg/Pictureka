package Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ControladorSalas {

    @FXML
    private AnchorPane anchorPaneSala;

    @FXML
    private VBox VBoxSala;

    @FXML
    private JFXToolbar ToolBarSala;

    @FXML
    private ImageView imgGuardia;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private GridPane GridPaneSala;

    @FXML
    private ImageView imgTemperatura;

    @FXML
    private ImageView imgLuz;

    @FXML
    private TextArea textTemperatura;

    @FXML
    private TextArea textLuz;

    @FXML
    private GridPane GridSensorDistancia;

    @FXML
    private ImageView imgDistancia;

    @FXML
    private JFXButton btnA;

    @FXML
    private JFXButton btnB;

    @FXML
    private JFXButton btnC;

    @FXML
    private JFXButton btnD;

    @FXML
    void cerrarSesion(MouseEvent event) {

    }

    @FXML
    void verPerfil(MouseEvent event) {

    }

}
