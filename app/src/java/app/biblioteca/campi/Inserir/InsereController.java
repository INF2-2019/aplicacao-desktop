/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.campi.Inserir;

import app.biblioteca.campi.Principal.DbConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author tuba1
 */
public class InsereController implements Initializable {
    
      @FXML
    private TextField nomeInput;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Label AnoLabel1;

    @FXML
    private Label AnoLabel10;

    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField ufInput;

    @FXML
    private TextField cityInput;

    @FXML
    private Label AnoLabel8;

    
    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    void acaoSalvar(ActionEvent event) throws SQLException {
        Connection con = new DbConnector().getConnection();
// cria um preparedStatement
        String sql = "insert into campi"
                + " (nome,cidade,uf)"
                + " values (?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        System.out.println((Button) event.getSource());
        // preenche os valores
       
        stmt.setString(1,(nomeInput.getText()));
        stmt.setString(2,(cityInput.getText()));
        stmt.setString(3,ufInput.getText());
        

        
         
        stmt.execute();
        stmt.close();
        con.close();
        fecha();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
    public void fecha(){
        InsereMain.getStage().close();
    }
}
