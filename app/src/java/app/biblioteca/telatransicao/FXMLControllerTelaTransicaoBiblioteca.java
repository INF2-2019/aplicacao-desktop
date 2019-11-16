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
    private Button btnManutencaoDescartes;
    @FXML
    private Button btnManutencaoAcervos;
    @FXML
    private Button btnMenuRelatorios;
    @FXML
    private Button btnManutencaoEmprestimos;
    @FXML
    private Button btnManutencaoReservas;
    
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
        Stage stage = (Stage) btnManutencaoDescartes.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoReservas(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoReservas.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoAcervos(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoAcervos.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void entraManutencaoEmprestimos(ActionEvent event) {
        Stage stage = (Stage) btnManutencaoEmprestimos.getScene().getWindow();
        stage.close();
    }

    @FXML
    void abreModalRelatorios(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/biblioteca/telatransicao/FXMLModalRelatoriosBiblioteca.fxml"));
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

    public static void setStageTelaTransicao(Stage stageTelaTransicao) {
        FXMLControllerTelaTransicaoBiblioteca.stageTelaTransicao = stageTelaTransicao;
    }
    
    
}