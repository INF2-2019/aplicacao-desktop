/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.cursos;

import app.diario.telatransicao.MainTelaTransicaoDiario;
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
public class ControllerTable implements Initializable{
    
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, Integer> col_id;
    @FXML
    private TableColumn<ModelTable, Integer> col_idDepto;
    @FXML
    private TableColumn<ModelTable, String> col_nome;
    @FXML
    private TableColumn<ModelTable, Integer> col_horas;
    @FXML
    private TableColumn<ModelTable, String> col_modalidade;
    @FXML
    private TableColumn<ModelTable, Button> col_funcoes1;
    @FXML
    private TableColumn<ModelTable, Button> col_funcoes2;
    @FXML
    private Button voltar;
    
    @FXML
    private void Insere(javafx.event.ActionEvent event) {
       InsereMain insere = new InsereMain();
       ControllerInsere controlador = new ControllerInsere();
        try {
            insere.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ControllerTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML 
    public void acaoVoltar(){
        MainTelaTransicaoDiario main = new MainTelaTransicaoDiario();
        fecha();
        try {
            main.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ControllerTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultarBD();
        criaTabela();
    }
    public void refresh(){
        consultarBD();
        criaTabela();
    }
    
    
    
   
    
    public void deleta(String id){
         try {
            Connection connection = DbConnector.getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from cursos where id="+id);
            stmt.execute();
            stmt.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
    
    static public void consultarBD(){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from cursos");
            oblist = FXCollections.observableArrayList();
            
            while(rs.next()){
                oblist.add(new ModelTable(rs.getString("id"),verificaDepto(rs.getString("id-depto")),rs.getString("nome"),rs.getString("horas-total"),rs.getString("modalidade"),new Button("EDITAR"),new Button("DELETAR")));
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControllerTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void criaTabela(){
        table.refresh();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_idDepto.setCellValueFactory(new PropertyValueFactory<>("idDepto"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_horas.setCellValueFactory(new PropertyValueFactory<>("horas"));
        col_modalidade.setCellValueFactory(new PropertyValueFactory<>("modalidade"));
        col_funcoes1.setCellValueFactory(new PropertyValueFactory<>("edita"));
        col_funcoes2.setCellValueFactory(new PropertyValueFactory<>("deleta"));
        
          
        table.setItems(oblist);
    }
    public static String verificaDepto(String id) throws SQLException {
        ResultSet resultadoBusca;
        Connection con = DbConnector.getConnection();
        int Iid = Integer.parseUnsignedInt(id);
        // cria um preparedStatement
        String sql = "SELECT `nome` FROM `departamentos` WHERE `id` = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Iid);
        resultadoBusca =  stmt.executeQuery();
        resultadoBusca.next();
        return resultadoBusca.getString("nome");
    }
    public void fecha() {
        CursosMain.getStage().close();
    }
}
