package app.diario.relatorios.relatorio10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Relatorio10Main extends Application {

	private static Stage stage;

	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/relatorios/relatorio10/relatorio10.fxml"));

		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("/app/diario/relatorios/relatorio10/styles.css").toExternalForm());

		stage.setTitle("Relatório 10");
		stage.setScene(sc);
		stage.setResizable(false);
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
