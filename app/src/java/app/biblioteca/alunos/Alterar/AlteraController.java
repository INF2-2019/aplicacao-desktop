/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.alunos.Alterar;



import app.biblioteca.alunos.Principal.DbConnector;
import app.biblioteca.alunos.Principal.Hasher;
import app.biblioteca.alunos.Principal.SexoCategoria;
import app.biblioteca.alunos.Principal.TableController;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marcelo
 */
public class AlteraController implements Initializable {
    static long id;

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
	AlteraController.id = id;
    }
    
   
    @FXML
    private TextField bairroInput;

    @FXML
    private TextField ufInput;

    @FXML
    private Label AnoLabel4;

    @FXML
    private TextField cityInput;

    @FXML
    private Label AnoLabel5;

    @FXML
    private DatePicker nascInput;

    @FXML
    private Label AnoLabel2;

    @FXML
    private Label AnoLabel3;

    @FXML
    private TextField compInput;

    @FXML
    private Label AnoLabel8;

    @FXML
    private Label AnoLabel9;

    @FXML
    private Label AnoLabel6;

    @FXML
    private Label AnoLabel7;

    @FXML
    private TextField nomeInput;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Label AnoLabel10;

    @FXML
    private TextField numInput;

    @FXML
    private Label AnoLabel11;

    @FXML
    private TextField cepInput;

    @FXML
    private TextField cpfInput;
    @FXML
    private TextField fotoInput;
    @FXML
    private PasswordField senhaInput;

    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField logInput;

    @FXML
    private TextField emailInput;

    @FXML
    private Label AnoLabel;

    @FXML
    private Label AnoLabel1;


       @FXML
    private ComboBox<SexoCategoria> SexoInput;
   private List<SexoCategoria> categorias=new ArrayList<>();   
    private ObservableList<SexoCategoria> obscategoria;   
   

    
  
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarregarSexo();
    }
    public void CarregarSexo()
    {
       SexoCategoria categoria1=new SexoCategoria(1,"Masculino") ;
         SexoCategoria categoria2=new SexoCategoria(2,"Feminino") ;
          
         
           categorias.add(categoria2);
           categorias.add(categoria1);
           obscategoria=FXCollections.observableList(categorias);
           SexoInput.setItems(obscategoria);
           
    }
    @FXML
    void AcaoCancelar(ActionEvent event) {
	fechar();
    }

    private void fechar() {
	AlteraMain.getStage().close();
    }
    
    @FXML
    void Alterar(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.print("pao");
	try {
            Connection connection = DbConnector.getConnection();
	    if(!"".equals(bairroInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `bairro` = '"+bairroInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
            if(!"".equals(nomeInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `nome` = '"+nomeInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
            if(!"".equals(emailInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `email` = '"+emailInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
            if(!(SexoInput.getValue() == null))
            {
                 String sexo=String.valueOf(SexoInput.getValue());
                 System.out.println(sexo);
                 if(sexo.equals("Masculino")){
                    
                        PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET `sexo` = 'M' WHERE `id` = "+AlteraController.getId());
                        stmt.execute();
                        stmt.close(); 
        }else if(sexo.equals("Feminino")){
            
            PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET `sexo` = 'F' WHERE `id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close(); 
        }                 
   
            }
            if(!"".equals(logInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `logradouro` = '"+logInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
            System.out.print("nao da n");
            
              
                if(!"".equals(compInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `complemento` = '"+logInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
                 if(!"".equals(cityInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `cidade` = '"+cityInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
                   if(!"".equals(cepInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `cep` = '"+cepInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
	      if(!"".equals(ufInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `uf` = '"+ufInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
              if(!"".equals(numInput.getText()))
                    {
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `numero` = '"+numInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
                  if(!(nascInput.getValue() == null))
                      
                    {
                        System.out.println("A"+nascInput.getValue()+"A");
                        java.util.Date date = java.util.Date.from(nascInput.getValue().atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant());
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
                        
                        PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `nascimento` = '"+sqlDate+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();  
                    }
                    
                  if(!"".equals(fotoInput.getText())){
                    PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `foto` = '"+fotoInput.getText()+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
                    stmt.execute();
                    stmt.close();  
                    }
                  if(!"".equals(senhaInput.getText())){
                      PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `senha` = '"+Hasher.hash(senhaInput.getText())+"'"
                    
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
                    stmt.execute();
                    stmt.close();  
                  }
              
	    
            connection.close();
            fechar();
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
/*
if(!"".equals(bairroInput.getText()) && !"".equals(ValorInput.getText())){
            PreparedStatement stmt = connection.prepareStatement("UPDATE `alunos` SET"
                    + " `ano` = '"+AnoInput.getText()+"'"
                    + ", `valor` = '"+ValorInput.getText()+"'"
                    + " WHERE `alunos`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();}
	    
	    else if(!"".equals(AnoInput.getText()) && "".equals(ValorInput.getText())){
            PreparedStatement stmt = connection.prepareStatement("UPDATE `etapas` SET"
                    + " `ano` = '"+AnoInput.getText()+"'"
                    + " WHERE `etapas`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();}
	    
	    else if("".equals(AnoInput.getText()) && !"".equals(ValorInput.getText())){
            PreparedStatement stmt = connection.prepareStatement("UPDATE `etapas` SET"
                    + " `valor` = '"+ValorInput.getText()+"'"
                    + " WHERE `etapas`.`id` = "+AlteraController.getId());
            stmt.execute();
            stmt.close();}


*/