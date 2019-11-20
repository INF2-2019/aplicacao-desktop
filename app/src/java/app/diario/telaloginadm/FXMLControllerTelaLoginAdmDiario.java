package app.diario.telaloginadm;

import app.inicio.MainApp;
import app.utils.ConnectionFactory;
import app.utils.Hasher;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import javafx.scene.control.Label;
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
    @FXML
    private Button btnAdmEntraDiario;
    @FXML
    private Label lblMsgErro;
    
    private Connection con;
    
    @FXML
    public void executaLoginAdmDiario(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String usuario = txtFieldUsuario.getText();
        String senha = passwordFieldSenha.getText();
        
        txtFieldUsuario.setText("");
        passwordFieldSenha.setText("");
        
        con = ConnectionFactory.getDiario();
        if (con == null) {
            lblMsgErro.setText("Falha ao conectar com o banco de dados!");
            lblMsgErro.setVisible(true);
        }
        else {
            try {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM admin WHERE usuario=?");
                stmt.setString(1, usuario);
                ResultSet rs = stmt.executeQuery();
                if(!rs.first()) {
                    lblMsgErro.setText("Usuário não encontrado. Tente novamente!");
                    lblMsgErro.setVisible(true);
                    return;
                }
                String senhaCorreta = rs.getString("senha");
                stmt.close();
                rs.close();
                if (Hasher.validar(senha, senhaCorreta)) {
                    Stage stage = (Stage) btnAdmEntraDiario.getScene().getWindow();
                    stage.close();
                    Parent root = FXMLLoader.load(MainApp.class.getResource("/app/diario/telatransicao/FXMLTelaTransicaoDiario.fxml"));
                    Stage stg = new Stage();
                    Scene scene = new Scene(root);
                    stg.setScene(scene);
                    stg.setResizable(false);
                    stg.setTitle("Diário");
                    stg.setWidth(1280);
                    stg.show();
                }
                else {
                    lblMsgErro.setText("Senha incorreta. Tente novamente!");
                    lblMsgErro.setVisible(true);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLControllerTelaLoginAdmDiario.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        stg.setTitle("Sistema Acadêmico");
        stg.setWidth(1280);
        stg.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}