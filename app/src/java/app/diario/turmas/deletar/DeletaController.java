package app.diario.turmas.deletar;

import app.diario.turmas.principal.Conector;
import app.diario.turmas.principal.MainController;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class DeletaController implements Initializable {

	private static int id;

	@FXML
	private Button botaoConfirmar;

	@FXML
	private Button botaoCancelar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println();
	}

	public int getId() {
		return id;
	}

	public static void setId(int i) {
		id = i;
	}

	public void fecha() {
		DeletaMain.getStage().close();
	}

	@FXML
	private void deletaAction(ActionEvent event) throws SQLException, ClassNotFoundException {
		Connection con = Conector.conectar();
		String sql = "DELETE FROM turmas WHERE id = " + id;
		int res = con.createStatement().executeUpdate(sql);
		MainController.updateTab();
		fecha();
	}

	@FXML
	private void cancelaAction(ActionEvent event) {
		fecha();
	}

}
