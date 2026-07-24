package system;

import services.ClienteService;
import services.QuartoService;

public class SistemaHotel {

    private ClienteService clienteService;
    private QuartoService quartoService;

    public SistemaHotel() {

        this.clienteService = new ClienteService();
        this.quartoService = new QuartoService();
    }

    public ClienteService getClienteService() {return clienteService;}
    public QuartoService getQuartoService() {return quartoService;}

}
