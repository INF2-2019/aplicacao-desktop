
package app.biblioteca.relatorios.relAtrasos;

import app.biblioteca.relatorios.principal.DbConnector;
import java.net.URL;
import java.sql.Connection;
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
    private TableColumn<ModelTable, Date> col_dataEmprestimo;
    @FXML
    private TableColumn<ModelTable, Date> col_dataPrevDevol;
    @FXML
    private TableColumn<ModelTable, Date> col_dataDevolucao;
    @FXML
    private TableColumn<ModelTable, Double> col_multa;
    
    
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
                        oblist.add(new ModelTable(rs.getString("id"),rs.getString("id-alunos"),rs.getString("id-acervo"),rs.getDate("data-emprestimo"),rs.getDate("data-prev-devol"),rs.getDate("data-devolucao"),rs.getDouble("multa")));
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
        col_dataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        col_dataPrevDevol.setCellValueFactory(new PropertyValueFactory<>("dataPrevDevol"));
	col_dataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        col_multa.setCellValueFactory(new PropertyValueFactory<>("multa"));
        //col_funcoes.setCellValueFactory(new PropertyValueFactory<>("info"));
        //col_funcoes1.setCellValueFactory(new PropertyValueFactory<>("edita"));
        //col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        table.setItems(oblist);
    }
}
