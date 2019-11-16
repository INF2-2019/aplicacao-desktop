package app.diario.telaloginadm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainTelaLoginAdmDiario extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTelaLoginAdmDiario.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("EstiloTelaLoginAdmDiario.css").toExternalForm());
        
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