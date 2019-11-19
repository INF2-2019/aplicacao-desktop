package app.diario.relatorios.rel8e9.relatorioModels;

public class Alunos {
	
	private long id;
	private String nome;
        private boolean passou = true;
        private boolean temRegistro = true;
        private boolean temMatricula = true;
	
	public Alunos() {
		this.id = 0;
                this.nome = "";
	}
        
	public Alunos(long id, String nome) {
		this.id = id;
                this.nome = nome;
	}
        
	public Alunos(long id, String nome,boolean passou) {
		this.id = id;
                this.nome = nome;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
        }
        
        public boolean getPassou() {
		return passou;
	}

	public void setPassou(boolean passou) {
		this.passou = passou;
        }
        
        public boolean getTemMatricula() {
		return temMatricula;
	}

	public void setTemMatricula(boolean temMatricula) {
		this.temMatricula = temMatricula;
        }
        
        public boolean getTemRegistro() {
		return temRegistro;
        }
        public void setTemRegistro(boolean temRegistro) {
		this.temRegistro = temRegistro;
        }
	
}