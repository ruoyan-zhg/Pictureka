package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Llamamos al codigo hecho en fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/holaVista.fxml"));
			Controlador control = new Controlador();
			//Asociamos la vista con el controlador
			loader.setController(control);
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
