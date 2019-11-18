/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.relatorios.relAcervo;

import app.biblioteca.relatorios.principal.DbConnector;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLAcervoPeriodicosController implements Initializable {

    @FXML
    private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable, Integer> col_id;

    @FXML
    private TableColumn<ModelTable, Integer> col_campi;

    @FXML
    private TableColumn<ModelTable, String> col_nome;

    @FXML
    private TableColumn<ModelTable, String> col_local;

    @FXML
    private TableColumn<ModelTable, Integer> col_ano;

    @FXML
    private TableColumn<ModelTable, String> col_editora;

    @FXML
    private TableColumn<ModelTable, Integer> col_paginas;

    @FXML
    private TableColumn<ModelTable, String> col_periodicidade;

    @FXML
    private TableColumn<ModelTable, Integer> col_issn;

    @FXML
    private TableColumn<ModelTable, Integer> col_volume;

    @FXML
    private TableColumn<ModelTable, String> col_subtipo;

    @FXML
    private TableColumn<ModelTable, String> col_mes;

    @FXML
    private Button btnImprimir;
    
     @FXML
    private Button btnVoltar;

    @FXML
    void refresh(MouseEvent event) {

    }

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

	    ResultSet rs = con.createStatement().executeQuery("select * from acervo");
	    ResultSet rsP = con.createStatement().executeQuery("select * from periodicos");
	    oblist = FXCollections.observableArrayList();

	    while (rs.next()) {
		if (rs.getString("tipo").equals("PERIODICOS") && rsP.next()) {
		    oblist.add(new ModelTable(rs.getInt("id"), rs.getInt("id-campi"), rs.getInt("ano"), rs.getInt("paginas"), rsP.getInt("volume"), rsP.getInt("issn"), rs.getString("nome"), rs.getString("local"), rs.getString("editora"), rsP.getString("periodicidade"), rsP.getString("mes"), rsP.getString("subtipo")));
		}
	    }

	    con.close();
	} catch (SQLException ex) {
	    Logger.getLogger(FXMLAcervoPeriodicosController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void criaTabela() {
	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
	col_campi.setCellValueFactory(new PropertyValueFactory<>("campi"));
	col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	col_local.setCellValueFactory(new PropertyValueFactory<>("local"));
	col_ano.setCellValueFactory(new PropertyValueFactory<>("ano"));
	col_editora.setCellValueFactory(new PropertyValueFactory<>("editora"));
	col_paginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
	col_periodicidade.setCellValueFactory(new PropertyValueFactory<>("periodicidade"));
	col_mes.setCellValueFactory(new PropertyValueFactory<>("mes"));
	col_volume.setCellValueFactory(new PropertyValueFactory<>("volume"));
	col_subtipo.setCellValueFactory(new PropertyValueFactory<>("subtipo"));
	col_issn.setCellValueFactory(new PropertyValueFactory<>("issn"));

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
	    //iText PDF 5
	    Document my_pdf_report = new Document();
	    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file.getAbsolutePath() + "\\relatorioAcervoPeriodicos.pdf"));
	    my_pdf_report.open();

	    //temos 9 colunas na tabela
	    PdfPTable my_report_table = new PdfPTable(12);
	    //cria um celula
	    PdfPCell table_cell;

	    ResultSet rs = con.createStatement().executeQuery("select * from acervo");
	    ResultSet rsP = con.createStatement().executeQuery("select * from periodicos");

	    String head[] = {"ID", "CAMPI", "NOME", "LOCAL", "ANO", "EDITORA", "PÁGINAS", "PERIODICIDADE", "MÊS", "VOLUME", "SUBTIPO", "ISSN"};
	    Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);

	    Paragraph title = new Paragraph("RELATÓRIO ACERVO PERIÓDICOS");
	    title.setSpacingAfter(50);

	    my_pdf_report.add(title);

	    for (String headEl : head) {
		my_report_table.addCell(new PdfPCell(new Phrase(headEl, bold)));
	    }

	    while (rs.next()) {
		if (rs.getString("tipo").equals("PERIODICOS") && rsP.next()) {
		    //adiciona os elementos do BD na tabela do PDF
		    int id = rs.getInt("id");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(id)));
		    my_report_table.addCell(table_cell);
		    int campi = rs.getInt("id-campi");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(campi)));
		    my_report_table.addCell(table_cell);
		    String nome = rs.getString("nome");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(nome)));
		    my_report_table.addCell(table_cell);
		    String local = rs.getString("local");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(local)));
		    my_report_table.addCell(table_cell);
		    int ano = rs.getInt("ano");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(ano)));
		    my_report_table.addCell(table_cell);
		    String editora = rs.getString("editora");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(editora)));
		    my_report_table.addCell(table_cell);
		    int paginas = rs.getInt("paginas");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(paginas)));
		    my_report_table.addCell(table_cell);
		    String periodicidade = rsP.getString("periodicidade");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(periodicidade)));
		    my_report_table.addCell(table_cell);
		    String mes = rsP.getString("mes");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(mes)));
		    my_report_table.addCell(table_cell);
		    int volume = rsP.getInt("volume");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(volume)));
		    my_report_table.addCell(table_cell);
		    String subtipo = rsP.getString("subtipo");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(subtipo)));
		    my_report_table.addCell(table_cell);
		    String issn = rsP.getString("issn");
		    table_cell = new PdfPCell(new Phrase(String.valueOf(issn)));
		    my_report_table.addCell(table_cell);
		}
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
        try {
            //.start(new Stage());
            RelAcervoPeridiocosMain.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAcervoPeriodicosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}

