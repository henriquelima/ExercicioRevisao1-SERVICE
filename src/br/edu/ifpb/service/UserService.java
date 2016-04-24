package br.edu.ifpb.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifpb.dao.UsuarioDAO;
import br.edu.ifpb.entidade.Usuario;

@Path("service")
public class UserService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Usuario login(Usuario usuario) {
		return usuario;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")	
	public void cadastrar(Usuario usuario){	
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.adiciona(usuario);		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar")
	public List<Usuario> listar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();		
		return usuarioDAO.consulta();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/remover")
	public void remover(Usuario usuario){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.deletar(usuario);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/atualizar")
	public Usuario atualizar(Usuario usuario){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.atualiza(usuario);
		return usuario;
	}
	


}
