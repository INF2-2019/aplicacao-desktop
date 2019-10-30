/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteInsere;

import java.net.URL;
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
    private TextField horasInput;

    @FXML
    private Label modalidadeLabel;

    @FXML
    private TextField modelidadeInput;

    @FXML
    private Label horasLabel;

    @FXML
    private Label nomeLabel;
    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    void acaoSalvar(ActionEvent event) {
        fecha();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fecha(){
        InsereMain.getStage().close();
    }
}
