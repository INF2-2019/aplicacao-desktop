package app.biblioteca.telaloginadm;

import app.inicio.MainApp;
import app.utils.ConnectionFactory;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLControllerTelaLoginAdmBiblioteca implements Initializable {
    
    @FXML
    private TextField txtFieldUsuario;
    @FXML
    private PasswordField passwordFieldSenha;
    @FXML
    private Button btnVoltaInicio;
    
    @FXML
    public void executaLoginAdmBiblioteca(ActionEvent event) {
        String usuario = txtFieldUsuario.getText();
        String senha = passwordFieldSenha.getText();
        
        System.out.println("Usuário: "+usuario+". Senha: "+senha+".");
        
        txtFieldUsuario.setText("");
        passwordFieldSenha.setText("");
        
        ConnectionFactory.getBiblioteca();
    }
    
    @FXML
    public void voltaInicio(ActionEvent event) throws Exception {
        Stage stage = (Stage) btnVoltaInicio.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/inicio/FXMLInicio.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setResizable(false);
        stg.setWidth(1280);
        stg.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}