package app.diario.disciplinas;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlterarMain {

	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		AlterarMain.stage = stage;
	}

	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/disciplinas/Alterar.fxml"));
		Scene scene = new Scene(root);
                stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		setStage(stage);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
