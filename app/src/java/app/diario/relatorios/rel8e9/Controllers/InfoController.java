package app.diario.relatorios.rel8e9.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import app.diario.relatorios.rel8e9.relatorioModels.*;
import app.utils.ConnectionFactory;

public class InfoController implements Initializable {
    static long id;
    String ThisNome = "";

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
	InfoController.id = id;
    }
    
    @FXML
    private TextField nome;

    @FXML
    private Button btnFechar;

    @FXML
    private TextField cpf;
    
    @FXML
    private TableView tabelaModal;

    @FXML
    private TableColumn cursosModal;

    @FXML
    private TableColumn disciplinasModal;

    @FXML
    private TableColumn cargaModal;
    
    String arquivo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try { 
        consulta();
    } catch (SQLException ex) {
        Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
    }
        criaTabela();
        String ThisId = Long.toString(getId());
        cpf.setText(ThisId); 
        nome.setText(ThisNome);
    }
    
    ObservableList<Professor> profs = FXCollections.observableArrayList();
    
    String[] nomeCursos;
    int[] idCursos;
    String[] nomeDisci;
    int[] carga;
    int[][] idTurmas;
    
    public void consulta() throws SQLException, IOException {
        int aux = 0;
		Connection con = ConnectionFactory.getDiario();
		if(con != null) {
		
			try(Statement stmt = con.createStatement()) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM `professores`");
                                ResultSetMetaData rsmd = rs.getMetaData();
                   
                                int sizeDisc=rsmd.getPrecision(2);
				while(rs.next()) {
                                    if(getId() == rs.getInt("id")){ 
                                        idCursos = procuraIdCurso(con, rs.getInt("id-depto"));
                                        nomeCursos = procuraCursos(con, rs.getInt("id-depto"));
                                        
                                        for(int i = 0; i < idCursos.length;i++){
                                            
                                            profs.add(new Professor(idCursos[i], nomeCursos[i]));
                                            int[] idT;
                                            idT = procuraIdTurma(con, idCursos[i]);
                                            for(int j = 0; j < idT.length; j++){
                                              
                                                idTurmas = new int[idCursos.length][idT.length];
                                                idTurmas[i][j] = idT[j];
                                                nomeDisci = procuraDisciplinas(con, idTurmas[i][j]);
                                                carga = procuraCarga(con, idTurmas[i][j]);
                                                for(int c = 0; c < carga.length;c++){
                                                     profs.add(new Professor(carga[c], nomeDisci[c], true));
                                                     System.out.println(carga[c]);
                                                     System.out.println(nomeDisci[c]);
                                                }
                                               
                                            }
                                        }
                                       
                                        ThisNome = procuraNome(con, getId());
                                    }					
				}
			}
			con.close();
		} else {
			throw new SQLException();
		}
	}
    
    public static String procuraNome(Connection con, long id) throws SQLException{
        PreparedStatement prst = con.prepareStatement("SELECT * FROM `professores` WHERE `id` = ?");
                prst.setLong(1, id);
        ResultSet rs = prst.executeQuery();
        rs.next();
        return rs.getString("nome");
    }
    
    public static String[] procuraDisciplinas(Connection con, int idTurma) throws SQLException {
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `disciplinas` WHERE `id-turmas` = ?");
		prst.setInt(1, idTurma);
		ResultSet rs = prst.executeQuery();
		prst = con.prepareStatement("SELECT * FROM `disciplinas` WHERE `id-turmas` = ?");
		prst.setInt(1, idTurma);
		ResultSet cp = prst.executeQuery();
		String nomesDis[];
		int cont = 0;
		while (rs.next()) {
			cont++;
		}
		nomesDis = new String[cont];
		cont = 0;
		while (cp.next()) {
			nomesDis[cont] = cp.getString("nome");
			cont++;
		}
		return nomesDis;

	}

	public static int[] procuraCarga(Connection con, int idTurma) throws SQLException {
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `disciplinas` WHERE `id-turmas` = ?");
		prst.setInt(1, idTurma);
		ResultSet rs = prst.executeQuery();
		prst = con.prepareStatement("SELECT * FROM `disciplinas` WHERE `id-turmas` = ?");
		prst.setInt(1, idTurma);
		ResultSet cp = prst.executeQuery();
		int cont = 0;
		int cargas[];
		while (rs.next()) {
			cont++;
		}
		cargas = new int[cont];
		cont = 0;
		while (cp.next()) {
			cargas[cont] = cp.getInt("carga-horaria-min");
			cont++;
		}
		return cargas;
	}

	public static int[] procuraIdTurma(Connection con, int idCurso) throws SQLException {
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `turmas` WHERE `id-cursos` = ?");
		prst.setInt(1, idCurso);
		ResultSet rs = prst.executeQuery();
		prst = con.prepareStatement("SELECT * FROM `turmas` WHERE `id-cursos` = ?");
		prst.setInt(1, idCurso);
		ResultSet cp = prst.executeQuery();
		int ids[];
		int cont = 0;
		while (rs.next()) {
			cont++;
		}
		ids = new int[cont];
		cont = 0;
		while (cp.next()) {
			ids[cont] = cp.getInt("id");
			cont++;
		}
		return ids;
	}

	public static String[] procuraCursos(Connection con, int idDepto) throws SQLException {
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `cursos` WHERE `id-depto` = ?");
		prst.setInt(1, idDepto);
		ResultSet rs = prst.executeQuery();
		prst = con.prepareStatement("SELECT * FROM `cursos` WHERE `id-depto` = ?");
		prst.setInt(1, idDepto);
		ResultSet cp = prst.executeQuery();
		String nomesCursos[];
		int cont = 0;
		while (rs.next()) {
			cont++;
		}
		nomesCursos = new String[cont];
		cont = 0;
		while (cp.next()) {
			nomesCursos[cont] = cp.getString("nome");
			cont++;
		}
		return nomesCursos;
	}

	public static int[] procuraIdCurso(Connection con, int idDepto) throws SQLException {
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `cursos` WHERE `id-depto` = ?");
		prst.setInt(1, idDepto);
		ResultSet rs = prst.executeQuery();
		prst = con.prepareStatement("SELECT * FROM `cursos` WHERE `id-depto` = ?");
		prst.setInt(1, idDepto);
		ResultSet cp = prst.executeQuery();
		int ids[];
		int cont = 0;
		while (rs.next()) {
			cont++;
		}
		ids = new int[cont];
		cont = 0;
		while (cp.next()) {
			ids[cont] = cp.getInt("id");
			cont++;
		}
		return ids;
	}


    @FXML
    private void fechar(ActionEvent event) throws Exception {
        Stage modal = (Stage)btnFechar.getScene().getWindow();
        modal.close();
    }
    
    public void criaTabela(){
            cursosModal.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
            disciplinasModal.setCellValueFactory(new PropertyValueFactory<>("nomeDisciplina"));        
            cargaModal.setCellValueFactory(new PropertyValueFactory<>("cargaH"));
            tabelaModal.setItems(profs);
                           
    }
}