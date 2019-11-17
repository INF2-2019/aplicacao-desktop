package app.diario.disciplinas.principal;

import app.diario.telatransicao.MainTelaTransicaoDiario;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableControllerDisciplinas implements Initializable {

	private ObservableList<Disciplinas> tabelaDisciplinas;

	@FXML
	private TableView<Disciplinas> tabela;

	@FXML
	private TableColumn<Disciplinas, Integer> ID;

	@FXML
	private TableColumn<Disciplinas, String> Nome;

	@FXML
	private TableColumn<Disciplinas, Integer> IDTurmas;

	@FXML
	private TableColumn<Disciplinas, Integer> CargaHoraria;

	@FXML
	private TableColumn<Disciplinas, Button> Alterar;

	@FXML
	private TableColumn<Disciplinas, Button> Deletar;

	public static ObservableList<Disciplinas> oblist = FXCollections.observableArrayList();

	private void loadTableData() {

		tabela.setItems(tabelaDisciplinas);
		tabela.setOnMouseClicked(e -> {
			events();
		});
	}

	private void events() {

	}

	@FXML
	void AdicionarDisciplina(ActionEvent event) throws IOException {

		Stage s1 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/app/diario/disciplinas/Modal.fxml"));
		Scene scene = new Scene(root);
                s1.setResizable(false);
		s1.setScene(scene);
		s1.show();

	}

	public TableColumn<Disciplinas, Integer> getID() {
		return ID;
	}

	public void setID(TableColumn<Disciplinas, Integer> ID) {
		this.ID = ID;
	}

	public TableColumn<Disciplinas, String> getNome() {
		return Nome;
	}

	public void setNome(TableColumn<Disciplinas, String> Nome) {
		this.Nome = Nome;
	}

	public TableColumn<Disciplinas, Integer> getIDTurmas() {
		return IDTurmas;
	}

	public void setIDTurmas(TableColumn<Disciplinas, Integer> IDTurmas) {
		this.IDTurmas = IDTurmas;
	}

	public TableColumn<Disciplinas, Button> getDeletar() {
		return Deletar;
	}

	public void setDeletar(TableColumn<Disciplinas, Button> Deletar) {
		this.Deletar = Deletar;
	}

	public void deleta(String nome) {
		try {
			Connection connection = BDConnection.getConnection();
			try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM disciplinas where nome = ?")) {
				stmt.setString(1, nome);
				stmt.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean Altera(TextField IDTurmas, TextField Nome, TextField CargaHoraria, TextField id) throws NumberFormatException, SQLException {
		try {
			if ((!"".equals(Nome.getText()) && !"".equals(IDTurmas.getText()))
					&& !"".equals(CargaHoraria.getText())) {
				if (isNum(IDTurmas.getText())
						&& isNum(CargaHoraria.getText())) {
					try (Connection connection = BDConnection.getConnection();
							PreparedStatement ps = connection.prepareStatement("UPDATE `disciplinas` SET `id-turmas` = ?, `nome` = ?, `carga-horaria-min` = ?  WHERE `id` = ?")) {
						ps.setInt(1, Integer.parseInt(IDTurmas.getText()));
						ps.setString(2, Nome.getText());
						ps.setInt(3, Integer.parseInt(CargaHoraria.getText()));
						ps.setInt(4, Integer.parseInt(id.getText()));
						ps.execute();

					}

				} else {
					System.out.println("ERRO");
				}
			} else {
				System.out.println("Erro");
			}
		} catch (SQLException ex) {
			Logger.getLogger(TableControllerDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
		}

		return true;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tabelaDisciplinas = FXCollections.observableArrayList();

		consultarBD();
		CriarTabela();

	}

	public void CriarTabela() {
		tabela.refresh();

		ID.setCellValueFactory(new PropertyValueFactory<>("id"));
		Nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		IDTurmas.setCellValueFactory(new PropertyValueFactory<>("idTurma"));
		CargaHoraria.setCellValueFactory(new PropertyValueFactory<>("CargaHoraria"));
		Alterar.setCellValueFactory(new PropertyValueFactory<>("edita"));
		Deletar.setCellValueFactory(new PropertyValueFactory<>("deleta"));
		tabela.setItems(oblist);
	}

	static public void consultarBD() {
		try {
			try (Connection connection = BDConnection.getConnection()) {
				ResultSet rs = connection.createStatement().executeQuery("select * from disciplinas");
				oblist = FXCollections.observableArrayList();

				while (rs.next()) {
					oblist.add(new Disciplinas(rs.getInt("id"), rs.getInt("id-turmas"), rs.getString("nome"), rs.getInt("carga-horaria-min"), new Button("EDITAR"), new Button("DELETAR")));
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(TableControllerDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static boolean isNum(String strNum) {
		boolean ret = true;
		try {

			Double.parseDouble(strNum);

		} catch (NumberFormatException e) {
			ret = false;
		}
		return ret;
	}
	
	@FXML
	private void Voltar(javafx.event.ActionEvent event){
	    MainTelaTransicaoDiario voltar = new MainTelaTransicaoDiario();
	    try{
		voltar.start(new Stage());
	    }catch(Exception ex){
		Logger.getLogger(TableControllerDisciplinas.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    MainApp.getStage().close();
	}
        
        public void AtualizaTabela(){
            consultarBD();
            CriarTabela();
        }
}
