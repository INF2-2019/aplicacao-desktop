package app.diario.relatorios.relatorio10;

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

public class Relatorio10ModalController implements Initializable {

	private String nomeAluno, nomeDisciplina;
	private int idAluno, idMatricula;
	private double notaFinal = 0, et1 = 0, et2 = 0, et3 = 0, et4 = 0;
	private Boolean podeGerar = false;

	@FXML
	private ChoiceBox selectAluno;
	@FXML
	private Button impressoBtn;
	@FXML
	private Button telaBtn;

	@FXML
	private void salvarAction(ActionEvent event) throws SQLException, ClassNotFoundException, DocumentException, FileNotFoundException {
		if (podeGerar) {
			nomeAluno = selectAluno.getValue().toString();
			Connection con = Conector.conectar();
			final DirectoryChooser dir = new DirectoryChooser();

			Stage stage = (Stage) impressoBtn.getScene().getWindow();

			File file = dir.showDialog(stage);

			if (file != null) {
				Document pdf = new Document();
				PdfWriter.getInstance(pdf, new FileOutputStream(file.getAbsolutePath() + "\\relatorioRelaçãoPorAluno.pdf"));
				pdf.open();

				PdfPTable tabela = new PdfPTable(6);
				PdfPCell cel;

				String head[] = {"DISCIPLINA", "1ª ETAPA", "2ª ETAPA", "3ª ETAPA", "4ª ETAPA", "NOTA"};
				Font bold = new Font(Font.FontFamily.UNDEFINED, 11, Font.BOLD);

				Paragraph title = new Paragraph("RELATÓRIO RELAÇÃO POR ALUNO");
				title.setSpacingAfter(50);

				pdf.add(title);
				
				Paragraph spec = new Paragraph("ALUNO: " + nomeAluno.toUpperCase());
				spec.setSpacingAfter(50);

				pdf.add(spec);

				for (String headEl : head) {
					tabela.addCell(new PdfPCell(new Phrase(headEl, bold)));
				}

				String sql = "SELECT * FROM alunos WHERE nome='" + nomeAluno + "'"; // Pega o id do aluno escolhido
				ResultSet res = con.createStatement().executeQuery(sql);
				if (res.next()) {
					idAluno = res.getInt("id");
				}

				sql = "SELECT * FROM matriculas WHERE `id-alunos`=" + idAluno; // Pega as matrículas relacionadas ao aluno
				ResultSet res2 = con.createStatement().executeQuery(sql);
				while (res2.next()) {
					idMatricula = res2.getInt("id");
					sql = "SELECT * FROM disciplinas WHERE id=" + res2.getInt("id-disciplinas"); // Pega as disciplinas relacionadas às matriculas do aluno
					ResultSet res3 = con.createStatement().executeQuery(sql);
					if (res3.next()) {
						nomeDisciplina = res3.getString("nome");
					}

					sql = "SELECT * FROM conteudos WHERE `id-disciplinas`=" + res2.getInt("id-disciplinas"); // Pega os conteudos relacionados à disciplina
					ResultSet res4 = con.createStatement().executeQuery(sql);
					et1 = et2 = et3 = et4 = 0;
					notaFinal = 0;
					while (res4.next()) {
						if (res4.getInt("id-etapas") == 1) {
							sql = "SELECT * FROM diario WHERE `id-conteudos`=" + res4.getInt("id") + " AND `id-matriculas`=" + idMatricula; // Pega o diario associado à matricula e ao conteúdo
							ResultSet res5 = con.createStatement().executeQuery(sql);
							while (res5.next()) {
								et1 += res5.getDouble("nota");
							}
						} else if (res4.getInt("id-etapas") == 2) {
							sql = "SELECT * FROM diario WHERE `id-conteudos`=" + res4.getInt("id") + " AND `id-matriculas`=" + idMatricula; // Pega o diario associado à matricula e ao conteúdo
							ResultSet res5 = con.createStatement().executeQuery(sql);
							while (res5.next()) {
								et2 += res5.getDouble("nota");
							}
						} else if (res4.getInt("id-etapas") == 3) {
							sql = "SELECT * FROM diario WHERE `id-conteudos`=" + res4.getInt("id") + " AND `id-matriculas`=" + idMatricula; // Pega o diario associado à matricula e ao conteúdo
							ResultSet res5 = con.createStatement().executeQuery(sql);
							while (res5.next()) {
								et3 += res5.getDouble("nota");
							}
						} else if (res4.getInt("id-etapas") == 4) {
							sql = "SELECT * FROM diario WHERE `id-conteudos`=" + res4.getInt("id") + " AND `id-matriculas`=" + idMatricula; // Pega o diario associado à matricula e ao conteúdo
							ResultSet res5 = con.createStatement().executeQuery(sql);
							while (res5.next()) {
								et4 += res5.getDouble("nota");
							}
						}
					}
					notaFinal = et1 + et2 + et3 + et4;
					cel = new PdfPCell(new Phrase(String.valueOf(nomeDisciplina)));
					tabela.addCell(cel);
					cel = new PdfPCell(new Phrase(String.valueOf(et1)));
					tabela.addCell(cel);
					cel = new PdfPCell(new Phrase(String.valueOf(et2)));
					tabela.addCell(cel);
					cel = new PdfPCell(new Phrase(String.valueOf(et3)));
					tabela.addCell(cel);
					cel = new PdfPCell(new Phrase(String.valueOf(et4)));
					tabela.addCell(cel);
					cel = new PdfPCell(new Phrase(String.valueOf(notaFinal)));
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
		if (podeGerar) {
			Relatorio10Main r10m = new Relatorio10Main();
			Relatorio10Controller.setNomeAluno(selectAluno.getValue().toString());
			try {
				r10m.start(new Stage());
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectAluno.getSelectionModel().selectedItemProperty().addListener((obs, valorAntigo, valorNovo) -> {
			podeGerar = true;
		});
		try {
			ObservableList lista = FXCollections.observableArrayList();
			Connection con = Conector.conectar();
			String sql = "SELECT * FROM alunos";
			ResultSet res = con.createStatement().executeQuery(sql);
			while (res.next()) {
				lista.add(res.getString("nome"));
			}
			con.close();
			selectAluno.setItems(lista);
		} catch (SQLException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Relatorio10Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
