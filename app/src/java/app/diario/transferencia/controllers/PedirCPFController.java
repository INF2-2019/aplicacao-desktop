/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.transferencia.controllers;

import app.diario.transferencia.utils.Validacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author artur
 */
public class PedirCPFController implements Initializable {

    @FXML
    private TextField inputCPF;

    @FXML
    private Button transferirBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void transferencia(ActionEvent event) {
        if(Validacao.validaCpf(inputCPF.getText())){
            System.out.println("a");
        }
    }
    
}
