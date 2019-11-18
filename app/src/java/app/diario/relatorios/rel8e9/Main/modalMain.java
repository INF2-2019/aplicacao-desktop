package app.diario.relatorios.rel8e9.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class modalMain extends Application{   
    public void start() throws IOException{  
        Stage stage = new Stage();
        
        /*URL url = new File("/app/diario/relatorios/modal-professor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);*/
        Parent root = FXMLLoader.load(getClass().getResource("/app/diario/relatorios/rel9/modal-professor.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setWidth(1280);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }
    

