package app.diario.matriculas.principal;

import app.diario.matriculas.consultar.ConsultaController;
import app.diario.matriculas.consultar.ConsultaMain;
import app.diario.matriculas.inserir.InsereMain;
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

public class MainController implements Initializable {

	public static ObservableList<Matricula> tabList = FXCollections.observableArrayList();

	public static int fixedId = 0;
	private static int maior;

	@FXML
	public TableView<Matricula> tab;

	@FXML
	private Button botaoAdicionar;

	@FXML
	private Button botaoInfo;

	private static TableView<Matricula> table;

	@FXML
	private TableColumn<Matricula, Integer> colId;

	@FXML
	private TableColumn<Matricula, String> colAlunos;

	@FXML
	private TableColumn<Matricula, String> colDisciplinas;

	@FXML
	private TableColumn<Matricula, String> colAno;

	@FXML
	private TableColumn<Matricula, Integer> colAtivo;

	@FXML
	private TableColumn<Matricula, Button> colAcoes;

	@FXML
	private TableColumn<Matricula, Button> colAcoes2;

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
		String sql = "SELECT * FROM matriculas";
		ResultSet res = con.createStatement().executeQuery(sql);
		while (res.next()) {
			sql = "SELECT * FROM alunos WHERE id=" + res.getInt("id-alunos");
			ResultSet res2 = con.createStatement().executeQuery(sql);
			sql = "SELECT * FROM disciplinas WHERE id=" + res.getInt("id-disciplinas");
			ResultSet res3 = con.createStatement().executeQuery(sql);
			String ativo;
			if (res.getInt("ativo") == 1) {
				ativo = "Sim";
			} else {
				ativo = "Não";
			}
			if (res2.next() && res3.next()) {
				tabList.add(new Matricula(res.getInt("id"), res2.getString("nome"), res3.getString("nome"), res.getString("ano"), ativo, new Button("DELETAR"), new Button("ALTERAR")));
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
		colAlunos.setCellValueFactory(new PropertyValueFactory<>("aluno"));
		colDisciplinas.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
		colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
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
