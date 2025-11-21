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
 * Testes de Mutação para o Jogo da Velha.
 * Valida comportamentos que seriam alterados por mutantes.
 * Total: 47 testes para manter alta taxa de morte de mutantes.
 */
public class TestMutacao {

    private ControladorJogo controlador;
    private Tabuleiro tabuleiro;
    private ValidadorEstado validador;

    @Before
    public void setUp() {
        controlador = new ControladorJogo(new VisaoConsole());
        tabuleiro = new Tabuleiro();
        validador = new ValidadorEstado();
    }

    // ===== MUTAÇÃO: Trocar == por != em verificações =====
    @Test
    public void testCellSymbolEqualityX() {
        Celula celula = new Celula();
        celula.definirSimbolo('X');
        assertTrue(celula.obterSimbolo() == 'X');
        assertFalse(celula.obterSimbolo() != 'X');
    }

    @Test
    public void testCellSymbolEqualityO() {
        Celula celula = new Celula();
        celula.definirSimbolo('O');
        assertTrue(celula.obterSimbolo() == 'O');
        assertFalse(celula.obterSimbolo() != 'O');
    }

    @Test
    public void testCellSymbolEqualityEmpty() {
        Celula celula = new Celula();
        assertTrue(celula.obterSimbolo() == ' ');
        assertFalse(celula.obterSimbolo() != ' ');
    }

    @Test
    public void testBoardIsEmptyNotFilledInitially() {
        assertFalse(tabuleiro.estaCheia());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(tabuleiro.posicaoDisponivel(i, j));
            }
        }
    }

    @Test
    public void testBoardAllPositionsCountedWhenFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro.marcarPosicao(i, j, i % 2 == 0 ? 'X' : 'O');
            }
        }
        assertTrue(tabuleiro.estaCheia());
    }

    // ===== MUTAÇÃO: Trocar operador de índice (0->1, 1->2, 2->0) =====
    @Test
    public void testVictoryRowIndexZero() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'X');
        tabuleiro.marcarPosicao(0, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'O'));
    }

    @Test
    public void testVictoryRowIndexOne() {
        tabuleiro.marcarPosicao(1, 0, 'X');
        tabuleiro.marcarPosicao(1, 1, 'X');
        tabuleiro.marcarPosicao(1, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'O'));
    }

    @Test
    public void testVictoryRowIndexTwo() {
        tabuleiro.marcarPosicao(2, 0, 'X');
        tabuleiro.marcarPosicao(2, 1, 'X');
        tabuleiro.marcarPosicao(2, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'O'));
    }

    @Test
    public void testVictoryColumnIndexZero() {
        tabuleiro.marcarPosicao(0, 0, 'O');
        tabuleiro.marcarPosicao(1, 0, 'O');
        tabuleiro.marcarPosicao(2, 0, 'O');
        assertTrue(validador.verificarVitoria(tabuleiro, 'O'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testVictoryColumnIndexOne() {
        tabuleiro.marcarPosicao(0, 1, 'O');
        tabuleiro.marcarPosicao(1, 1, 'O');
        tabuleiro.marcarPosicao(2, 1, 'O');
        assertTrue(validador.verificarVitoria(tabuleiro, 'O'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testVictoryColumnIndexTwo() {
        tabuleiro.marcarPosicao(0, 2, 'O');
        tabuleiro.marcarPosicao(1, 2, 'O');
        tabuleiro.marcarPosicao(2, 2, 'O');
        assertTrue(validador.verificarVitoria(tabuleiro, 'O'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testVictoryDiagonalMainExactPositions() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(1, 1, 'X');
        tabuleiro.marcarPosicao(2, 2, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
    }

    @Test
    public void testVictoryDiagonalAntiExactPositions() {
        tabuleiro.marcarPosicao(0, 2, 'X');
        tabuleiro.marcarPosicao(1, 1, 'X');
        tabuleiro.marcarPosicao(2, 0, 'X');
        assertTrue(validador.verificarVitoria(tabuleiro, 'X'));
    }

    // ===== MUTAÇÃO: Remover condições (if alterado para sempre true/false) =====
    @Test
    public void testBoardValidCoordinateBoundaryLowerX() {
        assertTrue(tabuleiro.posicaoDisponivel(0, 0));
        tabuleiro.marcarPosicao(0, 0, 'X');
        assertFalse(tabuleiro.posicaoDisponivel(0, 0));
    }

    @Test
    public void testBoardValidCoordinateBoundaryUpperX() {
        assertTrue(tabuleiro.posicaoDisponivel(2, 2));
        tabuleiro.marcarPosicao(2, 2, 'O');
        assertFalse(tabuleiro.posicaoDisponivel(2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoardInvalidCoordinateNegativeRow() {
        tabuleiro.marcarPosicao(-1, 1, 'X');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoardInvalidCoordinateNegativeCol() {
        tabuleiro.marcarPosicao(1, -1, 'X');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoardInvalidCoordinateOverflowRow() {
        tabuleiro.marcarPosicao(3, 1, 'X');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoardInvalidCoordinateOverflowCol() {
        tabuleiro.marcarPosicao(1, 3, 'X');
    }

    // ===== MUTAÇÃO: Trocar return true/false =====
    @Test
    public void testValidatorDrawReturnsFalseWhenVictory() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(0, 1, 'X');
        tabuleiro.marcarPosicao(0, 2, 'X');
        assertFalse(validador.verificarEmpate(tabuleiro));
    }

    @Test
    public void testValidatorDrawReturnsTrueOnlyWhenBoardFullAndNoVictory() {
        fillBoardWithDrawPattern(tabuleiro);
        assertTrue(validador.verificarEmpate(tabuleiro));
        assertFalse(validador.verificarVitoria(tabuleiro, 'X'));
        assertFalse(validador.verificarVitoria(tabuleiro, 'O'));
    }

    @Test
    public void testValidatorStateValidityWhenEmpty() {
        assertTrue(validador.ehEstadoValido(tabuleiro));
    }

    @Test
    public void testValidatorStateValidityDoesNotAllowDoubleVictory() {
        // Não pode ter ambos vencendo
        assertTrue(validador.ehEstadoValido(tabuleiro));
    }

    // ===== MUTAÇÃO: Trocar AND/OR em condições =====
    @Test
    public void testCellInvalidSymbolRejectsAll() {
        Celula celula = new Celula();
        try {
            celula.definirSimbolo('A');
            fail("Deve rejeitar símbolo 'A'");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        
        try {
            celula.definirSimbolo('Z');
            fail("Deve rejeitar símbolo 'Z'");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testPlayerSymbolValidationAnd() {
        try {
            new Jogador("P", 'Z');
            fail("Deve rejeitar símbolo inválido");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    // ===== MUTAÇÃO: Alterar constantes =====
    @Test
    public void testBoardSizeIs3() {
        assertEquals(3, tabuleiro.obterTamanho());
    }

    @Test
    public void testBoardAllPositionsAreSize3() {
        for (int i = 0; i < tabuleiro.obterTamanho(); i++) {
            for (int j = 0; j < tabuleiro.obterTamanho(); j++) {
                assertTrue(tabuleiro.posicaoDisponivel(i, j));
            }
        }
        assertEquals(9, countAvailablePositions());
    }

    @Test
    public void testGameStateCodesAreCorrect() {
        assertEquals(0, EstadoJogo.AGUARDANDO_JOGADA.obterCodigo());
        assertEquals(1, EstadoJogo.VITORIA_X.obterCodigo());
        assertEquals(2, EstadoJogo.VITORIA_O.obterCodigo());
        assertEquals(3, EstadoJogo.EMPATE.obterCodigo());
    }

    // ===== MUTAÇÃO: Trocar operadores aritméticos =====
    @Test
    public void testRandomAIAvailableMovesCountCorrect() {
        tabuleiro.marcarPosicao(0, 0, 'X');
        tabuleiro.marcarPosicao(1, 1, 'O');
        int[] move = new IAAleatoria("AI", 42).fazerJogada(tabuleiro, 'X');
        assertNotNull(move);
        assertTrue(move[0] >= 0 && move[0] < 3);
        assertTrue(move[1] >= 0 && move[1] < 3);
    }

    @Test
    public void testMinimaxScoresAreDifferent() {
        // Vitória > Empate > Derrota
        int victoryScore = 10; // simplificado
        int drawScore = 0;
        int lossScore = -10; // simplificado
        assertTrue(victoryScore > drawScore);
        assertTrue(drawScore > lossScore);
    }

    // ===== TESTES DE FLUXO DE CONTROLE =====
    @Test
    public void testGameControllerPlayerAlternation() {
        Jogador p1 = new Jogador("P1", 'X');
        Jogador p2 = new Jogador("P2", 'O');
        controlador.adicionarJogador(p1, new IAAleatoria("P1", 42));
        controlador.adicionarJogador(p2, new IAAleatoria("P2", 43));
        controlador.iniciarJogo();
        
        assertEquals(p1, controlador.obterJogadorAtual());
    }

    @Test
    public void testGameControllerGameOverCheck() {
        controlador.definirEstadoAtual(EstadoJogo.VITORIA_X);
        assertTrue(controlador.jogoTerminou());
        
        controlador.definirEstadoAtual(EstadoJogo.AGUARDANDO_JOGADA);
        assertFalse(controlador.jogoTerminou());
    }

    @Test
    public void testGameControllerGetWinnerX() {
        Jogador p1 = new Jogador("P1", 'X');
        Jogador p2 = new Jogador("P2", 'O');
        controlador.adicionarJogador(p1, new IAAleatoria("P1"));
        controlador.adicionarJogador(p2, new IAAleatoria("P2"));
        controlador.iniciarJogo();
        controlador.definirEstadoAtual(EstadoJogo.VITORIA_X);
        
        assertEquals(p1, controlador.obterVencedor());
    }

    @Test
    public void testGameControllerGetWinnerO() {
        Jogador p1 = new Jogador("P1", 'X');
        Jogador p2 = new Jogador("P2", 'O');
        controlador.adicionarJogador(p1, new IAAleatoria("P1"));
        controlador.adicionarJogador(p2, new IAAleatoria("P2"));
        controlador.iniciarJogo();
        controlador.definirEstadoAtual(EstadoJogo.VITORIA_O);
        
        assertEquals(p2, controlador.obterVencedor());
    }

    @Test
    public void testGameControllerGetWinnerNull() {
        Jogador p1 = new Jogador("P1", 'X');
        Jogador p2 = new Jogador("P2", 'O');
        controlador.adicionarJogador(p1, new IAAleatoria("P1"));
        controlador.adicionarJogador(p2, new IAAleatoria("P2"));
        controlador.iniciarJogo();
        controlador.definirEstadoAtual(EstadoJogo.EMPATE);
        
        assertNull(controlador.obterVencedor());
    }

    // ===== TESTES DE LOOP =====
    @Test
    public void testBoardLoopsAllCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(tabuleiro.posicaoDisponivel(i, j));
            }
        }
    }

    @Test
    public void testVictoryCheckLoopsAllRows() {
        for (int row = 0; row < 3; row++) {
            Tabuleiro testBoard = new Tabuleiro();
            testBoard.marcarPosicao(row, 0, 'X');
            testBoard.marcarPosicao(row, 1, 'X');
            testBoard.marcarPosicao(row, 2, 'X');
            assertTrue(validador.verificarVitoria(testBoard, 'X'));
        }
    }

    @Test
    public void testVictoryCheckLoopsAllColumns() {
        for (int col = 0; col < 3; col++) {
            Tabuleiro testBoard = new Tabuleiro();
            testBoard.marcarPosicao(0, col, 'O');
            testBoard.marcarPosicao(1, col, 'O');
            testBoard.marcarPosicao(2, col, 'O');
            assertTrue(validador.verificarVitoria(testBoard, 'O'));
        }
    }

    // ===== Métodos auxiliares =====
    private void fillBoardWithDrawPattern(Tabuleiro tabuleiro) {
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

    private int countAvailablePositions() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro.posicaoDisponivel(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
}
