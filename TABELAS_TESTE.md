# Jogo da Velha - Tabelas Técnicas de Teste Funcional

<style>
  /* Estilos simples inline para visualização em Markdown renderizado */
  .gh-table { width:100%; border-collapse: collapse; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif; font-size:13px; }
  .gh-table thead th { background:#f6f8fa; color:#24292f; border:1px solid #e1e4e8; padding:10px 12px; text-align:left; font-weight:600; }
  .gh-table tbody td { border:1px solid #e1e4e8; padding:10px 12px; vertical-align:top; color:#24292f; }
  .gh-muted { color:#6a737d; }
  .gh-success { color:#22863a; background:#f0fff4; padding:4px 8px; border-radius:6px; display:inline-block; }
  .gh-error { color:#cb2431; background:#fff5f5; padding:4px 8px; border-radius:6px; display:inline-block; }
  .gh-id { color:#0366d6; font-weight:600; }
  .gh-strip-odd { background:#ffffff; }
  .gh-strip-even { background:#f6f8fa; }
</style>

## TABELA 1: CLASSES DE EQUIVALÊNCIA

Resumo das 20+ classes identificadas nas 8 condições:

<table class="gh-table" aria-describedby="tabela-classes">
  <thead>
    <tr>
      <th>Condição de Entrada</th>
      <th>Classes de Equivalência Válidas</th>
      <th>Classes de Equivalência Inválidas</th>
    </tr>
  </thead>
  <tbody>
    <tr class="gh-strip-odd">
      <td><strong>C1: Linha</strong></td>
      <td><span class="gh-success">V1: 0 ≤ linha ≤ 2</span></td>
      <td><span class="gh-error">I1: linha &lt; 0</span>, <span class="gh-error">I2: linha &gt; 2</span></td>
    </tr>
    <tr class="gh-strip-even">
      <td><strong>C2: Coluna</strong></td>
      <td><span class="gh-success">V2: 0 ≤ coluna ≤ 2</span></td>
      <td><span class="gh-error">I3: coluna &lt; 0</span>, <span class="gh-error">I4: coluna &gt; 2</span></td>
    </tr>
    <tr class="gh-strip-odd">
      <td><strong>C3: Célula</strong></td>
      <td><span class="gh-success">V3: célula vazia</span></td>
      <td><span class="gh-error">I5: contém X</span>, <span class="gh-error">I6: contém O</span></td>
    </tr>
    <tr class="gh-strip-even">
      <td><strong>C4: Símbolo</strong></td>
      <td><span class="gh-success">V4: X</span>, <span class="gh-success">V5: O</span></td>
      <td><span class="gh-error">I7: outro símbolo</span></td>
    </tr>
    <tr class="gh-strip-odd">
      <td><strong>C5: Estado do Jogo</strong></td>
      <td><span class="gh-success">V6: ATIVO</span></td>
      <td><span class="gh-error">I8: VITÓRIA</span>, <span class="gh-error">I9: EMPATE</span></td>
    </tr>
    <tr class="gh-strip-even">
      <td><strong>C6: Turno</strong></td>
      <td><span class="gh-success">V7: turno correto</span></td>
      <td><span class="gh-error">I10: fora de turno</span></td>
    </tr>
    <tr class="gh-strip-odd">
      <td><strong>C7: Sequência</strong></td>
      <td><span class="gh-success">V8: 3 iguais (vitória)</span></td>
      <td><span class="gh-error">I11: sem sequência</span></td>
    </tr>
    <tr class="gh-strip-even">
      <td><strong>C8: Tabuleiro</strong></td>
      <td><span class="gh-success">V9: parcial</span>, <span class="gh-success">V10: cheio</span></td>
      <td><span class="gh-error">I12: estado corrompido (&gt;9)</span></td>
    </tr>
  </tbody>
</table>

**Total de Classes:** 20 (10 Válidas + 10 Inválidas)

---

## TABELA 2: CASOS TESTE - PARTICIONAMENTO (14 casos)

<table class="gh-table" aria-describedby="tabela-pce">
  <thead>
    <tr>
      <th style="width:110px;">ID</th>
      <th>Condições de Entrada</th>
      <th>Saída Esperada</th>
      <th style="width:160px;">Classes Eq. Exercitadas</th>
      <th style="width:120px;">Saída Obtida</th>
    </tr>
  </thead>
  <tbody>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-PCE-01</td>
      <td>L=0, C=0, X, turno=X, célula=vazia, estado=ATIVO</td>
      <td>Jogada aceita; atualiza tabuleiro[0][0]=X</td>
      <td>V1, V2, V4, V7, V3, V6</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-PCE-02</td>
      <td>L=2, C=2, O, turno=O, célula=vazia, estado=ATIVO</td>
      <td>Jogada aceita; atualiza tabuleiro[2][2]=O</td>
      <td>V1, V2, V5, V7, V3, V6</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-PCE-03</td>
      <td>L=1, C=1, X, turno=X, célula=vazia, estado=ATIVO</td>
      <td>Jogada aceita; atualiza tabuleiro[1][1]=X</td>
      <td>V1, V2, V4, V7, V3, V6</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-PCE-04</td>
      <td>L=-1, C=0, X, turno=X</td>
      <td><span class="gh-error">Erro: Linha inválida</span></td>
      <td>I1, V2</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-PCE-05</td>
      <td>L=3, C=2, X, turno=X</td>
      <td><span class="gh-error">Erro: Linha inválida</span></td>
      <td>I2, V2</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-PCE-06</td>
      <td>L=1, C=-1, X, turno=X</td>
      <td><span class="gh-error">Erro: Coluna inválida</span></td>
      <td>V1, I3</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-PCE-07</td>
      <td>L=1, C=3, X, turno=X</td>
      <td><span class="gh-error">Erro: Coluna inválida</span></td>
      <td>V1, I4</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-PCE-08</td>
      <td>L=0, C=0, X, turno=X, célula=X</td>
      <td><span class="gh-error">Erro: Célula ocupada</span></td>
      <td>V1,V2,V4,V7,I5</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-PCE-09</td>
      <td>L=0, C=0, X, turno=X, célula=O</td>
      <td><span class="gh-error">Erro: Célula ocupada</span></td>
      <td>V1,V2,V4,V7,I6</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-PCE-10</td>
      <td>L=1, C=1, *, turno válido, célula=vazia</td>
      <td><span class="gh-error">Erro: Símbolo inválido</span></td>
      <td>V1,V2,I7</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-PCE-11</td>
      <td>L=1, C=1, O, turno=X, célula=vazia</td>
      <td><span class="gh-error">Erro: Fora do turno</span></td>
      <td>V1,V2,V5,I10</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-PCE-12</td>
      <td>L=1, C=1, X, turno=X, estado=VITÓRIA</td>
      <td><span class="gh-error">Erro: Jogo finalizado</span></td>
      <td>V1,V2,V4,I8</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-PCE-13</td>
      <td>L=1, C=1, X, turno=X, estado=EMPATE</td>
      <td><span class="gh-error">Erro: Jogo finalizado</span></td>
      <td>V1,V2,V4,I9</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-PCE-14</td>
      <td>L=0, C=0, O, turno=O, célula=vazia, 8 jogadas anteriores</td>
      <td>Jogada aceita; resultado: Empate (tabuleiro cheio)</td>
      <td>V1,V2,V5,V7,V3,V9</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
  </tbody>
</table>

**Total Particionamento:** 14 casos

---

## TABELA 3: CASOS TESTE - ANÁLISE DE VALOR LIMITE (12 casos)

<table class="gh-table" aria-describedby="tabela-avl">
  <thead>
    <tr>
      <th>ID</th>
      <th>Posição</th>
      <th>Tipo</th>
      <th>Símbolo</th>
      <th>Saída Esperada</th>
      <th>Saída Obtida</th>
    </tr>
  </thead>
  <tbody>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-AVL-01</td>
      <td>(0,0)</td>
      <td>Canto</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-AVL-02</td>
      <td>(0,2)</td>
      <td>Canto</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-AVL-03</td>
      <td>(2,0)</td>
      <td>Canto</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-AVL-04</td>
      <td>(2,2)</td>
      <td>Canto</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-AVL-05</td>
      <td>(1,1)</td>
      <td>Centro</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-AVL-06</td>
      <td>(0,1)</td>
      <td>Borda</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-AVL-07</td>
      <td>(1,0)</td>
      <td>Borda</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-AVL-08</td>
      <td>(1,2)</td>
      <td>Borda</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-AVL-09</td>
      <td>(2,1)</td>
      <td>Borda</td>
      <td>X</td>
      <td>Aceita</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-AVL-10</td>
      <td>(-1,0)</td>
      <td>Fora</td>
      <td>X</td>
      <td><span class="gh-error">Erro: Linha inválida</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-AVL-11</td>
      <td>(3,0)</td>
      <td>Fora</td>
      <td>X</td>
      <td><span class="gh-error">Erro: Linha inválida</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-AVL-12</td>
      <td>(0,-1)</td>
      <td>Fora</td>
      <td>X</td>
      <td><span class="gh-error">Erro: Coluna inválida</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
  </tbody>
</table>

**Total Valor Limite:** 12 casos

---

## TABELA 4: CASOS TESTE - CAUSA-EFEITO (8 casos)

<table class="gh-table" aria-describedby="tabela-gce">
  <thead>
    <tr>
      <th>ID</th>
      <th>Cenário</th>
      <th>Entradas</th>
      <th>Saída Esperada</th>
      <th>Saída Obtida</th>
    </tr>
  </thead>
  <tbody>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-GCE-01</td>
      <td>Jogada válida sem vitória</td>
      <td>L=0,C=0,X,turno=X,vazio,ATIVO</td>
      <td>Aceita; turno alterna</td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-GCE-02</td>
      <td>Vitória em linha</td>
      <td>L=0,C=0,X (após [0,1][0,2]=X)</td>
      <td><span class="gh-success">Vitória X; jogo finaliza</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-GCE-03</td>
      <td>Empate (9ª jogada)</td>
      <td>L=0,C=2,O (sem 3 iguais)</td>
      <td><span class="gh-success">Empate; jogo finaliza</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-GCE-04</td>
      <td>Erro: Posição inválida</td>
      <td>L=-1,C=0,X</td>
      <td><span class="gh-error">Erro: Posição inválida</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-GCE-05</td>
      <td>Erro: Célula ocupada</td>
      <td>L=0,C=0 (já=X),X</td>
      <td><span class="gh-error">Erro: Célula ocupada</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-GCE-06</td>
      <td>Erro: Símbolo inválido</td>
      <td>L=0,C=0,*,válido</td>
      <td><span class="gh-error">Erro: Símbolo inválido</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-GCE-07</td>
      <td>Erro: Turno inválido</td>
      <td>L=0,C=0,O,turno=X</td>
      <td><span class="gh-error">Erro: Fora do turno</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-GCE-08</td>
      <td>Erro: Jogo finalizado</td>
      <td>L=1,C=1,X,estado=VITÓRIA</td>
      <td><span class="gh-error">Erro: Jogo finalizado</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
  </tbody>
</table>

**Total Causa-Efeito:** 8 casos

---

## TABELA 5: CASOS TESTE - VITÓRIA (8 casos)

<table class="gh-table" aria-describedby="tabela-vit">
  <thead>
    <tr>
      <th>ID</th>
      <th>Tipo</th>
      <th>Posições Vencedoras</th>
      <th>Símbolo</th>
      <th>Saída Esperada</th>
      <th>Saída Obtida</th>
    </tr>
  </thead>
  <tbody>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-VIT-01</td>
      <td>Linha 1</td>
      <td>(0,0), (0,1), (0,2)</td>
      <td>X</td>
      <td><span class="gh-success">Vitória X</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-VIT-02</td>
      <td>Linha 2</td>
      <td>(1,0), (1,1), (1,2)</td>
      <td>X</td>
      <td><span class="gh-success">Vitória X</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-VIT-03</td>
      <td>Linha 3</td>
      <td>(2,0), (2,1), (2,2)</td>
      <td>O</td>
      <td><span class="gh-success">Vitória O</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-VIT-04</td>
      <td>Coluna 1</td>
      <td>(0,0), (1,0), (2,0)</td>
      <td>X</td>
      <td><span class="gh-success">Vitória X</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-VIT-05</td>
      <td>Coluna 2</td>
      <td>(0,1), (1,1), (2,1)</td>
      <td>O</td>
      <td><span class="gh-success">Vitória O</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-VIT-06</td>
      <td>Coluna 3</td>
      <td>(0,2), (1,2), (2,2)</td>
      <td>X</td>
      <td><span class="gh-success">Vitória X</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-odd">
      <td class="gh-id">CT-VIT-07</td>
      <td>Diagonal \ (principal)</td>
      <td>(0,0), (1,1), (2,2)</td>
      <td>O</td>
      <td><span class="gh-success">Vitória O</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
    <tr class="gh-strip-even">
      <td class="gh-id">CT-VIT-08</td>
      <td>Diagonal / (secundária)</td>
      <td>(0,2), (1,1), (2,0)</td>
      <td>X</td>
      <td><span class="gh-success">Vitória X</span></td>
      <td style="background-color: #fff3cd; padding: 10px 12px;"><em>A preencher na Parte II</em></td>
    </tr>
  </tbody>
</table>

**Total Vitória:** 8 casos

---

## RESUMO CONSOLIDADO

<table class="gh-table">
  <thead>
    <tr>
      <th>Técnica</th>
      <th>Casos</th>
      <th>Percentual</th>
      <th>Status</th>
    </tr>
  </thead>
  <tbody>
    <tr class="gh-strip-odd"><td>Particionamento</td><td>14</td><td>33%</td><td><span class="gh-success">✓ 14/14</span></td></tr>
    <tr class="gh-strip-even"><td>Valor Limite</td><td>12</td><td>29%</td><td><span class="gh-success">✓ 12/12</span></td></tr>
    <tr class="gh-strip-odd"><td>Causa-Efeito</td><td>8</td><td>19%</td><td><span class="gh-success">✓ 8/8</span></td></tr>
    <tr class="gh-strip-even"><td>Vitória</td><td>8</td><td>19%</td><td><span class="gh-success">✓ 8/8</span></td></tr>
    <tr class="gh-strip-odd"><td><strong>TOTAL</strong></td><td><strong>42</strong></td><td><strong>100%</strong></td><td><span class="gh-success">✓ 42/42</span></td></tr>
  </tbody>
</table>

---

## RESUMO DE CLASSES

<table class="gh-table">
  <thead>
    <tr>
      <th>ID da Classe</th>
      <th>Descrição</th>
      <th>Tipo</th>
      <th>Condição</th>
    </tr>
  </thead>
  <tbody>
    <tr class="gh-strip-odd"><td>V1</td><td>Linha válida [0,1,2]</td><td>Válida</td><td>C1</td></tr>
    <tr class="gh-strip-even"><td>V2</td><td>Coluna válida [0,1,2]</td><td>Válida</td><td>C2</td></tr>
    <tr class="gh-strip-odd"><td>V3</td><td>Célula vazia</td><td>Válida</td><td>C3</td></tr>
    <tr class="gh-strip-even"><td>V4</td><td>Símbolo X</td><td>Válida</td><td>C4</td></tr>
    <tr class="gh-strip-odd"><td>V5</td><td>Símbolo O</td><td>Válida</td><td>C4</td></tr>
    <tr class="gh-strip-even"><td>V6</td><td>Estado ATIVO</td><td>Válida</td><td>C5</td></tr>
    <tr class="gh-strip-odd"><td>V7</td><td>Turno correto</td><td>Válida</td><td>C6</td></tr>
    <tr class="gh-strip-even"><td>V8</td><td>3 símbolos iguais</td><td>Válida</td><td>C7</td></tr>
    <tr class="gh-strip-odd"><td>V9</td><td>Tabuleiro parcial</td><td>Válida</td><td>C8</td></tr>
    <tr class="gh-strip-even"><td>V10</td><td>Tabuleiro cheio</td><td>Válida</td><td>C8</td></tr>
    <tr class="gh-strip-odd"><td>I1</td><td>Linha &lt; 0</td><td>Inválida</td><td>C1</td></tr>
    <tr class="gh-strip-even"><td>I2</td><td>Linha &gt; 2</td><td>Inválida</td><td>C1</td></tr>
    <tr class="gh-strip-odd"><td>I3</td><td>Coluna &lt; 0</td><td>Inválida</td><td>C2</td></tr>
    <tr class="gh-strip-even"><td>I4</td><td>Coluna &gt; 2</td><td>Inválida</td><td>C2</td></tr>
    <tr class="gh-strip-odd"><td>I5</td><td>Célula contém X</td><td>Inválida</td><td>C3</td></tr>
    <tr class="gh-strip-even"><td>I6</td><td>Célula contém O</td><td>Inválida</td><td>C3</td></tr>
    <tr class="gh-strip-odd"><td>I7</td><td>Símbolo inválido</td><td>Inválida</td><td>C4</td></tr>
    <tr class="gh-strip-even"><td>I8</td><td>Estado VITÓRIA</td><td>Inválida</td><td>C5</td></tr>
    <tr class="gh-strip-odd"><td>I9</td><td>Estado EMPATE</td><td>Inválida</td><td>C5</td></tr>
    <tr class="gh-strip-even"><td>I10</td><td>Turno incorreto</td><td>Inválida</td><td>C6</td></tr>
  </tbody>
</table>

**Total:** 20 Classes (10 Válidas + 10 Inválidas)

---

## ESTATÍSTICAS FINAIS

- **Total de Casos de Teste:** 42
- **Total de Classes de Equivalência:** 20
- **Total de Condições:** 8
- **Total de Causas:** 10
- **Total de Efeitos:** 8
- **Total de Possibilidades de Vitória:** 8
- **Cobertura Esperada:** 100%
