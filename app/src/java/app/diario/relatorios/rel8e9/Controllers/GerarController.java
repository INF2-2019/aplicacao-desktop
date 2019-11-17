/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.relatorios.rel8e9.Controllers;
import app.diario.campi.Principal.TableController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import app.diario.relatorios.rel8e9.relatorioModels.*;
import app.inicio.MainApp;
import app.utils.ConnectionFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class GerarController implements Initializable {
    @FXML
    private TextField TextCPF;
    @FXML
    private Label mensagem;
    @FXML
    private Button botaoFechar;
    @FXML
    private Button botaoImprimir;
    
    @FXML
    private void gerar(ActionEvent event) throws IOException {
        long cpf;
        Alunos al = null;
        Stage modalTransferencia = new Stage();
        FXMLLoader modalTransferenciaFXMLLoader = new FXMLLoader(getClass().getResource("/app/diario/relatorios/rel8/certificado-modal.fxml"));
        
        Parent modalTransferenciaParent = (Parent) modalTransferenciaFXMLLoader.load();
        cpf = Long.parseLong(TextCPF.getText());
        try{
            al = consulta(cpf);
        }
        catch(SQLException e){
           mensagem.setText("O aluno não existe"); 
           e.printStackTrace();
        }
        ModalCertificadoController modalTransferenciaController = modalTransferenciaFXMLLoader.<ModalCertificadoController>getController(); 
        
        if(al.getPassou()==false){
             mensagem.setText("O aluno não passou");
        } else if(al.getTemRegistro()==false){
            mensagem.setText("O aluno ainda não teve sua nota registrada em alguma das disciplinas");
        } else if(al.getTemMatricula()==false){
            mensagem.setText("Aluno não está matriculado em nada");
        } else{
            modalTransferenciaController.setNomeAluno(al.getNome());
            modalTransferencia.setScene(new Scene(modalTransferenciaParent));
            modalTransferencia.initOwner(((Node) event.getSource()).getScene().getWindow());
            modalTransferencia.initModality(Modality.APPLICATION_MODAL);
            modalTransferencia.showAndWait(); 
        }
        
    }    
    public Alunos consulta(Long id) throws SQLException {
		Connection con = ConnectionFactory.getDiario();
                Alunos al = new Alunos();
		if(con != null) 
                {
			int mat[];
			double notas[][];
			double grade[];
			mat = procuraMatricula(con, id);
			if (mat.length != 0) 
                        {
				for (int i = 0; i < mat.length; i++) 
                                {
					grade = pegarNota(con, mat[i]);
					if (grade.length!=0) 
                                        {
						for (int c = 0; c < grade.length; c++) 
                                                {
							notas = new double[mat.length][grade.length];
							notas[i][c] = grade[c];				
							if (notas[i][c] < 60) 
                                                        {
                                                                al.setPassou(false);
							}
						}
					} 
                                        else{
						al.setTemRegistro(false);
					}
				}
			} 
                        else {
                                al.setTemMatricula(false);
			}
			PreparedStatement prst = con.prepareStatement("SELECT * FROM `alunos` WHERE `id` = ?");
			prst.setLong(1, id);
			ResultSet rs = prst.executeQuery();
			rs.next();
			String nome = rs.getString("nome");
			con.close();
			al.setId(id);
                        al.setNome(nome);
                        return al;
		} 
                else {
			throw new SQLException();
		}
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }     
    
    public static int[] procuraMatricula(Connection con, Long id) throws SQLException{
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `matriculas` WHERE `id-alunos` = ?");
		prst.setLong(1, id);
		int cont = 0;
		int matriculas[];
		ResultSet rs = prst.executeQuery();
		prst = con.prepareStatement("SELECT * FROM `matriculas` WHERE `id-alunos` = ?");
		prst.setLong(1, id);
		ResultSet cp = prst.executeQuery();
		while(rs.next()) {
			cont++;
		}
		matriculas = new int[cont];
		cont = 0;
		while(cp.next()) {
			matriculas[cont] = cp.getInt("id");
			cont++;
		}
		return matriculas;
    }
    
    public static double[] pegarNota(Connection con, int mat) throws SQLException {
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `diario` WHERE `id-matriculas` = ?");
		prst.setInt(1, mat);
		int cont = 0;
		double notas[];
		ResultSet rs = prst.executeQuery();
		prst = con.prepareStatement("SELECT * FROM `diario` WHERE `id-matriculas` = ?");
		prst.setInt(1, mat);
		ResultSet cp = prst.executeQuery();
		while(rs.next()) {
			cont++;
		}
		notas = new double[cont];
		cont = 0;
		while(cp.next()) {
			notas[cont] = cp.getBigDecimal("nota").doubleValue();
			cont++;
		}
		return notas;
	}    
    
    public void voltar(javafx.event.ActionEvent event)
    {
        fechar(event);
        MainApp inicioabrir=new MainApp();
        try {
            inicioabrir.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    private void fechar(ActionEvent event) {
        Stage stage = (Stage)botaoFechar.getScene().getWindow();
        stage.close();
    }
}