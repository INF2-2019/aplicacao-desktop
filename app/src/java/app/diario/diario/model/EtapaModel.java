/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.diario.model;

/**
 *
 * @author User
 */
public class EtapaModel {
    /*protected Integer id, ano;
    protected Double valor;*/
    /*protected int id,ano;
    protected Double valor;*/
    protected String ano,id,valor;

    public EtapaModel(String ano, String id, String valor) {
        this.ano = ano;
        this.id = id;
        this.valor = valor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    

    
    

}
