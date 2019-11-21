package app.diario.professores.deletar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Nikolas Victor
 * @author Jonata Novais
 */
public class MainDeleta extends Application {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        MainDeleta.stage = stage;
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/diario/professores/Deletar.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setScene(scene);
        setStage(stage);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
