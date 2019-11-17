package app.biblioteca.reservas;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainReservas extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/reservas/Reservas.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/app/biblioteca/reservas/style.css").toExternalForm());
        
        stage.setTitle("Biblioteca");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
