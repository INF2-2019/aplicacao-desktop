 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.campi.Principal;

import app.biblioteca.campi.Alterar.AlteraController;
import app.biblioteca.campi.Alterar.AlteraMain;
import app.biblioteca.campi.Deletar.DeletaController;
import app.biblioteca.campi.Deletar.DeletaMain;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */
public class ModelTable {
    int id;
    String nome,cidade,uf;
    Button editar,deletar;

    

    

    public ModelTable(int id,String name, String cidade, String uf,Button deleta,Button edita){
        this.id = id;
        this.nome = name;
        this.cidade = cidade;
        this.deletar = deleta;
        this.uf=uf;
        this.deletar.setId("deleta");
        this.editar = edita;
        this.editar.setId("edita");
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Button getEditar() {
        return editar;
    }

    public void setEditar(Button editar) {
        this.editar = editar;
    }

    public Button getDeletar() {
        return deletar;
    }

    public void setDeletar(Button deletar) {
        this.deletar = deletar;
    }

    
    
}
