package app.diario.telatransicao;

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
    private Button btnRelatorioProfessores;
    @FXML
    private Button btnRelatorioAlunos;
    @FXML
    private Button btnRelatorioCertificados;
    @FXML
    private Button btnRelatorioConteudos;
    @FXML
    private Button btnRelatorioHistoricosEscolares;
    
    @FXML
    public void entraRelatorioProfessores(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioProfessores.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoDiario.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioCertificados(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioCertificados.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoDiario.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioHistoricosEscolares(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioHistoricosEscolares.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoDiario.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioAlunos(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioAlunos.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoDiario.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioConteudos(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioConteudos.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoDiario.getStageTelaTransicao();
        stage.close();
    }
    
    @FXML
    public void fechaModal(ActionEvent event) {
        Stage stage = (Stage) btnFechaModal.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}