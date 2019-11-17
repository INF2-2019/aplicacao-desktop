 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.alunos.Principal;


import app.diario.alunos.Alterar.AlteraController;
import app.diario.alunos.Alterar.AlteraMain;
import app.diario.alunos.Deletar.DeletaController;
import app.diario.alunos.Deletar.DeletaMain;
import app.diario.alunos.Informar.InfoController;
import app.diario.alunos.Informar.InfoMain;
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
    long cpf;
    String nome,email;
    Button info,editar,deletar;

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInfo(Button info) {
        this.info = info;
    }

    public void setEditar(Button editar) {
        this.editar = editar;
    }

    public void setDeletar(Button deletar) {
        this.deletar = deletar;
    }

    

    public ModelTable(long cpf,String name, String email, Button edita,Button deleta,Button info){
        this.cpf = cpf;
        this.nome = name;
        this.email = email;
        this.deletar = deleta;
        this.deletar.setId("deleta");
        this.editar = edita;
        this.editar.setId("edita");
        this.info=info;
        this.info.setId("info");
	deleta.setOnMouseClicked((MouseEvent event) -> {
	    System.out.println("clicou");
            DeletaMain mainDeleta = new DeletaMain();
            DeletaController.setId(cpf);
            try {
                mainDeleta.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(ModelTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        edita.setOnMouseClicked((MouseEvent event) -> {
            
		AlteraMain mainAltera = new AlteraMain();
		AlteraController.setId(cpf);
		try {
		    mainAltera.start(new Stage());
		} catch (Exception ex) {
		    Logger.getLogger(ModelTable.class.getName()).log(Level.SEVERE, null, ex);
		}
	    
	});
            info.setOnMouseClicked((MouseEvent event) -> {
            
		InfoMain mainInfo = new InfoMain();
		InfoController.setId(cpf);
		try {
		    mainInfo.start(new Stage());
		} catch (Exception ex) {
		    Logger.getLogger(ModelTable.class.getName()).log(Level.SEVERE, null, ex);
		}
	    
	});
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Button getInfo() {
        return info;
    }

    public Button getEditar() {
        return editar;
    }

    public Button getDeletar() {
        return deletar;
    }

    
}
