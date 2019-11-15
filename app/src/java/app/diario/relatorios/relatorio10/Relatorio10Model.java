package app.diario.relatorios.relatorio10;

public class Relatorio10Model {
	private String disciplina;
	private double nota, etapa1, etapa2, etapa3, etapa4;

	public Relatorio10Model(String disciplina, double nota, double etapa1, double etapa2, double etapa3, double etapa4) {
		this.disciplina = disciplina;
		this.nota = nota;
		this.etapa1 = etapa1;
		this.etapa2 = etapa2;
		this.etapa3 = etapa3;
		this.etapa4 = etapa4;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public double getEtapa1() {
		return etapa1;
	}

	public void setEtapa1(double etapa1) {
		this.etapa1 = etapa1;
	}

	public double getEtapa2() {
		return etapa2;
	}

	public void setEtapa2(double etapa2) {
		this.etapa2 = etapa2;
	}

	public double getEtapa3() {
		return etapa3;
	}

	public void setEtapa3(double etapa3) {
		this.etapa3 = etapa3;
	}

	public double getEtapa4() {
		return etapa4;
	}

	public void setEtapa4(double etapa4) {
		this.etapa4 = etapa4;
	}
	
	
}
