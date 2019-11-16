package app.diario.relatorios.relatorio11;

import app.diario.relatorios.relatorio10.Relatorio10Controller;
import app.diario.turmas.principal.Conector;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Relatorio11ModalController implements Initializable {

	private static String nomeDisciplina;
	private static int idEtapa, idDisciplina;
	private Boolean disciplina = false, etapa = false;

	@FXML
	private Button impressoBtn;
	@FXML
	private Button telaBtn;
	@FXML
	private ChoiceBox selectDisciplina;
	@FXML
	private ChoiceBox selectEtapa;

	@FXML
	private void salvarAction(ActionEvent event) throws SQLException, ClassNotFoundException, DocumentException, FileNotFoundException {
		if (disciplina && etapa) {
			nomeDisciplina = selectDisciplina.getValue().toString();
			idEtapa = Integer.parseInt(selectEtapa.getValue().toString());
			Connection con = Conector.conectar();
			final DirectoryChooser dir = new DirectoryChooser();

			Stage stage = (Stage) impressoBtn.getScene().getWindow();

			File file = dir.showDialog(stage);

			if (file != null) {
				Document pdf = new Document();
				PdfWriter.getInstance(pdf, new FileOutputStream(file.getAbsolutePath() + "\\relatorioRelaçãoDeConteúdos.pdf"));
				pdf.open();

				PdfPTable tabela = new PdfPTable(3);
				PdfPCell cel;

				String head[] = {"CONTEÚDO", "DATA", "VALOR"};
				Font bold = new Font(Font.FontFamily.UNDEFINED, 11, Font.BOLD);

				Paragraph title = new Paragraph("RELATÓRIO RELAÇÃO DE CONTEÚDOS");
				title.setSpacingAfter(50);

				pdf.add(title);

				Paragraph spec = new Paragraph("DISCIPLINA: " + nomeDisciplina.toUpperCase() + ", " + idEtapa + "ª ETAPA");
				spec.setSpacingAfter(50);

				pdf.add(spec);

				for (String headEl : head) {
					tabela.addCell(new PdfPCell(new Phrase(headEl, bold)));
				}

				String sql = "SELECT * FROM disciplinas WHERE nome='" + nomeDisciplina + "'";
				ResultSet res = con.createStatement().executeQuery(sql);
				if (res.next()) {
					idDisciplina = res.getInt("id");
				}

				sql = "SELECT * FROM conteudos WHERE `id-etapas`=" + idEtapa + " AND `id-disciplinas`=" + idDisciplina;
				ResultSet res2 = con.createStatement().executeQuery(sql);
				while (res2.next()) {
					cel = new PdfPCell(new Phrase(String.valueOf(res2.getString("conteudos"))));
					tabela.addCell(cel);
					cel = new PdfPCell(new Phrase(String.valueOf(res2.getDate("data"))));
					tabela.addCell(cel);
					cel = new PdfPCell(new Phrase(String.valueOf(res2.getDouble("valor"))));
					tabela.addCell(cel);
				}

				pdf.add(tabela);
				pdf.close();

				con.close();
			}
		}
	}

	@FXML
	private void telaAction(ActionEvent event) {
		if (disciplina && etapa) {
			Relatorio11Main r11m = new Relatorio11Main();
			Relatorio11Controller.setDisciplina(selectDisciplina.getValue().toString());
			Relatorio11Controller.setEtapa(Integer.parseInt(selectEtapa.getValue().toString()));
			try {
				r11m.start(new Stage());
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectDisciplina.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			disciplina = true;
		});
		selectEtapa.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			etapa = true;
		});
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM disciplinas";
			ResultSet res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				lista.add(res.getString("nome"));
			}
			selectDisciplina.setItems(lista);
			ObservableList lista2 = FXCollections.observableArrayList();
			sql = "SELECT * FROM etapas";
			res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				lista2.add(res.getString("id"));
			}
			selectEtapa.setItems(lista2);
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
