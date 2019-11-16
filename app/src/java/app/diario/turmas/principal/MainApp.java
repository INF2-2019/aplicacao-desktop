package app.diario.turmas.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/turmas/main.fxml"));

		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("/app/diario/turmas/styles.css").toExternalForm());

		primaryStage.setTitle("Manutenção de Turmas");
		primaryStage.setScene(sc);

		primaryStage.show();
	}
}
