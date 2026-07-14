package system;

import services.ClienteService;

public class SistemaPedidos {

    private ClienteService clienteService;

    public SistemaPedidos() {
        this.clienteService = new ClienteService();
    }

    public ClienteService getClienteService() {
        return clienteService;
    }
}
