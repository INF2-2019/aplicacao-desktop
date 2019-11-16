package app.biblioteca.acervo.principal;

import app.biblioteca.acervo.deletar.DeletarController;
import app.biblioteca.acervo.deletar.DeletarMain;
import app.biblioteca.acervo.editar.EditarController;
import app.biblioteca.acervo.editar.EditarMain;
import app.biblioteca.acervo.info.InfoController;
import app.biblioteca.acervo.info.InfoMain;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Indra Matsiendra & Amanda Jacomette
 */
public class Acervo {
    int id;
    int idCampi;
    String nome;
    String tipo;
    String local;
    int ano;
    String editora;
    int paginas;
    Button deleta,edita, info;
    boolean isCancela;
    int contador;

    public Acervo(int id, int idCampi, String nome, String tipo, String local, int ano, String editora, int paginas, Button edita, Button deleta, Button info) {
        this.id = id;
        this.idCampi = idCampi;
        this.nome = nome;
        this.tipo = tipo;
        this.local = local;
        this.ano = ano;
        this.editora = editora;
        this.paginas = paginas;
        this.deleta = deleta;
        this.edita = edita;
        this.info = info;
        this.deleta.setId("deleta");
        this.edita.setId("edita");
        this.info.setId("info");
        this.deleta.setCursor(Cursor.HAND);
        this.edita.setCursor(Cursor.HAND);
        this.info.setCursor(Cursor.HAND);
        deleta.setOnMouseClicked((MouseEvent event) -> {
            DeletarMain mainDeleta = new DeletarMain();
            DeletarController.setId(Integer.toString(id));
            DeletarController.setTipo(tipo);
            DeletarController.setNome(nome);
            try {
                mainDeleta.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        edita.setOnMouseClicked((MouseEvent event) -> {
            EditarMain mainEditar = new EditarMain();
            EditarController.setId(Integer.toString(id));
            try {
                mainEditar.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        info.setOnMouseClicked((MouseEvent event) -> {
            InfoMain mainInfo = new InfoMain();
            InfoController.setId(Integer.toString(id));
            InfoController.setTipo(tipo);
            try {
                mainInfo.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCampi() {
        return idCampi;
    }

    public void setIdCampi(int idCampi) {
        this.idCampi = idCampi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo.toLowerCase();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.toLowerCase();
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public Button getInfo() {
        return info;
    }

    public void setInfo(Button info) {
        this.info = info;
    }        
}