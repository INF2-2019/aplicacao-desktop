package dn;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dn.DescartadoLista;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
  
   
   
    @FXML
    private Label label;
    @FXML
    private Button Descartar;
    @FXML
    private TableView<DescartadoLista> TabelaDescarte;

    @FXML
    private TableColumn<DescartadoLista ,String> ACERVO;
   
    @FXML
   private TableColumn<DescartadoLista,String> OPERADOR;
    @FXML
    private TableColumn<DescartadoLista, String > DATA;
    @FXML
    private TableColumn<DescartadoLista,String> MOTIVO;

   List<DescartadoLista> ListaDescartados;
private ObservableList<DescartadoLista> a =FXCollections.observableArrayList();
    private Object pane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    List ListaD = null;
      Funções fun =new Funções();
        try {
             ListaD =fun.ExibeOqueJaFoiDescartado();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        ACERVO.setCellValueFactory(new PropertyValueFactory<>("ACERVO"));
        MOTIVO.setCellValueFactory(new PropertyValueFactory<>("MOTIVO"));
        OPERADOR.setCellValueFactory(new PropertyValueFactory<>("OPERADOR"));
        DATA.setCellValueFactory(new PropertyValueFactory<>("DATA"));
    
        TabelaDescarte.setItems(FXCollections.observableArrayList(ListaD));
       TabelaDescarte.getColumns().addAll(ACERVO,MOTIVO,OPERADOR,DATA);
        
       
    
      
    }
 @FXML
    private void sobre(ActionEvent event) throws IOException{
        Parent sobre = FXMLLoader.load(getClass().getResource("ModalDadosD.fxml"));
   
         Stage modalAdicionar = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModalDadosD.fxml"));
        modalAdicionar.setScene(new Scene(root));
        modalAdicionar.initOwner(((Node)event.getSource()).getScene().getWindow());
        modalAdicionar.initModality(Modality.APPLICATION_MODAL);
        modalAdicionar.showAndWait();
       // loadTableData();
    }
}


    