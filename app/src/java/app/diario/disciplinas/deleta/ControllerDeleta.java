package app.diario.disciplinas;

import app.diario.disciplinas.TableControllerDisciplinas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerDeleta implements Initializable {

	static String id, nome;

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		ControllerDeleta.nome = nome;
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		ControllerDeleta.id = id;
	}
	@FXML
	private Button deleteLabelCancelar;

	@FXML
	private Button deleteLabelConfirmar;

	@FXML
	private Label text;

	@FXML
	void confirma(ActionEvent event) {
		TableControllerDisciplinas controle = new TableControllerDisciplinas();
		controle.deleta(getNome());
		Stage modal = (Stage) deleteLabelConfirmar.getScene().getWindow();
		modal.close();
	}

	@FXML
	void cancela(ActionEvent event) {
		Stage modal = (Stage) deleteLabelCancelar.getScene().getWindow();
		modal.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		text.setText("Você realmente deseja deletar a disciplina?");
	}
}
