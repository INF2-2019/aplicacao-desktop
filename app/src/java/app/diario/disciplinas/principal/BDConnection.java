package app.diario.disciplinas.principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {

	public static Connection getConnection() throws SQLException {
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/diario?useTimezone=true&serverTimezone=UTC", "root", "");

		return connect;

	}

}
