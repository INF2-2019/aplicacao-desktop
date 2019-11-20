package app.diario.professores.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nikolas Victor
 * @author Jonata Novais
 */
public class ProfessorMain extends Application {
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/diario/professores/Principal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        setStage(stage);
	stage.setResizable(false);
	stage.setWidth(1280);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage(){
        return stage;
    }

    private void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
