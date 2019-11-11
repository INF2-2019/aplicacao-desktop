package app.biblioteca.telaloginadm;

import app.diario.telaloginadm.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainTelaLoginAdmBiblioteca extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTelaLoginAdm.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("EstiloTelaLoginAdm.css").toExternalForm());
        
        stage.setTitle("Sistema Acadêmico");
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