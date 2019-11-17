package app.diario.relatorios.rel8e9.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import app.diario.relatorios.rel8e9.relatorioModels.*;
import app.utils.ConnectionFactory;

/**
 *
 * @author osmar
 */
public class FXMLDocumentController implements Initializable {
    public String arquivo;
    
    @FXML
    private Button btnFechar;
    
    @FXML
    private TableView<Tabela> tabela;

    @FXML
    private TableColumn<Tabela, Long> id;

    @FXML
    private TableColumn<Tabela, String> nome;

    @FXML
    private TableColumn<Tabela, Button> acoes;
    
   
    @FXML
    private void voltar(ActionEvent event) throws Exception {
        
    }
       
    ObservableList<Tabela> profs = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultarBD(); 
        criaTabela();
        
    }
    public void refresh(){
        consultarBD();
        criaTabela();
    }
    
    public void consultarBD(){
        try {
            Connection con = ConnectionFactory.getDiario();
            
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `professores`");
            profs = FXCollections.observableArrayList();
            while(rs.next()){
                profs.add(new Tabela(rs.getLong("id"),rs.getString("nome"),new Button("info")));
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void criaTabela(){
        id.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));        
        acoes.setCellValueFactory(new PropertyValueFactory<>("info"));    
        tabela.setItems(profs);
    }
    
    
}
