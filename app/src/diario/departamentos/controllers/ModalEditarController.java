package diario.departamentos.controllers;

import diario.departamentos.repository.DepartamentoRepository;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalEditarController implements Initializable{

    private String nome;
    private int idCampi, id;
    
    @FXML
    private TextField nomeTf;

    @FXML
    private TextField campusTf;

    @FXML
    private Button cancelarBtn;

    @FXML
    void editarDepartamento(ActionEvent event) throws SQLException {
        try {
            String nome = nomeTf.getText();
            Integer campus = Integer.parseInt(campusTf.getText());
            DepartamentoRepository.atualiza(id, campus, nome);
        } catch (NumberFormatException ex) {
            System.err.println("O id do campus deve ser um inteiro");
        }
        
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        modal.close();
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        modal.close();
    }
    
    public void setData(int i, int idc, String n){
        id = i;
        idCampi = idc;
        nome = n;
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            nomeTf.setText(nome);
            campusTf.setText(String.valueOf(idCampi));
        });
    }

}
