package app.diario.relatorios.principal;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RelatoriosMain extends Application {
	
	private static Stage stage;
	
	@Override
	public void start(Stage stage) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/relatorios/principal/main.fxml"));
		
		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("/app/diario/relatorios/principal/styles.css").toExternalForm());
		
		stage.setTitle("Relatórios");
		stage.setScene(sc);
		setStage(stage);
		
		stage.setResizable(false);
		stage.show();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		RelatoriosMain.stage = stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
