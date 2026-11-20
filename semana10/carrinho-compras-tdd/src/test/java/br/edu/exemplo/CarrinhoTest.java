package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarrinhoTest {

    private static final double DELTA = 0.001;

    // Requisito 1 — Carrinho vazio
    @Test
    void carrinhoNovoDeveTerTotalZero() {
        Carrinho carrinho = new Carrinho();

        assertEquals(0, carrinho.getTotal(), DELTA);
    }

    // Requisito 2 — Adicionar um produto
    @Test
    void deveAdicionarUmProduto() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 100));

        assertEquals(100, carrinho.getTotal(), DELTA);
    }

    // Requisito 3 — Adicionar vários produtos
    @Test
    void deveSomarVariosProdutos() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 100));
        carrinho.adicionar(new Produto("Mouse", 50));

        assertEquals(150, carrinho.getTotal(), DELTA);
    }

    // Requisito 4 — Remover produto
    @Test
    void deveRemoverProduto() {
        Carrinho carrinho = new Carrinho();
        Produto teclado = new Produto("Teclado", 100);
        Produto mouse = new Produto("Mouse", 50);
        carrinho.adicionar(teclado);
        carrinho.adicionar(mouse);

        carrinho.remover(mouse);

        assertEquals(100, carrinho.getTotal(), DELTA);
    }

    // Requisito 5 — Produto com preço inválido (decisão: lançar exceção)
    @Test
    void naoDevePermitirProdutoComPrecoNegativo() {
        Carrinho carrinho = new Carrinho();

        assertThrows(IllegalArgumentException.class,
                () -> carrinho.adicionar(new Produto("Teclado", -100)));
        assertEquals(0, carrinho.getTotal(), DELTA);
    }

    // Requisito 6 — Desconto de 10% para compras a partir de R$ 500,00
    @Test
    void deveAplicarDescontoParaCompraAcimaDe500() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 600));

        assertEquals(540, carrinho.getTotal(), DELTA);
    }

    @Test
    void naoDeveAplicarDescontoEm49999() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 499.99));

        assertEquals(499.99, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoEm500() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 500));

        assertEquals(450, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoEm50001() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 500.01));

        assertEquals(450.009, carrinho.getTotal(), DELTA);
    }
}
