
package app.biblioteca.relatorios.relMultas;


import java.sql.Date;
import javafx.scene.control.Label;

public class ModelTable {
    String id,idAlunos,idAcervo;
    Date dataEmprestimo,dataDevolucao;
    Double multa;

    
    public ModelTable(String id, String idAlunos, String idAcervo, Date dataEmprestimo, Date dataDevolucao, Double multa){
        this.id = id;
        this.idAlunos = idAlunos;
        this.idAcervo = idAcervo;
        this.dataEmprestimo = dataEmprestimo;
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
