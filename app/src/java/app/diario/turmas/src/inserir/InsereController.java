package inserir;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import principal.Conector;
import principal.MainController;

public class InsereController implements Initializable {

	private int id, idCursos;
	private String nome;
	private boolean podeInserir = true;

	@FXML
	private Button botaoConfirmar;

	@FXML
	private Button botaoCancelar;

	@FXML
	private TextField idField;

	@FXML
	private TextField idCursosField;

	@FXML
	private TextField nomeField;

	@FXML
	private Label errorLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
			idCursos = Integer.parseInt(idCursosField.getText());
			nome = nomeField.getText();
			Connection con = Conector.conectar();
			String sql = "INSERT INTO turmas (`id`, `id-cursos`, `nome`) VALUES (" + id + "," + idCursos + ",'" + nome + "')";
			int res = con.createStatement().executeUpdate(sql);
			con.close();
			MainController.updateTab();
			fecha();
		}
	}

	public void confereId(String novoValor) throws SQLException, ClassNotFoundException {
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
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCursos() {
		return idCursos;
	}

	public void setIdCursos(int idCursos) {
		this.idCursos = idCursos;
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
