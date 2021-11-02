package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controlador {

    @FXML
    private TextField FieldMiTexto;

    @FXML
    private Button btnMostrar;

    @FXML
    private Label LabelTexto;

    @FXML
    void mostrarTexto(ActionEvent event) {
    String texto;
    texto = FieldMiTexto.getText();
    LabelTexto.setText("Buenas");
    }

}