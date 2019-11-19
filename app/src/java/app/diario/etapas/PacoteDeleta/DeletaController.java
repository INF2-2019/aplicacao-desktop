package app.diario.etapas.PacoteDeleta;

import app.diario.etapas.Principal.TableController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class DeletaController implements Initializable {
    static int id;
	
    public static void setId(int id) {
		DeletaController.id = id;
    }
    public static int getId() {
        return id;
    }

    @FXML
    private Button botaoDeletar;

    @FXML
    private Button botaoCancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void AcaoCancelar(ActionEvent event) {
		fechar();
    }

    private void fechar() {
		DeletaMain.getStage().close();
    }
    
    @FXML
    void deletar(ActionEvent event) {
		TableController controle = new TableController();
        controle.deleta(getId());
                
        fechar();
    }
 
}
