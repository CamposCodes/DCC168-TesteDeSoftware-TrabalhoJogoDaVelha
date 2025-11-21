package dcc168.jogodavelha.model;

/**
 * Valida o estado do jogo e verifica as regras do Jogo da Velha.
 */
public class ValidadorEstado {

    /**
     * Verifica se o jogador com o símbolo dado venceu.
     *
     * @param tabuleiro tabuleiro do jogo
     * @param simbolo símbolo do jogador ('X' ou 'O')
     * @return true se o jogador venceu, false caso contrário
     */
    public boolean verificarVitoria(Tabuleiro tabuleiro, char simbolo) {
        return verificarLinhas(tabuleiro, simbolo) || verificarColunas(tabuleiro, simbolo) || verificarDiagonais(tabuleiro, simbolo);
    }

    /**
     * Verifica vitória em linhas.
     *
     * @param tabuleiro tabuleiro do jogo
     * @param simbolo símbolo do jogador
     * @return true se há vitória em alguma linha
     */
    private boolean verificarLinhas(Tabuleiro tabuleiro, char simbolo) {
        for (int i = 0; i < tabuleiro.obterTamanho(); i++) {
            if (tabuleiro.obterSimbolo(i, 0) == simbolo && 
                tabuleiro.obterSimbolo(i, 1) == simbolo && 
                tabuleiro.obterSimbolo(i, 2) == simbolo) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica vitória em colunas.
     *
     * @param tabuleiro tabuleiro do jogo
     * @param simbolo símbolo do jogador
     * @return true se há vitória em alguma coluna
     */
    private boolean verificarColunas(Tabuleiro tabuleiro, char simbolo) {
        for (int j = 0; j < tabuleiro.obterTamanho(); j++) {
            if (tabuleiro.obterSimbolo(0, j) == simbolo && 
                tabuleiro.obterSimbolo(1, j) == simbolo && 
                tabuleiro.obterSimbolo(2, j) == simbolo) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica vitória em diagonais.
     *
     * @param tabuleiro tabuleiro do jogo
     * @param simbolo símbolo do jogador
     * @return true se há vitória em alguma diagonal
     */
    private boolean verificarDiagonais(Tabuleiro tabuleiro, char simbolo) {
        // Diagonal principal
        if (tabuleiro.obterSimbolo(0, 0) == simbolo && 
            tabuleiro.obterSimbolo(1, 1) == simbolo && 
            tabuleiro.obterSimbolo(2, 2) == simbolo) {
            return true;
        }
        // Diagonal secundária
        if (tabuleiro.obterSimbolo(0, 2) == simbolo && 
            tabuleiro.obterSimbolo(1, 1) == simbolo && 
            tabuleiro.obterSimbolo(2, 0) == simbolo) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se houve empate (tabuleiro cheio e sem vencedor).
     *
     * @param tabuleiro tabuleiro do jogo
     * @return true se houve empate
     */
    public boolean verificarEmpate(Tabuleiro tabuleiro) {
        return tabuleiro.estaCheia() && !verificarVitoria(tabuleiro, 'X') && !verificarVitoria(tabuleiro, 'O');
    }

    /**
     * Verifica a integridade do estado do jogo.
     * Valida que não há múltiplos vencedores.
     *
     * @param tabuleiro tabuleiro do jogo
     * @return true se o estado é válido
     */
    public boolean ehEstadoValido(Tabuleiro tabuleiro) {
        boolean xVence = verificarVitoria(tabuleiro, 'X');
        boolean oVence = verificarVitoria(tabuleiro, 'O');
        
        // Não pode haver dois vencedores simultaneamente
        if (xVence && oVence) {
            return false;
        }
        
        // Se X venceu, o jogo deveria ter parado
        // Se O venceu, o jogo deveria ter parado
        // Se há empate, o tabuleiro deve estar cheio
        
        return true;
    }
}
