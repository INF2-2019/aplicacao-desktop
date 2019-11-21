/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.relatorios.rel8e9.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 *
 * @author 
 */
public class ModalCertificadoController implements Initializable {
    @FXML
    private TextField nome;
    @FXML
    private TextField curso;
    @FXML
    private Button botaoFechar;
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    private void fechar(ActionEvent event) {
        Stage stage = (Stage)botaoFechar.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void setNomeAluno(String nome){
        this.nome.setText(nome);
    }
    @FXML
    public void setStage(Stage stage){
        this.stage=stage;
    }
    public Stage getStage(){
        return stage;
    }
}
