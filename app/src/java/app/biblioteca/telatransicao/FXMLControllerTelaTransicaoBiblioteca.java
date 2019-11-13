package app.biblioteca.telatransicao;

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

public class FXMLControllerTelaTransicaoBiblioteca implements Initializable {
    
    @FXML
    private Button btnVoltaInicio;
    
    @FXML
    public void voltaInicio(ActionEvent event) throws Exception {
        Stage stage = (Stage) btnVoltaInicio.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/inicio/FXMLInicio.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setResizable(false);
        stg.setWidth(1280);
        stg.show();
    }
    
    @FXML
    void entraManutencaoDescartes(ActionEvent event) {

    }

    @FXML
    void entraManutencaoReservas(ActionEvent event) {

    }

    @FXML
    void entraManutencaoAcervos(ActionEvent event) {

    }

    @FXML
    void entraManutencaoEmprestimos(ActionEvent event) {

    }

    @FXML
    void abreModalRelatorios(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/biblioteca/telatransicao/FXMLModalRelatoriosBiblioteca.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setResizable(false);
        stg.setWidth(740);
        stg.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}