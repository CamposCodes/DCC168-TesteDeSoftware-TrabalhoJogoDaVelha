# DCC168 - Teste de Software
## Parte I: Análise Funcional e Testes - Jogo da Velha

**Disciplina:** DCC168 - Teste de Software  
**Professor:** André Luiz de Oliveira  
**Trabalho:** Parte I - Análise Funcional e Testes do Jogo da Velha  
**Data:** 23 de Novembro de 2025  
**Integrantes do Grupo:**
- Gabriel Campos Lima Alves  - 202176005
- Celso Zacarias



---

## 1. INTRODUÇÃO

### 1.1 Objetivos da Parte I

A Parte I deste trabalho tem como objetivos:

1. **Análise Funcional:** Identificar e documentar as funcionalidades principais do Jogo da Velha
2. **Especificação de Entrada/Saída:** Definir claramente as entradas, processamento e saídas esperadas
3. **Aplicação de Técnicas de Teste:** Aplicar três técnicas complementares de teste caixa-preta:
   - **Particionamento de Equivalência:** Dividir o domínio de entrada em classes válidas e inválidas
   - **Análise de Valor Limite:** Testar os limites das classes de equivalência
   - **Grafo de Causa-Efeito:** Analisar as relações entre causas (entradas) e efeitos (saídas)
4. **Consolidação de Casos de Teste:** Integrar todos os casos em uma suite abrangente (TestSet-Func)
5. **Demonstração de Cobertura:** Garantir 100% de cobertura das funcionalidades

### 1.2 Descrição do Jogo da Velha

O **Jogo da Velha** é um jogo estratégico simples entre dois jogadores que alternam turnos em um tabuleiro 3×3. O objetivo é formar uma sequência de três símbolos idênticos (do mesmo jogador) em qualquer linha, coluna ou diagonal.

**Características Principais:**
- Tabuleiro de 9 células (3×3)
- Dois jogadores: **X** e **O**
- Turnos alternados (X começa primeiro)
- Vitória: 3 símbolos iguais (8 combinações possíveis)
- Empate: tabuleiro preenchido sem vencedor

**Posições no Tabuleiro:**
```
(0,0) | (0,1) | (0,2)
------|-------|------
(1,0) | (1,1) | (1,2)
------|-------|------
(2,0) | (2,1) | (2,2)
```

### 1.3 Técnicas de Teste Utilizadas

#### 1.3.1 Particionamento de Equivalência
Divide o domínio de entrada em classes onde espera-se que o software se comporte de forma similar.
- **Classe Válida (V):** Entrada que deve ser aceita
- **Classe Inválida (I):** Entrada que deve ser rejeitada
- **Vantagem:** Reduz casos de teste mantendo cobertura
- **Estratégia:** 8 condições → 20 classes mapeadas

#### 1.3.2 Análise de Valor Limite (AVL)
Foca em testar os valores nas fronteiras das classes de equivalência.
- **Hipótese:** Erros concentram-se em limites
- **Estratégia:** Teste valores mínimo, máximo e fora dos limites
- **Exemplos:** linha={-1, 0, 1, 2, 3}, coluna={-1, 0, 1, 2, 3}

#### 1.3.3 Grafo de Causa-Efeito
Analisa as relações lógicas entre condições (causas) e resultados (efeitos).
- **Causa:** Evento de entrada ou estado que pode ativar uma ação
- **Efeito:** Resultado ou mudança de estado produzida
- **Ferramenta:** Grafo de causa-efeito + Tabela de Decisão

---

## 2. ANÁLISE DA ESPECIFICAÇÃO

### 2.1 Funcionalidades Identificadas
O Jogo da Velha apresenta as seguintes funcionalidades principais:

| # | Funcionalidade | Descrição |
|---|---|---|
| F1 | Inicialização | Criar um novo jogo com tabuleiro vazio |
| F2 | Validação de Posição | Verificar se linha/coluna estão no intervalo [0,2] |
| F3 | Validação de Célula | Verificar se a célula está vazia |
| F4 | Validação de Símbolo | Verificar se o símbolo é X ou O |
| F5 | Validação de Turno | Verificar se é o turno correto do jogador |
| F6 | Registro de Jogada | Preencher célula com símbolo do jogador |
| F7 | Detecção de Vitória | Verificar se há 3 símbolos iguais em linha, coluna ou diagonal |
| F8 | Detecção de Empate | Verificar se o tabuleiro está cheio sem vencedor |
| F9 | Alternância de Turno | Alternar para o próximo jogador após jogada válida |
| F10 | Finalização | Encerrar jogo ao detectar vitória ou empate |

### 2.2 Especificação de Entrada/Saída
**Entrada:**
- `linha` (int): Linha do tabuleiro [0, 1, 2]
- `coluna` (int): Coluna do tabuleiro [0, 1, 2]
- `símbolo` (char): X ou O
- `estado`: Estado atual do jogo

**Saída:**
- Confirmação de jogada aceita ou rejeitada
- Mensagem de erro (posição inválida, célula ocupada, etc.)
- Estado atualizado do tabuleiro
- Indicação de vencedor ou empate

### 2.3 Processos e Regras de Negócio

1. **Validade de Posição:** 0 ≤ linha ≤ 2 AND 0 ≤ coluna ≤ 2
2. **Célula Disponível:** tabuleiro[linha][coluna] == VAZIA
3. **Símbolo Válido:** símbolo ∈ {X, O}
4. **Turno Correto:** símbolo corresponde ao jogador atual
5. **Jogo Ativo:** Estado ≠ FINALIZADO
6. **Vitória:** Detectar 3 símbolos iguais em:
   - Qualquer linha (3 combinações)
   - Qualquer coluna (3 combinações)
   - Diagonal principal \ (1 combinação)
   - Diagonal secundária / (1 combinação)
7. **Empate:** 9 células preenchidas AND nenhum vencedor

---

## 3. PARTICIONAMENTO DE EQUIVALÊNCIA
### 3.1 Metodologia

O particionamento de equivalência divide o domínio de entrada em subconjuntos (classes) onde o software deve apresentar comportamento similar. Cada classe é testada por um ou mais casos de teste representativos.

**Estratégia Aplicada:**
1. Identificar condições independentes
2. Dividir cada condição em classes válidas e inválidas
3. Mapear classes para casos de teste
4. Garantir cobertura de todas as classes

### 3.2 Condições e Classes de Equivalência

#### C1: Linha
- **V1 - Válido:** linha ∈ {0, 1, 2}
- **I1 - Inválido (menor):** linha < 0 (ex: -1, -5)
- **I2 - Inválido (maior):** linha > 2 (ex: 3, 10)

#### C2: Coluna
- **V2 - Válido:** coluna ∈ {0, 1, 2}
- **I3 - Inválido (menor):** coluna < 0 (ex: -1, -5)
- **I4 - Inválido (maior):** coluna > 2 (ex: 3, 10)

#### C3: Célula
- **V3 - Válido:** célula vazia (VAZIA)
- **I5 - Inválido:** célula contém X
- **I6 - Inválido:** célula contém O

#### C4: Símbolo
- **V4 - Válido:** símbolo = X
- **V5 - Válido:** símbolo = O
- **I7 - Inválido:** símbolo ∉ {X, O} (ex: *, A, espaço)

#### C5: Estado do Jogo
- **V6 - Válido:** Estado = ATIVO
- **I8 - Inválido:** Estado = VITÓRIA
- **I9 - Inválido:** Estado = EMPATE

#### C6: Turno
- **V7 - Válido:** Símbolo corresponde ao jogador atual
- **I10 - Inválido:** Símbolo NÃO corresponde ao jogador atual

#### C7: Sequência de Símbolos
- **V8 - Válido:** Há 3 símbolos iguais após jogada (vitória)
- **I11 - Inválido:** Não há sequência de 3 símbolos

#### C8: Tabuleiro
- **V9 - Válido:** Tabuleiro parcialmente preenchido (< 9 células)
- **V10 - Válido:** Tabuleiro completamente preenchido (9 células)
- **I12 - Inválido:** Tabuleiro com > 9 símbolos (estado corrompido)

### 3.3 Resumo das Classes Identificadas
**Total de Classes:** 20 (10 Válidas: V1-V10, 10 Inválidas: I1-I12)
A tabela completa de classes de equivalência está disponível no arquivo **TABELAS_TESTE.md - Tabela 1**.

### 3.4 Casos de Teste - Particionamento
Foram derivados **14 casos de teste** cobrindo todas as classes de equivalência identificadas:

- **CT-PCE-01 a CT-PCE-03:** Jogadas válidas com X e O em diferentes posições
- **CT-PCE-04 a CT-PCE-07:** Validação de limites de linha e coluna (casos inválidos)
- **CT-PCE-08 a CT-PCE-09:** Tentativas de jogar em células ocupadas
- **CT-PCE-10:** Validação de símbolo inválido
- **CT-PCE-11:** Validação de turno incorreto
- **CT-PCE-12 a CT-PCE-13:** Tentativas de jogar após jogo finalizado
- **CT-PCE-14:** Cenário de empate (9ª jogada)

**Ver especificação detalhada** (ID, condições de entrada, saídas esperadas e classes exercitadas) no arquivo **TABELAS_TESTE.md - Tabela 2**.

---

## 4. ANÁLISE DE VALOR LIMITE
### 4.1 Metodologia

A Análise de Valor Limite (AVL) testa valores específicos nas fronteiras das classes de equivalência, pois estudos mostram que erros são mais frequentes em limites.

**Estratégia:**
- Para cada variável: teste mínimo, mínimo-1, máximo, máximo+1
- Teste combinações de limites com centro do intervalo
- Posições especiais: cantos, bordas, centro

### 4.2 Limites Identificados

**Linha:** [0, 2]
- Mínimo válido: 0
- Máximo válido: 2
- Abaixo do limite: -1
- Acima do limite: 3

**Coluna:** [0, 2]
- Mínimo válido: 0
- Máximo válido: 2
- Abaixo do limite: -1
- Acima do limite: 3

**Posições Especiais no Tabuleiro:**
- **Cantos:** (0,0), (0,2), (2,0), (2,2) - 4 posições
- **Centro:** (1,1) - 1 posição
- **Bordas:** (0,1), (1,0), (1,2), (2,1) - 4 posições

### 4.3 Casos de Teste - Valor Limite

Foram derivados **12 casos de teste** focados em valores limite:

- **CT-AVL-01 a CT-AVL-04:** Testes nos 4 cantos do tabuleiro (limites válidos combinados)
- **CT-AVL-05:** Teste no centro do tabuleiro
- **CT-AVL-06 a CT-AVL-09:** Testes nas 4 bordas
- **CT-AVL-10 a CT-AVL-12:** Testes fora dos limites (valores inválidos)

**Ver especificação detalhada** no arquivo **TABELAS_TESTE.md - Tabela 3**.

---

## 5. GRAFO DE CAUSA-EFEITO
### 5.1 Metodologia
A técnica Causa-Efeito analisa as relações lógicas entre eventos de entrada (causas) e resultados (efeitos), utilizando:
1. **Grafo de Causa-Efeito:** Representação visual das relações
2. **Tabela de Decisão:** Combinações de causas e efeitos correspondentes
3. **Casos de Teste Derivados:** Um para cada linha da tabela

### 5.2 Definição de Causas e Efeitos
**Causas (Condições de Entrada):**
- **C1:** Posição Válida (0≤L≤2, 0≤C≤2)
- **C2:** Célula Vazia
- **C3:** Símbolo Válido (X ou O)
- **C4:** Turno Correto
- **C5:** Jogo Ativo
- **C6:** Sequência Completa (3 símbolos iguais)
- **C7:** Tabuleiro Cheio (9 células)
- **C8:** Empate Detectado (cheio + sem vencedor)
- **C9:** Célula Ocupada
- **C10:** Jogo Finalizado

**Efeitos (Resultados):**
- **E1:** Jogada Aceita (célula preenchida, turno alterado)
- **E2:** Vitória Detectada (3 símbolos iguais)
- **E3:** Empate Detectado (tabuleiro cheio, sem vencedor)
- **E4:** Erro - Posição Inválida
- **E5:** Erro - Célula Ocupada
- **E6:** Erro - Símbolo Inválido
- **E7:** Erro - Turno Inválido
- **E8:** Erro - Jogo Finalizado

### 5.3 Relações Lógicas

**Regras Principais:**

1. **Jogada Válida:** C1 ∧ C2 ∧ C3 ∧ C4 ∧ C5 → E1
2. **Vitória:** E1 ∧ C6 → E2
3. **Empate:** E1 ∧ C7 ∧ C8 → E3
4. **Erro Posição:** ¬C1 → E4
5. **Erro Célula:** ¬C2 → E5
6. **Erro Símbolo:** ¬C3 → E6
7. **Erro Turno:** ¬C4 → E7
8. **Erro Estado:** ¬C5 → E8

### 5.4 Grafo de Causa-Efeito

```
┌──────────────────────────────────────────────────────┐
│         ENTRADA: (Linha, Coluna, Símbolo, Estado)   │
└──────────────────────────────────────────────────────┘
                       │
        ┌──────────────┼──────────────┐
        │              │              │
    ┌───▼───┐     ┌───▼───┐     ┌───▼───┐
    │  C1   │     │  C2   │     │  C3   │
    │Posição│     │ Vazia │     │Símbolo│
    │Válida │     │       │     │Válido │
    └───┬───┘     └───┬───┘     └───┬───┘
        │             │             │
        └─────────────┼─────────────┘
                      │
              ┌───────▼────────┐
              │  C4 ∧ C5       │
              │ Turno Correto  │
              │ Jogo Ativo     │
              └───────┬────────┘
                      │
        ┌─────────────▼──────────────┐
        │    Jogada Válida?          │
        └─────────┬──────────────┬───┘
                 SIM             NÃO
                  │               │
         ┌────────▼────────┐  ┌──▼─ Retorna Erro
         │ C6 ∧ C7 ∧ C8    │  │   (E4-E8)
         │ Processar       │  │
         │ Vitória/Empate  │  │
         └────────┬────────┘  │
                  │           │
         ┌────────┴────┐      │
         │ Vitória?    │      │
        SIM           NÃO     │
         │              │     │
    ┌───▼───┐    ┌─────▼────────┐
    │ E2    │    │ Tabuleiro    │
    │Vitória│    │ Cheio?       │
    │       │    └─────┬────────┘
    └───────┘          │
                    SIM  NÃO
                     │    │
                  ┌──▼─┐  │
                  │ E3 │  │
                  │Emp.│  └─→ E1: Jogada Aceita
                  └────┘
```

**Legenda:** ∧ (AND), ∨ (OR), ¬ (NOT)

### 5.5 Tabela de Decisão

| Caso | C1 | C2 | C3 | C4 | C5 | C6 | C7 | C8 | **Efeito Principal** | Secundários |
|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|---|---|
| **CT-GCE-01** | ✓ | ✓ | ✓ | ✓ | ✓ | ✗ | ✗ | ✗ | **E1**: Aceita | Turno alterna |
| **CT-GCE-02** | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✗ | ✗ | **E2**: Vitória | Finaliza |
| **CT-GCE-03** | ✓ | ✓ | ✓ | ✓ | ✓ | ✗ | ✓ | ✓ | **E3**: Empate | Finaliza |
| **CT-GCE-04** | ✗ | ✓ | ✓ | ✓ | ✓ | ✗ | ✗ | ✗ | **E4**: Pos. Inválida | — |
| **CT-GCE-05** | ✓ | ✗ | ✓ | ✓ | ✓ | ✗ | ✗ | ✗ | **E5**: Célula Ocupada | — |
| **CT-GCE-06** | ✓ | ✓ | ✗ | ✓ | ✓ | ✗ | ✗ | ✗ | **E6**: Símbolo Inv. | — |
| **CT-GCE-07** | ✓ | ✓ | ✓ | ✗ | ✓ | ✗ | ✗ | ✗ | **E7**: Turno Inválido | — |
| **CT-GCE-08** | ✓ | ✓ | ✓ | ✓ | ✗ | ✗ | ✗ | ✗ | **E8**: Jogo Finalizado | — |

### 5.6 Casos de Teste - Causa-Efeito

Foram derivados **8 casos de teste** cobrindo todas as combinações relevantes de causas e efeitos:

- **CT-GCE-01:** Jogada válida simples (caminho feliz)
- **CT-GCE-02:** Vitória detectada após jogada
- **CT-GCE-03:** Empate detectado (9ª jogada sem vencedor)
- **CT-GCE-04 a CT-GCE-08:** Validação de erros (5 tipos de erro diferentes)

**Ver especificação detalhada** no arquivo **TABELAS_TESTE.md - Tabela 4**.

---

## 6. TESTES DE VITÓRIA

### 6.1 Descrição

O Jogo da Velha oferece **8 possibilidades de vitória** (3 linhas + 3 colunas + 2 diagonais). Esta seção garante que cada uma delas seja testada isoladamente.

### 6.2 Casos de Teste - Vitória

Foram criados **8 casos de teste específicos** para cada condição de vitória:

- **CT-VIT-01, CT-VIT-02, CT-VIT-03:** Vitórias em linhas 1, 2 e 3
- **CT-VIT-04, CT-VIT-05, CT-VIT-06:** Vitórias em colunas 1, 2 e 3
- **CT-VIT-07:** Vitória na diagonal principal (\)
- **CT-VIT-08:** Vitória na diagonal secundária (/)

**Ver especificação detalhada** (posições exatas e sequências) no arquivo **TABELAS_TESTE.md - Tabela 5**.

---

## 7. OUTROS CRITÉRIOS FUNCIONAIS

Além das três técnicas principais (Particionamento, Valor Limite e Causa-Efeito), consideramos a aplicação de critérios complementares:

### 7.1 Tabela de Decisão

A técnica de Tabela de Decisão analisa combinações de condições booleanas e seus respectivos efeitos. 

**Aplicação no Jogo da Velha:**
- Identificamos 4 condições principais: Posição Válida (S/N), Célula Vazia (S/N), Símbolo Válido (S/N), Turno Correto (S/N)
- Combinações possíveis: 2⁴ = 16
- Combinações viáveis após análise: 8 (eliminando combinações impossíveis)
- Resultado: Os casos derivados sobrepõem-se aos já identificados por Particionamento e Causa-Efeito

**Conclusão:** A Tabela de Decisão valida a completude dos casos já especificados, sem necessidade de casos adicionais.

### 7.2 Teste de Pares (Pairwise Testing)

O Pairwise Testing reduz o número de casos testando todas as combinações de pares de parâmetros, ao invés de todas as combinações possíveis.

**Aplicação no Jogo da Velha:**
- Parâmetros identificados:
  - Símbolo: {X, O} (2 valores)
  - Posição: {canto, borda, centro} (3 categorias)
  - Estado: {ativo, finalizado} (2 valores)
- Combinações completas: 2 × 3 × 2 = 12
- Pares necessários: 6 combinações cobrem todos os pares

**Conclusão:** Os 42 casos já especificados cobrem todas as combinações de pares identificadas. O critério Pairwise confirma que não há lacunas na cobertura.

### 7.3 Máquina de Estados

A Máquina de Estados modela o ciclo de vida do jogo através de estados e transições.

**Estados Identificados:**
1. **INICIAL:** Jogo criado, tabuleiro vazio (estado inicial)
2. **JOGANDO:** Partida em andamento (1 a 8 jogadas realizadas, sem vencedor)
3. **VITORIA_X:** Jogador X venceu (estado final)
4. **VITORIA_O:** Jogador O venceu (estado final)
5. **EMPATE:** Tabuleiro completo sem vencedor (estado final)

**Transições Validadas:**
- INICIAL → JOGANDO (primeira jogada válida)
- JOGANDO → JOGANDO (jogadas válidas intermediárias)
- JOGANDO → VITORIA_X (X completa sequência de 3)
- JOGANDO → VITORIA_O (O completa sequência de 3)
- JOGANDO → EMPATE (9ª jogada sem vencedor)
- VITORIA_X, VITORIA_O, EMPATE → [bloqueio] (tentativa de jogar após fim)

**Cobertura:**
- Todas as 6 transições válidas possuem casos de teste
- Transições inválidas (jogar após finalização) são testadas em CT-PCE-12 e CT-PCE-13

**Conclusão:** A Máquina de Estados confirma a completude da especificação de testes, garantindo que todos os estados e transições foram cobertos.

---

## 8. CONSOLIDAÇÃO

### 8.1 Suite Completa de Testes (TestSet-Func)

A Parte I consolida **42 casos de teste** derivados de 4 categorias:

| Categoria | Quantidade | IDs |
|---|---|---|
| **Particionamento de Equivalência** | 14 casos | CT-PCE-01 a CT-PCE-14 |
| **Análise de Valor Limite** | 12 casos | CT-AVL-01 a CT-AVL-12 |
| **Grafo de Causa-Efeito** | 8 casos | CT-GCE-01 a CT-GCE-08 |
| **Testes de Vitória** | 8 casos | CT-VIT-01 a CT-VIT-08 |
| **TOTAL** | **42 casos** | **TestSet-Func** |

### 8.2 Resumo Estatístico

| Métrica | Valor |
|---|---|
| Total de Casos de Teste | **42** |
| Classes de Equivalência Identificadas | **20** (10 válidas + 10 inválidas) |
| Funcionalidades Cobertas | **10** (F1 a F10) |
| Condições Analisadas | **8** (C1 a C8) |
| Causas Mapeadas | **10** |
| Efeitos Mapeados | **8** |
| Possibilidades de Vitória Testadas | **8** (100%) |
| **Cobertura Funcional Esperada** | **100%** |

### 8.3 Distribuição por Técnica

```
Particionamento (14):  ████████████████████████ (33%)
Valor Limite (12):     ██████████████████████ (29%)
Causa-Efeito (8):      ████████████ (19%)
Vitória (8):           ████████████ (19%)
                       ──────────────────────────
                       TOTAL: 42 CASOS
```

### 8.4 Matriz de Rastreabilidade

A matriz abaixo garante que todas as funcionalidades identificadas possuem pelo menos um caso de teste:

| Funcionalidade | Casos de Teste Relacionados | Cobertura |
|---|---|---|
| **F1: Inicialização** | CT-PCE-01, CT-AVL-01, CT-GCE-01 | ✓ |
| **F2: Validação de Posição** | CT-PCE-04, CT-PCE-05, CT-PCE-06, CT-PCE-07, CT-AVL-10, CT-AVL-11, CT-AVL-12, CT-GCE-04 | ✓ |
| **F3: Validação de Célula** | CT-PCE-08, CT-PCE-09, CT-GCE-05 | ✓ |
| **F4: Validação de Símbolo** | CT-PCE-10, CT-GCE-06 | ✓ |
| **F5: Validação de Turno** | CT-PCE-11, CT-GCE-07 | ✓ |
| **F6: Registro de Jogada** | Todos os casos válidos (22 casos) | ✓ |
| **F7: Detecção de Vitória** | CT-VIT-01 a CT-VIT-08, CT-GCE-02 | ✓ |
| **F8: Detecção de Empate** | CT-PCE-14, CT-GCE-03 | ✓ |
| **F9: Alternância de Turno** | CT-GCE-01, CT-PCE-01, CT-PCE-02 | ✓ |
| **F10: Finalização** | CT-PCE-12, CT-PCE-13, CT-GCE-08 | ✓ |

**Resultado:** 100% das funcionalidades cobertas.

---

## 9. ANÁLISE DOS CRITÉRIOS APLICADOS

### 9.1 Por que Particionamento de Equivalência?

**Justificativa:**
- **Eficiência:** Reduz o número de casos mantendo cobertura (20 classes → 14 casos representativos)
- **Manutenibilidade:** Organiza testes de forma lógica e estruturada
- **Cobertura Sistemática:** Garante teste de caminho válido e inválido para cada condição
- **Aplicação Ideal:** Perfeito para testar validações de entrada (posição, símbolo,