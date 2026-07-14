package services;

import entities.Cliente;
import entities.ItemPedido;
import entities.Pedido;
import entities.Produto;
import enums.StatusPedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoService {

    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    private final List<Pedido> pedidos = new ArrayList<>();

    public PedidoService(ClienteService clienteService, ProdutoService produtoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;

        carregarPedidosMock();
        carregarItensPedidoMock();
    }

    public List<Pedido> listarPedidos() {
        return Collections.unmodifiableList(pedidos);
    }

    public Pedido criarPedido(int idCliente){

        Cliente cliente = clienteService.buscarClientePorId(idCliente);

        if(cliente == null){
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        Pedido pedido = new Pedido(cliente, LocalDate.now(), StatusPedido.PENDENTE);
        pedidos.add(pedido);
        return pedido;
    }

    public Pedido buscarPedidoPorId(int id){

        for(Pedido pedido : pedidos){
            if(pedido.getId() == id){return pedido;}
        }
        return null;
    }

    public void adicionarItem(int idPedido, int idProduto, int quantidade){

        Pedido pedido = buscarPedidoPorId(idPedido);

        if(pedido == null){throw new IllegalArgumentException("Numero do pedido não existe");}

        Produto produto = produtoService.buscarProdutoPorId(idProduto);

        if(produto == null){throw new IllegalArgumentException("Produto não encontrado");}

        if(quantidade <= 0){
            throw new IllegalArgumentException("Quantidade insuficiente");
        }
        produto.diminuirEstoque(quantidade);

        ItemPedido itemPedido = new ItemPedido(produto,quantidade,produto.getPreco());
        pedido.adicionarItem(itemPedido);
    }


    private void carregarPedidosMock() {
        criarPedido(1);
        criarPedido(2);
        criarPedido(3);
        criarPedido(4);
        criarPedido(5);
        criarPedido(6);
    }

    private void carregarItensPedidoMock() {

        adicionarItem(1, 1, 2);
        adicionarItem(1, 3, 1);

        adicionarItem(2, 2, 3);
        adicionarItem(2, 5, 1);

        adicionarItem(3, 4, 2);

        adicionarItem(4, 6, 1);
        adicionarItem(4, 1, 1);

        adicionarItem(5, 3, 4);

        adicionarItem(6, 2, 2);
        adicionarItem(6, 5, 2);
    }
}
