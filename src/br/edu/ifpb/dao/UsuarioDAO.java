package br.edu.ifpb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import br.edu.ifpb.conexao.ConnectionFactory;
import br.edu.ifpb.entidade.Usuario;

public class UsuarioDAO {
private Connection con;
	
	public UsuarioDAO(){
		this.con = new ConnectionFactory().getConnection();			
	}
	public void adiciona(Usuario usuario){
		String sql = "insert into usuarios" + "(nome,email,senha)" + " values (?,?,?)";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1,usuario.getNome());
			stmt.setString(2,usuario.getEmail());
			stmt.setString(3,usuario.getSenha());
	
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		} 
	}
	
	public List<Usuario> consulta(){
		
		String sql = "select * from usuarios";
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
				
		try {		
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();			
			
			while (rs.next()){
				
				Usuario usuario = new Usuario();
				
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setId(rs.getInt("id"));	
				
				usuarios.add(usuario);
			
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		
		return usuarios;
	}
	
	public void atualiza(Usuario usuario){
		String sql = "update usuarios set nome=?, email=?, senha=?" +
             " where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setInt(4, usuario.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public Usuario consultaByEmail(String email){
		String sql = "select * from usuarios where email = ?";
		Usuario usuario = new Usuario();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
									
			if(rs.next()){
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setId(rs.getInt("id"));
			} else {
				usuario = null;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return usuario;		
		
	}
	
	public void deletar(Usuario usuario){
		try {

	         PreparedStatement stmt = con.prepareStatement("delete" +

	                 "from contatos where id=?");

	         stmt.setLong(1, usuario.getId());

	         stmt.execute();

	         stmt.close();

	     } catch (SQLException e) {

	         throw new RuntimeException(e);

	     }
	}

}
