package app.diario.matriculas.inserir;

import app.diario.matriculas.principal.MainController;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class InsereController implements Initializable {

	private int id, ativo, idAluno, idDisciplina;
	private String aluno, disciplina, ano = "2019";
	private Boolean podeInserir = false;

	@FXML
	private Button botaoConfirmar;

	@FXML
	private Button botaoCancelar;

	@FXML
	private TextField idField;

	@FXML
	private Label errorLabel;

	@FXML
	private ChoiceBox alunosField;

	@FXML
	private ChoiceBox disciplinasField;

	@FXML
	private TextField anoField;

	@FXML
	private RadioButton ativoField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		anoField.setText(ano);
		ativoField.setSelected(true);
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = ConnectionFactory.getDiario();
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
			Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
		}
		idField.setText(Integer.toString(MainController.fixedId));
		alunosField.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			try {
				if (confereId(idField.getText())) {
					podeInserir = true;
				}
			} catch (SQLException ex) {
				Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		disciplinasField.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			try {
				if (confereId(idField.getText())) {
					podeInserir = true;
				}
			} catch (SQLException ex) {
				Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
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

	public void fecha() {
		InsereMain.getStage().close();
	}

	@FXML
	private void cancelaAction(ActionEvent event) {
		fecha();
	}

	@FXML
	private void insereAction(ActionEvent event) throws SQLException, ClassNotFoundException {
		if (podeInserir) {
			id = Integer.parseInt(idField.getText());
			if (ativoField.isSelected()) {
				ativo = 1;
			} else {
				ativo = 0;
			}
			aluno = alunosField.getValue().toString();
			disciplina = disciplinasField.getValue().toString();
			ano = anoField.getText();
			Connection con = ConnectionFactory.getDiario();
			String sql = "SELECT * FROM alunos WHERE nome='" + aluno + "'";
			ResultSet res1 = con.createStatement().executeQuery(sql);
			res1.next();
			sql = "SELECT * FROM disciplinas WHERE nome='" + disciplina + "'";
			ResultSet res2 = con.createStatement().executeQuery(sql);
			res2.next();
			idAluno = res1.getInt("id");
			idDisciplina = res2.getInt("id");
			sql = "INSERT INTO matriculas (`id`, `id-alunos`, `id-disciplinas`, `ano`, `ativo`) VALUES ("+id+","+idAluno+","+idDisciplina+","+Integer.parseInt(ano)+","+ativo+")";
			int res = con.createStatement().executeUpdate(sql);
			con.close();
			MainController.updateTab();
			fecha();
		}
	}

	public boolean confereId(String novoValor) throws SQLException, ClassNotFoundException {
		Connection con = ConnectionFactory.getDiario();
		String sql = "SELECT * FROM matriculas WHERE id=" + Integer.parseInt(novoValor);
		ResultSet res = con.createStatement().executeQuery(sql);
		if (res.next()) {
			errorLabel.setText("ID já existente");
			podeInserir = false;
		} else {
			errorLabel.setText("");
			podeInserir = true;
		}
		return podeInserir;
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

	public Boolean getPodeInserir() {
		return podeInserir;
	}

	public void setPodeInserir(Boolean podeInserir) {
		this.podeInserir = podeInserir;
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

}
