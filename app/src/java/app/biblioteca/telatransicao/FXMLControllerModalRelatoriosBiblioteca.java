package app.biblioteca.telatransicao;

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
        Stage stage = (Stage) btnRelatorioMultas.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioReservas(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioReservas.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioAcervo(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioAcervo.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioEmprestimos(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioEmprestimos.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioAtrasos(ActionEvent event) {
        Stage stage = (Stage) btnRelatorioAtrasos.getScene().getWindow();
        stage.close();
        stage = FXMLControllerTelaTransicaoBiblioteca.getStageTelaTransicao();
        stage.close();
    }

    @FXML
    public void entraRelatorioDescartes(ActionEvent event) {
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