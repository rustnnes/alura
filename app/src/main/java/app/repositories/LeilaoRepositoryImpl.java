package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Leilao;

@Component
public class LeilaoRepositoryImpl
    extends Repository<Leilao, Long>
    implements LeilaoRepository {

	LeilaoRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
