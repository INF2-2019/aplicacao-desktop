package principal;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RelatoriosMain extends Application {
	
	@Override
	public void start(Stage stage) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
		
		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		
		stage.setTitle("Manutenção de Turmas");
		stage.setScene(sc);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
