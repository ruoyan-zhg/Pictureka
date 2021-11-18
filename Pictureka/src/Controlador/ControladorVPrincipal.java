package Controlador;

import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControladorVPrincipal {

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    private VBox VBoxPrincipal;

    @FXML
    private ImageView imgViewLupa;

    @FXML
    private JFXTextField txtField_busqueda;
    
    @FXML
    private ImageView imgViewSlider;
    
    int count = 0;

    @FXML
    void cambioImg(MouseEvent event) {
    	ArrayList<Image> imagenes = new ArrayList<Image>();
    	
    	imagenes.add(new Image("/MonaLisa.jpg"));
    	imagenes.add(new Image("/Dali.jpg"));
    	imagenes.add(new Image("/Sixtina.jpg"));
    	imagenes.add(new Image("/scream.jpg"));
    	imagenes.add(new Image("/VanGogh.jpg"));
    	
    	imgViewSlider.setImage(imagenes.get(count));
    	count++;
    	System.out.println(count+"\n");
    	
    	if(count==imagenes.size()) {
    		count=0;
    	}
    	
    	
    	
    	
    }

    @FXML
    void tocarLupa(MouseEvent event) {
    	
    }

}
