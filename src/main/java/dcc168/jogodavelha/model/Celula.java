package dcc168.jogodavelha.model;

/**
 * Representa uma célula individual do tabuleiro.
 * Cada célula pode conter 'X', 'O' ou estar vazia.
 */
public class Celula {
    private char simbolo; // 'X', 'O', ou ' '

    /**
     * Constrói uma célula vazia.
     */
    public Celula() {
        this.simbolo = ' ';
    }

    /**
     * Obtém o símbolo da célula.
     *
     * @return símbolo da célula
     */
    public char obterSimbolo() {
        return simbolo;
    }

    /**
     * Define o símbolo da célula.
     *
     * @param simbolo símbolo a ser atribuído ('X' ou 'O')
     * @throws IllegalArgumentException se o símbolo não for válido
     */
    public void definirSimbolo(char simbolo) {
        if (simbolo != 'X' && simbolo != 'O' && simbolo != ' ') {
            throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
        }
        this.simbolo = simbolo;
    }

    /**
     * Verifica se a célula está vazia.
     *
     * @return true se a célula está vazia, false caso contrário
     */
    public boolean estaVazia() {
        return simbolo == ' ';
    }

    /**
     * Limpa a célula (deixa vazia).
     */
    public void limpar() {
        this.simbolo = ' ';
    }

    @Override
    public String toString() {
        return String.valueOf(simbolo);
    }
}
