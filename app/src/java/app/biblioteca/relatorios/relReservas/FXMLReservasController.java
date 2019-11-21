/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.relatorios.relReservas;

import app.biblioteca.relatorios.principal.DbConnector;
import app.biblioteca.relatorios.relMultas.TableController;
import app.biblioteca.telatransicao.MainTelaTransicaoBiblioteca;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLReservasController implements Initializable {

    @FXML
    private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable, String> col_id;

    @FXML
    private TableColumn<ModelTable, String> col_alunos;

    @FXML
    private TableColumn<ModelTable, String> col_acervo;

    @FXML
    private TableColumn<ModelTable, String> col_reserva;

    @FXML
    private TableColumn<ModelTable, String> col_espera;

    @FXML
    private TableColumn<ModelTable, String> col_emprestou;

    @FXML
    private Button btnImprimir;
    
     @FXML
    private Button btnVoltar;

    @FXML
    private AnchorPane anchorImprimir;

    static ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	consultarBD();
	criaTabela();
    }

    public void refresh() {
	oblist.clear();
	consultarBD();
	criaTabela();
    }

    static public void consultarBD() {
	try {
	    Connection con = DbConnector.getConnection();
	    ResultSet rs = con.createStatement().executeQuery("select * from reservas");
	    oblist = FXCollections.observableArrayList();
	    while (rs.next()) {
		oblist.add(new ModelTable(rs.getInt("id"), rs.getLong("id-alunos"), rs.getInt("id-acervo"), rs.getDate("data-reserva"), rs.getInt("tempo-espera"), rs.getBoolean("emprestou")));
	    }

	    con.close();

	} catch (SQLException ex) {
	    Logger.getLogger(FXMLReservasController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void criaTabela() {
	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
	col_alunos.setCellValueFactory(new PropertyValueFactory<>("alunos"));
	col_acervo.setCellValueFactory(new PropertyValueFactory<>("acervo"));
	col_reserva.setCellValueFactory(new PropertyValueFactory<>("reserva"));
	col_espera.setCellValueFactory(new PropertyValueFactory<>("espera"));
	col_emprestou.setCellValueFactory(new PropertyValueFactory<>("emprestou"));

	table.setItems(oblist);
    }

    public void imprimePDF() throws FileNotFoundException, DocumentException, SQLException {
	//USADO iText PDF 5
	Connection con = DbConnector.getConnection();

	//Selecionar o diretório pelo botão salvar
	final DirectoryChooser dirch = new DirectoryChooser();

	Stage stage = (Stage) btnImprimir.getScene().getWindow();

	File file = dirch.showDialog(stage);

	if (file != null) {
	    Document my_pdf_report = new Document();
	    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file.getAbsolutePath() + "\\relatorioReservas.pdf"));
	    my_pdf_report.open();

	    //temos 6 colunas na tabela
	    PdfPTable my_report_table = new PdfPTable(6);
	    //cria um celula
	    PdfPCell table_cell;
	    
	    Paragraph title = new Paragraph("RELATÓRIO RESERVAS");
	    title.setSpacingAfter(50);
	    
	    my_pdf_report.add(title);

	    ResultSet rs = con.createStatement().executeQuery("select * from reservas");

	    String head[] = {"ID", "ID-ALUNOS", "ID-ACERVO", "DATA-RESERVA", "TEMPO-ESPERA", "EMPRESTOU"};
	    Font bold = new Font(FontFamily.UNDEFINED, 12, Font.BOLD);

	    for (String headEl : head) {
		my_report_table.addCell(new PdfPCell(new Phrase(headEl, bold)));
	    }

	    while (rs.next()) {
		//adiciona os elementos do BD na tabela do PDF
		int id = rs.getInt("id");
		table_cell = new PdfPCell(new Phrase(String.valueOf(id)));
		my_report_table.addCell(table_cell);
		long alunos = rs.getLong("id-alunos");
		table_cell = new PdfPCell(new Phrase(String.valueOf(alunos)));
		my_report_table.addCell(table_cell);
		int acervo = rs.getInt("id-acervo");
		table_cell = new PdfPCell(new Phrase(String.valueOf(acervo)));
		my_report_table.addCell(table_cell);
		Date reserva = rs.getDate("data-reserva");
		table_cell = new PdfPCell(new Phrase(String.valueOf(reserva)));
		my_report_table.addCell(table_cell);
		int espera = rs.getInt("tempo-espera");
		table_cell = new PdfPCell(new Phrase(String.valueOf(espera)));
		my_report_table.addCell(table_cell);
		boolean emprestou = rs.getBoolean("emprestou");
		table_cell = new PdfPCell(new Phrase(String.valueOf(emprestou)));
		my_report_table.addCell(table_cell);

	    }

	    //adiciona tabela no pdf
	    my_pdf_report.add(my_report_table);
	    my_pdf_report.close();

	    /* Fecha conexão*/
	    con.close();
	    rs.close();
	    con.close();
	}
    }
    
      @FXML
    public void volta() {
        //declaracao para a tela de transição
        MainTelaTransicaoBiblioteca main = new MainTelaTransicaoBiblioteca();
        try {
            main.start(new Stage());
            RelReservasMain.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(FXMLReservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}

