package app.diario.diario.controllers;



import app.diario.diario.main.MainDiario;
import app.diario.diario.model.AtividadeModel;
import app.diario.diario.model.ConteudosModel;
import app.diario.diario.model.DiarioModel;
import app.diario.diario.repository.ConteudosRepository;
import app.diario.telatransicao.MainTelaTransicaoDiario;

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
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class TableController implements Initializable {

   @FXML
   private TableColumn<ConteudosModel,String> Etapa;
   @FXML
   private TableColumn<ConteudosModel, String> Data;
   @FXML
   private TableColumn<ConteudosModel, String> Conteudo;
   @FXML
   private TableView<ConteudosModel> tabela;
   @FXML
   private TableView<AtividadeModel> TabelaAtv;
   
   private ObservableList<ConteudosModel> tabelaConteudos;
   
   private ObservableList<AtividadeModel> tabelaAtividades;
   
   @FXML
   private TableColumn<AtividadeModel,String> Atividade ;
   @FXML
   private TableColumn<AtividadeModel, String>Etapa1 ;
   @FXML
   private TableColumn<AtividadeModel, String> Valor;
   private Timer t;
   private String avisoMensagem = "";
   private int avisoTipo;
   @FXML
   private Label aviso;
   @FXML
   private Button adicionar;
   @FXML
   private TableColumn<ConteudosModel, HBox> col_acoes;
   @FXML
   private TableColumn<AtividadeModel, HBox> col_acoes2;
   @FXML
   private TextField pesquisaTf;
   @FXML
   private Button voltar;
   
   
    @FXML
    void modalAdicionar(ActionEvent event) throws IOException, SQLException {
        Stage modalAdicionar = new Stage();
        FXMLLoader modalAdicionarFXMLLoader = new FXMLLoader(getClass().getResource("/app/diario/diario/ModalAdicionar.fxml"));
        Parent modalAdicionarParent = (Parent) modalAdicionarFXMLLoader.load();
        ModalAdicionarController modalAdicionarController = modalAdicionarFXMLLoader.<ModalAdicionarController>getController();

        modalAdicionar.setScene(new Scene(modalAdicionarParent));
        modalAdicionar.initOwner(((Node) event.getSource()).getScene().getWindow());
        modalAdicionar.initModality(Modality.APPLICATION_MODAL);
        modalAdicionar.showAndWait();
        boolean status = modalAdicionarController.getStatus();

        if (status) {
            setAviso("Adicionado com Sucesso", 1);
        }

        loadTableData();
    }

    private void loadTableData() {
      try {
            tabelaAtividades = FXCollections.observableArrayList(ConteudosRepository.consultaAtividade());
             
           TabelaAtv.setItems(tabelaAtividades);
        } catch (SQLException e) {
            setAviso("Falha ao processar requisição", 0);
        }
       try {
            tabelaConteudos = FXCollections.observableArrayList(ConteudosRepository.consulta());
            tabela.getSortOrder().add(Data);
            tabela.setItems(tabelaConteudos);
        } catch (SQLException e) {
            setAviso("Falha ao processar requisição", 0);
        }
    }

    public void setAviso(String avisoMensagem, int avisoTipo) {
        this.avisoMensagem = avisoMensagem;
        this.avisoTipo = avisoTipo;
        mostraAviso();
    }

    public void mostraAviso() {
        if (this.avisoTipo == 1) {
            this.aviso.getStyleClass().clear();
            this.aviso.getStyleClass().add("aviso-sucesso");
            this.aviso.setText(this.avisoMensagem);
        } else if (this.avisoTipo == 0) {
            this.aviso.getStyleClass().clear();
            this.aviso.getStyleClass().add("aviso-erro");
            this.aviso.setText(this.avisoMensagem);
        }
        if (!this.avisoMensagem.isEmpty()) {
            this.aviso.getStyleClass().add("aviso");
            fadeOutAviso();
        }
    }

    public void fadeOutAviso() {
        FadeTransition transicao = new FadeTransition(Duration.millis(5000), aviso);
        transicao.setFromValue(1);
        transicao.setToValue(0);
        transicao.play();
    }

    private void initTable() {
      
        Data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>("hbox"));
        Conteudo.setCellValueFactory(new PropertyValueFactory<>("Conteudo"));
        Etapa.setCellValueFactory(new PropertyValueFactory<>("Etapa"));
       
        Atividade.setCellValueFactory(new PropertyValueFactory<>("conteudo"));
        col_acoes2.setCellValueFactory(new PropertyValueFactory<>("hbox"));
        Valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        Etapa1.setCellValueFactory(new PropertyValueFactory<>("idEtapa"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            tabelaConteudos= FXCollections.observableArrayList(); 
            tabelaAtividades=FXCollections.observableArrayList();
            initTable();
            loadTableData();
            mostraAviso();
        });
      
    }
     @FXML 
    public void acaoVoltar(ActionEvent event){
        MainTelaTransicaoDiario main = new MainTelaTransicaoDiario();
        
        try {
            main.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
       // MainDiario.getStage().close();
        Stage stage = (Stage)  voltar.getScene().getWindow(); //Obtendo a janela atual
    stage.close();
         
    }
   
}
   
