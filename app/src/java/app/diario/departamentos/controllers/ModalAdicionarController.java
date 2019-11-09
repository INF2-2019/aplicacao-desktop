package app.diario.departamentos.controllers;

import app.diario.departamentos.repository.DepartamentoRepository;
import app.diario.departamentos.utils.Validacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ModalAdicionarController { 
    
    private String mensagem;
    
    @FXML
    private Label aviso;
    
    @FXML
    private TextField nomeTf;

    @FXML
    private TextField campusTf;

    @FXML
    private Button adicionarBtn;

    @FXML
    private Button cancelarBtn;

    @FXML
    void adicionarDepartamento(ActionEvent event){
        try{
            if(nomeTf.getText().isEmpty() || campusTf.getText().isEmpty()){
                setAviso("Por favor, verifique o preenchimento dos campos abaixo.");  
            }
            else{
                String nome = nomeTf.getText();
                Integer campus = Integer.parseInt(campusTf.getText());
            
                if(!Validacao.validaNome(nome)){
                    setAviso("Nome inválido(Excede 255 caracteres).");
                }
                else{
                    DepartamentoRepository.insere(campus, nome);
                    Stage modal = (Stage) cancelarBtn.getScene().getWindow();
                    modal.close();
                }           
            }
        }
        catch(SQLException e){
            setAviso("Não foi possível adicionar o departamento.");
        }
    }

    public void setAviso(String aviso){
        this.aviso.setText(aviso);
    }
    
    @FXML
    private ChoiceBox<?> campus;
    
    @FXML
    void cancelar(ActionEvent event) {
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        modal.close();
    }

}
