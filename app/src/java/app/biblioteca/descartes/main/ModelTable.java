
package app.biblioteca.descartes.main;


import java.sql.Date;

public class ModelTable {
    String idAcervo, motivos, operador;
    Date dataDescarte;

   

    

    

    public ModelTable(String idAcervo, Date dataDescarte, String motivos, String operador){
        
        this.idAcervo = idAcervo;
        this.dataDescarte = dataDescarte;
        this.motivos=motivos;
	this.operador=operador;
        
    }
    
    

    public String getIdAcervo() {
	return idAcervo;
    }

    public void setIdAcervo(String idAcervo) {
	this.idAcervo = idAcervo;
    }

    public String getMotivos() {
	return motivos;
    }

    public void setMotivos(String motivos) {
	this.motivos = motivos;
    }

    public String getOperador() {
	return operador;
    }

    public void setOperador(String operador) {
	this.operador = operador;
    }

    public Date getDataDescarte() {
	return dataDescarte;
    }

    public void setDataDescarte(Date dataDescarte) {
	this.dataDescarte = dataDescarte;
    }

    
    
}
