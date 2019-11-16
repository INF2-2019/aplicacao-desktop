package app.biblioteca.acervo.info;

import app.biblioteca.acervo.principal.TableController;
import app.utils.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Indra Matsiendra & Amanda Jacomette
 */
public class InfoController implements Initializable {
    private static String id,tipo;

    public static String getId() {
        return id;
    }

    public static void setId(String i) {
        id = i;
    }

    public static String getTipo() {
        return tipo.toLowerCase();
    }

    public static void setTipo(String tipo) {
        InfoController.tipo = tipo.toLowerCase();
    }
    
    @FXML
    private Label labelId;

    @FXML
    private Label labelIdCampi;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelTipo;

    @FXML
    private Label labelLocal;

    @FXML
    private Label labelAno;

    @FXML
    private Label labelEditora;

    @FXML
    private Label labelPaginas;

    
    @FXML
    private Label l1;
    @FXML
    private Label label1;

    
    @FXML
    private Label l2;
    @FXML
    private Label label2;

    
    @FXML
    private Label l3;
    @FXML
    private Label label3;

    
    @FXML
    private Label l4;
    @FXML
    private Label label4;

    
    @FXML
    private Label l5;
    @FXML
    private Label label5;

    
    @FXML
    private Label l6;
    @FXML
    private Label label6;
    
    @FXML
    private Button botaoOk;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*BUSCA INFORMAÇÕES NO BANCO DE DADOS E MOSTRA (nos respectivos labels)*/
        try {
            Connection con = ConnectionFactory.getBiblioteca();
            /*ACERVO*/
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `acervo` WHERE `id`="+InfoController.getId());
            rs.first();
            labelId.setText(rs.getString("id"));
            labelIdCampi.setText(rs.getString("id-campi"));
            labelNome.setText(rs.getString("nome"));
            labelTipo.setText(rs.getString("tipo"));
            labelLocal.setText(rs.getString("local"));
            labelAno.setText(rs.getString("ano"));
            labelEditora.setText(rs.getString("editora"));
            labelPaginas.setText(rs.getString("paginas"));
            
            /*INFORMACOES DO TIPO DE OBRA*/
            ResultSet rsTipo = con.createStatement().executeQuery("SELECT * FROM `"+InfoController.getTipo()+"` WHERE `id-acervo`="+InfoController.getId());
            rsTipo.first();
            l1.setText("id-obra:");//fixo
            switch(tipo)
            {
                case "academicos":
                    label1.setText(rsTipo.getString("id-obra"));
                    l2.setText("programa:");
                    label2.setText(rsTipo.getString("programa"));
                    l3.setText("");
                    l4.setText("");
                    l5.setText("");
                    l6.setText("");
                    label3.setVisible(false);
                    label4.setVisible(false);
                    label5.setVisible(false);
                    label6.setVisible(false);
                    break;
                case "livros":
                    label1.setText(rsTipo.getString("id-obra"));
                    l2.setText("edição:");
                    label2.setText(rsTipo.getString("edicao"));
                    l3.setText("isbn:");
                    label3.setText("isbn");
                    l4.setText("");
                    l5.setText("");
                    l6.setText("");
                    label4.setVisible(false);
                    label5.setVisible(false);
                    label6.setVisible(false);
                    break;
                case "midias":
                    label1.setText(rsTipo.getString("id-obra"));
                    l2.setText("tempo:");
                    label2.setText(rsTipo.getString("tempo"));
                    l3.setText("subtipo:");
                    label3.setText("subtipo");
                    l4.setText("");
                    l5.setText("");
                    l6.setText("");
                    label4.setVisible(false);
                    label5.setVisible(false);
                    label6.setVisible(false);
                    break;
                case "periodicos":
                    label1.setText(rsTipo.getString("id"));
                    l2.setText("periodicidade:");
                    label2.setText(rsTipo.getString("periodicidade"));
                    l3.setText("mês:");
                    label3.setText(rsTipo.getString("mes"));
                    l4.setText("volume:");
                    label4.setText(rsTipo.getString("volume"));
                    l5.setText("subtipo:");
                    label5.setText(rsTipo.getString("subtipo"));
                    l6.setText("issn:");
                    label6.setText(rsTipo.getString("issn"));
                    break;
                    
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void fecha(){
        InfoMain.getStage().close();
    }
    
    
}