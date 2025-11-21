package dcc168.jogodavelha.controller;

import dcc168.jogodavelha.model.Tabuleiro;
import dcc168.jogodavelha.model.EstadoJogo;
import dcc168.jogodavelha.model.Jogador;
import dcc168.jogodavelha.model.ValidadorEstado;
import dcc168.jogodavelha.strategy.EstrategiaJogador;
import dcc168.jogodavelha.view.VisaoJogo;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador do jogo - gerencia a lógica de fluxo do jogo.
 * Implementa o padrão MVC como intermediário entre Model e View.
 */
public class ControladorJogo {
    private Tabuleiro tabuleiro;
    private VisaoJogo visao;
    private ValidadorEstado validador;
    private EstadoJogo estadoAtual;
    private List<Jogador> jogadores;
    private int indiceJogadorAtual;
    private List<EstrategiaJogador> estrategias;

    /**
     * Constrói o controlador do jogo.
     *
     * @param visao visualização do jogo
     */
    public ControladorJogo(VisaoJogo visao) {
        this.tabuleiro = new Tabuleiro();
        this.visao = visao;
        this.validador = new ValidadorEstado();
        this.estadoAtual = EstadoJogo.AGUARDANDO_JOGADA;
        this.jogadores = new ArrayList<>();
        this.estrategias = new ArrayList<>();
        this.indiceJogadorAtual = 0;
    }

    /**
     * Adiciona um jogador ao jogo.
     *
     * @param jogador jogador a ser adicionado
     * @param estrategia estratégia do jogador
     * @throws IllegalStateException se há mais de 2 jogadores
     */
    public void adicionarJogador(Jogador jogador, EstrategiaJogador estrategia) {
        if (jogadores.size() >= 2) {
            throw new IllegalStateException("Máximo de 2 jogadores!");
        }
        jogadores.add(jogador);
        estrategias.add(estrategia);
    }

    /**
     * Inicia o jogo.
     *
     * @throws IllegalStateException se há menos de 2 jogadores
     */
    public void iniciarJogo() {
        if (jogadores.size() < 2) {
            throw new IllegalStateException("É necessário exatamente 2 jogadores!");
        }
        tabuleiro.reiniciar();
        estadoAtual = EstadoJogo.AGUARDANDO_JOGADA;
        indiceJogadorAtual = 0;
        visao.exibirMensagem("=== JOGO INICIADO ===\n");
    }

    /**
     * Executa a próxima jogada do jogo.
     *
     * @throws IllegalStateException se o jogo já terminou
     */
    public void executarTurno() {
        if (estadoAtual != EstadoJogo.AGUARDANDO_JOGADA) {
            throw new IllegalStateException("Jogo já terminou!");
        }

        Jogador jogadorAtual = jogadores.get(indiceJogadorAtual);
        EstrategiaJogador estrategia = estrategias.get(indiceJogadorAtual);
        char simbolo = jogadorAtual.obterSimbolo();

        visao.exibirTabuleiro(tabuleiro);
        visao.exibirJogadorAtual(jogadorAtual.obterNome(), simbolo);

        try {
            int[] jogada = estrategia.fazerJogada(tabuleiro, simbolo);
            tabuleiro.marcarPosicao(jogada[0], jogada[1], simbolo);

            // Verificar vitória
            if (validador.verificarVitoria(tabuleiro, simbolo)) {
                estadoAtual = simbolo == 'X' ? EstadoJogo.VITORIA_X : EstadoJogo.VITORIA_O;
                visao.exibirTabuleiro(tabuleiro);
                visao.exibirFimJogo(estadoAtual, jogadorAtual.obterNome());
            }
            // Verificar empate
            else if (validador.verificarEmpate(tabuleiro)) {
                estadoAtual = EstadoJogo.EMPATE;
                visao.exibirTabuleiro(tabuleiro);
                visao.exibirFimJogo(estadoAtual, null);
            }
            // Alternar jogador
            else {
                indiceJogadorAtual = (indiceJogadorAtual + 1) % 2;
            }
        } catch (IllegalArgumentException e) {
            visao.exibirMensagem("Jogada inválida: " + e.getMessage());
        }
    }

    /**
     * Reinicia o jogo.
     */
    public void reiniciarJogo() {
        iniciarJogo();
    }

    /**
     * Obtém o estado atual do jogo.
     *
     * @return estado atual
     */
    public EstadoJogo obterEstadoAtual() {
        return estadoAtual;
    }

    /**
     * Obtém o tabuleiro atual.
     *
     * @return tabuleiro do jogo
     */
    public Tabuleiro obterTabuleiro() {
        return tabuleiro;
    }

    /**
     * Obtém o jogador atual.
     *
     * @return jogador atual
     */
    public Jogador obterJogadorAtual() {
        return jogadores.get(indiceJogadorAtual);
    }

    /**
     * Obtém todos os jogadores.
     *
     * @return lista de jogadores
     */
    public List<Jogador> obterJogadores() {
        return new ArrayList<>(jogadores);
    }

    /**
     * Obtém o índice do jogador atual.
     *
     * @return índice (0 ou 1)
     */
    public int obterIndiceJogadorAtual() {
        return indiceJogadorAtual;
    }

    /**
     * Define manualmente o estado do jogo (para testes).
     *
     * @param estado novo estado
     */
    public void definirEstadoAtual(EstadoJogo estado) {
        this.estadoAtual = estado;
    }

    /**
     * Verifica se o jogo terminou.
     *
     * @return true se o jogo terminou
     */
    public boolean jogoTerminou() {
        return estadoAtual != EstadoJogo.AGUARDANDO_JOGADA;
    }

    /**
     * Obtém o vencedor do jogo (null se empate ou jogo em andamento).
     *
     * @return vencedor ou null
     */
    public Jogador obterVencedor() {
        if (estadoAtual == EstadoJogo.VITORIA_X) {
            for (Jogador j : jogadores) {
                if (j.obterSimbolo() == 'X') {
                    return j;
                }
            }
        } else if (estadoAtual == EstadoJogo.VITORIA_O) {
            for (Jogador j : jogadores) {
                if (j.obterSimbolo() == 'O') {
                    return j;
                }
            }
        }
        return null;
    }
}
