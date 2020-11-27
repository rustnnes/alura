package app.repositories;

import app.models.Lance;

public interface LanceRepository {

	void salvar(Lance lance);
	Lance porId(Long id);
}
