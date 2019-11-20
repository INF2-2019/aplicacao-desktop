package app.diario.telatransicao;

import app.diario.relatorios.rel8e9.main.CertificadoPrincipal;
import app.diario.relatorios.rel8e9.main.RelatoriosMain;
import app.diario.relatorios.relatorio10.Relatorio10ModalMain;
import app.diario.relatorios.relatorio11.Relatorio11ModalMain;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void entraRelatorioProfessores(ActionEvent event) {
        RelatoriosMain relatorioProfessores = new RelatoriosMain();
        try {
            relatorioProfessores.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioProfessores.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoDiario.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioCertificados(ActionEvent event) {
        CertificadoPrincipal relatorioCertificados = new CertificadoPrincipal();
        try {
            relatorioCertificados.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioCertificados.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoDiario.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioAlunos(ActionEvent event) {
        Relatorio10ModalMain relatorioAlunos = new Relatorio10ModalMain();
        try {
            relatorioAlunos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioAlunos.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoDiario.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioConteudos(ActionEvent event) {
        Relatorio11ModalMain relatorioConteudos = new Relatorio11ModalMain();
        try {
            relatorioConteudos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
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