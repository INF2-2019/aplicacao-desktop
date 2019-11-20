
package app.biblioteca.emprestimos;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainEmprestimos extends Application {
    static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/emprestimos/emprestimos.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/app/biblioteca/emprestimos/style.css").toExternalForm());
        
        stage.setTitle("Biblioteca");
	setStage(stage);
        stage.setResizable(false);
        stage.setScene(scene);
	stage.setWidth(1280);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
     public static Stage getStage(){
        return stage;
    }

    private void setStage(Stage stage) {
        MainEmprestimos.stage = stage;
    }
}