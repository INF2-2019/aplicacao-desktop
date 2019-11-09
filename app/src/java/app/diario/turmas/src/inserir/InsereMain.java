package inserir;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InsereMain extends Application{

	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("insere.fxml"));

		Scene sc = new Scene(root);
		sc.getStylesheets().add(getClass().getResource("../principal/styles.css").toExternalForm());

		stage.setTitle("Inserir turma");
		stage.setScene(sc);
		setStage(stage);

		stage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
