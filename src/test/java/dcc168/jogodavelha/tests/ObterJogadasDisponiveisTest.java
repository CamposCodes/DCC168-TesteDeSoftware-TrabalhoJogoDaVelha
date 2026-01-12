package dcc168.jogodavelha.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dcc168.jogodavelha.model.Tabuleiro;
import dcc168.jogodavelha.strategy.EstrategiaJogador;
import dcc168.jogodavelha.strategy.IAAleatoria;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class ObterJogadasDisponiveisTest {
	@Test
	public void deveRetornarTodasPosicoes() {
		Tabuleiro tabuleiro = new Tabuleiro();

		List<int[]> resultado = EstrategiaJogador.obterJogadasDisponiveis(tabuleiro);

		assertEquals(9, resultado.size());
	}

	@Test
	public void deveRetornarListaVazia() {
		Tabuleiro tabuleiro = new Tabuleiro();

		for (int i = 0; i < tabuleiro.obterTamanho(); i++) {
			for (int j = 0; j < tabuleiro.obterTamanho(); j++) {
				tabuleiro.marcarPosicao(i, j, 'X');
			}
		}

		List<int[]> resultado = EstrategiaJogador.obterJogadasDisponiveis(tabuleiro);

		assertTrue(resultado.isEmpty());
	}

	@Test
	public void deveRetornarTabuleiroParcial() {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.marcarPosicao(0, 0, 'X');
		tabuleiro.marcarPosicao(1, 1, 'O');

		List<int[]> resultado = EstrategiaJogador.obterJogadasDisponiveis(tabuleiro);

		assertFalse(resultado.isEmpty());
		assertTrue(resultado.size() < 9);
	}

	@Test(expected = NullPointerException.class)
	public void deveLevantarExcecaoTabuleiroNulo() {
		EstrategiaJogador.obterJogadasDisponiveis(null);
	}

	@Test
	public void deveRetornarUnicaCasa() {
		Tabuleiro tabuleiro = new Tabuleiro();

		for (int i = 0; i < tabuleiro.obterTamanho(); i++) {
			for (int j = 0; j < tabuleiro.obterTamanho(); j++) {
				if (!(i == 2 && j == 1)) {
					tabuleiro.marcarPosicao(i, j, 'X');
				}
			}
		}

		List<int[]> resultado = EstrategiaJogador.obterJogadasDisponiveis(tabuleiro);

		assertEquals(1, resultado.size());
		assertArrayEquals(new int[] { 2, 1 }, resultado.get(0));
	}
}
