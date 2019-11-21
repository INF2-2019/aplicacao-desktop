package app.diario.matriculas.principal;

import app.diario.matriculas.alterar.AlteraController;
import app.diario.matriculas.alterar.AlteraMain;
import app.diario.matriculas.deletar.DeletaController;
import app.diario.matriculas.deletar.DeletaMain;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Matricula {

	private int id;
	private String aluno, disciplina, ano, ativo;
	private Button deletaBtn, alteraBtn;

	public Matricula(int id, String aluno, String disciplina, String ano, String ativo, Button deletaBtn, Button alteraBtn) {
		this.id = id;
		this.ativo = ativo;
		this.ano = ano;
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.deletaBtn = deletaBtn;
		this.deletaBtn.setId("deleta");
		deletaBtn.setOnMouseClicked((ev) -> {
			DeletaMain dm = new DeletaMain();
			DeletaController.setId(id);
			try {
				dm.start(new Stage());
			} catch (Exception ex) {
				System.out.println(ex);
			}
		});

		this.alteraBtn = alteraBtn;
		this.alteraBtn.setId("altera");
		alteraBtn.setOnMouseClicked((ev) -> {
			AlteraMain am = new AlteraMain();
			AlteraController.setIdVelho(id);
			AlteraController.setAnoVelho(ano);
			if(ativo == "Sim") AlteraController.setAtivoVelho(1);
			else AlteraController.setAtivoVelho(0);
			try {
				am.start(new Stage());
			} catch (Exception ex) {
				System.out.println(ex);
			}
		});
	}

	public Matricula(int id, String aluno, String disciplina, String ano, String ativo) {
		this.id = id;
		this.ativo = ativo;
		this.ano = ano;
		this.aluno = aluno;
		this.disciplina = disciplina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Button getDeletaBtn() {
		return deletaBtn;
	}

	public void setDeletaBtn(Button deletaBtn) {
		this.deletaBtn = deletaBtn;
	}

	public Button getAlteraBtn() {
		return alteraBtn;
	}

	public void setAlteraBtn(Button alteraBtn) {
		this.alteraBtn = alteraBtn;
	}

}
