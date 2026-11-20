# Lista prática — Testes de integração com Spring Boot e JUnit 5

**Tecnologias:** Java 17, Maven, Spring Boot 3.x, Spring Data JPA, H2, JUnit 5 e MockMvc.  
**Entrega:** repositório GitHub com o código e `README.md` descrevendo os testes executados.  
**Orientação:** usar o projeto-base fornecido; completar os métodos `TODO` das classes de teste. Não utilizar mocks para substituir `ProdutoRepository` ou `ProdutoService` nos exercícios.

## Exercício 1 — Integração entre Service, Repository e banco H2

**Objetivo:** verificar que as regras de cadastro e atualização se integram à persistência real.

**Cenário:** o serviço recebe dados de um produto, valida as regras e utiliza `ProdutoRepository` para armazenar os registros.

| Caso | Preparação / entrada | Verificação esperada |
|---|---|---|
| CT01 — Cadastro válido | Cadastrar `Teclado`, preço `150.0` | O produto recebe ID e é encontrado no repositório com nome e preço corretos. |
| CT02 — Nome inválido | Tentar cadastrar nome em branco e preço `80.0` | `IllegalArgumentException`; a contagem de registros não aumenta. |
| CT03 — Atualização (desafio) | Cadastrar `Mouse` por `80.0`, atualizar para `95.0` | A consulta posterior ao repositório retorna preço `95.0`. |

**Passos:**
1. Abrir `ProdutoServiceIntegrationTest` e completar CT01 e CT02; implementar CT03 como desafio.
2. Utilizar `@SpringBootTest`, `@Autowired` e `@Transactional` já presentes no projeto.
3. Usar `assertNotNull`, `assertEquals` e `assertThrows` quando apropriado.
4. Executar `mvn test` e registrar o resultado.

**Discussão:** por que a consulta com `repository.findById()` fornece uma evidência diferente de verificar apenas o objeto retornado pelo serviço? O que `@Transactional` faz ao término de cada teste?

## Exercício 2 — Integração HTTP com MockMvc

**Objetivo:** testar o fluxo Controller → Service → Repository → H2 por meio de requisições HTTP simuladas.

| Caso | Requisição | Verificação esperada |
|---|---|---|
| CT04 — Cadastro via API | `POST /produtos` com `{"nome":"Mouse","preco":80.0}` | HTTP `200`; resposta JSON com ID numérico, nome `Mouse` e preço `80.0`. |
| CT05 — Consulta via API | Cadastrar via POST; recuperar o ID; realizar `GET /produtos/{id}` | HTTP `200`; JSON da consulta corresponde ao produto cadastrado. |

**Passos:**
1. Abrir `ProdutoControllerIntegrationTest` e completar CT04 e CT05.
2. Utilizar `mockMvc.perform(...)`, `contentType(MediaType.APPLICATION_JSON)`, `status()` e `jsonPath()`.
3. Para CT05, capturar o corpo da resposta do POST e extrair o ID com `ObjectMapper` ou `JsonNode`.
4. Executar a suíte inteira com `mvn test`.

**Discussão:** quais componentes são efetivamente exercitados? Por que o MockMvc não precisa iniciar um servidor HTTP real? Qual é a diferença entre esse teste e um teste unitário do controller com dependências simuladas?

## Critérios de avaliação sugeridos

| Critério | Pontuação |
|---|---:|
| Exercício 1 — CT01 e CT02 | 4,0 |
| Exercício 1 — CT03 (desafio) | 1,0 |
| Exercício 2 — CT04 e CT05 | 4,0 |
| README com evidências e explicação do isolamento | 1,0 |
| **Total** | **10,0** |

## Execução

```bash
mvn test
```

**Observações:** o projeto-base contém a aplicação funcional e os testes com espaços `TODO`, sem as asserções prontas. Os testes incompletos não falham automaticamente: os estudantes devem preenchê-los antes de interpretar o resultado da suíte. Em testes com `@Transactional`, o Spring normalmente desfaz a transação ao final de cada método de teste; o uso de `MockMvc` aqui ocorre na mesma aplicação e sem servidor HTTP real.

---

## Resolução

### Testes implementados

| Caso | Classe / método | O que é verificado |
|---|---|---|
| CT01 | `ProdutoServiceIntegrationTest.deveCadastrarERecuperarProduto` | `service.cadastrar("Teclado", 150.0)` gera ID; `repository.findById` retorna o registro com nome `Teclado` e preço `150.0`. |
| CT02 | `ProdutoServiceIntegrationTest.naoDevePersistirProdutoComNomeEmBranco` | `service.cadastrar(" ", 80.0)` lança `IllegalArgumentException`; `repository.count()` permanece igual ao valor anterior. |
| CT03 | `ProdutoServiceIntegrationTest.deveAtualizarPrecoPersistido` | Cadastra `Mouse` por `80.0`, chama `service.atualizarPreco(id, 95.0)` e confirma pelo `repository.findById` que o preço é `95.0`. |
| CT04 | `ProdutoControllerIntegrationTest.deveCadastrarProdutoPelaApi` | `POST /produtos` com `{"nome":"Mouse","preco":80.0}` retorna HTTP 200 e JSON com `id` numérico, `nome` `Mouse` e `preco` `80.0`. |
| CT05 | `ProdutoControllerIntegrationTest.deveBuscarProdutoPelaApi` | Cadastra via `POST`, extrai o `id` da resposta com `ObjectMapper`/`JsonNode`, executa `GET /produtos/{id}` e confere HTTP 200, `id`, `nome` e `preco`. |

Nenhum mock foi utilizado: `ProdutoService` e `ProdutoRepository` são os beans reais do Spring, e a persistência ocorre no banco H2 em memória.

### Evidência da execução (`mvn test`)

```text
[INFO] Running br.edu.exemplo.ProdutoControllerIntegrationTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 10.28 s -- in br.edu.exemplo.ProdutoControllerIntegrationTest
[INFO] Running br.edu.exemplo.ProdutoServiceIntegrationTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.486 s -- in br.edu.exemplo.ProdutoServiceIntegrationTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
```

### Discussão — Exercício 1

**Por que `repository.findById()` fornece uma evidência diferente de verificar apenas o objeto retornado pelo serviço?**
O objeto retornado pelo serviço prova apenas que o método devolveu algo com os valores esperados; ele poderia ter sido montado em memória sem nunca chegar ao banco. Consultar o repositório pelo ID confirma que o registro foi de fato persistido pela camada JPA e pode ser recuperado, ou seja, verifica a integração Service → Repository → H2, e não somente o retorno do método.

**O que `@Transactional` faz ao término de cada teste?**
Cada método de teste é executado dentro de uma transação que o Spring desfaz (*rollback*) ao final. Assim, os produtos inseridos por um teste não ficam no banco para o teste seguinte: cada teste começa com o banco no mesmo estado, garantindo isolamento e independência da ordem de execução.

### Discussão — Exercício 2

**Quais componentes são efetivamente exercitados?**
Todo o fluxo da aplicação: o mapeamento HTTP e a conversão JSON do Spring MVC (`DispatcherServlet`, Jackson), o `ProdutoController`, o `ProdutoService` com suas validações, o `ProdutoRepository` (Spring Data JPA/Hibernate) e o banco H2.

**Por que o MockMvc não precisa iniciar um servidor HTTP real?**
O MockMvc chama diretamente o `DispatcherServlet` do Spring com objetos de requisição e resposta simulados, dentro do mesmo processo do teste. Não há abertura de porta nem tráfego de rede, mas toda a infraestrutura do Spring MVC (rotas, conversão de JSON, códigos de status) é executada normalmente.

**Qual é a diferença entre esse teste e um teste unitário do controller com dependências simuladas?**
No teste unitário o `ProdutoService` seria substituído por um mock, e só a lógica do controller seria verificada; regras de validação, mapeamento JPA e persistência não seriam exercitados. No teste de integração as dependências são reais, então o teste detecta falhas na comunicação entre as camadas (por exemplo, erro de mapeamento da entidade, de serialização JSON ou de persistência), ao custo de ser mais lento por subir o contexto do Spring e o banco.

### Isolamento dos testes

- `@SpringBootTest` sobe o contexto completo da aplicação com os beans reais.
- O banco é o H2 em memória (`jdbc:h2:mem:produtos`), com esquema criado e descartado a cada execução (`ddl-auto=create-drop`), sem dependência de banco externo.
- `@Transactional` nas classes de teste faz *rollback* ao final de cada método, de modo que nenhum teste depende de dados criados por outro.
