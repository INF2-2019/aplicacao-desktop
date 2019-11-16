
package app.biblioteca.relatorios.principal;

import app.biblioteca.relatorios.relAtrasos.RelAtrasosMain;
import app.biblioteca.relatorios.relDescartes.RelDescartesMain;
import app.biblioteca.relatorios.relMultas.RelMultasMain;

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
import javafx.stage.Stage;

public class RelatoriosController implements Initializable {
    
    
    @FXML
    private Button rel_descartes;

    @FXML
    private Button rel_atrasos;

    @FXML
    private Button rel_multas;
    
    @FXML
    private Button btnVoltar;

    
    @FXML
    void AbreRelAtrasos(ActionEvent event) throws SQLException {
         RelAtrasosMain emp = new RelAtrasosMain();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    @FXML
    void AbreRelMultas(ActionEvent event) throws SQLException {
         RelMultasMain emp = new RelMultasMain();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    @FXML
    void AbreRelDescartes(ActionEvent event) throws SQLException {
         RelDescartesMain emp = new RelDescartesMain();
        try {
            emp.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    @FXML
    public void volta() {
        BibliotecaMainRelatorios.getStage().close();
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fecha(){
        BibliotecaMainRelatorios.getStage().close();
    }
}
