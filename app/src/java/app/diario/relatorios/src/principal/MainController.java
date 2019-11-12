package principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import relatorio10.Relatorio10ModalMain;
import relatorio11.Relatorio11ModalMain;

public class MainController implements Initializable {

	@FXML
	private Button rel7Btn;
	
	@FXML
	private Button rel8Btn;
	
	@FXML
	private Button rel9Btn;
	
	@FXML
	private Button rel10Btn;
	
	@FXML
	private Button rel11Btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println();
	}

	@FXML
	private void rel7Action(ActionEvent event) {
		System.out.println("rel. 7");
	}

	@FXML
	private void rel8Action(ActionEvent event) {
		System.out.println("rel. 8");
	}

	@FXML
	private void rel9Action(ActionEvent event) {
		System.out.println("rel. 9");
	}

	@FXML
	private void rel10Action(ActionEvent event) {
		Relatorio10ModalMain r10mm = new Relatorio10ModalMain();
		try{
			r10mm.start(new Stage());
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	@FXML
	private void rel11Action(ActionEvent event) {
		Relatorio11ModalMain r11mm = new Relatorio11ModalMain();
		try{
			r11mm.start(new Stage());
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}
