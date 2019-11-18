/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteInfo;

import PacoteInsere.InsereMain;
import Principal.TableController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nikpl
 */
public class FXMLInfoController implements Initializable {

    @FXML
    private Label labelId;

    @FXML
    private Label labelDepartamento;

    @FXML
    private Label labelTitulacao;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelNome;
    
    
    @FXML
    private Button botaoFechar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
