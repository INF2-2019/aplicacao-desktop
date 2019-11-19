package app.biblioteca.reservas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;

public class AlunoException extends Exception {

	public AlunoException(String message) {
		super(message);
		ErroMain erro = new ErroMain(message);
		try {
			erro.start(new Stage());
		} catch (Exception ex) {
			Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
