package app.diario.professores.principal;

import app.diario.professores.deletar.MainDeleta;
import app.diario.professores.info.InfoController;
import app.diario.professores.insere.InsereMain;
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
import app.diario.professores.info.InfoMain;
import app.utils.ConnectionFactory;
import app.diario.telatransicao.MainTelaTransicaoDiario;

/**
 *
 * @author Nikolas Victor
 * @author Jonata Novais
 */
public class TableController implements Initializable {
    
    @FXML
    private TableView<Professor> tabelaProfessores;
    

    @FXML
    private TableColumn<Professor, Integer> colunaId;

    @FXML
    private TableColumn<Professor, Integer> colunaIdDpto;

    @FXML
    private TableColumn<Professor, String> colunaNome;
    
    @FXML
    private TableColumn<Professor, Button> colunaAcoes1;

    @FXML
    private TableColumn<Professor, Button> colunaAcoes2;
    
    @FXML
    private TableColumn<Professor, Button> colunaAcoes3;
            
    @FXML
    private Button botaoAdicionar;
	
    @FXML
    private Button voltar;
    
    
    
    private List<Professor> listaProfessores = new ArrayList();
    
    private ObservableList<Professor> observableListProfessor;
   
    
    public static ObservableList<Professor> oblist = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consultarBD();
        criaTabela();
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
	
	@FXML
    private void Voltar(javafx.event.ActionEvent event) {
		MainTelaTransicaoDiario voltar = new MainTelaTransicaoDiario();
        try {
            voltar.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
		ProfessorMain.getStage().close();
    }
    
    
    public void deleta(String id){
         try {
            Connection connection = ConnectionFactory.getDiario();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM professores WHERE id="+id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }
    
    static public void consultarBD(){
        try {
            Connection con = ConnectionFactory.getDiario();
            
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM professores");
            oblist = FXCollections.observableArrayList();
            
            while(rs.next()){
                oblist.add(new Professor(rs.getInt("id"),rs.getInt("id-depto"),rs.getString("nome"),rs.getString("titulacao"), rs.getString("email"), rs.getString("senha"),new Button("EDITAR"),new Button("DELETAR"), new Button("INFO")));
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void criaTabela(){
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaIdDpto.setCellValueFactory(new PropertyValueFactory<>("idDpto"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaAcoes1.setCellValueFactory(new PropertyValueFactory<>("info"));
        colunaAcoes2.setCellValueFactory(new PropertyValueFactory<>("edita"));
        colunaAcoes3.setCellValueFactory(new PropertyValueFactory<>("deleta"));
        
          
        tabelaProfessores.setItems(oblist);
    }
    
    public void refresh(){
        consultarBD();
        criaTabela();
    }
    
}
