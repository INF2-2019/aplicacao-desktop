package app.diario.departamentos.controllers;

import app.diario.departamentos.repository.DepartamentoRepository;
import app.diario.departamentos.utils.Validacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.regex.*;
import java.sql.SQLException;

public class ModalAdicionarController { 
    
    private String mensagem;
    
    @FXML
    private TextField nomeTf;

    @FXML
    private TextField campusTf;

    @FXML
    private Button adicionarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    void adicionarDepartamento(ActionEvent event) throws SQLException, NumberFormatException {
        if(nomeTf.getText().isEmpty() || campusTf.getText().isEmpty()){
            setMensagem("Por favor, verifique o preenchimento dos campos abaixo.");
            return;
        }
        else{
            String nome = nomeTf.getText();
            Integer campus = Integer.parseInt(campusTf.getText());
            
            if(!Validacao.validaNome(nome)){
                setMensagem("Nome inválido");
                return;
            }  

            DepartamentoRepository.insere(campus, nome);
        
            Stage modal = (Stage) cancelarBtn.getScene().getWindow();
            modal.close();
        }
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    
    @FXML
    void cancelar(ActionEvent event) {
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        modal.close();
    }

}
