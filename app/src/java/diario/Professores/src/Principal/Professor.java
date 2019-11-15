package Principal;

import PacoteDeletar.ControllerDeleta;
import PacoteDeletar.MainDeleta;
import PacoteEditar.ControllerEditar;
import PacoteEditar.MainEditar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Nikolas Victor
 * @author Jonata Novaes
 */
public class Professor {
    int idDpto;
    String nome;
    int id;
    String titulacao;
    String email;
    String senha;
    Button deleta,edita, info;
    boolean isCancela;
    int contador;
    
    public Professor(int id, int idDpto, String nome, String titulacao, String email, String senha, Button edita, Button deleta, Button info){
        this.id = id;
        this.idDpto = idDpto;
        this.nome = nome;
        this.titulacao = titulacao;
        this.email = email;
        this.senha = senha;
        this.deleta = deleta;
        this.edita = edita;
        this.info = info;
        this.deleta.setId("deleta");
        this.edita.setId("edita");
        this.info.setId("info");
        
        deleta.setOnMouseClicked((MouseEvent event) -> {
            MainDeleta mainDeleta = new MainDeleta();
            ControllerDeleta.setId(Integer.toString(id));
            ControllerDeleta.setNome(nome);
            try {
                mainDeleta.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Professor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        edita.setOnMouseClicked((MouseEvent event) -> {
            MainEditar mainEditar = new MainEditar();
            ControllerEditar.setId(Integer.toString(id));
            try {
                mainEditar.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Professor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        info.setOnMouseClicked((MouseEvent event) -> {
            
        });
        
    }

    public Button getDeleta() {
        return deleta;
    }

    public void setDeleta(Button deleta) {
        this.deleta = deleta;
    }

    public Button getEdita() {
        return edita;
    }

    public void setEdita(Button edita) {
        this.edita = edita;
    }

    public boolean isIsCancela() {
        return isCancela;
    }

    public void setIsCancela(boolean isCancela) {
        this.isCancela = isCancela;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    

    public int getIdDpto() {
        return idDpto;
    }

    public void setIdDpto(int idDpto) {
        this.idDpto = idDpto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
