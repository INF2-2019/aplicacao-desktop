/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteInsere;

import Principal.DbConnector;
import Principal.TableController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author tuba1
 */
public class InsereController implements Initializable {
    
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
    private TextField idDeptoInput;

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
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    void acaoSalvar(ActionEvent event) throws SQLException {
        System.out.println("\n"+idDeptoInput.getText()+"<-- texto aqui");
        if(!"".equals(idDeptoInput.getText()) || !"".equals(nomeInput.getText()) || !"".equals(horasInput.getText()) || !"".equals(modalidadeInput.getText())){
            Connection con = new DbConnector().getConnection();

            // cria um preparedStatement
            String sql = "insert into cursos" +
                    " (`id-depto`, `nome`, `horas-total`, `modalidade`)" +
                    " values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            // preenche os valores
            stmt.setString(1,idDeptoInput.getText());
            stmt.setString(2,nomeInput.getText());
            stmt.setString(3,horasInput.getText());
            stmt.setString(4,modalidadeInput.getText());
            stmt.execute();
            System.out.println(stmt.execute());
            stmt.close();
            con.close();
            fecha();
        }
        labelError.setText("É necessário preencher todos os campos");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fecha(){
        InsereMain.getStage().close();
    }
}
