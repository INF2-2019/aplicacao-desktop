/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.relatorios.relEmprestimos;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */
public class TableController implements Initializable{
    
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, Integer> col_id;
    @FXML
    private TableColumn<ModelTable, Integer> col_idAlunos;
    @FXML
    private TableColumn<ModelTable, Integer> col_idAcervo;
    @FXML
    private TableColumn<ModelTable, Date> col_dataEmprestimo;
    @FXML
    private TableColumn<ModelTable, Date> col_dataPrevDevol;
    @FXML
    private TableColumn<ModelTable, Date> col_dataDevolucao;
    @FXML
    private TableColumn<ModelTable, Double> col_multa;
    @FXML
    private Button btnImprimir;
    

    static ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultarBD();
        criaTabela();
    }
    
    public void refresh(){
        oblist.clear();
        consultarBD();
        criaTabela();
    }
    
    static public void consultarBD(){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from emprestimos");
            oblist = FXCollections.observableArrayList();
            while(rs.next()){
                oblist.add(new ModelTable(rs.getString("id"),rs.getString("id-alunos"),rs.getString("id-acervo"),rs.getDate("data-emprestimo"),rs.getDate("data-prev-devol"),rs.getDate("data-devolucao"),rs.getDouble("multa")));
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void criaTabela(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_idAlunos.setCellValueFactory(new PropertyValueFactory<>("idAlunos"));
        col_idAcervo.setCellValueFactory(new PropertyValueFactory<>("idAcervo"));
        col_dataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        col_dataPrevDevol.setCellValueFactory(new PropertyValueFactory<>("dataPrevDevol"));
	col_dataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        col_multa.setCellValueFactory(new PropertyValueFactory<>("multa"));
        
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
	    System.out.println(file.getAbsolutePath() + "\\relatorioEmprestimos.pdf");
	    Document my_pdf_report = new Document();
	    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file.getAbsolutePath() + "\\relatorioEmprestimos.pdf"));
	    my_pdf_report.open();

	    //temos 6 colunas na tabela
	    PdfPTable my_report_table = new PdfPTable(7);
	    //cria um celula
	    PdfPCell table_cell;
	    
	    Paragraph title = new Paragraph("RELATÓRIO ACERVO LIVROS");
	    title.setSpacingAfter(50);
	    
	    my_pdf_report.add(title);

	    ResultSet rs = con.createStatement().executeQuery("select * from emprestimos");

	    String head[] = {"ID", "ID-ALUNOS", "ID-ACERVO", "DATA-EMPRESTIMO", "DATA-PREV-DEVOL", "DATA-DEVOLUÇÃO" , "MULTA"};
	    Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);

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
		Date emprestimo = rs.getDate("data-emprestimo");
		table_cell = new PdfPCell(new Phrase(String.valueOf(emprestimo)));
		my_report_table.addCell(table_cell);
		Date prevdevol = rs.getDate("data-prev-devol");
		table_cell = new PdfPCell(new Phrase(String.valueOf(prevdevol)));
		my_report_table.addCell(table_cell);
		Date devol = rs.getDate("data-devolucao");
		table_cell = new PdfPCell(new Phrase(String.valueOf(devol)));
		my_report_table.addCell(table_cell);
		double multa = rs.getDouble("multa");
		table_cell = new PdfPCell(new Phrase(String.valueOf(multa)));
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
}
