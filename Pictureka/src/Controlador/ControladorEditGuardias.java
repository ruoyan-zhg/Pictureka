package Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import org.controlsfx.control.MasterDetailPane;

public class ControladorEditGuardias {

    @FXML
    private AnchorPane anchorPaneEditGuardia;

    @FXML
    private VBox VBoxEditGuardia;

    @FXML
    private JFXToolbar ToolBarAdmin;

    @FXML
    private ImageView imgAdministrador;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private GridPane gridPaneMaster;

    @FXML
    private MasterDetailPane MasterPaneGuardias;

    @FXML
    private GridPane gridPaneEdit;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnGuardar;



	@FXML
    void cerrarSesion(MouseEvent event) {
    	
    	FXMLLoader loaderApp = new FXMLLoader(getClass().getResource("/application/VentanaPrincipal.fxml"));
        ControladorVPrincipal controlerPrincipal = new ControladorVPrincipal();
        loaderApp.setController(controlerPrincipal);
        
        try {
        	AnchorPane registerPane = (AnchorPane) loaderApp.load();
        	anchorPaneEditGuardia.getChildren().clear();
            AnchorPane.setTopAnchor(registerPane, 0.0);
            AnchorPane.setRightAnchor(registerPane, 0.0);
            AnchorPane.setLeftAnchor(registerPane, 0.0);
            AnchorPane.setBottomAnchor(registerPane, 0.0);
            anchorPaneEditGuardia.getChildren().setAll(registerPane);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void verPerfil(MouseEvent event) {
    	
    	
    }
    
    
    public MasterDetailPane getMasterPaneGuardias() {
		return MasterPaneGuardias;
	}

	public void setMasterPaneGuardias(MasterDetailPane masterPaneGuardias) {
		MasterPaneGuardias = masterPaneGuardias;
	}
    

}
