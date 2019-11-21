package app.biblioteca.reservas;
import app.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {
    public static Connection getConnection() throws SQLException{
        Connection con = ConnectionFactory.getBiblioteca();
        if(con == null){
            throw new SQLException();
        }
        return con;
    }

}
