package app.diario.transferencia.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ModalTransferenciaController implements Initializable {

    @FXML
    private Button confirmarBtn;
    @FXML
    private Button cancelarBtn;
    @FXML
    private Label nomedoalunoLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }    

    @FXML
    private void transferirAluno(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
    public void setNomeAluno(String aluno){
        nomedoalunoLabel.setText(aluno);
    }
    
}
