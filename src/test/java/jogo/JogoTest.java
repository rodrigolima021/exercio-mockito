package jogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JogoTest {

    @Mock
    private Jogador jogador;

    @Mock
    private Dado dado1;

    @Mock
    private Dado dado2;

    private Jogo jogo;

    @BeforeEach
    void setUp() {
        jogo = new Jogo(jogador, dado1, dado2);
    }

    @Test
    void deveGanharQuandoPrimeiroLancamentoFor7() {

        when(jogador.lancar(dado1, dado2))
            .thenReturn(7);

        boolean resultado = jogo.jogo();

        assertTrue(resultado);

        verify(jogador, times(1))
            .lancar(dado1, dado2);
    }

    @Test
    void deveGanharQuandoPrimeiroLancamentoFor11() {

        when(jogador.lancar(dado1, dado2))
            .thenReturn(11);

        assertTrue(jogo.jogo());

        verify(jogador, times(1))
            .lancar(dado1, dado2);
    }

    @Test
    void devePerderQuandoPrimeiroLancamentoFor2() {

        when(jogador.lancar(dado1, dado2))
            .thenReturn(2);

        assertFalse(jogo.jogo());

        verify(jogador, times(1))
            .lancar(dado1, dado2);
    }

    @Test
    void devePerderQuandoPrimeiroLancamentoFor3() {

        when(jogador.lancar(dado1, dado2))
            .thenReturn(3);

        assertFalse(jogo.jogo());
    }

    @Test
    void devePerderQuandoPrimeiroLancamentoFor12() {

        when(jogador.lancar(dado1, dado2))
            .thenReturn(12);

        assertFalse(jogo.jogo());
    }

    @Test
    void deveGanharQuandoRepetirOPonto() {

        /*
         * Primeiro lançamento = 5
         * O ponto passa a ser 5
         *
         * Segundo lançamento = 8
         * Continua jogando
         *
         * Terceiro lançamento = 5
         * Repetiu o ponto -> ganhou
         */

        when(jogador.lancar(dado1, dado2))
            .thenReturn(5, 8, 5);

        assertTrue(jogo.jogo());

        verify(jogador, times(3))
            .lancar(dado1, dado2);
    }

    @Test
    void devePerderQuandoSair7DepoisDeDefinirOPonto() {

        /*
         * Primeiro lançamento = 5
         * Ponto = 5
         *
         * Segundo lançamento = 7
         * Perde
         */

        when(jogador.lancar(dado1, dado2))
            .thenReturn(5, 7);

        assertFalse(jogo.jogo());

        verify(jogador, times(2))
            .lancar(dado1, dado2);
    }
}
