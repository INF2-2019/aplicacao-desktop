
package app.biblioteca.relatorios.relMultas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RelMultasMain extends Application{
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMultas.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        setStage(stage);
	stage.setResizable(false);

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
