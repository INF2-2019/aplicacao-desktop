package relatorio10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Relatorio10Controller {

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
		try{
			r10m.start(new Stage());
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}
