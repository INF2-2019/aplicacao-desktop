
package app.biblioteca.emprestimos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErroController implements Initializable {

	static String id, nome;

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		ErroController.nome = nome;
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		ErroController.id = id;
	}
	@FXML
	private Button erroConfirmar;

	@FXML
	private Label text;

	@FXML
	void ok(ActionEvent event) {
		Stage modal = (Stage) erroConfirmar.getScene().getWindow();
		modal.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		text.setText(ErroMain.getMsg());
	}
}
