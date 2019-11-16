/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteInsere;

import Principal.DbConnector;
import Principal.TableController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
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
    private Label idDeptoLabel;

    @FXML
    private TextField idInput;

    @FXML
    private Label idLabel;

    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField idDeptoInput;

    @FXML
    private TextField seapInput;

    @FXML
    private Label titulacaoLabel;

    @FXML
    private TextField titulacaoInput;

    @FXML
    private Label seapLabel;

    @FXML
    private Label nomeLabel;
    
    @FXML
    private Label senhaLabel;
    
    @FXML
    private Label emailLabel;
    
    @FXML
    private TextField emailInput;
    
    @FXML
    private TextField senhaInput;
    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    void acaoSalvar(ActionEvent event) throws SQLException {
        Connection con = new DbConnector().getConnection();

            // cria um preparedStatement
            String sql = "insert into professores" +
                    " (`id`,`id-depto`, `nome`, `titulacao`, `email`, `senha`)" +
                    " values (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            // preenche os valores
            stmt.setString(2,idDeptoInput.getText());
            stmt.setString(3,nomeInput.getText());
            stmt.setString(1,seapInput.getText());
            stmt.setString(4,titulacaoInput.getText());
            stmt.setString(5,emailInput.getText());
            stmt.setString(6,senhaInput.getText());
            stmt.execute();
            stmt.close();
            con.close();
        fecha();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fecha(){
        InsereMain.getStage().close();
    }
    
    @FXML
    private void Insere(javafx.event.ActionEvent event){
        
    }
}