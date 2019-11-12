package app.diario.professores.editar;

import app.diario.professores.principal.TableController;
import app.utils.ConnectionFactory;
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
 * @author Nikolas Victor
 * @author Jonata Novais
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
    private Button botaoSalvar;

    @FXML
    private TextField idDeptoInput;

    @FXML
    private Label nomeLabel;
     
    @FXML
    private TextField senhaInput;
    
    @FXML
    private TextField emailInput;
    
    @FXML
    private TextField titulacaoInput;
    
    @FXML
    private TextField siapeInput;
    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    public void acaoSalvar(){
        try {
            Connection connection = ConnectionFactory.getDiario();
            PreparedStatement stmt = connection.prepareStatement("UPDATE `professores` SET"
                    + " `id-depto` = '"+idDeptoInput.getText()+"'"
                    + ", `nome` = '"+nomeInput.getText()+"'"
                    + ", `titulacao` = '"+titulacaoInput.getText()+"'"
                    + ", `email` = '"+emailInput.getText()+"'" 
                    + ", `senha` = '"+senhaInput.getText()+"'" 
                    + ", `id` = '"+siapeInput.getText()+"'" 
                    + " WHERE `professores`.`id` = "+ControllerEditar.getId());
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
