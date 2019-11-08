package app.diario.departamentos;

import app.diario.departamentos.controllers.TableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainDepartamentos extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/diario/departamentos/resources/TabelaDepartamentos.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app/diario/departamentos/Departamentos.css").toExternalForm());
        TableController t = new TableController();

        stage.setTitle("Departamentos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
