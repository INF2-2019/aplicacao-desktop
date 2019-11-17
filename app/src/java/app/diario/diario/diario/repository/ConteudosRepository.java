/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.diario.diario.repository;

import app.diario.diario.model.AdicionarConteudosModel;
import app.diario.diario.model.AtividadeModel;
import app.diario.diario.model.ConteudosModel;
import app.diario.diario.model.DiarioModel;
import app.diario.diario.model.EtapaModel;
import app.diario.diario.views.ExcecaoPadrao;
import app.utils.ConnectionFactory;



import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;

/**
 * @author juanr
 */
public class ConteudosRepository {


	public static List<ConteudosModel> consulta() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            List<ConteudosModel> Conteudos;
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM `conteudos`WHERE valor=0");
                Conteudos = new LinkedList();
                while (rs.next()) {
                    ConteudosModel Cont = new ConteudosModel( rs.getString("id-etapas"), rs.getString("conteudos"), rs.getString("data"));
                    Cont.setId(rs.getInt("id"));
                    Cont.setIdEtapa(rs.getString("id-etapas"));
  
                    System.out.println("\nEtapas"+Cont.getIdEtapa());
                    Conteudos.add(Cont);
                    //System.out.println("\nEtapas"+rs.getString("idEtapas"));
                }
            }
            con.close();
            return Conteudos;
        } else {
            throw new SQLException();
        }
    }
        
    /**
     *
     * @return
     * @throws SQLException
     */
    public static List<DiarioModel> consultaDiario() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            List<DiarioModel> Atividades;
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM `diario`");
                Atividades = new LinkedList();
                while (rs.next()) {
                    DiarioModel Diario = new  DiarioModel();
                  //  System.out.println("\nEtapas"+Cont.getIdEtapa());
                   Atividades.add(Diario);
                    //System.out.println("\nEtapas"+rs.getString("idEtapas"));
                }
            }
            con.close();
            return Atividades;
        } else {
            throw new SQLException();
        }
    }    
     public static List<AtividadeModel> consultaAtividade() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            List<AtividadeModel> Atividades;
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM `conteudos` WHERE valor!=0");
                Atividades = new LinkedList();
                while (rs.next()) {
                    AtividadeModel Atv = new  AtividadeModel(rs.getString("id-etapas"), rs.getString("conteudos"), rs.getString("data"),rs.getDouble("valor"));
                  
                   Atividades.add(Atv);
                    System.out.println("\nEtapas"+Atv.getValor());
                }
            }
            con.close();
            return Atividades;
        } else {
            throw new SQLException();
        }
    }    
        
         public static void atualizaE(int id, String Etapa) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            try (PreparedStatement prst = con.prepareStatement("UPDATE `conteudos` SET `id-etapas`= ? WHERE `id` = ?")) {
                prst.setString(1, Etapa);
                prst.setInt(2, id);
                prst.executeUpdate();
            }
            con.close();
        } else {
            throw new SQLException();
        }
    }

    public static void atualizaC(int id, String Conteudo) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            try (PreparedStatement prst = con.prepareStatement("UPDATE `conteudos` SET `conteudos`= ? WHERE `id` = ?")) {
                prst.setString(1, Conteudo);
                prst.setInt(2, id);
                prst.executeUpdate();
            }
            con.close();
        } else {
            throw new SQLException();
        }
    }

    public static void atualiza(int id, String Etapa, String Conteudo) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            try (PreparedStatement prst = con.prepareStatement("UPDATE `conteudos` SET `id-etapas` = ?, `conteudos` = ? WHERE `id` = ?")) {
                prst.setString(1, Etapa);
                prst.setString(2, Conteudo);
                prst.setInt(3, id);
                prst.executeUpdate();
            }
            con.close();
        } else {
            throw new SQLException();
        }
    }

    /**
     *
     * @param idEtapas
     * @param Conteudo
     * @param date
     * @throws SQLException
     */
    public static void insereConteudo(int idEtapas,String Conteudo,String date) throws SQLException   {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            try (PreparedStatement prst = con.prepareStatement("INSERT INTO `conteudos` (`id-etapas`, `conteudos`,`data´) VALUES (?, ?,?)")) {
                prst.setInt(1, idEtapas);
                prst.setString(2, Conteudo);
                prst.setString(3, date);
                //prst.setDate(date, null);
                prst.executeUpdate();
            }
            con.close();
        } else {
            throw new SQLException();
        }
    }
     public static List<EtapaModel> consultaEtapa() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        if (con != null) {
            List<EtapaModel> Etapalinked;
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM `etapas`");
                Etapalinked = new LinkedList();
                while (rs.next()) {
                    //String ide = String.valueOf(rs.getInt("id")),Ano =String.valueOf(rs.getInt("ano")),Value=String.valueOf(rs.getDouble("valor"));
                    //System.out.println("Ano"+Value);
                    String ide = rs.getString("id");
                    //String ide = String.valueOf(rs.getInt("id")),Ano =String.valueOf(rs.getInt("ano")),Value=String.valueOf(rs.getDouble("valor"));
                    //System.out.println("Ano"+Value);
                    String Ano = rs.getString("ano");
                    String Value =rs.getString("valor");
                    
                    EtapaModel e = new EtapaModel(ide,Ano,Value);
                    Etapalinked.add(e);
                    System.out.println("Ano"+e.getAno());
                    
                }
            }
            con.close();
            return Etapalinked;
        } else {
            throw new SQLException();
        }
    }
   public static boolean insere(AdicionarConteudosModel modelo) throws SQLException {
	Connection con =ConnectionFactory.getConnection();
         String query = "INSERT INTO conteudos(id,id-disciplinas, conteudos , data, valor) VALUES ("
                 + "?,?,?,?,?)";
         int r;
            try (PreparedStatement st = con.prepareStatement(query)) {
                st.setInt(1, 1);
                st.setInt(2, 9);
                st.setInt(3, 2);
                st.setString(4, modelo.getConteudo());
                st.setDate(5, modelo.getData());
                if (modelo.getValor() != null) {
                    st.setDouble(5, modelo.getValor());
                } else {
                    st.setDouble(6, 0.0);
                }
                r = st.executeUpdate();
            }
		return r != 0;
	}
   public static boolean remover(int id) throws SQLException, ExcecaoPadrao {
		Connection conexao = ConnectionFactory.getConnection();
                String query;
		PreparedStatement st;

		if (id !=0 ) {
			query = "DELETE FROM conteudos WHERE id=?";
			st = conexao.prepareStatement(query);
			st.setInt(1, id);

			int r = st.executeUpdate();
			if (r != 0) {
				DiarioRepository repositorioDiario = new DiarioRepository(conexao);
				DiarioModel filtroDiario = new DiarioModel();
				filtroDiario.setIdConteudo(id);
				repositorioDiario.remover(filtroDiario);
			}
			return r != 0;
		} else {
			query = "SELECT id FROM conteudos";
			ArrayList<ConteudosModel> resultado = (ArrayList<ConteudosModel>) consulta();

			int pos = 1;
			for (ConteudosModel modelo : resultado) {
				if (pos > 1) {
					query += " OR";
				}
				query += " id=?";
				pos++;
			}

			st = conexao.prepareStatement(query);

			pos = 1;
			for (ConteudosModel modelo : resultado) {
				st.setInt(pos, modelo.getId());
				pos++;
			}

			int r = st.executeUpdate();
			if (r != 0) {
				DiarioRepository repositorioDiario = new DiarioRepository(conexao);
				DiarioModel filtroDiario = new DiarioModel();

				for (ConteudosModel modelo : resultado) {
					filtroDiario.setIdConteudo(modelo.getId());
					repositorioDiario.remover(filtroDiario);
				}
			}

			return r != 0;
		}

	}

     
     
    
  
}
