package diario.departamentos.controllers;

import departamentos.model.Departamento;
import diario.departamentos.repository.DepartamentoRepository;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import utils.ConnectionFactory;

public class TableController implements Initializable{
    
    private ObservableList<Departamento> tabelaDeptos;
    
    @FXML
    private TableView<Departamento> tabela;

    @FXML
    private TableColumn<Departamento, String> col_nome;

    @FXML
    private TableColumn<Departamento, Integer> col_campus;

    @FXML
    private TableColumn<Departamento, HBox> col_acoes;
    
    private void initTable(){
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_campus.setCellValueFactory(new PropertyValueFactory<>("idCampi"));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>("hbox"));
    }

    private void loadTableData() throws SQLException{
        tabelaDeptos = (ObservableList) DepartamentoRepository.consulta();
        
        tabela.setItems(tabelaDeptos);
        tabela.setOnMouseClicked(e ->{
            events();
        });
    }
    
    private void events(){
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaDeptos = FXCollections.observableArrayList();
        
        initTable();
        try {
            loadTableData();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}