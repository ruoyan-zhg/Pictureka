package Modelo;

import java.beans.EventHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import Controlador.ControladorSalas;
import Controlador.ControladorVPrincipal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;






public class Alerta {

	private String usuario;

	boolean logged; //Este nos dira si la parsona esta logueada o no

	private String tipoStaff;
	 
	static final String USER = "pri_Pictureka";

	static final String PASS = "asas";
	
	Stage stage;

	Connection conn = null;
	
	String sql;

	Statement stmt = null;
	
	Timer timer_alert = new Timer(true);
	
	
	public Alerta(String usuario, String tipoStaff) {
		
		this.usuario = usuario;
		this.tipoStaff = tipoStaff;
			
	}
	
	private void abrirSensores(Sala sala) {
		
		 FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaSala.fxml"));
	        //Se le asigna el controlador de la ventana principal
	        ControladorSalas controlerPrincipal = new ControladorSalas(usuario, sala, tipoStaff);
	        loaderApp.setController(controlerPrincipal);
	        
	        //Sacamos una ventana nueva
	        Parent root;
	        try {
	            root = loaderApp.load();
	            //Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            //Hacemos que la ventana se muestre maximizada
	            stage.setMaximized(true);
	            //Medidas minimas de la ventana
		        stage.setMinHeight(600);
		        stage.setMinWidth(700);
	            stage.show();
	            stage.getIcons().add(new Image("Pictureka2.png"));
	            
	} catch (IOException e) {
     e.printStackTrace();
 }
		
		
		
	}
	
	
	private void checkSala(Sensor sensor) {
		
		Sala salaSensor = new Sala(sensor.getID_Sala());
		
		if (sensor.getTipo().equals("Luz")) {
			if (sensor.getLectura() < 512) {
				abrirSensores(salaSensor);
			}
		}
		else if (sensor.getTipo().equals("Temperatura")) {
			if (sensor.getLectura() < 14 || sensor.getLectura() > 26) {
				abrirSensores(salaSensor);
			}
			
		}
		else if (sensor.getTipo().equals("Distancia")) {
			if (sensor.getLectura() < 30) {
				abrirSensores(salaSensor);
			}
		}
		
		else {
			
			System.out.println("ERROR");
		}
		
	}
		
		 
	
	
	private void sensoresBD() {
		
		Sala sala = new Sala(0);
		
		boolean sala1 = false;
		boolean sala2 = false;
		boolean sala3 = false;
		boolean sala4 = false;
		
		
		try {
            //STEP 1: Register JDBC driver
        	Class.forName("org.mariadb.jdbc.Driver");

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://195.235.211.197/priPictureka", USER, PASS);
            
            //Se realiza la consulta en la tabla de CLIENTE
            sql = "SELECT * FROM SENSORES WHERE SENSORES.ID_Sala = "+sala+" ;";
            stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( sql );
			while ( rs.next() ) {
				String tipo = rs.getString("tipo");
				int ID_Sala = rs.getInt("ID_Sala");
				int Posicion = rs.getInt("Posicion");
				float lectura = rs.getFloat("lectura");
				Timestamp Fecha = rs.getTimestamp("Fecha");
				
				
				Sensor sensor = new Sensor(tipo, ID_Sala, Posicion, lectura, Fecha);
				
				sala.setIdentificador(ID_Sala);
				
				
				switch (ID_Sala) {
				
				case 1:
					if (!stage.isShowing()) {
						checkSala(sensor);
						sala1 = true;
					}
					break;
				case 2:
					if (!stage.isShowing()) {
						checkSala(sensor);
						sala1 = true;
					}
					break;
				case 3:
					if (!stage.isShowing()) {
						checkSala(sensor);
						sala1 = true;
					}
					break;
				case 4:
					if (!stage.isShowing()) {
						checkSala(sensor);
						sala1 = true;
					}
					break;
				default:
				
				
				}
				
				//sensores.add(new Sensor(tipo, ID_Sala, Posicion, lectura, Fecha));
			}
			 rs.close();
			stmt.close();
			
			conn.close(); 
			
			}
			 catch (Exception e) {
		            //Handle errors for Class.forName
		            e.printStackTrace();
		        } finally {
		            //finally block used to close resources
		            try {
		                if (stmt != null) {
		                    conn.close();
		                }
		            } catch (SQLException se) {
		            }// do nothing
		            try {
		                if (conn != null) {
		                    conn.close();
		                }
		            } catch (SQLException se) {
		                se.printStackTrace();
		            }//end finally try
		        }//end try
			   
	}
	
	public void getDatos() {
		timer_alert.scheduleAtFixedRate(new Task(1), 0 ,  4000);
	}
	
	
	class Task extends TimerTask {

		int sala = 0;
		
    	public Task(int sala) {
    		this.sala = sala;
    	}
		
		
		public void run() {
			sensoresBD();


		}
	}
	
	
}
