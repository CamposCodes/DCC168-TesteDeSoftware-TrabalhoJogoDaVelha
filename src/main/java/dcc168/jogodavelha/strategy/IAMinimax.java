package dcc168.jogodavelha.strategy;

import dcc168.jogodavelha.model.Tabuleiro;
import java.util.ArrayList;
import java.util.List;

/**
 * Estratégia de jogador com algoritmo Minimax.
 * Implementa IA forte que busca ganhar ou empatar.
 */
public class IAMinimax implements EstrategiaJogador {
    private String nome;

    /**
     * Constrói um jogador IA com Minimax.
     *
     * @param nome nome do jogador
     */
    public IAMinimax(String nome) {
        this.nome = nome;
    }

    @Override
    public int[] fazerJogada(Tabuleiro tabuleiro, char simboloJogador) {
        char simboloOponente = simboloJogador == 'X' ? 'O' : 'X';
        int melhorScore = Integer.MIN_VALUE;
        int[] melhorJogada = null;

        List<int[]> jogudasDisponiveis = obterJogudasDisponiveis(tabuleiro);
        if (jogudasDisponiveis.isEmpty()) {
            throw new IllegalStateException("Nenhuma posição disponível no tabuleiro!");
        }

        for (int[] jogada : jogudasDisponiveis) {
            tabuleiro.marcarPosicao(jogada[0], jogada[1], simboloJogador);
            int score = minimax(tabuleiro, 0, false, simboloJogador, simboloOponente);
            tabuleiro.obterCelula(jogada[0], jogada[1]).limpar();

            if (score > melhorScore) {
                melhorScore = score;
                melhorJogada = jogada;
            }
        }

        return melhorJogada;
    }

    /**
     * Algoritmo Minimax para avaliação de posições.
     *
     * @param tabuleiro tabuleiro atual
     * @param profundidade profundidade da recursão
     * @param isMaximizing true se é turno do maximizador
     * @param simboloJogador símbolo do jogador IA
     * @param simboloOponente símbolo do oponente
     * @return score da posição
     */
    private int minimax(Tabuleiro tabuleiro, int profundidade, boolean isMaximizing, char simboloJogador, char simboloOponente) {
        // Verificar estados terminais
        if (verificarVitoria(tabuleiro, simboloJogador)) {
            return 10 - profundidade; // Ganhar logo é melhor
        }
        if (verificarVitoria(tabuleiro, simboloOponente)) {
            return profundidade - 10; // Perder logo é pior
        }
        if (tabuleiro.estaCheia()) {
            return 0; // Empate
        }

        if (isMaximizing) {
            int melhorScore = Integer.MIN_VALUE;
            List<int[]> jogudasDisponiveis = obterJogudasDisponiveis(tabuleiro);
            for (int[] jogada : jogudasDisponiveis) {
                tabuleiro.marcarPosicao(jogada[0], jogada[1], simboloJogador);
                int score = minimax(tabuleiro, profundidade + 1, false, simboloJogador, simboloOponente);
                tabuleiro.obterCelula(jogada[0], jogada[1]).limpar();
                melhorScore = Math.max(score, melhorScore);
            }
            return melhorScore;
        } else {
            int melhorScore = Integer.MAX_VALUE;
            List<int[]> jogudasDisponiveis = obterJogudasDisponiveis(tabuleiro);
            for (int[] jogada : jogudasDisponiveis) {
                tabuleiro.marcarPosicao(jogada[0], jogada[1], simboloOponente);
                int score = minimax(tabuleiro, profundidade + 1, true, simboloJogador, simboloOponente);
                tabuleiro.obterCelula(jogada[0], jogada[1]).limpar();
                melhorScore = Math.min(score, melhorScore);
            }
            return melhorScore;
        }
    }

    /**
     * Verifica se um jogador venceu.
     *
     * @param tabuleiro tabuleiro
     * @param simbolo símbolo do jogador
     * @return true se o jogador venceu
     */
    private boolean verificarVitoria(Tabuleiro tabuleiro, char simbolo) {
        // Verificar linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro.obterSimbolo(i, 0) == simbolo && 
                tabuleiro.obterSimbolo(i, 1) == simbolo && 
                tabuleiro.obterSimbolo(i, 2) == simbolo) {
                return true;
            }
        }
        // Verificar colunas
        for (int j = 0; j < 3; j++) {
            if (tabuleiro.obterSimbolo(0, j) == simbolo && 
                tabuleiro.obterSimbolo(1, j) == simbolo && 
                tabuleiro.obterSimbolo(2, j) == simbolo) {
                return true;
            }
        }
        // Verificar diagonais
        if (tabuleiro.obterSimbolo(0, 0) == simbolo && 
            tabuleiro.obterSimbolo(1, 1) == simbolo && 
            tabuleiro.obterSimbolo(2, 2) == simbolo) {
            return true;
        }
        if (tabuleiro.obterSimbolo(0, 2) == simbolo && 
            tabuleiro.obterSimbolo(1, 1) == simbolo && 
            tabuleiro.obterSimbolo(2, 0) == simbolo) {
            return true;
        }
        return false;
    }

    /**
     * Obtém todas as posições disponíveis.
     *
     * @param tabuleiro tabuleiro
     * @return lista de movimentos disponíveis
     */
    private List<int[]> obterJogudasDisponiveis(Tabuleiro tabuleiro) {
        List<int[]> jogadas = new ArrayList<>();
        for (int i = 0; i < tabuleiro.obterTamanho(); i++) {
            for (int j = 0; j < tabuleiro.obterTamanho(); j++) {
                if (tabuleiro.posicaoDisponivel(i, j)) {
                    jogadas.add(new int[]{i, j});
                }
            }
        }
        return jogadas;
    }

    @Override
    public String obterNome() {
        return nome;
    }
}
