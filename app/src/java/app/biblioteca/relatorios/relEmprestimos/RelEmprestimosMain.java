package app.biblioteca.relatorios.relEmprestimos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RelEmprestimosMain extends Application{
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLEmprestimos.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setWidth(1280);
        stage.setResizable(false);
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
        RelEmprestimosMain.stage = stage;
    }
}
