package diario.departamentos.controllers;

import diario.departamentos.repository.DepartamentoRepository;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
    void editarDepartamento(ActionEvent event) throws SQLException, NumberFormatException {
        try{
            String nome = nomeTf.getText();
            
            if(Pattern.compile("[0-9]*").matcher(campusTf.getText()).matches()){
                Integer campus = Integer.parseInt(campusTf.getText());
                DepartamentoRepository.atualiza(id, campus, nome);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex){
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
