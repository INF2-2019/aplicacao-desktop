package app.diario.etapas.Principal;

import app.diario.etapas.PacoteConsulta.ConsultaMain;
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
import app.diario.etapas.PacoteInsere.InsereMain;
import app.diario.telatransicao.MainTelaTransicaoDiario;
import java.io.IOException;
import java.sql.PreparedStatement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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
    private TableColumn<ModelTable, Integer> col_ano;

    @FXML
    private TableColumn<ModelTable, Double> col_valor;
    
    @FXML
    private TableColumn<ModelTable, Button> col_funcoes;

    @FXML
    private TableColumn<ModelTable, Button> col_funcoes1;
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    private void Insere(javafx.event.ActionEvent event) {
        InsereMain insere = new InsereMain();
        try {
            insere.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void Consulta(javafx.event.ActionEvent event) {
        ConsultaMain consulta = new ConsultaMain();
        try {
            consulta.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void Recarrega(javafx.event.ActionEvent event) {
        oblist.clear();
        consultarBD("select * from etapas");
        criaTabela();
    }
    
    public void deleta(int id){
        try {
            Connection connection = DbConnector.getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from etapas where id="+id);
            stmt.execute();
            stmt.close();
			consultarBD("select * from etapas");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    static ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultarBD("select * from etapas");
        criaTabela();
        
    }
    public void refresh(){
        criaTabela();
    }
    
    static public void consultarBD(String sql){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery(sql);
            oblist = FXCollections.observableArrayList();
            while(rs.next()){
                oblist.add(new ModelTable(rs.getInt("id"),rs.getInt("ano"),rs.getDouble("valor"),new Button("EDITAR"),new Button("DELETAR")));
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void criaTabela(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ano.setCellValueFactory(new PropertyValueFactory<>("ano"));
        col_valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        col_funcoes.setCellValueFactory(new PropertyValueFactory<>("edita"));
        col_funcoes1.setCellValueFactory(new PropertyValueFactory<>("deleta"));
        
        table.setItems(oblist);
    }
    
    @FXML
     public void voltar() {
		MainTelaTransicaoDiario transi = new MainTelaTransicaoDiario();
        try {
			transi.start(new Stage());
            MainEtapas.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

}
