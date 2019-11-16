
package app.biblioteca.relatorios.relMultas;

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
import java.sql.PreparedStatement;
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
import java.util.Date;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

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
    private TableColumn<ModelTable, Date> col_dataDevolucao;
    @FXML
    private TableColumn<ModelTable, Double> col_multa;
    @FXML
    private Button btnImprimir;
    @FXML
    private Button btnVoltar;
   
    
    private boolean podeConstruir=false;
    private Date data =new Date();
    

    static ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultarBD();
        if(podeConstruir){
        criaTabela();
        }
        
    }
    public void refresh(){
        oblist.clear();
        consultarBD();
        if(podeConstruir){
        criaTabela();
        }
    }
    @FXML
    public void volta() {
        RelMultasMain.getStage().close();
    
    }
    
    public void consultarBD(){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from emprestimos");
            oblist = FXCollections.observableArrayList();
            //tabela.dataPrevDevol.compareTo(data);
             while(rs.next()){
                if(rs.getDate("data-devolucao").getTime()!=0){
                if(rs.getDouble("multa")!=0){
                    oblist.add(new ModelTable(rs.getString("id"),verificaAlunos(rs.getString("id-alunos")),verificaAcervo(rs.getString("id-acervo")),rs.getDate("data-emprestimo"),rs.getDate("data-devolucao"),rs.getDouble("multa")));
                    podeConstruir=true;
                }else if(rs.getDouble("multa")==0){
                    podeConstruir=false;
                }
                }
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
	col_dataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        col_multa.setCellValueFactory(new PropertyValueFactory<>("multa"));
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
    public static String verificaAlunos(String id) throws SQLException {
        ResultSet resultadoBusca;
        Connection con = DbConnector.getConnection();
        int Iid = Integer.parseUnsignedInt(id);
        // cria um preparedStatement
        String sql = "SELECT `nome` FROM `alunos` WHERE `id` = ?";
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
	    System.out.println(file.getAbsolutePath() + "\\relatorioMultas.pdf");
	    Document my_pdf_report = new Document(PageSize.A4.rotate());
	    PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file.getAbsolutePath() + "\\relatorioMultas.pdf"));
	    my_pdf_report.open();

	    //temos 6 colunas na tabela
	    PdfPTable my_report_table = new PdfPTable(6);
	    //PdfPTable table = new PdfPTable(new float[] { 0.2f, 0.2f, 0.6f });
	    //cria um celula
	    PdfPCell table_cell;
	    
	    Paragraph title = new Paragraph("RELATÓRIO MULTAS");
	    title.setSpacingAfter(50);
	    
	    my_pdf_report.add(title);

	    ResultSet rs = con.createStatement().executeQuery("select * from emprestimos");

	    String head[] = {"ID", "ALUNO", "ACERVO", "DATA EMPRÉSTIMO", "DATA DEVOLUCAO", "MULTA"};
	    Font bold = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);

	    for (String headEl : head) {
		my_report_table.addCell(new PdfPCell(new Phrase(headEl, bold)));
	    }
	    

	    while (rs.next()) {
		if(rs.getDate("data-devolucao").getTime()!=0){
                if(rs.getDouble("multa")!=0){
			int id = rs.getInt("id");
			table_cell = new PdfPCell(new Phrase(String.valueOf(id)));
			my_report_table.addCell(table_cell);
			String alunos = verificaAlunos(rs.getString("id-alunos"));
			table_cell = new PdfPCell(new Phrase(String.valueOf(alunos)));
			my_report_table.addCell(table_cell);
			String acervo = verificaAcervo(rs.getString("id-acervo"));
			table_cell = new PdfPCell(new Phrase(String.valueOf(acervo)));
			my_report_table.addCell(table_cell);
			java.sql.Date emprestimo = rs.getDate("data-emprestimo");
			table_cell = new PdfPCell(new Phrase(String.valueOf(emprestimo)));
			my_report_table.addCell(table_cell);
			java.sql.Date devol = rs.getDate("data-devolucao");
			table_cell = new PdfPCell(new Phrase(String.valueOf(devol)));
			my_report_table.addCell(table_cell);
			double multa = rs.getDouble("multa");
			table_cell = new PdfPCell(new Phrase(String.valueOf(multa)));
			my_report_table.addCell(table_cell);
			podeConstruir=true;
		    }else if(rs.getDouble("multa")==0){
			 podeConstruir=false;
		    }
                }
		
		//adiciona os elementos do BD na tabela do PDF
		

	    }

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
