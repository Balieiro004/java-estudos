package system;

import services.ClienteService;
import services.VeiculoService;

public class SistemaLocadora {

    private final ClienteService clienteService;
    private final VeiculoService veiculoService;

    public SistemaLocadora() {

        this.clienteService = new ClienteService();
        this.veiculoService = new VeiculoService();
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public VeiculoService getVeiculoService() {return veiculoService;}
}
