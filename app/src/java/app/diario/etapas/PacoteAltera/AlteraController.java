package app.diario.etapas.PacoteAltera;

import app.diario.etapas.Principal.*;
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

public class AlteraController implements Initializable {
    static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
	AlteraController.id = id;
    }
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    void AcaoCancelar(ActionEvent event) {
	fechar();
    }

    private void fechar() {
	AlteraMain.getStage().close();
    }
    
    @FXML
    void Alterar(ActionEvent event) {
	try {
            Connection connection = DbConnector.getConnection();
	    
	    if(!"".equals(AnoInput.getText()) && !"".equals(ValorInput.getText())){
            PreparedStatement stmt = connection.prepareStatement("UPDATE `etapas` SET"
                    + " `ano` = '"+AnoInput.getText()+"'"
                    + ", `valor` = '"+ValorInput.getText()+"'"
                    + " WHERE `etapas`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();}
	    
	    else if(!"".equals(AnoInput.getText()) && "".equals(ValorInput.getText())){
            PreparedStatement stmt = connection.prepareStatement("UPDATE `etapas` SET"
                    + " `ano` = '"+AnoInput.getText()+"'"
                    + " WHERE `etapas`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();}
	    
	    else if("".equals(AnoInput.getText()) && !"".equals(ValorInput.getText())){
            PreparedStatement stmt = connection.prepareStatement("UPDATE `etapas` SET"
                    + " `valor` = '"+ValorInput.getText()+"'"
                    + " WHERE `etapas`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();}
	    
            connection.close();
	    TableController.consultarBD("select * from etapas");
            fechar();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
