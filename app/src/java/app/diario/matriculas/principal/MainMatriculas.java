package app.diario.matriculas.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMatriculas extends Application {

	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/matriculas/main.fxml"));

		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("/app/diario/matriculas/styles.css").toExternalForm());

		primaryStage.setTitle("Manutenção de Matrículas");
		primaryStage.setResizable(false);
		primaryStage.setScene(sc);

		setStage(primaryStage);
		primaryStage.show();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		MainMatriculas.stage = stage;
	}
	
}
