package app.biblioteca.reservas;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Reservas {
    private int id;
    private long idAluno;
    private int idAcervo;
    private Date dataReserva;
    private int tempoEspera;
    private String emprestou;
    private Button alterar;
    private Button deletar;
    private Button emprestar;

    public Reservas(int id, long idAluno,int idAcervo,Date dataReserva, int tempoEspera,  boolean emprestou, Button alterar, Button deletar, Button emprestar) {
        this.id = id;
        this.idAluno = idAluno;
        this.idAcervo = idAcervo;
        this.dataReserva = dataReserva;
        this.tempoEspera = tempoEspera;
		if(emprestou == true)
        this.emprestou = "Sim";
		else this.emprestou = "Não";
        this.alterar = alterar;
	this.alterar.setId("alterar");
        this.deletar = deletar;
	this.deletar.setId("deletar");
        this.emprestar = emprestar;
	this.emprestar.setId("emprestar");
        alterar.setOnMouseClicked((MouseEvent event) -> {
            EditarMain editar = new EditarMain();
            ControllerEditar.setId(id);
            try {
                editar.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
         emprestar.setOnMouseClicked((MouseEvent event) -> {
	    try {
		TableControllerReservas.emprestar(id,idAluno,idAcervo,dataReserva);
	    } catch (SQLException ex) {
		Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (AlunoException ex) {
		Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (Exception ex) {
                Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        deletar.setOnMouseClicked((MouseEvent event) -> {
            DeletarMain mainDeleta = new DeletarMain();
            ControllerDeletar.setId(id);
            try {
                mainDeleta.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAcervo() {
        return idAcervo;
    }

    public void setIdAcervo(int idAcervo) {
        this.idAcervo = idAcervo;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String isEmprestou() {
        return emprestou;
    }

    public void setEmprestou(String emprestou) {
        this.emprestou = emprestou;
    }

    public Button getAlterar() {
        return alterar;
    }

    public void setAlterar(Button alterar) {
        this.alterar = alterar;
    }

    public Button getDeletar() {
        return deletar;
    }

    public void setDeletar(Button deletar) {
        this.deletar = deletar;
    }

    public Button getEmprestar() {
        return emprestar;
    }

    public void setEmprestar(Button emprestar) {
        this.emprestar = emprestar;
    }
    
    
    
    
    
}
