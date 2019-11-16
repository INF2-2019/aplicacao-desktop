package app.diario.disciplinas;

import app.diario.disciplinas.BDConnection;
import app.diario.disciplinas.BDConnection;
import app.diario.disciplinas.Disciplinas;
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

public class InserirDisciplina implements Initializable {

	@FXML
	private TextField nomeD;

	@FXML
	private TextField cargaH;

	@FXML
	private TextField idTurma;

	@FXML
	private Button adicionar;

	@FXML
	private Button cancelar;

	@FXML
	protected boolean Adicionar(ActionEvent event) throws SQLException {

		Connection connect = BDConnection.getConnection();
		int sucesso = 0;
		if ((!"".equals(nomeD.getText()) && !"".equals(idTurma.getText())) && !"".equals(cargaH.getText())) {

			try {
				verificaInt(idTurma, cargaH);
				PreparedStatement ps = connect.prepareStatement("INSERT INTO `disciplinas` (`id-turmas`,`nome`, `carga-horaria-min`) VALUES (?,?, ?)");

				ps.setInt(1, Integer.parseInt(idTurma.getText()));
				ps.setString(2, nomeD.getText());
				ps.setInt(3, Integer.parseInt(cargaH.getText()));

				sucesso = ps.executeUpdate();

				Stage modal = (Stage) adicionar.getScene().getWindow();
				modal.close();
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
		return sucesso != 0;
	}

	@FXML
	protected void Cancelar(ActionEvent event) throws SQLException {
		Stage modal = (Stage) cancelar.getScene().getWindow();
		modal.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	private void verificaInt(TextField idTurma, TextField cargaH) throws NumberFormatException {
		int id = Integer.parseUnsignedInt(idTurma.getText());
		int carga = Integer.parseUnsignedInt(cargaH.getText());
	}
}
