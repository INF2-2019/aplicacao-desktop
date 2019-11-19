package app.diario.alunos.Informar;


import app.diario.alunos.Principal.DbConnector;
import app.diario.alunos.Principal.TableController;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InfoController implements Initializable {
static long id;

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
	InfoController.id = id;
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
    private TextField logInput;
 
     @FXML
    private TextField fotonput;
      @FXML
    private PasswordField senhaInput;
@FXML
    private ImageView foto;
    @FXML
    private TextField emailInput;

    @FXML
    private Label AnoLabel;

    @FXML
    private Label AnoLabel1;

    @FXML
    private TextField SexoInput;

       @FXML
    private ImageView imageInput;

    
  
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
            Connection con = DbConnector.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM alunos  WHERE `id` = "+InfoController.getId());
            rs.next();
          cpfInput.setText(Long.toString(rs.getLong("id")));
         
            bairroInput.setText(rs.getString("bairro"));
           
            nomeInput.setText(rs.getString("nome"));
            if("M".equals(rs.getString("sexo")))
            {
                 SexoInput.setText("Masculino");
            }
            else if("F".equals(rs.getString("sexo")))
            {
                SexoInput.setText("Femenino");
            }
            else
            {
                 SexoInput.setText("Indefinido");
            }
         
            emailInput.setText(rs.getString("email"));
            cityInput.setText(rs.getString("cidade"));
            ufInput.setText(rs.getString("uf"));
            Date data = rs.getDate("nascimento");
            nascInput.setValue(data.toLocalDate());
            cepInput.setText(Integer.toString(rs.getInt("cep")));
            numInput.setText(Integer.toString(rs.getInt("numero")));
            logInput.setText(rs.getString("logradouro"));
            compInput.setText(rs.getString("complemento"));
            fotonput.setText(rs.getString("foto"));
           
            try{
                 Image img = new Image(rs.getString("foto"));
                foto.setImage(img);
            }catch(IllegalArgumentException ex){
               
            } 
      }catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   

    private void fechar() {
	InfoMain.getStage().close();
    }

    @FXML
    private void acaoCancelar(ActionEvent event) {
        fechar();
    }
    
}