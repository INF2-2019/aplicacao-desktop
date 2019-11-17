/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.descartes.model;

import java.sql.Date;
import java.util.Calendar;
import javafx.scene.control.Button;

/**
 * @author juanr
 */
public class DescartesModel {

	Integer id = null;
	String nome = null , operador = null , motivo = null;
	Date data = null;

	public DescartesModel() {
	}

    

	public DescartesModel(int id, String nome, Date data ,String operador ,String motivo ) {
		this.id = id;
                this.operador = operador;
                this.nome = nome;
		setData(data);
	}

	public DescartesModel(int id , String nome, Date data) {
		this.nome = nome;
		setData(data);
	}

	public DescartesModel( String nome, Date data) {
		setData(data);
	}

	/* Getters e Setters */
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
       

	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, 1);
		this.data = new Date(c.getTimeInMillis());
	}

}
