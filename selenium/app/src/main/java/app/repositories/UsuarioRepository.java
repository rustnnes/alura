package app.repositories;

import java.util.List;

import app.models.Usuario;

public interface UsuarioRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Usuario entity);
	
	Usuario update(Usuario entity);
	
	void destroy(Usuario entity);
	
	Usuario find(Long id);
	
	List<Usuario> findAll();

}
