package app.diario.turmas.consultar;

import app.diario.turmas.principal.Conector;
import app.diario.turmas.principal.Turma;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConsultaController implements Initializable {

    @FXML
    private TableView<Turma> tab;

    @FXML
    public TableColumn<Turma, Integer> colId;

    @FXML
    public TableColumn<Turma, Integer> colCursos;

    @FXML
    public TableColumn<Turma, String> colNome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	colId.setCellValueFactory(new PropertyValueFactory<>("id"));
	colCursos.setCellValueFactory(new PropertyValueFactory<>("cursos"));
	colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	Connection con;
	ObservableList<Turma> tabList;
	try {
	    tabList = FXCollections.observableArrayList();
	    con = Conector.conectar();
	    String sql = "SELECT * FROM turmas";
	    ResultSet res = con.createStatement().executeQuery(sql);
	    while (res.next()) {
		sql = "SELECT * FROM cursos WHERE id=" + res.getInt("id-cursos");
		ResultSet res2 = con.createStatement().executeQuery(sql);
		res2.next();
		tabList.add(new Turma(res.getInt("id"), res2.getString("nome"), res.getString("nome")));
	    }
	    tab.setItems(tabList);
	    con.close();
	} catch (SQLException ex) {
	    Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

}
