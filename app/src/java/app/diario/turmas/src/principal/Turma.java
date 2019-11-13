package principal;

import alterar.AlteraController;
import alterar.AlteraMain;
import deletar.DeletaController;
import deletar.DeletaMain;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Turma {

    private int id, idCursos;
    private String nome;
	private Button deletaBtn, alteraBtn;

	public Turma(int id, int idCursos, String nome, Button deletaBtn, Button alteraBtn) {
		this.id = id;
		this.idCursos = idCursos;
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

	public int getIdCursos() {
		return idCursos;
	}

	public void setIdCursos(int idCursos) {
		this.idCursos = idCursos;
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
