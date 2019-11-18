
package app.biblioteca.relatorios.impressao;

import app.biblioteca.relatorios.relDescartes.RelDescartesMain;

import java.net.URL;
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
import javafx.stage.Stage;


public class ImpRelDescartesController implements Initializable {
    
    

    @FXML
    private Button rel_descartes;

    
    
    @FXML
    void AbreRelDescartes(ActionEvent event) throws SQLException {
         RelDescartesMain emp = new RelDescartesMain();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ImpRelDescartesController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fecha(){
        MainImpRelDescartes.getStage().close();
    }
}