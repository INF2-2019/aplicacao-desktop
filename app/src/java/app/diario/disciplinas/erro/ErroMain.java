package app.diario.disciplinas.erro;

import app.diario.disciplinas.deleta.DeletaMain;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErroMain extends Application {

	static Stage stage;
	private static String msg;

	public ErroMain(String m) {
		msg = m;
	}

	public static String getMsg() {
		return msg;
	}

	public static void setMsg(String msg) {
		ErroMain.msg = msg;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		ErroMain.stage = stage;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/disciplinas/Erro.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
		setStage(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
