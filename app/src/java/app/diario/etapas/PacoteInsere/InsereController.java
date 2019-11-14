package app.diario.etapas.PacoteInsere;

import app.diario.etapas.Principal.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InsereController implements Initializable {
    
    @FXML
    private Label AnoLabel;

    @FXML
    private Label ValorLabel;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField AnoInput;

    @FXML
    private TextField ValorInput;
    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    void acaoSalvar(ActionEvent event) throws SQLException {
        Connection con = new DbConnector().getConnection();

		// cria um preparedStatement
		String sql = "insert into etapas" +
				" (`ano`, `valor`)" +
				" values (?,?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println((Button) event.getSource());

		// preenche os valores
		stmt.setInt(1,Integer.parseInt(AnoInput.getText()));
		stmt.setDouble(2,Double.parseDouble(ValorInput.getText()));
		stmt.execute();
		stmt.close();
		con.close();
		fecha();
		TableController.consultarBD("select * from etapas");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void fecha(){
        InsereMain.getStage().close();
    }
}
