package app.diario.transferencia.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HistoricoAlunoController implements Initializable {

    @FXML
    private Button voltarBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void voltar(ActionEvent event) {
        Stage historico = (Stage) voltarBtn.getScene().getWindow();
        historico.close();
    }
    
}
