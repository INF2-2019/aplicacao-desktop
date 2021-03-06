package app.diario.transferencia.controllers;

import app.diario.transferencia.repository.TransferenciaRepository;
import app.diario.transferencia.utils.Validacao;
import app.diario.telatransicao.MainTelaTransicaoDiario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PedirCPFController implements Initializable {

    @FXML
    private TextField inputCPF;

    @FXML
    private Button transferirBtn;
    
    @FXML
    private Label avisoL;
    @FXML
    private Button voltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }    

    @FXML
    private void transferencia(ActionEvent event) throws IOException, SQLException {
        try{
            if(!inputCPF.getText().isEmpty()){
                if(Validacao.validaCpf(inputCPF.getText())){
            
                    long cpf = Long.parseLong(inputCPF.getText().replaceAll("\\D", ""));
                    String cpfAluno = TransferenciaRepository.consultaNomeAluno(cpf);
            
                    if(cpfAluno.equals("Aluno não encontrado")){
                        avisoL.setText("Aluno não encontrado.");
                        avisoL.getStyleClass().add("aviso");
                        fadeOutAviso();
                
                    } else {
                        Stage modalTransferencia = new Stage();
                        FXMLLoader modalTransferenciaFXMLLoader = new FXMLLoader(getClass().getResource("/app/diario/transferencia/ModalTransferencia.fxml"));
        
                        Parent modalTransferenciaParent = (Parent) modalTransferenciaFXMLLoader.load();
                        modalTransferenciaParent.getStylesheets().add(getClass().getResource("/app/diario/transferencia/EstiloTransferencia.css").toExternalForm());
                        ModalTransferenciaController modalTransferenciaController = modalTransferenciaFXMLLoader.<ModalTransferenciaController>getController(); 
                        modalTransferenciaController.setNomeAluno(cpfAluno);
                        modalTransferenciaController.setCpf(cpf);
            
                        modalTransferencia.setScene(new Scene(modalTransferenciaParent));
                        modalTransferencia.initOwner(((Node) event.getSource()).getScene().getWindow());
                        modalTransferencia.initModality(Modality.APPLICATION_MODAL);
                        modalTransferencia.showAndWait();
                    }
                } else {
                    avisoL.setText("CPF Inválido.");
                    avisoL.getStyleClass().add("aviso");
                    fadeOutAviso();
                }
            } else {
                avisoL.setText("Por favor, insira um CPF.");
                avisoL.getStyleClass().add("aviso");
                fadeOutAviso();
            }
        } catch (SQLException e) {
            avisoL.setText("Não foi possivel consultar o CPF.");
            avisoL.getStyleClass().add("aviso");
            fadeOutAviso();
        }
    }
    
    public void fadeOutAviso() {
        FadeTransition transicao = new FadeTransition(Duration.millis(5000), avisoL);
        transicao.setFromValue(1);
        transicao.setToValue(0);
        transicao.play();
    }

    @FXML
    private void voltafunc(ActionEvent event) {
        MainTelaTransicaoDiario main = new MainTelaTransicaoDiario();
        Stage tabelaDeptos = (Stage) voltar.getScene().getWindow();
        tabelaDeptos.close();
        try {
            main.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PedirCPFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
