
package app.biblioteca.relatorios.principal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getConnection() throws SQLException{
        Connection con = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca?useTimezone=true&serverTimezone=UTC", "root", "123456");
        }catch(ClassNotFoundException ex){
            
        }
        
        return con;
	
	
    }
}
