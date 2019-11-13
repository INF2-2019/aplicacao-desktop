package relatorio11;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Relatorio11Main extends Application {

	private static Stage stage;

	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("relatorio11.fxml"));

		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("../principal/styles.css").toExternalForm());

		stage.setTitle("Relatório 11");
		stage.setScene(sc);
		setStage(stage);

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
