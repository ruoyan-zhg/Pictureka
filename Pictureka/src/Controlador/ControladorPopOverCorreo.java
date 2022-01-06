package Controlador;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Modelo.Cliente;
import Modelo.modelo_Museo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ControladorPopOverCorreo {

    @FXML
    private AnchorPane anchorPopOver;

    @FXML
    private AnchorPane anchorScrollPopOver;

    @FXML
    private JFXTextField textTituloCorreo;

    @FXML
    private JFXTextArea textAreaCorreo;

    @FXML
    private JFXButton btnEnviarCorreo;

    private String usuario;		//esta el usuario o mail del usuario que tiene la sesion iniciada
	
    boolean logged; //Este nos dira si la parsona esta logueada o no
    
	 
    
	 public ControladorPopOverCorreo(String usuario) {
		 if (usuario == "vacio") {
			 logged = false;
			 this.usuario = usuario;
		 }
		 else {
			 this.usuario = usuario;
			 logged = true;
		 }
		 
	}
    
    
    @FXML
    void EnviarEmail(ActionEvent event) {
    	
    	modelo_Museo modelo = new modelo_Museo();
    	Cliente cliente = modelo.devolverCliente(usuario);
    	
    	String tituloEmail = textTituloCorreo.getText();
    	String cuerpoEmail = textAreaCorreo.getText();
    	
    	
    	
    	String to = "picturekasfw@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = cliente.getEmail();

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        
     // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("picturekasfw@gmail.com", "abajoCrYpt");

            }

        });
        
        
     // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(cliente.getUsuario() + "(" + cliente.getDni()+"): " + tituloEmail);

            // Now set the actual message
            message.setText(cuerpoEmail);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        
        
    	
    }

    
    
    
    
	public AnchorPane getAnchorPopOver() {
		return anchorPopOver;
	}

	public void setAnchorPopOver(AnchorPane anchorPopOver) {
		this.anchorPopOver = anchorPopOver;
	}

	public AnchorPane getAnchorScrollPopOver() {
		return anchorScrollPopOver;
	}

	public void setAnchorScrollPopOver(AnchorPane anchorScrollPopOver) {
		this.anchorScrollPopOver = anchorScrollPopOver;
	}

	public JFXTextField getTextTituloCorreo() {
		return textTituloCorreo;
	}

	public void setTextTituloCorreo(JFXTextField textTituloCorreo) {
		this.textTituloCorreo = textTituloCorreo;
	}

	public JFXTextArea getTextAreaCorreo() {
		return textAreaCorreo;
	}

	public void setTextAreaCorreo(JFXTextArea textAreaCorreo) {
		this.textAreaCorreo = textAreaCorreo;
	}

	public JFXButton getBtnEnviarCorreo() {
		return btnEnviarCorreo;
	}

	public void setBtnEnviarCorreo(JFXButton btnEnviarCorreo) {
		this.btnEnviarCorreo = btnEnviarCorreo;
	}
    

}

