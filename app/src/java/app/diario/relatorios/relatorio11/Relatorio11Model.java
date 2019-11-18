package app.diario.relatorios.relatorio11;

public class Relatorio11Model {
	private String conteudo, data;
	private double valor;

	public Relatorio11Model(String conteudo, String data, double valor) {
		this.conteudo = conteudo;
		this.data = data;
		this.valor = valor;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
