package app.biblioteca.reservas;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditarMain {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditarMain.stage = stage;
    }
    
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/reservas/Editar.fxml"));        
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
