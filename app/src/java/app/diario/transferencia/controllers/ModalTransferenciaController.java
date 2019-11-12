package app.diario.transferencia.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class ModalTransferenciaController implements Initializable {

    @FXML
    private Button confirmarBtn;
    @FXML
    private Button cancelarBtn;
    @FXML
    private Label nomedoalunoLabel;
    @FXML
    private Label nomedoalunoLabel1;

    public void setNomeAluno(String aluno){
        nomedoalunoLabel.setText(aluno);
    }  

    @FXML
    private void transferirAluno(ActionEvent event) throws IOException {
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
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        modal.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
