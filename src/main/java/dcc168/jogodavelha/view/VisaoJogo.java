package dcc168.jogodavelha.view;

import dcc168.jogodavelha.model.Tabuleiro;
import dcc168.jogodavelha.model.EstadoJogo;

/**
 * Interface abstrata para visualização do jogo.
 * Define o contrato que todas as visões devem seguir.
 */
public interface VisaoJogo {
    
    /**
     * Mostra o tabuleiro na tela.
     *
     * @param tabuleiro tabuleiro a ser exibido
     */
    void exibirTabuleiro(Tabuleiro tabuleiro);

    /**
     * Mostra uma mensagem na tela.
     *
     * @param mensagem mensagem a ser exibida
     */
    void exibirMensagem(String mensagem);

    /**
     * Mostra o estado atual do jogo.
     *
     * @param estado estado do jogo
     */
    void exibirEstadoJogo(EstadoJogo estado);

    /**
     * Mostra o jogador atual.
     *
     * @param nomeJogador nome do jogador atual
     * @param simbolo símbolo do jogador
     */
    void exibirJogadorAtual(String nomeJogador, char simbolo);

    /**
     * Mostra o resultado final do jogo.
     *
     * @param estado estado final
     * @param nomeVencedor nome do vencedor (null se empate)
     */
    void exibirFimJogo(EstadoJogo estado, String nomeVencedor);
}
