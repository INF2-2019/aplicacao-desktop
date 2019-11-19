package app.diario.disciplinas.principal;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/disciplinas/FXML.fxml"));

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/diario/disciplinas/styles.css").toExternalForm());
		TableControllerDisciplinas t = new TableControllerDisciplinas();
		
		stage.setTitle("Disciplinas");
		stage.setScene(scene);
                stage.setResizable(false);
                stage.setWidth(1280);
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
		MainApp.stage = stage;

	}

}
