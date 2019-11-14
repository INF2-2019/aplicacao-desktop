package app.diario.turmas.inserir;

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
import principal.Conector;

public class InsereController implements Initializable {

	private int id, idCurso;
	private String nome, curso;
	private boolean podeInserir = false;

	@FXML
	private Button botaoConfirmar;

	@FXML
	private Button botaoCancelar;

	@FXML
	private TextField idField;

	@FXML
	private ChoiceBox cursosField;

	@FXML
	private TextField nomeField;

	@FXML
	private Label errorLabel;

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
			cursosField.setItems(lista);
		} catch (SQLException ex) {
			Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(InsereController.class.getName()).log(Level.SEVERE, null, ex);
		}
		idField.setText(Integer.toString(MainController.fixedId));
		cursosField.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			try {
				if(confereId(idField.getText())) podeInserir = true;
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
			curso = cursosField.getValue().toString();
			nome = nomeField.getText();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM cursos WHERE nome='" + curso + "'";
			ResultSet res1 = con.createStatement().executeQuery(sql);
			res1.next();
			idCurso = res1.getInt("id");
			sql = "INSERT INTO turmas (`id`, `id-cursos`, `nome`) VALUES (" + id + "," + idCurso + ",'" + nome + "')";
			int res = con.createStatement().executeUpdate(sql);
			con.close();
			MainController.updateTab();
			fecha();
		}
	}

	public boolean confereId(String novoValor) throws SQLException, ClassNotFoundException {
		Connection con = Conector.conectar();
		String sql = "SELECT * FROM turmas WHERE id=" + Integer.parseInt(novoValor);
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPodeInserir() {
		return podeInserir;
	}

	public void setPodeInserir(boolean podeInserir) {
		this.podeInserir = podeInserir;
	}

}
