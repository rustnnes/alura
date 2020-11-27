package br.com.caelum.pm73;

import br.com.caelum.pm73.dao.CriadorDeSessao;
import br.com.caelum.pm73.dao.UsuarioDao;
import br.com.caelum.pm73.dominio.Usuario;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UsuarioDaoTest {
    private Session session;
    private UsuarioDao usuarioDao;

    @BeforeEach
    public void hi(){
        session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);

        session.beginTransaction();
    }

    @AfterEach public void bye(){
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    public void deveEncontrarPeloNomeEEmailMockado(){
        usuarioDao.salvar(new Usuario("Joao da Silva", "joao@dasilva.com.br"));

        Usuario usuario = new Usuario("Joao da Silva", "joao@dasilva.com.br");
        Usuario usuariodoBanco = usuarioDao.porNomeEEmail("Joao da Silva", "joao@dasilva.com.br");

        assertEquals(usuario.getNome(), usuariodoBanco.getNome());
        assertEquals(usuario.getEmail(), usuariodoBanco.getEmail());
    }

    @Test
    public void deveRetornarObjetoNuloDoBanco(){
        Usuario usuariodoBanco = usuarioDao.porNomeEEmail("Joao da Silva", "joao@dasilva.com.br");
        assertNull(usuariodoBanco);
    }
}