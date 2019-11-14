package app.diario.turmas.principal;

import app.diario.turmas.consultar.ConsultaController;
import app.diario.turmas.consultar.ConsultaMain;
import app.diario.turmas.inserir.InsereMain;
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
import javafx.stage.Stage;
import principal.Conector;

public class MainController implements Initializable {

	private final String URL_CONSULTAR = "http://localhost:8080/app/diario/turmas/consultar";
	private final String URL_INSERIR = "http://localhost:8080/app/diario/turmas/inserir";
	private final String URL_ALTERAR = "http://localhost:8080/app/diario/turmas/alterar";
	private final String URL_DELETAR = "http://localhost:8080/app/diario/turmas/deletar";

	public static ObservableList<Turma> tabList = FXCollections.observableArrayList();

	public static int fixedId = 0;
	private static int maior;

	@FXML
	public TableView<Turma> tab;

	@FXML
	public TableColumn<Turma, Integer> colId;

	@FXML
	public TableColumn<Turma, String> colCursos;

	@FXML
	public TableColumn<Turma, String> colNome;

	@FXML
	public TableColumn<Turma, Button> colAcoes;

	@FXML
	public TableColumn<Turma, Button> colAcoes2;

	@FXML
	private Button botaoAdicionar;

	@FXML
	private Button botaoInfo;

	private static TableView<Turma> table;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			table = tab;
			createTab();
		} catch (SQLException ex) {
			Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void setTable() {
		table.setItems(tabList);
	}

	public static void updateTab() throws SQLException, ClassNotFoundException {
		maior = 0;
		tabList.clear();
		Connection con = Conector.conectar();
		String sql = "SELECT * FROM turmas";
		ResultSet res = con.createStatement().executeQuery(sql);
		while (res.next()) {
			sql = "SELECT * FROM cursos WHERE id=" + res.getInt("id-cursos");
			ResultSet res2 = con.createStatement().executeQuery(sql);
			if (res2.next()) {
				tabList.add(new Turma(res.getInt("id"), res2.getString("nome"), res.getString("nome"), new Button("DELETAR"), new Button("ALTERAR")));
				if (maior < res.getInt("id")) {
					maior = res.getInt("id");
				}
			}
		}
		fixedId = maior + 1;
		setTable();
		con.close();
	}

	public void createTab() throws SQLException, ClassNotFoundException {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCursos.setCellValueFactory(new PropertyValueFactory<>("cursos"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colAcoes.setCellValueFactory(new PropertyValueFactory<>("deletaBtn"));
		colAcoes2.setCellValueFactory(new PropertyValueFactory<>("alteraBtn"));
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

	@FXML
	private void consultaAction(ActionEvent event) {
		ConsultaMain cm = new ConsultaMain();
		try {
			cm.start(new Stage());
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
