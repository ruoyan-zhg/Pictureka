package application;
package controlador;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Llamamos al codigo hecho en fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaRegistro.fxml"));
			ControladorRegistro controlRegister = new ControladorRegistro();
			//Asociamos la vista con el controlador
			loader.setController(controlRegister);
			//Llamar a la funcion load de loadere
			Parent root = loader.load();
			
			
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
