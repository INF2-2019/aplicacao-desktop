package app.diario.etapas.Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public class DbConnector {
    public static Connection getConnection() throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:/diario?useTimezone=true&serverTimezone=UTC","root","");
        
        return con;
    }
}
