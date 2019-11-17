/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.campi.Principal;

import app.diario.campi.Inserir.InsereMain;
import app.inicio.MainApp;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.PreparedStatement;

/**
 *
 * @author Aluno
 */
public class TableController implements Initializable{
    
     @FXML
    private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable, Integer> col_id;

    @FXML
    private Button adiciona;
    @FXML
    private Button voltar;
    @FXML
    private TableColumn<ModelTable, String> col_nome;

    @FXML
    private TableColumn<ModelTable, String> col_cidade;

    @FXML
    private TableColumn<ModelTable, Button> col_funcoes;

    @FXML
    private TableColumn<ModelTable,Button> col_funcoes1;

    
    @FXML
    private TableColumn<ModelTable, String> col_uf;

        
   
    @FXML
    private void Insere(javafx.event.ActionEvent event) {
       InsereMain insere = new InsereMain();
        try {
            insere.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleta(int id){
         try {
            Connection connection = DbConnector.getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from campi where id="+id);
            stmt.execute();
            stmt.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
     public void voltar(javafx.event.ActionEvent event)
    {
        fechar();
        MainApp inicioabrir=new MainApp();
        try {
            inicioabrir.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     private void fechar() {
	Main.getStage().close();
    }

    

    static ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultarBD();
        criaTabela();
        
    }
    public void refresh(){
        oblist.clear();
        consultarBD();
        criaTabela();
    }
    
    static public void consultarBD(){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from campi");
            oblist = FXCollections.observableArrayList();
            while(rs.next()){
                oblist.add(new ModelTable(rs.getInt("id"),rs.getString("nome"),rs.getString("cidade"),rs.getString("uf"),new Button("DELETAR"),new Button("EDITAR") ));
            }
            //rs.getInt("id"),rs.getString("nome"),rs.getString("email"), new Button("INFO") ,new Button("EDITAR"),new Button("DELETAR")
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void criaTabela(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        col_uf.setCellValueFactory(new PropertyValueFactory<>("uf"));
        col_funcoes.setCellValueFactory(new PropertyValueFactory<>("deletar"));
        col_funcoes1.setCellValueFactory(new PropertyValueFactory<>("editar"));
        table.setItems(oblist);
    }
}
