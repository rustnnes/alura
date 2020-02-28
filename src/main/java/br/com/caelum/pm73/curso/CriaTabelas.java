package br.com.caelum.pm73.curso;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.caelum.pm73.dao.CriadorDeSessao;
import org.hibernate.tool.schema.TargetType;

import java.util.EnumSet;

public class CriaTabelas {

	public static void main(String[] args) {
		
		Configuration cfg = new CriadorDeSessao().getConfig();
		SchemaExport se = new SchemaExport();

		se.create( EnumSet.of( TargetType.DATABASE ), metadata() );
//		se.create(true, true);
	}
	
}
