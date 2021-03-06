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
public class ControllerInsere implements Initializable {

    public static ObservableList<String> departamentosLista = FXCollections.observableArrayList();
    
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
    private Label labelError;
    
    @FXML
    private  ChoiceBox <String> DepartamentosInput;

    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }


    @FXML
    void acaoSalvar(ActionEvent event) throws SQLException {
        if (
                !"".equals(nomeInput.getText())
                && !"".equals(horasInput.getText())
                && !"".equals(modalidadeInput.getText())) {
            if (
                     isNum(horasInput.getText())) {
                Connection con = new DbConnector().getConnection();

                // cria um preparedStatement
                String sql = "insert into cursos"
                        + " (`id-depto`, `nome`, `horas-total`, `modalidade`)"
                        + " values (?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);

                // preenche os valores
                stmt.setString(1, DepartamentosInput.getValue());
                stmt.setString(2, nomeInput.getText());
                stmt.setString(3, horasInput.getText());
                stmt.setString(4, modalidadeInput.getText());
                stmt.execute();
                stmt.close();
                con.close();
                fecha();
            } else {
                labelError.setText("Valores inseridos inválidos");
            }
        } else {
            labelError.setText("É necessário preencher todos os campos");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheChoiceBox();
    }

    public void fecha() {
        InsereMain.getStage().close();
    }

    public static boolean isNum(String strNum) {
        boolean ret = true;
        try {

            Double.parseDouble(strNum);

        } catch (NumberFormatException e) {
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
            DepartamentosInput.getItems().addAll(departamentosLista);
    }
      
    
}
