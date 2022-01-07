package application;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Vector;

import com.jfoenix.transitions.JFXFillTransition;

import Controlador.ControladorLogo;
import Modelo.*;

/**
 * 
 * En la clase <i>Main</i> se inicia el programa.
 * 
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			//Llamamos al codigo hecho en fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/VentanaLogo.fxml"));
			ControladorLogo controlLogo = new ControladorLogo();
			//Creamos 2 tipos de animaciones
			ScaleTransition scale = new ScaleTransition();
			JFXFillTransition fill = new JFXFillTransition();

			
			//Asociamos la vista con el controlador
			loader.setController(controlLogo);
			//Llamar a la funcion load de loadere
			Parent root = loader.load();
			primaryStage.setScene(new Scene(root));
			//Se establece el titulo de la ventana
			primaryStage.setTitle("Pictureka");
			
			//Se establece que la ventana del logo no sea resizable
			primaryStage.setResizable(false);
			
			//Animacion del boton de entrar
			fill.setCycleCount(TranslateTransition.INDEFINITE);
			fill.setDuration(Duration.millis(2500));
			fill.setRegion(controlLogo.getBtnEntrar());
			fill.setFromValue(Color.WHITE);
			fill.setToValue(Color.rgb(210, 167, 244));
			
			//Animacion de la imagen del logo
			scale.setNode(controlLogo.getImageLogo());
			scale.setDuration(Duration.millis(2500));
			scale.setCycleCount(TranslateTransition.INDEFINITE);
			scale.setInterpolator(Interpolator.LINEAR);
			scale.setByX(0.3);
			scale.setByY(0.3);
			scale.setAutoReverse(true);
			
			
			
			//Mostramos las animaciones creadas
			fill.play();
			scale.play();
			
			primaryStage.getIcons().add(new Image("Pictureka2.png"));
			primaryStage.show();
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
