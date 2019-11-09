package principal;

import inserir.InsereMain;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController implements Initializable {

	private final String URL_CONSULTAR = "http://localhost:8080/app/diario/turmas/consultar";
	private final String URL_INSERIR = "http://localhost:8080/app/diario/turmas/inserir";
	private final String URL_ALTERAR = "http://localhost:8080/app/diario/turmas/alterar";
	private final String URL_DELETAR = "http://localhost:8080/app/diario/turmas/deletar";

	public static ObservableList<Turma> tabList = FXCollections.observableArrayList();
	
	@FXML
	public TableView<Turma> tab;

	@FXML
	public TableColumn<Turma, Integer> colId;

	@FXML
	public TableColumn<Turma, Integer> colIdCursos;

	@FXML
	public TableColumn<Turma, String> colNome;

	@FXML
	public TableColumn<Turma, String> colAcoes;
	
	@FXML
	public TableColumn<Turma, String> colAcoes2;

	@FXML
	private Button botaoAdicionar;

	@FXML
	private Button botaoEditar;

	private static TableView<Turma> table;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			table = tab;
			createTab();
		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public static void setTable(){
		table.setItems(tabList);
	}
	
	public static void updateTab() throws SQLException, ClassNotFoundException {
		tabList.clear();
		Connection con = Conector.conectar();
		String sql = "SELECT * FROM turmas";
		ResultSet res = con.createStatement().executeQuery(sql);
		while (res.next()) {
			tabList.add(new Turma(res.getInt("id"), res.getInt("id-cursos"), res.getString("nome"), new Button("DELETAR"), new Button("ALTERAR")));
		}
		setTable();
		con.close();
	}

	public void createTab() throws SQLException, ClassNotFoundException {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colIdCursos.setCellValueFactory(new PropertyValueFactory<>("idCursos"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colAcoes.setCellValueFactory(new PropertyValueFactory<>("deletaBtn"));
		colAcoes2.setCellValueFactory(new PropertyValueFactory<>("alteraBtn"));
		System.out.println(colAcoes);
		updateTab();
	}

	@FXML
	private void adicionaAction(ActionEvent event) throws SQLException, ClassNotFoundException {
		InsereMain im = new InsereMain();
		try {
			im.start(new Stage());
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
