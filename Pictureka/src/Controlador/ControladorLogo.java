package Controlador;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * En la clase ControladorLogo, manejamos los eventos que ocurran en la vista <b>VentanaLogo</b>.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerán López
 *
 */

public class ControladorLogo {


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Button btnEntrar;

    @FXML
    /**
     * 
     * El usuario pulsa el botón <b>Entrar</b> y se muestra la ventana principal del programa.
     * 
     * @param event    Momento en el que el usuario pulsa el botón.
     */
    void entrarApp(ActionEvent event) {

    	//Se carga el contenido de la ventana principal
        FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
        //Se le asigna el controlador de la ventana principal
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal("vacio");
        loaderApp.setController(controlerPrincipal);
        
        //Sacamos una ventana nueva
        Parent root;
        try {
            root = loaderApp.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //Hacemos que la ventana se muestre maximizada
            stage.setMaximized(true);
            //Medidas minimas de la ventana
	        stage.setMinHeight(600);
	        stage.setMinWidth(700);
            stage.show();
            stage.getIcons().add(new Image("Pictureka2.png"));
            
            
            //Obtenemos la ventanaLogo
            Stage primaryStage = (Stage)btnEntrar.getScene().getWindow();
            //Escondemos la ventanaLogo
            primaryStage.hide();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    	
    }

	public Button getBtnEntrar() {
		return btnEntrar;
	}

	public void setBtnEntrar(Button btnEntrar) {
		this.btnEntrar = btnEntrar;
	}

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}

	public ImageView getImageLogo() {
		return imageLogo;
	}

	public void setImageLogo(ImageView imageLogo) {
		this.imageLogo = imageLogo;
	}

}
