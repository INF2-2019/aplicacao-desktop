/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteEditar;

import Principal.DbConnector;
import Principal.TableController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ControllerEditar implements Initializable {
    static String id;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        ControllerEditar.id = id;
    }

    public static void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    public void acaoSalvar(){
        try {
            Connection connection = DbConnector.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE `cursos` SET"
                    + " `id-depto` = '"+idDeptoInput.getText()+"'"
                    + ", `nome` = '"+nomeInput.getText()+"'"
                    + ", `horas-total` = '"+horasInput.getText()+"'"
                    + ", `modalidade` = '"+modalidadeInput.getText()+"'"
                    + " WHERE `cursos`.`id` = "+ControllerEditar.getId());
            stmt.execute();
            stmt.close();
            connection.close();
            fecha();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    public void acaoCancelar(){
        fecha();
    }
    
            
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fecha(){
        MainEditar.getStage().close();
    }
}
