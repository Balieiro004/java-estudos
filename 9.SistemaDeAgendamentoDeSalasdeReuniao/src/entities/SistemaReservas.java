package entities;

import services.ColaboradorService;
import services.SalaService;

public class SistemaReservas {

    private SalaService salaService = new SalaService();
    private ColaboradorService colaboradorService = new ColaboradorService();

    public SistemaReservas() {}

    public SalaService getSalaService() {
        return salaService;
    }

    public ColaboradorService getColaboradorService() {
        return colaboradorService;
    }
}
