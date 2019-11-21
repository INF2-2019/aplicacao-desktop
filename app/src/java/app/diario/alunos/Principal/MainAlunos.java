
package app.diario.alunos.Principal;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAlunos extends Application{
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/app/diario/alunos/Principal/FXML2.fxml"));
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(1280);
        setStage(stage);
        stage.show();
    }
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
