package app.biblioteca.acervo.inserir.livro;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Indra Matsiendra & Amanda Jacomette
 */
public class InsereLivroMain extends Application {
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/acervo/InsereLivro.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
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
        InsereLivroMain.stage = stage;
    }
}