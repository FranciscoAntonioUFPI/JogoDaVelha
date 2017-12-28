package br.ufpi.pdsi2.jogodavelha.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufpi.pdsi2.jogodavelha.JogoDaVelha;

public class JogoDaVelhaTest {
	JogoDaVelha j;

	@Before
	public void setup() {
		j = new JogoDaVelha();
	}

	@After
	public void teardown() {
		j = null;
	}

	@Test
	public void testIniciarJogoCom2Jogadores() {
		j.inicia();
		assertEquals("deveria ter somente 2 jogadores!", 2, j.getJogadores()
				.size());
	}

	@Test
	public void testIniciarJogoComMenosDe2Jogadores() {
		j.inicia();
		assertFalse(" ", j.getJogadores().size() < 2);
	}

	@Test
	public void testIniciarJogoComMaisDe2Jogadores() {
		j.inicia();
		assertFalse(" ", j.getJogadores().size() > 2);
	}

	@Test
	public void testTabuleiroLimpo() {
		j.inicia();
		for (int lin = 0; lin < 3; lin++) {
			for (int col = 0; col < 3; col++) {
				assertTrue("deveria estar nulo",
						j.getTabuleiro()[lin][col].isEmpty());
			}
		}
	}

	@Test
	public void testIniciarJogoSemGanhador() {
		j.inicia();
		assertFalse("não deveria ter um ganhador!", j.temVencedor());
	}

	@Test
	public void testJogadaValidaVezJogador() {

		for (int numJogada = 0; numJogada < 9; numJogada++) {
			if (numJogada % 2 == 0)
				assertTrue("Não deveria ser o O!", j.vezJogador(numJogada, "X"));
			else
				assertTrue("Não deveria ser o X!", j.vezJogador(numJogada, "O"));
		}

	}

	@Test
	public void testJogadaInvalidaVezJogador() {

		for (int numJogada = 0; numJogada < 9; numJogada++) {
			if (numJogada % 2 == 0)
				assertFalse("Deveria ser o X!", j.vezJogador(numJogada, "O"));
			else
				assertFalse("Deveria ser o O !", j.vezJogador(numJogada, "X"));
		}

	}

	@Test
	public void testJogadaPosicaoValidaTabuleiroVazio() {
		j.inicia();
		for (int pos = 1; pos <= 9; pos++) {
			assertTrue("Deveria ser valida!", j.ehJogadaValida(pos));
		}

	}

	@Test
	public void testJogadaPosicaoInvalidaTabuleiroMarcado() {
		j.inicia();
		for (int pos = 1; pos <= 9; pos++) {
			assertTrue("Deveria ser valida!", j.ehJogadaValida(pos));
			j.marca(pos);
			assertFalse("Deveria ser invalida!", j.ehJogadaValida(pos));
		}
	}

	@Test
	public void testIncrementoJogada() {
		j.inicia();
		Random r = new Random();
		assertEquals("Deveria ser 0.", 0, j.getOrdemDeJogada());
		for (int numJogadas = 1; numJogadas <= 9; numJogadas++) {
			j.marca(r.nextInt(9) + 1);
			System.out.println("Teste incremento: "+j.getOrdemDeJogada());
			assertEquals("Deveria ser .", numJogadas, j.getOrdemDeJogada());
		}
	}

	@Test
	public void testNaoTemVencedor() {
		j.inicia();
		Random r = new Random();
		for (int numJogadas = 1; numJogadas <= 4; numJogadas++) {
			j.marca(numJogadas);
		}
		assertFalse("Nao deveria ter vencedor!", j.temVencedor());
	}

	@Test
	public void testTemVencedorColuna1() {
		j.inicia();
		j.marca(1);
		j.marca(2);
		j.marca(4);
		j.marca(5);
		j.marca(7);
		assertTrue("deveria ter vencedor!", j.temVencedor());
	}
	
	@Test
	public void testTemVencedorColuna2() {
		j.inicia();
		j.marca(2);
		j.marca(1);
		j.marca(5);
		j.marca(3);
		j.marca(8);
		assertTrue("deveria ter vencedor!", j.temVencedor());
	}
	
	@Test
	public void testTemVencedorColuna3() {
		j.inicia();
		j.marca(3);
		j.marca(2);
		j.marca(6);
		j.marca(5);
		j.marca(9);
		assertTrue("deveria ter vencedor!", j.temVencedor());
	}
	
	@Test
	public void testTemVencedorLinha1() {
		j.inicia();
		j.marca(1);
		j.marca(4);
		j.marca(2);
		j.marca(5);
		j.marca(3);
		assertTrue("deveria ter vencedor!", j.temVencedor());
	}
	
	@Test
	public void testTemVencedorLinha2() {
		j.inicia();
		j.marca(4);
		j.marca(1);
		j.marca(5);
		j.marca(3);
		j.marca(6);
		assertTrue("deveria ter vencedor!", j.temVencedor());
	}
	
	@Test
	public void testTemVencedorLinha3() {
		j.inicia();
		j.marca(7);
		j.marca(2);
		j.marca(8);
		j.marca(5);
		j.marca(9);
		assertTrue("deveria ter vencedor!", j.temVencedor());
	}
	
	@Test
	public void testTemVencedorDiagonal1() {
		j.inicia();
		j.marca(1);
		j.marca(2);
		j.marca(5);
		j.marca(6);
		j.marca(9);
		assertTrue("deveria ter vencedor!", j.temVencedor());
	}

	@Test
	public void testTemVencedorDiagonal2() {
		j.inicia();
		j.marca(3);
		j.marca(2);
		j.marca(5);
		j.marca(8);
		j.marca(7);
		assertTrue("deveria ter vencedor!", j.temVencedor());
	}
	

	@Test
	public void testEmpate() {
		j.inicia();
		j.marca(1);
		j.marca(2);
		j.marca(3);
		j.marca(9);
		j.marca(6);
		j.marca(5);
		j.marca(8);
		j.marca(7);
		j.marca(4);
		assertTrue("deveria ter vencedor!", j.empate());
	}
	

}
