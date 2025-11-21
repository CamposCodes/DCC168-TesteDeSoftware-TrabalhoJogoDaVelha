package dcc168.jogodavelha.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import dcc168.jogodavelha.controller.ControladorJogo;
import dcc168.jogodavelha.model.Tabuleiro;
import dcc168.jogodavelha.model.Celula;
import dcc168.jogodavelha.model.EstadoJogo;
import dcc168.jogodavelha.model.Jogador;
import dcc168.jogodavelha.model.ValidadorEstado;
import dcc168.jogodavelha.strategy.JogadorHumano;
import dcc168.jogodavelha.strategy.IAAleatoria;
import dcc168.jogodavelha.strategy.IAMinimax;
import dcc168.jogodavelha.view.VisaoConsole;
import dcc168.jogodavelha.view.VisaoJogo;
import java.util.Scanner;

/**
 * Testes Estruturais para o Jogo da Velha.
 * Testa cobertura de branches, métodos e estruturas de controle.
 * Total: 37 testes para manter 100% de cobertura estrutural.
 */
public class TestEstruturais {

    private ControladorJogo controlador;
    private Tabuleiro tabuleiro;
    private ValidadorEstado validador;
    private VisaoJogo view;

    @Before
    public void setUp() {
        view = new VisaoConsole();
        controlador = new ControladorJogo(view);
        tabuleiro = new Tabuleiro();
        validador = new ValidadorEstado();
    }

    // ===== TESTES DO CELL (5 testes) =====
    @Test
    public void testCellGetSymbolAfterSet() {
        Celula celula = new Celula();
        celula.definirSimbolo('X');
        assertEquals('X', celula.obterSimbolo());
    }

    @Test
    public void testCellIsEmptyTrue() {
        Celula celula = new Celula();
        assertTrue(celula.estaVazia());
    }

    @Test
    public void testCellIsEmptyFalse() {
        Celula celula = new Celula();
        celula.definirSimbolo('O');
        assertFalse(celula.estaVazia());
    }

    @Test
    public void testCellClear() {
        Celula celula = new Celula();
        celula.definirSimbolo('X');
        celula.limpar();
        assertEquals(' ', celula.obterSimbolo());
    }

    @Test
    public void testCellToString() {
        Celula celula = new Celula();
        celula.definirSimbolo('X');
        assertEquals("X", celula.toString());
    }

    // ===== TESTES DO BOARD (8 testes) =====
    @Test
    public void testBoardGetSize() {
        assertEquals(3, tabuleiro.obterTamanho());
    }

    @Test
    public void testBoardResetClearsBoardCompletely() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(1, 1, 'O');
        tabuleiro.reiniciar();
        assertTrue(tabuleiro.posicaoDisponivel(0, 0));
        assertTrue(tabuleiro.posicaoDisponivel(1, 1));
    }

    @Test
    public void testBoardGetCell() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        Celula celula = tabuleiro.obterCelula(0, 0);
        assertEquals('X', celula.obterSimbolo());
    }

    @Test
    public void testBoardGetCells() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        Celula[][] cells = tabuleiro.obterCelulas();
        assertEquals('X', cells[0][0].obterSimbolo());
    }

    @Test
    public void testBoardToString() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        String result = tabuleiro.toString();
        assertNotNull(result);
        assertTrue(result.contains("X"));
    }

    @Test
    public void testBoardIsFullFalseWithThreeMarks() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'O');
        tabuleiro.marcarPosicao(0, 2, 'X');
        assertFalse(tabuleiro.estaCheia());
    }

    @Test
    public void testBoardMarkPositionMultiple() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(1, 1, 'O');
        tabuleiro.marcarPosicao(2, 2, 'X');
        assertEquals(3, countMarkedPositions(tabuleiro));
    }

    @Test
    public void testBoardPositionAvailableAfterClear() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.obterCelula(0, 0).limpar();
        assertTrue(tabuleiro.posicaoDisponivel(0, 0));
    }

    // ===== TESTES DE RULESVALIDATOR (8 testes) =====
    @Test
    public void testValidatorCheckVictoryAllRows() {
        for (int row = 0; row < 3; row++) {
            Tabuleiro testBoard = new Tabuleiro();
            testBoard.marcarPosicao(row, 0, 'X');
            testBoard.marcarPosicao(row, 1, 'X');
            testBoard.marcarPosicao(row, 2, 'X');
            assertTrue("Row " + row + " should be victory", validador.verificarVitoria(testBoard, 'X'));
        }
    }

    @Test
    public void testValidatorCheckVictoryAllColumns() {
        for (int col = 0; col < 3; col++) {
            Tabuleiro testBoard = new Tabuleiro();
            testBoard.marcarPosicao(0, col, 'O');
            testBoard.marcarPosicao(1, col, 'O');
            testBoard.marcarPosicao(2, col, 'O');
            assertTrue("Column " + col + " should be victory", validador.verificarVitoria(testBoard, 'O'));
        }
    }

    @Test
    public void testValidatorCheckMainDiagonal() {
        validador.verificarVitoria(tabuleiro, 'X');
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(1, 1, 'X');
        tabuleiro.marcarPosicao(2, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testValidatorCheckAntiDiagonal() {
        tabuleiro.marcarPosicao(0, 2, 'O');
        tabuleiro.marcarPosicao(1, 1, 'O');
        tabuleiro.marcarPosicao(2, 0, 'O');
        assertTrue(validador.verificarVitoria(tabuleiro, 'O'));
    }

    @Test
    public void testValidatorCheckDrawCondition() {
        fillBoardWithDraw(tabuleiro);
        assertTrue(validador.verificarEmpate(tabuleiro));
    }

    @Test
    public void testValidatorIsValidStateTrueEmpty() {
        assertTrue(validador.ehEstadoValido(tabuleiro));
    }

    @Test
    public void testValidatorIsValidStateWithVictory() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'X');
        tabuleiro.marcarPosicao(0, 2, 'X');
        assertTrue(validador.ehEstadoValido(tabuleiro));
    }

    @Test
    public void testValidatorCheckDrawNoVictory() {
        fillBoardWithDraw(tabuleiro);
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'O'));
    }

    // ===== TESTES DO PLAYER (4 testes) =====
    @Test
    public void testPlayerGetName() {
        Jogador jogador = new Jogador("Teste", 'X');
        assertEquals("Teste", jogador.obterNome());
    }

    @Test
    public void testPlayerGetSymbol() {
        Jogador jogador = new Jogador("Jogador", 'O');
        assertEquals('O', jogador.obterSimbolo());
    }

    @Test
    public void testPlayerIsMySymbolTrue() {
        Jogador jogador = new Jogador("P", 'X');
        assertTrue(jogador.ehMeuSimbolo('X'));
    }

    @Test
    public void testPlayerIsMySymbolFalse() {
        Jogador jogador = new Jogador("P", 'X');
        assertFalse(jogador.ehMeuSimbolo('O'));
    }

    // ===== TESTES DO GAMESTATE ENUM (4 testes) =====
    @Test
    public void testGameStateFromCodeAguardandoJogada() {
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, EstadoJogo.doCodigo(0));
    }

    @Test
    public void testGameStateFromCodeVitoriaX() {
        assertEquals(EstadoJogo.VITORIA_X, EstadoJogo.doCodigo(1));
    }

    @Test
    public void testGameStateFromCodeVitoriaO() {
        assertEquals(EstadoJogo.VITORIA_O, EstadoJogo.doCodigo(2));
    }

    @Test
    public void testGameStateFromCodeEmpate() {
        assertEquals(EstadoJogo.EMPATE, EstadoJogo.doCodigo(3));
    }

    // ===== TESTES DO GAMECONTROLLER (8 testes) =====
    @Test
    public void testGameControllerGetBoard() {
        assertNotNull(controlador.obterTabuleiro());
    }

    @Test
    public void testGameControllerGetCurrentState() {
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, controlador.obterEstadoAtual());
    }

    @Test
    public void testGameControllerAddPlayerAndGetList() {
        Jogador p1 = new Jogador("P1", 'X');
        controlador.adicionarJogador(p1, new IAAleatoria("P1"));
        assertEquals(1, controlador.obterJogadores().size());
    }

    @Test
    public void testGameControllerStartGameSetsState() {
        Jogador p1 = new Jogador("P1", 'X');
        Jogador p2 = new Jogador("P2", 'O');
        controlador.adicionarJogador(p1, new IAAleatoria("P1"));
        controlador.adicionarJogador(p2, new IAAleatoria("P2"));
        controlador.iniciarJogo();
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, controlador.obterEstadoAtual());
    }

    @Test
    public void testGameControllerSetCurrentState() {
        controlador.definirEstadoAtual(EstadoJogo.VITORIA_X);
        assertEquals(EstadoJogo.VITORIA_X, controlador.obterEstadoAtual());
    }

    @Test
    public void testGameControllerIsGameOverFalseInitial() {
        assertFalse(controlador.jogoTerminou());
    }

    @Test
    public void testGameControllerIsGameOverTrueAfterState() {
        controlador.definirEstadoAtual(EstadoJogo.EMPATE);
        assertTrue(controlador.jogoTerminou());
    }

    @Test
    public void testGameControllerGetCurrentPlayerIndex() {
        Jogador p1 = new Jogador("P1", 'X');
        Jogador p2 = new Jogador("P2", 'O');
        controlador.adicionarJogador(p1, new IAAleatoria("P1"));
        controlador.adicionarJogador(p2, new IAAleatoria("P2"));
        controlador.iniciarJogo();
        assertEquals(0, controlador.obterIndiceJogadorAtual());
    }

    // ===== TESTES DO RANDOMAI (3 testes) =====
    @Test
    public void testRandomAIGetName() {
        IAAleatoria ai = new IAAleatoria("TestAI");
        assertEquals("TestAI", ai.obterNome());
    }

    @Test
    public void testRandomAIMakeMoveReturnsArray() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        int[] move = new IAAleatoria("AI", 42).fazerJogada(tabuleiro, 'O');
        assertEquals(2, move.length);
    }

    @Test
    public void testRandomAIMakeMoveValidCoordinates() {
        for (int i = 0; i < 8; i++) {
            Tabuleiro testBoard = new Tabuleiro();
            for (int j = 0; j < i; j++) {
                testBoard.marcarPosicao(j / 3, j % 3, j % 2 == 0 ? 'X' : 'O');
            }
            int[] move = new IAAleatoria("AI", System.currentTimeMillis()).fazerJogada(testBoard, 'X');
            assertTrue(move[0] >= 0 && move[0] < 3 && move[1] >= 0 && move[1] < 3);
        }
    }

    // ===== TESTES DO MINIMAXAI (2 testes) =====
    @Test
    public void testMinimaxAIGetName() {
        IAMinimax ai = new IAMinimax("Minimax");
        assertEquals("Minimax", ai.obterNome());
    }

    @Test
    public void testMinimaxAIMakeMoveReturnsValidPosition() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        int[] move = new IAMinimax("Minimax").fazerJogada(tabuleiro, 'O');
        assertTrue(move[0] >= 0 && move[0] < 3 && move[1] >= 0 && move[1] < 3);
    }

    // ===== TESTES DA VIEW (3 testes) =====
    @Test
    public void testConsoleViewDisplayBoard() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        view.exibirTabuleiro(tabuleiro);
        // Se não lança exceção, passou
        assertTrue(true);
    }

    @Test
    public void testConsoleViewDisplayMessage() {
        view.exibirMensagem("Teste");
        assertTrue(true);
    }

    @Test
    public void testConsoleViewDisplayGameState() {
        view.exibirEstadoJogo(EstadoJogo.AGUARDANDO_JOGADA);
        assertTrue(true);
    }

    // ===== Métodos auxiliares =====
    private int countMarkedPositions(Tabuleiro tabuleiro) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!tabuleiro.posicaoDisponivel(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private void fillBoardWithDraw(Tabuleiro tabuleiro) {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'O');
        tabuleiro.marcarPosicao(0, 2, 'X');
        tabuleiro.marcarPosicao(1, 0, 'O');
        tabuleiro.marcarPosicao(1, 1, 'X');
        tabuleiro.marcarPosicao(1, 2, 'O');
        tabuleiro.marcarPosicao(2, 0, 'O');
        tabuleiro.marcarPosicao(2, 1, 'X');
        tabuleiro.marcarPosicao(2, 2, 'O');
    }
}
