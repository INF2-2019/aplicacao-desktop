
package app.biblioteca.emprestimos;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Emprestimos {
    private int id;
    private long idAluno;
    private int idAcervo;
    private Date dataEmprestimo;
    private Date dataPrevDevolucao;
    private double multa;
    private Date devolucao;
    private Button alterar;
    private Button deletar;
    private Button devolver;

    public Emprestimos(int id, long idAluno, int idAcervo, Date dataEmprestimo, Date dataPrevDevolucao, Date devolucao,double multa, Button alterar, Button deletar, Button devolver) {
		
	this.id = id;
        this.idAluno = idAluno;
        this.idAcervo = idAcervo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevDevolucao = dataPrevDevolucao;
        this.devolucao = devolucao;
	this.multa = multa;
        this.alterar = alterar;
        this.alterar.setId("altera");
        this.deletar = deletar;
        this.deletar.setId("deleta");
        this.devolver = devolver;
        this.devolver.setId("devolver");
        alterar.setOnMouseClicked((MouseEvent event) -> {
            EditarMain editar = new EditarMain();
			ControllerEdita.setId(id);
            try {
                editar.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Emprestimos.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
        
         devolver.setOnMouseClicked((MouseEvent event) -> {
			try {
				TableControllerEmprestimos.devolve(id,dataPrevDevolucao);
			} catch (SQLException ex) {
				Logger.getLogger(Emprestimos.class.getName()).log(Level.SEVERE, null, ex);
			}
        });
        deletar.setOnMouseClicked((MouseEvent event) -> {
            DeletarMain mainDeleta = new DeletarMain();
            ControllerDeletar.setId(id);
            try {
                mainDeleta.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(Emprestimos.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdAcervo() {
        return idAcervo;
    }

    public void setIdAcervo(int idAcervo) {
        this.idAcervo = idAcervo;
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

    public Button getDevolver() {
        return devolver;
    }

    public void setDevolver(Button devolver) {
        this.devolver = devolver ;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataPrevDevolucao() {
        return dataPrevDevolucao;
    }

    public void setDataPrevDevolucao(Date dataPrevDevolucao) {
        this.dataPrevDevolucao = dataPrevDevolucao;
    }

    public Date getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(Date devolucao) {
        this.devolucao = devolucao;
    }

	public double getMulta() {
		return multa;
	}

	public void setMulta(double multa) {
		this.multa = multa;
	}
    
    
}
