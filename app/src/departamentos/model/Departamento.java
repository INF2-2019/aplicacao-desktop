package departamentos.model;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javax.swing.ButtonGroup;

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
        btns = new Button[3];
        hbox = new HBox();
        btns[0]= new Button("INFO");
        btns[1]=new Button("EDITAR");
        btns[2]=new Button("DELETAR");
        hbox.getChildren().add(0, btns[0]);
        btns[0].getStyleClass().add("info");
        btns[1].getStyleClass().add("editar");
        btns[2].getStyleClass().add("deletar");
        hbox.getChildren().add(1, btns[1]);
        hbox.getChildren().add(2, btns[2]);
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
    
}