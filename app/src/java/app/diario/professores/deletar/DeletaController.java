package app.diario.professores.deletar;

import app.diario.professores.principal.TableController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author Nikolas Victor
 * @author Jonata Novais
 */
public class DeletaController implements Initializable {
    static String id,nome;

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        DeletaController.nome = nome;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        DeletaController.id = id;
    }

    @FXML
    private Button deleteLabelCancelar;

    @FXML
    private Button deleteLabelConfirmar;
    
    @FXML
    private Label text;

    @FXML
    void confirma(ActionEvent event) {
        TableController controle = new TableController();
        controle.deleta(DeletaController.getId());         
        fecha();
    }

    @FXML
    void cancela(ActionEvent event) {
        fecha();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text.setText("Você realmente deseja DELETAR o professor: "+DeletaController.getNome());
    }  
    
    public void fecha(){
        MainDeleta.getStage().close();
    }
    
}
