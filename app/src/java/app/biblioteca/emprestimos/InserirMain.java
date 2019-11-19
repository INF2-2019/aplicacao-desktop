
package app.biblioteca.emprestimos;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class InserirMain extends Application {
    static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/emprestimos/ModalInserir.fxml"));
        
        Scene scene = new Scene(root);
        scene.setCursor(Cursor.HAND);
         
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        setStage(stage);
    }
    
     public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage(){
        return stage;
    }

    private void setStage(Stage stage) {
        InserirMain.stage = stage;
    }
    
}
