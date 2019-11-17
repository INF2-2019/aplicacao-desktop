package app.diario.disciplinas.principal;

import app.diario.disciplinas.alterar.AlterarController;
import app.diario.disciplinas.alterar.AlterarMain;
import app.diario.disciplinas.deleta.ControllerDeleta;
import app.diario.disciplinas.deleta.DeletaMain;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Disciplinas {

	private int id;

	private int idTurma;
	private String nome;
	private int CargaHoraria;
	private Button edita, deleta;

	public Disciplinas(int id, int idTurma, String nome, int CargaHoraria, Button edita, Button deleta) {
		this.id = id;
		this.idTurma = idTurma;
		this.nome = nome;
		this.CargaHoraria = CargaHoraria;
		this.edita = edita;
		this.edita.setId("edita");
		this.deleta = deleta;
		this.deleta.setId("deleta");
		edita.setOnMouseClicked((MouseEvent event) -> {
			AlterarMain mainAltera = new AlterarMain();
			AlterarController.setNome(nome);
			try {
				mainAltera.start(new Stage());
			} catch (Exception ex) {
				Logger.getLogger(Disciplinas.class.getName()).log(Level.SEVERE, null, ex);
			}

		});
		deleta.setOnMouseClicked((MouseEvent event) -> {
			DeletaMain mainDeleta = new DeletaMain();
			ControllerDeleta.setNome(nome);
			try {
				mainDeleta.start(new Stage());
			} catch (Exception ex) {
				Logger.getLogger(Disciplinas.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return CargaHoraria;
	}

	public void setCargaHoraria(int CargaHoraria) {
		this.CargaHoraria = CargaHoraria;
	}

	public Button getEdita() {
		return edita;
	}

	public void setEdita(Button edita) {
		this.edita = edita;
	}

	public Button getDeleta() {
		return deleta;
	}

	public void setDeleta(Button deleta) {
		this.deleta = deleta;
	}
}
