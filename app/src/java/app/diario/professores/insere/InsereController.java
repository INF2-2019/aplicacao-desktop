package app.diario.professores.insere;

import app.diario.professores.principal.TableController;
import app.utils.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Nikolas Victor
 * @author Jonata Novais
 */
public class InsereController implements Initializable {
    
    @FXML
    private TextField nomeInput;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Label idDeptoLabel;

    @FXML
    private TextField idInput;

    @FXML
    private Label idLabel;

    @FXML
    private Button botaoSalvar;

    @FXML
    private ChoiceBox<String> idDeptoInput;
    
    @FXML
    private TextField seapInput;

    @FXML
    private Label titulacaoLabel;

    @FXML
    private ChoiceBox<String> tituInput;

    @FXML
    private Label seapLabel;

    @FXML
    private Label nomeLabel;
    
    @FXML
    private Label senhaLabel;
    
    @FXML
    private Label emailLabel;
    
    @FXML
    private TextField emailInput;
    
    @FXML
    private TextField senhaInput;
    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
	
	private HashMap<String, Integer> deptosMap;
        
    @FXML
    void acaoSalvar(ActionEvent event) throws SQLException {
        Connection con = ConnectionFactory.getDiario();

        // cria um preparedStatement
        String sql = "insert into professores" +
                " (`id`,`id-depto`, `nome`, `titulacao`, `email`, `senha`)" +
                " values (?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);

        // preenche os valores
        stmt.setInt(2,deptosMap.get(idDeptoInput.getValue()));
        stmt.setString(3,nomeInput.getText());
        stmt.setString(1,seapInput.getText());
        stmt.setString(4,tituInput.getValue());
        stmt.setString(5,emailInput.getText());
        stmt.setString(6,senhaInput.getText());
        stmt.execute();
        stmt.close();
        con.close();
        fecha();

    }
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		
		deptosMap = new HashMap<>();
        
        ObservableList<String> oblistTitulacao = FXCollections.observableArrayList();
        oblistTitulacao.add("M");
        oblistTitulacao.add("D");
        oblistTitulacao.add("G");
        oblistTitulacao.add("E");
        tituInput.setItems(oblistTitulacao);
        tituInput.setValue("G");
        
        try {
			ObservableList<String> oblistDeptos = FXCollections.observableArrayList();
			atualizarDeptos(oblistDeptos);
			idDeptoInput.setItems(oblistDeptos);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
    }
    
    private void atualizarDeptos(ObservableList<String> oblist) throws SQLException {
        String query = "SELECT * FROM `departamentos`";
        Connection con = ConnectionFactory.getDiario();
        ResultSet rs = con.createStatement().executeQuery(query);
		while(rs.next()) {
			String nome = rs.getString("nome");
			deptosMap.put(nome, (Integer) rs.getInt("id"));
			oblist.add(nome);
		}
    }
    
    public void fecha(){
        InsereMain.getStage().close();
    }
    
    @FXML
    private void Insere(javafx.event.ActionEvent event){
        
    }
}
