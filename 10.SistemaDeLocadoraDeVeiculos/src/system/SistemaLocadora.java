package system;

import services.ClienteService;

public class SistemaLocadora {

    private final ClienteService clienteService;

    public SistemaLocadora() {
        this.clienteService = new ClienteService();
    }

    public ClienteService getClienteService() {
        return clienteService;
    }
}
