package app.biblioteca.acervo.principal;

import app.biblioteca.telatransicao.MainTelaTransicaoBiblioteca;
        
import app.biblioteca.acervo.inserir.InsereMain;
import app.utils.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Indra Matsiendra & Amanda Jacomette
 */
public class TableController implements Initializable {
    
    @FXML
    private AnchorPane AnchorPane;
    
    @FXML
    private TableView<Acervo> tabelaAcervo;
    
    @FXML
    private TableColumn<Acervo, Integer> colunaId;

    @FXML
    private TableColumn<Acervo, Integer> colunaIdCampi;

    @FXML
    private TableColumn<Acervo, String> colunaNome;

    @FXML
    private TableColumn<Acervo, String> colunaTipo;

    @FXML
    private TableColumn<Acervo, String> colunaLocal;

    @FXML
    private TableColumn<Acervo, Integer> colunaAno;

    @FXML
    private TableColumn<Acervo, String> colunaEditora;

    @FXML
    private TableColumn<Acervo, Integer> colunaPaginas;

    @FXML
    private TableColumn<Acervo, Button> colunaEditar;

    @FXML
    private TableColumn<Acervo, Button> colunaDeletar;

    @FXML
    private TableColumn<Acervo, Button> colunaInfo;

    
    @FXML
    private Button botaoAdicionar;
    
    private ObservableList<Acervo> observableListAcervo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consultarBD();
    }    
    
    
    @FXML
    private void insere(javafx.event.ActionEvent event) {
        InsereMain mainInsere = new InsereMain();
        try {
            mainInsere.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void voltar(javafx.event.ActionEvent event) {
        MainTelaTransicaoBiblioteca main = new MainTelaTransicaoBiblioteca();
        try {
            main.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ListagemMain.getStage().close();
    }
    
    public void deleta(String id,String tipo){
         try {
            Connection connection = ConnectionFactory.getBiblioteca();
            PreparedStatement stmt = connection.prepareStatement("delete " +
                    "from acervo where id="+id);
            stmt.execute();
            stmt.close();
	    /*esse switch serve também como forma de verificar se o tipo é permitido*/
	    switch(tipo.toLowerCase())
	    {
		case "academicos":
		case "livros":
		case "midias":
		case "periodicos":
                PreparedStatement stmt2 = connection.prepareStatement("delete " +
              	"from "+tipo.toLowerCase()+" where `id-acervo`= ?");
	        stmt2.setInt(1,Integer.parseInt(id));
		stmt2.execute();
        	stmt2.close();
	    }
	    connection.close();
        } catch (SQLException e) {
             System.out.println(""+e.getMessage());
             //throw new RuntimeException(e);
        }
    }
    
    public void consultarBD(){
        try {
            Connection con = ConnectionFactory.getBiblioteca();
            
            ResultSet rs = con.createStatement().executeQuery("select * from acervo");
            observableListAcervo = FXCollections.observableArrayList();
            
            while(rs.next()){
                Button edita=new Button("Editar");
                edita.setId("edita");
                Button deleta=new Button("Deletar");
                deleta.setId("deleta");
                Button info=new Button("Info");
                info.setId("info");
                observableListAcervo.add(new Acervo(rs.getInt("id"),rs.getInt("id-campi"),rs.getString("nome"),rs.getString("tipo"), rs.getString("local"), rs.getInt("ano"), rs.getString("editora"), rs.getInt("paginas"),edita,deleta, info));
            }
            tabelaAcervo.setItems(observableListAcervo);
            colunaId.setCellValueFactory(new PropertyValueFactory("id"));
            colunaIdCampi.setCellValueFactory(new PropertyValueFactory("idCampi"));
            colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
            colunaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
            colunaLocal.setCellValueFactory(new PropertyValueFactory("local"));
            colunaAno.setCellValueFactory(new PropertyValueFactory("ano"));
            colunaEditora.setCellValueFactory(new PropertyValueFactory("editora"));
            colunaPaginas.setCellValueFactory(new PropertyValueFactory("paginas"));
            colunaEditar.setCellValueFactory(new PropertyValueFactory("edita"));
            colunaDeletar.setCellValueFactory(new PropertyValueFactory("deleta"));
            colunaInfo.setCellValueFactory(new PropertyValueFactory("info"));
           tabelaAcervo.getColumns().setAll(colunaId,colunaIdCampi,colunaNome,colunaTipo,colunaLocal,colunaAno,colunaEditora,colunaPaginas,colunaEditar,colunaDeletar,colunaInfo);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}