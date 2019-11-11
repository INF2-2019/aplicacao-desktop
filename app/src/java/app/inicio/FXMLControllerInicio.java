package app.inicio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLControllerInicio implements Initializable {
    
    @FXML
    private Button btnEntraTelaLoginAdmDiario;
    @FXML
    private Button btnEntraTelaLoginAdmBiblioteca;
    
    @FXML
    public void entraTelaLoginAdmDiario(ActionEvent event) throws Exception {
        Stage stage = (Stage) btnEntraTelaLoginAdmDiario.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/diario/telaloginadm/FXMLTelaLoginAdmDiario.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setResizable(false);
        stg.setWidth(1280);
        stg.showAndWait();
    }
    
    @FXML
    public void entraTelaLoginAdmBiblioteca(ActionEvent event) throws Exception {
        Stage stage = (Stage) btnEntraTelaLoginAdmBiblioteca.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(MainApp.class.getResource("/app/biblioteca/telaloginadm/FXMLTelaLoginAdmBiblioteca.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setResizable(false);
        stg.setWidth(1280);
        stg.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}