
package app.biblioteca.emprestimos;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeletarMain extends Application{
    static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        DeletarMain.stage = stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
		System.out.println("entro fdp pqp");
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/emprestimos/ModalDeletar.fxml"));
        
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