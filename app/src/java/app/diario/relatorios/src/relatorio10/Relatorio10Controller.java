package relatorio10;

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
import principal.Conector;

public class Relatorio10Controller implements Initializable {

	private static String nome;
	private int idAluno, idMatricula;
	private double nota;

	@FXML
	private TableView tab;

	@FXML
	private TableColumn colEtapas;

	@FXML
	private TableColumn colDisciplinas;

	@FXML
	private TableColumn colNotas;

	@FXML
	private Label nomeAluno;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM alunos WHERE nome=" + nome; // Procura qual o id do aluno selecionado
			ResultSet res = con.createStatement().executeQuery(sql);
			idAluno = res.getInt("id");

			sql = "SELECT * FROM matriculas WHERE id-alunos=" + idAluno; // Procura qual a matricula do aluno 
			res = con.createStatement().executeQuery(sql);
			idMatricula = res.getInt("id");

			sql = "SELECT * FROM diario WHERE id-matriculas=" + idMatricula; // Procura a nota no diario pelo numero de matricula
			res = con.createStatement().executeQuery(sql);
			nota = res.getDouble("nota");
			idMatricula = res.getInt("id");

		} catch (SQLException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		}*/
			System.out.println("fazer relatorio");
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		Relatorio10Controller.nome = nome;
	}

}
