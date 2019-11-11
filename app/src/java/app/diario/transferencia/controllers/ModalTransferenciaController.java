/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.transferencia.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author caios
 */
public class ModalTransferenciaController implements Initializable {

    @FXML
    private Button confirmarBtn;
    @FXML
    private Button cancelarBtn;
    @FXML
    private Label nomedoalunoLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void transferirAluno(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
}
