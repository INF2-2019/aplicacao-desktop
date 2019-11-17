/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.alunos.Inserir;

import app.biblioteca.alunos.Principal.DbConnector;
import app.biblioteca.alunos.Principal.Hasher;
import app.biblioteca.alunos.Principal.SexoCategoria;
import app.biblioteca.alunos.Principal.Validators;
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
 *
 * @author tuba1
 */
public class InsereController implements Initializable {
    
    
    

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
    private TextField fotoInput;

    @FXML
    private PasswordField senhaInput;

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
       
    @FXML
    void acaoCancelar(ActionEvent event) {
        fecha();
    }
    @FXML
    void acaoSalvar(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Connection con = new DbConnector().getConnection(); 
        if(Validators.isCPF(cpfInput.getText())==false)
        {
     
	System.out.println("<erro><mensagem>CPF inserido é inválido</mensagem></erro>");
        }
        else{
        String sql = "insert into alunos"
                + " (id,nome,email,senha,sexo,nascimento,logradouro,numero,complemento,bairro,cidade,cep,uf,foto)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        System.out.println((Button) event.getSource());
        // preenche os valores
        stmt.setLong(1,Long.parseLong(cpfInput.getText()));
        stmt.setString(2,(nomeInput.getText()));
        stmt.setString(3,(emailInput.getText()));
        stmt.setString(4,Hasher.hash(senhaInput.getText()));
        
         String sexo=String.valueOf(SexoInput.getValue());
                 System.out.println(sexo); 
        
        if(sexo.equals("Masculino")){
            stmt.setString(5, "M");
        }else if(sexo.equals("Femenino")){
            stmt.setString(5, "F");
        }
     
       // SexoInput.toString()
        java.util.Date date = java.util.Date.from(nascInput.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        stmt.setDate(6, sqlDate);

         stmt.setString(7,(logInput.getText()));
         stmt.setInt(8,Integer.parseInt(numInput.getText()));
                stmt.setString(9,(compInput.getText()));
                         stmt.setString(10,(bairroInput.getText()));
                  stmt.setString(11,(cityInput.getText())); 
                           stmt.setInt(12,Integer.parseInt(cepInput.getText()));
                         stmt.setString(13,(ufInput.getText()));
                         stmt.setString(14, fotoInput.getText());
         
        stmt.execute();
        stmt.close();
        con.close();
        fecha();
        
    }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CarregarSexo();
    }    
    public void CarregarSexo()
    {
       SexoCategoria categoria1=new SexoCategoria(1,"Masculino") ;
         SexoCategoria categoria2=new SexoCategoria(2,"Femenino") ;
           
          
           categorias.add(categoria2);
           categorias.add(categoria1);
           obscategoria=FXCollections.observableList(categorias);
           SexoInput.setItems(obscategoria);
           
    }
    public void fecha(){
        InsereMain.getStage().close();
    }
}
