package app.diario.matriculas.alterar;

import app.diario.matriculas.principal.Conector;
import app.diario.matriculas.principal.MainController;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AlteraController implements Initializable {

	public static int idVelho, ativoVelho;
	public static String anoVelho;
	private int id, ativo, idAluno, idDisciplina;
	private String aluno, disciplina, ano = "2019";
	private boolean podeAlterar = false;

	@FXML
	private TextField idField;

	@FXML
	private ChoiceBox alunosField;

	@FXML
	private Button botaoCancelar;

	@FXML
	private Button botaoConfirmar;

	@FXML
	private Label errorLabel;

	@FXML
	private ChoiceBox disciplinasField;

	@FXML
	private TextField anoField;

	@FXML
	private RadioButton ativoField;

	public void fecha() {
		AlteraMain.getStage().close();
	}

	@FXML
	private void cancelaAction(ActionEvent event) {
		fecha();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		anoField.setText(anoVelho);
		if (ativoVelho == 1) {
			ativoField.setSelected(true);
		}
		idField.setText(Integer.toString(idVelho));
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM alunos";
			ResultSet res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				lista.add(res.getString("nome"));
			}
			alunosField.setItems(lista);
			ObservableList lista2 = FXCollections.observableArrayList();
			sql = "SELECT * FROM disciplinas";
			ResultSet res2 = con.createStatement().executeQuery(sql);
			while (res2.next()) {
				lista2.add(res2.getString("nome"));
			}
			disciplinasField.setItems(lista2);
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(app.diario.matriculas.inserir.InsereController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(app.diario.matriculas.inserir.InsereController.class.getName()).log(Level.SEVERE, null, ex);
		}
		alunosField.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			try {
				if (confereId(idField.getText())) {
					podeAlterar = true;
				}
			} catch (SQLException ex) {
				Logger.getLogger(app.diario.matriculas.inserir.InsereController.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(app.diario.matriculas.inserir.InsereController.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		disciplinasField.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			try {
				if (confereId(idField.getText())) {
					podeAlterar = true;
				}
			} catch (SQLException ex) {
				Logger.getLogger(app.diario.matriculas.inserir.InsereController.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(app.diario.matriculas.inserir.InsereController.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		idField.textProperty().addListener((obs, valorAntigo, novoValor) -> {
			try {
				confereId(novoValor);
			} catch (SQLException ex) {
				System.out.println(ex);
			} catch (ClassNotFoundException ex) {
				System.out.println(ex);
			} catch (NumberFormatException ex) {
			}
		});
	}

	@FXML
	private void alteraAction(ActionEvent event) throws SQLException, ClassNotFoundException {
		if (podeAlterar) {
			id = Integer.parseInt(idField.getText());
			if (ativoField.isSelected()) {
				ativo = 1;
			} else {
				ativo = 0;
			}
			aluno = alunosField.getValue().toString();
			disciplina = disciplinasField.getValue().toString();
			ano = anoField.getText();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM alunos WHERE nome='" + aluno + "'";
			ResultSet res1 = con.createStatement().executeQuery(sql);
			res1.next();
			sql = "SELECT * FROM disciplinas WHERE nome='" + disciplina + "'";
			ResultSet res2 = con.createStatement().executeQuery(sql);
			res2.next();
			idAluno = res1.getInt("id");
			idDisciplina = res2.getInt("id");
			sql = "UPDATE matriculas SET `id`=" + id + ",`id-alunos`=" + idAluno + ",`id-disciplinas`=" + idDisciplina + ",`ano`=" + Integer.parseInt(ano) + ",`ativo`=" + ativo + " WHERE id=" + idVelho;
			int res = con.createStatement().executeUpdate(sql);
			con.close();
			MainController.updateTab();
			fecha();
		}
	}

	public boolean confereId(String novoValor) throws SQLException, ClassNotFoundException {
		if (Integer.parseInt(novoValor) != idVelho) {
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM matriculas WHERE id=" + Integer.parseInt(novoValor);
			ResultSet res = con.createStatement().executeQuery(sql);
			if (res.next()) {
				errorLabel.setText("ID já existente");
				podeAlterar = false;
			} else {
				errorLabel.setText("");
				podeAlterar = true;
			}
		}
		else{
			errorLabel.setText("");
			podeAlterar = true;
		}
		return podeAlterar;
	}

	public static int getIdVelho() {
		return idVelho;
	}

	public static void setIdVelho(int idVelho) {
		AlteraController.idVelho = idVelho;
	}

	public static int getAtivoVelho() {
		return ativoVelho;
	}

	public static void setAtivoVelho(int ativoVelho) {
		AlteraController.ativoVelho = ativoVelho;
	}

	public static String getAnoVelho() {
		return anoVelho;
	}

	public static void setAnoVelho(String anoVelho) {
		AlteraController.anoVelho = anoVelho;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public boolean isPodeAlterar() {
		return podeAlterar;
	}

	public void setPodeAlterar(boolean podeAlterar) {
		this.podeAlterar = podeAlterar;
	}

}
