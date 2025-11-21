# Classes de Equivalência - Jogo da Velha (Nova Arquitetura MVC)

## Documento de Teste Funcional
**Versão:** 2.0 (MVC + Strategy Pattern)  
**Data:** 2025-11-21  
**Arquitetura:** Model-View-Controller com Strategy Pattern

---

## 1. Visão Geral

Este documento especifica as 17 classes de equivalência (partições de equivalência) identificadas para o Jogo da Velha com a nova arquitetura. As classes de equivalência representam grupos de entradas que devem ser tratadas de forma semelhante pelo programa.

---

## 2. Classes de Equivalência Identificadas

### **CE-1: Valores Válidos de Símbolos de Célula**
- **Partição 1a:** Símbolo 'X' → Válido
- **Partição 1b:** Símbolo 'O' → Válido  
- **Partição 1c:** Símbolo ' ' (vazio) → Válido
- **Partição 1d:** Símbolos inválidos (A-Z exceto X e O) → Inválido
- **Exemplos de teste:** testCellValidSymbolX, testCellValidSymbolO, testCellEmptySymbol, testCellInvalidSymbol

### **CE-2: Operações de Tabuleiro (Board)**
- **Partição 2a:** Marcar posição válida com X → Sucesso
- **Partição 2b:** Marcar posição válida com O → Sucesso
- **Partição 2c:** Posição ocupada → Falha (exceção)
- **Exemplos de teste:** testBoardMarkPositionX, testBoardMarkPositionO, testBoardMarkOccupiedPosition

### **CE-3: Verificação de Tabuleiro Cheio**
- **Partição 3a:** Tabuleiro vazio (0 células marcadas) → Não cheio
- **Partição 3b:** Tabuleiro parcialmente preenchido (1-8 células) → Não cheio
- **Partição 3c:** Tabuleiro cheio (9 células) → Cheio
- **Exemplos de teste:** testBoardNotFullInitial, testBoardFullAfterNineMarks, testBoardNotFullWithEightMarks

### **CE-4: Vitória em Linhas (Rows)**
- **Partição 4a:** Três X's consecutivos em linha 0 → Vitória X
- **Partição 4b:** Três O's consecutivos em linha 1 → Vitória O
- **Partição 4c:** Três X's consecutivos em linha 2 → Vitória X
- **Exemplos de teste:** testVictoryXFirstRow, testVictoryOSecondRow, testVictoryXThirdRow

### **CE-5: Vitória em Colunas (Columns)**
- **Partição 5a:** Três X's consecutivos em coluna 0 → Vitória X
- **Partição 5b:** Três O's consecutivos em coluna 1 → Vitória O
- **Partição 5c:** Três X's consecutivos em coluna 2 → Vitória X
- **Exemplos de teste:** testVictoryXFirstColumn, testVictoryOSecondColumn, testVictoryXThirdColumn

### **CE-6: Vitória em Diagonais (Diagonals)**
- **Partição 6a:** Três X's em diagonal principal (0,0 → 1,1 → 2,2) → Vitória X
- **Partição 6b:** Três O's em diagonal secundária (0,2 → 1,1 → 2,0) → Vitória O
- **Exemplos de teste:** testVictoryMainDiagonalX, testVictoryAntiDiagonalO

### **CE-7: Ausência de Vitória**
- **Partição 7a:** Tabuleiro vazio → Sem vitória
- **Partição 7b:** Linha parcial (menos de 3 símbolos iguais) → Sem vitória
- **Partição 7c:** Símbolos mistos (X e O) na mesma linha → Sem vitória
- **Exemplos de teste:** testNoVictoryEmptyBoard, testNoVictoryPartialRow, testNoVictoryMixedSymbols

### **CE-8: Detecção de Empate (Draw)**
- **Partição 8a:** Tabuleiro vazio → Sem empate
- **Partição 8b:** Tabuleiro parcialmente preenchido → Sem empate
- **Partição 8c:** Tabuleiro cheio sem vitória → Empate
- **Exemplos de teste:** testNoDraw, testDrawDetection

### **CE-9: Validação de Estado do Jogo**
- **Partição 9a:** Tabuleiro vazio → Estado válido
- **Partição 9b:** Tabuleiro com marcações válidas → Estado válido
- **Partição 9c:** Múltiplos vencedores (teoricamente impossível) → Estado inválido
- **Exemplos de teste:** testValidStateEmptyBoard, testValidStateWithMarks

### **CE-10: Criação de Jogador com Símbolo Válido**
- **Partição 10a:** Jogador com símbolo 'X' → Válido
- **Partição 10b:** Jogador com símbolo 'O' → Válido
- **Partição 10c:** Jogador com símbolo inválido → Inválido (exceção)
- **Exemplos de teste:** testPlayerCreationX, testPlayerCreationO, testPlayerInvalidSymbol

### **CE-11: Gerenciamento de Jogadores no Controller**
- **Partição 11a:** Adicionar 1 jogador → 1 jogador registrado
- **Partição 11b:** Adicionar 2 jogadores → 2 jogadores registrados
- **Partição 11c:** Tentar adicionar 3ª jogador → Exceção (máximo 2)
- **Exemplos de teste:** testAddTwoPlayers, testAddThreePlayers

### **CE-12: Inicialização de Jogo**
- **Partição 12a:** Iniciar com 2 jogadores → Sucesso, estado AGUARDANDO_JOGADA
- **Partição 12b:** Iniciar sem jogadores → Falha (exceção)
- **Exemplos de teste:** testStartGameWithTwoPlayers, testStartGameWithoutPlayers

### **CE-13: Transições de Estado do Jogo**
- **Partição 13a:** Estado inicial → AGUARDANDO_JOGADA
- **Partição 13b:** Vitória de X → VITORIA_X
- **Partição 13c:** Vitória de O → VITORIA_O
- **Partição 13d:** Empate → EMPATE
- **Exemplos de teste:** testGameStateTransitionToVictoryX, testGameStateTransitionToVictoryO

### **CE-14: Estratégia RandomAI**
- **Partição 14a:** Tabuleiro com posições disponíveis → Retorna movimento válido
- **Partição 14b:** Tabuleiro cheio → Lança exceção (sem movimentos disponíveis)
- **Exemplos de teste:** testRandomAIMakeValidMove, testRandomAINoAvailableMoves

### **CE-15: Estratégia MinimaxAI**
- **Partição 15a:** Movimento válido disponível → Retorna movimento ótimo
- **Partição 15b:** Posição de ameaça do oponente → Bloqueia vitória do adversário
- **Exemplos de teste:** testMinimaxAIMakesValidMove, testMinimaxAIBlocksOpponentVictory

### **CE-16: Enumeração GameState**
- **Partição 16a:** Código 0 → AGUARDANDO_JOGADA
- **Partição 16b:** Código 1 → VITORIA_X
- **Partição 16c:** Código 2 → VITORIA_O
- **Partição 16d:** Código 3 → EMPATE
- **Exemplos de teste:** testGameStateValues

### **CE-17: Fluxo Completo do Jogo**
- **Partição 17a:** Jogo completo RandomAI vs RandomAI → Resultado determinístico
- **Partição 17b:** Jogo completo MinimaxAI vs RandomAI → Minimax não perde
- **Partição 17c:** Reset de jogo → Retorna ao estado inicial
- **Exemplos de teste:** testCompleteGameFlowRandomVsRandom, testCompleteGameFlowMinimaxVsRandom, testResetGameRestartsState

---

## 3. Análise de Valores Limite (Boundary Value Analysis)

### Coordenadas do Tabuleiro
- **Valor mínimo válido:** 0 (linha 0, coluna 0)
- **Valor máximo válido:** 2 (linha 2, coluna 2)
- **Valor fora do intervalo (abaixo):** -1
- **Valor fora do intervalo (acima):** 3

**Testes de limite:** 
- testBoardInvalidCoordinatesNegative
- testBoardInvalidCoordinatesOverflow
- testBoundaryConditionBoardCorners
- testBoundaryConditionBoardCenter
- testBoundaryConditionBoardEdges

### Contagem de Células Marcadas
- **Mínimo (vazio):** 0 células
- **Máximo (cheio):** 9 células
- **Transição crítica:** 8 → 9 (último movimento antes de cheio ou vitória)

**Testes de limite:**
- testBoardNotFullInitial
- testBoardFullAfterNineMarks
- testBoardAllPositionsCountedWhenFull

---

## 4. Casos de Teste Representativos

| ID | Classe | Entrada | Saída Esperada | Teste JUnit |
|----|--------|---------|----------------|-------------|
| CE1-1 | CE-1 | setSymbol('X') | getSymbol() == 'X' | testCellValidSymbolX |
| CE1-2 | CE-1 | setSymbol('Z') | IllegalArgumentException | testCellInvalidSymbol |
| CE3-1 | CE-3 | Tabuleiro 0/9 células | isFull() == false | testBoardNotFullInitial |
| CE3-2 | CE-3 | Tabuleiro 9/9 células | isFull() == true | testBoardFullAfterNineMarks |
| CE4-1 | CE-4 | [X][X][X] linha 0 | checkVictory('X') == true | testVictoryXFirstRow |
| CE6-1 | CE-6 | Diagonal principal X | checkVictory('X') == true | testVictoryMainDiagonalX |
| CE8-1 | CE-8 | Tabuleiro cheio, sem vitória | checkDraw() == true | testDrawDetection |
| CE10-1 | CE-10 | Player("P1", 'X') | getSymbol() == 'X' | testPlayerCreationX |
| CE11-1 | CE-11 | addPlayer 2 jogadores | size() == 2 | testAddTwoPlayers |
| CE14-1 | CE-14 | Posição disponível | makeMove() valido | testRandomAIMakeValidMove |

---

## 5. Matriz de Cobertura

Total de classes de equivalência: **17**  
Total de partições: **59**  
Total de casos de teste funcionais: **55**

**Cobertura de equivalência:**
- CE-1 (Símbolos): 4 testes
- CE-2 (Board ops): 6 testes
- CE-3 (Board full): 3 testes
- CE-4 (Linhas): 3 testes
- CE-5 (Colunas): 3 testes
- CE-6 (Diagonais): 2 testes
- CE-7 (Sem vitória): 3 testes
- CE-8 (Empate): 2 testes
- CE-9 (Estado válido): 2 testes
- CE-10 (Jogador): 4 testes
- CE-11 (Adicionar jogadores): 2 testes
- CE-12 (Iniciar jogo): 2 testes
- CE-13 (Estados): 3 testes
- CE-14 (RandomAI): 2 testes
- CE-15 (MinimaxAI): 2 testes
- CE-16 (GameState enum): 1 teste
- CE-17 (Fluxo completo): 3 testes

---

## 6. Conclusão

As 17 classes de equivalência identificadas cobrem todas as funcionalidades críticas do Jogo da Velha na nova arquitetura MVC:

- **Validação de entrada:** Símbolos, coordenadas
- **Lógica de negócio:** Vitória, empate, validação de estado
- **Componentes arquiteturais:** Model, Controller, View, Strategy
- **Fluxos completos:** Início, jogadas, fim de jogo

Com 55 testes funcionais distribuídos entre essas classes, o sistema atinge uma cobertura robusta de todos os casos de uso do jogo.
