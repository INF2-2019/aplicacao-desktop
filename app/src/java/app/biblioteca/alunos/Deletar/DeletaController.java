/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.alunos.Deletar;


import app.biblioteca.alunos.Principal.TableController;
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
    
    	/*public boolean checarAutorizacaoAluno(HttpServletRequest request, HttpServletResponse response, String id) {
		DiarioAutenticador x = new DiarioAutenticador(request, response);
		long idParsed = Long.parseLong(id);
		return x.cargoLogado() == DiarioCargos.ALUNO && (Long) x.idLogado() == idParsed;
	}
    */
    
}
