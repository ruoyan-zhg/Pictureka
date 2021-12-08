package Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ControladorTickets {

    @FXML
    private AnchorPane anchorPaneTickets;

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
    
    @FXML
    void CancelarReserva(ActionEvent event) {

    }

    @FXML
    void ReservarTickets(ActionEvent event) {

    }

}

