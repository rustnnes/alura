package br.com.caelum.pm73.curso;

import br.com.caelum.pm73.dao.CriadorDeSessao;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CriaTabelas {

	public static void main(String[] args) {

		Configuration cfg = CriadorDeSessao.getConfig();
		SchemaExport se = new SchemaExport(cfg);

		se.create(true, true);
	}

}
