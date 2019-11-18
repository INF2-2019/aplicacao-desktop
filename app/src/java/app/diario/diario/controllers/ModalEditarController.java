package app.diario.diario.controllers;


import app.diario.diario.model.ConteudosModel;

import app.diario.diario.utils.Validacao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import app.diario.diario.repository.ConteudosRepository;
import java.time.LocalDate;

public class ModalEditarController implements Initializable {

    private ObservableList<String> choiceBoxObservableList;
    private ObservableList<ConteudosModel> ConteudoObservableList;

    private String conteudo;

    private int id;
    
    private boolean status;
    private String Conteudo,Etapa;
    private Date data;
    
    private String mensagem;

    @FXML
    private Label aviso;

    @FXML
    private TextField EdConteudo;
   
 
    @FXML
    private DatePicker  EdData;

    @FXML
    private TextField EdEtapa;

    @FXML
    private ChoiceBox<String> campusCb;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Button editarBtn;

    @FXML
    void editarConteudo(ActionEvent event) {
        try {
          
            if (EdConteudo.getText().isEmpty() || EdEtapa.getText() == null ||EdData.getValue() == null) {
                setAviso("Por favor, verifique o preenchimento dos campos abaixo.");
            } else {
                System.out.println("1");
                String Cont = EdConteudo.getText();
                String Et = EdEtapa.getText();
                LocalDate Dat = EdData.getValue();
               
                if (!Validacao.validaNome(conteudo)) {
                    setAviso("Nome inválido(Excede 255 caracteres).");
                } else {
                    ConteudosRepository.atualiza(id, Et, Cont);
                    Stage modal = (Stage) cancelarBtn.getScene().getWindow();
                    status = true;
                    modal.close();
                }
            }
        } catch (SQLException e) {
            setAviso("Não foi possível editar o departamento.");
        }
    }

    ;

    @FXML
    void cancelar(ActionEvent event) {
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        status = false;
        modal.close();
    }

    public void setData(int i, String idc, String n) {
        id = i;
        Etapa = idc;
        Conteudo = n;
    }

    public void setAviso(String aviso) {
        this.aviso.setText(aviso);
    }

 
    public boolean getStatus() {
        return status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }

  
}
