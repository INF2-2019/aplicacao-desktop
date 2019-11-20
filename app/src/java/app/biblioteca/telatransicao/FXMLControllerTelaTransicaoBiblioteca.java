package app.biblioteca.telatransicao;

import app.biblioteca.acervo.principal.ListagemMain;
import app.biblioteca.alunos.Principal.MainAlunos;
import app.biblioteca.campi.Principal.MainCampi;
import app.biblioteca.descartes.main.MainDescartes;
import app.biblioteca.emprestimos.MainEmprestimos;
import app.biblioteca.reservas.MainReservas;
import app.inicio.MainApp;
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

public class FXMLControllerTelaTransicaoBiblioteca implements Initializable {
    
    @FXML
    private Button btnVoltaInicio;
    @FXML
    private Button btnManutencaoDescartes;
    @FXML
    private Button btnManutencaoAcervos;
    @FXML
    private Button btnMenuRelatorios;
    @FXML
    private Button btnManutencaoEmprestimos;
    @FXML
    private Button btnManutencaoReservas;
    @FXML
    private Button btnManutencaoAlunos;
    @FXML
    private Button btnManutencaoCampi;
    
    private static Stage stageTelaTransicao;
    
    @FXML
    public void voltaInicio(ActionEvent event) throws Exception {
        Stage stage = (Stage) btnVoltaInicio.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/inicio/FXMLInicio.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setResizable(false);
        stg.setTitle("Sistema Acadêmico");
        stg.setWidth(1280);
        stg.show();
    }
    
    @FXML
    public void entraManutencaoDescartes(ActionEvent event) {
        MainDescartes manutencaoDescartes = new MainDescartes();
        try {
            manutencaoDescartes.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoDescartes.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoReservas(ActionEvent event) {
        MainReservas manutencaoReservas = new MainReservas();
        try {
            manutencaoReservas.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoReservas.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoAcervos(ActionEvent event) {
        ListagemMain manutencaoAcervo = new ListagemMain();
        try {
            manutencaoAcervo.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoAcervos.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoEmprestimos(ActionEvent event) {
        MainEmprestimos manutencaoEmprestimos = new MainEmprestimos();
        try {
            manutencaoEmprestimos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoEmprestimos.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void entraManutencaoAlunos(ActionEvent event) {
        MainAlunos manutencaoAlunos = new MainAlunos();
        try {
            manutencaoAlunos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoAlunos.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void entraManutencaoCampi(ActionEvent event) {
        MainCampi manutencaoCampi = new MainCampi();
        try {
            manutencaoCampi.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoCampi.getScene().getWindow();
        stage.close();
    }

    @FXML
    void abreModalRelatorios(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/biblioteca/telatransicao/FXMLModalRelatoriosBiblioteca.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setResizable(false);
        stg.setTitle("Relatórios");
        stg.setWidth(740);
        setStageTelaTransicao((Stage) btnMenuRelatorios.getScene().getWindow());
        stg.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public static Stage getStageTelaTransicao() {
        return stageTelaTransicao;
    }

    public static void setStageTelaTransicao(Stage stageTelaTransicao) {
        FXMLControllerTelaTransicaoBiblioteca.stageTelaTransicao = stageTelaTransicao;
    }
    
    
}