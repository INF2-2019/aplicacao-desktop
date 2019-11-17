/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.campi.Alterar;


import app.biblioteca.campi.Principal.DbConnector;
import app.biblioteca.campi.Principal.TableController;
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
 * FXML Controller class
 *
 * @author Marcelo
 */
public class AlteraController implements Initializable {
    static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
	AlteraController.id = id;
    }
    
   
   @FXML
    private TextField nomeInput;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Label AnoLabel1;

    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField ufInput;

    @FXML
    private TextField cityInput;

    @FXML
    private Label AnoLabel4;

    @FXML
    private Label AnoLabel2;


    
    
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
        System.out.print("pao");
	try {
            Connection connection = DbConnector.getConnection();
	   
            if(!"".equals(nomeInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `campi` SET"
                    + " `nome` = '"+nomeInput.getText()+"'"
                    
                    + " WHERE `campi`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
            
                 if(!"".equals(cityInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `campi` SET"
                    + " `cidade` = '"+cityInput.getText()+"'"
                    
                    + " WHERE `campi`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
                   
	      if(!"".equals(ufInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `campi` SET"
                    + " `uf` = '"+ufInput.getText()+"'"
                    
                    + " WHERE `campi`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
             
                
	    
            connection.close();
            fechar();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
    }
    
}
/*
if(!"".equals(bairroInput.getText()) && !"".equals(ValorInput.getText())){
            PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `ano` = '"+AnoInput.getText()+"'"
                    + ", `valor` = '"+ValorInput.getText()+"'"
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
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


*/