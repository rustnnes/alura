package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.dao.LeilaoDao;
import br.com.caelum.leilao.infra.dao.RepositorioDeLeiloes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EncerradorDeLeilaoTest {

    private RepositorioDeLeiloes leilaoDao;
    private EnviadorDeEmail carteiro;
    private EncerradorDeLeilao encerradorDeLeilao;

    @BeforeEach
    public void setUp(){
        leilaoDao = mock(RepositorioDeLeiloes.class);
        carteiro = mock(EnviadorDeEmail.class);
        encerradorDeLeilao = new EncerradorDeLeilao(leilaoDao, carteiro);
    }

    @Test void deveEncerrarLeiloesIniciadosUmaSemanaAntes(){
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, Calendar.FEBRUARY ,20);
        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma").naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();

        when(leilaoDao.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
        encerradorDeLeilao.encerra();

        InOrder inOrder = inOrder(leilaoDao, carteiro);
        inOrder.verify(leilaoDao, times(1)).atualiza(leilao1);
        inOrder.verify(carteiro, times(1)).envia(leilao1);
        inOrder.verify(leilaoDao, times(1)).atualiza(leilao2);
        inOrder.verify(carteiro, times(1)).envia(leilao2);

        assertEquals(2, encerradorDeLeilao.getTotalEncerrados());
        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }

    @Test void naoDeveEncerrarLeiloesIniciadosOntem(){
        Calendar antiga = Calendar.getInstance();
        antiga.add(Calendar.DAY_OF_MONTH, -1);
        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma").naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();

        when(leilaoDao.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));

        encerradorDeLeilao.encerra();

        assertEquals(0, encerradorDeLeilao.getTotalEncerrados());
        assertFalse(leilao1.isEncerrado());
        assertFalse(leilao2.isEncerrado());

        verify(leilaoDao, never()).atualiza(leilao1);
        verify(leilaoDao, never()).atualiza(leilao2);
    }

    @Test void deveContinuarAExecucaoQuandoDaoFalha(){
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, Calendar.FEBRUARY ,20);
        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma").naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();

        when(leilaoDao.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
        doThrow(new RuntimeException()).when(leilaoDao).atualiza(leilao1);

        encerradorDeLeilao.encerra();

        verify(leilaoDao, times(1)).atualiza(leilao2);
        verify(carteiro, times(1)).envia(leilao2);

        assertEquals(2, encerradorDeLeilao.getTotalEncerrados());
//        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }

    @Test void deveContinuarAExecucaoQuandoEmailFalha(){
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, Calendar.FEBRUARY ,20);
        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma").naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();

        when(leilaoDao.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
        doThrow(new RuntimeException()).when(carteiro).envia(leilao1);

        encerradorDeLeilao.encerra();

        verify(leilaoDao, times(1)).atualiza(leilao2);
        verify(carteiro, times(1)).envia(leilao2);

        assertEquals(2, encerradorDeLeilao.getTotalEncerrados());
//        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }

    @Test void deveDesistirSeDaoFalhaPraSempre(){
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, Calendar.FEBRUARY ,20);
        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma").naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();

        when(leilaoDao.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
        doThrow(new RuntimeException()).when(leilaoDao).atualiza(any(Leilao.class));

        encerradorDeLeilao.encerra();

        verify(carteiro, never()).envia(any(Leilao.class));
    }

    @Test void naoDeveEncerrarLeiloesInexistentes(){
        when(leilaoDao.correntes()).thenReturn(Collections.emptyList());

        encerradorDeLeilao.encerra();

        assertEquals(0, encerradorDeLeilao.getTotalEncerrados());
    }

    @Test void testeDeMetodoEstatico(){
//        when(LeilaoDao.teste()).thenReturn("teste");
        assertEquals("teste", LeilaoDao.teste());
    }
}