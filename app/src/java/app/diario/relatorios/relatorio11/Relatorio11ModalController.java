package app.diario.relatorios.relatorio11;

import app.diario.relatorios.relatorio10.Relatorio10Controller;
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

public class Relatorio11ModalController implements Initializable {

	private Boolean disciplina = false, etapa = false;

	@FXML
	private Button impressoBtn;
	@FXML
	private Button telaBtn;
	@FXML
	private ChoiceBox selectDisciplina;
	@FXML
	private ChoiceBox selectEtapa;

	@FXML
	private void impressoAction(ActionEvent event) {
		System.out.println("impresso action");
	}

	@FXML
	private void telaAction(ActionEvent event) {
		if (disciplina && etapa) {
			Relatorio11Main r11m = new Relatorio11Main();
			Relatorio11Controller.setDisciplina(selectDisciplina.getValue().toString());
			Relatorio11Controller.setEtapa(Integer.parseInt(selectEtapa.getValue().toString()));
			try {
				r11m.start(new Stage());
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectDisciplina.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			disciplina = true;
		});
		selectEtapa.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			etapa = true;
		});
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM disciplinas";
			ResultSet res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				lista.add(res.getString("nome"));
			}
			selectDisciplina.setItems(lista);
			ObservableList lista2 = FXCollections.observableArrayList();
			sql = "SELECT * FROM etapas";
			res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				lista2.add(res.getString("id"));
			}
			selectEtapa.setItems(lista2);
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
