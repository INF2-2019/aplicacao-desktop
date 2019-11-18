package app.biblioteca.acervo.inserir.midia;

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
public class InsereMidiaController {
 
    private static String id;
    
    @FXML
    private TextField idInput;

    @FXML
    private TextField tempoInput;

    @FXML
    private TextField subtipoInput;

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
            System.out.println(idInput.getText());
            
            Connection con = ConnectionFactory.getBiblioteca();
            String sql = "insert into midias" +
                    " (`id-obra`,`id-acervo`, `tempo`, `subtipo`)" +
                    " values (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(idInput.getText()));
            stmt.setInt(2,Integer.parseInt(id));
            stmt.setString(3,tempoInput.getText());
            stmt.setString(4,subtipoInput.getText());
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
        InsereMidiaMain.getStage().close();
    }
       
}