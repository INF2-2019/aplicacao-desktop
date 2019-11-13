package alterar;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import principal.Conector;
import principal.MainController;

public class AlteraController implements Initializable {

	public static int idVelho;
	private int id, idCursos;
	private String nome;
	private boolean podeAlterar = true;

	@FXML
	private TextField idField;

	@FXML
	private TextField idCursosField;

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
			idCursos = Integer.parseInt(idCursosField.getText());
			nome = nomeField.getText();
			Connection con = Conector.conectar();
			String sql = "UPDATE turmas SET `id` = " + id + ", `id-cursos` = " + idCursos + ", `nome` = '" + nome + "' WHERE id = " + idVelho;;
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

	public int getIdCursos() {
		return idCursos;
	}

	public void setIdCursos(int idCursos) {
		this.idCursos = idCursos;
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

	public void confereId(String novoValor) throws SQLException, ClassNotFoundException {
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
	}

}
