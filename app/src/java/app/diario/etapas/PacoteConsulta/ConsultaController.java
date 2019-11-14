package app.diario.etapas.PacoteConsulta;

import app.diario.etapas.Principal.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConsultaController implements Initializable {
	@FXML
	private Label AnoLabel;

	@FXML
	private Label ValorLabel;

	@FXML
	private Label IdLabel;

	@FXML
	private Button botaoCancelar;

	@FXML
	private Button botaoSalvar;

	@FXML
	private TextField AnoInput;

	@FXML
	private TextField ValorInput;

	@FXML
	private TextField IdInput;

	@FXML
	void acaoCancelar(ActionEvent event) {
		fecha();
	}
	@FXML
	void acaoConsultar(ActionEvent event) throws SQLException {
		Connection con = new DbConnector().getConnection();

		String sql;
		int id, ano;
		double valor;
		
		if(!"".equals(IdInput.getText())){
			id = Integer.parseInt(IdInput.getText());
			sql = "select * from etapas WHERE id="+id;
		} else if(!"".equals(AnoInput.getText()) && !"".equals(ValorInput.getText())){
			ano = Integer.parseInt(AnoInput.getText());
			valor = Double.parseDouble(ValorInput.getText());
			sql = "select * from etapas WHERE ano="+ano+" AND valor="+valor;
		} else if(!"".equals(AnoInput.getText()) && "".equals(ValorInput.getText())){
			ano = Integer.parseInt(AnoInput.getText());
			sql = "select * from etapas WHERE ano="+ano;
		} else{
			valor = Double.parseDouble(ValorInput.getText());
			sql = "select * from etapas WHERE valor="+valor;
		}
		// preenche os valores
		TableController.consultarBD(sql);
		fecha();

	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}    

	public void fecha(){
		ConsultaMain.getStage().close();
	}

}
