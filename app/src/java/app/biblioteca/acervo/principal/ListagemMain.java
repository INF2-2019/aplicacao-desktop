package app.biblioteca.acervo.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Indra Matsiendra & Amanda Jacomette
 */
public class ListagemMain extends Application {
     private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/acervo/listagem.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setWidth(1280);
        stage.setResizable(false);
        setStage(stage);
        
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
