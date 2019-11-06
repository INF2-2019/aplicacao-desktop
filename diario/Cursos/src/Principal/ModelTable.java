/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import PacoteDeletar.ControllerDeleta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import PacoteDeletar.MainDeleta;
import PacoteEditar.ControllerEditar;
import PacoteEditar.MainEditar;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */
public class ModelTable {
    String id,idDepto,nome,horas,modalidade;
    Button deleta,edita;
    boolean isCancela;

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
    int contador;

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

    public ModelTable(String id, String idDepto, String nome, String horas, String modalidade, Button edita,Button deleta){
        this.id = id;
        this.idDepto = idDepto;
        this.nome = nome;
        this.horas = horas;
        this.modalidade = modalidade;
        this.deleta = deleta;
        this.deleta.setId("deleta");
        this.edita = edita;
        this.edita.setId("edita");
        deleta.setOnMouseClicked((MouseEvent event) -> {
            MainDeleta mainDeleta = new MainDeleta();
            ControllerDeleta.setId(id);
            ControllerDeleta.setNome(nome);
            try {
                mainDeleta.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ModelTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        edita.setOnMouseClicked((MouseEvent event) -> {
            MainEditar mainEditar = new MainEditar();
            ControllerEditar.setId(id);
            try {
                mainEditar.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ModelTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(String idDepto) {
        this.idDepto = idDepto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
   
}
