package diario.departamentos.model;

import diario.departamentos.controllers.ModalConfirmacaoController;
import diario.departamentos.controllers.ModalEditarController;
import diario.departamentos.controllers.TableController;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Departamento {
    
    private int id, idCampi;
    private String nome;
    private Button btns[];
    private HBox hbox;
    
    public Departamento() {
        this(0, 0, "");
    }
    
    public Departamento(int idCampi, String nome) {
        this(0, idCampi, nome);
    }
    
    public Departamento(int id, int idCampi, String nome) {
        this.id = id;
        this.idCampi = idCampi;
        this.nome = nome;
        btns = new Button[2];
        hbox = new HBox();
        btns[0] = new Button("EDITAR");
        btns[1] = new Button("DELETAR");
        btns[0].getStyleClass().add("editar");
        btns[1].getStyleClass().add("deletar");
        hbox.getChildren().add(0, btns[0]);
        hbox.getChildren().add(1, btns[1]);
        btnSetup(this.id);
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox vbox) {
        this.hbox = vbox;
    }

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCampi() {
        return idCampi;
    }

    public void setIdCampi(int idCampi) {
        this.idCampi = idCampi;
    }

    public Button[] getBtns() {
        return btns;
    }

    public void setBtns(Button[] btns) {
        this.btns = btns;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void btnSetup(int id){
        btns[0].setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    editarDepto(event);
                } catch (SQLException ex) {
                    System.err.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
        }});
        btns[1].setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    removerDepto(event);
                } catch (SQLException ex) {
                    System.err.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }});
    }
    
    private void editarDepto (Event event) throws SQLException, IOException{
        Stage modalEditar = new Stage();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/diario/departamentos/resources/ModalEditar.fxml"));     

        Parent root = (Parent)fxmlLoader.load();          
        ModalEditarController controller = fxmlLoader.<ModalEditarController>getController();
        controller.setData(this.id, this.idCampi, this.nome);
        Scene modalEditarScene = new Scene(root); 

        modalEditar.setScene(modalEditarScene);     
        
        modalEditar.initOwner(((Node)event.getSource()).getScene().getWindow());
        modalEditar.initModality(Modality.APPLICATION_MODAL);
        modalEditar.showAndWait();

        Stage table = (Stage)btns[0].getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/diario/departamentos/resources/TabelaDepartamentos.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("app/resources/styles.css");
        table.setScene(scene);
    }
    
    private void removerDepto(Event event) throws SQLException, IOException{
        Stage modalConfirmar = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/diario/departamentos/resources/ModalConfirmacao.fxml"));
        
        modalConfirmar.setScene(new Scene(root));
        ModalConfirmacaoController.setId(this.id);
        modalConfirmar.initOwner(((Node)event.getSource()).getScene().getWindow());
        modalConfirmar.initModality(Modality.APPLICATION_MODAL);
        modalConfirmar.showAndWait();

        Stage table = (Stage)btns[1].getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/diario/departamentos/resources/TabelaDepartamentos.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("app/resources/styles.css");
        table.setScene(scene);
    }     
}