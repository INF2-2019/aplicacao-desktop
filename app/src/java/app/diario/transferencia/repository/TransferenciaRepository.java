package app.diario.transferencia.repository;

import app.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferenciaRepository {
    
    public static String consultaNomeAluno(long cpf) throws SQLException{
        Connection con = ConnectionFactory.getDiario();
        if(con != null){
            PreparedStatement prst = con.prepareStatement("SELECT nome FROM `alunos` WHERE `id` = ?");
            prst.setLong(1, cpf);
            ResultSet rs = prst.executeQuery();
            
            String nome;
            
            if(rs.next()){
                nome = rs.getString("nome");
            } else {
                return "Aluno não encontrado";
            }
            

            prst.close();
            con.close();
            
            return nome;
        } else {
            throw new SQLException();
        }
    }
    
}
