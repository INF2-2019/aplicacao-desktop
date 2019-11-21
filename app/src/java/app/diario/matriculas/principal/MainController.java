package app.diario.matriculas.principal;

import app.diario.telatransicao.MainTelaTransicaoDiario;
import app.diario.matriculas.consultar.ConsultaController;
import app.diario.matriculas.consultar.ConsultaMain;
import app.diario.matriculas.inserir.InsereMain;
import app.utils.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	
	@FXML
	private TextField pesquisaField;

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
		Connection con = ConnectionFactory.getDiario();
		String sql = "SELECT * FROM matriculas";
		ResultSet res = con.createStatement().executeQuery(sql);
		while (res.next()) {
			sql = "SELECT * FROM alunos WHERE id=" + res.getLong("id-alunos");
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
	
	@FXML
	public void pesquisaAction() {
		FilteredList<Matricula> filtro = new FilteredList<Matricula>(tabList);
		String s = "Sim", n = "Não";
		pesquisaField.textProperty().addListener((observable, oldValue, newValue) -> {
			filtro.setPredicate(matricula -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String digitado = newValue.toLowerCase();

				if (Integer.toString(matricula.getId()).contains(digitado)) {
					return true;
				} else if (matricula.getAluno().toLowerCase().contains(digitado)) {
					return true;
				} else if (matricula.getDisciplina().toLowerCase().contains(digitado)) {
					return true;
				} else if (matricula.getAno().toLowerCase().contains(digitado)) {
					return true;
				}
				
				return false;
			});

			SortedList<Matricula> sortedList = new SortedList<>(filtro);
			sortedList.comparatorProperty().bind(tab.comparatorProperty());
			tab.setItems(sortedList);
		});
	}
	
	@FXML
	private void voltarAction(ActionEvent event) {
		fecha();
	}
	
	public void fecha(){
		MainMatriculas.getStage().close();
		MainTelaTransicaoDiario mt = new MainTelaTransicaoDiario();
		try {
			mt.start(new Stage());
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
