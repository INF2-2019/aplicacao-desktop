package app.biblioteca.acervo.inserir.academico;

import app.biblioteca.acervo.inserir.InsereController;
import app.biblioteca.acervo.principal.TableController;
import app.utils.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Indra Matsiendra & Amanda Jacomette
 */
public class InsereAcademicoController {
    private static String id;
    
    @FXML
    private TextField idObraAcademicoInput;

    @FXML
    private TextField programaInput;
    
    public static String getId() {
        return id;
    }

    public static void setId(String i) {
        id = i;
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void acaoSalvar(){
        try {
            
            Connection con = ConnectionFactory.getBiblioteca();
            String sql = "insert into academicos" +
                    " (`id-obra`,`id-acervo`, `programa`)" +
                    " values (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(idObraAcademicoInput.getText()));
            stmt.setInt(2,Integer.parseInt(id));
            stmt.setString(3,programaInput.getText());
            stmt.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fecha();
    }
    public void acaoCancelar(){
        /*se for cancelado aquilo que foi salvo em acervo nao pode permanecer na tabela, será apagado*/
        try{
	    Connection con = ConnectionFactory.getBiblioteca();
            String sql = "DELETE FROM `acervo` WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.setInt(1,Integer.parseInt(InsereController.getId()));
            stmt.execute();
	    con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
	fecha();
    }
    public void fecha(){
        InsereAcademicoMain.getStage().close();
    }
       
}
    
