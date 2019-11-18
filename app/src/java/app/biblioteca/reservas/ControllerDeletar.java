package app.biblioteca.reservas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerDeletar implements Initializable{
    static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ControllerDeletar.id = id;
    }
    
    @FXML
    private Button Cancelar;

    @FXML
    private Button Confirmar;
    
    @FXML
    private Label text;

    @FXML
    void confirmaDeletar(ActionEvent event) {
        TableControllerReservas controle = new TableControllerReservas();
        controle.deletarReservas(getId());
        Stage modal = (Stage)Confirmar.getScene().getWindow();
        modal.setResizable(false);
        modal.close();
    }
    
    @FXML
    void cancelaDeletar(ActionEvent event){
        Stage modal = (Stage)Cancelar.getScene().getWindow();
        modal.setResizable(false);
        modal.close(); 
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text.setText("Você realmente deseja deletar a reserva?");
    }
}
