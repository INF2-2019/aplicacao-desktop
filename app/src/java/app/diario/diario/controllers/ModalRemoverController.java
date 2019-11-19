package app.diario.diario.controllers;


import app.diario.diario.model.ConteudosModel;
import app.diario.diario.repository.ConteudosRepository;
import app.diario.diario.views.ExcecaoPadrao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ModalRemoverController {

    private int id;
    private boolean status;

   
    @FXML
    private Button apagarBtn;

    @FXML
    private Label aviso;

   

    @FXML
    private Button cancelarBtn;

   @FXML
    void apagar(ActionEvent event) throws SQLException, ExcecaoPadrao {
        System.out.println("Entrou2");
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        ConteudosRepository.remover(this.id);
        status = true;
        modal.close();
        
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        status = false;
        modal.close();
    }

    public boolean getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    
}
