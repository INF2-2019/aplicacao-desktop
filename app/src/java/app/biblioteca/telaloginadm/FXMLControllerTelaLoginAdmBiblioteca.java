package app.biblioteca.telaloginadm;

import app.diario.telaloginadm.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLControllerTelaLoginAdmBiblioteca implements Initializable {
    
    @FXML
    private TextField txtFieldUsuario;
    @FXML
    private PasswordField passwordFieldSenha;
    
    @FXML
    public void executaLoginAdmDiario(ActionEvent event) {
        String usuario = txtFieldUsuario.getText();
        String senha = passwordFieldSenha.getText();
        
        System.out.println("Usuário: "+usuario+". Senha: "+senha+".");
        
        txtFieldUsuario.setText("");
        passwordFieldSenha.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}