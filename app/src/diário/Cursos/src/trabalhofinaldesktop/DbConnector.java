
package trabalhofinaldesktop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getConnection() throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/diario?useTimezone=true&serverTimezone=UTC","root","");
        
        return con;
    }
}
