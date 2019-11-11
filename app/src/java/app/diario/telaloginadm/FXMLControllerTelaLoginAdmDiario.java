package app.diario.telaloginadm;

import app.inicio.MainApp;
import app.utils.ConnectionFactory;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLControllerTelaLoginAdmDiario implements Initializable {
    
    @FXML
    private TextField txtFieldUsuario;
    @FXML
    private PasswordField passwordFieldSenha;
    @FXML
    private Button btnVoltaInicio;
    
    private Connection con;
    
    @FXML
    public void executaLoginAdmDiario(ActionEvent event) {
        String usuario = txtFieldUsuario.getText();
        String senha = passwordFieldSenha.getText();
        
        System.out.println("Usuário: "+usuario+". Senha: "+senha+".");
        
        txtFieldUsuario.setText("");
        passwordFieldSenha.setText("");
        
        con = ConnectionFactory.getDiario();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT usuario, senha FROM admin WHERE usuario=?, senha=?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Erro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLControllerTelaLoginAdmDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
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