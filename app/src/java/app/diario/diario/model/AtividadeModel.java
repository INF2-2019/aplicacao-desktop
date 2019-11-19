/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.diario.model;

import app.diario.diario.controllers.ModalEditarController;
import app.diario.diario.controllers.ModalRemoverController;
import app.diario.diario.controllers.TableController;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AtividadeModel {

	String  idEtapa, idDisciplina;
	String conteudo ;//nesse caso é o id do conteudo
        int ideetapas,id;
	String data ;
	Double valor,nota;
        private Button btns[];
        private HBox hbox;

   
	  public AtividadeModel() {
      
    }

    public int getIdeetapas() {
        return ideetapas;
    }

    public void setIdeetapas(int ideetapas) {
        this.ideetapas = ideetapas;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

      

    public AtividadeModel(String conteudo, int ideetapas, String data) {
        this.conteudo = conteudo;
        this.ideetapas = ideetapas;
        this.data = data;
    }

    public AtividadeModel(int id, String idEtapa, String idDisciplina, String conteudo, int ideetapas, String data, Double valor, Button[] btns, HBox hbox) {
        this.id = id;
        this.idEtapa = idEtapa;
        this.idDisciplina = idDisciplina;
        this.conteudo = conteudo;
        this.ideetapas = ideetapas;
        this.data = data;
        btns = new Button[3];
        hbox = new HBox();
        btns[0] = new Button("EDITAR");
        btns[1] = new Button("DELETAR");
        btns[2] = new Button("FALTAS");
        
        btns[0].getStyleClass().add("editar");
        btns[1].getStyleClass().add("deletar");
        
        hbox.getChildren().add(0, btns[0]);
        hbox.getChildren().add(1, btns[1]);
        hbox.getChildren().add(2,btns[2]);
        
         
    }

    public AtividadeModel(String idEtapa, String conteudo, String data, Button[] btns, HBox hbox) {
        this.idEtapa = idEtapa;
        this.conteudo = conteudo;
        this.data = data;
        this.btns = btns;
        this.hbox = hbox;
         btns = new Button[3];
        hbox = new HBox();
         btns[0] = new Button("EDITAR");
        btns[1] = new Button("DELETAR");
        btns[2] = new Button("FALTAS");
        
        btns[0].getStyleClass().add("editar");
        btns[1].getStyleClass().add("deletar");
        
        hbox.getChildren().add(0, btns[0]);
        hbox.getChildren().add(1, btns[1]);
        hbox.getChildren().add(2,btns[2]);
       
    //    btnSetup(1);
         
    }

   

    public AtividadeModel(int id, String idEtapa, String idDisciplina, String conteudo,String Data) {
        this.id = id;
        this.idEtapa = idEtapa;
        this.idDisciplina = idDisciplina;
        this.conteudo = conteudo;
        btns = new Button[3];
        hbox = new HBox();
        btns[0] = new Button("EDITAR");
        btns[1] = new Button("DELETAR");
        btns[2] = new Button("FALTAS");
        
        btns[0].getStyleClass().add("editar");
        btns[1].getStyleClass().add("deletar");
        
        hbox.getChildren().add(0, btns[0]);
        hbox.getChildren().add(1, btns[1]);
        hbox.getChildren().add(2,btns[2]);
     //   btnSetup(1);
    }

    public AtividadeModel(int id, String idEtapa, String idDisciplina, String conteudo, String data, Double valor) {
        this.id = id;
        this.idEtapa = idEtapa;
        this.idDisciplina = idDisciplina;
        this.conteudo = conteudo;
        this.data = data;
        this.valor = valor;
         btns = new Button[3];
        hbox = new HBox();
        btns[0] = new Button("EDITAR");
        btns[1] = new Button("DELETAR");
        btns[2] = new Button("FALTAS");
        
        btns[0].getStyleClass().add("editar");
        btns[1].getStyleClass().add("deletar");
        
        hbox.getChildren().add(0, btns[0]);
        hbox.getChildren().add(1, btns[1]);
        hbox.getChildren().add(2,btns[2]);
      //  btnSetup(1);
    }

    public AtividadeModel(String idEtapa, String conteudo, String data) {
        this.idEtapa = idEtapa;
        this.conteudo = conteudo;
        this.data = data;
        
        btns = new Button[3];
        hbox = new HBox();
        btns[0] = new Button("EDITAR");
        btns[1] = new Button("DELETAR");
        btns[2] = new Button("FALTAS");
        
        btns[0].getStyleClass().add("editar");
        btns[1].getStyleClass().add("deletar");
        
        hbox.getChildren().add(0, btns[0]);
        hbox.getChildren().add(1, btns[1]);
        hbox.getChildren().add(2,btns[2]);
      //  btnSetup(1);
    }

    public AtividadeModel(String idEtapa, String conteudo, String data,Double valor ) {
        this.idEtapa = idEtapa;
        this.conteudo = conteudo;
        this.data = data;
        this.valor=valor;
         btns = new Button[3];
        hbox = new HBox();
        btns[0] = new Button("EDITAR");
        btns[1] = new Button("DELETAR");
        btns[2] = new Button("FALTAS");
       btns[0].getStyleClass().add("editar");
        btns[1].getStyleClass().add("deletar");
        
        hbox.getChildren().add(0, btns[0]);
        hbox.getChildren().add(1, btns[1]);
        hbox.getChildren().add(2,btns[2]);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(String idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(String idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }
     public Button[] getBtns() {
        return btns;
    }

    public void setBtns(Button[] btns) {
        this.btns = btns;
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

		 public void btnSetup(int id) {
        btns[0].setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    editar(event);
                } catch (SQLException ex) {
                    System.err.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        });
        btns[1].setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    remover(event);
                } catch (SQLException ex) {
                    System.err.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        });
         btns[2].setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    remover(event);
                } catch (SQLException ex) {
                    System.err.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        });
         /*btns[3].setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    faltas(event);
                } catch (SQLException ex) {
                    System.err.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        });*/
    }

    private void editar(Event event) throws SQLException, IOException {
        Stage modalEditar = new Stage();
        FXMLLoader modalEditarFXMLLoader = new FXMLLoader(getClass().getResource("/app/diario/diario/ModalEditar.fxml"));

        Parent modalEditarParent = (Parent) modalEditarFXMLLoader.load();
        ModalEditarController modalEditarController = modalEditarFXMLLoader.<ModalEditarController>getController();
//        modalEditarController.setData(this.id, this.idEtapa, this.conteudo);
        
        modalEditar.setScene(new Scene(modalEditarParent));
        modalEditar.initOwner(((Node) event.getSource()).getScene().getWindow());
        modalEditar.initModality(Modality.APPLICATION_MODAL);
        modalEditar.showAndWait();
//        boolean status = modalEditarController.getStatus();

        Stage table = (Stage) btns[1].getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane TabelaConteudoRoot = fxmlLoader.load(getClass().getResource("/app/diario/diario/TabelaConteudo.fxml").openStream());
        TableController tableController = (TableController) fxmlLoader.getController();

      /*  if (status) {
            tableController.setAviso("Conteudo editado com sucesso", 1);
            
        }*/

        Scene scene = new Scene(TabelaConteudoRoot);
        scene.getStylesheets().add("/app/diario/diario/EstiloDiario.css");
        table.setScene(scene);
    }
    /*private void faltas (Event event) throws SQLException, IOException {
    
         Stage modalEditar = new Stage();
        FXMLLoader modalEFaltasFXMLLoader = new FXMLLoader(getClass().getResource("/app/diario/diario/ModalFaltas.fxml"));

       /* Parent FaltasModalParent = (Parent) modalFaltasFXMLLoader.load();
        ModalEditarController modalEditarController = modalEditarFXMLLoader.<ModalEditarController>getController();
//        modalEditarController.setData(this.id, this.idEtapa, this.conteudo);
        *//*
        //modalEditar.setScene(new Scene(modalFaltasParent));
        modalEditar.initOwner(((Node) event.getSource()).getScene().getWindow());
        modalEditar.initModality(Modality.APPLICATION_MODAL);
        modalEditar.showAndWait();
//        boolean status = modalEditarController.getStatus();

        Stage table = (Stage) btns[1].getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane TabelaConteudoRoot = fxmlLoader.load(getClass().getResource("/app/diario/diario/TabelaConteudo.fxml").openStream());
        TableController tableController = (TableController) fxmlLoader.getController();

      /*  if (status) {
            tableController.setAviso("Conteudo editado com sucesso", 1);
            
        }*//*

        Scene scene = new Scene(TabelaConteudoRoot);
        scene.getStylesheets().add("/app/diario/diario/EstiloDiario.css");
        table.setScene(scene);
    }

   */
      
    private void remover(Event event) throws SQLException, IOException {
        Stage modalRemover = new Stage();
        FXMLLoader modalRemoverFXMLLoader = new FXMLLoader(getClass().getResource("/app/diario/diario/ModalRemover.fxml"));

        Parent modalRemoverParent = (Parent) modalRemoverFXMLLoader.load();
        ModalRemoverController modalRemoverController = modalRemoverFXMLLoader.<ModalRemoverController>getController();
        modalRemoverController.setId(this.id);

        modalRemover.setScene(new Scene(modalRemoverParent));
        modalRemover.initOwner(((Node) event.getSource()).getScene().getWindow());
        modalRemover.initModality(Modality.APPLICATION_MODAL);
        modalRemover.showAndWait();
    //  boolean status = modalRemoverController.getStatus();

        Stage table = (Stage) btns[1].getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane TabelaConteudoRoot = fxmlLoader.load(getClass().getResource("/app/diario/diario/TabelaConteudo.fxml").openStream());
        TableController tableController = (TableController) fxmlLoader.getController();

       /* if (status) {
            tableController.setAviso("Conteudo com sucesso", 1);
        }
*/
        Scene scene = new Scene(TabelaConteudoRoot);
        scene.getStylesheets().add("/app/diario/diario/EstiloDiario.css");
        table.setScene(scene);
    }

}

