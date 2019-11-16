package app.biblioteca.acervo.inserir.periodico;

import app.biblioteca.acervo.inserir.InsereController;
import app.biblioteca.acervo.principal.DbConnector;
import app.biblioteca.acervo.principal.TableController;
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
public class InserePeriodicoController {
 
    private static String id;
    
    @FXML
    private TextField idPeriodicoInput;

    @FXML
    private TextField periodicidadeInput;

    @FXML
    private TextField mesInput;

    @FXML
    private TextField volumeInput;

    @FXML
    private TextField subtipoInput;

    @FXML
    private TextField issnInput;

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
        /*BUSCA INFORMAÇÕES NO BANCO DE DADOS E MOSTRA (nos respectivos labels)*/
        try {
            System.out.println(idPeriodicoInput.getText());
            
            Connection con = DbConnector.getConnection();
            String sql = "insert into periodicos" +
                    " (`id`,`id-acervo`, `periodicidade`, `mes`, `volume`, `subtipo`, `issn`)" +
                    " values (?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(idPeriodicoInput.getText()));
            stmt.setInt(2,Integer.parseInt(id));
            stmt.setString(3,periodicidadeInput.getText());
            stmt.setString(4,mesInput.getText());
            stmt.setInt(5,Integer.parseInt(volumeInput.getText()));
            stmt.setString(6,subtipoInput.getText());
            stmt.setInt(7,Integer.parseInt(issnInput.getText()));
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
	    Connection con = new DbConnector().getConnection();
            String sql = "DELETE FROM acervo WHERE id=?";
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
        InserePeriodicoMain.getStage().close();
    }
       
}