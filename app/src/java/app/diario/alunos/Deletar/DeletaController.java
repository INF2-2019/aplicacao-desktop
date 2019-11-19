/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.alunos.Deletar;


import app.diario.alunos.Principal.TableController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Marcelo
 */
public class DeletaController implements Initializable {
    static long id;
    public static void setId(long id) {
	DeletaController.id = id;
    }
    public static long getId() {
        return id;
    }

    @FXML
    private Button botaoDeletar;

    @FXML
    private Button botaoCancelar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
