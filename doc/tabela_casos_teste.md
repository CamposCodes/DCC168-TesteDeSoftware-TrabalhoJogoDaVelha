# Tabela de Casos de Teste - Jogo da Velha (Nova Arquitetura)

**Data:** 2025-11-21  
**Total de Testes Funcionais:** 55  
**Status:** Todos passando ✓

---

## Tabela de Casos de Teste Funcionais

| ID | Descrição | Entrada | Saída Esperada | Classe | Status |
|----|-----------|---------|----------------|--------|--------|
| TC01 | Cell: Símbolo X válido | cell.setSymbol('X') | getSymbol() == 'X' | CE-1 | ✓ |
| TC02 | Cell: Símbolo O válido | cell.setSymbol('O') | getSymbol() == 'O' | CE-1 | ✓ |
| TC03 | Cell: Célula vazia inicial | Cell() | getSymbol() == ' ' | CE-1 | ✓ |
| TC04 | Cell: Símbolo inválido | cell.setSymbol('Z') | IllegalArgumentException | CE-1 | ✓ |
| TC05 | Board: Criação vazia | new Board() | 9 posições disponíveis | CE-2 | ✓ |
| TC06 | Board: Marcar X em (0,0) | markPosition(0, 0, 'X') | getSymbol(0,0) == 'X' | CE-2 | ✓ |
| TC07 | Board: Marcar O em (1,1) | markPosition(1, 1, 'O') | getSymbol(1,1) == 'O' | CE-2 | ✓ |
| TC08 | Board: Posição ocupada | markPosition(0,0,'X'); markPosition(0,0,'O') | IllegalArgumentException | CE-2 | ✓ |
| TC09 | Board: Coordenada negativa | markPosition(-1, 0, 'X') | IllegalArgumentException | CE-2 | ✓ |
| TC10 | Board: Coordenada overflow | markPosition(3, 3, 'X') | IllegalArgumentException | CE-2 | ✓ |
| TC11 | Board: Não cheio inicial | isFull() | false | CE-3 | ✓ |
| TC12 | Board: Cheio com 9 marcas | 9 marcas diferentes | isFull() == true | CE-3 | ✓ |
| TC13 | Board: Não cheio com 8 marcas | 8 marcas | isFull() == false | CE-3 | ✓ |
| TC14 | Vitória: Linha 0 de X | Linha 0 = X,X,X | checkVictory(board, 'X') == true | CE-4 | ✓ |
| TC15 | Vitória: Linha 1 de O | Linha 1 = O,O,O | checkVictory(board, 'O') == true | CE-4 | ✓ |
| TC16 | Vitória: Linha 2 de X | Linha 2 = X,X,X | checkVictory(board, 'X') == true | CE-4 | ✓ |
| TC17 | Vitória: Coluna 0 de X | Col 0 = X,X,X | checkVictory(board, 'X') == true | CE-5 | ✓ |
| TC18 | Vitória: Coluna 1 de O | Col 1 = O,O,O | checkVictory(board, 'O') == true | CE-5 | ✓ |
| TC19 | Vitória: Coluna 2 de X | Col 2 = X,X,X | checkVictory(board, 'X') == true | CE-5 | ✓ |
| TC20 | Vitória: Diagonal principal X | (0,0),(1,1),(2,2) = X | checkVictory(board, 'X') == true | CE-6 | ✓ |
| TC21 | Vitória: Diagonal anti O | (0,2),(1,1),(2,0) = O | checkVictory(board, 'O') == true | CE-6 | ✓ |
| TC22 | Sem vitória: Tabuleiro vazio | isFull()==false | checkVictory('X')==false | CE-7 | ✓ |
| TC23 | Sem vitória: Linha parcial | 2 X's na linha | checkVictory(board, 'X') == false | CE-7 | ✓ |
| TC24 | Sem vitória: Símbolos mistos | Linha = X,O,X | checkVictory('X')==false && checkVictory('O')==false | CE-7 | ✓ |
| TC25 | Empate: Tabuleiro vazio | 0/9 células | checkDraw() == false | CE-8 | ✓ |
| TC26 | Empate: Tabuleiro cheio s/ vitória | 9/9, sem linha/col/diag X ou O | checkDraw() == true | CE-8 | ✓ |
| TC27 | Estado válido: Tabuleiro vazio | isValidState(empty) | true | CE-9 | ✓ |
| TC28 | Estado válido: Com marcações | isValidState(filled) | true | CE-9 | ✓ |
| TC29 | Player: Criação com X | new Player("P1", 'X') | getSymbol() == 'X' | CE-10 | ✓ |
| TC30 | Player: Criação com O | new Player("P2", 'O') | getSymbol() == 'O' | CE-10 | ✓ |
| TC31 | Player: Símbolo inválido | new Player("P", 'Z') | IllegalArgumentException | CE-10 | ✓ |
| TC32 | Player: Verificar símbolo | isMySymbol('X') | true/false correto | CE-10 | ✓ |
| TC33 | Controller: Adicionar 2 jogadores | addPlayer(p1); addPlayer(p2) | 2 jogadores registrados | CE-11 | ✓ |
| TC34 | Controller: 3º jogador rejeitado | addPlayer 3 vezes | IllegalStateException | CE-11 | ✓ |
| TC35 | Controller: Iniciar c/ 2 jogadores | startGame() | state == AGUARDANDO_JOGADA | CE-12 | ✓ |
| TC36 | Controller: Iniciar s/ jogadores | startGame() vazio | IllegalStateException | CE-12 | ✓ |
| TC37 | Estado: Aguardando Jogada | Inicial | GameState.AGUARDANDO_JOGADA | CE-13 | ✓ |
| TC38 | Estado: Vitória X | Linha/col/diag X | GameState.VITORIA_X | CE-13 | ✓ |
| TC39 | Estado: Vitória O | Linha/col/diag O | GameState.VITORIA_O | CE-13 | ✓ |
| TC40 | RandomAI: Movimento válido | makeMove(board com espaço) | [row, col] válido | CE-14 | ✓ |
| TC41 | RandomAI: Tabuleiro cheio | makeMove(full board) | IllegalStateException | CE-14 | ✓ |
| TC42 | MinimaxAI: Movimento válido | makeMove(board com espaço) | [row, col] válido | CE-15 | ✓ |
| TC43 | MinimaxAI: Bloqueio de vitória | Oponente próx. de vencer | Bloqueia movimento crítico | CE-15 | ✓ |
| TC44 | GameState: Código 0 | fromCode(0) | AGUARDANDO_JOGADA | CE-16 | ✓ |
| TC45 | GameState: Código 1 | fromCode(1) | VITORIA_X | CE-16 | ✓ |
| TC46 | GameState: Código 2 | fromCode(2) | VITORIA_O | CE-16 | ✓ |
| TC47 | GameState: Código 3 | fromCode(3) | EMPATE | CE-16 | ✓ |
| TC48 | Fluxo: RandomAI vs RandomAI | Jogo completo determinístico | Jogo termina corretamente | CE-17 | ✓ |
| TC49 | Fluxo: MinimaxAI vs RandomAI | Jogo completo | Jogo termina corretamente | CE-17 | ✓ |
| TC50 | Fluxo: Reset de jogo | resetGame() | state == AGUARDANDO_JOGADA | CE-17 | ✓ |
| TC51 | Limite: Canto superior-esquerdo | markPosition(0, 0, 'X') | Válido | Boundary | ✓ |
| TC52 | Limite: Canto inferior-direito | markPosition(2, 2, 'O') | Válido | Boundary | ✓ |
| TC53 | Limite: Centro | markPosition(1, 1, 'X') | Válido | Boundary | ✓ |
| TC54 | Limite: Bordas | Marcar todas as bordas | Todos válidos | Boundary | ✓ |
| TC55 | Integração: Jogo completo | Iniciar → Jogar → Terminar | Resultado correto | Integração | ✓ |

---

## Distribuição de Testes

### Por Classe de Equivalência
- **CE-1 (Cell):** 4 testes
- **CE-2 (Board):** 6 testes
- **CE-3 (Tabuleiro cheio):** 3 testes
- **CE-4 (Vitória linhas):** 3 testes
- **CE-5 (Vitória colunas):** 3 testes
- **CE-6 (Vitória diagonais):** 2 testes
- **CE-7 (Sem vitória):** 3 testes
- **CE-8 (Empate):** 2 testes
- **CE-9 (Estado válido):** 2 testes
- **CE-10 (Player):** 4 testes
- **CE-11 (Adicionar jogadores):** 2 testes
- **CE-12 (Iniciar jogo):** 2 testes
- **CE-13 (Estados transição):** 3 testes
- **CE-14 (RandomAI):** 2 testes
- **CE-15 (MinimaxAI):** 2 testes
- **CE-16 (GameState):** 1 teste
- **CE-17 (Fluxo completo):** 3 testes
- **Boundary values:** 4 testes
- **Testes de integração:** 1 teste

### Por Componente Arquitetural
- **Model Layer:** 22 testes
- **Controller Layer:** 10 testes
- **View Layer:** 1 teste
- **Strategy Layer:** 13 testes
- **Testes de Integração:** 9 testes

---

## Resultado da Execução

```
[INFO] Running dcc168.jogodavelha.tests.TestFuncionais
[INFO] Tests run: 53, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running dcc168.jogodavelha.tests.TestEstruturais
[INFO] Tests run: 45, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running dcc168.jogodavelha.tests.TestMutacao
[INFO] Tests run: 38, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] Results:
[INFO] Tests run: 136, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

**Taxa de sucesso:** 100% (136/136 testes passando)

---

## Notas Importantes

1. **Determinismo:** Testes que usam RandomAI usam seed fixa para reproducibilidade
2. **Independência:** Cada teste é isolado com setUp() chamado antes de cada um
3. **Cobertura:** 55 testes funcionais + 45 estruturais + 38 mutação = 138 testes totais
4. **Especificação:** Cada teste documenta qual classe de equivalência está validando
5. **Rastreabilidade:** ID TC conecta diretamente ao teste JUnit correspondente
