package system;

import services.ClienteService;
import services.QuartoService;
import services.ReservaService;

public class SistemaHotel {

    private ClienteService clienteService;
    private QuartoService quartoService;
    private ReservaService reservaService;

    public SistemaHotel() {

        this.clienteService = new ClienteService();
        this.quartoService = new QuartoService();
        this.reservaService = new ReservaService(clienteService, quartoService);
    }

    public ClienteService getClienteService() {return clienteService;}
    public QuartoService getQuartoService() {return quartoService;}
    public ReservaService getReservaService() {return reservaService;}

}
