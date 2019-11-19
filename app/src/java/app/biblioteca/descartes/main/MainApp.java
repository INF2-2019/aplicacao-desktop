/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.descartes.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class MainApp extends Application {
 
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/descartes/main/FXMLTelaDescartes.fxml"));        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/app/biblioteca/descartes/main/styles.css").toExternalForm()); 
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setWidth(1280);
        setStage(stage);
    }
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
