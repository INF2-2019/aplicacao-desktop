/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.campi.Principal;

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
