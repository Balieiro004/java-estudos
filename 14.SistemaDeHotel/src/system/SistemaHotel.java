package system;

import services.ClienteService;

public class SistemaHotel {

    private ClienteService clienteService;

    public SistemaHotel() {
        this.clienteService = new ClienteService();
    }

    public ClienteService getClienteService() {return clienteService;}

}
