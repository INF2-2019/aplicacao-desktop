package app.diario.etapas.PacoteConsulta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConsultaMain extends Application {
    private static Stage stage;
	
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/diario/etapas/Consulta.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
	setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ConsultaMain.stage = stage;
    }
    
}
