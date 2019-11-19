package app.diario.transferencia.main;

import app.diario.transferencia.controllers.PedirCPFController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTranferencia extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/diario/transferencia/PedirCPF.fxml"));        
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/app/diario/transferencia/EstiloTransferencia.css").toExternalForm());
        
        stage.setTitle("Sistema Acadêmico");
        stage.setScene(scene);
        stage.setWidth(1280);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
