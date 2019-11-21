package app.diario.etapas.Principal;

import app.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public class DbConnector {
    public static Connection getConnection() throws SQLException{
        Connection con = ConnectionFactory.getDiario();
        if(con == null){
            throw new SQLException();
        }
        return con;
    }
}
