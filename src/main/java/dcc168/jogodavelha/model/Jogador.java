package dcc168.jogodavelha.model;

/**
 * Representa um jogador do Jogo da Velha.
 */
public class Jogador {
    private String nome;
    private char simbolo; // 'X' ou 'O'

    /**
     * Constrói um jogador com nome e símbolo.
     *
     * @param nome nome do jogador
     * @param simbolo símbolo do jogador ('X' ou 'O')
     * @throws IllegalArgumentException se o símbolo for inválido
     */
    public Jogador(String nome, char simbolo) {
        if (simbolo != 'X' && simbolo != 'O') {
            throw new IllegalArgumentException("Símbolo inválido: " + simbolo);
        }
        this.nome = nome;
        this.simbolo = simbolo;
    }

    /**
     * Obtém o nome do jogador.
     *
     * @return nome do jogador
     */
    public String obterNome() {
        return nome;
    }

    /**
     * Obtém o símbolo do jogador.
     *
     * @return símbolo do jogador
     */
    public char obterSimbolo() {
        return simbolo;
    }

    /**
     * Verifica se um símbolo pertence a este jogador.
     *
     * @param simbolo símbolo a verificar
     * @return true se o símbolo é do jogador
     */
    public boolean ehMeuSimbolo(char simbolo) {
        return this.simbolo == simbolo;
    }

    @Override
    public String toString() {
        return nome + " (" + simbolo + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jogador jogador = (Jogador) obj;
        return simbolo == jogador.simbolo && nome.equals(jogador.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode() ^ Character.hashCode(simbolo);
    }
}
