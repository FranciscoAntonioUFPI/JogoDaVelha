package br.ufpi.pdsi2.jogodavelha;

import java.util.ArrayList;
import java.util.List;

public class JogoDaVelha {

	private List jogadores;
	private String[][] tabuleiro;
	private int numJogadas;

	public void inicia() {
		this.numJogadas = 0;
		criarJogadores();
		iniciaTabuleiro();

	}

	private void iniciaTabuleiro() {
		this.tabuleiro = new String[3][3];
		for (int lin = 0; lin < 3; lin++) {
			for (int col = 0; col < 3; col++) {
				this.tabuleiro[lin][col] = "";
			}
		}
	}

	private void criarJogadores() {
		jogadores = new ArrayList<Jogador>();
		jogadores.add(new Jogador("X"));
		jogadores.add(new Jogador("O"));
	}

	public List getJogadores() {
		return this.jogadores;
	}

	public String[][] getTabuleiro() {
		return this.tabuleiro;
	}

	public boolean temVencedor() {
		if (this.numJogadas < 5)
			return false;
		if (
		// linhas
		verificaLinha(1) || verificaLinha(2) || verificaLinha(3) ||
		// colunas
				verificaColuna(1) || verificaColuna(2) || verificaColuna(3) ||
				// diagonais
				verificaDiagonais())
			return true;
		return false;
	}

	private boolean verificaDiagonais() {
		if (tabuleiro[0][0].equals(tabuleiro[1][1])
				&& tabuleiro[0][0].equals(tabuleiro[2][2]))
			return true;
		if (tabuleiro[0][2].equals(tabuleiro[1][1])
				&& tabuleiro[0][2].equals(tabuleiro[2][0]))
			return true;
		return false;
	}

	private boolean verificaColuna(int i) {
		boolean resultado = true;
		for (int lin = 0; lin < 2; lin++) {
			if (!tabuleiro[lin][i - 1].equals(tabuleiro[lin + 1][i - 1]))
				resultado = false;
		}
		return resultado;
	}

	private boolean verificaLinha(int i) {
		boolean resultado = true;
		for (int col = 0; col < 2; col++) {
			if (!tabuleiro[i - 1][col].equals(tabuleiro[i - 1][col + 1]))
				resultado = false;
		}
		return resultado;
	}

	public boolean vezJogador(int i, String cod) {
		if (i % 2 == 0 && cod.equals("X"))
			return true;
		else if (i % 2 != 0 && cod.equals("O"))
			return true;
		return false;
	}

	public boolean ehJogadaValida(int pos) {
		switch (pos) {
		case 1:
			if (this.tabuleiro[0][0].isEmpty())
				return true;
			break;
		case 2:
			if (this.tabuleiro[0][1].isEmpty())
				return true;
			break;
		case 3:
			if (this.tabuleiro[0][2].isEmpty())
				return true;
			break;
		case 4:
			if (this.tabuleiro[1][0].isEmpty())
				return true;
			break;
		case 5:
			if (this.tabuleiro[1][1].isEmpty())
				return true;
			break;
		case 6:
			if (this.tabuleiro[1][2].isEmpty())
				return true;
			break;
		case 7:
			if (this.tabuleiro[2][0].isEmpty())
				return true;
			break;
		case 8:
			if (this.tabuleiro[2][1].isEmpty())
				return true;
			break;
		case 9:
			if (this.tabuleiro[2][2].isEmpty())
				return true;
			break;

		default:
			return false;
		}
		return false;
	}

	public void marca(int pos) {
		String jogada = numJogadas % 2 == 0 ? "X" : "O";

		switch (pos) {
		case 1:
			this.tabuleiro[0][0] = jogada;
			break;
		case 2:
			this.tabuleiro[0][1] = jogada;
			break;
		case 3:
			this.tabuleiro[0][2] = jogada;
			break;
		case 4:
			this.tabuleiro[1][0] = jogada;
			break;
		case 5:
			this.tabuleiro[1][1] = jogada;
			break;
		case 6:
			this.tabuleiro[1][2] = jogada;
			break;
		case 7:
			this.tabuleiro[2][0] = jogada;
			break;
		case 8:
			this.tabuleiro[2][1] = jogada;
			break;
		case 9:
			this.tabuleiro[2][2] = jogada;
			break;
		}
		imprimeTabuleiro();
	}

	private void imprimeTabuleiro() {

		this.numJogadas++;
	}

	public int getOrdemDeJogada() {
		return this.numJogadas;
	}

	public boolean empate() {
		return !temVencedor();
	}

}
