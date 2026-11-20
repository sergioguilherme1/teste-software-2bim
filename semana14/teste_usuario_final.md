# Teste de Usuário Final — Mercado Livre

## 1. Objetivo do Teste

Avaliar a facilidade de uso, eficiência e clareza da interface durante as etapas de **busca, comparação e simulação de compra** de um produto no site Mercado Livre.

## 2. Cenário de Uso

O usuário procura **um fone de ouvido sem fio** para uso diário e quer comprar pelo Mercado Livre. Seu objetivo é **encontrar um modelo com boa avaliação**, comparar opções e **simular a compra até o carrinho** (sem finalizar o pagamento).

## 3. Tarefas Executadas

1. Acessar o site https://www.mercadolivre.com.br.
2. Pesquisar "**fone de ouvido Bluetooth**".
3. Usar os filtros para:
   - selecionar apenas produtos **com entrega Full**;
   - ordenar pelos **melhores avaliados**.
4. Escolher um produto e **acessar os detalhes**.
5. Adicionar o item ao carrinho.
6. Simular o início da compra (sem concluir o pagamento).
7. Retornar à tela inicial.

## 4. Recursos e Organização

| Papel | Descrição |
|---|---|
| Usuário final | U1 a U5, executando as tarefas como compradores. |
| Avaliador | Observa e registra tempo, erros e comentários. |

**Ferramentas:** navegador web, cronômetro, planilha de observação (este documento) e formulário SUS.

## 5. Planilha de Observação

A planilha com os dados de observação e métricas, está em [`planilha_teste_usuario_final.xlsx`](planilha_teste_usuario_final.xlsx).

## 6. Dados Coletados

### Tempo de execução

| Usuário | Tempo total (s) | Tempo total (min) |
|---|---:|---:|
| U1 | 120 | 2:00 |
| U2 | 174 | 2:54 |
| U3 | 147 | 2:27 |
| U4 | 270 | 4:30 |
| U5 | 145 | 2:25 |
| **Média** | **171,2** | **2:51** |

Tempo médio por tarefa (s): acessar site 9,2 · buscar produto 14,2 · **aplicar filtros 58,0** · abrir detalhes 26,4 · adicionar ao carrinho 21,8 · simular compra 35,6 · retornar à tela inicial 6,0.

### Taxa de sucesso

- Concluíram o fluxo completo **sem ajuda**: 4 de 5 usuários (**80%**). U4 precisou de ajuda na simulação da compra.
- Adicionaram o produto ao carrinho: 5 de 5 usuários (**100%**).

### Erros cometidos

| Usuário | Erros | Principais ocorrências |
|---|---:|---|
| U1 | 0 | — |
| U2 | 2 | Confusão nos filtros (localização do filtro Full). |
| U3 | 1 | Clique incorreto em anúncio patrocinado. |
| U4 | 5 | Confusão nos filtros (3), variação obrigatória não selecionada (1), perda do caminho na simulação da compra (1). |
| U5 | 1 | Confusão nos filtros (localização do filtro Full). |
| **Total** | **9** | Média de 1,8 erro por usuário; 6 dos 9 erros ocorreram na tarefa **Aplicar filtros**. |

## 7. Critérios de Sucesso

| Critério | Meta | Resultado | Situação |
|---|---|---|:---:|
| Usuários que adicionaram o produto ao carrinho | ≥ 90% | 100% (5/5) | ✅ Atendido |
| Tempo médio para concluir o teste | ≤ 4 minutos | 2 min 51 s | ✅ Atendido |

Observação: um usuário (U4) ultrapassou individualmente os 4 minutos, mas a média ficou dentro da meta.

## 8. Formulário SUS (System Usability Scale)

Aplicado a cada participante logo após a execução das tarefas. Escala de 1 (discordo totalmente) a 5 (concordo totalmente).

**Formulário (Google Forms):** https://docs.google.com/forms/d/e/1FAIpQLSeRLdx_Jzdx_lcZKcQEZCtvZ7Kbbtsm5LCA24L7JyoQou4wNw/viewform?usp=dialog


| Nº | Afirmação |
|---|---|
| 1 | Eu acho que gostaria de usar este sistema com frequência. |
| 2 | Eu achei o sistema desnecessariamente complexo. |
| 3 | Eu achei o sistema fácil de usar. |
| 4 | Eu acho que precisaria de ajuda de uma pessoa com conhecimentos técnicos para usar o sistema. |
| 5 | Eu achei que as várias funções do sistema estavam bem integradas. |
| 6 | Eu achei que havia muita inconsistência no sistema. |
| 7 | Eu imagino que a maioria das pessoas aprenderia a usar este sistema rapidamente. |
| 8 | Eu achei o sistema muito complicado de usar. |
| 9 | Eu me senti muito confiante usando o sistema. |
| 10 | Eu precisei aprender muitas coisas antes de conseguir usar o sistema. |

**Cálculo:** afirmações ímpares valem (resposta − 1); afirmações pares valem (5 − resposta); a soma é multiplicada por 2,5, resultando em uma pontuação de 0 a 100. A média de referência do SUS é 68.

### Respostas

| Usuário | Q1 | Q2 | Q3 | Q4 | Q5 | Q6 | Q7 | Q8 | Q9 | Q10 | Pontuação SUS |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| U1 | 4 | 2 | 4 | 1 | 4 | 2 | 5 | 1 | 4 | 2 | 82,5 |
| U2 | 4 | 2 | 3 | 2 | 4 | 2 | 4 | 2 | 3 | 2 | 70,0 |
| U3 | 5 | 1 | 4 | 1 | 4 | 2 | 5 | 1 | 5 | 1 | 92,5 |
| U4 | 3 | 3 | 3 | 3 | 3 | 3 | 4 | 3 | 2 | 3 | 50,0 |
| U5 | 4 | 2 | 4 | 2 | 4 | 1 | 4 | 2 | 4 | 2 | 77,5 |
| **Média** | | | | | | | | | | | **74,5** |

## 9. Conclusão

Com os dados obtidos, os dois critérios de sucesso foram atendidos: 100% dos usuários adicionaram o produto ao carrinho e o tempo médio foi de 2 min 51 s. A pontuação SUS média (74,5) ficou acima da referência de 68. O principal ponto de dificuldade foi a tarefa **Aplicar filtros**: ela concentrou a maior parte dos erros (6 de 9) e o maior tempo médio (58 s), por causa da localização do filtro de entrega Full e das opções de ordenação. A simulação da compra também foi apontada como etapa sensível, pois o usuário que precisou de ajuda não compreendeu a etapa de login/endereço.