package app.biblioteca.reservas;

import app.biblioteca.telatransicao.MainTelaTransicaoBiblioteca;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TableControllerReservas implements Initializable {

    private ObservableList<Reservas> tabelaEmprestimos;

    @FXML
    private TableView<Reservas> tabela;

    @FXML
    private TableColumn<Reservas, Integer> ID;

    @FXML
    private TableColumn<Reservas, Long> IDAluno;

    @FXML
    private TableColumn<Reservas, Integer> IDAcervo;

    @FXML
    private TableColumn<Reservas, Integer> TempoEspera;

    @FXML
    private TableColumn<Reservas, Date> DataReserva;

    @FXML
    private TableColumn<Reservas, String> Emprestou;

    @FXML
    private TableColumn<Reservas, Button> Alterar;

    @FXML
    private TableColumn<Reservas, Button> Deletar;

    @FXML
    private TableColumn<Reservas, Button> Emprestar;

    public static ObservableList<Reservas> oblist = FXCollections.observableArrayList();

    public ObservableList<Reservas> getTabelaEmprestimos() {
	return tabelaEmprestimos;
    }

    public void setTabelaEmprestimos(ObservableList<Reservas> tabelaEmprestimos) {
	this.tabelaEmprestimos = tabelaEmprestimos;
    }

    public TableView<Reservas> getTabela() {
	return tabela;
    }

    public void setTabela(TableView<Reservas> tabela) {
	this.tabela = tabela;
    }

    public TableColumn<Reservas, Integer> getID() {
	return ID;
    }

    public void setID(TableColumn<Reservas, Integer> ID) {
	this.ID = ID;
    }

    public TableColumn<Reservas, Long> getIDAluno() {
	return IDAluno;
    }

    public void setIDAluno(TableColumn<Reservas, Long> IDAluno) {
	this.IDAluno = IDAluno;
    }

    public TableColumn<Reservas, Integer> getIDAcervo() {
	return IDAcervo;
    }

    public void setIDAcervo(TableColumn<Reservas, Integer> IDAcervo) {
	this.IDAcervo = IDAcervo;
    }

    public TableColumn<Reservas, Integer> getTempoEspera() {
	return TempoEspera;
    }

    public void setTempoEspera(TableColumn<Reservas, Integer> TempoEspera) {
	this.TempoEspera = TempoEspera;
    }

    public TableColumn<Reservas, Date> getDataReserva() {
	return DataReserva;
    }

    public void setDataReserva(TableColumn<Reservas, Date> DataReserva) {
	this.DataReserva = DataReserva;
    }

    public TableColumn<Reservas, Button> getAlterar() {
	return Alterar;
    }

    public void setAlterar(TableColumn<Reservas, Button> Alterar) {
	this.Alterar = Alterar;
    }

    public TableColumn<Reservas, Button> getDeletar() {
	return Deletar;
    }

    public void setDeletar(TableColumn<Reservas, Button> Deletar) {
	this.Deletar = Deletar;
    }

    public TableColumn<Reservas, Button> getEmprestar() {
	return Emprestar;
    }

    public void setEmprestar(TableColumn<Reservas, Button> Emprestar) {
	this.Emprestar = Emprestar;
    }

    public TableColumn<Reservas, String> getEmprestou() {
	return Emprestou;
    }

    public void setEmprestou(TableColumn<Reservas, String> Emprestou) {
	this.Emprestou = Emprestou;
    }

    public static ObservableList<Reservas> getOblist() {
	return oblist;
    }

    public static void setOblist(ObservableList<Reservas> oblist) {
	TableControllerReservas.oblist = oblist;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	tabelaEmprestimos = FXCollections.observableArrayList();

	consultaBD();
	CriarTabela();

    }

    public void CriarTabela() {
	tabela.refresh();

	ID.setCellValueFactory(new PropertyValueFactory<>("id"));
	IDAluno.setCellValueFactory(new PropertyValueFactory<>("idAluno"));
	IDAcervo.setCellValueFactory(new PropertyValueFactory<>("idAcervo"));
	DataReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));
	TempoEspera.setCellValueFactory(new PropertyValueFactory<>("tempoEspera"));
	Emprestou.setCellValueFactory(new PropertyValueFactory<>("emprestou"));
	Alterar.setCellValueFactory(new PropertyValueFactory<>("Alterar"));
	Deletar.setCellValueFactory(new PropertyValueFactory<>("Deletar"));
	Emprestar.setCellValueFactory(new PropertyValueFactory<>("Emprestar"));
	tabela.setItems(oblist);
    }

    public void consultaBD() {
	try {
	    try (Connection connection = BDConnection.getConnection()) {
		ResultSet rs = connection.createStatement().executeQuery("select * from `reservas`");
		oblist = FXCollections.observableArrayList();
		while (rs.next()) {
		    oblist.add(new Reservas(rs.getInt("id"), rs.getLong("id-alunos"), rs.getInt("id-acervo"), rs.getDate("data-reserva"), rs.getInt("tempo-espera"), rs.getBoolean("emprestou"), new Button("ALTERAR"), new Button("DELETAR"), new Button("EMPRESTAR")));
		}
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(TableControllerReservas.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @FXML
    void AdicionarReservas(ActionEvent event) throws IOException {

	Stage s1 = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/reservas/InserirReservas.fxml"));
	Scene scene = new Scene(root);

	s1.setScene(scene);
	s1.setResizable(false);
	s1.show();

    }

    public void deletarReservas(int id) {
	try {
	    Connection connection = BDConnection.getConnection();
	    try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM reservas where id = ?")) {
		stmt.setInt(1, id);
		stmt.execute();
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void AlterarReserva(TextField IDAluno, TextField IDAcervo, DatePicker DataReserva, TextField tempoEspera, int id) throws SQLException, Exception {
	System.out.println(DataReserva);
	int adcs = 0;
	int cont = 1;
	PreparedStatement ps;
	ResultSet resultadoBusca;
	Connection con = BDConnection.getConnection();
	boolean[] pars = new boolean[5];
	for (int i = 0; i < 3; i++) {
	    pars[i] = false;
	}
	String query = "UPDATE reservas SET";
	if (!"".equals(IDAluno.getText())) {
	    try{
	    ps = con.prepareStatement("SELECT * FROM `alunos` WHERE `id` = ?");
	    ps.setLong(1, Long.parseLong(IDAluno.getText()));
	    resultadoBusca = ps.executeQuery();

	    if (!resultadoBusca.next()) {
		ErroMain erro = new ErroMain("Não existe esse aluno.");
		erro.start(new Stage());
		return ;
	    }
	}catch(SQLException ex){
	    ErroMain erro = new ErroMain("Os dados inseridos estão formatados errado.");
		erro.start(new Stage());
		return ;
	}
	    query += " `id-alunos`= ?";
	    adcs++;
	    pars[0] = true;
	}
	if (!"".equals(IDAcervo.getText())) {
	    ps = con.prepareStatement("SELECT * FROM `acervo` WHERE `id` = ?");
	    ps.setInt(1, Integer.parseInt(IDAcervo.getText()));
	    resultadoBusca = ps.executeQuery();

	    if (!resultadoBusca.next()) {
		ErroMain erro = new ErroMain("Não existe esse acervo.");
		erro.start(new Stage());
		return ;
	    }
	    if (adcs > 0) {
		query += ",";
	    }
	    query += " `id-acervo`= ?";
	    adcs++;
	    pars[1] = true;
	}
	if (DataReserva.getValue() != null) {
	    if (adcs > 0) {
		query += ",";
	    }
	    query += " `data-reserva`= ?";
	    pars[2] = true;
	}
	if (!"".equals(tempoEspera.getText())) {
	    if (adcs > 0) {
		query += ",";
	    }
	    query += " `tempo-espera`= ?";
	    pars[3] = true;
	}

	query += " WHERE `id` = ? ";
	System.out.println(query);
	 ps = con.prepareStatement(query);

	if (pars[0]) {
	    ps.setString(cont, IDAluno.getText());
	    cont++;
	}
	if (pars[1]) {
	    ps.setString(cont, IDAcervo.getText());
	    cont++;
	}
	if (pars[2]) {
	    Date emprestimo = Date.from(DataReserva.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    ps.setDate(cont, new java.sql.Date(emprestimo.getTime()));
	    cont++;
	}
	if (pars[3]) {
	    ps.setString(cont, tempoEspera.getText());
	    cont++;
	}

	ps.setInt(cont, id);

	int sucesso = ps.executeUpdate();

    }

    public void salvarAlteracaoReserva() {

    }

    public static void emprestar(int id, long idAluno,int idAcervo, Date dataReserva) throws SQLException, AlunoException, Exception {
	String sql;
	Date dataEmp = new java.util.Date(new Date().getTime());
	int tempoEspera = 0;
	Connection con = BDConnection.getConnection();
	PreparedStatement ps = con.prepareStatement("SELECT * FROM `emprestimos` WHERE `id-acervo` = ? AND `data-devolucao`= '1970-01-01'");
	ps.setInt(1, idAcervo);
	ResultSet busca = ps.executeQuery();
	while (busca.next()) {
	     ErroMain erro = new ErroMain("Este livro, atualmente, já está emprestado");
	      erro.start(new Stage());
	      return ;
	}
	if (!dataEmp.after(dataReserva)) {
	    tempoEspera = 0;
	} else {
	    long tempoEmprestimo = dataEmp.getTime() - dataReserva.getTime();
	    int days = (int) (tempoEmprestimo / 86400000);
	    if (days != 0) {
		days -= 1;
	    }
	    tempoEspera = days;
	}
	sql = "UPDATE `reservas` SET `tempo-espera` = ?, `emprestou` = ? WHERE `id` = ?";
	PreparedStatement stat = con.prepareStatement(sql);
	stat.setInt(1, tempoEspera);
	stat.setBoolean(2, true);
	stat.setInt(3, id);
	stat.executeUpdate();
	Date dataEmprestimo = new Date(new Date().getTime());
	Calendar c = Calendar.getInstance();
	c.setTime(dataEmprestimo);
	c.add(Calendar.DATE, +7);
	Date dataPrevDevol = c.getTime();
	Date dataDevolucao = new Date(0);
	double multa = 0.00;
	ps = con.prepareStatement("INSERT INTO `emprestimos` (`id-alunos`, `id-acervo`, `data-emprestimo`, `data-prev-devol`, `data-devolucao`, `multa`) VALUES (?, ?, ?, ?, ?, ?)");
	ps.setLong(1, idAluno);
	ps.setInt(2, idAcervo);
	ps.setDate(3, new java.sql.Date(dataEmprestimo.getTime()));
	ps.setDate(4, new java.sql.Date(dataPrevDevol.getTime()));
	ps.setDate(5, new java.sql.Date(dataDevolucao.getTime()));
	ps.setDouble(6, multa);
	ps.executeUpdate();
    }
    
    @FXML
    public void Voltar(javafx.event.ActionEvent event){
        MainTelaTransicaoBiblioteca voltar = new MainTelaTransicaoBiblioteca(); 
        try{
            voltar.start(new Stage());
        }catch(Exception ex){
             Logger.getLogger(TableControllerReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainReservas.getStage().close();
    }
    
    public void atualizarTabela() {
	consultaBD();
	CriarTabela();
    }
}
