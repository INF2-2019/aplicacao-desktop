package app.biblioteca.descartes.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import app.biblioteca.descartes.main.DescartadoLista;
import app.biblioteca.descartes.main.Funcoes;
import app.biblioteca.descartes.main.MainApp;
import app.biblioteca.descartes.main.descartarMain;
import app.diario.departamentos.controllers.ModalAdicionarController;
import app.diario.telatransicao.MainTelaTransicaoDiario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class TelaDescartesController implements Initializable {
  
   
   
    @FXML
    private Label label;
    @FXML
    private Button Descartar;
    @FXML
    private Button Voltar;
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
    @FXML 
    public void acaoVoltar(){
        MainTelaTransicaoDiario main = new MainTelaTransicaoDiario();
        fecha();
        try {
            main.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TelaDescartesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void modalAdicionar(ActionEvent event) throws IOException, SQLException {
        Stage modalAdicionar = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/biblioteca/descartes/Descartar/ModalDescartar.fxml"));
        modalAdicionar.setScene(new Scene(root));
        modalAdicionar.initOwner(((Node)event.getSource()).getScene().getWindow());
        modalAdicionar.initModality(Modality.APPLICATION_MODAL);
        modalAdicionar.showAndWait();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    List ListaD = null;
      Funcoes fun =new Funcoes();
        try {
             ListaD =fun.ExibeOqueJaFoiDescartado();
        } catch (SQLException ex) {
            Logger.getLogger(TelaDescartesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        ACERVO.setCellValueFactory(new PropertyValueFactory<>("ACERVO"));
        MOTIVO.setCellValueFactory(new PropertyValueFactory<>("MOTIVO"));
        OPERADOR.setCellValueFactory(new PropertyValueFactory<>("OPERADOR"));
        DATA.setCellValueFactory(new PropertyValueFactory<>("DATA"));
    
        TabelaDescarte.setItems(FXCollections.observableArrayList(ListaD));
       TabelaDescarte.getColumns().addAll(ACERVO,MOTIVO,OPERADOR,DATA);
        
       
    
    }
    
    public void fecha() {
        MainApp.getStage().close();
    }
      
    }


    