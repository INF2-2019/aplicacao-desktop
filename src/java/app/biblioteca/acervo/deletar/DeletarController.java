package app.biblioteca.acervo.deletar;

import app.biblioteca.acervo.principal.TableController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Indra Matsiendra & Amanda Jacomette
 */
public class DeletarController implements Initializable {
    private static String id,nome,tipo;

    public static String getTipo() {
        return tipo;
    }

    public static void setTipo(String tipo) {
        DeletarController.tipo = tipo;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        DeletarController.nome = nome;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        DeletarController.id = id;
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
        controle.deleta(DeletarController.getId(),DeletarController.getTipo());
                
        fecha();
    }

    @FXML
    void cancela(ActionEvent event) {
        fecha();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text.setText("Você realmente deseja DELETAR a obra "+DeletarController.getNome()+"? ");
    }  
    
    public void fecha(){
        DeletarMain.getStage().close();
    }
    
}