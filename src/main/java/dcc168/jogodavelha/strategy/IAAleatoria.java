package dcc168.jogodavelha.strategy;

import dcc168.jogodavelha.model.Tabuleiro;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Estratégia de jogador que faz jogadas aleatórias.
 */
public class IAAleatoria implements EstrategiaJogador {
    private String nome;
    private Random aleatorio;

    /**
     * Constrói um jogador IA aleatório.
     *
     * @param nome nome do jogador
     */
    public IAAleatoria(String nome) {
        this.nome = nome;
        this.aleatorio = new Random();
    }

    /**
     * Constrói um jogador IA aleatório com seed para testes.
     *
     * @param nome nome do jogador
     * @param seed seed para o gerador aleatório
     */
    public IAAleatoria(String nome, long seed) {
        this.nome = nome;
        this.aleatorio = new Random(seed);
    }

    @Override
    public int[] fazerJogada(Tabuleiro tabuleiro, char simboloJogador) {
        List<int[]> jogudasDisponiveis = obterJogudasDisponiveis(tabuleiro);
        
        if (jogudasDisponiveis.isEmpty()) {
            throw new IllegalStateException("Nenhuma posição disponível no tabuleiro!");
        }

        return jogudasDisponiveis.get(aleatorio.nextInt(jogudasDisponiveis.size()));
    }

    /**
     * Obtém todas as posições disponíveis no tabuleiro.
     *
     * @param tabuleiro tabuleiro atual
     * @return lista de posições disponíveis [linha, coluna]
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
