package app.diario.disciplinas.deleta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeletaMain extends Application {

	static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		DeletaMain.stage = stage;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/disciplinas/Deleta.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
                stage.setResizable(false);
		setStage(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
