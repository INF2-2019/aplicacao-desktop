package app.biblioteca.reservas;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    protected boolean Reservar(ActionEvent event) throws SQLException, AlunoException, Exception {

	Connection con = BDConnection.getConnection();
	PreparedStatement ps;
	ResultSet resultadoBusca;
	long idAlunos = 00000000000L;
	String SidAlunos;
	if (!"".equals(IDAluno.getText())) {
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
	if (!"".equals(IDAcervo.getText())) {
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
	ps = con.prepareStatement("SELECT * FROM `reservas` WHERE `id-acervo` = ? AND `emprestou`= ?");
	ps.setInt(1, idAcervo);
	ps.setBoolean(2, false);
	resultadoBusca = ps.executeQuery();

	while (resultadoBusca.next()) {
	     ErroMain erro = new ErroMain("Ja existe uma reserva sobre o item no Acervo");
	     erro.start(new Stage());
	    return false;
	}
	int tempoEspera = 0;
	Date dataReserva = new Date(new Date().getTime());
	boolean emprestou = false;
	ps = con.prepareStatement("INSERT INTO `reservas` (`id-alunos`, `id-acervo`, `data-reserva`,`tempo-espera`,`emprestou`) VALUES (?, ?, ?, ?, ?)");

	ps.setLong(1, idAlunos);
	ps.setInt(2, idAcervo);
	ps.setDate(3, new java.sql.Date(dataReserva.getTime()));
	ps.setInt(4, tempoEspera);
	ps.setBoolean(5, emprestou);

	int sucesso = ps.executeUpdate();
	Stage modal = (Stage)adicionar.getScene().getWindow();
        modal.setResizable(false);
        modal.close(); 
	return true;
    }

    @FXML
    protected void CancelarReservas(ActionEvent event) throws SQLException {
	Stage modal = (Stage) cancelar.getScene().getWindow();
	modal.close();
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
