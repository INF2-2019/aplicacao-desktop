package app.biblioteca.telatransicao;

import app.biblioteca.relatorios.relAcervo.TransicaoMain;
import app.biblioteca.relatorios.relAtrasos.RelAtrasosMain;
import app.biblioteca.relatorios.relDescartes.RelDescartesMain;
import app.biblioteca.relatorios.relEmprestimos.RelEmprestimosMain;
import app.biblioteca.relatorios.relMultas.RelMultasMain;
import app.biblioteca.relatorios.relReservas.RelReservasMain;
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

public class FXMLControllerModalRelatoriosBiblioteca implements Initializable {
    
    @FXML
    private Button btnFechaModal;
    @FXML
    private Button btnRelatorioAtrasos;
    @FXML
    private Button btnRelatorioDescartes;
    @FXML
    private Button btnRelatorioEmprestimos;
    @FXML
    private Button btnRelatorioMultas;
    @FXML
    private Button btnRelatorioReservas;
    @FXML
    private Button btnRelatorioAcervo;
    
    @FXML
    public void entraRelatorioMultas(ActionEvent event) {
        RelMultasMain relatorioMultas = new RelMultasMain();
        try {
            relatorioMultas.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioMultas.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioReservas(ActionEvent event) {
        RelReservasMain relatorioReservas = new RelReservasMain();
        try {
            relatorioReservas.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioReservas.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioAcervo(ActionEvent event) {
        TransicaoMain telaTransicaoAcervo = new TransicaoMain();
        try {
            telaTransicaoAcervo.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioAcervo.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioEmprestimos(ActionEvent event) {
        RelEmprestimosMain relatorioEmprestimos = new RelEmprestimosMain();
        try {
            relatorioEmprestimos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioEmprestimos.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioAtrasos(ActionEvent event) {
        RelAtrasosMain relatorioAtrasos = new RelAtrasosMain();
        try {
            relatorioAtrasos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioAtrasos.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioDescartes(ActionEvent event) {
        RelDescartesMain relatorioDescartes = new RelDescartesMain();
        try {
            relatorioDescartes.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerModalRelatoriosBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnRelatorioDescartes.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
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