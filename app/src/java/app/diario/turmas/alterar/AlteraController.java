package app.diario.turmas.alterar;

import PacoteInsere.InsereController;
import app.diario.turmas.principal.Conector;
import app.diario.turmas.principal.MainController;
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
import javafx.scene.control.TextField;

public class AlteraController implements Initializable {

	public static int idVelho;
	private int id, idCurso;
	private String nome, curso;
	private boolean podeAlterar = false;

	@FXML
	private TextField idField;

	@FXML
	private ChoiceBox cursoField;

	@FXML
	private TextField nomeField;

	@FXML
	private Button botaoCancelar;

	@FXML
	private Button botaoConfirmar;

	@FXML
	private Label errorLabel;

	public void fecha() {
		AlteraMain.getStage().close();
	}

	@FXML
	private void cancelaAction(ActionEvent event) {
		fecha();
	}

	@FXML
	private void alteraAction(ActionEvent event) throws SQLException, ClassNotFoundException {
		if (podeAlterar) {
			id = Integer.parseInt(idField.getText());
			curso = cursoField.getValue().toString();
			nome = nomeField.getText();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM cursos WHERE nome='" + curso + "'";
			ResultSet res1 = con.createStatement().executeQuery(sql);
			res1.next();
			idCurso = res1.getInt("id");
			sql = "UPDATE turmas SET `id` = " + id + ", `id-cursos` = " + idCurso + ", `nome` = '" + nome + "' WHERE id = " + idVelho;;
			int res = con.createStatement().executeUpdate(sql);
			con.close();
			MainController.updateTab();
			fecha();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getIdVelho() {
		return idVelho;
	}

	public static void setIdVelho(int iv) {
		idVelho = iv;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM cursos";
			ResultSet res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				lista.add(res.getString("nome"));
			}
			con.close();
			cursoField.setItems(lista);
		} catch (SQLException ex) {
			Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
		}
		idField.setText(Integer.toString(idVelho));
		cursoField.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			try {
				if(confereId(idField.getText())) podeAlterar = true;
			} catch (SQLException ex) {
				System.out.println(ex);
			} catch (ClassNotFoundException ex) {
				System.out.println(ex);
			} catch (NumberFormatException ex) {
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

	public boolean confereId(String novoValor) throws SQLException, ClassNotFoundException {
		if (Integer.parseInt(novoValor) != idVelho) {
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM turmas WHERE id=" + Integer.parseInt(novoValor);
			ResultSet res = con.createStatement().executeQuery(sql);
			if (res.next()) {
				errorLabel.setText("ID já existente");
				podeAlterar = false;
			} else {
				errorLabel.setText("");
				podeAlterar = true;
			}
		} else {
			errorLabel.setText("");
			podeAlterar = true;
		}
		return podeAlterar;
	}

}
