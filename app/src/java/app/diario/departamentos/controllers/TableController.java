package app.diario.departamentos.controllers;

import app.diario.departamentos.model.Departamento;
import app.diario.departamentos.repository.DepartamentoRepository;
import app.diario.telatransicao.MainTelaTransicaoDiario;
import app.inicio.MainApp;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class TableController implements Initializable {

    private ObservableList<Departamento> tabelaDeptos;

    private Timer t;

    private String avisoMensagem = "";
    private int avisoTipo;

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
    private Button voltar;

    @FXML
    void modalAdicionar(ActionEvent event) throws IOException, SQLException {
        Stage modalAdicionar = new Stage();
        FXMLLoader modalAdicionarFXMLLoader = new FXMLLoader(getClass().getResource("/app/diario/departamentos/ModalAdicionar.fxml"));
        Parent modalAdicionarParent = (Parent) modalAdicionarFXMLLoader.load();
        ModalAdicionarController modalAdicionarController = modalAdicionarFXMLLoader.<ModalAdicionarController>getController();

        modalAdicionar.setScene(new Scene(modalAdicionarParent));
        modalAdicionar.setResizable(false);
        modalAdicionar.initOwner(((Node) event.getSource()).getScene().getWindow());
        modalAdicionar.initModality(Modality.APPLICATION_MODAL);
        modalAdicionar.showAndWait();
        boolean status = modalAdicionarController.getStatus();

        if (status) {
            setAviso("Departamento Adicionado com Sucesso", 1);
        }

        loadTableData();
    }

    private void loadTableData() {
        try {
            tabelaDeptos = FXCollections.observableArrayList(DepartamentoRepository.consulta());
            tabela.getSortOrder().add(col_campus);
            tabela.setItems(tabelaDeptos);
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
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_campus.setCellValueFactory(new PropertyValueFactory<>("nomeCampi"));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>("hbox"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

    }

    @FXML
    private void pesquisar(javafx.scene.input.KeyEvent event) {
        FilteredList<Departamento> filtro = new FilteredList<>(tabelaDeptos, p -> true);
        pesquisaTf.textProperty().addListener((observable, oldValue, newValue) -> {
            filtro.setPredicate(depto -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String digitado = newValue.toLowerCase();

                if (depto.getNome().toLowerCase().contains(digitado)) {
                    return true;
                } else if (depto.getNomeCampi().toLowerCase().contains(digitado)) {
                    return true;
                } else if (Integer.toString(depto.getId()).contains(digitado)) {
                    return true;
                }

                return false;
            });

            SortedList<Departamento> sortedList = new SortedList<>(filtro);
            sortedList.comparatorProperty().bind(tabela.comparatorProperty());
            tabela.setItems(sortedList);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            tabelaDeptos = FXCollections.observableArrayList();
            initTable();
            loadTableData();
            mostraAviso();
        });
    }

    @FXML
    private void voltafunc(ActionEvent event) throws IOException {
        MainTelaTransicaoDiario main = new MainTelaTransicaoDiario();
        Stage tabelaDeptos = (Stage) voltar.getScene().getWindow();
        tabelaDeptos.close();
        try {
            main.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}