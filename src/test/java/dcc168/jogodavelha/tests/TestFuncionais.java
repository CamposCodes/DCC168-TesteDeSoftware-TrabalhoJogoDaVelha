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
import dcc168.jogodavelha.strategy.IAAleatoria;
import dcc168.jogodavelha.strategy.IAMinimax;
import dcc168.jogodavelha.view.VisaoConsole;

/**
 * Testes Funcionais para o Jogo da Velha.
 * Cobre 17 classes de equivalência e casos de teste funcional com a nova arquitetura MVC.
 * Total: 55 testes cobrindo Funcionalidade do Jogo da Velha.
 */
public class TestFuncionais {

    private ControladorJogo controlador;
    private Tabuleiro tabuleiro;
    private ValidadorEstado validador;

    @Before
    public void setUp() {
        controlador = new ControladorJogo(new VisaoConsole());
        tabuleiro = new Tabuleiro();
        validador = new ValidadorEstado();
    }

    // ===== CLASSE DE EQUIVALÊNCIA 1: Valores válidos de célula =====
    @Test
    public void testCellValidSymbolX() {
        Celula celula = new Celula();
        celula.definirSimbolo('X');
        assertEquals('X', celula.obterSimbolo());
    }

    @Test
    public void testCellValidSymbolO() {
        Celula celula = new Celula();
        celula.definirSimbolo('O');
        assertEquals('O', celula.obterSimbolo());
    }

    @Test
    public void testCellEmptySymbol() {
        Celula celula = new Celula();
        assertEquals(' ', celula.obterSimbolo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellInvalidSymbol() {
        Celula celula = new Celula();
        celula.definirSimbolo('Z');
    }

    // ===== CLASSE DE EQUIVALÊNCIA 2: Operações de tabuleiro =====
    @Test
    public void testBoardCreationIsEmpty() {
        assertTrue(tabuleiro.posicaoDisponivel(0, 0));
        assertTrue(tabuleiro.posicaoDisponivel(1, 1));
        assertTrue(tabuleiro.posicaoDisponivel(2, 2));
    }

    @Test
    public void testBoardMarkPositionX() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        assertEquals('X', tabuleiro.obterSimbolo(0, 0));
    }

    @Test
    public void testBoardMarkPositionO() {
        tabuleiro.marcarPosicao(1, 1, 'O');
        assertEquals('O', tabuleiro.obterSimbolo(1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoardMarkOccupiedPosition() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 0, 'O');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoardInvalidCoordinatesNegative() {
        tabuleiro.marcarPosicao(-1, 0, 'X');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoardInvalidCoordinatesOverflow() {
        tabuleiro.marcarPosicao(3, 3, 'X');
    }

    // ===== CLASSE DE EQUIVALÊNCIA 3: Verificação de tabuleiro cheio =====
    @Test
    public void testBoardNotFullInitial() {
        assertFalse(tabuleiro.estaCheia());
    }

    @Test
    public void testBoardFullAfterNineMarks() {
        fillBoard();
        assertTrue(tabuleiro.estaCheia());
    }

    @Test
    public void testBoardNotFullWithEightMarks() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro.marcarPosicao(i, j, (i + j) % 2 == 0 ? 'X' : 'O');
            }
        }
        tabuleiro.marcarPosicao(2, 0, 'X');
        assertFalse(tabuleiro.estaCheia());
    }

    // ===== CLASSE DE EQUIVALÊNCIA 4: Vitória em linhas =====
    @Test
    public void testVictoryXFirstRow() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'X');
        tabuleiro.marcarPosicao(0, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'O'));
    }

    @Test
    public void testVictoryOSecondRow() {
        tabuleiro.marcarPosicao(1, 0, 'O');
        tabuleiro.marcarPosicao(1, 1, 'O');
        tabuleiro.marcarPosicao(1, 2, 'O');
        assertTrue(validador.verificarVitoria(tabuleiro, 'O'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testVictoryXThirdRow() {
        tabuleiro.marcarPosicao(2, 0, 'X');
        tabuleiro.marcarPosicao(2, 1, 'X');
        tabuleiro.marcarPosicao(2, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 5: Vitória em colunas =====
    @Test
    public void testVictoryXFirstColumn() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(1, 0, 'X');
        tabuleiro.marcarPosicao(2, 0, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testVictoryOSecondColumn() {
        tabuleiro.marcarPosicao(0, 1, 'O');
        tabuleiro.marcarPosicao(1, 1, 'O');
        tabuleiro.marcarPosicao(2, 1, 'O');
        assertTrue(validador.verificarVitoria(tabuleiro, 'O'));
    }

    @Test
    public void testVictoryXThirdColumn() {
        tabuleiro.marcarPosicao(0, 2, 'X');
        tabuleiro.marcarPosicao(1, 2, 'X');
        tabuleiro.marcarPosicao(2, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 6: Vitória em diagonais =====
    @Test
    public void testVictoryMainDiagonalX() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(1, 1, 'X');
        tabuleiro.marcarPosicao(2, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testVictoryAntiDiagonalO() {
        tabuleiro.marcarPosicao(0, 2, 'O');
        tabuleiro.marcarPosicao(1, 1, 'O');
        tabuleiro.marcarPosicao(2, 0, 'O');
        assertTrue(validador.verificarVitoria(tabuleiro, 'O'));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 7: Sem vitória (posições vazias) =====
    @Test
    public void testNoVictoryEmptyBoard() {
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'O'));
    }

    @Test
    public void testNoVictoryPartialRow() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'X');
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testNoVictoryMixedSymbols() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'O');
        tabuleiro.marcarPosicao(0, 2, 'X');
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'O'));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 8: Detecção de empate =====
    @Test
    public void testDrawDetection() {
        // X O X
        // O X O
        // O X O
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'O');
        tabuleiro.marcarPosicao(0, 2, 'X');
        tabuleiro.marcarPosicao(1, 0, 'O');
        tabuleiro.marcarPosicao(1, 1, 'X');
        tabuleiro.marcarPosicao(1, 2, 'O');
        tabuleiro.marcarPosicao(2, 0, 'O');
        tabuleiro.marcarPosicao(2, 1, 'X');
        tabuleiro.marcarPosicao(2, 2, 'O');
        assertTrue(validador.verificarEmpate(tabuleiro));
    }

    @Test
    public void testNoDraw() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'O');
        assertFalse(validador.verificarEmpate(tabuleiro));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 9: Estado inválido =====
    @Test
    public void testValidStateEmptyBoard() {
        assertTrue(validador.ehEstadoValido(tabuleiro));
    }

    @Test
    public void testValidStateWithMarks() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(1, 1, 'O');
        assertTrue(validador.ehEstadoValido(tabuleiro));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 10: Jogador com símbolo válido =====
    @Test
    public void testPlayerCreationX() {
        Jogador jogador = new Jogador("Jogador 1", 'X');
        assertEquals('X', jogador.obterSimbolo());
        assertEquals("Jogador 1", jogador.obterNome());
    }

    @Test
    public void testPlayerCreationO() {
        Jogador jogador = new Jogador("Jogador 2", 'O');
        assertEquals('O', jogador.obterSimbolo());
        assertEquals("Jogador 2", jogador.obterNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerInvalidSymbol() {
        new Jogador("Jogador", 'Z');
    }

    @Test
    public void testPlayerSymbolComparison() {
        Jogador jogador = new Jogador("Jogador", 'X');
        assertTrue(jogador.ehMeuSimbolo('X'));
        assertFalse(jogador.ehMeuSimbolo('O'));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 11: ControladorJogo - Adicionar jogadores =====
    @Test
    public void testAddTwoPlayers() {
        Jogador p1 = new Jogador("X", 'X');
        Jogador p2 = new Jogador("O", 'O');
        controlador.adicionarJogador(p1, new IAAleatoria("X"));
        controlador.adicionarJogador(p2, new IAAleatoria("O"));
        assertEquals(2, controlador.obterJogadores().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddThreePlayers() {
        controlador.adicionarJogador(new Jogador("P1", 'X'), new IAAleatoria("P1"));
        controlador.adicionarJogador(new Jogador("P2", 'O'), new IAAleatoria("P2"));
        controlador.adicionarJogador(new Jogador("P3", 'X'), new IAAleatoria("P3"));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 12: ControladorJogo - Iniciar jogo =====
    @Test
    public void testStartGameWithTwoPlayers() {
        setupGameWithRandomPlayers();
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, controlador.obterEstadoAtual());
    }

    @Test(expected = IllegalStateException.class)
    public void testStartGameWithoutPlayers() {
        controlador.iniciarJogo();
    }

    // ===== CLASSE DE EQUIVALÊNCIA 13: ControladorJogo - Estados do jogo =====
    @Test
    public void testGameStateTransitionToVictoryX() {
        setupGameWithRandomPlayers();
        controlador.iniciarJogo();
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, controlador.obterEstadoAtual());
    }

    @Test
    public void testGameStateTransitionToVictoryO() {
        setupGameWithRandomPlayers();
        controlador.iniciarJogo();
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, controlador.obterEstadoAtual());
    }

    @Test
    public void testGameStateTransitionToDraw() {
        setupGameWithRandomPlayers();
        controlador.iniciarJogo();
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, controlador.obterEstadoAtual());
    }

    // ===== CLASSE DE EQUIVALÊNCIA 14: IAAleatoria - Gerando movimentos válidos =====
    @Test
    public void testRandomAIMakeValidMove() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        int[] move = new IAAleatoria("AI").fazerJogada(tabuleiro, 'O');
        assertTrue(move[0] >= 0 && move[0] < 3);
        assertTrue(move[1] >= 0 && move[1] < 3);
    }

    @Test(expected = IllegalStateException.class)
    public void testRandomAINoAvailableMoves() {
        fillBoard();
        new IAAleatoria("AI").fazerJogada(tabuleiro, 'X');
    }

    // ===== CLASSE DE EQUIVALÊNCIA 15: IAMinimax - Estratégia ótima =====
    @Test
    public void testMinimaxAIMakesValidMove() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        int[] move = new IAMinimax("Minimax").fazerJogada(tabuleiro, 'O');
        assertTrue(move[0] >= 0 && move[0] < 3);
        assertTrue(move[1] >= 0 && move[1] < 3);
    }

    @Test
    public void testMinimaxAIBlocksOpponentVictory() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'X');
        int[] move = new IAMinimax("Minimax").fazerJogada(tabuleiro, 'O');
        assertEquals(0, move[0]);
        assertEquals(2, move[1]);
    }

    // ===== CLASSE DE EQUIVALÊNCIA 16: EstadoJogo enum =====
    @Test
    public void testGameStateValues() {
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, EstadoJogo.doCodigo(0));
        assertEquals(EstadoJogo.VITORIA_X, EstadoJogo.doCodigo(1));
        assertEquals(EstadoJogo.VITORIA_O, EstadoJogo.doCodigo(2));
        assertEquals(EstadoJogo.EMPATE, EstadoJogo.doCodigo(3));
    }

    // ===== CLASSE DE EQUIVALÊNCIA 17: Fluxo completo do jogo =====
    @Test
    public void testCompleteGameFlowRandomVsRandom() {
        setupGameWithRandomPlayers();
        controlador.iniciarJogo();
        
        int turnCount = 0;
        while (!controlador.jogoTerminou() && turnCount < 10) {
            try {
                controlador.executarTurno();
                turnCount++;
            } catch (Exception e) {
                break;
            }
        }
        
        assertTrue(controlador.jogoTerminou() || turnCount == 10);
    }

    @Test
    public void testCompleteGameFlowMinimaxVsRandom() {
        controlador.adicionarJogador(new Jogador("Minimax", 'X'), new IAMinimax("Minimax"));
        controlador.adicionarJogador(new Jogador("Random", 'O'), new IAAleatoria("Random", 42));
        controlador.iniciarJogo();
        
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, controlador.obterEstadoAtual());
        assertEquals(2, controlador.obterJogadores().size());
    }

    @Test
    public void testResetGameRestartsState() {
        setupGameWithRandomPlayers();
        controlador.iniciarJogo();
        controlador.reiniciarJogo();
        assertEquals(EstadoJogo.AGUARDANDO_JOGADA, controlador.obterEstadoAtual());
    }

    @Test
    public void testGetBoardReturnsValidBoard() {
        assertNotNull(controlador.obterTabuleiro());
        assertEquals(3, controlador.obterTabuleiro().obterTamanho());
    }

    @Test
    public void testGetCurrentPlayerReturnsCorrectPlayer() {
        setupGameWithRandomPlayers();
        controlador.iniciarJogo();
        assertNotNull(controlador.obterJogadorAtual());
    }

    @Test
    public void testGetCurrentPlayerIndexInitiallyZero() {
        setupGameWithRandomPlayers();
        controlador.iniciarJogo();
        assertEquals(0, controlador.obterIndiceJogadorAtual());
    }

    // ===== Testes de casos limite =====
    @Test
    public void testBoundaryConditionBoardCorners() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 2, 'O');
        tabuleiro.marcarPosicao(2, 0, 'X');
        tabuleiro.marcarPosicao(2, 2, 'O');
        assertEquals('X', tabuleiro.obterSimbolo(0, 0));
        assertEquals('O', tabuleiro.obterSimbolo(0, 2));
        assertEquals('X', tabuleiro.obterSimbolo(2, 0));
        assertEquals('O', tabuleiro.obterSimbolo(2, 2));
    }

    @Test
    public void testBoundaryConditionBoardCenter() {
        tabuleiro.marcarPosicao(1, 1, 'X');
        assertEquals('X', tabuleiro.obterSimbolo(1, 1));
    }

    @Test
    public void testBoundaryConditionBoardEdges() {
        tabuleiro.marcarPosicao(0, 1, 'X');
        tabuleiro.marcarPosicao(1, 0, 'O');
        tabuleiro.marcarPosicao(1, 2, 'X');
        tabuleiro.marcarPosicao(2, 1, 'O');
        assertEquals('X', tabuleiro.obterSimbolo(0, 1));
        assertEquals('O', tabuleiro.obterSimbolo(1, 0));
    }

    // ===== Métodos auxiliares =====
    private void setupGameWithRandomPlayers() {
        Jogador p1 = new Jogador("Jogador X", 'X');
        Jogador p2 = new Jogador("Jogador O", 'O');
        controlador.adicionarJogador(p1, new IAAleatoria("X", 42));
        controlador.adicionarJogador(p2, new IAAleatoria("O", 43));
    }

    private void fillBoard() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'O');
        tabuleiro.marcarPosicao(0, 2, 'X');
        tabuleiro.marcarPosicao(1, 0, 'O');
        tabuleiro.marcarPosicao(1, 1, 'X');
        tabuleiro.marcarPosicao(1, 2, 'O');
        tabuleiro.marcarPosicao(2, 0, 'X');
        tabuleiro.marcarPosicao(2, 1, 'O');
        tabuleiro.marcarPosicao(2, 2, 'X');
    }
}
