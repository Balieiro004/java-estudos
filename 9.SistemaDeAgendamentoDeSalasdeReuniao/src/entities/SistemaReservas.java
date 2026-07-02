package entities;

import services.SalaService;

public class SistemaReservas {

    private SalaService salaService = new SalaService();
    public SistemaReservas() {}

    public SalaService getSalaService() {
        return salaService;
    }
}
