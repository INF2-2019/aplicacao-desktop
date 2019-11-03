package app;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
        import javafx.stage.Stage;


public class TelaAdicionar {
    public static void display(String title, String message){
        Stage tela = new Stage();
        
        tela.initModality(Modality.APPLICATION_MODAL);
        tela.setTitle(title);
        tela.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        
        Button butao = new Button("Fechar");
        butao.setOnAction(e -> tela.close());
        
        VBox layout = new VBox(10);
        
        
        Scene cena = new Scene(layout);
        tela.setScene(cena);
        tela.showAndWait();
    }
    
}
