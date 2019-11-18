package app.biblioteca.reservas;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEditar implements Initializable{
      
 static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ControllerEditar.id = id;
    }
    
    @FXML
    private TextField IDAluno;
    
    @FXML
    private TextField IDAcervo;
    
    @FXML
    private DatePicker DataReserva; 
    
    @FXML
    private TextField TempoEspera;
    
    @FXML
    private Button Alterar;
    
    @FXML
    private Button Deletar;
    
     @FXML
    void salvarAlteracaoReserva(ActionEvent event) throws NumberFormatException, SQLException, Exception {
        TableControllerReservas controle = new TableControllerReservas();
        controle.AlterarReserva(IDAluno,IDAcervo,DataReserva,TempoEspera,getId());
        Stage modal = (Stage)Alterar.getScene().getWindow();
        modal.setResizable(false);
        modal.close();
        
    }
    
    @FXML
    void cancelarAlteracao(ActionEvent event){
        Stage modal =(Stage)Deletar.getScene().getWindow();
        modal.setResizable(false);
        modal.close(); 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }  
}
