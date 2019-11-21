package app.diario.telatransicao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainTelaTransicaoDiario extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTelaTransicaoDiario.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("EstiloTelaTransicaoDiario.css").toExternalForm());
        
        stage.setTitle("Diário");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}