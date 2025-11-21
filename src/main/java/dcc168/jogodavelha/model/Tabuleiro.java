package dcc168.jogodavelha.model;

/**
 * Representa o tabuleiro do Jogo da Velha (3x3).
 * Gerencia as células e suas marcações.
 */
public class Tabuleiro {
    private static final int TAMANHO = 3;
    private Celula[][] celulas;

    /**
     * Constrói um tabuleiro vazio.
     */
    public Tabuleiro() {
        celulas = new Celula[TAMANHO][TAMANHO];
        inicializarTabuleiro();
    }

    /**
     * Inicializa o tabuleiro com células vazias.
     */
    private void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                celulas[i][j] = new Celula();
            }
        }
    }

    /**
     * Marca uma posição no tabuleiro com o símbolo do jogador.
     *
     * @param linha linha da posição (0-2)
     * @param coluna coluna da posição (0-2)
     * @param simbolo símbolo do jogador ('X' ou 'O')
     * @throws IllegalArgumentException se coordenadas inválidas ou posição já ocupada
     */
    public void marcarPosicao(int linha, int coluna, char simbolo) {
        validarCoordenadas(linha, coluna);
        if (!celulas[linha][coluna].estaVazia()) {
            throw new IllegalArgumentException("Posição já ocupada!");
        }
        celulas[linha][coluna].definirSimbolo(simbolo);
    }

    /**
     * Verifica se uma posição está disponível.
     *
     * @param linha linha da posição (0-2)
     * @param coluna coluna da posição (0-2)
     * @return true se a posição está vazia, false caso contrário
     * @throws IllegalArgumentException se coordenadas inválidas
     */
    public boolean posicaoDisponivel(int linha, int coluna) {
        validarCoordenadas(linha, coluna);
        return celulas[linha][coluna].estaVazia();
    }

    /**
     * Obtém o símbolo em uma posição do tabuleiro.
     *
     * @param linha linha da posição (0-2)
     * @param coluna coluna da posição (0-2)
     * @return símbolo na posição
     * @throws IllegalArgumentException se coordenadas inválidas
     */
    public char obterSimbolo(int linha, int coluna) {
        validarCoordenadas(linha, coluna);
        return celulas[linha][coluna].obterSimbolo();
    }

    /**
     * Verifica se o tabuleiro está cheio.
     *
     * @return true se todas as posições estão ocupadas, false caso contrário
     */
    public boolean estaCheia() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (celulas[i][j].estaVazia()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Obtém o tamanho do tabuleiro.
     *
     * @return tamanho do tabuleiro (3)
     */
    public int obterTamanho() {
        return TAMANHO;
    }

    /**
     * Limpa o tabuleiro (reseta todas as células).
     */
    public void reiniciar() {
        inicializarTabuleiro();
    }

    /**
     * Valida se as coordenadas estão dentro dos limites do tabuleiro.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @throws IllegalArgumentException se coordenadas estão fora dos limites
     */
    private void validarCoordenadas(int linha, int coluna) {
        if (linha < 0 || linha >= TAMANHO || coluna < 0 || coluna >= TAMANHO) {
            throw new IllegalArgumentException("Coordenadas inválidas: (" + linha + ", " + coluna + ")");
        }
    }

    /**
     * Obtém uma cópia da célula em uma posição.
     *
     * @param linha linha da posição
     * @param coluna coluna da posição
     * @return célula na posição
     * @throws IllegalArgumentException se coordenadas inválidas
     */
    public Celula obterCelula(int linha, int coluna) {
        validarCoordenadas(linha, coluna);
        return celulas[linha][coluna];
    }

    /**
     * Obtém todas as células do tabuleiro.
     *
     * @return matriz de células
     */
    public Celula[][] obterCelulas() {
        return celulas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                sb.append(" ").append(celulas[i][j].obterSimbolo()).append(" ");
                if (j < TAMANHO - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i < TAMANHO - 1) {
                sb.append("-----------\n");
            }
        }
        return sb.toString();
    }
}
