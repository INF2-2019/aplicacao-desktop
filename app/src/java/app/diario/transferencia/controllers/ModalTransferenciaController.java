package app.diario.transferencia.controllers;

import app.diario.transferencia.repository.TransferenciaRepository;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ModalTransferenciaController implements Initializable {

    private long cpf;
    
    @FXML
    private Button confirmarBtn;
    
    @FXML
    private Button cancelarBtn;
    
    @FXML
    private Label nomedoalunoLabel;
    
    @FXML
    private Label avisoL;

    public void setNomeAluno(String aluno){
        nomedoalunoLabel.setText(aluno);
    }  
    
    public void setCpf(long cpf){
        this.cpf = cpf;
    }
    
    @FXML
    private void transferirAluno(ActionEvent event) throws IOException { 
        try{
            TransferenciaRepository.mudaStatusMatricula(cpf);
            
            Stage janelaHistorico = new Stage();
            FXMLLoader modalTransferenciaFXMLLoader = new FXMLLoader(getClass().getResource("/app/diario/transferencia/HistoricoAluno.fxml"));
        
            Parent historicoAlunoParent = (Parent) modalTransferenciaFXMLLoader.load();
            HistoricoAlunoController historicoAlunoController = modalTransferenciaFXMLLoader.<HistoricoAlunoController>getController();
            
            janelaHistorico.setScene(new Scene(historicoAlunoParent));
            janelaHistorico.initOwner(((Node) event.getSource()).getScene().getWindow());
            janelaHistorico.initModality(Modality.APPLICATION_MODAL);
            janelaHistorico.showAndWait();
        
            Stage modal = (Stage) cancelarBtn.getScene().getWindow();
            modal.close();
        } catch (SQLException e){
            avisoL.setText("Não foi possível transferir o aluno.");
            avisoL.getStyleClass().add("aviso");
            fadeOutAviso();
        }
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        modal.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void fadeOutAviso() {
        FadeTransition transicao = new FadeTransition(Duration.millis(5000), avisoL);
        transicao.setFromValue(1);
        transicao.setToValue(0);
        transicao.play();
    }
}
