/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.diario.diario.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.scene.control.Button;

/**
 *
 * @author User
 */
public class AdicionarConteudosModel {



	Integer id = null, idEtapa = null, idDisciplina = null;
	String conteudo = null;
	Date data;
	Double valor = null;

	public AdicionarConteudosModel() {
        this.data = null;
	}

    public AdicionarConteudosModel(int id, int idEtapa, int idDisciplina, String conteudo,String data, Double valor) {
       
        this.data = null;
		this.id = id;
		this.idEtapa = idEtapa;
		this.idDisciplina = idDisciplina;
		this.conteudo = conteudo;
		this.valor = valor;
		//setData(data);
	}

	public AdicionarConteudosModel(int idEtapa, int idDisciplina, String conteudo, String data, Double valor) {
        this.data = null;
		this.idEtapa = idEtapa;
		this.idDisciplina = idDisciplina;
		this.conteudo = conteudo;
		this.valor = valor;
		//setData(data);
	}

	public AdicionarConteudosModel(int idEtapa, int idDisciplina, String conteudo,String data) {
        this.data = null;
		this.idEtapa = idEtapa;
		this.idDisciplina = idDisciplina;
		this.conteudo = conteudo;
		//setData(data);
		valor = null;
	}

	/* Getters e Setters */
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(int idEtapa) {
		this.idEtapa = idEtapa;
	}

	public Integer getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

 

	

    public void setData(LocalDate data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	



}
