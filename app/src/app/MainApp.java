package app;

import diario.departamentos.controllers.TableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/diario/departamentos/TabelaDepartamentos.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("resources/styles.css").toExternalForm());
        TableController t = new TableController();

        stage.setTitle("Departamentos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
