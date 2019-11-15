package app.diario.matriculas.principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost/";
	private final static String USUARIO = "root";
	private final static String SENHA = "";
	private final static String DB = "diario";
	
	public static Connection conectar() throws SQLException, ClassNotFoundException{
		Class.forName(DRIVER);
		Connection c = DriverManager.getConnection(URL + DB + "?useTimezone=true&serverTimezone=UTC&useSSL=false", USUARIO, SENHA);
        return c;
    }
}
