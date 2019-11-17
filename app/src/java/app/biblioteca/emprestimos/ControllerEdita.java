
package app.biblioteca.emprestimos;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;


public class ControllerEdita implements Initializable{
   
 static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ControllerEdita.id = id;
    }

    @FXML
    private TextField IDAluno;
    
    @FXML
    private TextField IDAcervo;
    
    @FXML
    private DatePicker DataEmprestimo; 
    
    @FXML
    private DatePicker DataPrevDevolucao; 
    
    @FXML
    private DatePicker DataDevolucao; 
    
    @FXML
    private Button Alterar;
    
    @FXML
    private Button Deletar;
    
    @FXML
    void salvarAlteracaoEmprestimo(ActionEvent event) throws NumberFormatException, SQLException {
        TableControllerEmprestimos controle = new TableControllerEmprestimos();
		System.out.println(DataEmprestimo);
        controle.AlterarEmprestimo(IDAluno,IDAcervo,DataEmprestimo,DataPrevDevolucao,DataDevolucao,getId());
        Stage modal = (Stage)Alterar.getScene().getWindow();
        modal.setResizable(false);
        modal.close();
        controle.atualizarTabela();
        
    }
    
    @FXML
    void cancelarEmprestimo(ActionEvent event){
        Stage modal =(Stage)Deletar.getScene().getWindow();
        modal.setResizable(false);
        modal.close(); 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
