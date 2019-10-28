package departamentos.controllers;

import departamentos.model.Departamento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

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

    private void loadTableData(){
        for(int i = 1; i < 6; i++){
            tabelaDeptos.add(new Departamento(i-1, i, "Campus " + i));
        }
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
        loadTableData();
    }

}
