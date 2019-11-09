package app.diario.departamentos.controllers;

import app.diario.departamentos.model.Departamento;
import app.diario.departamentos.repository.DepartamentoRepository;
import java.awt.event.KeyEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;

public class TableController implements Initializable{
    private ObservableList<Departamento> tabelaDeptos;
    
    private Timer t;
    
    private static String mensagem;
    
    @FXML
    private Label aviso;
    
    @FXML
    private Button adicionar;
    
    @FXML
    private TableView<Departamento> tabela;

    @FXML
    private TableColumn<Departamento, String> col_id;
    
    @FXML
    private TableColumn<Departamento, String> col_nome;

    @FXML
    private TableColumn<Departamento, String> col_campus;

    @FXML
    private TableColumn<Departamento, HBox> col_acoes;
    
    @FXML
    private TextField pesquisaTf;
    
    @FXML
    void modalAdicionar(ActionEvent event) throws IOException, SQLException {
        Stage modalAdicionar = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/diario/departamentos/ModalAdicionar.fxml"));
        modalAdicionar.setScene(new Scene(root));
        modalAdicionar.initOwner(((Node)event.getSource()).getScene().getWindow());
        modalAdicionar.initModality(Modality.APPLICATION_MODAL);
        modalAdicionar.showAndWait();
        loadTableData();
    }
    
    private void loadTableData(){
        try{
            tabelaDeptos =  FXCollections.observableArrayList(DepartamentoRepository.consulta());      
            tabela.getSortOrder().add(col_campus);
            tabela.setItems(tabelaDeptos);
            aviso.setText("");

        } 
        catch (SQLException e){
            setAviso("Falha ao processar requisição");
        }  
    }
    
    public void setAviso(String aviso){
        this.aviso.setText(aviso);
    }
    
    private void initTable(){
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_campus.setCellValueFactory(new PropertyValueFactory<>("nomeCampi"));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>("hbox"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
    }
    
    @FXML
    private void pesquisar(javafx.scene.input.KeyEvent event) {
        System.out.println("A");
        FilteredList<Departamento> filtro = new FilteredList<>(tabelaDeptos, p -> true);
        pesquisaTf.textProperty().addListener((observable, oldValue, newValue) -> {
            filtro.setPredicate(depto -> {
                
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String digitado = newValue.toLowerCase();
                
                if (depto.getNome().toLowerCase().contains(digitado)) {
                    return true; 
                } 
                else if (depto.getNomeCampi().toLowerCase().contains(digitado)) {
                    return true;
                }
                else if (Integer.toString(depto.getId()).contains(digitado)) {
                    return true;
                }
                
                return false;
            });
            
            SortedList<Departamento> sortedList = new SortedList<>(filtro);
            sortedList.comparatorProperty().bind(tabela.comparatorProperty());
            tabela.setItems(sortedList);        
        });     
    }
    
    public static void setMensagem(String m){
        mensagem = m;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            tabelaDeptos = FXCollections.observableArrayList();
            initTable();
            loadTableData();
        });
    }
}