# Classes de Equivalência - Jogo da Velha

## 1. Definição das Classes de Equivalência

### 1.1 Domínio de Entrada: Coordenadas (Linha e Coluna)

#### CE1 - Posições Válidas
- **Intervalo**: Linha ∈ {0, 1, 2}, Coluna ∈ {0, 1, 2}
- **Descrição**: Posições dentro do tabuleiro 3×3
- **Representantes**: (0,0), (1,1), (2,2)
- **Propriedade**: Deve permitir marcação

#### CE2 - Posições Inválidas (Línhas)
- **Intervalo**: Linha < 0 ou Linha > 2
- **Descrição**: Línhas fora do intervalo válido
- **Representantes**: (-1, 0), (3, 0), (10, 0)
- **Propriedade**: Deve lançar IllegalArgumentException

#### CE3 - Posições Inválidas (Colunas)
- **Intervalo**: Coluna < 0 ou Coluna > 2
- **Descrição**: Colunas fora do intervalo válido
- **Representantes**: (0, -1), (0, 3), (0, 10)
- **Propriedade**: Deve lançar IllegalArgumentException

### 1.2 Domínio de Entrada: Estado da Célula

#### CE4 - Célula Vazia
- **Símbolo**: ' ' (espaço)
- **Descrição**: Célula disponível para marcação
- **Representante**: Qualquer posição não marcada
- **Propriedade**: Deve permitir marcar com sucesso

#### CE5 - Célula Ocupada
- **Símbolo**: 'X' ou 'O'
- **Descrição**: Célula já contém um símbolo
- **Representante**: Posição previamente marcada
- **Propriedade**: Deve retornar false ou lançar erro

### 1.3 Domínio de Entrada: Símbolos

#### CE6 - Símbolo Válido X
- **Valor**: 'X'
- **Descrição**: Símbolo válido do jogo
- **Propriedade**: Aceitável em Jogador e Tabuleiro

#### CE7 - Símbolo Válido O
- **Valor**: 'O'
- **Descrição**: Símbolo válido do jogo
- **Propriedade**: Aceitável em Jogador e Tabuleiro

#### CE8 - Símbolo Inválido
- **Valor**: Qualquer char que não seja 'X' ou 'O'
- **Exemplos**: 'Z', '1', ' '
- **Propriedade**: Deve lançar IllegalArgumentException ao criar Jogador

### 1.4 Domínio de Saída: Estados do Jogo

#### CE9 - Jogo em Andamento
- **Estado**: ESTADO_EM_ANDAMENTO (0)
- **Descrição**: Partida não terminou
- **Propriedade**: Permite novas jogadas

#### CE10 - Vitória de X
- **Estado**: ESTADO_VITORIA_X (1)
- **Descrição**: Jogador X completou linha/coluna/diagonal
- **Propriedade**: Encerra o jogo

#### CE11 - Vitória de O
- **Estado**: ESTADO_VITORIA_O (2)
- **Descrição**: Jogador O completou linha/coluna/diagonal
- **Propriedade**: Encerra o jogo

#### CE12 - Empate
- **Estado**: ESTADO_EMPATE (3)
- **Descrição**: Tabuleiro cheio sem vencedor
- **Propriedade**: Encerra o jogo

### 1.5 Domínio: Padrões de Vitória

#### CE13 - Vitória Horizontal
- **Descrição**: Três símbolos iguais em uma linha
- **Representante**: (0,0), (0,1), (0,2) com mesmo símbolo
- **Propriedade**: Detecção correta em qualquer linha

#### CE14 - Vitória Vertical
- **Descrição**: Três símbolos iguais em uma coluna
- **Representante**: (0,0), (1,0), (2,0) com mesmo símbolo
- **Propriedade**: Detecção correta em qualquer coluna

#### CE15 - Vitória Diagonal Principal
- **Descrição**: Três símbolos iguais em diagonal (0,0)-(1,1)-(2,2)
- **Representante**: (0,0), (1,1), (2,2) com mesmo símbolo
- **Propriedade**: Detecção correta

#### CE16 - Vitória Diagonal Secundária
- **Descrição**: Três símbolos iguais em diagonal (0,2)-(1,1)-(2,0)
- **Representante**: (0,2), (1,1), (2,0) com mesmo símbolo
- **Propriedade**: Detecção correta

#### CE17 - Sem Vitória
- **Descrição**: Nenhum padrão de vitória formado
- **Propriedade**: verificarVitoria() retorna false para ambos símbolos

## 2. Análise de Valores Limite (Boundary Values)

### 2.1 Linha
- **Min válido**: 0
- **Max válido**: 2
- **Min inválido**: -1
- **Max inválido**: 3
- **Casos de teste**:
  - (0, 1): Mínimo válido
  - (2, 1): Máximo válido
  - (-1, 1): Abaixo do mínimo
  - (3, 1): Acima do máximo

### 2.2 Coluna
- **Min válido**: 0
- **Max válido**: 2
- **Min inválido**: -1
- **Max inválido**: 3
- **Casos de teste**:
  - (1, 0): Mínimo válido
  - (1, 2): Máximo válido
  - (1, -1): Abaixo do mínimo
  - (1, 3): Acima do máximo

### 2.3 Quantidade de Células
- **Mínimo**: 0 (tabuleiro vazio)
- **Máximo**: 9 (tabuleiro cheio)
- **Empate**: Exatamente 9 sem vencedor

## 3. Grafo de Causa-Efeito

### 3.1 Causas (Entradas)
```
C1: Coordenadas válidas
C2: Coordenadas inválidas
C3: Célula vazia
C4: Célula ocupada
C5: Sequência gera vitória horizontal
C6: Sequência gera vitória vertical
C7: Sequência gera vitória diagonal
C8: Tabuleiro cheio sem vitória
C9: Jogada por X
C10: Jogada por O
```

### 3.2 Efeitos (Saídas)
```
E1: Célula marcada com sucesso
E2: Exceção IllegalArgumentException
E3: Retorno false (posição ocupada)
E4: Estado muda para VITORIA_X
E5: Estado muda para VITORIA_O
E6: Estado muda para EMPATE
E7: Estado permanece EM_ANDAMENTO
E8: Jogador alterna
E9: Jogo encerra
```

### 3.3 Relacionamentos Causa-Efeito
```
C1 ∧ C3 → E1 ∧ E8 (ou E4, E5, E6)
C1 ∧ C4 → E3
C2 → E2
C5 ∧ C1 → E4 (se X) ou E5 (se O) ∧ E9
C6 ∧ C1 → E4 (se X) ou E5 (se O) ∧ E9
C7 ∧ C1 → E4 (se X) ou E5 (se O) ∧ E9
C8 ∧ C1 → E6 ∧ E9
```

## 4. Matriz de Decisão

| ID | Causa (Entrada) | Efeito (Saída) | Válido? |
|--|--|--|--|
| D1 | Coordenadas válidas ∧ Célula vazia | Marcação com sucesso | Sim |
| D2 | Coordenadas válidas ∧ Célula ocupada | Retorno false | Sim |
| D3 | Linha < 0 | IllegalArgumentException | Sim |
| D4 | Linha > 2 | IllegalArgumentException | Sim |
| D5 | Coluna < 0 | IllegalArgumentException | Sim |
| D6 | Coluna > 2 | IllegalArgumentException | Sim |
| D7 | Linha X vitória horizontal | ESTADO_VITORIA_X | Sim |
| D8 | Linha X vitória vertical | ESTADO_VITORIA_X | Sim |
| D9 | Linha X vitória diagonal | ESTADO_VITORIA_X | Sim |
| D10 | Linha O vitória horizontal | ESTADO_VITORIA_O | Sim |
| D11 | Tabuleiro cheio, sem vitória | ESTADO_EMPATE | Sim |
| D12 | Tabuleiro incompleto | ESTADO_EM_ANDAMENTO | Sim |

## 5. Resumo das Classes de Equivalência

| Classe | Tipo | Intervalo/Valor | Resultado |
|--|--|--|--|
| CE1 | Válida | Linha 0-2, Coluna 0-2 | Aceito |
| CE2 | Inválida | Linha < 0 ou > 2 | Exceção |
| CE3 | Inválida | Coluna < 0 ou > 2 | Exceção |
| CE4 | Válida | Célula vazia | Permite marcar |
| CE5 | Inválida | Célula ocupada | Rejeita |
| CE6-7 | Válida | Símbolo X ou O | Aceito |
| CE8 | Inválida | Outro símbolo | Exceção |
| CE9-12 | Válida | Estados do jogo | Transição correta |
| CE13-16 | Válida | Padrões vitória | Detecta corretamente |
| CE17 | Válida | Sem vitória | Retorna false |
