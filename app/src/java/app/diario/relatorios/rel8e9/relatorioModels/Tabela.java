package app.diario.relatorios.rel8e9.relatorioModels;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import app.diario.relatorios.rel8e9.main.*;
import app.diario.relatorios.rel8e9.Controllers.*;
import java.io.IOException;

/**
 *
 * @author Aluno
 */
public class Tabela {
    long cpf;
    String nome;
    Button info;

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public void setInfo(Button info) {
        this.info = info;
    }


    

    public Tabela(long cpf,String name, Button info){
        this.cpf = cpf;
        this.nome = name;
        this.info=info;
        this.info.setId("info");
        
        info.setOnMouseClicked((MouseEvent event) -> {
                modalMain abre = new modalMain() {};
                InfoController.setId(cpf);
                
            try {
                abre.start();
            } catch (IOException ex) {
                Logger.getLogger(Tabela.class.getName()).log(Level.SEVERE, null, ex);
            }
          
		
	    
	}); 
           
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    
    public Button getInfo() {
        return info;
    }

   

    
}
