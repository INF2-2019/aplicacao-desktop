package app.diario.relatorios.relatorio10;

import app.diario.turmas.principal.Conector;
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

public class Relatorio10Controller implements Initializable {

	private static String nomeAluno, nomeDisciplina;
	private int idAluno, idDisciplina, idMatricula;
	private double notaFinal = 0, et1 = 0, et2 = 0, et3 = 0, et4 = 0;

	@FXML
	private TableView<Relatorio10Model> tab;

	@FXML
	private TableColumn<Relatorio10Model, String> colDisciplinas;

	@FXML
	private TableColumn<Relatorio10Model, Double> colNotas;

	@FXML
	private TableColumn<Relatorio10Model, Double> colEtapas1;

	@FXML
	private TableColumn<Relatorio10Model, Double> colEtapas2;

	@FXML
	private TableColumn<Relatorio10Model, Double> colEtapas3;

	@FXML
	private TableColumn<Relatorio10Model, Double> colEtapas4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colDisciplinas.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
		colEtapas1.setCellValueFactory(new PropertyValueFactory<>("etapa1"));
		colEtapas2.setCellValueFactory(new PropertyValueFactory<>("etapa2"));
		colEtapas3.setCellValueFactory(new PropertyValueFactory<>("etapa3"));
		colEtapas4.setCellValueFactory(new PropertyValueFactory<>("etapa4"));
		colNotas.setCellValueFactory(new PropertyValueFactory<>("nota"));
		try {
			ObservableList<Relatorio10Model> lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM alunos WHERE nome='" + nomeAluno + "'"; // Pega o id do aluno escolhido
			ResultSet res = con.createStatement().executeQuery(sql);
			if (res.next()) {
				idAluno = res.getInt("id");
			}

			sql = "SELECT * FROM matriculas WHERE `id-alunos`=" + idAluno; // Pega as matrículas relacionadas ao aluno
			ResultSet res2 = con.createStatement().executeQuery(sql);
			while (res2.next()) {
				idMatricula = res2.getInt("id");
				sql = "SELECT * FROM disciplinas WHERE id=" + res2.getInt("id-disciplinas"); // Pega as disciplinas relacionadas às matriculas do aluno
				ResultSet res3 = con.createStatement().executeQuery(sql);
				if (res3.next()) {
					nomeDisciplina = res3.getString("nome");
				}

				sql = "SELECT * FROM conteudos WHERE `id-disciplinas`=" + res2.getInt("id-disciplinas"); // Pega os conteudos relacionados à disciplina
				ResultSet res4 = con.createStatement().executeQuery(sql);
				et1 = et2 = et3 = et4 = 0;
				notaFinal = 0;
				while (res4.next()) {
					if (res4.getInt("id-etapas") == 1) {
						sql = "SELECT * FROM diario WHERE `id-conteudos`=" + res4.getInt("id") + " AND `id-matriculas`=" + idMatricula; // Pega o diario associado à matricula e ao conteúdo
						ResultSet res5 = con.createStatement().executeQuery(sql);
						while (res5.next()) {
							et1 += res5.getDouble("nota");
						}
					} else if (res4.getInt("id-etapas") == 2) {
						sql = "SELECT * FROM diario WHERE `id-conteudos`=" + res4.getInt("id") + " AND `id-matriculas`=" + idMatricula; // Pega o diario associado à matricula e ao conteúdo
						ResultSet res5 = con.createStatement().executeQuery(sql);
						while (res5.next()) {
							et2 += res5.getDouble("nota");
						}
					} else if (res4.getInt("id-etapas") == 3) {
						sql = "SELECT * FROM diario WHERE `id-conteudos`=" + res4.getInt("id") + " AND `id-matriculas`=" + idMatricula; // Pega o diario associado à matricula e ao conteúdo
						ResultSet res5 = con.createStatement().executeQuery(sql);
						while (res5.next()) {
							et3 += res5.getDouble("nota");
						}
					} else if (res4.getInt("id-etapas") == 4) {
						sql = "SELECT * FROM diario WHERE `id-conteudos`=" + res4.getInt("id") + " AND `id-matriculas`=" + idMatricula; // Pega o diario associado à matricula e ao conteúdo
						ResultSet res5 = con.createStatement().executeQuery(sql);
						while (res5.next()) {
							et4 += res5.getDouble("nota");
						}
					}
				}
				notaFinal = et1 + et2 + et3 + et4;
				lista.add(new Relatorio10Model(nomeDisciplina, notaFinal, et1, et2, et3, et4));
				tab.setItems(lista);
			}

		} catch (SQLException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static String getNomeAluno() {
		return nomeAluno;
	}

	public static void setNomeAluno(String nomeAluno) {
		Relatorio10Controller.nomeAluno = nomeAluno;
	}

}
