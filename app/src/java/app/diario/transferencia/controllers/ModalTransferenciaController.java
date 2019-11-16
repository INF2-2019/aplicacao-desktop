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
            int transferido = TransferenciaRepository.consultaStatusMatricula(cpf);
            
            switch(transferido){
                case 0:
                    avisoL.setText("Aluno já transferido.");
                    avisoL.getStyleClass().clear();
                    avisoL.getStyleClass().add("aviso");
                    fadeOutAviso();
                    break;
                case 1:
                    TransferenciaRepository.mudaStatusMatricula(cpf);
                    avisoL.setText("Aluno transferido com sucesso.");
                    avisoL.getStyleClass().clear();
                    avisoL.getStyleClass().add("sucesso");
                    fadeOutAviso();
                    break;
                case 2:
                    avisoL.setText("Aluno não encontrado");
                    avisoL.getStyleClass().clear();
                    avisoL.getStyleClass().add("aviso");
                    fadeOutAviso();
                    break;
            }
            
            TransferenciaRepository.mudaStatusMatricula(cpf);
        } catch (SQLException e){
            avisoL.setText("Não foi possível transferir o aluno.");
            avisoL.getStyleClass().add("aviso");
            System.out.println(e);
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
