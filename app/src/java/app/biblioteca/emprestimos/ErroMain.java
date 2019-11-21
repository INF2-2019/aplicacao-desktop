/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.emprestimos;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author User
 */
public class ErroMain extends Application{
    static Stage stage;
	private static String msg;

	public ErroMain(String m) {
		msg = m;
	}
	public ErroMain(){
	this(" ");
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
	Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/emprestimos/Erro.fxml"));
		System.out.println(root);
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setResizable(false);
		setStage(stage);
                stage.show();
    }
    public static void main(String[] args) {
		launch(args);
	}
}
