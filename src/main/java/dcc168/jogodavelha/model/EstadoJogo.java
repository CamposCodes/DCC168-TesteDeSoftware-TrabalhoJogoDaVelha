package dcc168.jogodavelha.model;

/**
 * Enumeração dos possíveis estados do jogo.
 */
public enum EstadoJogo {
    AGUARDANDO_JOGADA(0, "Aguardando Jogada"),
    VITORIA_X(1, "Vitória de X"),
    VITORIA_O(2, "Vitória de O"),
    EMPATE(3, "Empate"),
    FIM(4, "Jogo Finalizado");

    private final int codigo;
    private final String descricao;

    /**
     * Constrói um estado de jogo com código e descrição.
     *
     * @param codigo código do estado
     * @param descricao descrição do estado
     */
    EstadoJogo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Obtém o código do estado.
     *
     * @return código do estado
     */
    public int obterCodigo() {
        return codigo;
    }

    /**
     * Obtém a descrição do estado.
     *
     * @return descrição do estado
     */
    public String obterDescricao() {
        return descricao;
    }

    /**
     * Obtém o estado a partir de seu código.
     *
     * @param codigo código do estado
     * @return estado correspondente
     * @throws IllegalArgumentException se código inválido
     */
    public static EstadoJogo doCodigo(int codigo) {
        for (EstadoJogo estado : EstadoJogo.values()) {
            if (estado.codigo == codigo) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Código de estado inválido: " + codigo);
    }

    @Override
    public String toString() {
        return descricao;
    }
}
