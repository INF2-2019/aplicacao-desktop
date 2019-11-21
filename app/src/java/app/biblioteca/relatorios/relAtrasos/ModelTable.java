
package app.biblioteca.relatorios.relAtrasos;


import java.sql.Date;
import javafx.scene.control.Label;

public class ModelTable {
    String id,idAlunos,idAcervo,atraso;

    

    public ModelTable(String id, String idAlunos, String idAcervo,  String atraso){
        this.id = id;
        this.idAlunos = idAlunos;
        this.idAcervo = idAcervo;
        this.atraso = atraso;
        
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

    public String getAtraso() {
	return atraso;
    }

    public void setAtraso(String atraso) {
	this.atraso = atraso;
    }

    
}
