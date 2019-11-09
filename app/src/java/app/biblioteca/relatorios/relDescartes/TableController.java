/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.relatorios.relDescartes;

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
    private TableColumn<ModelTable, Integer> col_idAcervo;
    @FXML
    private TableColumn<ModelTable, Date> col_dataDescarte;
    @FXML
    private TableColumn<ModelTable, String> col_motivos;
    @FXML
    private TableColumn<ModelTable, String> col_operador;
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
	    try (Connection con = DbConnector.getConnection()) {
		ResultSet rs = con.createStatement().executeQuery("select * from descartes");
		oblist = FXCollections.observableArrayList();
		while(rs.next()){
		    oblist.add(new ModelTable(rs.getString("id-acervo"),rs.getDate("data-descarte"),rs.getString("motivos"),rs.getString("operador"),new Label()));
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
        //col_funcoes1.setCellValueFactory(new PropertyValueFactory<>("edita"));
        col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        table.setItems(oblist);
    }
}
