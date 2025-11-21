# Casos de Teste Funcionais - Jogo da Velha

## Tabela de Casos de Teste Funcionais (Parte I)

### 1. Testes de Posições Válidas

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC01 | Marcar posição (0,0) | linha=0, coluna=0, símbolo='X' | true, célula='X' | CE1, CE4 |
| TC02 | Marcar posição (1,1) centro | linha=1, coluna=1, símbolo='X' | true, célula='X' | CE1, CE4 |
| TC03 | Marcar posição (2,2) | linha=2, coluna=2, símbolo='X' | true, célula='X' | CE1, CE4 |
| TC04 | Marcar todas 9 posições | Várias (0,0) a (2,2) | Todos true | CE1, CE4 |

### 2. Testes de Posições Inválidas - Linha Negativa

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC05 | Linha = -1 | linha=-1, coluna=0, símbolo='X' | IllegalArgumentException | CE2 |
| TC09 | Linha = -10 | linha=-10, coluna=0, símbolo='X' | IllegalArgumentException | CE2 |

### 3. Testes de Posições Inválidas - Coluna Negativa

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC06 | Coluna = -1 | linha=0, coluna=-1, símbolo='X' | IllegalArgumentException | CE3 |
| TC10 | Coluna = -10 | linha=0, coluna=-10, símbolo='X' | IllegalArgumentException | CE3 |

### 4. Testes de Posições Inválidas - Linha Acima do Limite

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC07 | Linha = 3 | linha=3, coluna=0, símbolo='X' | IllegalArgumentException | CE2 |
| TC11 | Linha = 10 | linha=10, coluna=0, símbolo='X' | IllegalArgumentException | CE2 |

### 5. Testes de Posições Inválidas - Coluna Acima do Limite

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC08 | Coluna = 3 | linha=0, coluna=3, símbolo='X' | IllegalArgumentException | CE3 |
| TC12 | Coluna = 10 | linha=0, coluna=10, símbolo='X' | IllegalArgumentException | CE3 |

### 6. Testes de Célula Ocupada

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC13 | Marcar célula ocupada | (0,0) com X, depois (0,0) com O | false | CE5 |
| TC14 | Múltiplas tentativas na mesma célula | (1,1) com X, depois X e O | false, false | CE5 |

### 7. Testes de Vitória Horizontal

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC15 | Vitória linha 0 | (0,0)=X, (0,1)=X, (0,2)=X | true (vitória X) | CE13 |
| TC16 | Vitória linha 1 | (1,0)=O, (1,1)=O, (1,2)=O | true (vitória O) | CE13 |
| TC17 | Vitória linha 2 | (2,0)=X, (2,1)=X, (2,2)=X | true (vitória X) | CE13 |

### 8. Testes de Vitória Vertical

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC18 | Vitória coluna 0 | (0,0)=X, (1,0)=X, (2,0)=X | true (vitória X) | CE14 |
| TC19 | Vitória coluna 1 | (0,1)=O, (1,1)=O, (2,1)=O | true (vitória O) | CE14 |
| TC20 | Vitória coluna 2 | (0,2)=X, (1,2)=X, (2,2)=X | true (vitória X) | CE14 |

### 9. Testes de Vitória Diagonal

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC21 | Vitória diagonal principal | (0,0)=X, (1,1)=X, (2,2)=X | true (vitória X) | CE15 |
| TC22 | Vitória diagonal secundária | (0,2)=O, (1,1)=O, (2,0)=O | true (vitória O) | CE16 |
| TC23 | Sem vitória 2 em linha | (0,0)=X, (0,1)=X, (0,2)=O | false | CE17 |
| TC24 | Sem vitória tabuleiro vazio | Tabuleiro vazio | false | CE17 |

### 10. Testes de Empate

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC25 | Empate padrão | X O X / O X O / O X X | true (empate) | CE12 |
| TC26 | Empate outro padrão | X X O / O O X / X O X | true (empate) | CE12 |
| TC27 | Não empate tabuleiro parcial | Apenas 2 movimentos | false | CE9 |

### 11. Testes de Jogo (JogoDaVelha)

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC28 | Primeira jogada válida | fazerJogada(0,0) | true, estado EM_ANDAMENTO | CE1, CE4, CE9 |
| TC29 | Sequência de jogadas | 4 jogadas válidas alternadas | Todos true | CE1, CE4, CE9 |
| TC30 | Jogada em posição ocupada | Mesma posição 2x | false | CE5 |
| TC31 | Vitória X no jogo | 5 jogadas levando X a vitória | ESTADO_VITORIA_X | CE10, CE13 |
| TC32 | Vitória O no jogo | 5 jogadas levando O a vitória | ESTADO_VITORIA_O | CE11, CE14 |
| TC33 | Empate no jogo | 9 jogadas sem vencedor | ESTADO_EMPATE | CE12 |
| TC34 | Não permitir jogada após vitória | Tentar jogar após vitória | false | CE10 |
| TC35 | Não permitir jogada após empate | Tentar jogar após empate | false | CE12 |

### 12. Testes de Reinicialização

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC36 | Reiniciar após vitória | reiniciar() após vitória | estado EM_ANDAMENTO | CE9 |
| TC37 | Continuar após reinício | reiniciar() e nova jogada | true, permitido | CE9 |

### 13. Testes de Validação de Integridade

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC38 | Integridade estado válido | 1X, 1O | true | - |
| TC39 | Integridade tabuleiro vazio | 0X, 0O | true | - |
| TC40 | Múltiplos vencedores | Linha X + Linha O | false | - |

### 14. Testes de Valores Limite

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC41 | Limite inferior linha 0 | (0,1) | true | CE1 |
| TC42 | Limite superior linha 2 | (2,1) | true | CE1 |
| TC43 | Limite inferior coluna 0 | (1,0) | true | CE1 |
| TC44 | Limite superior coluna 2 | (1,2) | true | CE1 |
| TC45 | Fora limite linha -1 | (-1,1) | IllegalArgumentException | CE2 |
| TC46 | Fora limite linha 3 | (3,1) | IllegalArgumentException | CE2 |
| TC47 | Fora limite coluna -1 | (1,-1) | IllegalArgumentException | CE3 |
| TC48 | Fora limite coluna 3 | (1,3) | IllegalArgumentException | CE3 |

### 15. Testes de Posição Disponível

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC49 | Posição vazia disponível | (0,0) vazio | true | CE4 |
| TC50 | Posição ocupada indisponível | (1,1) com X | false | CE5 |

### 16. Testes de Símbolo

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC51 | Jogador com símbolo X | Jogador("Teste", 'X') | símbolo='X' | CE6 |
| TC52 | Jogador com símbolo O | Jogador("Teste", 'O') | símbolo='O' | CE7 |
| TC53 | Símbolo inválido | Jogador("Teste", 'Z') | IllegalArgumentException | CE8 |

### 17. Testes Gerais

| ID | Descrição | Entrada | Saída Esperada | Classe Exercitada |
|--|--|--|--|--|
| TC54 | Estado inicial | Jogo recém criado | ESTADO_EM_ANDAMENTO, X é atual | CE9 |
| TC55 | Alternância jogadores | Duas jogadas | Após primeira X, após segunda O | CE9 |

## Total de Casos Funcionais

**55 casos de teste** cobrindo:
- ✓ Classes de Equivalência (17 classes)
- ✓ Valores Limite (8 limites identificados)
- ✓ Condições Válidas e Inválidas
- ✓ Padrões de Vitória (linhas, colunas, diagonais)
- ✓ Empate
- ✓ Fluxo de Jogo
- ✓ Reinicialização
- ✓ Integridade de Estado

## Cobertura de Requisitos

| Requisito | ID de Casos |
|--|--|
| Validação de Coordenadas | TC01-TC12, TC41-TC48 |
| Marcação de Posição | TC01-TC04, TC28-TC30 |
| Detecção Vitória Horizontal | TC15-TC17, TC31 |
| Detecção Vitória Vertical | TC18-TC20, TC32 |
| Detecção Vitória Diagonal | TC21-TC22, TC31-TC32 |
| Empate | TC25-TC27, TC33, TC35 |
| Fluxo do Jogo | TC28-TC35 |
| Símbolos | TC51-TC53 |
| Estado | TC54-TC55 |

## Ordem de Execução Recomendada

1. **Fase 1 - Posições (TC01-TC48)**: Validação de entrada
2. **Fase 2 - Vitória (TC15-TC24)**: Detecção de padrões
3. **Fase 3 - Empate (TC25-TC27)**: Condição final
4. **Fase 4 - Jogo (TC28-TC37)**: Integração
5. **Fase 5 - Símbolos (TC51-TC53)**: Validação de domínio
6. **Fase 6 - Estado (TC54-TC55)**: Máquina de estado

