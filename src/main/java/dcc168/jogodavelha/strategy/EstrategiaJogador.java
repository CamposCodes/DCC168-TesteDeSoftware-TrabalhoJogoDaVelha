package dcc168.jogodavelha.strategy;

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
}

