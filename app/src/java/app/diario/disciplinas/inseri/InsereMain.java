package app.diario.disciplinas.inseri;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InsereMain extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/disciplinas/Modal.fxml"));

		Scene scene = new Scene(root);
                scene.setCursor(Cursor.HAND);

		stage.setScene(scene);
		stage.show();
		setStage(stage);
         
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	private void setStage(Stage stage) {
		InsereMain.stage = stage;
	}    
        
}
