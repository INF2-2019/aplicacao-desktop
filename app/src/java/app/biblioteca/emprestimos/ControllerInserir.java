/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.emprestimos;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerInserir {

	@FXML
	private TextField IDAluno;

	@FXML
	private TextField IDAcervo;

	@FXML
	private Button adicionar;

	@FXML
	private Button cancelar;

	@FXML
	protected boolean AdicionarEmprestimo(ActionEvent event) throws SQLException, AlunoException, Exception {
		Connection con = BDConnection.getConnection();
		PreparedStatement ps;
		ResultSet resultadoBusca;
		long idAlunos = 00000000000L;
		String SidAlunos;
		if (IDAluno.getText() != "") {
			SidAlunos = (IDAluno.getText());
			if (SidAlunos.length() != 11) {
				ErroMain erro = new ErroMain("Número inválido para um CPF.");
				erro.start(new Stage());
				return false;
			}
			idAlunos = Long.parseLong(SidAlunos);
		} else {
			ErroMain erro = new ErroMain("O id(CPF) do aluno é obrigatório");
			erro.start(new Stage());
			return false;
		}

		ps = con.prepareStatement("SELECT * FROM `alunos` WHERE `id` = ?");
		ps.setLong(1, idAlunos);
		resultadoBusca = ps.executeQuery();

		if (!resultadoBusca.next()) {
			ErroMain erro = new ErroMain("Não existe esse aluno.");
			erro.start(new Stage());
			return false;
		}
		int idAcervo = 0;
		if (IDAcervo.getText() != "") {
			idAcervo = Integer.parseUnsignedInt(IDAcervo.getText());
		}
		ps = con.prepareStatement("SELECT * FROM `acervo` WHERE `id` = ? ");
		ps.setInt(1, idAcervo);
		resultadoBusca = ps.executeQuery();

		if (!resultadoBusca.next()) {
			ErroMain erro = new ErroMain("Não existe esse acervo.");
			erro.start(new Stage());
			return false;
		}
		ps = con.prepareStatement("SELECT * FROM `emprestimos` WHERE `id-acervo` = ? AND `data-devolucao`= '1970-01-01'");
		ps.setInt(1, idAcervo);
		resultadoBusca = ps.executeQuery();
		while (resultadoBusca.next()) {
			ErroMain erro = new ErroMain("Este livro, atualmente, já está emprestado");
			erro.start(new Stage());
			return false;
		}
		Date dataEmprestimo = new Date(new Date().getTime());

		Calendar c = Calendar.getInstance();
		c.setTime(dataEmprestimo);
		c.add(Calendar.DATE, +7);

		Date dataPrevDevol = c.getTime();
		Date dataDevolucao = new Date(0);
		double multa = 0.00;
		ps = con.prepareStatement("INSERT INTO `emprestimos` (`id-alunos`, `id-acervo`, `data-emprestimo`, `data-prev-devol`, `data-devolucao`, `multa`) VALUES (?, ?, ?, ?, ?, ?)");
		ps.setLong(1, idAlunos);
		ps.setInt(2, idAcervo);
		ps.setDate(3, new java.sql.Date(dataEmprestimo.getTime()));
		ps.setDate(4, new java.sql.Date(dataPrevDevol.getTime()));
		ps.setDate(5, new java.sql.Date(dataDevolucao.getTime()));
		ps.setDouble(6, multa);
		ps.executeUpdate();
		Stage modal = (Stage) adicionar.getScene().getWindow();
		modal.close();
		return false;
	}

	@FXML
	protected void Cancelar(ActionEvent event) throws SQLException {
		Stage modal = (Stage) cancelar.getScene().getWindow();
		modal.close();
	}

	public void initialize(URL location, ResourceBundle resources) {

	}

}
