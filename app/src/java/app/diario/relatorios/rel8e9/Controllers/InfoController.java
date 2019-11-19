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

    
    public void consulta() throws SQLException, IOException {
		Connection con = ConnectionFactory.getDiario();
		if (con != null) {
			PreparedStatement prst = con.prepareStatement("SELECT * FROM `professores` WHERE `id` = ?");
			prst.setInt(1, (int)id);
			ResultSet rs = prst.executeQuery();
			rs.next();
			int idDis[];
			idDis =  procuraIdDisciplinas(con, (int)id);
			String nomeCursos[] = new String[idDis.length];
			int cargas[];
			String nomeDis[];
			String cursoDis[][] = new String [idDis.length][3];
			nomeDis = procuraDisciplinas(con, idDis);
			cargas = procuraCarga(con, idDis);
			for (int i = 0; i < idDis.length; i++) {
				nomeCursos[i] = procuraCursos(con, idDis[i]);
				cursoDis[i][0] = nomeCursos[i];
				cursoDis[i][1] = nomeDis[i];
				cursoDis[i][2] = Integer.toString(cargas[i]);
			}
			sortStrings(cursoDis, idDis.length);
			int j = 0;
			for (int i = 0; i < idDis.length;i++, j++) {
			if (i != 0) {	
				if (!cursoDis[i][0].equals(cursoDis[i-1][0])) {
					profs.add(new Professor(cursoDis[i][0]));
				}
			} else {
				profs.add(new Professor(cursoDis[i][0]));
			}
				profs.add(new Professor(cursoDis[i][2], cursoDis[i][1], true));
			}
			ThisNome = procuraNome(con, getId());
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
    
    public static int[] procuraCarga(Connection con, int idDis[]) throws SQLException {
		int[] cargas;
		cargas = new int[idDis.length];
		PreparedStatement prst;
		ResultSet rs;
		for (int i = 0; i < idDis.length; i++) {
			prst = con.prepareStatement("SELECT * FROM `disciplinas` WHERE `id` = ?");
			prst.setInt(1, idDis[i]);
			rs = prst.executeQuery();
			rs.next();
			cargas[i] = rs.getInt("carga-horaria-min");
		}
		return cargas;
	}

	public static String procuraCursos(Connection con, int idDis) throws SQLException {
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `disciplinas` WHERE `id` = ?");
		prst.setInt(1, idDis);
		ResultSet rs = prst.executeQuery();
		rs.next();
		int idT = rs.getInt("id-turmas");
		prst = con.prepareStatement("SELECT * FROM `turmas` WHERE `id` = ?");
		prst.setInt(1, idT);
		rs = prst.executeQuery();
		rs.next();
		int idC = rs.getInt("id-cursos");
		prst = con.prepareStatement("SELECT * FROM `cursos` WHERE `id` = ?");
		prst.setInt(1, idC);
		rs = prst.executeQuery();
		rs.next();
		String curso = rs.getString("nome");
		return curso;
	}
	
	public static String[] procuraDisciplinas(Connection con, int idDis[]) throws SQLException {
		String nomeDis[] = new String [idDis.length];
		PreparedStatement prst;
		ResultSet rs;
		for (int i = 0; i < idDis.length; i++) {
			prst = con.prepareStatement("SELECT * FROM `disciplinas` WHERE `id` = ?");
			prst.setInt(1, idDis[i]);
			rs = prst.executeQuery();
			rs.next();
			nomeDis[i] = rs.getString("nome");
		}
		return nomeDis;
	}
	
	public static int[] procuraIdDisciplinas(Connection con, int idProf) throws SQLException {
		PreparedStatement prst = con.prepareStatement("SELECT * FROM `prof_disciplinas` WHERE `id-professores` = ?");
		prst.setInt(1, idProf);
		ResultSet rs = prst.executeQuery();
		prst = con.prepareStatement("SELECT * FROM `prof_disciplinas` WHERE `id-professores` = ?");
		prst.setInt(1, idProf);
		ResultSet cp = prst.executeQuery();
		int[] ids;
		int cont = 0;
		while (rs.next()) {
			cont++;
		}
		ids = new int[cont];
		cont = 0;
		while (cp.next()) {
			ids[cont] = cp.getInt("id-disciplinas");
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
	
	public static void sortStrings(String[][] arr, int n)  
    { 
        String temp; 
		String temp2;
		String temp3;
        // Sorting strings using bubble sort 
        for (int j = 0; j < n - 1; j++) 
        { 
            for (int i = j + 1; i < n; i++)  
            { 
                if (arr[j][0].compareTo(arr[i][0]) > 0) 
                { 
                    temp = arr[j][0];
					temp2 = arr[j][1];
					temp3 = arr[j][2];
                    arr[j][0] = arr[i][0];
					arr[j][1] = arr[i][1];
					arr[j][2] = arr[i][2];
                    arr[i][0] = temp;
					arr[i][1] = temp2; 
					arr[i][2] = temp3; 
                } 
            } 
        } 
    }
}