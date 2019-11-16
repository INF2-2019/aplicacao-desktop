package app.diario.turmas.principal;

import app.diario.turmas.alterar.AlteraController;
import app.diario.turmas.alterar.AlteraMain;
import app.diario.turmas.deletar.DeletaController;
import app.diario.turmas.deletar.DeletaMain;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Turma {

	private int id;
	private String cursos, nome;
	private Button deletaBtn, alteraBtn;

	public Turma(int id, String cursos, String nome) {
		this.id = id;
		this.cursos = cursos;
		this.nome = nome;
	}

	public Turma(int id, String cursos, String nome, Button deletaBtn, Button alteraBtn) {
		this.id = id;
		this.cursos = cursos;
		this.nome = nome;
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
			try {
				am.start(new Stage());
			} catch (Exception ex) {
				System.out.println(ex);
			}
		});
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCursos() {
		return cursos;
	}

	public void setCursos(String cursos) {
		this.cursos = cursos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Button getDeletaBtn() {
		return deletaBtn;
	}

	public void setDeletaBtn(Button deleteBtn) {
		this.deletaBtn = deleteBtn;
	}

	public Button getAlteraBtn() {
		return alteraBtn;
	}

	public void setAlteraBtn(Button alteraBtn) {
		this.alteraBtn = alteraBtn;
	}

}
