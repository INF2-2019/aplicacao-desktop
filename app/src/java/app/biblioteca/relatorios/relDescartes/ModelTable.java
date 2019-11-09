
package app.biblioteca.relatorios.relDescartes;


import java.sql.Date;
import javafx.scene.control.Label;

public class ModelTable {
    String idAcervo, motivos, operador;
    Date dataDescarte;

    Label estado;

    public Label getEstado() {
        return estado;
    }

    public void setEstado(Label estado) {
        this.estado = estado;
    }

    

    

    public ModelTable(String idAcervo, Date dataDescarte, String motivos, String operador, Label estado){
        
        this.idAcervo = idAcervo;
        this.dataDescarte = dataDescarte;
        this.motivos=motivos;
	this.operador=operador;
        this.estado=estado;
        
        this.estado.setId("estado");
        this.estado.setText("algo");
        
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
