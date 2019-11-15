package app.diario.relatorios.relatorio10;

import app.diario.turmas.principal.Conector;
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
import javafx.stage.Stage;

public class Relatorio10ModalController implements Initializable {

	private Boolean podeGerar = false;

	@FXML
	private ChoiceBox selectAluno;
	@FXML
	private Button impressoBtn;
	@FXML
	private Button telaBtn;

	@FXML
	private void impressoAction(ActionEvent event) {
		System.out.println("impresso action");
	}

	@FXML
	private void telaAction(ActionEvent event) {
		if (podeGerar) {
			Relatorio10Main r10m = new Relatorio10Main();
			Relatorio10Controller.setNomeAluno(selectAluno.getValue().toString());
			try {
				r10m.start(new Stage());
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectAluno.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			podeGerar = true;
		});
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM alunos";
			ResultSet res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				lista.add(res.getString("nome"));
			}
			con.close();
			selectAluno.setItems(lista);
		} catch (SQLException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
