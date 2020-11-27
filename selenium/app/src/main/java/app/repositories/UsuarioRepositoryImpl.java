package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Usuario;

@Component
public class UsuarioRepositoryImpl
    extends Repository<Usuario, Long>
    implements UsuarioRepository {

	UsuarioRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
