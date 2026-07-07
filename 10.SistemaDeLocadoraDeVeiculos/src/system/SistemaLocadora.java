package system;

import services.ClienteService;
import services.LocacaoService;
import services.VeiculoService;

public class SistemaLocadora {

    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    private final LocacaoService locacaoService;

    public SistemaLocadora() {

        this.clienteService = new ClienteService();
        this.veiculoService = new VeiculoService();

        this.locacaoService = new LocacaoService(clienteService, veiculoService);
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public VeiculoService getVeiculoService() {return veiculoService;}

    public LocacaoService getLocacaoService() {return locacaoService;}
}
