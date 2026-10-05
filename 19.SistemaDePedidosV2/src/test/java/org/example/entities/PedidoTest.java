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

    @Test
    void devePagarPedidoAberto(){
        Pedido pedido = new Pedido();

        pedido.pagar();

        assertEquals(StatusPedido.PAGO, pedido.getStatus());
    }

    @Test
    void deveImpedirPagarPedidoJaPago(){
        Pedido pedido = new Pedido();
        pedido.pagar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.pagar();
        });
    }

    @Test
    void deveImpedirPagarPedidoJaCancelado(){
        Pedido pedido = new Pedido();
        pedido.cancelar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.pagar();
        });
    }

    @Test
    void deveEnviarPedidoPago(){
        Pedido pedido = new Pedido();
        pedido.pagar();
        pedido.enviar();

        assertEquals(StatusPedido.ENVIADO, pedido.getStatus());
    }

    @Test
    void deveImpedirEnviarPedidoAberto(){
        Pedido pedido = new Pedido();

        assertThrows(IllegalStateException.class, () -> {
            pedido.enviar();
        });
    }

    @Test
    void deveImpedirEnviarPedidoCancelado(){
        Pedido pedido = new Pedido();

        pedido.cancelar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.enviar();
        });
    }

    @Test
    void deveImpedirEnviarPedidoJaEnviado(){
        Pedido pedido = new Pedido();

        pedido.pagar();
        pedido.enviar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.enviar();
        });
    }

    @Test
    void deveImpedirCancelarPedidoPago(){
        Pedido pedido = new Pedido();

        pedido.pagar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.cancelar();
        });
    }

    @Test
    void deveImpedirCancelarPedidoEnviado(){
        Pedido pedido = new Pedido();

        pedido.pagar();
        pedido.enviar();
        assertThrows(IllegalStateException.class, () -> {
            pedido.cancelar();
        });
    }

    @Test
    void deveImpedirPagarPedidoJaEnviado(){
        Pedido pedido = new Pedido();

        pedido.pagar();
        pedido.enviar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.pagar();
        });
    }

    @Test
    void deveRemoverItemDoPedido(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);

        pedido.removerItem(item);

        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void deveImpedirRemoverItemDePedidoPago(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);
        pedido.pagar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.removerItem(item);
        });
    }

    @Test
    void deveImpedirRemoverItemDePedidoEnviado(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);
        pedido.pagar();
        pedido.enviar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.removerItem(item);
        });
    }

    @Test
    void deveImpedirRemoverItemDePedidoCancelado(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);
        pedido.cancelar();

        assertThrows(IllegalStateException.class, () -> {
            pedido.removerItem(item);
        });
    }

    @Test
    void deveDevolverEstoqueAoRemoverItemDoPedido(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);

        assertEquals(8, produto.getEstoque());

        pedido.removerItem(item);

        assertEquals(10, produto.getEstoque());
    }

    @Test
    void deveDevolverEstoqueAoCancelarPedido(){
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);

        assertEquals(8, produto.getEstoque());

        pedido.cancelar();

        assertEquals(10, produto.getEstoque());
    }

    @Test
    void deveDevolverEstoqueDeTodosOsItensAoCancelarPedido(){
        Pedido pedido = new Pedido();

        Produto notebook = new Produto("Notebook", 100, 10);
        Produto mouse = new Produto("Mouse", 50, 20);

        ItemPedido itemNotebook = new ItemPedido(notebook, 2);
        ItemPedido itemMouse = new ItemPedido(mouse, 3);

        pedido.adicionarItem(itemNotebook);
        pedido.adicionarItem(itemMouse);

        assertEquals(8, notebook.getEstoque());
        assertEquals(17, mouse.getEstoque());

        pedido.cancelar();

        assertEquals(10, notebook.getEstoque());
        assertEquals(20, mouse.getEstoque());
    }

    @Test
    void deveImpedirRemoverItemQueNaoPertenceAoPedido(){
        Pedido pedido = new Pedido();

        Produto notebook = new Produto("Notebook", 100, 10);
        Produto mouse = new Produto("Mouse", 50, 20);

        ItemPedido itemNotebook = new ItemPedido(notebook, 2);
        ItemPedido itemMouse = new ItemPedido(mouse, 3);

        pedido.adicionarItem(itemNotebook);

        assertThrows(IllegalArgumentException.class, () -> {
            pedido.removerItem(itemMouse);
        });
    }

    @Test
    void naoDeveAlterarEstoqueAoTentarRemoverItemQueNaoPertenceAoPedido(){
        Pedido pedido = new Pedido();

        Produto notebook = new Produto("Notebook", 100, 10);
        Produto mouse = new Produto("Mouse", 50, 20);

        ItemPedido itemNotebook = new ItemPedido(notebook, 2);
        ItemPedido itemMouse = new ItemPedido(mouse, 3);

        pedido.adicionarItem(itemNotebook);

        assertEquals(20, mouse.getEstoque());

        assertThrows(IllegalArgumentException.class, () -> {
            pedido.removerItem(itemMouse);
        });

        assertEquals(20, mouse.getEstoque());
    }

    @Test
    void deveImpedirAdicionarItemEmPedidoPago(){
        Pedido pedido = new Pedido();

        pedido.pagar();

        Produto produto  = new Produto("Notebook", 100, 10);
        ItemPedido item  = new ItemPedido(produto , 2);

        assertThrows(IllegalStateException.class, () -> {
            pedido.adicionarItem(item);
        });
    }

    @Test
    void deveImpedirAdicionarItemEmPedidoEnviado(){
        Pedido pedido = new Pedido();

        pedido.pagar();
        pedido.enviar();

        Produto produto  = new Produto("Notebook", 100, 10);
        ItemPedido item  = new ItemPedido(produto, 2);

        assertThrows(IllegalStateException.class, () -> {
            pedido.adicionarItem(item);
        });
    }

    @Test
    void naoDeveAlterarEstoqueAoTentarAdicionarItemEmPedidoPago(){
        Pedido pedido = new Pedido();

        pedido.pagar();

        Produto produto  = new Produto("Notebook", 100, 10);
        ItemPedido item  = new ItemPedido(produto, 2);

        assertEquals(10, produto.getEstoque());

        assertThrows(IllegalStateException.class, () -> {
            pedido.adicionarItem(item);
        });

        assertEquals(10, produto.getEstoque());
    }

    @Test
    void naoDeveAlterarEstoqueAoTentarAdicionarItemEmPedidoEnviado(){
        Pedido pedido = new Pedido();

        pedido.pagar();
        pedido.enviar();

        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        assertEquals(10, produto.getEstoque());

        assertThrows(IllegalStateException.class, () -> {
            pedido.adicionarItem(item);
        });

        assertEquals(10, produto.getEstoque());
    }

    @Test
    void deveRemoverSomenteOItemSelecionado(){
        Pedido pedido = new Pedido();

        Produto notebook = new Produto("Notebook", 100, 10);
        Produto mouse = new Produto("Mouse", 50, 20);

        ItemPedido itemNotebook = new ItemPedido(notebook, 2);
        ItemPedido itemMouse = new ItemPedido(mouse, 3);

        pedido.adicionarItem(itemNotebook);
        pedido.adicionarItem(itemMouse);

        pedido.removerItem(itemNotebook);

        assertEquals(1, pedido.getItens().size());
        assertTrue(pedido.getItens().contains(itemMouse));
        assertEquals(10, notebook.getEstoque());
        assertEquals(17, mouse.getEstoque());
    }

    @Test
    void deveImpedirRemoverItemNulo(){
        Pedido pedido = new Pedido();

        assertThrows(IllegalArgumentException.class, () -> {
            pedido.removerItem(null);
        });
    }

    @Test
    void deveManterItensAoCancelarPedido(){
        Pedido pedido = new Pedido();

        Produto notebook = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(notebook, 2);

        pedido.adicionarItem(item);

        pedido.cancelar();

        assertEquals(1, pedido.getItens().size());
        assertTrue(pedido.getItens().contains(item));
    }

    @Test
    void naoDevePermitirAlterarListaDeItensDiretamente(){
        Pedido pedido = new Pedido();

        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);

        assertThrows(UnsupportedOperationException.class, () -> {
            pedido.getItens().clear();
        });

        assertEquals(1, pedido.getItens().size());
    }

    @Test
    void naoDevePermitirRemoverItemDiretamenteDaLista(){
        Pedido pedido = new Pedido();

        Produto produto = new Produto("Notebook", 100, 10);
        ItemPedido item = new ItemPedido(produto, 2);

        pedido.adicionarItem(item);

        assertThrows(UnsupportedOperationException.class, () -> {
            pedido.getItens().remove(item);
        });

        assertEquals(1, pedido.getItens().size());
        assertEquals(8, produto.getEstoque());
    }
}
