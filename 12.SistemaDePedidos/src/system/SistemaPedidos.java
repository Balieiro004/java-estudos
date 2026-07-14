package system;

import services.ClienteService;
import services.PedidoService;
import services.ProdutoService;

public class SistemaPedidos {

    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PedidoService pedidoService;

    public SistemaPedidos() {
        this.clienteService = new ClienteService();
        this.produtoService = new ProdutoService();
        this.pedidoService = new PedidoService(clienteService, produtoService);
    }

    public ClienteService getClienteService() {return clienteService;}

    public ProdutoService getProdutoService() {return produtoService;}

    public PedidoService getPedidoService() {return pedidoService;}
}
