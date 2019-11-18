/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.cursos;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author tuba1
 */
public class ControllerEditar implements Initializable {
    private static String id,nome,horas,modalidade;
    public ObservableList<String> departamentosLista = FXCollections.observableArrayList();

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        ControllerEditar.nome = nome;
    }

    public static String getHoras() {
        return horas;
    }

    public static void setHoras(String horas) {
        ControllerEditar.horas = horas;
    }

    public static String getModalidade() {
        return modalidade;
    }

    public static void setModalidade(String modalidade) {
        ControllerEditar.modalidade = modalidade;
    }
    
    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        ControllerEditar.id = id;
    }
    @FXML
    private TextField nomeInput;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Label idDeptoLabel;

    @FXML
    private TextField idInput;

    @FXML
    private Label idLabel;

    @FXML
    private Button botaoSalvar;

    @FXML
    private ChoiceBox departamentosInput;

    @FXML
    private TextField horasInput;

    @FXML
    private Label modalidadeLabel;

    @FXML
    private TextField modalidadeInput;

    @FXML
    private Label horasLabel;

    @FXML
    private Label nomeLabel;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    public void acaoSalvar(){
        try {
           if(
              !"".equals(nomeInput.getText()) &&
              !"".equals(horasInput.getText()) &&
              !"".equals(modalidadeInput.getText())){
              if( 
                 isNum(horasInput.getText())){
                    Connection connection = DbConnector.getConnection();
                        PreparedStatement stmt = connection.prepareStatement("UPDATE `cursos` SET"
                                + " `id-depto` = '"+departamentosInput.getValue()+"'"
                                + ", `nome` = '"+nomeInput.getText()+"'"
                                + ", `horas-total` = '"+horasInput.getText()+"'"
                                + ", `modalidade` = '"+modalidadeInput.getText()+"'"
                                + " WHERE `cursos`.`id` = "+ControllerEditar.getId());
                        stmt.execute();
                        stmt.close();
                        connection.close();
                        fecha();
               }else
                   errorLabel.setText("Valores inseridos inválidos");
            }
            else{
                errorLabel.setText("É necessário preencher todos os campos");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    public void acaoCancelar(){
        fecha();
    }
    
            
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheChoiceBox();
        textosIniciais();
    }    
    
    public void fecha(){
        EditarMain.getStage().close();
    }
     public static boolean isNum(String strNum) {
        boolean ret = true;
        try {

            Double.parseDouble(strNum);

        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }
     public void preencheChoiceBox(){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from departamentos");
            //departamentosLista = FXCollections.observableArrayList();
            departamentosLista.removeAll(departamentosLista);
            while(rs.next()){
                departamentosLista.add((rs.getString("id")));
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTable.class.getName()).log(Level.SEVERE, null, ex);
        }
            departamentosInput.getItems().addAll(departamentosLista);
    }
     public  void textosIniciais(){
         nomeInput.setText(ControllerEditar.getNome());
         horasInput.setText(ControllerEditar.getHoras());
         modalidadeInput.setText(ControllerEditar.getModalidade());
         
     }
}
