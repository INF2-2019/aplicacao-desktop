
package app.biblioteca.relatorios.impressao;


import app.biblioteca.relatorios.relAtrasos.RelAtrasosMain;

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


public class ImpRelAtrasosController implements Initializable {
    
    @FXML
    private Button rel_atrasos;
    
    
    @FXML
    void AbreRelAtrasos(ActionEvent event) throws SQLException {
         RelAtrasosMain emp = new RelAtrasosMain();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ImpRelAtrasosController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fecha(){
        MainImpRelAtrasos.getStage().close();
    }
}
