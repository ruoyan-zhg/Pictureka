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
    
    @FXML
    private ImageView imgViewSlider2;

    @FXML
    private ImageView imgViewSlider3;
    
    int count = 0;

    @FXML
    void cambioImg(MouseEvent event) {
    	//System.out.println(count+"\n");
    	
    	ArrayList<Image> imagenes = new ArrayList<Image>();
    	
    	imagenes.add(new Image("/MonaLisa.jpg"));
    	imagenes.add(new Image("/Dali.jpg"));
    	imagenes.add(new Image("/Sixtina.jpg"));
    	imagenes.add(new Image("/scream.jpg"));
    	imagenes.add(new Image("/VanGogh.jpg"));
    	imagenes.add(new Image("/people.jpg"));
    	
    	
    	
    	imgViewSlider.setImage(imgViewSlider2.getImage());
    	imgViewSlider2.setImage(imgViewSlider3.getImage());
    	imgViewSlider3.setImage(imagenes.get(count));
    	
    	count++;
    	if(count>=imagenes.size()) {
    		count=0;
    	}
    	
    	//System.out.println(count+"\n");
    
    	
    }

    @FXML
    void tocarLupa(MouseEvent event) {
    	
    }

}
