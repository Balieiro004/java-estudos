package org.example.entities;

import org.example.enums.StatusProduto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    void deveImpedirProdutoComPrecoNegativo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Notebook", -100, 10);
        });
    }

    @Test
    void deveImpedirProdutoComPrecoZero(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Notebook", 0,10);
        });
    }

    @Test
    void devePermitirProdutoComPrecoValido(){
        assertDoesNotThrow(() -> new Produto("Notebook", 100, 10));
    }

    @Test
    void deveImpedirProdutoComNomeVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("", 100, 10);
        });
    }

    @Test
    void deveImpedirProdutoComNomeEmBranco(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("   ", 100, 10);
        });
    }

    @Test
    void deveRetornarNomeDoProduto(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertEquals("Notebook", produto.getNome());
    }

    @Test
    void deveRetornarPrecoDoProduto(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertEquals(100, produto.getPreco());
    }

    @Test
    void estoqueNaoPodeSerNegativo(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Notebook", 100, -1);
        });
    }

    @Test
    void deveRetornarEstoqueDoProduto(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertEquals(10, produto.getEstoque());
    }

    @Test
    void deveBaixarEstoqueDoProduto(){
        Produto produto = new Produto("Notebook", 100, 10);

        produto.baixarEstoque(3);

        assertEquals(7, produto.getEstoque());
    }

    @Test
    void deveImpedirBaixaMaiorQueEstoqueDisponivel(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            produto.baixarEstoque(11);
        });
    }

    @Test
    void deveImpedirBaixaComQuantidadeNegativa(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            produto.baixarEstoque(-5);
        });
    }

    @Test
    void deveImpedirBaixaComQuantidadeZero(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            produto.baixarEstoque(0);
        });
    }

    @Test
    void deveAumentaroEstoqueDoProduto(){
        Produto produto = new Produto("Notebook", 100, 10);

        produto.reporEstoque(5);

        assertEquals(15, produto.getEstoque());
    }

    @Test
    void deveImpedirReposicaoComQuantidadeZero(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            produto.reporEstoque(0);
        });
    }

    @Test
    void deveCriarProdutoComoAtivo(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertEquals(StatusProduto.ATIVO, produto.getStatus());
    }

    @Test
    void deveDesativarProduto(){
        Produto produto = new Produto("Notebook", 100, 10);

        produto.desativar();

        assertEquals(StatusProduto.INATIVO, produto.getStatus());
    }

    @Test
    void deveAtivarProdutoNovamente(){
        Produto produto = new Produto("Notebook", 100, 10);

        produto.desativar();
        produto.ativar();

        assertEquals(StatusProduto.ATIVO, produto.getStatus());
    }

    @Test
    void deveImpedirDesativarProdutoQueJaEstaInativo() {
        Produto produto = new Produto("Notebook", 100, 10);

        produto.desativar();

        assertThrows(IllegalStateException.class, () -> {
            produto.desativar();
        });
    }

    @Test
    void deveImpedirAtivarProdutoQueJaEstaAtivo(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertThrows(IllegalStateException.class, produto::ativar);
    }

    @Test
    void deveCalcularValorDeQuantidadeDeProdutos(){
        Produto produto = new Produto("Notebook", 100, 10);

        double resultado = produto.calcularValor(3);

        assertEquals(300,resultado);
    }

    @Test
    void deveImpedirCalculoComQuantidadeZero(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            produto.calcularValor(0);
        });
    }

    @Test
    void deveImpedirCalculoComQuantidadeNegativa(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            produto.calcularValor(-5);
        });
    }

    @Test
    void deveImpedirBaixaDeEstoqueDeProdutoInativo(){
        Produto produto = new Produto("Notebook", 100, 10);

        produto.desativar();

        assertThrows(IllegalStateException.class, () -> {
            produto.baixarEstoque(2);
        });
    }
}
