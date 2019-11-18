
package app.biblioteca.relatorios.relDescartes;

import app.biblioteca.relatorios.principal.DbConnector;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.Date;
import java.sql.PreparedStatement;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;


public class TableController implements Initializable{
    
    @FXML
    private TableView<ModelTable> table;
    
    @FXML
    private TableColumn<ModelTable, Integer> col_idAcervo;
    @FXML
    private TableColumn<ModelTable, Date> col_dataDescarte;
    @FXML
    private TableColumn<ModelTable, String> col_motivos;
    @FXML
    private TableColumn<ModelTable, String> col_operador;
    @FXML
    private Button btnImprimir;
    
    @FXML
    private Button btnVoltar;
    

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
    @FXML
    public void volta() {
        RelDescartesMain.getStage().close();
    
    }
    
    static public void consultarBD(){
        try {
	    try (Connection con = DbConnector.getConnection()) {
		ResultSet rs = con.createStatement().executeQuery("select * from descartes");
		oblist = FXCollections.observableArrayList();
		while(rs.next()){
		    oblist.add(new ModelTable(verificaAcervo(rs.getString("id-acervo")),rs.getDate("data-descarte"),rs.getString("motivos"),rs.getString("operador")));
		}
	    }
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void criaTabela(){
        
        col_idAcervo.setCellValueFactory(new PropertyValueFactory<>("idAcervo"));
        col_dataDescarte.setCellValueFactory(new PropertyValueFactory<>("dataDescarte"));
        col_motivos.setCellValueFactory(new PropertyValueFactory<>("motivos"));
	col_operador.setCellValueFactory(new PropertyValueFactory<>("operador"));
        //col_multa.setCellValueFactory(new PropertyValueFactory<>("multa"));
        //col_funcoes.setCellValueFactory(new PropertyValueFactory<>("info"));
        
        table.setItems(oblist);
    }
    public static String verificaAcervo(String id) throws SQLException {
        ResultSet resultadoBusca;
        Connection con = DbConnector.getConnection();
        int Iid = Integer.parseUnsignedInt(id);
        // cria um preparedStatement
        String sql = "SELECT `nome` FROM `acervo` WHERE `id` = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Iid);
        resultadoBusca =  stmt.executeQuery();
        resultadoBusca.next();
        return resultadoBusca.getString("nome");
    }
     public void imprimePDF() throws FileNotFoundException, DocumentException, SQLException, IOException {
	//USADO iText PDF 5
	Connection con = DbConnector.getConnection();

	//Selecionar o diretório pelo botão salvar
	final DirectoryChooser dirch = new DirectoryChooser();

	Stage stage = (Stage) btnImprimir.getScene().getWindow();

	File file = dirch.showDialog(stage);

	if (file != null) {
	    System.out.println(file.getAbsolutePath() + "\\relatorioObrasDescartadas.pdf");
	    Document my_pdf_report = new Document(PageSize.A4.rotate());
	    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file.getAbsolutePath() + "\\relatorioObrasDescartadas.pdf"));
	    my_pdf_report.open();

	    //temos 6 colunas na tabela
	    //PdfPTable my_report_table = new PdfPTable(6);
	    PdfPTable my_report_table = new PdfPTable(new float[] { 0.15f, 0.15f, 0.55f , 0.15f});
	    //cria um celula
	    PdfPCell table_cell;
	    
	    Paragraph title = new Paragraph("RELATÓRIO OBRAS DERCARTADAS");
	    title.setSpacingAfter(50);
	    
	    my_pdf_report.add(title);

	    ResultSet rs = con.createStatement().executeQuery("select * from descartes");

	    String head[] = {"ACERVO", "DATA DESCARTE", "MOTIVOS", "OPERADOR"};
	    Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);

	    for (String headEl : head) {
		my_report_table.addCell(new PdfPCell(new Phrase(headEl, bold)));
	    }
	    

	    while (rs.next()) {
		String acervo = verificaAcervo(rs.getString("id-acervo"));
		table_cell = new PdfPCell(new Phrase(String.valueOf(acervo)));
		my_report_table.addCell(table_cell);
		java.sql.Date desc = rs.getDate("data-descarte");
		table_cell = new PdfPCell(new Phrase(String.valueOf(desc)));
		my_report_table.addCell(table_cell);
		String motivos = rs.getString("motivos");
		table_cell = new PdfPCell(new Phrase(String.valueOf(motivos)));
		my_report_table.addCell(table_cell);
		String oper = rs.getString("operador");
		table_cell = new PdfPCell(new Phrase(String.valueOf(oper)));
		my_report_table.addCell(table_cell);
		    
                }
		
		//adiciona os elementos do BD na tabela do PDF
		

	    

	    //adiciona tabela no pdf
	    my_pdf_report.add(my_report_table);
	    my_pdf_report.close();
	    //Desktop.getDesktop().print(new File("fileName"));
	    /* Fecha conexão*/
	    con.close();
	    rs.close();
	    con.close();
	    
	}
    }
}
