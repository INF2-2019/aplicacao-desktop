package app.biblioteca.emprestimos;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
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

public class TableControllerEmprestimos implements Initializable {

    private ObservableList<Emprestimos> tabelaEmprestimos;

    @FXML
    private TableView<Emprestimos> tabela;

    @FXML
    private TableColumn<Emprestimos, Long> ID;

    @FXML
    private TableColumn<Emprestimos, Integer> IDAluno;

    @FXML
    private TableColumn<Emprestimos, Integer> IDAcervo;

    @FXML
    private TableColumn<Emprestimos, Date> DataEmprestimo;

    @FXML
    private TableColumn<Emprestimos, Date> DataPrevDevolucao;

    @FXML
    private TableColumn<Emprestimos, Date> DataDevolucao;

    @FXML
    private TableColumn<Emprestimos, Double> Multa;

    @FXML
    private TableColumn<Emprestimos, Button> Alterar;

    @FXML
    private TableColumn<Emprestimos, Button> Deletar;

    @FXML
    private TableColumn<Emprestimos, Button> Devolver;

    public static ObservableList<Emprestimos> oblist = FXCollections.observableArrayList();

    public TableView<Emprestimos> getTabela() {
	return tabela;
    }

    public void setTabela(TableView<Emprestimos> tabela) {
	this.tabela = tabela;
    }

    public TableColumn<Emprestimos, Long> getID() {
	return ID;
    }

    public void setID(TableColumn<Emprestimos, Long> ID) {
	this.ID = ID;
    }

    public TableColumn<Emprestimos, Integer> getIDAluno() {
	return IDAluno;
    }

    public void setIDAluno(TableColumn<Emprestimos, Integer> IDAluno) {
	this.IDAluno = IDAluno;
    }

    public TableColumn<Emprestimos, Integer> getIDAcervo() {
	return IDAcervo;
    }

    public void setIDAcervo(TableColumn<Emprestimos, Integer> IDAcervo) {
	this.IDAcervo = IDAcervo;
    }

    public ObservableList<Emprestimos> getTabelaEmprestimos() {
	return tabelaEmprestimos;
    }

    public void setTabelaEmprestimos(ObservableList<Emprestimos> tabelaEmprestimos) {
	this.tabelaEmprestimos = tabelaEmprestimos;
    }

    public TableColumn<Emprestimos, Date> getDataEmprestimo() {
	return DataEmprestimo;
    }

    public void setDataEmprestimo(TableColumn<Emprestimos, Date> DataEmprestimo) {
	this.DataEmprestimo = DataEmprestimo;
    }

    public TableColumn<Emprestimos, Date> getDataPrevDevolucao() {
	return DataPrevDevolucao;
    }

    public void setDataPrevDevolucao(TableColumn<Emprestimos, Date> DataPrevDevolucao) {
	this.DataPrevDevolucao = DataPrevDevolucao;
    }

    public TableColumn<Emprestimos, Date> getDataDevolucao() {
	return DataDevolucao;
    }

    public void setDataDevolucao(TableColumn<Emprestimos, Date> DataDevolucao) {
	this.DataDevolucao = DataDevolucao;
    }

    public TableColumn<Emprestimos, Double> getMulta() {
	return Multa;
    }

    public void setMulta(TableColumn<Emprestimos, Double> Multa) {
	this.Multa = Multa;
    }

    public static ObservableList<Emprestimos> getOblist() {
	return oblist;
    }

    public static void setOblist(ObservableList<Emprestimos> oblist) {
	TableControllerEmprestimos.oblist = oblist;
    }

    public TableColumn<Emprestimos, Button> getAlterar() {
	return Alterar;
    }

    public void setAlterar(TableColumn<Emprestimos, Button> Alterar) {
	this.Alterar = Alterar;
    }

    public TableColumn<Emprestimos, Button> getDeletar() {
	return Deletar;
    }

    public void setDeletar(TableColumn<Emprestimos, Button> Deletar) {
	this.Deletar = Deletar;
    }

    public TableColumn<Emprestimos, Button> geDevovler() {
	return Devolver;
    }

    public void setDevolver(TableColumn<Emprestimos, Button> Devolver) {
	this.Devolver = Devolver;
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
	DataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
	DataPrevDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataPrevDevolucao"));
	DataDevolucao.setCellValueFactory(new PropertyValueFactory<>("devolucao"));
	Multa.setCellValueFactory(new PropertyValueFactory<>("multa"));
	Alterar.setCellValueFactory(new PropertyValueFactory<>("Alterar"));
	Deletar.setCellValueFactory(new PropertyValueFactory<>("Deletar"));
	Devolver.setCellValueFactory(new PropertyValueFactory<>("Devolver"));
	tabela.setItems(oblist);
    }

    public void consultaBD() {
	try {
	    try (Connection connection = BDConnection.getConnection()) {
		ResultSet rs = connection.createStatement().executeQuery("select * from `emprestimos`");
		oblist = FXCollections.observableArrayList();
		while (rs.next()) {
		    System.out.println(rs.getDouble("multa"));
		    oblist.add(new Emprestimos(rs.getInt("id"), rs.getLong("id-alunos"), rs.getInt("id-acervo"), rs.getDate("data-emprestimo"), rs.getDate("data-prev-devol"), rs.getDate("data-devolucao"), rs.getDouble("multa"), new Button("EDITAR"), new Button("DELETAR"), new Button("DEVOLVER")));
		}
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(TableControllerEmprestimos.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @FXML
    void AdicionarEmprestimo(ActionEvent event) throws IOException {

	Stage s1 = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/emprestimos/ModalInserir.fxml"));
	Scene scene = new Scene(root);

	s1.setScene(scene);
	s1.setResizable(false);
	s1.show();

    }

    public void deletarEmprestimo(int id) {
	//ACHO, SÓ ACHO MSM que essa função deve tá certa
	try {
	    Connection connection = BDConnection.getConnection();
	    try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM emprestimos where id = ?")) {
		stmt.setInt(1, id);
		stmt.execute();
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public boolean AlterarEmprestimo(TextField IDAluno, TextField IDAcervo, DatePicker DataEmprestimo, DatePicker DataPrevDevolucao, DatePicker DataDevolucao, int id) throws NumberFormatException, Exception {
	System.out.println(DataEmprestimo);
	int adcs = 0;
	ResultSet resultadoBusca;
	PreparedStatement ps;
	int cont = 1;
	Connection con = BDConnection.getConnection();
	boolean[] pars = new boolean[5];
	for (int i = 0; i < 3; i++) {
	    pars[i] = false;
	}
	String query = "UPDATE emprestimos SET";
	if (!"".equals(IDAluno.getText())) {
	try{
	    ps = con.prepareStatement("SELECT * FROM `alunos` WHERE `id` = ?");
	    ps.setLong(1, Long.parseLong(IDAluno.getText()));
	    resultadoBusca = ps.executeQuery();

	    if (!resultadoBusca.next()) {
		ErroMain erro = new ErroMain("Não existe esse aluno.");
		erro.start(new Stage());
		return false;
	    }
	}catch(SQLException ex){
	    ErroMain erro = new ErroMain("Os dados inseridos estão formatados errado.");
		erro.start(new Stage());
		return false;
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
		return false;
	    }
	    if (adcs > 0) {
		query += ",";
	    }
	    query += " `id-acervo`= ?";
	    adcs++;
	    pars[1] = true;
	}
	System.out.println(DataEmprestimo.getValue());
	if (DataEmprestimo.getValue() != null) {
	    if (adcs > 0) {
		query += ",";
	    }
	    query += " `data-emprestimo`= ?";
	    pars[2] = true;
	}
	if (DataPrevDevolucao.getValue() != null) {
	    if (adcs > 0) {
		query += ",";
	    }
	    query += " `data-prev-devol`= ?";
	    pars[3] = true;
	}
	if (DataDevolucao.getValue() != null) {
	    if (adcs > 0) {
		query += ",";
	    }
	    query += " `data-devolucao`= ?";
	    pars[4] = true;
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
	    Date emprestimo = Date.from(DataEmprestimo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    ps.setDate(cont, new java.sql.Date(emprestimo.getTime()));
	    cont++;
	}
	if (pars[3]) {
	    Date preDevolv = Date.from(DataPrevDevolucao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    ps.setDate(cont, new java.sql.Date(preDevolv.getTime()));
	    cont++;
	}
	if (pars[4]) {
	    Date devolv = Date.from(DataDevolucao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	    ps.setDate(cont, new java.sql.Date(devolv.getTime()));
	    cont++;
	}

	ps.setInt(cont, id);

	int sucesso = ps.executeUpdate();

	return false;
    }

    public void atualizarTabela() {

    }

    public static void devolve(int id, Date dataPrevDevolucao) throws SQLException {
	Connection con = BDConnection.getConnection();
	String sql;
	double multa = 0.00;
	java.util.Date dataDevolucao = new java.util.Date();
	if (!dataDevolucao.after(dataPrevDevolucao)) {
	    multa = 0.00;
	} else {
	    long tempoEmprestimo = dataDevolucao.getTime() - dataPrevDevolucao.getTime();
	    int days = (int) (tempoEmprestimo / 86400000) - 1;
	    multa = days * 0.50;
	}
	sql = "UPDATE `emprestimos` SET `data-devolucao` = ?, `multa` = ? WHERE `id` = ?";

	PreparedStatement stat = con.prepareStatement(sql);
	stat.setDate(1, new java.sql.Date(dataDevolucao.getTime()));
	stat.setDouble(2, multa);
	stat.setInt(3, id);
	stat.executeUpdate();
    }

    public void AtualizaTabela() {
	consultaBD();
	CriarTabela();
    }

}
