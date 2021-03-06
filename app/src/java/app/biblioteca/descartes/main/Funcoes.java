/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.biblioteca.descartes.main;

import app.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class Funcoes {
     private ObservableList<DescartadoLista> ObservLista =FXCollections.observableArrayList();
    
    public List<DescartadoLista>  ExibeOqueJaFoiDescartado() throws SQLException{
        
       Connection conexao = ConnectionFactory.getBiblioteca();
       PreparedStatement st = conexao.prepareStatement("SELECT * FROM descartes");
       ResultSet rs = st.executeQuery();
     //  List<DescartadoLista> descartes = new LinkedList();
        List ListaD = null;
   while ( rs.next()) {
       String IdA = rs.getString("id-acervo"); 
       String Data = rs.getString("data-descarte"); 
       String Operador = rs.getString("operador"); 
       String motivos = rs.getString("motivos");
      
   
      
    
        DescartadoLista Aux= new DescartadoLista(IdA,Data,Operador, motivos);
          ObservLista.add(Aux);
         
       System.out.println(""+IdA +" :Acervos  moivos "+motivos+"\n");
      // descartes.add(new DescartadoLista(IdA,motivos));   
   
   }    
   st.close();
   conexao.close();
   return      ObservLista;

      
    }
    
    

    
    public  void insereDescartesEremoveAcervo(int idAcervo,Date data,String idFuncionario,String motivo) throws SQLException{
         Connection con = ConnectionFactory.getBiblioteca();
      
      
      
        if(con  != null){
            String queryDelete = "DELETE FROM acervo WHERE id="+idAcervo;
            con.createStatement().executeUpdate(queryDelete);
            PreparedStatement prst = con.prepareStatement("INSERT INTO descartes(`id-acervo`,`data-descarte`, motivos, operador) VALUES (?,?,?,?)");
            
            prst.executeUpdate();
            prst.close();
            con.close();
            prst.setInt(1, idAcervo); //id-acervo
	    prst.setDate(2, data); //data-descarte
            prst.setString(3, motivo); // motivos
	    prst.setString(4, idFuncionario); //operador
            prst.executeUpdate();
            prst.close();
            con.close();
		
	String xml = RespostaXML.sucesso("Acervo " + idAcervo + " foi descartado!");
		//out.print(xml);
        }
        else{
            throw new SQLException();
        }
    }
  
    
  

}
