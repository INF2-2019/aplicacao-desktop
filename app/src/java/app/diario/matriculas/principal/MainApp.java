package app.diario.matriculas.principal;

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
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/matriculas/main.fxml"));

		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("/app/diario/matriculas/styles.css").toExternalForm());

		primaryStage.setTitle("Manutenção de Matrículas");
		primaryStage.setScene(sc);

		primaryStage.show();
	}
}
