package app.biblioteca.relatorios.relEmprestimos;

import java.sql.Date;

public class ModelTable {
    String id,idAlunos,idAcervo;
    Date dataEmprestimo,dataPrevDevol,dataDevolucao;
    Double multa;
    
    public ModelTable(String id, String idAlunos, String idAcervo, Date dataEmprestimo, Date dataPrevDevol, Date dataDevolucao, Double multa){
        this.id = id;
        this.idAlunos = idAlunos;
        this.idAcervo = idAcervo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevDevol = dataPrevDevol;
	this.dataDevolucao = dataDevolucao;
        this.multa = multa;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getIdAlunos() {
	return idAlunos;
    }

    public void setIdAlunos(String idAlunos) {
	this.idAlunos = idAlunos;
    }

    public String getIdAcervo() {
	return idAcervo;
    }

    public void setIdAcervo(String idAcervo) {
	this.idAcervo = idAcervo;
    }

    public Date getDataEmprestimo() {
	return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
	this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataPrevDevol() {
	return dataPrevDevol;
    }

    public void setDataPrevDevol(Date dataPrevDevol) {
	this.dataPrevDevol = dataPrevDevol;
    }

    public Date getDataDevolucao() {
	return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
	this.dataDevolucao = dataDevolucao;
    }

    public Double getMulta() {
	return multa;
    }

    public void setMulta(Double multa) {
	this.multa = multa;
    }
    
}
