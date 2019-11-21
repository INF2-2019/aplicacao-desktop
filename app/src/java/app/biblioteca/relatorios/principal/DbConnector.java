
package app.biblioteca.relatorios.principal;


import app.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getConnection() throws SQLException{
        Connection con = ConnectionFactory.getBiblioteca();
        if(con == null){
            throw new SQLException();
        }
        return con;
    }
}
