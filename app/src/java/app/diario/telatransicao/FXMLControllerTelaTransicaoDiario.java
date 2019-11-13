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

public class FXMLControllerTelaTransicaoDiario implements Initializable {
    
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
    void entraManutencaoProfessores(ActionEvent event) {

    }

    @FXML
    void entraManutencaoCursos(ActionEvent event) {

    }

    @FXML
    void entraManutencaoDisciplinas(ActionEvent event) {

    }

    @FXML
    void entraManutencaoCampi(ActionEvent event) {

    }

    @FXML
    void entraTransferenciaAluno(ActionEvent event) {

    }

    @FXML
    void entraManutencaoDepartamentos(ActionEvent event) {

    }

    @FXML
    void entraManutencaoEtapas(ActionEvent event) {

    }

    @FXML
    void entraManutencaoAlunos(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}