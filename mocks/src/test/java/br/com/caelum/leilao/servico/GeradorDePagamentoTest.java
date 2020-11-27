package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.infra.dao.Relogio;
import br.com.caelum.leilao.infra.dao.RepositorioDeLeiloes;
import br.com.caelum.leilao.infra.dao.RepositorioDePagamentos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GeradorDePagamentoTest {
    private RepositorioDeLeiloes leiloes;
    private RepositorioDePagamentos pagamentos;
    private Avaliador avaliador;
    private Relogio relogio;

    @BeforeEach
    public void setUp() {
        leiloes = mock(RepositorioDeLeiloes.class);
        pagamentos = mock(RepositorioDePagamentos.class);
        avaliador = mock(Avaliador.class);
        relogio = mock(Relogio.class);
    }

    @Test
    public void deveGerarPagamentoParaUmLeilaoEncerrado() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation")
                .lance(new Usuario("José da Silva"), 2000.0)
                .lance(new Usuario("Maria Pereira"), 2500.0)
                .constroi();

        when(leiloes.encerrados()).thenReturn(Collections.singletonList(leilao));
        when(avaliador.getMaiorLance()).thenReturn(2500.0);

        GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, new Avaliador());
        gerador.gera();

        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());
        Pagamento pagamentoGerado = argumento.getValue();
        assertEquals(2500.0, pagamentoGerado.getValor(), 0.00001);
    }

    @Test public void deveEmpurrarParaOProximoDiaUtilQuandoSabado() {
        Calendar sabado = Calendar.getInstance();
        sabado.set(2012, Calendar.APRIL, 7);

        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation")
                .lance(new Usuario("José da Silva"), 2000.0)
                .lance(new Usuario("Maria Pereira"), 2500.0)
                .constroi();

        when(leiloes.encerrados()).thenReturn(Collections.singletonList(leilao));
        when(relogio.hoje()).thenReturn(sabado);

        GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, new Avaliador(), relogio);
        gerador.gera();

        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());
        Pagamento pagamentoGerado = argumento.getValue();

        assertEquals(Calendar.MONDAY, pagamentoGerado.getData().get(Calendar.DAY_OF_WEEK));
        assertEquals(9, pagamentoGerado.getData().get(Calendar.DAY_OF_MONTH));
    }

    @Test public void deveEmpurrarParaOProximoDiaUtilQuandoDomingo() {
        Calendar domingo = Calendar.getInstance();
        domingo.set(2013, Calendar.SEPTEMBER, 8);

        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation")
                .lance(new Usuario("José da Silva"), 2000.0)
                .lance(new Usuario("Maria Pereira"), 2500.0)
                .constroi();

        when(leiloes.encerrados()).thenReturn(Collections.singletonList(leilao));
        when(relogio.hoje()).thenReturn(domingo);

        GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, new Avaliador(), relogio);
        gerador.gera();

        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());
        Pagamento pagamentoGerado = argumento.getValue();

        assertEquals(Calendar.MONDAY, pagamentoGerado.getData().get(Calendar.DAY_OF_WEEK));
        assertEquals(9, pagamentoGerado.getData().get(Calendar.DAY_OF_MONTH));
    }
}