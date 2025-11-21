package dcc168.jogodavelha.view;

import dcc168.jogodavelha.model.Tabuleiro;
import dcc168.jogodavelha.model.EstadoJogo;

/**
 * Implementação de visualização em console para o Jogo da Velha.
 */
public class VisaoConsole implements VisaoJogo {

    @Override
    public void exibirTabuleiro(Tabuleiro tabuleiro) {
        System.out.println("\nTabuleiro:");
        System.out.println(tabuleiro.toString());
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void exibirEstadoJogo(EstadoJogo estado) {
        System.out.println("Estado do jogo: " + estado.obterDescricao());
    }

    @Override
    public void exibirJogadorAtual(String nomeJogador, char simbolo) {
        System.out.println("\nVez de " + nomeJogador + " (" + simbolo + ")");
    }

    @Override
    public void exibirFimJogo(EstadoJogo estado, String nomeVencedor) {
        System.out.println("\n===== FIM DO JOGO =====");
        
        if (estado == EstadoJogo.VITORIA_X || estado == EstadoJogo.VITORIA_O) {
            System.out.println("Vencedor: " + nomeVencedor);
            System.out.println("Estado: " + estado.obterDescricao());
        } else if (estado == EstadoJogo.EMPATE) {
            System.out.println("Resultado: EMPATE");
            System.out.println("Estado: " + estado.obterDescricao());
        }
        
        System.out.println("=======================\n");
    }
}
