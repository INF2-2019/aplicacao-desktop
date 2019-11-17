package app.diario.matriculas.consultar;

import app.diario.matriculas.principal.Matricula;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultaController implements Initializable {

	@FXML
	private TableView<Matricula> tab;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colAlunos.setCellValueFactory(new PropertyValueFactory<>("aluno"));
		colDisciplinas.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
		colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		colAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));

		Connection con;
		ObservableList<Matricula> tabList;
		try {
			tabList = FXCollections.observableArrayList();
			con = ConnectionFactory.getDiario();
			String sql = "SELECT * FROM matriculas";
			ResultSet res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				sql = "SELECT * FROM alunos WHERE id=" + res.getInt("id-alunos");
				ResultSet res2 = con.createStatement().executeQuery(sql);
				sql = "SELECT * FROM disciplinas WHERE id=" + res.getInt("id-disciplinas");
				ResultSet res3 = con.createStatement().executeQuery(sql);
				res2.next();
				res3.next();
				String ativo;
				if (res.getInt("ativo") == 1) {
					ativo = "Sim";
				} else {
					ativo = "Não";
				}
				tabList.add(new Matricula(res.getInt("id"), res2.getString("nome"), res3.getString("nome"), res.getString("ano"), ativo));
			}
			tab.setItems(tabList);
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
