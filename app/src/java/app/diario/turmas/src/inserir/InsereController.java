package inserir;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import principal.Conector;
import principal.MainController;

public class InsereController implements Initializable {

	private int id, idCursos;
	private String nome;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println();
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

}
