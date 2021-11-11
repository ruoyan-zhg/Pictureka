package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import Controlador.ControladorInicioSesion;
import Controlador.ControladorRegistro;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//REGISTRO
			//Llamamos al codigo hecho en fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/VentanaLogo.fxml"));
			ControladorLogo controlLogo = new ControladorLogo();
			//Asociamos la vista con el controlador
			loader.setController(controlLogo);
			//Llamar a la funcion load de loadere
			Parent root = loader.load();
<<<<<<< Updated upstream
			
=======

>>>>>>> Stashed changes
			//INICIO SESION
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/application/InterfazLogin.fxml"));
			ControladorInicioSesion controlInicioSesion = new ControladorInicioSesion();
			loader.setController(controlInicioSesion);
			Parent root2 = loader.load();
<<<<<<< Updated upstream
			
=======

>>>>>>> Stashed changes
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
