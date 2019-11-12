package relatorio10;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Relatorio10Controller implements Initializable {
	private static String nome;
	
	@FXML
	private TableView tab;
	
	@FXML
	private TableColumn colEtapas;
	
	@FXML
	private TableColumn colDisciplinas;
	
	@FXML
	private TableColumn colNotas;
	
	@FXML
	private Label nomeAluno;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colEtapas.setCellValueFactory(new PropertyValueFactory<>("etapas"));
		colDisciplinas.setCellValueFactory(new PropertyValueFactory<>("disciplinas"));
		colNotas.setCellValueFactory(new PropertyValueFactory<>("notas"));
		nomeAluno.setText("Aluno: "+nome);
	}
	
	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		Relatorio10Controller.nome = nome;
	}

}
