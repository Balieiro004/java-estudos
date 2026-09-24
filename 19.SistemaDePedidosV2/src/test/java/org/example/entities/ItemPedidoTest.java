package org.example.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemPedidoTest {

    @Test
    void deveCriarItemPedidoComProdutoEQuantidade(){
        Produto produto = new Produto("Notebook", 100, 10);

        ItemPedido item = new ItemPedido(produto, 2);

        assertEquals(produto, item.getProduto());
        assertEquals(2, item.getQuantidade());
    }

    @Test
    void deveImpedirItemPedidoComQuantidadeZero(){
        Produto produto = new Produto("Notebook", 100, 10);


        assertThrows(IllegalArgumentException.class, () -> {
            new ItemPedido(produto, 0);
        });
    }

    @Test
    void deveImpedirItemPedidoComQuantidadeNegativo(){
        Produto produto = new Produto("Notebook", 100, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            new ItemPedido(produto, -1);
        });
    }

    @Test
    void deveCalcularSubtotalDoItemPedido(){
        Produto produto = new Produto("Notebook", 100, 10);

        ItemPedido item = new ItemPedido(produto, 3);

        assertEquals(300, item.calcularSubtotal());
    }

    @Test
    void deveImpedirItemPedidoSemProduto(){
        assertThrows(IllegalArgumentException.class, () -> {
           new ItemPedido(null, 2);
        });
    }

    @Test
    void deveImpedirItemPedidoComProdutoInativo(){
        Produto produto = new Produto("Notebook", 100, 10);
        produto.desativar();

        assertThrows(IllegalArgumentException.class, () -> {
            new ItemPedido(produto, 2);
        });
    }

}
