package app.diario.telatransicao;

import app.inicio.MainApp;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLControllerModalRelatoriosDiario implements Initializable {
    
    @FXML
    private Button btnFechaModal;
    
    @FXML
    public void fechaModal(ActionEvent event) {
        Stage stage = (Stage) btnFechaModal.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}