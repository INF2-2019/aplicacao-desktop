/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import PacoteDeletar.MainDeleta;
import PacoteInfo.FXMLInfoController;
import PacoteInsere.InsereMain;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pacoteinfo.InfoMain;

/**
 *
 * @author Nikolas Victor
 * @author Jonata Novaes
 */
public class TableController implements Initializable {
    
    @FXML
    private TableView<Professor> tabelaProfessores;
    
    @FXML
    private TableColumn<Professor, String> colunaTitulacao;

    @FXML
    private TableColumn<Professor, Integer> colunaId;

    @FXML
    private TableColumn<Professor, Integer> colunaIdDpto;

    @FXML
    private TableColumn<Professor, String> colunaNome;
    
    @FXML
    private Button botaoAdicionar;
            
    
    private List<Professor> listaProfessores = new ArrayList();
    
    private ObservableList<Professor> observableListProfessor;
    
    private int id = 3;
    
    public static ObservableList<Professor> oblist = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    
    @FXML
    private void Insere(javafx.event.ActionEvent event) {
       InsereMain insere = new InsereMain();
        try {
            insere.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void deleta(String id){
         try {
            Connection connection = DbConnector.getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from professores where id="+id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }
    
    static public void consultarBD(){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from professores");
            oblist = FXCollections.observableArrayList();
            
            while(rs.next()){
                oblist.add(new Professor(rs.getInt("id"),rs.getInt("idDpto"),rs.getString("nome"),rs.getString("titulacao"), rs.getString("email"), rs.getString("email"),new Button("EDITAR"),new Button("DELETAR"), new Button("INFO")));
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
