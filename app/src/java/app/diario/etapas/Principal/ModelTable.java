package app.diario.etapas.Principal;

import app.diario.etapas.PacoteAltera.AlteraController;
import app.diario.etapas.PacoteAltera.AlteraMain;
import app.diario.etapas.PacoteDeleta.DeletaController;
import app.diario.etapas.PacoteDeleta.DeletaMain;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModelTable {
    int id, ano;
    double valor;
    Button deleta,edita;

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


    public ModelTable(int id,int ano, double valor, Button edita,Button deleta){
        this.id = id;
        this.ano = ano;
        this.valor = valor;
        this.deleta = deleta;
        this.deleta.setId("deleta");
        this.edita = edita;
        this.edita.setId("edita");
		deleta.setOnMouseClicked((MouseEvent event) -> {
			System.out.println("clicou");
            DeletaMain mainDeleta = new DeletaMain();
            DeletaController.setId(id);
            try {
                mainDeleta.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ModelTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        edita.setOnMouseClicked((MouseEvent event) -> {
			AlteraMain mainAltera = new AlteraMain();
			AlteraController.setId(id);
			try {
				mainAltera.start(new Stage());
			} catch (Exception ex) {
				Logger.getLogger(ModelTable.class.getName()).log(Level.SEVERE, null, ex);
			}
	    
		});
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
