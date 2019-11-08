package app.diario.departamentos.controllers;

import app.diario.departamentos.repository.DepartamentoRepository;
import app.diario.departamentos.utils.Validacao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.scene.control.Label;

public class ModalEditarController implements Initializable{

    private String nome;
    private int idCampi, id;
    private String mensagem;

    @FXML
    private Label aviso;
    
    @FXML
    private TextField nomeTf;

    @FXML
    private TextField campusTf;

    @FXML
    private Button cancelarBtn;

    @FXML
    void editarDepartamento(ActionEvent event) throws SQLException, NumberFormatException {   
        if(nomeTf.getText().isEmpty() || campusTf.getText().isEmpty()){
                setMensagem("Por favor, verifique o preenchimento dos campos abaixo.");
                return;
        }
        else{
            String nome = nomeTf.getText();
            Integer campus = Integer.parseInt(campusTf.getText());
            
            if(!Validacao.validaNome(nome)){
                setMensagem("Nome inválido(Excede 255 caracteres).");
                return;
            }  

            DepartamentoRepository.atualiza(id, campus, nome);
        
            Stage modal = (Stage) cancelarBtn.getScene().getWindow();
            modal.close();
        }
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
    
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            nomeTf.setText(nome);
            campusTf.setText(String.valueOf(idCampi));
        });
    }

}
