/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.relatorios.relReservas;

import java.sql.Date;

/**
 *
 * @author Aluno
 */
public class ModelTable {
    long alunos;
    int id, acervo, espera;
    Date reserva;
    Boolean emprestou;

    public ModelTable( int id, long alunos, int acervo,  Date reserva, int espera, Boolean emprestou) {
        this.alunos = alunos;
        this.id = id;
        this.acervo = acervo;
        this.espera = espera;
        this.reserva = reserva;
        this.emprestou = emprestou;
    }

    public long getAlunos() {
	return alunos;
    }

    public void setAlunos(long alunos) {
	this.alunos = alunos;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getAcervo() {
	return acervo;
    }

    public void setAcervo(int acervo) {
	this.acervo = acervo;
    }

    public int getEspera() {
	return espera;
    }

    public void setEspera(int espera) {
	this.espera = espera;
    }

    public Date getReserva() {
	return reserva;
    }

    public void setReserva(Date reserva) {
	this.reserva = reserva;
    }

    public Boolean getEmprestou() {
	return emprestou;
    }

    public void setEmprestou(Boolean emprestou) {
	this.emprestou = emprestou;
    }
    
    

    
}
