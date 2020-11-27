package app.controllers;

import java.util.List;

import app.models.Usuario;
import app.repositories.UsuarioRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import javax.persistence.EntityManager;

@Resource
public class TesteController {
	
	private EntityManager em;
	
	public TesteController(EntityManager em) {
		this.em = em;
	} 
	
	@Get("/apenas-teste/limpa")
	public void index() {
		em.createNativeQuery("delete from lance").executeUpdate();
		em.createNativeQuery("delete from leilao").executeUpdate();
		em.createNativeQuery("delete from usuario").executeUpdate();
	}
}