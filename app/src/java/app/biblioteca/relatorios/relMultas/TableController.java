/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.relatorios.relMultas;

import java.awt.event.ActionEvent;
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
import javafx.scene.control.Label;

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
    private TableColumn<ModelTable, Label> col_estado;
    
    
    

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
        System.out.println("clicou");
    }
    
    static public void consultarBD(){
        try {
            Connection con = DbConnector.getConnection();
            
            ResultSet rs = con.createStatement().executeQuery("select * from emprestimos");
            oblist = FXCollections.observableArrayList();
            while(rs.next()){
                oblist.add(new ModelTable(rs.getString("id"),rs.getString("id-alunos"),rs.getString("id-acervo"),rs.getDate("data-emprestimo"),rs.getDate("data-prev-devol"),rs.getDate("data-devolucao"),rs.getDouble("multa"),new Label()));
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
        //col_funcoes.setCellValueFactory(new PropertyValueFactory<>("info"));
        //col_funcoes1.setCellValueFactory(new PropertyValueFactory<>("edita"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        table.setItems(oblist);
    }
}
