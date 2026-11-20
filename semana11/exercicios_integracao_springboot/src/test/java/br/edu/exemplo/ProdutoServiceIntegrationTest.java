package br.edu.exemplo;
import br.edu.exemplo.model.Produto;
import br.edu.exemplo.repository.ProdutoRepository;
import br.edu.exemplo.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional
class ProdutoServiceIntegrationTest {
 @Autowired ProdutoService service;
 @Autowired ProdutoRepository repository;
 @Test void deveCadastrarERecuperarProduto(){
  // TODO EX1: cadastrar Teclado por 150, confirmar ID e buscar pelo repositório.
  // Conferir nome e preço do registro recuperado.
  Produto salvo = service.cadastrar("Teclado", 150.0);
  assertNotNull(salvo.getId());

  Produto recuperado = repository.findById(salvo.getId()).orElseThrow();
  assertEquals("Teclado", recuperado.getNome());
  assertEquals(150.0, recuperado.getPreco(), 0.001);
 }
 @Test void naoDevePersistirProdutoComNomeEmBranco(){
  // TODO EX1: contar registros, tentar cadastrar nome em branco,
  // verificar IllegalArgumentException e que a contagem não mudou.
  long antes = repository.count();

  assertThrows(IllegalArgumentException.class, () -> service.cadastrar(" ", 80.0));

  assertEquals(antes, repository.count());
 }
 @Test void deveAtualizarPrecoPersistido(){
  // TODO DESAFIO: cadastrar, atualizar preço e buscar novamente no repositório.
  Produto salvo = service.cadastrar("Mouse", 80.0);

  service.atualizarPreco(salvo.getId(), 95.0);

  Produto recuperado = repository.findById(salvo.getId()).orElseThrow();
  assertEquals(95.0, recuperado.getPreco(), 0.001);
 }
}
