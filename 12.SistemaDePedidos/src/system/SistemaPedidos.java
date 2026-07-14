package system;

import services.ClienteService;
import services.ProdutoService;

public class SistemaPedidos {

    private ClienteService clienteService;
    private ProdutoService produtoService;

    public SistemaPedidos() {
        this.clienteService = new ClienteService();
        this.produtoService = new ProdutoService();
    }

    public ClienteService getClienteService() {
        return clienteService;
    }
    public ProdutoService getProdutoService() {return produtoService;}
}
