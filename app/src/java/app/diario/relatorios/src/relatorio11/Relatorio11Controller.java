package relatorio11;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Relatorio11Controller implements Initializable {

	private static String disciplina;
	private static int etapa;

	@FXML
	private TableView tab;


	@FXML
	private Label nomeDisciplinaEtapa;
	@FXML
	private TableColumn colConteudos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colConteudos.setCellValueFactory(new PropertyValueFactory<>("conteudos"));
		nomeDisciplinaEtapa.setText("Disciplina \""+disciplina+"\", etapa "+etapa);
	}

	public static String getDisciplina() {
		return disciplina;
	}

	public static void setDisciplina(String disciplina) {
		Relatorio11Controller.disciplina = disciplina;
	}

	public static int getEtapa() {
		return etapa;
	}

	public static void setEtapa(int etapa) {
		Relatorio11Controller.etapa = etapa;
	}

}
