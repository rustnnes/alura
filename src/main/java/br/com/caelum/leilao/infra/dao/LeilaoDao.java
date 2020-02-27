package br.com.caelum.leilao.infra.dao;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LeilaoDao implements RepositorioDeLeiloes{

	private Connection conexao;

	public LeilaoDao() {
		try {
			this.conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost/mocks", "root", "eSOq1Dcpeh0h_GhfaZ");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Calendar data(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public void salva(Leilao leilao) {
		try {
			String sql = "insert into leilao (descricao, data, encerrado) values (?,?,?);";
			PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, leilao.getDescricao());
			ps.setDate(2, new java.sql.Date(leilao.getData().getTimeInMillis()));
			ps.setBoolean(3, leilao.isEncerrado());
			
			ps.execute();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            leilao.setId(generatedKeys.getInt(1));
	        }
			
			for(Lance lance : leilao.getLances()) {
				sql = "insert into lances (leilao_id, usuario_id, valor) values (?,?,?);";
				PreparedStatement ps2 = conexao.prepareStatement(sql);
				ps2.setInt(1, leilao.getId());
				ps2.setInt(2, lance.getUsuario().getId());
				ps2.setDouble(3, lance.getValor());
				
				ps2.execute();
				ps2.close();
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Leilao> encerrados() { return porEncerrado(true); }
	
	public List<Leilao> correntes() { return porEncerrado(false); }
	
	private List<Leilao> porEncerrado(boolean status) {
		try {
			String sql = "select * from leilao where encerrado = " + status + ";";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Leilao> leiloes = new ArrayList<Leilao>();
			while(rs.next()) {
				Leilao leilao = new Leilao(rs.getString("descricao"), data(rs.getDate("data")));
				leilao.setId(rs.getInt("id"));
				if(rs.getBoolean("encerrado")) leilao.encerra();
				
				String sql2 = "select valor, nome, u.id as usuario_id, l.id as lance_id from lances l " +
						"inner join usuario u on u.id = l.usuario_id where leilao_id = " + rs.getInt("id");
				PreparedStatement ps2 = conexao.prepareStatement(sql2);
				ResultSet rs2 = ps2.executeQuery();
				
				while(rs2.next()) {
					Usuario usuario = new Usuario(rs2.getInt("id"), rs2.getString("nome"));
					Lance lance = new Lance(usuario, rs2.getDouble("valor"));
					
					leilao.propoe(lance);
				}
				rs2.close();
				ps2.close();
				
				leiloes.add(leilao);
			}
			rs.close();
			ps.close();
			
			return leiloes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualiza(Leilao leilao) {
		try {
			String sql = "update leilao set descricao=?, data=?, encerrado=? where id = ?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, leilao.getDescricao());
			ps.setDate(2, new java.sql.Date(leilao.getData().getTimeInMillis()));
			ps.setBoolean(3, leilao.isEncerrado());
			ps.setInt(4, leilao.getId());

			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int x() { return 10; }
	public static String teste() { return "teste"; }

}
