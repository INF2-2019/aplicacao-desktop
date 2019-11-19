package app.biblioteca.relatorios.relAcervo;

public class ModelTable {
   int id, campi, ano, paginas, edicao, isbn, volume, issn;
   String nome, local, editora, periodicidade, mes, subtipo, programa, tempo;

    public ModelTable(int id, int campi, int ano, int paginas, int edicao, int isbn, String nome, String local, String editora) {
        this.id = id;
        this.campi = campi;
        this.ano = ano;
        this.paginas = paginas;
        this.edicao = edicao;
        this.isbn = isbn;
        this.nome = nome;
        this.local = local;
        this.editora = editora;
    }

    public ModelTable(int id, int campi, int ano, int paginas, int volume,int issn, String nome, String local, String editora, String periodicidade, String mes, String subtipo) {
        this.id = id;
        this.campi = campi;
        this.ano = ano;
        this.paginas = paginas;
        this.volume = volume;
        this.nome = nome;
        this.local = local;
        this.editora = editora;
        this.periodicidade = periodicidade;
        this.mes = mes;
        this.subtipo = subtipo;
        this.issn = issn;
    }

    public ModelTable(int id, int campi, int ano, int paginas, String nome, String local, String editora, String programa) {
        this.id = id;
        this.campi = campi;
        this.ano = ano;
        this.paginas = paginas;
        this.nome = nome;
        this.local = local;
        this.editora = editora;
        this.programa = programa;
    }

    public ModelTable(int id, int campi, int ano, int paginas, String nome, String local, String editora, String subtipo, String tempo) {
        this.id = id;
        this.campi = campi;
        this.ano = ano;
        this.paginas = paginas;
        this.nome = nome;
        this.local = local;
        this.editora = editora;
        this.subtipo = subtipo;
        this.tempo = tempo;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCampi() {
        return campi;
    }

    public void setCampi(int campi) {
        this.campi = campi;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getIssn() {
        return issn;
    }

    public void setIssn(int issn) {
        this.issn = issn;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
   
    
   
}
