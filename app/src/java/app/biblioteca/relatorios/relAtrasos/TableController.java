
package app.biblioteca.relatorios.relAtrasos;

import app.biblioteca.relatorios.principal.DbConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;


public class TableController implements Initializable{
    
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, Integer> col_id;
    @FXML
    private TableColumn<ModelTable, Integer> col_idAlunos;
    @FXML
    private TableColumn<ModelTable, Integer> col_idAcervo;
    @FXML
    private TableColumn<ModelTable, String> col_atraso;
    
    
    private boolean podeConstruir=false;
    private Date data =new Date();

    static ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultarBD();
        if(podeConstruir){
        criaTabela();
        }
        
    }
    public void refresh(){
        oblist.clear();
        consultarBD();
        if(podeConstruir){
        criaTabela();
        }
    }
    
     public void consultarBD(){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from emprestimos");
            oblist = FXCollections.observableArrayList();
            //tabela.dataPrevDevol.compareTo(data);
            
           while(rs.next()){
                
                if(rs.getDate("data-devolucao").getTime()==0){
                    if(rs.getDate("data-prev-devol").compareTo(data)<0){
			
			long dt = (data.getTime() - rs.getDate("data-prev-devol").getTime()) + 3600000; // 1 hora para compensar horário de verão
			double diasatraso = dt / 86400000L;
			String strAtraso = Double.toString(diasatraso);
			String strSemPonto = strAtraso.substring(0, strAtraso.length()-2);
                        oblist.add(new ModelTable(rs.getString("id"),verificaAlunos(rs.getString("id-alunos")),verificaAcervo(rs.getString("id-acervo")),strSemPonto));
                        podeConstruir=true;
                      }else if(rs.getDate("data-prev-devol").compareTo(data)>=0){
                         podeConstruir=false;
                     }
                }
            }
            
            
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void criaTabela(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_idAlunos.setCellValueFactory(new PropertyValueFactory<>("idAlunos"));
        col_idAcervo.setCellValueFactory(new PropertyValueFactory<>("idAcervo"));
        col_atraso.setCellValueFactory(new PropertyValueFactory<>("atraso"));
        table.setItems(oblist);
    }
    public static String verificaAcervo(String id) throws SQLException {
        ResultSet resultadoBusca;
        Connection con = DbConnector.getConnection();
        int Iid = Integer.parseUnsignedInt(id);
        // cria um preparedStatement
        String sql = "SELECT `nome` FROM `acervo` WHERE `id` = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Iid);
        resultadoBusca =  stmt.executeQuery();
        resultadoBusca.next();
        return resultadoBusca.getString("nome");
    }
    public static String verificaAlunos(String id) throws SQLException {
        ResultSet resultadoBusca;
        Connection con = DbConnector.getConnection();
        int Iid = Integer.parseUnsignedInt(id);
        // cria um preparedStatement
        String sql = "SELECT `nome` FROM `alunos` WHERE `id` = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Iid);
        resultadoBusca =  stmt.executeQuery();
        resultadoBusca.next();
        return resultadoBusca.getString("nome");
    }
}
