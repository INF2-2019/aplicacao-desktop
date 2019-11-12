package relatorio10;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import principal.Conector;

public class Relatorio10ModalController implements Initializable {

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
		Relatorio10Main r10m = new Relatorio10Main();
		Relatorio10Controller.setNome(selectAluno.getValue().toString());
		try {
			r10m.start(new Stage());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM alunos";
			ResultSet res = con.createStatement().executeQuery(sql);
			while(res.next()){
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
