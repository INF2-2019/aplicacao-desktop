/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.biblioteca.relatorios.relAcervo;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcelo
 */
public class FXMLAcervoTransicaoController implements Initializable {

    @FXML
    private Button relLivros;

    @FXML
    private Button relPeriodicos;

    @FXML
    private Button relAcademicos;

    @FXML
    private Button relMidias;

    @FXML
    void abreAcademicos(ActionEvent event) {
        RelAcervoAcademicosMain acad  =  new RelAcervoAcademicosMain();
        try {
            acad.start(new Stage());
            TransicaoMain.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAcervoTransicaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void abreLivros(ActionEvent event) {
        RelAcervoLivrosMain liv  =  new RelAcervoLivrosMain();
        try {
            liv.start(new Stage());
            TransicaoMain.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAcervoTransicaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void abreMidias(ActionEvent event) {
        RelAcervoMidiasMain mid  =  new RelAcervoMidiasMain();
        try {
            mid.start(new Stage());
            TransicaoMain.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAcervoTransicaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void abrePeriodicos(ActionEvent event) {
        RelAcervoPeridiocosMain peri  =  new RelAcervoPeridiocosMain();
        try {
            peri.start(new Stage());
            TransicaoMain.getStage().close();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAcervoTransicaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
