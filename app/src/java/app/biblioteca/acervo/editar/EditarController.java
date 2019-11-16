package app.biblioteca.acervo.editar;

import app.biblioteca.acervo.principal.DbConnector;
import app.biblioteca.acervo.principal.TableController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
 * @author Indra Matsiendra & Amanda Jacomette
 */
public class EditarController implements Initializable {
    static String id;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        EditarController.id = id;
    }

    
    @FXML
    private Button botaoCancelar;
    
    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField idInput;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idCampiInput;

    @FXML
    private TextField nomeInput;
    
    @FXML
    private Label nomeLabel;

    @FXML
    private TextField localInput;

    @FXML
    private Label localLabel;

    @FXML
    private Label AnoLabel;
    
    @FXML
    private TextField anoInput;

    @FXML
    private Label editoraLabel;
    
    @FXML
    private TextField editoraInput;

    @FXML
    private Label paginasLabel;
    
    @FXML
    private TextField paginasInput;

    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    public void acaoSalvar(){
        try {
            Connection connection = DbConnector.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE `acervo` SET"
                    + " `id` = ? , `id-campi` = ? "
                    + ", `nome` = ? , `local` = ?"
                    + ", `ano` = ? , `editora` = ?"
                    + ", `paginas` = ? WHERE `id` = "+EditarController.getId());
            // preenche os valores
            stmt.setInt(1,Integer.parseInt(idInput.getText()));
            stmt.setInt(2,Integer.parseInt(idCampiInput.getText()));
            stmt.setString(3,nomeInput.getText());
            stmt.setString(4,localInput.getText());
            stmt.setInt(5,Integer.parseInt(anoInput.getText()));
            stmt.setString(6,editoraInput.getText());
            stmt.setInt(7,Integer.parseInt(paginasInput.getText()));
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
        EditarMain.getStage().close();
    }
}