package org.example.entities;

import org.example.enums.StatusPedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    void deveCriarPedidoComStatusAberto(){
        Pedido pedido = new Pedido();

        assertEquals(StatusPedido.ABERTO, pedido.getStatus());
    }

    @Test
    void deveCriarPedidoSemItens(){
        Pedido pedido = new Pedido();

        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void deveAdicionarItemAoPedido(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);

        assertEquals(1, pedido.getItens().size());
        assertEquals(item, pedido.getItens().getFirst());
    }

    @Test
    void deveImpedirAdicionarItemNulo(){
        Pedido pedido = new Pedido();

        assertThrows(IllegalArgumentException.class, () -> {
            pedido.adicionarItem(null);
        });
    }

    @Test
    void deveImpedirAdicionarItemEmPedidoCancelado(){
        Pedido pedido = new Pedido();
        pedido.cancelar();

        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        assertThrows(IllegalStateException.class, () -> {
            pedido.adicionarItem(item);
        });
    }

    @Test
    void deveCancelarPedido(){
        Pedido pedido = new Pedido();
        pedido.cancelar();

        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
    }

    @Test
    void deveImpedirCancelarPedidoJaCancelado(){
        Pedido pedido = new Pedido();

        pedido.cancelar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.cancelar();
        });
    }

    @Test
    void deveCalcularTotalDoPedido(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);

        assertEquals(200, pedido.calcularTotal());
    }

    @Test
    void deveCalcularTotalDoPedidoComVariosItens(){
        Pedido pedido = new Pedido();

        Produto notebook = new Produto("Notebook", 100, 10);
        Produto mouse = new Produto("Mouse", 50, 20);

        ItemPedido itemNotebook = new ItemPedido(notebook, 2);
        ItemPedido itemMouse = new ItemPedido(mouse, 3);

        pedido.adicionarItem(itemNotebook);
        pedido.adicionarItem(itemMouse);

        assertEquals(350, pedido.calcularTotal());
    }

    @Test
    void deveCalcularTotalZeroQuandoPedidoNaoPossuiItens(){
        Pedido pedido = new Pedido();

        assertEquals(0, pedido.calcularTotal());
    }

    @Test
    void deveBaixarEstoqueAoAdicionarItemAoPedido(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);

        assertEquals(8, produto.getEstoque());
    }

    @Test
    void deveImpedirAdicionarItemComQuantidadeMaiorQueEstoque(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 2);
        ItemPedido item = new ItemPedido(produto, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            pedido.adicionarItem(item);
        });
    }

    @Test
    void naoDeveAdicionarItemAoPedidoQuandoEstoqueForInsuficiente(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 2);
        ItemPedido item = new ItemPedido(produto, 3);

        assertThrows(IllegalArgumentException.class, () -> {
            pedido.adicionarItem(item);
        });

        assertTrue(pedido.getItens().isEmpty());
    }
}
