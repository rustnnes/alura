package br.com.caelum.pm73;

import br.com.caelum.pm73.dao.CriadorDeSessao;
import br.com.caelum.pm73.dao.LeilaoDao;
import br.com.caelum.pm73.dao.UsuarioDao;
import br.com.caelum.pm73.dominio.Lance;
import br.com.caelum.pm73.dominio.Leilao;
import br.com.caelum.pm73.dominio.Usuario;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeilaoDaoTest {
    private Session session;
    private UsuarioDao usuarioDao;
    private LeilaoDao leilaoDao;

    @BeforeEach
    public void hi(){
        session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);
        leilaoDao = new LeilaoDao(session);

        session.beginTransaction();
    }

    @AfterEach
    public void bye(){
        session.getTransaction().rollback();
        session.close();
    }

    @Test public void deveContarLeiloesEncerrados(){
        Usuario mauricio = new Usuario("Mauricio", "mauricio@mauricio.com.br");

        Leilao ativo = new LeilaoBuilder().comNome("Geladeira").comValor(1500.0).comDono(mauricio).constroi();
        Leilao encerrado = new LeilaoBuilder().comNome("Xbox").comValor(3000.0).comDono(mauricio).constroi();
        encerrado.encerra();

        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(ativo);
        leilaoDao.salvar(encerrado);

        Long total = leilaoDao.total();

        assertEquals(1L, total);

    }

    @Test public void deveRetornarZeroLeiloesAtivos(){
        Usuario mauricio = new Usuario("Mauricio", "mauricio@mauricio.com.br");

        Leilao encerrado1 = new LeilaoBuilder().comNome("Geladeira").comValor(1500.0).comDono(mauricio).constroi();
        Leilao encerrado2 = new LeilaoBuilder().comNome("Xbox").comValor(3000.0).comDono(mauricio).constroi();
        encerrado1.encerra();
        encerrado2.encerra();

        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(encerrado1);
        leilaoDao.salvar(encerrado2);

        Long total = leilaoDao.total();

        assertEquals(0L, total);
    }

    @Test public void deveRetornarUmLeilaoDeItemUsado(){
        Usuario mauricio = new Usuario("Mauricio", "mauricio@mauricio.com.br");

        Leilao leilao1 = new LeilaoBuilder().comNome("Geladeira").comValor(1500.0).comDono(mauricio).constroi();
        Leilao leilao2 = new LeilaoBuilder().comNome("Xbox").comValor(3000.0).comDono(mauricio).usado().constroi();

        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);

        List<Leilao> novos = leilaoDao.novos();

        assertEquals(1, novos.size());
        assertEquals("Geladeira", novos.get(0).getNome());
    }

    @Test public void deveRetornarApenasLeiloesComMaisdeSeteDias(){
        Usuario usuario = new Usuario("Jose da Silva", "jose@dasilva.com.br");
        Leilao leilao1 = new LeilaoBuilder().comNome("Geladeira").comValor(1500.0).comDono(usuario).diasAtras(3).constroi();
        Leilao leilao2 = new LeilaoBuilder().comNome("Xbox").comValor(3000.0).comDono(usuario).diasAtras(7).usado().constroi();
        Leilao leilao3 = new LeilaoBuilder().comNome("Nintendo Wii").comValor(2500.0).comDono(usuario).diasAtras(14).usado().constroi();

        usuarioDao.salvar(usuario);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);

        List<Leilao> antigos = leilaoDao.antigos();

        assertEquals(2, antigos.size());
        assertEquals("Xbox", antigos.get(0).getNome());
        assertEquals("Nintendo Wii", antigos.get(1).getNome());
    }

    @Test public void deveRetornarApenasLeiloesComExatosSeteDias(){
        Usuario usuario = new Usuario("Jose da Silva", "jose@dasilva.com.br");

        Leilao leilao1 = new LeilaoBuilder().comNome("Geladeira").comValor(1500.0).comDono(usuario).diasAtras(3).constroi();
        Leilao leilao2 = new LeilaoBuilder().comNome("Xbox").comValor(3000.0).comDono(usuario).diasAtras(7).usado().constroi();

        usuarioDao.salvar(usuario);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);

        List<Leilao> antigos = leilaoDao.antigos();

        assertEquals(1, antigos.size());
        assertEquals("Xbox", antigos.get(0).getNome());
    }

    @Test public void deveTrazerLeiloesNaoEncerradosNoPeriodo(){
        TimeZone tz = TimeZone.getTimeZone(ZoneId.of("GMT-3"));

        Calendar begin = Calendar.getInstance(tz);
        Calendar end = Calendar.getInstance(tz);
        begin.add(Calendar.DAY_OF_MONTH,-10);

        Usuario usuario = new Usuario("Jose da Silva", "jose@dasilva.com.br");

        Leilao leilao1 = new LeilaoBuilder().comNome("Geladeira").comValor(1500.0).comDono(usuario).diasAtras(2).constroi();
        Leilao leilao2 = new LeilaoBuilder().comNome("Xbox").comValor(3000.0).comDono(usuario).diasAtras(20).usado().constroi();

        usuarioDao.salvar(usuario);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);

        List<Leilao> leiloes = leilaoDao.porPeriodo(begin, end);

        assertEquals(1, leiloes.size());
        assertEquals("Geladeira", leiloes.get(0).getNome());
    }

    @Test public void naoDeveTrazerLeiloesEncerradosNoPeriodo(){
        TimeZone tz = TimeZone.getTimeZone(ZoneId.of("GMT-3"));

        Calendar begin = Calendar.getInstance(tz);
        Calendar end = Calendar.getInstance(tz);
        begin.add(Calendar.DAY_OF_MONTH,-10);

        Usuario usuario = new Usuario("Jose da Silva", "jose@dasilva.com.br");

        Leilao leilao1 = new LeilaoBuilder().comNome("Geladeira").comValor(1500.0).comDono(usuario).diasAtras(2).constroi();
        Leilao leilao2 = new LeilaoBuilder().comNome("Xbox").comValor(3000.0).comDono(usuario).diasAtras(7).usado().encerrado().constroi();

        usuarioDao.salvar(usuario);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);

        List<Leilao> leiloes = leilaoDao.porPeriodo(begin, end);

        assertEquals(1, leiloes.size());
        assertEquals("Geladeira", leiloes.get(0).getNome());
    }

    @Test public void deveRetornarLeiloesDisputadosNoPeriodo(){
        Usuario dono = new Usuario("Jose da Silva", "jose@dasilva.com.br");
        Usuario comprador1 = new Usuario("Joao", "joao@joao.com.br");
        Usuario comprador2 = new Usuario("Maria", "maria@maria.com.br");

        Leilao leilao1 = new LeilaoBuilder().comNome("Geladeira").comValor(1500.0).comDono(dono).diasAtras(2)
                .comLance(new Lance(Calendar.getInstance(),comprador1,2000.0)).encerrado().constroi();

        Leilao leilao2 = new LeilaoBuilder().comNome("Nintendo Wii").comValor(2500.0).comDono(dono).diasAtras(6)
                .comLance(new Lance(Calendar.getInstance(),comprador1,3000.0))
                .comLance(new Lance(Calendar.getInstance(),comprador2,3500.0))
                .comLance(new Lance(Calendar.getInstance(),comprador1,4000.0))
                .constroi();

        Leilao leilao3 = new LeilaoBuilder().comNome("Xbox").comValor(3000.0).comDono(dono).diasAtras(12)
                .comLance(new Lance(Calendar.getInstance(),comprador1,4000.0)).usado().constroi();

        usuarioDao.salvar(dono);
        usuarioDao.salvar(comprador1);
        usuarioDao.salvar(comprador2);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);
        leilaoDao.salvar(leilao3);

        List<Leilao> leiloes = leilaoDao.disputadosEntre(1000.0, 3000.0);

        assertEquals(1, leiloes.size());
        assertEquals("Nintendo Wii", leiloes.get(0).getNome());
    }
}