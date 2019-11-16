package app.biblioteca.acervo.inserir;

import app.biblioteca.acervo.inserir.midia.InsereMidiaMain;
import app.biblioteca.acervo.inserir.midia.InsereMidiaController;
import app.biblioteca.acervo.inserir.periodico.InserePeriodicoMain;
import app.biblioteca.acervo.inserir.livro.InsereLivroMain;
import app.biblioteca.acervo.inserir.livro.InsereLivroController;
import app.biblioteca.acervo.inserir.academico.InsereAcademicoController;
import app.biblioteca.acervo.inserir.academico.InsereAcademicoMain;
import app.biblioteca.acervo.inserir.periodico.InserePeriodicoController;
import app.biblioteca.acervo.principal.DbConnector;
import app.biblioteca.acervo.principal.TableController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsereController implements Initializable {
    
    ObservableList<String> tipoL= FXCollections.observableArrayList("livros","periodicos","academicos","midias");
    private static String id;

    public static String getId() {
        return id.toLowerCase();
    }

    public static void setId(String i) {
        id = i.toLowerCase();
    }
    
    @FXML
    private ChoiceBox tipo;
    
    @FXML
    private TextField idInput;

    @FXML
    private TextField idCampiInput;

    @FXML
    private TextField nomeInput;
    
    @FXML
    private TextField localInput;

    @FXML
    private TextField anoInput;

    @FXML
    private TextField editoraInput;
    
    @FXML
    private TextField paginasInput;
    
    
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    void acaoSalvar(ActionEvent event) throws SQLException {
        Connection con = new DbConnector().getConnection();

            /*inserção no acervo:*/
            String sql = "insert into acervo" +
                    " (`id`,`id-campi`, `nome`, `tipo`, `local`, `ano`, `editora`, `paginas`)" +
                    " values (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(idInput.getText()));
            stmt.setInt(2,Integer.parseInt(idCampiInput.getText()));
            stmt.setString(3,nomeInput.getText());
            stmt.setString(4,tipo.getValue().toString());
            stmt.setString(5,localInput.getText());
            stmt.setInt(6,Integer.parseInt(anoInput.getText()));
            stmt.setString(7,editoraInput.getText());
            stmt.setInt(8,Integer.parseInt(paginasInput.getText()));
            stmt.execute();
            
            
            /*inserção de acordo com tipo:*/
            setId(idInput.getText().toLowerCase());
            Stage ms=InsereMain.getStage();
            switch(tipo.getValue().toString().toLowerCase())
            {
                case "academicos":
                    InsereAcademicoMain mainAcademico = new InsereAcademicoMain();
                    InsereAcademicoController.setId(id);
                    try {
                        mainAcademico.start(ms);
                        /* o modal que depende do tipo será aberto no mesmo stage que o modal principal para inserção no acervo, então não será 
			*necessário fechar os dois, é só fechar o modal principal depois que o usuário terminar de preencher as informações específicas
			*InsereMain.getStage().close();-> o que é feito no controler do tipo específico
			*
			*PS.:quando o usuario cancelar o preenchimento do tipo especifico, os dados salvos em acervo anteriormente serão apagados, pois,
			* a pessoa nao pode salvar só em acervo sem salvar no tipo da obra->isso é feito em "acaoCancelar" do controller do tipo
                        */
                        
                    } catch (Exception ex) {
                        Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "livros":
                    InsereLivroMain mainLivro = new InsereLivroMain();
                    InsereLivroController.setId(id);
                    try {
                        mainLivro.start(ms);
                    } catch (Exception ex) {
                        Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "midias":
                    InsereMidiaMain mainMidia = new InsereMidiaMain();
                    InsereMidiaController.setId(id);
                    try {
                        mainMidia.start(ms);
                    } catch (Exception ex) {
                        Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "periodicos":
                    InserePeriodicoMain mainPeriodico = new InserePeriodicoMain();
                    InserePeriodicoController.setId(id);
                    try {
                        mainPeriodico.start(ms);
                    } catch (Exception ex) {
                        Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
            
            con.close();
        
	/*nossa tabela em especifico nao precisa do fechar porque vai ter outro modal aberto em seguida e nele sao fechados ambos*/
        //fecha();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipo.setItems(tipoL);
    }    
    
    public void fecha(){
        InsereMain.getStage().close();
    }
}
