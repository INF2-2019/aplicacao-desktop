package relatorio11;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import principal.Conector;

public class Relatorio11Controller implements Initializable {

	private static String disciplina;
	private static int etapa, idDisciplina;

	@FXML
	private TableView tab;

	@FXML
	private Label nomeDisciplinaEtapa;

	@FXML
	private TableColumn colConteudos;

	@FXML
	private TableColumn colData;

	@FXML
	private TableColumn colValor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colConteudos.setCellValueFactory(new PropertyValueFactory<>("conteudos"));
		colData.setCellValueFactory(new PropertyValueFactory<>("data"));
		colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		nomeDisciplinaEtapa.setText("Disciplina \"" + disciplina + "\", etapa " + etapa);
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM disciplinas";
			ResultSet res = con.createStatement().executeQuery(sql);
			while(res.next()){
				if(res.getString("nome") == disciplina) idDisciplina = res.getInt("id");
			}
			System.out.println(idDisciplina);
			
			sql = "SELECT * FROM conteudos WHERE `id-etapas`=" + etapa + " AND `id-disciplinas`=" + idDisciplina;
			res = con.createStatement().executeQuery(sql);
			while(res.next()){
				lista.add(res.getString("conteudos"));
				lista.add(res.getDate("data"));
				lista.add(res.getDouble("valor"));
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
