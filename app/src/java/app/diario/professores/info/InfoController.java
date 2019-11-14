package app.diario.professores.info;

import app.diario.professores.insere.InsereMain;
import app.diario.professores.principal.TableController;
import app.utils.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nikolas Victor
 * @author Jonata Novais
 */
public class InfoController implements Initializable {

    public static String id;
    @FXML
    private Label labelId;

    @FXML
    private Label labelDepartamento;

    @FXML
    private Label labelTitulacao;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelNome;
    
    
    @FXML
    private Button botaoFechar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection connection = ConnectionFactory.getDiario();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `professores` WHERE `id`="+InfoController.getId());
            rs.first();
            labelId.setText(rs.getString("id"));
            labelNome.setText(rs.getString("nome"));
            labelEmail.setText(rs.getString("email"));
            labelTitulacao.setText(rs.getString("titulacao"));
            PreparedStatement s = connection.prepareStatement("SELECT `nome` FROM `departamentos` WHERE `id` = ?");
            s.setInt(1, rs.getInt("id-depto"));
			ResultSet r = s.executeQuery();
			r.first();
			labelDepartamento.setText(r.getString(1));
  
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        InfoController.id = id;
    }
    
    
    
}
