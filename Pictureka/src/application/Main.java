package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Controlador.ControladorLogo;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			//Llamamos al codigo hecho en fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/VentanaLogo.fxml"));
			ControladorLogo controlLogo = new ControladorLogo();
			//Asociamos la vista con el controlador
			loader.setController(controlLogo);
			//Llamar a la funcion load de loadere
			Parent root = loader.load();
			primaryStage.setScene(new Scene(root));
			//Se establece el titulo de la ventana
			primaryStage.setTitle("Pictureka");
			//Se establece que la ventana del logo no sea resizable
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
