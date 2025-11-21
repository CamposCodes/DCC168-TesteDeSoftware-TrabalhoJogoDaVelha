package dcc168.jogodavelha.strategy;

import dcc168.jogodavelha.model.Tabuleiro;
import java.util.Scanner;

/**
 * Estratégia de jogador humano que lê jogadas do console.
 */
public class JogadorHumano implements EstrategiaJogador {
    private String nome;
    private Scanner scanner;

    /**
     * Constrói um jogador humano com nome e scanner para leitura.
     *
     * @param nome nome do jogador
     * @param scanner scanner para leitura de entrada
     */
    public JogadorHumano(String nome, Scanner scanner) {
        this.nome = nome;
        this.scanner = scanner;
    }

    /**
     * Constrói um jogador humano com nome.
     * Usa System.in como entrada.
     *
     * @param nome nome do jogador
     */
    public JogadorHumano(String nome) {
        this(nome, new Scanner(System.in));
    }

    @Override
    public int[] fazerJogada(Tabuleiro tabuleiro, char simboloJogador) {
        while (true) {
            try {
                System.out.print(nome + ", digite linha (0-2): ");
                int linha = scanner.nextInt();
                System.out.print(nome + ", digite coluna (0-2): ");
                int coluna = scanner.nextInt();

                if (linha < 0 || linha >= 3 || coluna < 0 || coluna >= 3) {
                    System.out.println("Coordenadas fora do intervalo!");
                    continue;
                }

                if (!tabuleiro.posicaoDisponivel(linha, coluna)) {
                    System.out.println("Posição já ocupada!");
                    continue;
                }

                return new int[]{linha, coluna};
            } catch (Exception e) {
                System.out.println("Entrada inválida!");
                scanner.nextLine(); // limpar buffer
            }
        }
    }

    @Override
    public String obterNome() {
        return nome;
    }
}
