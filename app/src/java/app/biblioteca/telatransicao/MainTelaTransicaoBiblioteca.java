package app.biblioteca.telatransicao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainTelaTransicaoBiblioteca extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTelaTransicaoBiblioteca.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("EstiloTelaTransicaoBiblioteca.css").toExternalForm());
        
        stage.setTitle("Biblioteca");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(1280);
//        stage.getIcons().add(new Image("file:/src/resources/imgs/logoDiario.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}