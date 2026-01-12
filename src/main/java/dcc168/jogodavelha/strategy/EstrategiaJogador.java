package dcc168.jogodavelha.strategy;

import java.util.ArrayList;
import java.util.List;

import dcc168.jogodavelha.model.Tabuleiro;

/**
 * Interface para diferentes estratégias de jogadores.
 * Define o contrato que todos os jogadores devem seguir.
 */
public interface EstrategiaJogador {
    
    /**
     * Faz uma jogada no tabuleiro.
     * Deve retornar uma posição válida [linha, coluna].
     *
     * @param tabuleiro tabuleiro atual do jogo
     * @param simboloJogador símbolo do jogador ('X' ou 'O')
     * @return array com [linha, coluna] da jogada
     * @throws IllegalStateException se não conseguir fazer uma jogada válida
     */
    int[] fazerJogada(Tabuleiro tabuleiro, char simboloJogador);

    /**
     * Obtém o nome da estratégia do jogador.
     *
     * @return nome do jogador
     */
    String obterNome();
    /**
     * Obtém todas as posições disponíveis no tabuleiro.
     *
     * @param tabuleiro tabuleiro atual
     * @return lista de posições disponíveis [linha, coluna]
     */
    public static List<int[]> obterJogadasDisponiveis(Tabuleiro tabuleiro) {
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
}

