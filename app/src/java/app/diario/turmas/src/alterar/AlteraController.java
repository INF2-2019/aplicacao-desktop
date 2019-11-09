package alterar;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import principal.Conector;
import principal.MainController;

public class AlteraController {

	public static int idVelho;
	private int id, idCursos;
	private String nome;
	
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

	public void fecha() {
		AlteraMain.getStage().close();
	}

	@FXML
	private void cancelaAction(ActionEvent event) {
		fecha();
	}

	@FXML
	private void alteraAction(ActionEvent event) throws SQLException, ClassNotFoundException {
		id = Integer.parseInt(idField.getText());
		idCursos = Integer.parseInt(idCursosField.getText());
		nome = nomeField.getText();
		Connection con = Conector.conectar();
		String sql = "UPDATE turmas SET `id` = "+id+", `id-cursos` = " + idCursos + ", `nome` = '" + nome + "' WHERE id = " + idVelho;;
		int res = con.createStatement().executeUpdate(sql);
		con.close();
		MainController.updateTab();
		fecha();
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
	
	

}
