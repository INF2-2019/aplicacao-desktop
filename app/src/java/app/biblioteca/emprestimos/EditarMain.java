
package app.biblioteca.emprestimos;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditarMain extends Application{
    
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditarMain.stage = stage;
    }
    public EditarMain(){}
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/emprestimos/Editar.fxml"));        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        setStage(stage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
