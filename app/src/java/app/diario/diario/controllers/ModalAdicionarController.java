package app.diario.diario.controllers;

import app.diario.diario.model.AdicionarConteudosModel;

import app.diario.diario.model.EtapaModel;
import app.diario.diario.repository.ConteudosRepository;



import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class ModalAdicionarController implements Initializable {

    private ObservableList<EtapaModel> EtapaObservableList;
    private ObservableList<String> choiceBoxObservableList;

    private String mensagem;

    private int idEtapa;

    private boolean status;

    @FXML
    private Label aviso;

    @FXML
    private TextField ConteudoTf;
    @FXML
    private TextField EtapaTf;
    @FXML
    private TextField DataTf;
    @FXML
    private DatePicker DataPicker;
    

    @FXML
    private ChoiceBox<String> EtapaCb;

    @FXML
    private Button adicionarBtn;

    @FXML
    private Button cancelarBtn;
    private Object data;

    @FXML
    void Adicionar(ActionEvent event) throws SQLException, ParseException {
        try {
            if (ConteudoTf.getText().isEmpty() || EtapaCb.getValue() == null) {
                setAviso("Por favor, verifique o preenchimento dos campos abaixo.");
            } else {
                AdicionarConteudosModel AdcConteudos= new AdicionarConteudosModel();
                String Cont = ConteudoTf.getText();
             
                AdcConteudos.setConteudo(Cont);
            
               AdcConteudos.setId(13);
               AdcConteudos.setValor(0.00);
                boolean insere = ConteudosRepository.insere(AdcConteudos);
                
            
                   insere = ConteudosRepository.insere(AdcConteudos);
                    
                    Stage modal = (Stage) cancelarBtn.getScene().getWindow();
                    status = true;
                    modal.close();
                
            }
        } catch (SQLException e) {
            status = false;
            setAviso("Não foi possível adicionar o departamento.");
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage modal = (Stage) cancelarBtn.getScene().getWindow();
        modal.close();
    }

    public void setChoiceBox() throws SQLException {
        EtapaObservableList = FXCollections.observableArrayList(ConteudosRepository.consultaEtapa());
        choiceBoxObservableList = FXCollections.observableArrayList();
       for (EtapaModel e : EtapaObservableList) {
            choiceBoxObservableList.add(e.getId() + " - " + e.getAno() + " - " + e.getValor());
        }
        EtapaCb.setItems(choiceBoxObservableList);
    }

    public void setAviso(String aviso) {
        this.aviso.setText(aviso);
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aviso.getStylesheets().add("/app/diario/departamentos/Departamentos.css");
        aviso.getStyleClass().add("aviso-erro");
        try {
            setChoiceBox();
        } catch (SQLException ex) {
            setAviso("Houve um erro ao carregar os campi");
        }
    }
}
