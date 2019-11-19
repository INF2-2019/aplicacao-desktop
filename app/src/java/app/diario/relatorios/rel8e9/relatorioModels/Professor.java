package app.diario.relatorios.rel8e9.relatorioModels;

import java.util.LinkedList;
import java.util.List;

public class Professor {
	
    //variaveis para professor
	private int id, idDept;
	private String nome;
        
    //varaveis para curso
        private int idCurso;
	private String nomeCurso;
    
    //varaveis para turma
        private int idTurma;
        
        private String nomeDisciplina;
        private String cargaH;

	public Professor() {
		this.id = 0;
                this.idDept = 0;
                this.idCurso = 0;
                this.idTurma = 0;
                this.nome = "";
                this.nomeCurso = "";
 
	}
	
	public Professor(String cargaH, String nomeDisciplina, boolean x) {
		this.cargaH = cargaH;
                this.nomeDisciplina = nomeDisciplina;
	}
        
        public Professor(String nomeCurso) {
                this.nomeCurso = nomeCurso;
	}
	
        
        public Professor(int id, String nomeCurso, String nome) {
		this.id = id;
		this.nomeCurso = nomeCurso;
		this.nome = nome; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

        public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
        
        public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
        
	public int getIdDept() {
		return idDept;
	}

	public void getIdDept(int idDept) {
            this.setIdDept(idDept);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
        
        public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

    /**
     * @param idDept the idDept to set
     */
    public void setIdDept(int idDept) {
        this.idDept = idDept;
    }

    /**
     * @return the nomeDisciplina
     */
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    /**
     * @param nomeDisciplina the nomeDisciplina to set
     */
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    /**
     * @return the cargaH
     */
    public String getCargaH() {
        return cargaH;
    }

    /**
     * @param cargaH the cargaH to set
     */
    public void setCargaH(String cargaH) {
        this.cargaH = cargaH;
    }
        
       
	
}