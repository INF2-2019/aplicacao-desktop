package app.inicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLInicio.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("EstiloInicio.css").toExternalForm());
        
        stage.setTitle("Sistema Acadêmico");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
