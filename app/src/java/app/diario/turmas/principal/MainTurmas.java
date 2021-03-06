package app.diario.turmas.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTurmas extends Application {
	
	private static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/turmas/main.fxml"));

		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("/app/diario/turmas/styles.css").toExternalForm());

		primaryStage.setTitle("Manutenção de Turmas");
		primaryStage.setResizable(false);
		primaryStage.setScene(sc);

		setStage(primaryStage);
		primaryStage.show();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		MainTurmas.stage = stage;
	}
	
	
}
