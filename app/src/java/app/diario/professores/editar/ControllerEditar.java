package app.diario.professores.editar;

import app.diario.professores.principal.TableController;
import app.utils.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
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
    private ChoiceBox<String> tituInput;
    
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
                    + ", `titulacao` = '"+tituInput.getValue()+"'"
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
               
        ObservableList<String> oblist = FXCollections.observableArrayList();
        oblist.add("M");
        oblist.add("D");
        oblist.add("G");
        oblist.add("E");
        
         try {
            Connection connection = ConnectionFactory.getDiario();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `professores` WHERE `id`="+id);
            rs.first();
            siapeInput.setText(rs.getString("id"));
            nomeInput.setText(rs.getString("nome"));
            emailInput.setText(rs.getString("email"));
            tituInput.setValue(rs.getString("titulacao"));
            tituInput.setItems(oblist);
            idDeptoInput.setText(Integer.toString(rs.getInt("id-depto")));
            senhaInput.setText(rs.getString("senha"));
  
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void fecha(){
        MainEditar.getStage().close();
    }
}
