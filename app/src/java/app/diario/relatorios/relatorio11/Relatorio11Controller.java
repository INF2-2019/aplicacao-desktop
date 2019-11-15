package app.diario.relatorios.relatorio11;

import app.diario.turmas.principal.Conector;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Relatorio11Controller implements Initializable {

	private static String disciplina;
	private static int etapa, idDisciplina;

	@FXML
	private TableView<Relatorio11Model> tab;

	@FXML
	private TableColumn<Relatorio11Model, String> colConteudos;

	@FXML
	private TableColumn<Relatorio11Model, String> colData;

	@FXML
	private TableColumn<Relatorio11Model, Double> colValor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colConteudos.setCellValueFactory(new PropertyValueFactory<>("conteudo"));
		colData.setCellValueFactory(new PropertyValueFactory<>("data"));
		colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		try {
			ObservableList<Relatorio11Model> lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM disciplinas WHERE nome='" + disciplina + "'";
			ResultSet res = con.createStatement().executeQuery(sql);
			if (res.next()) {
				idDisciplina = res.getInt("id");
			}

			sql = "SELECT * FROM conteudos WHERE `id-etapas`=" + etapa + " AND `id-disciplinas`=" + idDisciplina;
			ResultSet res2 = con.createStatement().executeQuery(sql);
			while (res2.next()) {
				lista.add(new Relatorio11Model(res2.getString("conteudos"), res2.getDate("data").toString(), res2.getDouble("valor")));
			}
			tab.setItems(lista);
		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}

	public static String getDisciplina() {
		return disciplina;
	}

	public static void setDisciplina(String disciplina) {
		Relatorio11Controller.disciplina = disciplina;
	}

	public static int getEtapa() {
		return etapa;
	}

	public static void setEtapa(int etapa) {
		Relatorio11Controller.etapa = etapa;
	}

}
