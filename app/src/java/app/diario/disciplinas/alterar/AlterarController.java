package app.diario.disciplinas;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlterarController implements Initializable {

	static String nome;

	Connection connect;

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		AlterarController.nome = nome;
	}

	@FXML
	private TextField Nome;

	@FXML
	private TextField CargaHoraria;

	@FXML
	private TextField IDTurmas;

	@FXML
	private TextField ID;

	@FXML
	private Button Alterar;

	@FXML
	private Button Deletar;

	@FXML
	void salvar(ActionEvent event) throws NumberFormatException, SQLException {
		TableControllerDisciplinas controle = new TableControllerDisciplinas();
		connect = BDConnection.getConnection();
		int sucesso = 0;
		if ((!"".equals(IDTurmas.getText()) && !"".equals(CargaHoraria.getText())) && !"".equals(ID.getText()) && !"".equals(ID.getText())) {

			try {
				int id = verificaInt(IDTurmas, CargaHoraria, ID);

				PreparedStatement ps = connect.prepareStatement("SELECT * FROM `disciplinas` WHERE `id` = ?");
				ps.setInt(1, id);
				if (!ps.executeQuery().next()) {
					ErroMain erro = new ErroMain("Esse não é o ID da disciplina.");
					try {
						erro.start(new Stage());
					} catch (Exception ex) {
						Logger.getLogger(Disciplinas.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else {
					controle.Altera(IDTurmas, Nome, CargaHoraria, ID);

					Stage modal = (Stage) Alterar.getScene().getWindow();
					modal.close();
				}
			} catch (NumberFormatException e) {
				ErroMain erro = new ErroMain("Não coloque letras ou símbolos nos campos de números");
				try {
					erro.start(new Stage());
				} catch (Exception ex) {
					Logger.getLogger(Disciplinas.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

		} else {
			ErroMain erro = new ErroMain("Todos os campos devem ser preenchidos");
			try {
				erro.start(new Stage());
			} catch (Exception ex) {
				Logger.getLogger(Disciplinas.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	@FXML
	void cancel(ActionEvent event) {
		Stage modal = (Stage) Deletar.getScene().getWindow();
		modal.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	private int verificaInt(TextField IDTurmas, TextField CargaHoraria, TextField ID) {
		int id = Integer.parseUnsignedInt(IDTurmas.getText());
		int carga = Integer.parseUnsignedInt(CargaHoraria.getText());
		int i = Integer.parseUnsignedInt(ID.getText());

		return i;
	}

}
