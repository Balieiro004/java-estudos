package system;

import services.ClienteService;
import services.ContaService;

public class SistemaBanco {

    private final ClienteService clienteService;
    private final ContaService contaService;


    public SistemaBanco() {

        this.clienteService = new ClienteService();
        this.contaService = new ContaService(clienteService);
    }


    public ClienteService getClienteService() {
        return clienteService;
    }

    public ContaService getContaService() {
        return contaService;
    }
}
