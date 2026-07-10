package system;

import services.ClienteService;

public class SistemaBanco {

    private final ClienteService clienteService;


    public SistemaBanco() {
        this.clienteService = new ClienteService();
    }


    public ClienteService getClienteService() {
        return clienteService;
    }
}
