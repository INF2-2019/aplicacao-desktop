/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import javafx.scene.control.Button;

/**
 *
 * @author Aluno
 */
public class ModelTable {
    String id,idDepto,nome,horas,modalidade;
    Button deleta,edita,info;

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

    public Button getInfo() {
        return info;
    }

    public void setInfo(Button info) {
        this.info = info;
    }

    public ModelTable(String id, String idDepto, String nome, String horas, String modalidade, Button info, Button edita,Button deleta){
        this.id = id;
        this.idDepto = idDepto;
        this.nome = nome;
        this.horas = horas;
        this.modalidade = modalidade;
        this.info = info;
        this.info.setId("info");
        this.deleta = deleta;
        this.deleta.setId("deleta");
        this.edita = edita;
        this.edita.setId("edita");
        
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
