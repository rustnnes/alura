package app.controllers;

import java.util.List;

import app.models.Leilao;
import app.repositories.LeilaoRepository;
import app.repositories.UsuarioRepository;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.Validator;

import java.util.Calendar;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class LeilaoController {

	private final Result result;
	private final LeilaoRepository leiloes;
	private final Validator validator;
	private final UsuarioRepository usuarios;
	
	LeilaoController(Result result, LeilaoRepository leiloes, UsuarioRepository usuarios, Validator validator) {
		this.result = result;
		this.leiloes = leiloes;
		this.usuarios = usuarios;
		this.validator = validator;
	}
	
	@Get("/leiloes")
	public List<Leilao> index() {
		return leiloes.findAll();
	}
	
	@Post("/leiloes")
	public void create(final Leilao leilao) {

		if (leilao.getNome().isEmpty()) {
		    validator.add(new ValidationMessage("Nome obrigatorio!", "error"));
		}
		if (leilao.getValorInicial() == null || leilao.getValorInicial() <= 0) {
		    validator.add(new ValidationMessage("Valor inicial deve ser maior que zero!", "error"));
		}

		validator.onErrorUsePageOf(this).newLeilao();
		
		leilao.setDataAbertura(Calendar.getInstance());
		leiloes.create(leilao);
		result.redirectTo(this).index();
	}
	
	@Get("/leiloes/new")
	public Leilao newLeilao() {
		result.include("usuarios", usuarios.findAll());
		return new Leilao();
	}
	
	@Get("/leiloes/{leilao.id}")
	public Leilao show(Leilao leilao) {
		result.include("usuarios", usuarios.findAll());
		return leiloes.find(leilao.getId());
	}

}