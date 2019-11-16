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
    private Button btnManutencaoCursos;
    @FXML
    private Button btnManutencaoDepartamentos;
    @FXML
    private Button btnManutencaoDisciplinas;
    @FXML
    private Button btnManutencaoCampi;
    @FXML
    private Button btnMenuRelatorios;
    @FXML
    private Button btnManutencaoAlunos;
    @FXML
    private Button btnManutencaoProfessores;
    @FXML
    private Button btnTransferenciaAluno;
    @FXML
    private Button btnManutencaoEtapas;
    
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
    public void entraManutencaoProfessores(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoProfessores.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoCursos(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoCursos.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoDisciplinas(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoDisciplinas.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoCampi(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoCampi.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraTransferenciaAluno(ActionEvent event) {
        Stage stage = (Stage) btnTransferenciaAluno.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoDepartamentos(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoDepartamentos.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoEtapas(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoEtapas.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoAlunos(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoAlunos.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void abreModalRelatorios(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/diario/telatransicao/FXMLModalRelatoriosDiario.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setResizable(false);
        stg.setTitle("Sistema Acadêmico");
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

    private static void setStageTelaTransicao(Stage stageTelaTransicao) {
        FXMLControllerTelaTransicaoDiario.stageTelaTransicao = stageTelaTransicao;
    }
    
    
}