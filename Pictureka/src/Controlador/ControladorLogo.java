package Controlador;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorLogo {


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Button btnEntrar;

    @FXML
    void entrarApp(ActionEvent event) {

    	//Se carga el contenido de la ventana principal
        FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal();
        loaderApp.setController(controlerPrincipal);
        
        
        Parent root;
        try {
            root = loaderApp.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
	        stage.setMinHeight(900);
	        stage.setMinWidth(950);
            stage.show();
            
            
            //Obtenemos la ventanaLogo
            Stage primaryStage = (Stage)btnEntrar.getScene().getWindow();
            //Escondemos la ventana
            primaryStage.hide();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    	
    }

}
