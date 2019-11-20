package app.diario.telatransicao;

import app.diario.campi.Principal.MainCampi;
import app.diario.alunos.Principal.MainAlunos;
import app.diario.cursos.CursosMain;
import app.diario.departamentos.main.MainDepartamentos;
import app.diario.diario.main.MainDiario;
import app.diario.disciplinas.principal.MainDisciplinas;
import app.diario.etapas.Principal.MainEtapas;
import app.diario.matriculas.principal.MainMatriculas;
import app.diario.professores.principal.ProfessorMain;
import app.diario.transferencia.main.MainTranferencia;
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
    @FXML
    private Button btnManutencaoMatriculas;
    @FXML
    private Button btnConteudosAtividades;
    
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
        ProfessorMain manutencaoProfessores = new ProfessorMain();
        try {
            manutencaoProfessores.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoProfessores.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoCursos(ActionEvent event) {
        CursosMain manutencaoCursos = new CursosMain();
        try {
            manutencaoCursos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoCursos.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoDisciplinas(ActionEvent event) {
        MainDisciplinas manutencaoDisciplinas = new MainDisciplinas();
        try {
            manutencaoDisciplinas.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoDisciplinas.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoCampi(ActionEvent event) {
        MainCampi manutencaoCampi = new MainCampi();
        try {
            manutencaoCampi.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoCampi.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraTransferenciaAluno(ActionEvent event) {
        MainTranferencia transferenciaAluno = new MainTranferencia();
        try {
            transferenciaAluno.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnTransferenciaAluno.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoDepartamentos(ActionEvent event) {
        MainDepartamentos manutencaoDepartamentos = new MainDepartamentos();
        try {
            manutencaoDepartamentos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoDepartamentos.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoEtapas(ActionEvent event) {
        MainEtapas manutencaoEtapas = new MainEtapas();
        try {
            manutencaoEtapas.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoEtapas.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoAlunos(ActionEvent event) {
        MainAlunos manutencaoAlunos = new MainAlunos();
        try {
            manutencaoAlunos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoAlunos.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void entraManutencaoMatriculas(ActionEvent event) {
        MainMatriculas manutencaoMatriculas = new MainMatriculas();
        try {
            manutencaoMatriculas.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) btnManutencaoAlunos.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void entraConteudosAtividades(ActionEvent event) {
        MainDiario conteudosAtividades = new MainDiario();
        try {
            conteudosAtividades.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLControllerTelaTransicaoDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private static void setStageTelaTransicao(Stage stageTelaTransicao) {
        FXMLControllerTelaTransicaoDiario.stageTelaTransicao = stageTelaTransicao;
    }
    
    
}